package com.jy.medicine.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jy.medicine.model.Medicine;
import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.service.MedicineService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.Paging;

@Controller
public class MedicineController {
	@Autowired
	MedicineService medicineService;
	@Autowired
	MedTypeService medTypeService;

	@RequestMapping("medicine")
	public String getMedicine(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", medicineService.getAllMedicines(paging));
		model.addAttribute("medType", medicineService.getMedType());
		session.removeAttribute("med_tiaojian");
		return "medicines/medicine";
	}

	@RequestMapping("getMed")
	@ResponseBody
	public Paging getMed() {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		return medicineService.getAllMedicines(paging);
	}

	@RequestMapping("selectMed")
	@ResponseBody
	public Paging selectMed(@RequestParam Map<String, Object> map, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		StringBuffer buffer = new StringBuffer();
		String med_name = (String) map.get("med_name");
		String med_no = (String) map.get("med_no");
		String type_id = (String) map.get("type_id");
		String factory = (String) map.get("factory");
		String minprice = (String) map.get("minprice");
		String maxprice = (String) map.get("maxprice");
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
		paging.setTiaojian(buffer.toString());
		session.setAttribute("med_tiaojian", paging.getTiaojian());
		return medicineService.getAllMedicines(paging);
	}

	@RequestMapping("toAddMed")
	public String toAddMed(Model model) {
		model.addAttribute("medtype", medicineService.getMedType());
		return "medicines/addMed";
	}

	@RequestMapping("addMed")
	public String addMed(Medicine medicine, @RequestParam(value = "attach_pic") MultipartFile imFile,
			HttpSession session) {
		if (!imFile.isEmpty()) {
			medicine.setPicture(DBUtil.upLoadFile(imFile));
		} else {
			medicine.setPicture("/upload/999.jpg");
		}
		int result = medicineService.addMed(medicine);
		if (result > 0) {
			session.setAttribute("error", "添加成功！");
			return "redirect:/medicine";
		} else {
			session.setAttribute("error", "添加失败！");
			return "redirect:/toAddMed";
		}
	}

	@RequestMapping("med/isExist")
	@ResponseBody
	public String isExist(@ModelAttribute Medicine medicine) {
		Medicine medicine2 = new Medicine();
		if (medicine.getId() == 0) {
			medicine2 = medicineService.getMedByMedNo(medicine.getMed_no());
		} else {
			medicine2 = medicineService.getMed(medicine);
		}
		if (medicine2 != null) {
			return "false";
		} else {
			return "true";
		}
	}

	@RequestMapping("toUpdateMed")
	@ResponseBody
	public Map<String, Object> toUpdateMed(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", medicineService.getMedById(id));
		return map;
	}

	@RequestMapping("updateMed")
	@ResponseBody
	public Map<String, Object> updateMed(Medicine medicine, @RequestParam(value = "attach_pic") MultipartFile imFile)
			throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!imFile.isEmpty()) {
			medicine.setPicture(DBUtil.upLoadFile(imFile));
		}
		int result = medicineService.updateMedById(medicine);
		if (result > 0) {
			map.put("error", "修改成功！");
		} else {
			map.put("error", "修改失败！");
		}
		return map;

	}

	@RequestMapping("med/delData")
	@ResponseBody
	public Map<String, Object> delMed(String id, String type) {
		Map<String, Object> map = new HashMap<>();
		Paging paging = new Paging();
		paging.setPageIndex(1);
		int result = medicineService.delMedById(id, type);
		if (result > 0) {
			map.put("error", "删除成功！");
		} else {
			map.put("error", "删除失败！");
		}
		map.put("data", medicineService.getAllMedicines(paging));
		return map;
	}

	@RequestMapping("med/toDataByPage")
	@ResponseBody
	public Paging toDataByPage(int pageIndex, String tiaojian, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		tiaojian = (String) session.getAttribute("med_tiaojian");
		if (tiaojian == null || tiaojian == "") {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian(tiaojian);
		}
		return medicineService.getAllMedicines(paging);
	}

	@RequestMapping("buyMed")
	@ResponseBody
	public Map<String, Object> buyMed(Medicine medicine) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = medicineService.buyMed(medicine);
		if (result > 0) {
			map.put("error", "进货成功！");
		} else {
			map.put("error", "进货失败！");
		}
		return map;
	}
}
