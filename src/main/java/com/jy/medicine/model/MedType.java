package com.jy.medicine.model;

import java.util.Date;

/**
 * 药品种类信息
 * @author Administrator
 *
 */
public class MedType {
	private int id;// 药品种类ID
	private String typename;// 药品种类名称
	private String description;// 药品种类描述
	private Date createtime;// 添加时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
