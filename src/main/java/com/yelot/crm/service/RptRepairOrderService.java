package com.yelot.crm.service;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.RptRepairOrder;
import com.yelot.crm.enums.ChannelSource;
import com.yelot.crm.enums.RepairOrderStatus;
import com.yelot.crm.mapper.*;
import com.yelot.crm.vo.City;
import com.yelot.crm.vo.CityList;
import com.yelot.crm.vo.CityListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xyzloveabc
 * @2017年9月16日
 */
@Service
@Transactional
public class RptRepairOrderService {

	@Autowired
	private RptRepairOrderMapper rptRepairOrderMapper;

	@Autowired
	private RepairOrderOperatorsMapper repairOrderOperatorsMapper;

	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 分类转化为CityList
	 * 
	 * @return
	 */
	public CityListVo convertToCityListVo() {
		List<Category> categoryList = categoryMapper.findAllFirstClass();
		
		Category category = new Category("全部", 0);
		categoryList.add(0, category);
		
		for (int i = 0; categoryList != null && i < categoryList.size(); i++) {
			List<Category> children = categoryMapper.findChildren(categoryList
					.get(i).getId());

			Category categoryChildren = new Category("全部", 0);
			children.add(0, categoryChildren);
			
			categoryList.get(i).setChildren(children);
		}

		/**
		 * 以下对象转化为，需要的层次结构
		 */
		CityListVo cityListVo = new CityListVo();

		for (int i = 0; categoryList != null && i < categoryList.size(); i++) {
			List<Category> children = categoryList.get(i).getChildren();
			String p = categoryList.get(i).getName();
			CityList cityList = new CityList();
			cityList.setP(p);
			if (children != null && children.size() > 0) {
				for (int j = 0; j < children.size(); j++) {
					City city = new City();
					city.setN(children.get(j).getName());
					cityList.getC().add(city);
				}
			}
			cityListVo.getCitylist().add(cityList);
		}

		return cityListVo;
	}

