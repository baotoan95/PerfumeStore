package com.baotoan.dev.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.dao.StatisticDAO;

public class StatisticDAOImpl extends StandardConnection implements StatisticDAO {

	@Override
	public int update(HttpSession session) {
		String sql = "SELECT count FROM statistic";
		try {
			ResultSet rss = connection.getStatement().executeQuery(sql);
			rss.next();
			int quantity = rss.getInt(1);
			sql = "UPDATE statistic set count=" + (quantity + 1);
			connection.getStatement().executeUpdate(sql);
			return quantity + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
