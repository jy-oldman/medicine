package com.jy.medicine.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = { " ", "/" })
	public String home() {
		return "login";
	}

	@RequestMapping("nav")
	public String navMain() {
		return "nav";
	}

	@RequestMapping("table")
	public String tableMain() {
		return "table";
	}

	@RequestMapping("outSystem")
	public String outSystem(HttpSession session) {
		session.invalidate();
		return "login";
	}

}
