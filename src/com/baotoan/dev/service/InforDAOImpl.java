package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.InforDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Information;

public class InforDAOImpl extends StandardConnection implements InforDAO {

	@Override
	public List<Information> getAll() {
		String sql = "SELECT * FROM information";
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<Information> result = new ArrayList<Information>();
			while(rs.next()) {
				result.add(new Information(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5)));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Information getInfoById(int id) {
		String sql = "SELECT * FROM information WHERE id=?";
		try {
			PreparedStatement pre = connection.getPreparedStatement(sql);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			Information info = null;
			while(rs.next()) {
				info = new Information(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5));
				break;
			}
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateInfo(Information information) {
		String sql = "UPDATE information SET name=?, content=?, status=? WHERE id=?";
		try {
			PreparedStatement pre = connection.getPreparedStatement(sql);
			pre.setString(1, information.getName());
			pre.setString(2, information.getContent());
			pre.setBoolean(3, information.getStatus());
			pre.setInt(4, information.getId());
			int check = pre.executeUpdate();
			return check > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Information getInfoBySlug(String slug) {
		String sql = "SELECT * FROM information WHERE slug=?";
		try {
			PreparedStatement pre = connection.getPreparedStatement(sql);
			pre.setString(1, slug);
			ResultSet rs = pre.executeQuery();
			Information info = null;
			while(rs.next()) {
				info = new Information(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5));
				break;
			}
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
