package com.yelot.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 物流实体类
 * Created by kee on 17/10/8.
 */
public class Express {
    private Long id;
    private String expressName;
    private String expressNo;
    private int sendType;
    private int payType;
    private int payAmount;
    private int insuranceAmount;
    private String insuranceNo;
    private String repairOrderNoJson;
    private String acceptPersonName;
    private String acceptPersonPhone;
    private String sendPersonName;
    private String sendPersonPhone;
    private Long createUserId;
    private Long shopId;
    private String comment;
    private Date createAt;
    private int expressType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(int insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public String getRepairOrderNoJson() {
        return repairOrderNoJson;
    }

    public void setRepairOrderNoJson(String repairOrderNoJson) {
        this.repairOrderNoJson = repairOrderNoJson;
    }

    public String getAcceptPersonName() {
        return acceptPersonName;
    }

    public void setAcceptPersonName(String acceptPersonName) {
        this.acceptPersonName = acceptPersonName;
    }

    public String getAcceptPersonPhone() {
        return acceptPersonPhone;
    }

    public void setAcceptPersonPhone(String acceptPersonPhone) {
        this.acceptPersonPhone = acceptPersonPhone;
    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public String getSendPersonPhone() {
        return sendPersonPhone;
    }

    public void setSendPersonPhone(String sendPersonPhone) {
        this.sendPersonPhone = sendPersonPhone;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

	public int getExpressType() {
		return expressType;
	}

	public void setExpressType(int expressType) {
		this.expressType = expressType;
	}
    
}
