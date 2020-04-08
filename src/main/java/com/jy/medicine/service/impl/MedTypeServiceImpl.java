package com.jy.medicine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.medicine.dao.MedTypeMapper;
import com.jy.medicine.model.MedType;
import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.Paging;

@Service("medTypeService")
public class MedTypeServiceImpl implements MedTypeService {
	@Autowired
	MedTypeMapper medTypeMapper;

	@Override
	public Paging getMedType(Paging paging) {
		Map<String, Object> map = new HashMap<>();
		paging.setTableName("med_type");
		paging.setPageSize(5);
		map.put("tableName", paging.getTableName());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<MedType> list = medTypeMapper.getMedType(map);
		return DBUtil.getPaging(paging, list, map);
	}

}
