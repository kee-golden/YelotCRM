package com.yelot.crm.entity;

import java.util.Date;

/**
 * 审核记录表，操作详情记录表
 * Created by kee on 17/5/30.
 */
public class RepairOrderOperators {
    private Long id;
    private Long repair_order_id;
    private Long approve_user_id;
    private Long next_approve_user_id;
    private String operator_comment;
    private int operator_status;
    private Date create_at;

    private String orderNo;

    private String createUserName;

    private String approveUserName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNext_approve_user_id() {
        return next_approve_user_id;
    }

    public void setNext_approve_user_id(Long next_approve_user_id) {
        this.next_approve_user_id = next_approve_user_id;
    }


    public Long getRepair_order_id() {
        return repair_order_id;
    }

    public void setRepair_order_id(Long repair_order_id) {
        this.repair_order_id = repair_order_id;
    }

    public Long getApprove_user_id() {
        return approve_user_id;
    }

    public void setApprove_user_id(Long approve_user_id) {
        this.approve_user_id = approve_user_id;
    }

    public String getOperator_comment() {
        return operator_comment;
    }

    public void setOperator_comment(String operator_comment) {
        this.operator_comment = operator_comment;
    }

    public int getOperator_status() {
        return operator_status;
    }

    public void setOperator_status(int operator_status) {
        this.operator_status = operator_status;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName;
    }
}
