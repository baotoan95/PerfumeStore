package com.baotoan.dev.service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.dao.UserDAO;
import com.baotoan.dev.entity.User;
import com.baotoan.dev.utils.GenerateCode;

public class UserDAOImpl extends StandardConnection implements UserDAO {

	@Override
	public boolean addUser(User user) {
		String sql = "{CALL registryAccount(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement callable = connection.getCallableStatement(sql);
		try {
			callable.setString(1, user.getUsername());
			callable.setString(2, user.getPassword());
			callable.setString(3, user.getName());
			callable.setString(4, user.getAddress());
			callable.setString(5, user.getCity());
			callable.setString(6, user.getPhone());
			callable.setInt(7, user.getScore());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			callable.setString(8, format.format(user.getRegistryDate()));
			callable.setString(9, user.getEmail());
			callable.setString(10, user.getStatus());
			callable.setString(11, user.getSecureQuestion());
			callable.setString(12, user.getSecureAnswer());
			callable.setInt(13, user.getRole());
			callable.setBoolean(14, user.isFBAcc());
			callable.registerOutParameter(15, Types.VARCHAR);
			callable.execute();
			return "ok".equalsIgnoreCase(callable.getString(15));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  false;
	}

	@Override
	public boolean delUser(String username) {
		String sql = "DELETE FROM users WHERE username='" + username + "'";
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "UPDATE users SET password=?,name=?,address=?,city=?,phone=?,score=?,registryDate=?,email=?,status=?,secureQuestion=?,secureAnswer=?,role=?, isFbAcc=? WHERE username=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, user.getPassword());
			pre.setString(2, user.getName());
			pre.setString(3, user.getAddress());
			pre.setString(4, user.getCity());
			pre.setString(5, user.getPhone());
			pre.setInt(6, user.getScore());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pre.setString(7, format.format(user.getRegistryDate()));
			pre.setString(8, user.getEmail());
			pre.setString(9, user.getStatus());
			pre.setString(10, user.getSecureQuestion());
			pre.setString(11, user.getSecureAnswer());
			pre.setInt(12, user.getRole());
			pre.setBoolean(13, user.isFBAcc());
			pre.setString(14, user.getUsername());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Map<String, Object> getUsers(int currentPage, int numRecordPerPage) {
		String sql = "SELECT COUNT(*) FROM users";
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			ResultSet totalRecords = connection.getStatement().executeQuery(sql);
			totalRecords.next();
			int totalRecordResult = totalRecords.getInt(1);
			int numPageNeedShow = 5;
			String html = GenerateCode.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage);
			
			int recordStart = ((currentPage - 1) * numRecordPerPage);
			if(numRecordPerPage > totalRecordResult) {
				recordStart = 0;
				currentPage = 1;
			}
			sql = "SELECT * FROM users LIMIT " + recordStart + ", " + numRecordPerPage;
			
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<User> data = new ArrayList<User>();
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setAddress(rs.getString(4));
				user.setCity(rs.getString(5));
				user.setPhone(rs.getString(6));
				user.setScore(rs.getInt(7));
				user.setRegistryDate(rs.getDate(8));
				user.setEmail(rs.getString(9));
				user.setStatus(rs.getString(10));
				user.setSecureQuestion(rs.getString(11));
				user.setSecureAnswer(rs.getString(12));
				user.setRole(rs.getInt(13));
				data.add(user);
			}
			
			result.put("pagination", html);
			result.put("data", data);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM users WHERE users.username = ?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		User user = null;
		try {
			pre.setString(1, username);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setAddress(rs.getString(4));
				user.setCity(rs.getString(5));
				user.setPhone(rs.getString(6));
				user.setScore(rs.getInt(7));
				user.setRegistryDate(rs.getDate(8));
				user.setEmail(rs.getString(9));
				user.setStatus(rs.getString(10));
				user.setSecureQuestion(rs.getString(11));
				user.setSecureAnswer(rs.getString(12));
				user.setRole(rs.getInt(13));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public String checkLogin(String username, String password, String type) {
		String sql = "{CALL checkLogin(?,?,?,?)}";
		CallableStatement callable = connection.getCallableStatement(sql);
		try {
			callable.setString(1, username);
			callable.setString(2, password);
			callable.setString(3, type);
			callable.registerOutParameter(4, Types.VARCHAR);
			callable.executeUpdate();
			return callable.getString(4);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Lỗi kết nối!!!";
		}
	}

	@Override
	public String activeAccount(String username, String code) {
		String sql = "{CALL activeAccount(?,?,?)}";
		CallableStatement callable = connection.getCallableStatement(sql);
		try {
			callable.setString(1, username);
			callable.setString(2, code);
			callable.registerOutParameter(3, Types.INTEGER);
			callable.executeUpdate();
			return callable.getInt(3) > 0 ? "ok" : "fail";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Lỗi kết nối!!!";
		}
	}

	@Override
	public String remindPassword(String username, String password) {
		String sql = "{CALL remindPassword(?,?,?)}";
		CallableStatement callable = connection.getCallableStatement(sql);
		String result = "";
		try {
			callable.setString(1, username);
			callable.setString(2, password);
			callable.registerOutParameter(3, Types.VARCHAR);
			callable.execute();
			result = callable.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String checkRequestRMPassword(String username) {
		String sql = "{CALL checkRequestRMPassword(?,?)}";
		CallableStatement callable = connection.getCallableStatement(sql);
		String result = "";
		try {
			callable.setString(1, username);
			callable.registerOutParameter(2, Types.VARCHAR);
			callable.execute();
			result = callable.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String requestRMPassword(String username, String code, String secureAnswer) {
		String sql = "{CALL requestRMPassword(?,?,?,?)}";
		CallableStatement callable = connection.getCallableStatement(sql);
		String result = "";
		try {
			callable.setString(1, username);
			callable.setString(2, secureAnswer);
			callable.setString(3, code);
			callable.registerOutParameter(4, Types.VARCHAR);
			callable.execute();
			result = callable.getString(4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
