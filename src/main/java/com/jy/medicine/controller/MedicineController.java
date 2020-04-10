package com.jy.medicine.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.service.MedicineService;
import com.jy.medicine.util.Paging;

@Controller
public class MedicineController {
	@Autowired
	MedicineService medicineService;
	@Autowired
	MedTypeService medTypeService;

	@RequestMapping("medicine")
	public String getMedicine(Model model) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", medicineService.getAllMedicines(paging));
		model.addAttribute("medType", medicineService.getMedType());
		return "medicines/medicine";
	}

	@RequestMapping("selectMed")
	@ResponseBody
	public Paging selectMed(@RequestParam Map<String, Object> map, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		String med_name = (String) map.get("med_name");
		String med_no = (String) map.get("med_no");
		String type_id = (String) map.get("type_id");
		String factory = (String) map.get("factory");
		String minprice = (String) map.get("minprice");
		String maxprice = (String) map.get("maxprice");
		if (med_name != "" || med_name != null) {
			paging.setTiaojian(" med_name ='" + med_name + "'");
		} else if (med_no != "" || med_no != null) {
			paging.setTiaojian(" med_no ='" + med_no + "'");
		} else if (type_id != "" || type_id != null) {
			paging.setTiaojian(" type_id =" + Integer.parseInt(type_id));
		} else if (factory != "" || factory != null) {
			paging.setTiaojian(" factory ='" + factory + "'");
		} else if (minprice != "" || minprice != null) {
			paging.setTiaojian(" minprice >" + Double.parseDouble(minprice));
		} else if (maxprice != "" || maxprice != null) {
			paging.setTiaojian(" maxprice <" + Double.parseDouble(maxprice));
		}
		return medicineService.getAllMedicines(paging);
	}

	@RequestMapping("toAddMed")
	public String toAddMed(Model model) {
		model.addAttribute("medtype", medicineService.getMedType());
		return "medicines/addMed";
	}

}
