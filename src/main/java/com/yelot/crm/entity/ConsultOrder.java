package com.yelot.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 意向客户订单
 * Created by kee on 17/8/22.
 */
public class ConsultOrder {
    private Long id;
    private String customerName;
    private int customerSex;
    private String customerPhone;
    private String customerAgesAlmost;
    private String wechatNo;
    private String repairCommands;
    private String province;
    private String city;
    private Long firstCategory;
    private Long secondCategory;
    private String firstCategoryName;
    private String secondCategoryName;
    private Long brandId;
    /**
     * 浏览网页，来源渠道
     */
    private String channelUrl;
    private String keywords;
    private String priceLimit;
    private String timeLimit;
    private String qulityLimit;
    private String specialCommands;
    private String imagesPath;
    /**
     * 预约上门，服务时间
     */
    private Date vistorAt;
    /**
     * 创建时间，也就是首次咨询时间
     */
    private Date createAt;
    private Long createUserId;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 状态，正在进行(1)，已接单(2)，未接单(3)
     */
    private int status;

    private Long shopId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(int customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAgesAlmost() {
        return customerAgesAlmost;
    }

    public void setCustomerAgesAlmost(String customerAgesAlmost) {
        this.customerAgesAlmost = customerAgesAlmost;
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getRepairCommands() {
        return repairCommands;
    }

    public void setRepairCommands(String repairCommands) {
        this.repairCommands = repairCommands;
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

    public Long getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(Long firstCategory) {
        this.firstCategory = firstCategory;
    }

    public Long getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(Long secondCategory) {
        this.secondCategory = secondCategory;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(String priceLimit) {
        this.priceLimit = priceLimit;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getQulityLimit() {
        return qulityLimit;
    }

    public void setQulityLimit(String qulityLimit) {
        this.qulityLimit = qulityLimit;
    }

    public String getSpecialCommands() {
        return specialCommands;
    }

    public void setSpecialCommands(String specialCommands) {
        this.specialCommands = specialCommands;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getVistorAt() {
        return vistorAt;
    }

    public void setVistorAt(Date vistorAt) {
        this.vistorAt = vistorAt;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
