package com.yelot.crm.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomImages {
	private Long id;
	private Long orderId;
	private String imagesJson;
	private List<String> imagesList;
	private String imagesDesc;
	private Long updateUserId;
	private String updateUserName;
	private Date updateAt;
	private String updateAtStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getImagesJson() {
		return imagesJson;
	}

	public void setImagesJson(String imagesJson) {
		this.imagesJson = imagesJson;
	}

	public List<String> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}

	public String getImagesDesc() {
		return imagesDesc;
	}

	public void setImagesDesc(String imagesDesc) {
		this.imagesDesc = imagesDesc;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm", timezone = "GMT+8")
	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getUpdateAtStr() {
		return updateAtStr;
	}

	public void setUpdateAtStr(String updateAtStr) {
		this.updateAtStr = updateAtStr;
	}
	
}
