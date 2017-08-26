package com.yelot.crm.enums;

/**
 * Created by kee on 17/6/4.
 */
public enum RepairOrderStatus {
    /**
     *  状态流转（2-->4-->8-->）
     */
    COMPLETED(1,"已完成"),//目前该状态，未使用，就是 28 状态
    CANCEL(0,"取消"),//门店接收人员，创建订单后，可以进行取消操作,并且该订单，财务未审核，仅仅当状态为2的时候，创建人可以取消自己的订单
    SUBMIT(2,"已提交，待财务审核"),
    ACCOUNTANT_APPROVE(4,"审核通过,门店客服主管"),
    ACCOUNTANT_REJECT(6,"审核拒绝"),
    MANAGER_DELIVERY(8,"门店管理员已发货，发货到售后中心"),//如果门店有维修人员，则不需要改状态，则直接是状态10，
    SHOP_MAINTAINCE(10,"门店管理员不需要发货，本门店维修师傅"),//门店售后维护，派发给本门店维修师傅
    CENTER_RECEIVE(12,"维修中心，已收货确认,拍照存档，再次派发给中心维修师傅"),
    REPAREMAN_RECEIVE(14,"维修师傅已收货，开始评估是否可修"),//该状态是否可以省略，
    REPAREMAN_APPROVE(16,"维修师傅确认可修"),
    REPAREMAN_REJECT(18,"维修师傅确认不可修,订单转给维修中心"),
    QC_APPROVE(20,"维修中心，确认通过，并拍照上传，增加快递单号"),
    QC_REJECT(22,"维修中心，拒绝，需要重新维修"),
    SHOP_RECEIVE(24,"门店管理员接收确认"),
    SHOP_DELIVERY(26,"门店管理员，已收款，并已发货"),
    CUSTOMER_RECEIVE(28,"客户已收货确认");


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