	/**
	 * 分页查询
	 * @param pageHelper
	 * @return
	 */
	public List<RptRepairOrder> findByPage(String startDate, String endDate, String shopId, String firstCategory, String secondCategory, 
			String onLineUser, String shopUser, String deliverType, String customerType, String channelSource, String status, PageHelper pageHelper) {
		
		if ("全部".equals(firstCategory)) {
			firstCategory = "";
		}
		
		if ("全部".equals(secondCategory)) {
			secondCategory = "";
		}
		
		List<RptRepairOrder> rptRepairOrderList = rptRepairOrderMapper.findByPage(startDate, endDate, shopId, firstCategory, secondCategory, onLineUser, shopUser, deliverType, customerType, channelSource, getStatusList(status), pageHelper);
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			rptRepairOrder.setToday(new Date()); // 今天日期
			rptRepairOrder.setConsultCreateUserName(rptRepairOrder.getConsultCreateUserName() == null ? rptRepairOrder.getCreateUserName() : rptRepairOrder.getConsultCreateUserName()); // 首接人
			rptRepairOrder.setDeliverType(rptRepairOrder.getDeliverType() == null ? "客户上门" : rptRepairOrder.getDeliverType()); // 接单方式
			rptRepairOrder.setMaterialPayment("-1".equals(rptRepairOrder.getMaterialPayment()) ? "待定" : rptRepairOrder.getMaterialPayment()); // 料钱
			rptRepairOrder.setDiscountAmountPayment("-1".equals(rptRepairOrder.getDiscountAmountPayment()) ? "待定" : rptRepairOrder.getDiscountAmountPayment()); //优惠
			setOneRptRepairServiceItem(rptRepairOrder); // 服务项
			setOneRptRepairStatus(rptRepairOrder); // 订单状态
			setOneRptRepairChannelSource(rptRepairOrder); // 客户来源
		}
		
//		setRptRepairServiceItem(rptRepairOrderList); // 服务项
//		setRptRepairStatus(rptRepairOrderList); // 订单状态
//		setRptRepairChannelSource(rptRepairOrderList); // 客户来源
		return rptRepairOrderList;
	}
	
	/**
	 * 查询总的记录条数
	 * @return 总的记录条数
	 */
	public Integer countTotalPage(String startDate, String endDate, String shopId, String firstCategory, String secondCategory, 
			String onLineUser, String shopUser, String deliverType, String customerType, String channelSource, String status) {
		
		if ("全部".equals(firstCategory)) {
			firstCategory = "";
		}
		
		if ("全部".equals(secondCategory)) {
			secondCategory = "";
		}
		
		return rptRepairOrderMapper.countTotalPage(startDate, endDate, shopId, firstCategory, secondCategory, onLineUser, shopUser, deliverType, customerType, channelSource, getStatusList(status));
	}

	/**
	 * 订单列表中服务项转中文
	 * @param rptRepairOrderList
	 */
	private void setRptRepairServiceItem(List<RptRepairOrder> rptRepairOrderList){
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			setOneRptRepairServiceItem(rptRepairOrder);
		}
	}
	
	/**
	 * 单个订单服务项转中文
	 * @param rptRepairOrder
	 */
	private void setOneRptRepairServiceItem(RptRepairOrder rptRepairOrder) {
		String serviceItemIds = rptRepairOrder.getServiceItemIds();
		List<String> serviceItemIdsList = JSON.parseArray(serviceItemIds, String.class);
		String serviceItemNames = "";
		if (serviceItemIdsList != null && serviceItemIdsList.size() > 0) {
			for (int i = 0; i < serviceItemIdsList.size(); i++) {
				String serviceItemName = rptRepairOrderMapper
						.findServiceItemName(serviceItemIdsList.get(i));
				if (i == serviceItemIdsList.size() - 1) {
					serviceItemNames += serviceItemName;
				} else {
					serviceItemNames += serviceItemName + "，";
				}
			}
		}
		rptRepairOrder.setServiceItemNames(serviceItemNames);
	}

	/**
	 * 订单列表中订单状态转中文
	 * @param rptRepairOrderList
	 */
	private void setRptRepairStatus(List<RptRepairOrder> rptRepairOrderList){
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			setOneRptRepairStatus(rptRepairOrder);
		}
	}
	
	/**
	 * 单个订单中订单状态转中文
	 * @param rptRepairOrder
	 */
	private void setOneRptRepairStatus(RptRepairOrder rptRepairOrder){
		RepairOrderStatus[] repairOrderStatusList = RepairOrderStatus.values();
		for (RepairOrderStatus repairOrderStatus : repairOrderStatusList) {
			if (rptRepairOrder.getStatus() == repairOrderStatus.getCode()) {
				rptRepairOrder.setStatusName(repairOrderStatus.getMessage());
				break;
			}
		}
	}

	/**
	 * 订单列表中客户来源转中文
	 * @param rptRepairOrderList
	 */
	private void setRptRepairChannelSource(List<RptRepairOrder> rptRepairOrderList){
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			setOneRptRepairChannelSource(rptRepairOrder);
		}
	}
	
	/**
	 * 单个订单中客户来源转中文
	 * @param rptRepairOrder
	 */
	private void setOneRptRepairChannelSource(RptRepairOrder rptRepairOrder){
		if (rptRepairOrder.getChannelSource() != null && rptRepairOrder.getChannelSource() != "") {
			ChannelSource[] channelSourceList = ChannelSource.values();
			for (ChannelSource channelSource : channelSourceList) {
				if (rptRepairOrder.getChannelSource().equals(String.valueOf(channelSource.getCode()))) {
					rptRepairOrder.setChannelSource(channelSource.getMessage());
					break;
				}
			}
		}
	}
	
	private List<String> getStatusList(String status){
		List<String> statusList = new ArrayList<String>();
		if (status != null && !"".equals(status)) {
			String tmp[] = status.split(",");
			for (String string : tmp) {
				statusList.add(string);
			}
		}
		return statusList;
	}
	
}
