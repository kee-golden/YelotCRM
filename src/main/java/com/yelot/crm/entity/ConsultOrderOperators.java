package com.yelot.crm.entity;

import java.util.Date;

/**
 * Created by kee on 17/9/6.
 */
public class ConsultOrderOperators {

    private Long id;
    private Long userId;
    private Date createAt;
    private Long consultOrderId;
    private String consultOrderJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getConsultOrderId() {
        return consultOrderId;
    }

    public void setConsultOrderId(Long consultOrderId) {
        this.consultOrderId = consultOrderId;
    }

    public String getConsultOrderJson() {
        return consultOrderJson;
    }

    public void setConsultOrderJson(String consultOrderJson) {
        this.consultOrderJson = consultOrderJson;
    }
}
