package com.baotoan.dev.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.dev.entity.Category;

public interface CategoryDAO {
	public Category getCategoryById(int id);
	public Map<String, Object> getCategories(int currentPage, int numRecordPerPage);
	public boolean addCategory(Category category);
	public boolean delCategory(int id);
	public boolean updateCategory(Category category);
	public Category getCategory(int brandId, int intendedId);
	public List<Category> getCategoriesByIntendedId(int intendedId);
}
