package com.jy.medicine.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.medicine.dao.UserMapper;
import com.jy.medicine.model.Level;
import com.jy.medicine.model.Users;
import com.jy.medicine.service.UserService;
import com.jy.medicine.util.DBUtil;
import com.jy.medicine.util.MD5Utils;
import com.jy.medicine.util.Paging;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public Users login(String username, String userpwd) {
		userpwd = MD5Utils.MD5Encode(userpwd, "utf-8");
		return userMapper.login(username, userpwd);
	}

	@Override
	public Paging getAllUsers(Paging paging) {
		Map<String, Object> map = new HashMap<>();
		paging.setTableName("users");
		paging.setPageSize(2);
		map.put("tableName", paging.getTableName());
		map.put("tiaojian", paging.getTiaojian());
		map.put("pageIndex", paging.getPageIndex());
		map.put("pageSize", paging.getPageSize());
		List<Users> list = userMapper.getAllUsers(map);
		return DBUtil.getPaging(paging, list, map);
	}

	@Override
	public List<Level> getAllLevel() {
		return userMapper.getAllLevel();
	}

	@Override
	public int addUser(Users user) {
		user.setUserpwd(MD5Utils.MD5Encode(user.getUserpwd(), "utf-8"));
		return userMapper.addUser(user);
	}

	@Override
	public Users getUserById(String id) {
		return userMapper.getUserById(id);
	}

	@Override
	public Users getUserByName(String username) {
		return userMapper.getUserByName(username);
	}

	@Override
	public List<Users> getUserByLevel(String level) {
		return userMapper.getUserByLevel(level);
	}

	@Override
	public int updateUserById(Users user) {
		user.setLevel(DBUtil.spiltRtoL(user.getLevel()).substring(0, 1));
		if (user.getUserpwd() != "" || user.getUserpwd() != null) {
			user.setUserpwd(MD5Utils.MD5Encode(user.getUserpwd(), "utf-8"));
		}
		return userMapper.updateUserById(user);
	}

	@Override
	@Transactional
	public int delUserById(String id, String type) {
		if ("alone".equals(type)) {
			return userMapper.delUserById(Integer.parseInt(id));
		} else {
			int result = 0;
			String[] id1 = id.substring(0, id.length() - 1).split(",");
			for (String string : id1) {
				result = userMapper.delUserById(Integer.parseInt(string));
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
	public Users getUser(Users user) {
		return userMapper.getUser(user);
	}

}
