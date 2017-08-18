package com.yelot.crm.enums;

/**
 * Created by kee on 17/5/28.
 */
public enum OperatorStatus {
    SUBMIT(1,"Submit"),
    APPROVE(2,"Approve"),
    REJECT(3,"Reject");

    private int code;
    private String message;

    OperatorStatus(int code, String message){
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
