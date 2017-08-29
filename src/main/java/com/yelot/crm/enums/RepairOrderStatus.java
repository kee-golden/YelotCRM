package com.yelot.crm.enums;

import java.util.LinkedList;

/**
 * Created by kee on 17/6/4.
 */
public enum RepairOrderStatus {
    /**
     *  状态流转（2-->4-->8-->）
     */
//    COMPLETED(1,"已完成"),//目前该状态，未使用，就是 28 状态
    CANCEL(0,"取消"),//门店接收人员，创建订单后，可以进行取消操作,并且该订单，财务未审核，仅仅当状态为2的时候，创建人可以取消自己的订单
    SUBMIT(2,"已提交，待财务审核"),
    SHOP_MANAGE_APPROVE(4,"审核通过,门店客服主管"),
    SHOP_MANAGE_REJECT(6,"审核拒绝"),
    CENTER_APPROVE(12,"维修中心，已收货确认,分练货品完成"),
    CENTER_REJECT(14,"维修中心，货物没有收到，拒绝该订单"),
    CHECK_APPROVE(16,"预检完成，通过可以维修"),
    CHECK_REJECT(18,"预检拒绝，无法维修"),
    QC_APPROVE(20,"预检完成，通过可以维修"),
    QC_REJECT(22,"预检拒绝，无法维修"),
    CHECKIN_APPROVE(24,"入库完成"),
    CHECKIN_REJECT(26,"入库拒绝"),
    CHECKOUT_APPROVE(28,"出库完成"),
    CHECKOUT_REJECT(30,"出库拒绝"),
    SHOP_RECEIVE_APPROVE(32,"门店收货确认"),
    SHOP_RECEIVE_REJECT(34,"门店收货拒绝，没有收到货"),
    CUSTOMER_RECEIVE_APPROVE(36,"客户已收货确认");


    private int code;
    private String message;

    RepairOrderStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
