package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.PromotionDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Promotion;
import com.baotoan.dev.utils.GenerateCode;

public class PromotionDAOImpl extends StandardConnection implements PromotionDAO {

	@Override
	public boolean addPromotion(Promotion promotion) {
		String sql = "INSERT INTO promotions(title,content,descript,imageUrl,startDate,endDate) values(?,?,?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, promotion.getTitle());
			pre.setString(2, promotion.getContent());
			pre.setString(3, promotion.getDescript());
			pre.setString(4, promotion.getImageUrl());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pre.setString(5, format.format(promotion.getStartDate()));
			pre.setString(6, format.format(promotion.getEndDate()));
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delPromotion(int id) {
		String sql = "DELETE FROM promotions WHERE id=" + id;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePromotion(Promotion promotion) {
		String sql = "UPDATE promotions SET title=?,content=?,descript=?,imageUrl=?,startDate=?,endDate=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, promotion.getTitle());
			pre.setString(2, promotion.getContent());
			pre.setString(3, promotion.getDescript());
			pre.setString(4, promotion.getImageUrl());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pre.setString(5, format.format(promotion.getStartDate()));
			pre.setString(6, format.format(promotion.getEndDate()));
			pre.setInt(7, promotion.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Promotion getPromotionById(int id) {
		String sql = "SELECT * FROM promotions WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Promotion promotion = new Promotion();
				promotion.setId(rs.getInt(1));
				promotion.setTitle(rs.getString(2));
				promotion.setContent(rs.getString(3));
				promotion.setDescript(rs.getString(4));
				promotion.setImageUrl(rs.getString(5));
				promotion.setStartDate(rs.getDate(6));
				promotion.setEndDate(rs.getDate(7));
				return promotion;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getPromotions(int currentPage, int numRecordPerPage) {
		String sql = "SELECT COUNT(*) FROM promotions";
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
			sql = "SELECT * FROM promotions LIMIT " + recordStart + ", " + numRecordPerPage;
			
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<Promotion> data = new ArrayList<Promotion>();
			while(rs.next()) {
				Promotion promotion = new Promotion();
				promotion.setId(rs.getInt(1));
				promotion.setTitle(rs.getString(2));
				promotion.setContent(rs.getString(3));
				promotion.setDescript(rs.getString(4));
				promotion.setImageUrl(rs.getString(5));
				promotion.setStartDate(rs.getDate(6));
				promotion.setEndDate(rs.getDate(7));
				data.add(promotion);
			}
			result.put("pagination", html);
			result.put("data", data);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
