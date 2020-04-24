package com.jy.medicine.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jy.medicine.model.Level;
import com.jy.medicine.model.Users;

public interface UserMapper {

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param userpwd
	 * @return
	 */
	Users login(@Param("username") String username, @Param("userpwd") String userpwd);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<Users> getAllUsers(Map<String, Object> map);

	/**
	 * 获取权限等级信息
	 * 
	 * @return
	 */
	List<Level> getAllLevel();

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	int addUser(Users user);

	/**
	 * 根据ID查找用户
	 * 
	 * @param username
	 * @return
	 */
	Users getUserById(String id);

	/**
	 * 查找用户
	 * 
	 * @param username
	 * @return
	 */
	Users getUser(Users user);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	Users getUserByName(String username);

	/**
	 * 根据权限等级查找用户
	 * 
	 * @param username
	 * @return
	 */
	List<Users> getUserByLevel(String level);

	/**
	 * 根据ID修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserById(Users user);

	/**
	 * 根据ID删除用户
	 * 
	 * @param id
	 * @return
	 */
	int delUserById(int id);

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	int updatePwd(Users user);
}
