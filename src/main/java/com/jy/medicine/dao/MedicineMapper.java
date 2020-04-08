package com.jy.medicine.dao;

import java.util.List;
import java.util.Map;

import com.jy.medicine.model.MedType;
import com.jy.medicine.model.Medicine;

public interface MedicineMapper {

	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	List<Medicine> getAllMedicines(Map<String, Object> map);

	/**
	 * 获取药品种类信息
	 * 
	 * @return
	 */
	List<MedType> getMedType();
}
