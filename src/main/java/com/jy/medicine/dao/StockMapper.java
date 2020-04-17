package com.jy.medicine.dao;

import java.util.List;
import java.util.Map;

import com.jy.medicine.model.MedTypeVO;
import com.jy.medicine.model.Medicine;

public interface StockMapper {

	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	List<MedTypeVO> getAllRequire(Map<String, Object> map);

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
	int delReqCount(int id);

	/**
	 * 完成进货
	 * 
	 * @param medicine
	 * @return
	 */
	int buyData(Medicine medicine);
}
