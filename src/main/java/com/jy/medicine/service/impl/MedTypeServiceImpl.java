package com.jy.medicine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.medicine.dao.MedTypeMapper;
import com.jy.medicine.model.MedType;
import com.jy.medicine.model.Medicine;
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
		paging.setPageSize(2);
		map.put("tableName", paging.getTableName());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<MedType> list = medTypeMapper.getAllMedType(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public MedType getMedType(MedType medType) {
		return medTypeMapper.getMedType(medType);
	}

	@Override
	public MedType getMedTypeByName(String typename) {
		return medTypeMapper.getMedTypeByName(typename);
	}

	@Override
	public int addMedType(MedType medType) {
		return medTypeMapper.addMedType(medType);
	}

	@Override
	public MedType getMedTypeById(String id) {
		return medTypeMapper.getMedTypeById(id);
	}

	@Override
	public int updateMedTypeById(MedType medType) {
		return medTypeMapper.updateMedTypeById(medType);
	}

	@Override
	@Transactional
	public int delMedTypeById(String id, String type) {
		if ("alone".equals(type)) {
			return medTypeMapper.delMedTypeById(Integer.parseInt(id));
		} else {
			int result = 0;
			String[] id1 = id.substring(0, id.length() - 1).split(",");
			for (String string : id1) {
				result = medTypeMapper.delMedTypeById(Integer.parseInt(string));
				if (result > 0) {
					result = 0;
				} else {
					result = -1;
					break;
				}
			}
			if (result == 0) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	@Override
	public List<Medicine> getMedByType(int id) {
		return medTypeMapper.getMedByType(id);
	}

}
