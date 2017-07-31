//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yelot.crm.Util;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private Object code;
    private String message;
    private Map<String, Object> data = new HashMap();

    public ResultData() {
    }

    public ResultData(Object code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(Object code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object getCode() {
        return this.code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Map<String, Object> message) {
        this.data = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultData putDataValue(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public static ResultData ok() {
        return new ResultData(BaseResultStatus.SUCCESS.getCode(), BaseResultStatus.SUCCESS.getMessage());
    }

    public static ResultData hasExits(){
        return new ResultData(BaseResultStatus.SUCCESS.getCode(), BaseResultStatus.SUCCESS.getMessage());

    }

    public static ResultData notFound() {
        return new ResultData(BaseResultStatus.NOT_FOND.getCode(), BaseResultStatus.NOT_FOND.getMessage());
    }

    public static ResultData errorRequest() {
        return new ResultData(BaseResultStatus.ERROR_REQUEST.getCode(), BaseResultStatus.ERROR_REQUEST.getMessage());
    }

    public static ResultData forbidden() {
        return new ResultData(BaseResultStatus.FORBIDDEN.getCode(), BaseResultStatus.FORBIDDEN.getMessage());
    }

    public static ResultData unauthorized() {
        return new ResultData(BaseResultStatus.UNAUTHORIZED.getCode(), BaseResultStatus.UNAUTHORIZED.getMessage());
    }

    public static ResultData serverError() {
        return new ResultData(BaseResultStatus.SERVER_ERROR.getCode(), BaseResultStatus.SERVER_ERROR.getMessage());
    }
}
