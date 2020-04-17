package com.jy.medicine.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.medicine.dao.SellMapper;
import com.jy.medicine.model.MedSell;
import com.jy.medicine.model.MedTypeVO;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.model.Users;
import com.jy.medicine.service.SellService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.Paging;

@Service("sellService")
public class SellServiceImpl implements SellService {

	@Autowired
	SellMapper sellMapper;

	@Override
	public Paging getAllMed(Paging paging) {
		Map<String, Object> map = new HashMap<>();
		paging.setTableName("medicine a,med_type b");
		paging.setClumn("a.*,b.typename");
		if (paging.getTiaojian() == null) {
			paging.setTiaojian(" a.type_id=b.id and med_count>0 ");
		} else {
			paging.setTiaojian(" a.type_id=b.id and med_count>0 " + paging.getTiaojian());
		}
		paging.setPageSize(5);
		map.put("tableName", paging.getTableName());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<MedTypeVO> list = sellMapper.getAllMed(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public Medicine getMedByMedNo(String medNo) {
		return sellMapper.getMedByMedNo(medNo);
	}

	@Override
	@Transactional
	public int pay(List<MedSell> list) {
		int result = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yymmddHHmmss");
		for (MedSell s : list) {
			s.setSelltime(date);
			s.setSellno("YP" + sdf.format(date));
			if (sellMapper.updateMedCount(s) > 0) {
				result = sellMapper.pay(s);
			} else {
				break;
			}

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

	@Override
	public int updateMedCount(MedSell medSell) {
		return sellMapper.updateMedCount(medSell);
	}

	@Override
	public Paging getAllSell(Paging paging) {
		Map<String, Object> map = new HashMap<>();
		paging.setTableName("med_sell");
		paging.setPageSize(5);
		map.put("tableName", paging.getTableName());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<MedSell> list = sellMapper.getAllSell(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public List<Users> getAllUser() {
		return sellMapper.getAllUser();
	}

}
