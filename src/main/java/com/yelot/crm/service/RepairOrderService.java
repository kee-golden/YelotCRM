package com.yelot.crm.service;

import com.alibaba.fastjson.JSON;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Category;
import com.yelot.crm.entity.RepairOrder;
import com.yelot.crm.entity.RepairOrderOperators;
import com.yelot.crm.enums.OperatorStatus;
import com.yelot.crm.enums.RepairOrderStatus;
import com.yelot.crm.mapper.*;
import com.yelot.crm.vo.City;
import com.yelot.crm.vo.CityList;
import com.yelot.crm.vo.CityListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public Integer countTotalPage(String extra_search, String type, Long userId) {
		Long create_user_id = null;
		String three_days_after = null;
		
		if ("my".equals(type)) {
			create_user_id = userId;
		}
		if("warn".equals(type)){
			three_days_after = getThreeDaysAfter();
		}
		return repairOrderMapper.countTotalPage(extra_search, create_user_id, three_days_after, type);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageHelper
	 * @return
	 */
	public List<RepairOrder> findByPage(String extra_search, String type, Long userId, PageHelper pageHelper) {
		Long create_user_id = null;
		String three_days_after = null;
		
		if ("my".equals(type)) {
			create_user_id = userId;
		}
		if("warn".equals(type)){
			three_days_after = getThreeDaysAfter();
		}
		
		List<RepairOrder> repairOrderList = repairOrderMapper.findByPage(extra_search, create_user_id, three_days_after, type, pageHelper);
		setRepairServiceItem(repairOrderList);
		setRepairStatus(repairOrderList);
		return repairOrderList;
	}
	
	/**
	 * 根据客户的手机号查询客户的维修单号
	 * @param phone
	 * @return
	 */
	public List<String> findUserOrderNoByPhone(String phone){
		List<RepairOrder> userOrderList = repairOrderMapper.findUserOrderNoByPhone(phone);
		List<String> userOrderNo = new ArrayList<String>();
		for (RepairOrder repairOrder : userOrderList) {
			userOrderNo.add(repairOrder.getOrderNo());
		}
		return userOrderNo;
	}
    public int countTotalPageCheckList(String extra_search, List<String> statusList, String type) {
		return repairOrderMapper.countTotalPageCheckList(extra_search,statusList,type);
    }

	public List<RepairOrder> findByPageCheckList(String extra_search, List<String> statusList,PageHelper pageHelper, String type) {
		List<RepairOrder> repairOrderList =  repairOrderMapper.findByPageCheckList(extra_search,statusList,pageHelper,type);
		setRepairServiceItem(repairOrderList);
		setRepairStatus(repairOrderList);
		return repairOrderList;
	}

	private void setRepairServiceItem(List<RepairOrder> repairOrderList){
		for (RepairOrder repairOrder : repairOrderList) {
			setOneRepairServiceItem(repairOrder);
		}
	}
	
	private void setOneRepairServiceItem(RepairOrder repairOrder) {
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
					serviceItemNames += serviceItemName + "，";
				}
			}
		}
		repairOrder.setServiceItemNames(serviceItemNames);
	}

	/**
	 * 订单列表中订单状态转中文
	 * @param repairOrderList
	 */
	private void setRepairStatus(List<RepairOrder> repairOrderList){
		for (RepairOrder repairOrder : repairOrderList) {
			setOneRepairStatus(repairOrder);
		}
	}
	
	/**
	 * 单个订单中订单状态转中文
	 * @param repairOrder
	 */
	private void setOneRepairStatus(RepairOrder repairOrder){
		RepairOrderStatus[] repairOrderStatusList = RepairOrderStatus.values();
		for (RepairOrderStatus repairOrderStatus : repairOrderStatusList) {
			if (repairOrder.getStatus() == repairOrderStatus.getCode()) {
				repairOrder.setStatusName(repairOrderStatus.getMessage());
				break;
			}
		}
	}

	private void setRepairProductInfo(RepairOrder repairOrder){
		List<Map> productInfoList = JSON.parseArray(repairOrder.getProductInfoJson(), Map.class);
		repairOrder.setProductInfoList(productInfoList);
	}

	public int countTotalPageCheckListAndShop(String extra_search, List<String> statusList, Long shopId, String type) {
		return repairOrderMapper.countTotalPageCheckListAndShop(extra_search,statusList,shopId,type);
	}

	public List<RepairOrder> findByPageCheckListAndShop(String extra_search, List<String> statusList, PageHelper pageHelper, Long shopId, String type) {
		List<RepairOrder> repairOrderList = repairOrderMapper.findByPageCheckListAndShop(extra_search,statusList,pageHelper,shopId,type);
		setRepairServiceItem(repairOrderList);
		setRepairStatus(repairOrderList);
		return repairOrderList;
	}
	
	public RepairOrder findRepairOrderByOrderId(Long orderId) {
		RepairOrder repairOrder = repairOrderMapper.findRepairOrderByOrderId(orderId);
		setRepairProductInfo(repairOrder);
		repairOrder.setImagesList((repairOrder.getImagesJson() == null || "".equals(repairOrder.getImagesJson())) ? new ArrayList<String>() : Arrays.asList(repairOrder.getImagesJson().split(",")));
		repairOrder.setPrecheckImagesList((repairOrder.getPrecheckImages() == null || "".equals(repairOrder.getPrecheckImages())) ? new ArrayList<String>() : Arrays.asList(repairOrder.getPrecheckImages().split(",")));
		repairOrder.setQccheckImagesList((repairOrder.getQccheckImages() == null || "".equals(repairOrder.getQccheckImages())) ? new ArrayList<String>() : Arrays.asList(repairOrder.getQccheckImages().split(",")));
		setOneRepairServiceItem(repairOrder);
		return repairOrder;
	}

    public int countByShopId(Long shopId) {
		return repairOrderMapper.countByShopId(shopId);
    }

    public void update(RepairOrder repairOrder) {
		repairOrderMapper.update(repairOrder);
    }

    public void updateRefOrderIdsByOrderNo(String orderNo, String refOrderIdsJson) {
    	List<String> refOrderIdsJsonList = JSON.parseArray(refOrderIdsJson, String.class);
    	List<String> refOrderIdsJsonList2 =  JSON.parseArray(refOrderIdsJson, String.class);
    	
    	for (String orderNoTmp : refOrderIdsJsonList2) {
    		List<RepairOrder> repairOrderList = repairOrderMapper.findRefOrderIdsByOrderNo(orderNoTmp);
    		for (RepairOrder repairOrder : repairOrderList) {
    			if (repairOrder.getRefOrderIds() != null && !"null".equals(repairOrder.getRefOrderIds()) && !"".equals(repairOrder.getRefOrderIds())) {
        			List<String> refOrderIdsJsonList3 =  JSON.parseArray(repairOrder.getRefOrderIds(), String.class);
        			for (String refOrderId : refOrderIdsJsonList3) {
    					if (!refOrderIdsJsonList.contains(refOrderId)) {
    						refOrderIdsJsonList.add(refOrderId);
    					}
    				}
					
				}
			}
		}
    	
    	refOrderIdsJsonList.add(orderNo);
    	
    	for (String orderNoTmp : refOrderIdsJsonList) {
    		
    		List<String> refOrderIdsJsonListTmp = new ArrayList<String>();
			for (String refOrderId : refOrderIdsJsonList) {
				if (orderNoTmp.equals(refOrderId)) {
					continue;
				} else {
					refOrderIdsJsonListTmp.add(refOrderId);
				}
			}
			
			String refOrderIds = JSON.toJSONString(refOrderIdsJsonListTmp);
			repairOrderMapper.updateRefOrderIdsByOrderNo(orderNoTmp, refOrderIds);
		}
    }
    
    /**
     * 获取三天后的日期
     * @return
     */
    private String getThreeDaysAfter(){
		  Calendar calendar = Calendar.getInstance();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  calendar.add(Calendar.DATE, 3);
		  String three_days_after = sdf.format(calendar.getTime());
		  return three_days_after;
    }
    
}
