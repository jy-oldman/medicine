package com.jy.medicine.service;

import java.util.List;

import com.jy.medicine.model.MedType;
import com.jy.medicine.util.Paging;

public interface MedicineService {
	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	Paging getAllMedicines(Paging paging);

	/**
	 * 获取药品种类信息
	 * 
	 * @return
	 */
	List<MedType> getMedType();
}
