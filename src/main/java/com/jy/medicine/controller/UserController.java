package com.jy.medicine.controller;

import java.util.Date;
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

import com.jy.medicine.model.Users;
import com.jy.medicine.service.UserService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.MD5Utils;
import com.jy.medicine.util.Paging;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("login")
	public String login(Users user, HttpSession session, Model model) {
		Users user1 = userService.login(user.getUsername(), user.getUserpwd());
		if (user1 == null) {
			model.addAttribute("error", "用户名或密码错误！");
			return "login";
		} else {
			model.addAttribute("user", user1);
			session.setAttribute("level", user1.getLevel());
			session.setAttribute("userId", user1.getId());
			return "main";
		}
	}

	@RequestMapping("user")
	public String getAllUsers(String level, String pageIndex, Model model, HttpSession session) {
		session.setAttribute("userLevel", "all");
		session.setAttribute("pageIndex", 1);
		Paging paging = new Paging();
		paging.setPageIndex(1);
		if (pageIndex != null) {
			paging.setPageIndex(Integer.parseInt(pageIndex));
		}
		model.addAttribute("user", userService.getAllUsers(paging));
		model.addAttribute("level", userService.getAllLevel());
		if (level != "" || level != null) {
			model.addAttribute("backLevel", level);
		}
		return "users/user";
	}

	@RequestMapping("toUserByPage")
	@ResponseBody
	public Paging toUserByPage(int pageIndex, String level, HttpSession session) {
		Paging paging = new Paging();
		session.setAttribute("pageIndex", pageIndex);
		paging.setPageIndex(pageIndex);
		level = (String) session.getAttribute("userLevel");
		if ("all".equals(level)) {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian("level='" + level + "'");
		}
		return userService.getAllUsers(paging);
	}

	@RequestMapping("selectUser")
	@ResponseBody
	public Paging selectUser(String level, HttpSession session) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		session.setAttribute("userLevel", level);
		if ("all".equals(level)) {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian("level='" + level + "'");
		}
		return userService.getAllUsers(paging);
	}

	@RequestMapping("getAdd")
	public String getAdd(Model model) {
		model.addAttribute("level", userService.getAllLevel());
		return "users/addUser";
	}

	@RequestMapping(value = { "isExist" })
	@ResponseBody
	public String isExist(@ModelAttribute Users user) {
		Users user1 = new Users();
		if (user.getId() == 0) {
			user1 = userService.getUserByName(user.getUsername());
		} else {
			user1 = userService.getUser(user);
		}

		if (user1 != null) {
			return "false";
		} else {
			return "true";
		}
	}

	@RequestMapping("addUser")
	public String addUser(Users user, @RequestParam(value = "attach_pic", required = true) MultipartFile imFile,
			HttpSession session) {
		user.setCreatetime(new Date());
		user.setUserpwd(MD5Utils.MD5Encode(user.getUserpwd(), ""));
		if (!imFile.isEmpty()) {
			user.setPhoto(DBUtil.upLoadFile(imFile));
		} else {
			user.setPhoto("/upload/default.jpg");
		}
		int result = userService.addUser(user);
		if (result > 0) {
			session.setAttribute("error", "添加成功！");
			return "redirect:/user";
		} else {
			session.setAttribute("error", "添加失败！");
			return "redirect:/getAdd";
		}
	}

	@RequestMapping(value = "getUpdate", params = { "id" })
	public String getUpdate(String id, Model model, HttpSession session) {
		model.addAttribute("updateIndex", session.getAttribute("pageIndex"));
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("level", userService.getAllLevel());
		return "users/updateUser";
	}

	@RequestMapping("updateUser")
	public String updateUser(Users user, HttpSession session) {
		int result = userService.updateUserById(user);
		if (result > 0) {
			session.setAttribute("error", "修改成功！");
		} else {
			session.setAttribute("error", "修改失败！");
		}
		return "redirect:/user";
	}

	@RequestMapping("delUser")
	@ResponseBody
	public Map<String, Object> delUser(String id, String type) {
		Paging paging = new Paging();
		paging.setPageIndex(1);
		Map<String, Object> map = new HashMap<>();
		int result = userService.delUserById(id, type);
		if (result > 0) {
			map.put("error", "删除成功！");
		} else {
			map.put("error", "删除失败！");
		}
		map.put("user", userService.getAllUsers(paging));
		return map;
	}

	@RequestMapping("backUser")
	public String backUser(int pageIndex, String level, Model model) {
		Paging paging = new Paging();
		paging.setPageIndex(pageIndex);
		if ("all".equals(level)) {
			paging.setTiaojian("");
		} else {
			paging.setTiaojian("level='" + level + "'");
		}
		model.addAttribute("user", userService.getAllUsers(paging));
		model.addAttribute("level", userService.getAllLevel());
		return "users/user";
	}

	@RequestMapping("delSession")
	@ResponseBody
	public void delSession(String session, HttpSession session2) {
		session2.removeAttribute(session);
	}
}
