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
     * 该字段为冗余字段，主要是为了统计方便（快速获得shop_id），通过create_user_id还需要经过一次查询
     */
    private Long shopId;
    
    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 接单日期
     */
    private Date createAt;
	
	/**
	 * 今天日期
	 */
	private Date today;
	
    /**
     * 订单编号，编码规则待定：
     */
    private String orderNo;

    /**
     * 交货日期,预估的时间
     */
    private Date pickupAt;
    
    /**
     * 到期提醒
     */
    private String daoQiTiXing;

    /**
     * 送回日
     */
    private Date songHuiDate;
    
    /**
     * 取货日
     */
    private Date quHuoDate;

    /**
     * 首接人
     */
    private String consultCreateUserName;

    /**
     * 订单创建者
     */
    private Long createUserId;
    
    /**
     * 订单创建者姓名
     */
    private String createUserName;

    /**
     * 接单方式
     */
    private String deliverType;

    /**
     * 订单状态,提交（2）,最终（1），取消（0）
     */
    private int status;
    
    /**
     * 订单状态名字
     */
    private String statusName;
    
    /**
     * 计算月份
     */
    private String jiSuanYueFen;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;
    
    /**
     * 类型id
     */
    private Long firstCategoryId;
    
    /**
     * 类型名称
     */
    private String firstCategoryName;

    /**
     * 货品id
     */
    private Long secondCategoryId;
    
    /**
     * 货品名称
     */
    private String secondCategoryName;

    /**
     * 维修工序
     */
    private String repairDesc;
    
    /**
     * 是否返修
     */
    private String isFanXiu;

    /**
     * 小结
     */
    private String totalPayment;

    /**
     * 料钱
     */
    private String materialPayment;
    
    /**
     * 回收料
     */
    private String huiShouLiao;
    
    /**
     * 付款金额
     */
    private String fuKuanJine;
    
    /**
     * 凭证号
     */
    private String pingZhengHao;

    /**
     * 发票
     */
    private String faPiao;
    
    /**
     * 合计支出
     */
    private String heJiZhiChu;
    
    /**
     * 设备号
     */
    private String sheBeiHao;
    
    /**
     * 搜索关键词
     */
    private String guanJianCi;
    
    /**
     * 着陆页链接
     */
    private String zhuoLuYe;
    
    /**
     * 备注
     */
    private String beiZhu;
    
    /**
     * 对比照片
     */
    private String duiBiZhaoPian;
    
    /**
     * 交货方式：上门自取，快递邮寄
     */
    private String pickupType;

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
    private String advancePayment;

    /**
     * 未付款，代付款，这个值一般需要后期维修鉴定后才能填写完成
     */
    private String nonPayment;

    private String nonPaymentType;
    private String nonPaymentTypeName;

    /**
     * 付款方式：现金，刷卡，支付宝，微信等
     */
    private String advancePaymentType;


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
    
    private String categoryName;
    
    


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

    private String labourPayment;

    private String discountAmountPayment;
    
    private Date consultCreateAt;
    
    /**
     * 快递费 
     */
    private String expressMoney;
    
    /**
     * 快递名字
     */
    private String expressName;
    
    /**
     * 快递单号
     */
    private String expressNo;
    
    /**
     * 保费
     */
    private String insuranceAmount;
    
    /**
     * 保单号
     */
    private String insuranceNo;
    
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

	@JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
    public Date getPickupAt() {
        return pickupAt;
    }

    public void setPickupAt(Date pickupAt) {
        this.pickupAt = pickupAt;
    }

    public String getPickupType() {
        return pickupType;
    }

    public void setPickupType(String pickupType) {
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

	public String getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(String advancePayment) {
        this.advancePayment = advancePayment;
    }

    public String getNonPayment() {
        return nonPayment;
    }

    public void setNonPayment(String nonPayment) {
        this.nonPayment = nonPayment;
    }

    public String getAdvancePaymentType() {
        return advancePaymentType;
    }

    public void setAdvancePaymentType(String advancePaymentType) {
        this.advancePaymentType = advancePaymentType;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
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

    public String getNonPaymentType() {
        return nonPaymentType;
    }

    public void setNonPaymentType(String nonPaymentType) {
        this.nonPaymentType = nonPaymentType;
    }

    public String getNonPaymentTypeName() {
		return nonPaymentTypeName;
	}

	public void setNonPaymentTypeName(String nonPaymentTypeName) {
		this.nonPaymentTypeName = nonPaymentTypeName;
	}

	@JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
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

    public String getLabourPayment() {
        return labourPayment;
    }

    public void setLabourPayment(String labourPayment) {
        this.labourPayment = labourPayment;
    }

    public String getMaterialPayment() {
        return materialPayment;
    }

    public void setMaterialPayment(String materialPayment) {
        this.materialPayment = materialPayment;
    }

	public String getDiscountAmountPayment() {
		return discountAmountPayment;
	}

	public void setDiscountAmountPayment(String discountAmountPayment) {
		this.discountAmountPayment = discountAmountPayment;
	}

    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
	public Date getConsultCreateAt() {
		return consultCreateAt;
	}

	public void setConsultCreateAt(Date consultCreateAt) {
		this.consultCreateAt = consultCreateAt;
	}

    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
	public Date getSongHuiDate() {
		return songHuiDate;
	}

	public void setSongHuiDate(Date songHuiDate) {
		this.songHuiDate = songHuiDate;
	}

    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
	public Date getQuHuoDate() {
		return quHuoDate;
	}

	public void setQuHuoDate(Date quHuoDate) {
		this.quHuoDate = quHuoDate;
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

	public String getExpressMoney() {
		return expressMoney;
	}

	public void setExpressMoney(String expressMoney) {
		this.expressMoney = expressMoney;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
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

    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "GMT+8")
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getDaoQiTiXing() {
		return daoQiTiXing;
	}

	public void setDaoQiTiXing(String daoQiTiXing) {
		this.daoQiTiXing = daoQiTiXing;
	}

	public String getJiSuanYueFen() {
		return jiSuanYueFen;
	}

	public void setJiSuanYueFen(String jiSuanYueFen) {
		this.jiSuanYueFen = jiSuanYueFen;
	}

	public String getIsFanXiu() {
		return isFanXiu;
	}

	public void setIsFanXiu(String isFanXiu) {
		this.isFanXiu = isFanXiu;
	}

	public String getHuiShouLiao() {
		return huiShouLiao;
	}

	public void setHuiShouLiao(String huiShouLiao) {
		this.huiShouLiao = huiShouLiao;
	}

	public String getFuKuanJine() {
		return fuKuanJine;
	}

	public void setFuKuanJine(String fuKuanJine) {
		this.fuKuanJine = fuKuanJine;
	}

	public String getPingZhengHao() {
		return pingZhengHao;
	}

	public void setPingZhengHao(String pingZhengHao) {
		this.pingZhengHao = pingZhengHao;
	}

	public String getFaPiao() {
		return faPiao;
	}

	public void setFaPiao(String faPiao) {
		this.faPiao = faPiao;
	}

	public String getHeJiZhiChu() {
		return heJiZhiChu;
	}

	public void setHeJiZhiChu(String heJiZhiChu) {
		this.heJiZhiChu = heJiZhiChu;
	}

	public String getSheBeiHao() {
		return sheBeiHao;
	}

	public void setSheBeiHao(String sheBeiHao) {
		this.sheBeiHao = sheBeiHao;
	}

	public String getGuanJianCi() {
		return guanJianCi;
	}

	public void setGuanJianCi(String guanJianCi) {
		this.guanJianCi = guanJianCi;
	}

	public String getZhuoLuYe() {
		return zhuoLuYe;
	}

	public void setZhuoLuYe(String zhuoLuYe) {
		this.zhuoLuYe = zhuoLuYe;
	}

	public String getBeiZhu() {
		return beiZhu;
	}

	public void setBeiZhu(String beiZhu) {
		this.beiZhu = beiZhu;
	}

	public String getDuiBiZhaoPian() {
		return duiBiZhaoPian;
	}

	public void setDuiBiZhaoPian(String duiBiZhaoPian) {
		this.duiBiZhaoPian = duiBiZhaoPian;
	}
    
}
