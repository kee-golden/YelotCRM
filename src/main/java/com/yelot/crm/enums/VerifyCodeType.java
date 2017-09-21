package com.yelot.crm.enums;

/**
 * @author kee
 */
public enum VerifyCodeType {

    Verify_Delay(1,"验证码超时"),
    Verify_Error(2,"验证码错误"),
    Verify_Success(3,"验证成功")
    ;

    private Integer code;
    private String name;

    VerifyCodeType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
