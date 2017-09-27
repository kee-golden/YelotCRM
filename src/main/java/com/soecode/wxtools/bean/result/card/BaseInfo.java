/**
  * Copyright 2017 aTool.org 
  */
package com.soecode.wxtools.bean.result.card;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Auto-generated: 2017-09-24 21:8:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class BaseInfo {

    private String id;
    @JSONField(name = "logo_url")
    private String logoUrl;
    @JSONField(name = "code_type")
    private String codeType;
    @JSONField(name = "brand_name")
    private String brandName;
    private String title;
    @JSONField(name = "sub_title")
    private String subTitle;
    @JSONField(name = "date_info")
    private DateInfo dateInfo;
    private String color;
    private String notice;
    private String description;
    @JSONField(name = "location_id_list")
    private List<Integer> locationIdList;
    @JSONField(name = "get_limit")
    private int getLimit;
    @JSONField(name = "can_share")
    private boolean canShare;
    @JSONField(name = "can_give_friend")
    private boolean canGiveFriend;
    private String status;
    private Sku sku;
    @JSONField(name = "create_time")
    private int createTime;
    @JSONField(name = "update_time")
    private int updateTime;
    @JSONField(name = "custom_url_name")
    private String customUrlName;
    @JSONField(name = "custom_url")
    private String customUrl;
    @JSONField(name = "custom_url_sub_title")
    private String customUrlSubTitle;
    @JSONField(name = "use_all_locations")
    private boolean useAllLocations;
    @JSONField(name = "area_code_list")
    private List<String> areaCodeList;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setLogoUrl(String logoUrl) {
         this.logoUrl = logoUrl;
     }
     public String getLogoUrl() {
         return logoUrl;
     }

    public void setCodeType(String codeType) {
         this.codeType = codeType;
     }
     public String getCodeType() {
         return codeType;
     }

    public void setBrandName(String brandName) {
         this.brandName = brandName;
     }
     public String getBrandName() {
         return brandName;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setSubTitle(String subTitle) {
         this.subTitle = subTitle;
     }
     public String getSubTitle() {
         return subTitle;
     }

    public void setDateInfo(DateInfo dateInfo) {
         this.dateInfo = dateInfo;
     }
     public DateInfo getDateInfo() {
         return dateInfo;
     }

    public void setColor(String color) {
         this.color = color;
     }
     public String getColor() {
         return color;
     }

    public void setNotice(String notice) {
         this.notice = notice;
     }
     public String getNotice() {
         return notice;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setLocationIdList(List<Integer> locationIdList) {
         this.locationIdList = locationIdList;
     }
     public List<Integer> getLocationIdList() {
         return locationIdList;
     }

    public void setGetLimit(int getLimit) {
         this.getLimit = getLimit;
     }
     public int getGetLimit() {
         return getLimit;
     }

    public void setCanShare(boolean canShare) {
         this.canShare = canShare;
     }
     public boolean getCanShare() {
         return canShare;
     }

    public void setCanGiveFriend(boolean canGiveFriend) {
         this.canGiveFriend = canGiveFriend;
     }
     public boolean getCanGiveFriend() {
         return canGiveFriend;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setSku(Sku sku) {
         this.sku = sku;
     }
     public Sku getSku() {
         return sku;
     }

    public void setCreateTime(int createTime) {
         this.createTime = createTime;
     }
     public int getCreateTime() {
         return createTime;
     }

    public void setUpdateTime(int updateTime) {
         this.updateTime = updateTime;
     }
     public int getUpdateTime() {
         return updateTime;
     }

    public void setCustomUrlName(String customUrlName) {
         this.customUrlName = customUrlName;
     }
     public String getCustomUrlName() {
         return customUrlName;
     }

    public void setCustomUrl(String customUrl) {
         this.customUrl = customUrl;
     }
     public String getCustomUrl() {
         return customUrl;
     }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
         this.customUrlSubTitle = customUrlSubTitle;
     }
     public String getCustomUrlSubTitle() {
         return customUrlSubTitle;
     }

    public void setUseAllLocations(boolean useAllLocations) {
         this.useAllLocations = useAllLocations;
     }
     public boolean getUseAllLocations() {
         return useAllLocations;
     }

    public void setAreaCodeList(List<String> areaCodeList) {
         this.areaCodeList = areaCodeList;
     }
     public List<String> getAreaCodeList() {
         return areaCodeList;
     }

}