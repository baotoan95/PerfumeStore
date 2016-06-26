package com.baotoan.dev.dao;

import java.util.Map;

import com.baotoan.dev.entity.Promotion;

public interface PromotionDAO {
	public boolean addPromotion(Promotion promotion);
	public boolean delPromotion(int id);
	public boolean updatePromotion(Promotion promotion);
	public Promotion getPromotionById(int id);
	public Map<String, Object> getPromotions(int currentPage, int numRecordPerPage);
}
