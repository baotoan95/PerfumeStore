package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Brand;

public interface BrandDAO {
	public boolean addBrand(Brand brand);
	public boolean delBrand(int id);
	public boolean updateBrand(Brand brand);
	public Brand getBrandById(int id);
	public List<Brand> getAll();
}
