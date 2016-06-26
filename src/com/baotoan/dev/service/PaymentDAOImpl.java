package com.baotoan.dev.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.PaymentDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Payment;

public class PaymentDAOImpl extends StandardConnection implements PaymentDAO {

	@Override
	public List<Payment> getAll() {
		String sql = "SELECT * FROM payments";
		List<Payment> result = new ArrayList<Payment>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				result.add(new Payment(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Payment getPaymentById(int id) {
		String sql = "SELECT * FROM payments WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			rs.next();
			return new Payment(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
