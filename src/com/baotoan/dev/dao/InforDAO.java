package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Information;

public interface InforDAO {
	public List<Information> getAll();
	public Information getInfoById(int id);
	public Information getInfoBySlug(String slug);
	public boolean updateInfo(Information information);
}
