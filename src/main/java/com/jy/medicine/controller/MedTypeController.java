package com.jy.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.util.Paging;

@Controller
public class MedTypeController {

	@Autowired
	MedTypeService medTypeService;

	@RequestMapping("medType")
	public String getMedicine(Model model) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medtype", medTypeService.getMedType(paging));
		return "medicines/medtype";
	}
}
