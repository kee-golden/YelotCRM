package com.yelot.crm.vo;

import java.util.List;

/**
 * @author kee
 *         <p>
 *         dataTables ui 封装
 */
public class Tree {

    private String id;
    private String name;
    private String iconSkin;
    private Integer type;
    private Boolean checked;
    private Boolean isParent;
    private String parentId;
    private List<Tree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconSkin='" + iconSkin + '\'' +
                ", type=" + type +
                ", isParent=" + isParent +
                ", checked=" + checked +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                '}';
    }
}
