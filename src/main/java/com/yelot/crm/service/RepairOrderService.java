package com.yelot.crm.service;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.entity.User;
import com.yelot.crm.enums.OperatorStatus;
import com.yelot.crm.mapper.*;
import com.yelot.crm.vo.City;
import com.yelot.crm.vo.CityList;
import com.yelot.crm.vo.CityListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/6/5.
 */
@Service
@Transactional
public class RepairOrderService {

	@Autowired
	private RepairOrderMapper repairOrderMapper;

	@Autowired
	private RepairOrderOperatorsMapper repairOrderOperatorsMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	RepairOrder find(Long id) {
		return repairOrderMapper.find(id);
	}



	public void save(RepairOrder repairOrder) {

		User user = UserUtil.getCurrentUser();
		repairOrder.setApproveRoleId(user.getShop().getUser_id());// 首次创建后的审批人为该店店长
		repairOrderMapper.save(repairOrder);

		submitOperator(repairOrder);

	}

	private void submitOperator(RepairOrder repairOrder) {
		RepairOrderOperators repairOrderOperators = new RepairOrderOperators();
		repairOrderOperators.setRepair_order_id(repairOrder.getId());
		repairOrderOperators.setApprove_user_id(repairOrder.getCreateUserId());
		repairOrderOperators
				.setOperator_status(OperatorStatus.SUBMIT.getCode());
//		User user = UserUtil.getCurrentUser();
//		repairOrderOperators.setNext_approve_user_id(user.getShop()
//				.getUser_id());
		repairOrderOperators.setOrder_status(repairOrder.getStatus());
		repairOrderOperators.setCreateAt(new Date());

		repairOrderOperatorsMapper.save(repairOrderOperators);
	}

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
	 * 查询总的记录条数
	 * 
	 * @return 总的记录条数
	 */
	public Integer countTotalPageMy(String extra_search, String type, Long userId) {
		Long create_user_id = null;
		Long approve_user_id = null;
		
		if ("my".equals(type)) {
			create_user_id = userId;
		}
		if("check".equals(type)){
			approve_user_id = userId;
		}
		
		return repairOrderMapper.countTotalPageMy(extra_search, create_user_id, approve_user_id);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageHelper
	 * @return
	 */
	public List<RepairOrder> findByPageMy(String extra_search, String type, Long userId, PageHelper pageHelper) {

		Long create_user_id = null;
		Long approve_user_id = null;
		
		if ("my".equals(type)) {
			create_user_id = userId;
		}
		if("check".equals(type)){
			approve_user_id = userId;
		}
		
		List<RepairOrder> repairOrderList = repairOrderMapper.findByPageMy(extra_search, create_user_id, approve_user_id, pageHelper);
		setRepairServiceItem(repairOrderList);
//		for (RepairOrder repairOrder : RepairOrderList) {
//			String serviceItemIds = repairOrder.getServiceItemIds();
//			List<String> serviceItemIdsList = JSON.parseArray(serviceItemIds, String.class);
//			String serviceItemNames = "";
//			if (serviceItemIdsList != null && serviceItemIdsList.size() > 0) {
//				for (int i = 0; i < serviceItemIdsList.size(); i++) {
//					String serviceItemName = repairOrderMapper
//							.findServiceItemName(serviceItemIdsList.get(i));
//					if (i == serviceItemIdsList.size() - 1) {
//						serviceItemNames += serviceItemName;
//					} else {
//						serviceItemNames += serviceItemName + ",";
//					}
//				}
//			}
//			repairOrder.setServiceItemNames(serviceItemNames);
//		}
		return repairOrderList;
	}

    public int countTotalPageCheckList(String extra_search, String statusString) {
		return repairOrderMapper.countTotalPageCheckList(extra_search,statusString);
    }

	public List<RepairOrder> findByPageCheckList(String extra_search, String statusString,PageHelper pageHelper) {
		List<RepairOrder> repairOrderList =  repairOrderMapper.findByPageCheckList(extra_search,statusString,pageHelper);
		setRepairServiceItem(repairOrderList);
		return repairOrderList;
	}

	private void setRepairServiceItem(List<RepairOrder> repairOrderList){
		for (RepairOrder repairOrder : repairOrderList) {
			String serviceItemIds = repairOrder.getServiceItemIds();
			List<String> serviceItemIdsList = JSON.parseArray(serviceItemIds, String.class);
			String serviceItemNames = "";
			if (serviceItemIdsList != null && serviceItemIdsList.size() > 0) {
				for (int i = 0; i < serviceItemIdsList.size(); i++) {
					String serviceItemName = repairOrderMapper
							.findServiceItemName(serviceItemIdsList.get(i));
					if (i == serviceItemIdsList.size() - 1) {
						serviceItemNames += serviceItemName;
					} else {
						serviceItemNames += serviceItemName + ",";
					}
				}
			}
			repairOrder.setServiceItemNames(serviceItemNames);
		}
	}

	public int countTotalPageCheckListAndShop(String extra_search, String statusString, Long shopId) {
		return repairOrderMapper.countTotalPageCheckListAndShop(extra_search,statusString,shopId);
	}

	public List<RepairOrder> findByPageCheckListAndShop(String extra_search, String statusString, PageHelper pageHelper, Long shopId) {
		List<RepairOrder> repairOrderList = repairOrderMapper.findByPageCheckListAndShop(extra_search,statusString,pageHelper,shopId);
		setRepairServiceItem(repairOrderList);
		return repairOrderList;
	}
}
