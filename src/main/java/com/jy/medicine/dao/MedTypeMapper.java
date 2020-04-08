package com.jy.medicine.dao;

import java.util.List;
import java.util.Map;

import com.jy.medicine.model.MedType;

public interface MedTypeMapper {

	/**
	 * 获取所有药品种类
	 * 
	 * @return
	 */
	List<MedType> getMedType(Map<String, Object> map);

}
