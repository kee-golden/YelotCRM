package com.yelot.crm.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/5/30.
 */
public class Privilege {
    private Long id;
    private String name;
    private String code;

    private Integer type;

    private String action;

    /**
     * 菜单的css icon 样式名称
     */
    private String menuClass;

    private Privilege parent;

    private Long sort;

    private Date createAt;

    private Date updateAt;

    private List<Privilege> children;

    private List<RolePrivilegeRef> rolePrivilegeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public Privilege getParent() {
        return parent;
    }

    public void setParent(Privilege parent) {
        this.parent = parent;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<Privilege> getChildren() {
        return children;
    }

    public void setChildren(List<Privilege> children) {
        this.children = children;
    }

    public List<RolePrivilegeRef> getRolePrivilegeList() {
        return rolePrivilegeList;
    }

    public void setRolePrivilegeList(List<RolePrivilegeRef> rolePrivilegeList) {
        this.rolePrivilegeList = rolePrivilegeList;
    }
}
