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
    SUBMIT(2,"待客服主管审核"),
    SHOP_MANAGE_APPROVE(4,"待分练人员审核"),
    SHOP_MANAGE_REJECT(6,"审核拒绝"),
    CENTER_APPROVE(12,"待预检人员审核"),
    CENTER_REJECT(14,"维修中心，货物没有收到，拒绝该订单"),
    CHECK_APPROVE(16,"待QC审核"),
    CHECK_REJECT(18,"预检拒绝，无法维修"),
    CHECK_EVALUE_APPROVE(17,"待客服主管审核"),
    CHECK_EVALUE_REJECT(19,"评估单预检拒绝"),
    QC_APPROVE(20,"待入库人员审核"),
    QC_REJECT(22,"预检拒绝，无法维修"),
    CHECKIN_APPROVE(24,"待出库审核"),
    CHECKIN_REJECT(26,"入库拒绝"),
    CHECKOUT_APPROVE(28,"待门店收货确认"),
    CHECKOUT_REJECT(30,"出库拒绝"),
    SHOP_RECEIVE_APPROVE(32,"待门店发货确认"),
    SHOP_RECEIVE_REJECT(34,"门店收货拒绝，没有收到货"),
    SHOP_EXPRESS_APPROVE(36,"待用户收货确认"),
    SHOP_EXPRESS_REJECT(38,"客户收货拒绝"),
    CUSTOMER_RECEIVE_APPROVE(40,"客户收货已确认"),
    CUSTOMER_RECEIVE_REJECT(42,"客户收货拒绝"),
    //订单如果是评估单，状态流程过程是 2-->4-->12-->17-->44->48--->20-->24--->28--->32-->36
    SHOP_EVALUE_MANAGE_APPROVE(44,"评估单，客服主管再次审核通过"),
    SHOP_EVALUE_MANAGE_REJECT(46,"评估单，客服主管再次审核通过"),
    CHECK_EVALUE_ORDER_APPROVE(48,"预检人员，审核评估单再次通过"),
    CHECK_EVALUE_ORDER_REJECT(50,"预检人员，审核评估单再次拒绝");


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
