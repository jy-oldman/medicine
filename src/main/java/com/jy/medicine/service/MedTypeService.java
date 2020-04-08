package com.jy.medicine.service;

import com.jy.medicine.util.Paging;

public interface MedTypeService {
	/**
	 * 获取所有药品
	 * 
	 * @return
	 */
	Paging getMedType(Paging paging);

}
