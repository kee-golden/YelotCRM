package com.yelot.crm.vo;


import java.util.List;

/**
 * @author kee
 *         用来生成菜单
 */
public class BaseMenu {
    private String name;
    private String code;
    private String menuClass;

    public BaseMenu() {
        super();
    }

    private String action;
    private BaseMenu parent;

    private List<BaseMenu> children;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BaseMenu getParent() {
        return parent;
    }

    public void setParent(BaseMenu parent) {
        this.parent = parent;
    }

    public List<BaseMenu> getChildren() {
        return children;
    }

    public void setChildren(List<BaseMenu> children) {
        this.children = children;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public BaseMenu(String name, String code, String action,String menuClass) {
        this.name = name;
        this.code = code;
        this.action = action;
        this.menuClass = menuClass;
    }
}
