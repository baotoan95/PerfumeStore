package com.baotoan.dev.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable {
	private int id;
	private String name;
	private String avatar;
	private int views;
	private Category category;
	private int sellCount;
	private String descript;
	private Promotion promotion;
	private Date updateDay;
	private boolean gift;
	private List<ProductDetail> listDetail = new ArrayList<ProductDetail>();
	
	private int price;
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		if(price == 0) {
			return listDetail.get(0).getPrice();
		}
		return price;
	}

	public Product() {
	}

	public Product(int id, String name, String avatar, int views,
			Category category, int sellCount, String descript, Promotion promotion,
			Date updateDay, boolean gift) {
		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.views = views;
		this.category = category;
		this.sellCount = sellCount;
		this.descript = descript;
		this.promotion = promotion;
		this.updateDay = updateDay;
		this.gift = gift;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Date getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
	}

	public boolean isGift() {
		return gift;
	}

	public void setGift(boolean gift) {
		this.gift = gift;
	}

	public List<ProductDetail> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<ProductDetail> listDetail) {
		this.listDetail = listDetail;
	}

	public void addProductDetail(ProductDetail proDetail) {
		this.listDetail.add(proDetail);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", avatar=" + avatar
				+ ", views=" + views + ", category=" + category
				+ ", sellCount=" + sellCount + ", descript=" + descript
				+ ", promotion=" + promotion + ", updateDay=" + updateDay
				+ ", gift=" + gift + ", listDetail=" + listDetail + "]";
	}

}
