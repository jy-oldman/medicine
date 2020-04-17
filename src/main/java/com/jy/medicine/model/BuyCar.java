package com.jy.medicine.model;

import java.util.Date;
import java.util.List;

/**
 * 购物车
 * 
 * @author Administrator
 *
 */
public class BuyCar {

	private int id;// 操作员ID
	private List<MedSell> list;
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<MedSell> getList() {
		return list;
	}

	public void setList(List<MedSell> list) {
		this.list = list;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
