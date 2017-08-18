package com.yelot.crm.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by kee on 17/5/30.
 */
public class RepairOrder {
    private Long id;
    /**
     * 订单编号，编码规则待定：
     */
    private String orderNo;

    /**
     * 该字段为冗余字段，主要是为了统计方便（快速获得shop_id），通过create_user_id还需要经过一次查询
     */
    private Long shopId;
    
    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 订单状态,提交（2）,最终（1），取消（0）
     */
    private int status;
    /**
     * 订单创建者
     */
    private Long createUserId;
    
    /**
     * 订单创建者姓名
     */
    private String createUserName;

    /**
     * 当前订单待审核者，订单当前状态，所有者，要审核订单的用户id
     */
    private Long approveUserId;

    /**
     * 交货日期,预估的时间
     */
    private Date pickupAt;

    /**
     * 交货方式：上门自取，快递邮寄
     */
    private int pickupType;

    /**
     * 客户的id,要把用户信息先插入表t_customer中
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户手机号
     */
    private String customerPhone;

    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 预付款,保留小数2位，存到数据库中扩大100倍
     */
    private int advancePayment;

    /**
     * 未付款，代付款，这个值一般需要后期维修鉴定后才能填写完成
     */
    private int nonPayment;

    private int nonPaymentType;

    /**
     * 付款方式：现金，刷卡，支付宝，微信等
     */
    private int advancePaymentType;

    /**
     * 总价
     */
    private int totalPayment;

    private String imagesJson;

    private String imageDesc;

    private String productInfoJson;

    private String serviceItemIds;
    
    private String serviceItemNames;

    private Long firstCategoryId;

    private Long secondCategoryId;
    
    private String categoryName;

    private Long brandId;

    private String repairDesc;

    private Date createAt;

    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(Long approveUserId) {
        this.approveUserId = approveUserId;
    }

    public Date getPickupAt() {
        return pickupAt;
    }

    public void setPickupAt(Date pickupAt) {
        this.pickupAt = pickupAt;
    }

    public int getPickupType() {
        return pickupType;
    }

    public void setPickupType(int pickupType) {
        this.pickupType = pickupType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(int advancePayment) {
        this.advancePayment = advancePayment;
    }

    public int getNonPayment() {
        return nonPayment;
    }

    public void setNonPayment(int nonPayment) {
        this.nonPayment = nonPayment;
    }

    public int getAdvancePaymentType() {
        return advancePaymentType;
    }

    public void setAdvancePaymentType(int advancePaymentType) {
        this.advancePaymentType = advancePaymentType;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getImagesJson() {
        return imagesJson;
    }

    public void setImagesJson(String imagesJson) {
        this.imagesJson = imagesJson;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getProductInfoJson() {
        return productInfoJson;
    }

    public void setProductInfoJson(String productInfoJson) {
        this.productInfoJson = productInfoJson;
    }

    public String getServiceItemIds() {
        return serviceItemIds;
    }

    public void setServiceItemIds(String serviceItemIds) {
        this.serviceItemIds = serviceItemIds;
    }

    public String getServiceItemNames() {
		return serviceItemNames;
	}

	public void setServiceItemNames(String serviceItemNames) {
		this.serviceItemNames = serviceItemNames;
	}

	public Long getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(Long firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public Long getSecondCategoryId() {
        return secondCategoryId;
    }

    public void setSecondCategoryId(Long secondCategoryId) {
        this.secondCategoryId = secondCategoryId;
    }

    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public int getNonPaymentType() {
        return nonPaymentType;
    }

    public void setNonPaymentType(int nonPaymentType) {
        this.nonPaymentType = nonPaymentType;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
