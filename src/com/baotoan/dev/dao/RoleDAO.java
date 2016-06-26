package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Role;

public interface RoleDAO {
	public List<Role> getAll();
	public Role getRoleById(int id);
}
