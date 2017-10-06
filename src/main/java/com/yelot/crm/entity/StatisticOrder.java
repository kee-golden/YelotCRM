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
    /**
     * 总金额
     */
    private Integer totalPayment;
    /**
     * 总数量
     */
    private Integer totalCount;


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
}
