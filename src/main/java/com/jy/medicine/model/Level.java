package com.jy.medicine.model;

/**
 * 权限等级信息
 * @author Administrator
 *
 */
public class Level {

	private int id;//权限ID
	private String levelno;//权限等级编码
	private String levelname;//权限等级名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevelno() {
		return levelno;
	}
	public void setLevelno(String levelno) {
		this.levelno = levelno;
	}
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
}
