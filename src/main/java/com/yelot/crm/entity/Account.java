package com.yelot.crm.entity;

import java.util.Date;

/**
 * Created by kee on 17/9/20.
 */
public class Account {
    private Long id;
    private String phone;
    private String password;
    private String wxOpenid;
    private String wxNickname;
    private String verifyCode;
    private Date sendMessageAt;
    private Date createAt;
    /**
     * 系统生成的会员ID,要根据这号来生成二维码
     * 从1000开始
     */
    private String accountNo;

    private String firstname;
    private String lastname;
    private String expressAddress;
    private String hobbyJson;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getSendMessageAt() {
        return sendMessageAt;
    }

    public void setSendMessageAt(Date sendMessageAt) {
        this.sendMessageAt = sendMessageAt;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    public String getHobbyJson() {
        return hobbyJson;
    }

    public void setHobbyJson(String hobbyJson) {
        this.hobbyJson = hobbyJson;
    }
}
