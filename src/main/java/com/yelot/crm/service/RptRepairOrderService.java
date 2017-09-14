package com.yelot.crm.service;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.entity.RptRepairOrder;
import com.yelot.crm.entity.User;
import com.yelot.crm.enums.OperatorStatus;
import com.yelot.crm.mapper.*;
import com.yelot.crm.vo.City;
import com.yelot.crm.vo.CityList;
import com.yelot.crm.vo.CityListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kee on 17/6/5.
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

		for (int i = 0; categoryList != null && i < categoryList.size(); i++) {
			List<Category> children = categoryMapper.findChildren(categoryList
					.get(i).getId());
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
	public List<RptRepairOrder> findByPage(String startDate, String endDate,
			String firstCategory, String secondCategory, String shopId,
			String status, String typeName, PageHelper pageHelper) {
		List<RptRepairOrder> rptRepairOrderList = rptRepairOrderMapper.findByPage(startDate, endDate, firstCategory, secondCategory, shopId, status, typeName, pageHelper);
		setRptRepairServiceItem(rptRepairOrderList);
		return rptRepairOrderList;
	}
	
	/**
	 * 查询总的记录条数
	 * @return 总的记录条数
	 */
	public Integer countTotalPage(String startDate, String endDate,
			String firstCategory, String secondCategory, String shopId,
			String status, String typeName) {
		return rptRepairOrderMapper.countTotalPage(startDate, endDate, firstCategory, secondCategory, shopId, status, typeName);
	}

	

	private void setRptRepairServiceItem(List<RptRepairOrder> rptRepairOrderList){
		for (RptRepairOrder rptRepairOrder : rptRepairOrderList) {
			setOneRptRepairServiceItem(rptRepairOrder);
		}
	}
	
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
	
}
