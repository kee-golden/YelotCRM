package com.yelot.crm.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author xyzloveabc
 * @2017年9月1日
 */
public class RptRepairOrder {
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
     * 客户性别
     */
    private String customerSex;
    
    /**
     * 省
     */
    private String province;
    
    /**
     * 市
     */
    private String city;
    
    /**
     * 微信ID
     */
    private String wechatId;
    
    /**
     * 微信名称
     */
    private String wechatNickname;
    
    /**
     * QQ
     */
    private String customerQQ;
    
    /**
     * 客户类型
     */
    private String customerType;
    
    /**
     * 渠道来源
     */
    private String channelSource;
    
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

    /**
     * QC 维修完成后，上次的图片，以逗号分隔
     */
    private String qccheckImages;

    /**
     * 新增类别：（内部单，返修单，评估单，客修单）
     */
    private String typeName;

    private int labourPayment;

    private int materialPayment;
    private int discountAmountPayment;
    
    private Date consultCreateAt;
    
    private String consultCreateUserName;
    private String deliverType;
    
    private String startDate;
    
    private String endDate;

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

    public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getWechatNickname() {
		return wechatNickname;
	}

	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}

	public String getCustomerQQ() {
		return customerQQ;
	}

	public void setCustomerQQ(String customerQQ) {
		this.customerQQ = customerQQ;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getChannelSource() {
		return channelSource;
	}

	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
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

    public String getQccheckImages() {
        return qccheckImages;
    }

    public void setQccheckImages(String qccheckImages) {
        this.qccheckImages = qccheckImages;
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

	public int getDiscountAmountPayment() {
		return discountAmountPayment;
	}

	public void setDiscountAmountPayment(int discountAmountPayment) {
		this.discountAmountPayment = discountAmountPayment;
	}

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
	public Date getConsultCreateAt() {
		return consultCreateAt;
	}

	public void setConsultCreateAt(Date consultCreateAt) {
		this.consultCreateAt = consultCreateAt;
	}

	public String getConsultCreateUserName() {
		return consultCreateUserName;
	}

	public void setConsultCreateUserName(String consultCreateUserName) {
		this.consultCreateUserName = consultCreateUserName;
	}

	public String getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
}
