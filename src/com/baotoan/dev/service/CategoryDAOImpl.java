package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.BrandDAO;
import com.baotoan.dev.dao.CategoryDAO;
import com.baotoan.dev.dao.IntendedDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Category;
import com.baotoan.dev.utils.GenerateCode;

public class CategoryDAOImpl extends StandardConnection implements CategoryDAO {
	private BrandDAO brandDAO = new BrandDAOImpl();
	private IntendedDAO intendDAO = new IntendedDAOImpl();

	@Override
	public Category getCategoryById(int id) {
		String sql = "SELECT * FROM categories WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while (rs.next()) {
				Category cate = new Category();
				cate.setId(rs.getInt(1));
				cate.setBrand(brandDAO.getBrandById(rs.getInt(2)));
				cate.setIntended(intendDAO.getIntendedById(rs.getInt(3)));
				return cate;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getCategories(int currentPage, int numRecordPerPage) {
		String sql = "SELECT COUNT(*) FROM categories";
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			ResultSet totalRecords = connection.getStatement()
					.executeQuery(sql);
			totalRecords.next();
			int totalRecordResult = totalRecords.getInt(1);
			int numPageNeedShow = 5;
			String html = GenerateCode.generateHTML(totalRecordResult,
					numPageNeedShow, numRecordPerPage, currentPage);

			int recordStart = ((currentPage - 1) * numRecordPerPage);
			if (numRecordPerPage > totalRecordResult) {
				recordStart = 0;
				currentPage = 1;
			}
			sql = "SELECT * FROM categories LIMIT " + recordStart + ", "
					+ numRecordPerPage;

			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<Category> data = new ArrayList<Category>();
			while (rs.next()) {
				Category cate = new Category();
				cate.setId(rs.getInt(1));
				cate.setBrand(brandDAO.getBrandById(rs.getInt(2)));
				cate.setIntended(intendDAO.getIntendedById(rs.getInt(3)));
				data.add(cate);
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
	public boolean addCategory(Category category) {
		String sql = "INSERT INTO categories(brand,intended) values(?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setInt(1, category.getBrand().getId());
			pre.setInt(2, category.getIntended().getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delCategory(int id) {
		String sql = "DELETE FROM categories WHERE id=" + id;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCategory(Category category) {
		String sql = "UPDATE categories SET brand=?,intended=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setInt(1, category.getBrand().getId());
			pre.setInt(2, category.getIntended().getId());
			pre.setInt(3, category.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category getCategory(int brandId, int intendedId) {
		String sql = "SELECT * FROM categories WHERE brand=? and intended=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setInt(1, brandId);
			pre.setInt(2, intendedId);
			ResultSet rs = pre.executeQuery();
			if(rs.next() == true) {
				Category cate = new Category();
				cate.setId(rs.getInt(1));
				cate.setBrand(brandDAO.getBrandById(rs.getInt(2)));
				cate.setIntended(intendDAO.getIntendedById(rs.getInt(3)));
				return cate;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getCategoriesByIntendedId(int intendedId) {
		String sql = "SELECT * FROM categories WHERE intended=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		List<Category> data = new ArrayList<>();
		try {
			pre.setInt(1, intendedId);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				Category cate = new Category();
				cate.setId(rs.getInt(1));
				cate.setBrand(brandDAO.getBrandById(rs.getInt(2)));
				cate.setIntended(intendDAO.getIntendedById(rs.getInt(3)));
				data.add(cate);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
