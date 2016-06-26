package com.baotoan.dev.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.dao.StatusDAO;
import com.baotoan.dev.entity.Status;

public class StatusDAOImpl extends StandardConnection implements StatusDAO {

	@Override
	public List<Status> getAll() {
		String sql = "SELECT * FROM status_order";
		List<Status> result = new ArrayList<Status>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				result.add(new Status(rs.getInt(1), rs.getString(2)));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Status getStatusById(int id) {
		String sql = "SELECT * FROM status_order WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			rs.next();
			return new Status(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
