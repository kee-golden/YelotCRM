package com.yelot.crm.Util;

import java.util.HashMap;

/**
 * Created by kee on 17/5/26.
 */
public class ResponseData {
    private int code;
    private String  message;
    private HashMap<String, Object> data = new HashMap<String, Object>();

    public static int SUCCESS = 200;
    public static int FAIL = 500;

    public static String SUCCESS_MESSAGE = "success";
    public static String FAIL_MESSAGE = "fail";

    public ResponseData(){

    }

    public static ResponseData ok(){
        return new ResponseData(SUCCESS,SUCCESS_MESSAGE);
    }

    public ResponseData(int code,String message){
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

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	public ResponseData putDataValue(String key, Object value) {
		this.data.put(key, value);
		return this;
	}
	
}
