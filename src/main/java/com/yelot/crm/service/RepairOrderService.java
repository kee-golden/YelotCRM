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
	private RepairOrderMapper repareOrderMapper;

	@Autowired
	private RepairOrderOperatorsMapper repairOrderOperatorsMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	RepairOrder find(Long id) {
		return repareOrderMapper.find(id);
	}

	// void save(RepairOrder repareOrder, RepareOrderItem repareOrderItem,
	// RepareOrderItemImage repareOrderItemImage){
	// //step 1:首先保存订单表
	// repareOrderMapper.save(repareOrder);
	// RepairOrder repareOrder1 =
	// repareOrderMapper.findByOrderNo(repareOrder.getOrder_no());
	//
	// //step 2:保存item表
	// repareOrderItem.setRepare_order_id(repareOrder1.getId());
	// repareOrderItemMapper.save(repareOrderItem);
	//
	// //step 3:保存图片
	// List<RepareOrderItem> repareOrderItemList =
	// repareOrderItemMapper.findByOrderId(repareOrder1.getId());
	// if(repareOrderItemList != null && repareOrderItemList.size()>0){
	// //当前不用修改，一个订单中只有一个item,取第一个也是唯一的一个即可。后期如果扩展成多个，需要修改.
	// repareOrderItemImage.setRepare_order_item_id(repareOrderItemList.get(0).getId());
	// }
	// repareOrderItemImgMapper.save(repareOrderItemImage);
	// }

	public void save(RepairOrder repairOrder) {

		User user = UserUtil.getCurrentUser();
		repairOrder.setApproveUserId(user.getShop().getUser_id());// 首次创建后的审批人为该店店长
		repareOrderMapper.save(repairOrder);

		submitOperator(repairOrder);

	}

	private void submitOperator(RepairOrder repairOrder) {
		RepairOrderOperators repairOrderOperators = new RepairOrderOperators();
		repairOrderOperators.setRepair_order_id(repairOrder.getId());
		repairOrderOperators.setApprove_user_id(repairOrder.getCreateUserId());
		repairOrderOperators
				.setOperator_status(OperatorStatus.SUBMIT.getCode());
		User user = UserUtil.getCurrentUser();
		repairOrderOperators.setNext_approve_user_id(user.getShop()
				.getUser_id());
		repairOrderOperators.setCreate_at(new Date());

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
	public Integer countTotalPage(String extra_search, Long userId) {
		return repareOrderMapper.countTotalPage(extra_search, userId);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageHelper
	 * @return
	 */
	public List<RepairOrder> findByPage(String extra_search, Long userId,
			PageHelper pageHelper) {
		List<RepairOrder> RepairOrderList = repareOrderMapper.findByPage(
				extra_search, userId, pageHelper);
		for (RepairOrder repairOrder : RepairOrderList) {
			String serviceItemIds = repairOrder.getServiceItemIds();
			List<String> serviceItemIdsList = JSON.parseArray(serviceItemIds,
					String.class);
			String serviceItemNames = "";
			if (serviceItemIdsList != null && serviceItemIdsList.size() > 0) {
				for (int i = 0; i < serviceItemIdsList.size(); i++) {
					String serviceItemName = repareOrderMapper
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
		return RepairOrderList;
	}

}
