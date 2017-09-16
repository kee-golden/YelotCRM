package com.yelot.crm.enums;

/**
 * @author xyzloveabc
 * @2017年9月16日
 */
public enum ChannelSource {
	SOURCE_1(1, "udesk"),
	SOURCE_2(2, "北京7860"),
	SOURCE_3(3, "上海5588"),
	SOURCE_4(4, "总机400"),
	SOURCE_5(5, "杭州3123"),
	SOURCE_6(6, "上门"),
	SOURCE_7(7, "微博"),
	SOURCE_8(8, "微信"),
	SOURCE_9(9, "淘宝C店"),
	SOURCE_10(10, "淘宝B店"),
	SOURCE_11(11, "大众点评"),
	SOURCE_12(12, "老客介绍"),
	SOURCE_13(13, "品牌介绍"),
	SOURCE_14(14, "员工介绍"),
	SOURCE_15(15, "老板介绍"),
	SOURCE_16(16, "官网留言"),
	SOURCE_17(17, "论坛、博客"),
	SOURCE_18(18, "其他");
	
    private int code;
    private String message;

    ChannelSource(int code, String message){
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
