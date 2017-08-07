package com.yelot.crm.entity;

import java.util.List;

/**
 * Created by kee on 17/5/26.
 */
public class Category {
    private Long id;
    /**
     * 分类名称：当前分类（珠宝，皮具，腕表，定制）
     */
    private String name;
    /**
     * 排序显示
     */
    private int sort;

    private Long parentId;

    private List<Category> children;

    public Category(){

    }

    public Category(String name,int sort){
        this.name = name;
        this.sort = sort;
    }

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
