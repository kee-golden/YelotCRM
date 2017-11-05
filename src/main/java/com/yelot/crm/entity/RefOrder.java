package com.yelot.crm.entity;

/**
 * @author xyzloveabc
 * @2017年11月6日
 */
public class RefOrder {
	private String orderNo;
	/**
	 * 是否选择状态
	 */
	private boolean selectedStatus;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public boolean isSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(boolean selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

}
