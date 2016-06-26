package com.baotoan.dev.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.dev.entity.Post;

public interface PostDAO {
	public boolean addPost(Post post);
	public boolean delPost(int id);
	public boolean updatePost(Post post);
	public Post getPostById(int id);
	public List<Post> getPostsUnapproved();
	public Map<String, Object> getPosts(int currentPage, int numOfRecords);
}
