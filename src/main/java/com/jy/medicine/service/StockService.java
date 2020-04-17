package com.jy.medicine.service;

import com.jy.medicine.model.Medicine;
import com.jy.medicine.util.Paging;

public interface StockService {
	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	Paging getAllMedicines(Paging paging);

	/**
	 * 修改需求
	 * 
	 * @param medicine
	 * @return
	 */
	int updateReqCount(Medicine medicine);

	/**
	 * 需求归零
	 * 
	 * @param id
	 * @return
	 */
	int delReqCount(String id, String type);

	/**
	 * 完成进货
	 * 
	 * @param medicine
	 * @return
	 */
	int buyData(Medicine medicine);
}
