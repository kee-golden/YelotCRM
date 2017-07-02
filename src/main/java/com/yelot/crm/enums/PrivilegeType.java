package com.yelot.crm.enums;

/**
 * @author kee
 */
public enum PrivilegeType {
    MENU(1, "菜单", "menu"),
    BUTTON(2, "按钮", "button");

    private Integer code;
    private String message;
    private String skin;

    PrivilegeType(Integer code, String message, String skin) {
        this.code = code;
        this.message = message;
        this.skin = skin;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public static String getSelectOptions(Integer code) {
        String options = "";
        for (PrivilegeType privilegeType : values()) {
            options += " <option value=\"" + privilegeType.getCode() + "\"";
            if (privilegeType.getCode().equals(code)) {
                options += "selected=selected";
            }
            options += ">" + privilegeType.getMessage() + "</option>";
        }

        return options;

    }

    public static String getIconSkin(Integer code) {
        for (PrivilegeType privilegeType : values()) {
            if (privilegeType.getCode().equals(code))
                return privilegeType.getSkin();
        }
        return null;
    }


}
