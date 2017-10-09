package com.yelot.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by kee on 17/5/28.
 */
public class Customer {
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话，手机电话
     */
    private String phone;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址,
     */
    private String address;
    /**
     * 性别，0，女性，1，男性
     */
    private int sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * qq
     */
    private String qq;

    /**
     * 微信ID
     */
    private String wechatId;

    /**
     * 微信昵称
     */
    private String wechatNickname;

    /**
     * 微信号记录在哪个设备上，目前有多台设备不用的号码添加了用户
     */
    private String deviceName;
    /**
     * 备注
     */
    private String comment;

    /**
     * 客户来源
     */
    private  int channelSource;
    /**
     * 是否删除，有效
     */
    private int is_alive;

    /**
     * 用户类别，0新客户，1老客户
     */
    private int type;
    private Date firstConsultAt;
    private Date createAt;
    private Date updateAt;

    /**
     * 创建者ID
     */
    private Long createUserId;
    
    /**
     * 其他联系方式
     */
    private String otherPhone;

    public Customer(){

    }

    public Customer(String name,String phone,String address,int sex,String email,String qq,String comment){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.qq = qq;
        this.comment = comment;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIs_alive() {
        return is_alive;
    }

    public void setIs_alive(int is_alive) {
        this.is_alive = is_alive;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatNickname() {
        return wechatNickname;
    }

    public void setWechatNickname(String wechatNickname) {
        this.wechatNickname = wechatNickname;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getFirstConsultAt() {
        return firstConsultAt;
    }

    public void setFirstConsultAt(Date firstConsultAt) {
        this.firstConsultAt = firstConsultAt;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getChannelSource() {
        return channelSource;
    }

    public void setChannelSource(int channelSource) {
        this.channelSource = channelSource;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
    
}
