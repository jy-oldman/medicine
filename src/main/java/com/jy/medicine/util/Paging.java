package com.jy.medicine.util;

import java.util.ArrayList;
import java.util.List;

public class Paging {
	
	private String tableName;// 表名
	
	private String clumn;// 查询的列
	
	private String tiaojian;// 查询条件

	private int pageSize;// 每页显示的条数

	private int pageIndex;// 当前页

	private int rowCount;// 总记录数

	private int pageCount;// 总页数

	private int prePage;// 上一页

	private int nextPage;// 下一页

	private List<Object> list = new ArrayList<>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClumn() {
		return clumn;
	}

	public void setClumn(String clumn) {
		this.clumn = clumn;
	}

	public String getTiaojian() {
		return tiaojian;
	}

	public void setTiaojian(String tiaojian) {
		this.tiaojian = tiaojian;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}


}
