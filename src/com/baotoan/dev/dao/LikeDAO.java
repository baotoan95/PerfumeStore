package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Like;

public interface LikeDAO {
	public boolean addLike(Like like);
	public boolean delLike(String username, int productId);
	public int countLikes(String username);
	public List<Like> getLikeByUsername(String username);
}
