package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.IntendedDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Intended;

public class IntendedDAOImpl extends StandardConnection implements IntendedDAO {

	@Override
	public boolean addIntended(Intended intended) {
		String sql = "INSERT INTO intended(name) values(?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, intended.getName());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delIntended(int id) {
		String sql = "DELETE intended WHERE id=" + id;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateIntended(Intended intended) {
		String sql = "UPDATE intended SET name=" + intended.getName() + " WHERE id=" + intended.getId();
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Intended getIntendedById(int id) {
		String sql = "SELECT * FROM intended WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Intended intended = new Intended();
				intended.setId(rs.getInt(1));
				intended.setName(rs.getString(2));
				return intended;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Intended> getAll() {
		String sql = "SELECT * FROM intended";
		List<Intended> result = new ArrayList<Intended>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Intended intended = new Intended();
				intended.setId(rs.getInt(1));
				intended.setName(rs.getString(2));
				result.add(intended);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
