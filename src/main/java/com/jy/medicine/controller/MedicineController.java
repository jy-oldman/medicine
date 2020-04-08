package com.jy.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.medicine.service.MedicineService;
import com.jy.medicine.util.Paging;

@Controller
public class MedicineController {
	@Autowired
	MedicineService medicineService;

	@RequestMapping("medicine")
	public String getMedicine(Model model) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", medicineService.getAllMedicines(paging));
		model.addAttribute("medType", medicineService.getMedType());
		return "medicines/medicine";
	}
}
