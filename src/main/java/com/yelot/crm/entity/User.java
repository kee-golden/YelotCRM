package com.yelot.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 后台用户，门店的管理人员
 * Created by yelot on 17/5/15.
 */
public class User {
    private Long id;
    /**
     * 名称
     */
    private String name = "";
    /**
     * 密码
     */
    private String password = "";

    /**
     * 真实名称
     */
    private String realname = "";
    /**
     * 手机号码
     */
    private String phone = "";
    /**
     * 员工编号
     */
    private String emp_no = "";

    private Long shop_id;

    private String shopName;

    /**
     * 所属的门店
     */
    private Shop shop;
    /**
     * 是否被删除，或离职的员工
     */
    private int is_alive;
    /**
     * 创建时间
     */
    private Date create_at;
    /**
     * 更新时间
     */
    private Date update_at;

    public User(){

    }

    public User(String name,String password){
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getIs_alive() {
        return is_alive;
    }

    public void setIs_alive(int is_alive) {
        this.is_alive = is_alive;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
