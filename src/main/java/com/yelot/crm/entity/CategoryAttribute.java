package com.yelot.crm.entity;

/**
 * Created by kee on 17/6/1.
 */
public class CategoryAttribute {
	
	/**
	 * 主键
	 */
	private Long id;

	
	/**
	 * 产品类型id
	 */
	private Long category_id;

	/**
	 * 产品属性id
	 */
	private String attribute_ids;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getAttribute_ids() {
		return attribute_ids;
	}

	public void setAttribute_ids(String attribute_ids) {
		this.attribute_ids = attribute_ids;
	}
}
