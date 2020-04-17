package com.jy.medicine.service;

import java.util.List;

import com.jy.medicine.model.MedSell;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.model.Users;
import com.jy.medicine.util.Paging;

public interface SellService {
	/**
	 * 获取所有药品（库存>0）
	 * 
	 * @param map
	 * @return
	 */
	Paging getAllMed(Paging paging);

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
	int pay(List<MedSell> list);

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
	Paging getAllSell(Paging paging);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<Users> getAllUser();
}
