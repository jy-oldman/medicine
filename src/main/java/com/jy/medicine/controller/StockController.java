package com.jy.medicine.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.medicine.model.Medicine;
import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.service.MedicineService;
import com.jy.medicine.service.StockService;
import com.jy.medicine.util.Paging;

@Controller
public class StockController {
	@Autowired
	MedicineService medicineService;
	@Autowired
	MedTypeService medTypeService;
	@Autowired
	StockService stockService;

	@RequestMapping("queryStock")
	public String getMedicine(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", medicineService.getAllMedicines(paging));
		model.addAttribute("medType", medicineService.getMedType());
		session.removeAttribute("stock_tiaojian");
		return "stock/queryStock";
	}

	@RequestMapping("requirement")
	public String requirement(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", stockService.getAllMedicines(paging));
		model.addAttribute("medType", medicineService.getMedType());
		session.removeAttribute("stock_tiaojian1");
		return "stock/requirement";
	}

	@RequestMapping("buyMedicine")
	public String buyMedicine(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", stockService.getAllMedicines(paging));
		model.addAttribute("medType", medicineService.getMedType());
		session.removeAttribute("stock_tiaojian1");
		return "stock/buyMedicine";
	}

	@RequestMapping("stock/getMed")
	@ResponseBody
	public Paging getMed() {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		return stockService.getAllMedicines(paging);
	}

	@RequestMapping("stock/selectMed")
	@ResponseBody
	public Paging selectMed(@RequestParam Map<String, Object> map, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		StringBuffer buffer = new StringBuffer();
		String type = (String) map.get("type");
		String med_name = (String) map.get("med_name");
		String med_no = (String) map.get("med_no");
		String type_id = (String) map.get("type_id");
		String factory = (String) map.get("factory");
		String minprice = (String) map.get("minprice");
		String maxprice = (String) map.get("maxprice");
		String mincount = (String) map.get("mincount");
		String maxcount = (String) map.get("maxcount");
		if (med_name != "" && med_name != null) {
			buffer.append(" and med_name ='" + med_name + "'");
		}
		if (med_no != "" && med_no != null) {
			buffer.append(" and med_no ='" + med_no + "'");
		}
		if (type_id != "" && type_id != null) {
			buffer.append(" and type_id =" + Integer.parseInt(type_id));
		}
		if (factory != "" && factory != null) {
			buffer.append(" and factory ='" + factory + "'");
		}
		if (minprice != "" && minprice != null) {
			buffer.append(" and price >=" + Double.parseDouble(minprice));
		}
		if (maxprice != "" && maxprice != null) {
			buffer.append(" and price <=" + Double.parseDouble(maxprice));
		}
		if (mincount != "" && mincount != null) {
			buffer.append(" and med_count >=" + Double.parseDouble(mincount));
		}
		if (maxcount != "" && maxcount != null) {
			buffer.append(" and med_count <=" + Double.parseDouble(maxcount));
		}
		paging.setTiaojian(buffer.toString());
		if ("require".equals(type)) {
			session.setAttribute("stock_tiaojian1", paging.getTiaojian());
			return stockService.getAllMedicines(paging);
		} else {
			session.setAttribute("stock_tiaojian", paging.getTiaojian());
			return medicineService.getAllMedicines(paging);
		}

	}

	@RequestMapping("toUpdateReqCount")
	@ResponseBody
	public Map<String, Object> toUpdateReqCount(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", medicineService.getMedById(id));
		return map;
	}

	@RequestMapping("updateReqCount")
	@ResponseBody
	public Map<String, Object> updateReqCount(Medicine medicine) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = stockService.updateReqCount(medicine);
		if (result > 0) {
			map.put("error", "修改成功！");
		} else {
			map.put("error", "修改失败！");
		}
		return map;
	}

	@RequestMapping("stock/delData")
	@ResponseBody
	public Map<String, Object> delData(String id, String type) {
		Map<String, Object> map = new HashMap<>();
		Paging paging = new Paging();
		paging.setPageIndex(1);
		int result = stockService.delReqCount(id, type);
		if (result > 0) {
			map.put("error", "删除成功！");
		} else {
			map.put("error", "删除失败！");
		}
		map.put("data", stockService.getAllMedicines(paging));
		return map;
	}

	//
	@RequestMapping("stock/toDataByPage")
	@ResponseBody
	public Paging toDataByPage(int pageIndex, String tiaojian, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		tiaojian = (String) session.getAttribute("stock_tiaojian");
		if (tiaojian == null || tiaojian == "") {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian(tiaojian);
		}
		return medicineService.getAllMedicines(paging);
	}

	@RequestMapping("stock/toDataByPage1")
	@ResponseBody
	public Paging toDataByPage1(int pageIndex, String tiaojian, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		tiaojian = (String) session.getAttribute("stock_tiaojian1");
		if (tiaojian == null || tiaojian == "") {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian(tiaojian);
		}
		return stockService.getAllMedicines(paging);
	}

	@RequestMapping("stock/buyData")
	@ResponseBody
	public Map<String, Object> buyData(Medicine medicine) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		Map<String, Object> map = new HashMap<String, Object>();
		int result = stockService.buyData(medicine);
		if (result > 0) {
			map.put("error", "进货成功！");
		} else {
			map.put("error", "进货失败！");
		}
		map.put("data", stockService.getAllMedicines(paging));
		return map;
	}
}
