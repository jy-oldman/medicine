package com.jy.medicine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.medicine.dao.StockMapper;
import com.jy.medicine.model.MedTypeVO;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.service.StockService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.Paging;

@Service("stockService")
public class StockServiceImpl implements StockService {
	@Autowired
	StockMapper stockMapper;

	@Override
	public Paging getAllMedicines(Paging paging) {
		Map<String, Object> map = new HashMap<>();
		paging.setTableName("medicine a,med_type b");
		paging.setClumn("a.*,b.typename");
		if (paging.getTiaojian() == null) {
			paging.setTiaojian(" a.type_id=b.id and req_count>0 ");
		} else {
			paging.setTiaojian(" a.type_id=b.id and req_count>0 " + paging.getTiaojian());
		}
		paging.setPageSize(5);
		map.put("tableName", paging.getTableName());
		map.put("clumn", paging.getClumn());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<MedTypeVO> list = stockMapper.getAllRequire(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public int updateReqCount(Medicine medicine) {
		return stockMapper.updateReqCount(medicine);
	}

	@Override
	@Transactional
	public int delReqCount(String id, String type) {
		if ("alone".equals(type)) {
			return stockMapper.delReqCount(Integer.parseInt(id));
		} else {
			int result = 0;
			String[] id1 = id.substring(0, id.length() - 1).split(",");
			for (String string : id1) {
				result = stockMapper.delReqCount(Integer.parseInt(string));
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
	public int buyData(Medicine medicine) {
		return stockMapper.buyData(medicine);
	}

}
