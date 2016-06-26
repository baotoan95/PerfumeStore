package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Status;

public interface StatusDAO {
	public List<Status> getAll();
	public Status getStatusById(int id);
}
