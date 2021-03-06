package com.yelot.crm.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 订单状态
     */
    private int status;
    
    private String statusName;
    /**
     * 订单创建者
     */
    private Long createUserId;
    
    /**
     * 订单创建者姓名
     */
    private String createUserName;

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
     * 其他联系方式
     */
    private String customerPhoneSecond;

    /**
     * 客户地址
     */
    private String customerAddress;

    /**
     * 预付款,保留小数2位，存到数据库中扩大100倍
     */
    private int advancePayment;

    /**
     * 人工费，保留小数2位，存到数据库中扩大100倍
     */
    private int labourPayment;

    /**
     * 材料费，保留小数2位，存到数据库中扩大100倍
     */
    private int materialPayment;

    /**
     * 材料费备注
     */
    private String materialDesc;
    /**
     * 优惠金额，保留小数2位，存到数据库中扩大100倍
     */
    private int discountAmountPayment;

    /**
     * 优惠备注
     */
    private String discountDesc;

    /**
     * 未付款，代付款，这个值一般需要后期维修鉴定后才能填写完成，保留小数2位，存到数据库中扩大100倍
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

    /**
     * 客服上传的图片，创建时上传的图片
     */
    private String imagesJson;
    
    private List<String> imagesList;

    private String imageDesc;

    /**
     * 根据分类，存储不同的产品信息json
     */
    private String productInfoJson;
    
    private List<Map> productInfoList;

    /**
     * 用户服务项Ids
     */
    private String serviceItemIds;
    
    private String serviceItemNames;

    private Long firstCategoryId;

    private Long secondCategoryId;
    
    private String categoryName;
    
    private String firstCategoryName;
    
    private String secondCategoryName;

    private Long brandId;

    private String brandName;

    private String repairDesc;

    private Date createAt;

    private Date updateAt;

    /**
     * 预检的，上次的图片，以逗号分隔
     */
    private String precheckImages;
    
    private List<String> precheckImagesList;

    /**
     * QC 维修完成后，上次的图片，以逗号分隔
     */
    private String qccheckImages;
    
    private List<String> qccheckImagesList;

    /**
     * 新增类别：（内部单，返修单，评估单，客修单）
     */
    private String typeName;
    
    /**
     * 咨询单id
     */
    private Long consultOrderId;
    
    /**
     * 咨询单号
     */
    private Long consultOrderNo;
    
    /**
     * 门店地址
     */
    private String shopAddress;
    
    /**
     * 门店电话
     */
    private String shopPhone;
    
    /**
     * 维修人员
     */
    private Long repairUserId;
    
    /**
     * 最迟完成时间
     */
    private Date repairLastAt;
    

    
    /**
     * 关联单号
     */
    private String refOrderIds;
    private List<String> refOrderIdsList;
    
    /**
     * 客户来源
     */
    private String channelSource;
    
    /**
     * 物流单id
     */
    private Long expressId;
    
    /**
     * 物流费
     */
    private int expressMoney;
    
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

    public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
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

    public String getCustomerPhoneSecond() {
		return customerPhoneSecond;
	}

	public void setCustomerPhoneSecond(String customerPhoneSecond) {
		this.customerPhoneSecond = customerPhoneSecond;
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

    public List<String> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
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

	public List<Map> getProductInfoList() {
		return productInfoList;
	}

	public void setProductInfoList(List<Map> productInfoList) {
		this.productInfoList = productInfoList;
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

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

    public String getPrecheckImages() {
        return precheckImages;
    }

    public void setPrecheckImages(String precheckImages) {
        this.precheckImages = precheckImages;
    }

    public List<String> getPrecheckImagesList() {
		return precheckImagesList;
	}

	public void setPrecheckImagesList(List<String> precheckImagesList) {
		this.precheckImagesList = precheckImagesList;
	}

	public String getQccheckImages() {
        return qccheckImages;
    }

    public void setQccheckImages(String qccheckImages) {
        this.qccheckImages = qccheckImages;
    }

    public List<String> getQccheckImagesList() {
		return qccheckImagesList;
	}

	public void setQccheckImagesList(List<String> qccheckImagesList) {
		this.qccheckImagesList = qccheckImagesList;
	}

	public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLabourPayment() {
        return labourPayment;
    }

    public void setLabourPayment(int labourPayment) {
        this.labourPayment = labourPayment;
    }

    public int getMaterialPayment() {
        return materialPayment;
    }

    public void setMaterialPayment(int materialPayment) {
        this.materialPayment = materialPayment;
    }

	public Long getConsultOrderId() {
		return consultOrderId;
	}

	public void setConsultOrderId(Long consultOrderId) {
		this.consultOrderId = consultOrderId;
	}

	public Long getConsultOrderNo() {
		return consultOrderNo;
	}

	public void setConsultOrderNo(Long consultOrderNo) {
		this.consultOrderNo = consultOrderNo;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public Long getRepairUserId() {
		return repairUserId;
	}

	public void setRepairUserId(Long repairUserId) {
		this.repairUserId = repairUserId;
	}

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
	public Date getRepairLastAt() {
		return repairLastAt;
	}

	public void setRepairLastAt(Date repairLastAt) {
		this.repairLastAt = repairLastAt;
	}

    public int getDiscountAmountPayment() {
        return discountAmountPayment;
    }

    public void setDiscountAmountPayment(int discountAmountPayment) {
        this.discountAmountPayment = discountAmountPayment;
    }

	public String getDiscountDesc() {
		return discountDesc;
	}

	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}

	public String getRefOrderIds() {
		return refOrderIds;
	}

	public void setRefOrderIds(String refOrderIds) {
		this.refOrderIds = refOrderIds;
	}

	public List<String> getRefOrderIdsList() {
		return refOrderIdsList;
	}

	public void setRefOrderIdsList(List<String> refOrderIdsList) {
		this.refOrderIdsList = refOrderIdsList;
	}

	public String getChannelSource() {
		return channelSource;
	}

	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}

	public Long getExpressId() {
		return expressId;
	}

	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	public int getExpressMoney() {
		return expressMoney;
	}

	public void setExpressMoney(int expressMoney) {
		this.expressMoney = expressMoney;
	}

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

}
