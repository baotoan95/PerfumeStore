package com.baotoan.dev.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.CityDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.City;

public class CityDAOImpl extends StandardConnection implements CityDAO {

	@Override
	public List<City> getAll() {
		String sql = "SELECT * FROM city";
		List<City> result = new ArrayList<City>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				City c = new City();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				result.add(c);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public City getCityById(int id) {
		String sql = "SELECT * FROM city WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			rs.next();
			return new City(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
