package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.PostDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.entity.Post;
import com.baotoan.dev.utils.GenerateCode;

public class PostDAOImpl extends StandardConnection implements PostDAO {

	@Override
	public boolean addPost(Post post) {
		String sql = "INSERT INTO posts(title,content,updateDate,status) values(?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, post.getTitle());
			pre.setString(2, post.getContent());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pre.setString(3, format.format(post.getPublishDate()));
			pre.setBoolean(4, post.isStatus());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delPost(int id) {
		String sql = "DELETE FROM posts WHERE id=" + id;
		Statement st = connection.getStatement();
		try {
			return st.executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePost(Post post) {
		String sql = "UPDATE posts set title=?,content=?,updateDate=?,status=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, post.getTitle());
			pre.setString(2, post.getContent());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pre.setString(3, format.format(post.getPublishDate()));
			pre.setBoolean(4, post.isStatus());
			pre.setInt(5, post.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Post getPostById(int id) {
		String sql = "SELECT * FROM posts WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setContent(rs.getString(3));
				post.setPublishDate(rs.getDate(4));
				post.setStatus(rs.getBoolean(5));
				return post;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getPosts(int currentPage, int numRecordPerPage) {
		String sql = "SELECT COUNT(*) FROM posts";
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
			sql = "SELECT * FROM posts LIMIT " + recordStart + ", " + numRecordPerPage;
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<Post> data = new ArrayList<Post>();
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setContent(rs.getString(3));
				post.setPublishDate(rs.getDate(4));
				post.setStatus(rs.getBoolean(5));
				data.add(post);
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
	public List<Post> getPostsUnapproved() {
		String sql = "SELECT * FROM posts WHERE status=0";
		
		try {
			List<Post> result = new ArrayList<Post>();
			ResultSet rs = connection.getStatement().executeQuery(sql);
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setContent(rs.getString(3));
				post.setPublishDate(rs.getDate(4));
				post.setStatus(rs.getBoolean(5));
				result.add(post);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
