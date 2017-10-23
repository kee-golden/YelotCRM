package com.yelot.crm.entity;

import java.util.Date;

/**
 * Created by kee on 17/9/20.
 */
public class Account {
    private Long id;
    private String phone;
    private String wxOpenid;
    private String wxNickname;
    private Date createAt;
    /**
     * 系统生成的会员ID,要根据这号来生成二维码
     * 从1000开始
     */
    private String accountNo;

    private String fullName;
    private String myPhone;
    private String city;
    private String expressAddress;
    private String interestJson;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMyPhone() {
        return myPhone;
    }

    public void setMyPhone(String myPhone) {
        this.myPhone = myPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    public String getInterestJson() {
        return interestJson;
    }

    public void setInterestJson(String interestJson) {
        this.interestJson = interestJson;
    }
}
