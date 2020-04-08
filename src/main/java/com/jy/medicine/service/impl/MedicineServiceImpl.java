package com.jy.medicine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.medicine.dao.MedicineMapper;
import com.jy.medicine.model.MedType;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.service.MedicineService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.Paging;

@Service("medicineService")
public class MedicineServiceImpl implements MedicineService {
	@Autowired
	MedicineMapper medicineMapper;

	@Override
	public Paging getAllMedicines(Paging paging) {
		Map<String, Object> map = new HashMap<>();
		paging.setTableName("medicine");
		paging.setPageSize(5);
		map.put("tableName", paging.getTableName());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<Medicine> list = medicineMapper.getAllMedicines(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public List<MedType> getMedType() {
		return medicineMapper.getMedType();
	}
}
