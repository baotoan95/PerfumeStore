package com.baotoan.dev.dao;

import java.util.Map;

import com.baotoan.dev.entity.User;

public interface UserDAO {
	public boolean addUser(User user);
	public boolean delUser(String userId);
	public boolean updateUser(User user);
	
	public Map<String, Object> getUsers(int currentPage, int numOfRecordPerPage);
	public User getUserByUsername(String username);
	
	public String checkLogin(String username, String password, String type);
	public String activeAccount(String username, String code);
	public String checkRequestRMPassword(String username);
	public String requestRMPassword(String username, String code, String secureAnswer);
	public String remindPassword(String username, String password);
}
