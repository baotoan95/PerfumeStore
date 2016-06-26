package com.baotoan.dev.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.RoleDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Role;

public class RoleDAOImpl extends StandardConnection implements RoleDAO {

	@Override
	public List<Role> getAll() {
		String sql = "SELECT * FROM roles";
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<Role> result = new ArrayList<Role>();
			while(rs.next()) {
				result.add(new Role(rs.getInt(1), rs.getString(2)));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Role getRoleById(int id) {
		String sql = "SELECT * FROM roles WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			rs.next();
			return new Role(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
