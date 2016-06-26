package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.City;

public interface CityDAO {
	public List<City> getAll();
	public City getCityById(int id);
}
