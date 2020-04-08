package com.jy.medicine.model;

import java.util.Date;

/**
 * 药品销售信息
 * @author Administrator
 *
 */
public class MedSell {
	private int id;// 药品销售ID
	private int med_id;// 药品ID
	private int user_id;// 操作员ID
	private String sellname;// 药品名称
	private double sellprice;// 药品价格
	private int sellcount;// 药品数量
	private Date selltime;// 交易时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMed_id() {
		return med_id;
	}

	public void setMed_id(int med_id) {
		this.med_id = med_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getSellname() {
		return sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
	}

	public double getSellprice() {
		return sellprice;
	}

	public void setSellprice(double sellprice) {
		this.sellprice = sellprice;
	}

	public int getSellcount() {
		return sellcount;
	}

	public void setSellcount(int sellcount) {
		this.sellcount = sellcount;
	}

	public Date getSelltime() {
		return selltime;
	}

	public void setSelltime(Date selltime) {
		this.selltime = selltime;
	}
}
