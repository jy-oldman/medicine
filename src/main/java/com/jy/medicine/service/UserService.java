package com.jy.medicine.service;

import java.util.List;

import com.jy.medicine.model.Level;
import com.jy.medicine.model.Users;
import com.jy.medicine.util.Paging;

public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param userpwd
	 * @return
	 */
	public Users login(String username, String userpwd);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public Paging getAllUsers(Paging paging);

	/**
	 * 获取权限等级信息
	 * 
	 * @return
	 */
	public List<Level> getAllLevel();

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public int addUser(Users user);

	/**
	 * 根据ID查找用户
	 * 
	 * @param username
	 * @return
	 */
	public Users getUserById(String id);

	/**
	 * 查找用户
	 * 
	 * @param username
	 * @return
	 */
	public Users getUser(Users user);
	
	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public Users getUserByName(String username);

	/**
	 * 根据权限等级查找用户
	 * 
	 * @param username
	 * @return
	 */
	public List<Users> getUserByLevel(String level);

	/**
	 * 根据ID修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserById(Users user);

	/**
	 * 根据ID删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int delUserById(String id, String type);
}
