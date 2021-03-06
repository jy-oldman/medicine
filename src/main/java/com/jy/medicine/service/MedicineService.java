package com.jy.medicine.service;

import java.util.List;

import com.jy.medicine.model.MedType;
import com.jy.medicine.model.Medicine;
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

	/**
	 * 添加药品信息
	 * 
	 * @param medicine
	 * @return
	 */
	int addMed(Medicine medicine);

	/**
	 * 查找药品
	 * 
	 * @param medicine
	 * @return
	 */
	Medicine getMed(Medicine medicine);

	/**
	 * 根据编号查找药品
	 * 
	 * @param medNo
	 * @return
	 */
	Medicine getMedByMedNo(String medNo);

	/**
	 * 根据ID获取药品
	 * 
	 * @param id
	 * @return
	 */
	Medicine getMedById(String id);

	/**
	 * 根据ID修改药品
	 * 
	 * @param medicine
	 * @return
	 */
	int updateMedById(Medicine medicine);

	/**
	 * 根据ID删除药品
	 * 
	 * @param id
	 * @return
	 */
	int delMedById(String id, String type);

	/**
	 * 进货
	 * 
	 * @param medicine
	 * @return
	 */
	int buyMed(Medicine medicine);
}
