package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Intended;

public interface IntendedDAO {
	public boolean addIntended(Intended intended);
	public boolean delIntended(int id);
	public boolean updateIntended(Intended intended);
	public Intended getIntendedById(int id);
	public List<Intended> getAll();
}
