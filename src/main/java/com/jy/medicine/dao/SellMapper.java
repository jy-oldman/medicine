package com.jy.medicine.dao;

import java.util.List;
import java.util.Map;

import com.jy.medicine.model.MedSell;
import com.jy.medicine.model.MedTypeVO;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.model.Users;

public interface SellMapper {

	/**
	 * 获取所有药品（库存>0）
	 * 
	 * @param map
	 * @return
	 */
	List<MedTypeVO> getAllMed(Map<String, Object> map);

	/**
	 * 根据药品编码获取药品
	 * 
	 * @param medNo
	 * @return
	 */
	Medicine getMedByMedNo(String medNo);

	/**
	 * 结账
	 * 
	 * @param medSell
	 * @return
	 */
	int pay(MedSell medSell);

	/**
	 * 更新药品库存
	 * 
	 * @param medSell
	 * @return
	 */
	int updateMedCount(MedSell medSell);

	/**
	 * 获取销售明细
	 * 
	 * @param map
	 * @return
	 */
	List<MedSell> getAllSell(Map<String, Object> map);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<Users> getAllUser();

}
