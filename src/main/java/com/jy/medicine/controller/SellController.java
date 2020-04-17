package com.jy.medicine.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.medicine.model.BuyCar;
import com.jy.medicine.model.MedSell;
import com.jy.medicine.service.MedTypeService;
import com.jy.medicine.service.MedicineService;
import com.jy.medicine.service.SellService;
import com.jy.medicine.service.StockService;
import com.jy.medicine.service.UserService;
import com.jy.medicine.util.Paging;

@Controller
public class SellController {
	@Autowired
	MedicineService medicineService;
	@Autowired
	MedTypeService medTypeService;
	@Autowired
	StockService stockService;
	@Autowired
	SellService sellService;
	@Autowired
	UserService userService;

	@RequestMapping("sellMed")
	public String getMedicine(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("medicine", sellService.getAllMed(paging));
		model.addAttribute("medType", medicineService.getMedType());
		session.removeAttribute("sell_tiaojian");
		return "sell/sellMedicine";
	}

	@RequestMapping("sellDetail")
	public String sellDetail(Model model, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		model.addAttribute("sell", sellService.getAllSell(paging));
		model.addAttribute("medType", medicineService.getMedType());
		model.addAttribute("user", sellService.getAllUser());
		session.removeAttribute("sell_tiaojian1");
		return "sell/sellDetail";
	}

	@RequestMapping("sell/getMed")
	@ResponseBody
	public Paging getMed() {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		return stockService.getAllMedicines(paging);
	}

	@RequestMapping("sell/selectMed")
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
		session.setAttribute("sell_tiaojian", paging.getTiaojian());
		return sellService.getAllMed(paging);
	}

	@RequestMapping("selectSell")
	@ResponseBody
	public Map<String, Object> selectSell(@RequestParam Map<String, Object> map, HttpSession session)
			throws ParseException {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		StringBuffer buffer = new StringBuffer();
		String med_name = (String) map.get("med_name");
		String med_no = (String) map.get("med_no");
		String sellno = (String) map.get("sellno");
		String username = (String) map.get("username");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		buffer.append(" 1=1 ");
		if (med_name != "" && med_name != null) {
			buffer.append(" and sellname ='" + med_name + "'");
		}
		if (med_no != "" && med_no != null) {
			buffer.append(" and med_id ='" + med_no + "'");
		}
		if (sellno != "" && sellno != null) {
			buffer.append(" and sellno ='" + sellno + "'");
		}
		if (username != "" && username != null) {
			if (userService.getUserByName(username) == null) {
				buffer.append(" and user_id =0");
			} else {
				buffer.append(" and user_id =" + userService.getUserByName(username).getId());
			}
		}
		if (startDate != "" && startDate != null) {
			buffer.append(" and selltime >='" + sdf.format(sdf.parse(startDate)) + "'");
		}
		if (endDate != "" && endDate != null) {
			buffer.append(" and selltime <='" + sdf.format(sdf.parse(endDate)) + "'");
		}
		paging.setTiaojian(buffer.toString());
		session.setAttribute("sell_tiaojian1", paging.getTiaojian());
		map.put("sell", sellService.getAllSell(paging));
		map.put("user", sellService.getAllUser());
		return map;
	}

	@RequestMapping("toBuyMed")
	@ResponseBody
	public Map<String, Object> toBuyMed(String id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		MedSell medSell = new MedSell();
		BuyCar buyCar = new BuyCar();
		List<MedSell> list = new ArrayList<MedSell>();
		if (session.getAttribute(session.getAttribute("userId").toString()) != null) {
			buyCar = (BuyCar) session.getAttribute(session.getAttribute("userId").toString());
			list = buyCar.getList();
			boolean isInto = false;
			for (MedSell s : list) {
				if (s.getMed_id().equals(medicineService.getMedById(id).getMed_no())) {
					isInto = true;
				}
			}
			if (!isInto) {
				medSell.setMed_id(medicineService.getMedById(id).getMed_no());
				medSell.setSellname(medicineService.getMedById(id).getMed_name());
				medSell.setSellprice(medicineService.getMedById(id).getPrice());
				medSell.setSellcount(1);
				medSell.setUser_id((int) session.getAttribute("userId"));
				list.add(medSell);
			}
		} else {
			medSell.setMed_id(medicineService.getMedById(id).getMed_no());
			medSell.setSellname(medicineService.getMedById(id).getMed_name());
			medSell.setSellprice(medicineService.getMedById(id).getPrice());
			medSell.setSellcount(1);
			medSell.setUser_id((int) session.getAttribute("userId"));
			list.add(medSell);
		}
		buyCar.setList(list);
		map.put("data", buyCar);
		session.setAttribute(session.getAttribute("userId").toString(), buyCar);
		return map;
	}

	@RequestMapping("sell/delData")
	@ResponseBody
	public Map<String, Object> delData(String id, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		BuyCar buyCar = new BuyCar();
		List<MedSell> list = new ArrayList<MedSell>();
		buyCar = (BuyCar) session.getAttribute(session.getAttribute("userId").toString());
		list = buyCar.getList();
		int index = 0;
		for (MedSell s : list) {
			if (s.getMed_id().equals(id)) {
				index = list.indexOf(s);
			}
		}
		list.remove(index);
		buyCar.setList(list);
		map.put("data", buyCar);
		session.setAttribute(session.getAttribute("userId").toString(), buyCar);
		return map;
	}

	@RequestMapping("sell/toDataByPage")
	@ResponseBody
	public Paging toDataByPage(int pageIndex, String tiaojian, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		tiaojian = (String) session.getAttribute("sell_tiaojian");
		if (tiaojian == null || tiaojian == "") {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian(tiaojian);
		}
		return sellService.getAllMed(paging);
	}

	@RequestMapping("sell/toDataByPage1")
	@ResponseBody
	public Map<String, Object> toDataByPage1(int pageIndex, String tiaojian, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		tiaojian = (String) session.getAttribute("sell_tiaojian1");
		if (tiaojian == null || tiaojian == "") {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian(tiaojian);
		}
		map.put("sell", sellService.getAllSell(paging));
		map.put("user", sellService.getAllUser());
		return map;
	}

	@RequestMapping("updateMedCount")
	@ResponseBody
	public Map<String, Object> buyData(String id, int count, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		BuyCar buyCar = new BuyCar();
		List<MedSell> list = new ArrayList<MedSell>();
		buyCar = (BuyCar) session.getAttribute(session.getAttribute("userId").toString());
		list = buyCar.getList();
		for (MedSell s : list) {
			if (s.getMed_id().equals(id)) {
				if (count > sellService.getMedByMedNo(id).getMed_count()) {
					map.put("error", "库存不足！");
				} else {
					s.setSellcount(count);
				}
			}
		}
		buyCar.setList(list);
		map.put("data", buyCar);
		session.setAttribute(session.getAttribute("userId").toString(), buyCar);
		return map;
	}

	@RequestMapping("delCar")
	@ResponseBody
	public Map<String, Object> delCar(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		session.removeAttribute(session.getAttribute("userId").toString());
		map.put("data", null);
		return map;
	}

	@RequestMapping("pay")
	@ResponseBody
	public Map<String, Object> pay(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		BuyCar buyCar = (BuyCar) session.getAttribute(session.getAttribute("userId").toString());
		List<MedSell> list = buyCar.getList();
		int result = sellService.pay(list);
		if (result > 0) {
			map.put("error", "结账成功！");
			session.removeAttribute(session.getAttribute("userId").toString());
			buyCar = null;
		} else {
			map.put("error", "结账失败！");
		}
		map.put("data", buyCar);
		return map;
	}
}
