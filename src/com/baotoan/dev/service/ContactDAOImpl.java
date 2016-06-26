package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.ContactDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Contact;
import com.baotoan.dev.utils.Common;
import com.baotoan.dev.utils.GenerateCode;

public class ContactDAOImpl extends StandardConnection implements ContactDAO {

	@Override
	public boolean addContact(Contact contact) {
		String sql = "INSERT INTO contacts(name, email, phone, content, sentDate, status) VALUES(?,?,?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, contact.getName());
			pre.setString(2, contact.getEmail());
			pre.setString(3, contact.getPhone());
			pre.setString(4, contact.getContent());
			pre.setString(5, Common.dateFormat.format(contact.getSentDate()));
			pre.setBoolean(6, contact.isStatus());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delContact(int id) {
		String sql = "DELETE FROM contacts WHERE id=" + id + "";
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Contact getContact(int id) {
		String sql = "SELECT * FROM contacts WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			if(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setName(rs.getString(2));
				contact.setEmail(rs.getString(3));
				contact.setPhone(rs.getString(4));
				contact.setContent(rs.getString(5));
				try {
					contact.setSentDate(Common.dateFormat.parse(rs.getString(6)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				contact.setStatus(rs.getBoolean(7));
				return contact;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> getAll(int currentPage, int numRecordPerPage) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String sql = "SELECT * FROM contacts";
		int totalRecordResult = 0;
		try {
			ResultSet rs = connection.getStatement().executeQuery("SELECT COUNT(*) FROM (" + sql + ") AS BT_TABLE");
			rs.next();
			totalRecordResult = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		int numPageNeedShow = 5;
		String html = GenerateCode.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Contact> data = new ArrayList<Contact>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setName(rs.getString(2));
				contact.setEmail(rs.getString(3));
				contact.setPhone(rs.getString(4));
				contact.setContent(rs.getString(5));
				try {
					contact.setSentDate(Common.dateFormat.parse(rs.getString(6)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				contact.setStatus(rs.getBoolean(7));
				data.add(contact);
			}
			result.put("data", data);
			result.put("pagination", html);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateContact(Contact contact) {
		String sql = "UPDATE contacts SET status=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setBoolean(1, contact.isStatus());
			pre.setInt(2, contact.getId());
			pre.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
