package com.yelot.crm.entity;

import java.util.Date;

/**
 * Created by kee on 16/12/16.
 */
public class SendMessage {
    private String id;

    private String phone;

    /**
     * 发送最后一次的验证码
     */
    private String verifyCode;

    /**
     * 该手机号，发送的短信总数
     */
    private Integer sendTimes;

    /**
     * 一天短信条数，短信接口中，一个手机号最多可以发送10条，3分钟之内3条
     */
    private Integer dayTimes;

    /**
     * 最后一条发送的时间
     */
    private Date sendTm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getSendTimes() {
        return sendTimes;
    }

    public void setSendTimes(Integer sendTimes) {
        this.sendTimes = sendTimes;
    }

    public Integer getDayTimes() {
        return dayTimes;
    }

    public void setDayTimes(Integer dayTimes) {
        this.dayTimes = dayTimes;
    }

    public Date getSendTm() {
        return sendTm;
    }

    public void setSendTm(Date sendTm) {
        this.sendTm = sendTm;
    }
}
