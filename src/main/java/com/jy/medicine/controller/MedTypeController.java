package com.jy.medicine.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.medicine.model.MedType;
import com.jy.medicine.model.Medicine;
import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.util.Paging;

@Controller
public class MedTypeController {

	@Autowired
	MedTypeService medTypeService;

	@RequestMapping("medType")
	public String getMedType(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medtype", medTypeService.getMedType(paging));
		session.removeAttribute("medType_tiaojian");
		return "medicines/medtype";
	}

	@RequestMapping("getMedType")
	@ResponseBody
	public Paging getMedType() {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		return medTypeService.getMedType(paging);
	}

	@RequestMapping("selectMedType")
	@ResponseBody
	public Paging selectMedType(String name, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		paging.setTiaojian(" typename like '%" + name + "%'");
		session.setAttribute("medType_tiaojian", paging.getTiaojian());
		return medTypeService.getMedType(paging);
	}

	@RequestMapping("toAddMedType")
	public String toAddMedType() {
		return "medicines/addMedType";
	}

	@RequestMapping("addMedType")
	public String addMedType(MedType medType, HttpSession session) {
		medType.setCreatetime(new Date());
		int result = medTypeService.addMedType(medType);
		if (result > 0) {
			session.setAttribute("error", "添加成功！");
			return "redirect:/medType";
		} else {
			session.setAttribute("error", "添加失败！");
			return "redirect:/toAddMedType";
		}
	}

	@RequestMapping("medType/isExist")
	@ResponseBody
	public String isExist(@ModelAttribute MedType medType) {
		MedType medType2 = new MedType();
		if (medType.getId() == 0) {
			medType2 = medTypeService.getMedTypeByName(medType.getTypename());
		} else {
			medType2 = medTypeService.getMedType(medType);
		}
		if (medType2 != null) {
			return "false";
		} else {
			return "true";
		}
	}

	@RequestMapping("toUpdateMedType")
	@ResponseBody
	public Map<String, Object> toUpdateMedType(String id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", medTypeService.getMedTypeById(id));
		return map;
	}

	@RequestMapping("updateMedType")
	@ResponseBody
	public Map<String, Object> updateMedType(MedType medType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = medTypeService.updateMedTypeById(medType);
		if (result > 0) {
			map.put("error", "修改成功！");
		} else {
			map.put("error", "修改失败！");
		}
		return map;
	}

	@RequestMapping("medType/delData")
	@ResponseBody
	public Map<String, Object> delMedType(String id, String type) {
		List<Medicine> list = new ArrayList<Medicine>();
		Map<String, Object> map = new HashMap<>();
		Paging paging = new Paging();
		paging.setPageIndex(1);
		if ("alone".equals(type)) {
			list = medTypeService.getMedByType(Integer.parseInt(id));
		} else {
			String[] id1 = id.substring(0, id.length() - 1).split(",");
			for (String str : id1) {
				list = medTypeService.getMedByType(Integer.parseInt(str));
			}
		}
		if (list == null) {
			int result = medTypeService.delMedTypeById(id, type);
			if (result > 0) {
				map.put("error", "删除成功！");
			} else {
				map.put("error", "删除失败！");
			}
		} else {
			map.put("error", "所要删除的药品种类有对应的药品，无法删除！");
		}
		map.put("data", medTypeService.getMedType(paging));
		return map;
	}

	@RequestMapping("medType/toDataByPage")
	@ResponseBody
	public Paging toDataByPage(int pageIndex, String tiaojian, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		tiaojian = (String) session.getAttribute("medType_tiaojian");
		if (tiaojian == null || tiaojian == "") {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian(tiaojian);
		}
		return medTypeService.getMedType(paging);
	}

}
