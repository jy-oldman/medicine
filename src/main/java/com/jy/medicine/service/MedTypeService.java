package com.jy.medicine.service;

import java.util.List;

import com.jy.medicine.model.MedType;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.util.Paging;

public interface MedTypeService {
	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	Paging getMedType(Paging paging);

	/**
	 * 查找药品种类
	 * 
	 * @param medType
	 * @return
	 */
	MedType getMedType(MedType medType);

	/**
	 * 根据名称查找药品种类
	 * 
	 * @param typename
	 * @return
	 */
	MedType getMedTypeByName(String typename);

	/**
	 * 添加药品种类
	 * 
	 * @param medType
	 * @return
	 */
	int addMedType(MedType medType);

	/**
	 * 根据ID获取药品种类
	 * 
	 * @param id
	 * @return
	 */
	MedType getMedTypeById(String id);

	/**
	 * 根据ID修改药品种类
	 * 
	 * @param medType
	 * @return
	 */
	int updateMedTypeById(MedType medType);

	/**
	 * 根据ID删除药品种类
	 * 
	 * @param id
	 * @return
	 */
	int delMedTypeById(String id, String type);

	/**
	 * 根据药品种类获取药品
	 * 
	 * @param id
	 * @return
	 */
	List<Medicine> getMedByType(int id);
}
