package com.jy.medicine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.medicine.dao.MedicineMapper;
import com.jy.medicine.model.MedType;
import com.jy.medicine.model.MedTypeVO;
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
		paging.setTableName("medicine a,med_type b");
		paging.setClumn("a.*,b.typename");
		if (paging.getTiaojian() == null) {
			paging.setTiaojian(" a.type_id=b.id ");
		} else {
			paging.setTiaojian(" a.type_id=b.id " + paging.getTiaojian());
		}
		paging.setPageSize(5);
		map.put("tableName", paging.getTableName());
		map.put("clumn", paging.getClumn());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<MedTypeVO> list = medicineMapper.getAllMedicines(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public List<MedType> getMedType() {
		return medicineMapper.getMedType();
	}

	@Override
	public int addMed(Medicine medicine) {
		return medicineMapper.addMed(medicine);
	}

	@Override
	public Medicine getMed(Medicine medicine) {
		return medicineMapper.getMed(medicine);
	}

	@Override
	public Medicine getMedByMedNo(String medNo) {
		return medicineMapper.getMedByMedNo(medNo);
	}

	@Override
	public Medicine getMedById(String id) {
		return medicineMapper.getMedById(id);
	}

	@Override
	public int updateMedById(Medicine medicine) {
		return medicineMapper.updateMedById(medicine);
	}

	@Override
	@Transactional
	public int delMedById(String id, String type) {
		if ("alone".equals(type)) {
			return medicineMapper.delMedById(Integer.parseInt(id));
		} else {
			int result = 0;
			String[] id1 = id.substring(0, id.length() - 1).split(",");
			for (String string : id1) {
				result = medicineMapper.delMedById(Integer.parseInt(string));
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
	public int buyMed(Medicine medicine) {
		return medicineMapper.buyMed(medicine);
	}
}
