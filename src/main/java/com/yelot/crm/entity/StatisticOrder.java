package com.yelot.crm.entity;

/**
 * 统计分析vo类
 * Created by kee on 17/10/4.
 */
public class StatisticOrder {

    private String userName;
    private String realname;
    private String phone;
    /**
     * 门店统计中，使用，根据不同的统计方式，显示不同的时间样式
     */
    private String time;
    private String shopName;
    private String province;
    private String city;
    private String brandName;
    /**
     * 总金额
     */
    private Integer totalPayment;
    /**
     * 总数量
     */
    private Integer totalCount;
    /**
     * 成交率比较的时候，需要多个数量总和，如咨询单总量和维修单总量
     */
    private Integer totalCount2;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTotalCount2() {
        return totalCount2;
    }

    public void setTotalCount2(Integer totalCount2) {
        this.totalCount2 = totalCount2;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
