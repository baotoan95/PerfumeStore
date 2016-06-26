package com.baotoan.dev.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductDetail implements Serializable {
	private int id;
	private int productId;
	private String capacity;
	private int price;
	private int market_price;
	private String imageUrl;

	public ProductDetail() {
	}

	public ProductDetail(int id, int productId, String capacity, int price,
			int market_price, String imageUrl) {
		this.id = id;
		this.productId = productId;
		this.capacity = capacity;
		this.price = price;
		this.market_price = market_price;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMarket_price() {
		return market_price;
	}

	public void setMarket_price(int market_price) {
		this.market_price = market_price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", productId=" + productId
				+ ", capacity=" + capacity + ", price=" + price
				+ ", market_price=" + market_price + "]";
	}

}
