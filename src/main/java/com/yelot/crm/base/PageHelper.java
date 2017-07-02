package com.yelot.crm.base;

/**
 * Created by kee on 2016/7/1.
 */
public class PageHelper {
    /**
     * 第一页 page = 0
     */
    private Integer page;
    private Integer size;
    /**
     * 分页中的第一个偏移值
     */
    private Integer offset;
    private String sort;
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public PageHelper(){

    }
    public PageHelper(Integer page,Integer size){
        this.page = page;
        this.size = size;
        this.offset = page*size;
    }
    public PageHelper(Integer page, Integer size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.offset = page * size;
    }

}
