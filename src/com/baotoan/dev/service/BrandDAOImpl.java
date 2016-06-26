package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.BrandDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Brand;

public class BrandDAOImpl extends StandardConnection implements BrandDAO {
	@Override
	public boolean addBrand(Brand brand) {
		String sql = "INSERT INTO brands(name,description,made_in,poster,avatarUrl) values(?,?,?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, brand.getName());
			pre.setString(2, brand.getDescription());
			pre.setString(3, brand.getMade_in());
			pre.setString(4, brand.getPoster());
			pre.setString(5, brand.getAvatarUrl());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delBrand(int id) {
		String sql = "DELETE * FROM brands WHERE id=" + id;
		Statement st = connection.getStatement();
		try {
			return st.executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBrand(Brand brand) {
		String sql = "UPDATE brands SET name=?,description=?,made_in=?,poster=?,avatarUrl=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, brand.getName());
			pre.setString(2, brand.getDescription());
			pre.setString(3, brand.getMade_in());
			pre.setString(4, brand.getPoster());
			pre.setString(5, brand.getAvatarUrl());
			pre.setInt(6, brand.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Brand getBrandById(int id) {
		String sql = "SELECT * FROM brands WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Brand brand = new Brand();
				brand.setId(rs.getInt(1));
				brand.setName(rs.getString(2));
				brand.setDescription(rs.getString(3));
				brand.setMade_in(rs.getString(4));
				brand.setPoster(rs.getString(5));
				brand.setAvatarUrl(rs.getString(6));
				return brand;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Brand> getAll() {
		String sql = "SELECT * FROM brands";
		List<Brand> result = new ArrayList<Brand>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Brand brand = new Brand();
				brand.setId(rs.getInt(1));
				brand.setName(rs.getString(2));
				brand.setDescription(rs.getString(3));
				brand.setMade_in(rs.getString(4));
				brand.setPoster(rs.getString(5));
				brand.setAvatarUrl(rs.getString(6));
				result.add(brand);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
