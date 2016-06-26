package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.baotoan.dev.dao.LikeDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Like;

public class LikeDAOImpl extends StandardConnection implements LikeDAO {
	private ProductDAO proDAO = new ProductDAOImpl();

	@Override
	public boolean addLike(Like like) {
		try {
			String check1 = "SELECT * FROM likes WHERE username=? AND productId=?";
			PreparedStatement pre1 = connection.getPreparedStatement(check1);
			pre1.setString(1, like.getUsername());
			pre1.setInt(2, like.getProduct().getId());
			ResultSet rs = pre1.executeQuery();
			rs.next();
			if(rs.getInt(1) > 0) {
				return false;
			}
		} catch (SQLException e) {}
			
		try {
			String sql = "INSERT INTO likes(username,productId) values(?,?)";
			PreparedStatement pre = connection.getPreparedStatement(sql);
			pre.setString(1, like.getUsername());
			pre.setInt(2, like.getProduct().getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delLike(String username, int productId) {
		String sql = "DELETE FROM likes WHERE username='" + username + "' AND productId=" + productId;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Like> getLikeByUsername(String username) {
		String sql = "SELECT * FROM likes WHERE username='" + username + "'";
		List<Like> result = new ArrayList<Like>();
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Like like = new Like();
				like.setId(rs.getInt(1));
				like.setUsername(rs.getString(2));
				like.setProduct(proDAO.getProductById(rs.getInt(3)));
				result.add(like);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countLikes(String username) {
		String sql = "SELECT COUNT(*) FROM likes WHERE username='" + username + "'";
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
