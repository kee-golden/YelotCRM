package com.yelot.crm.enums;

/**
 * Created by kee on 17/8/24.
 */
public enum  ConsultOrderStatus {

    OnGoing(1,"正在进行，未处理"),
    REJECT(2,"未接单，已处理"),
    ACCEPT(3,"已接单，已处理");

    private int code;
    private String message;

    ConsultOrderStatus(int code,String message){
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
