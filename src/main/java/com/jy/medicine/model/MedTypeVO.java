package com.jy.medicine.model;

/**
 * 药品基本信息
 * 
 * @author Administrator
 *
 */
public class MedTypeVO {
	private int id;// 药品ID
	private int type_id;// 药品种类
	private String typename;// 药品种类名称
	private String med_no;// 药品编码
	private String med_name;// 药品名称
	private String factory;// 生产厂家
	private double price;// 价格
	private String description;// 描述
	private int med_count;// 库存
	private int req_count;// 需求数量
	private String picture;// 图片

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getMed_no() {
		return med_no;
	}

	public void setMed_no(String med_no) {
		this.med_no = med_no;
	}

	public String getMed_name() {
		return med_name;
	}

	public void setMed_name(String med_name) {
		this.med_name = med_name;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMed_count() {
		return med_count;
	}

	public void setMed_count(int med_count) {
		this.med_count = med_count;
	}

	public int getReq_count() {
		return req_count;
	}

	public void setReq_count(int req_count) {
		this.req_count = req_count;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
}
