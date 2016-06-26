package com.baotoan.dev.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Like implements Serializable {
	private int id;
	private String username;
	private Product product;

	public Like() {
	}

	public Like(int id, String username, Product product) {
		this.id = id;
		this.username = username;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", username=" + username + ", product="
				+ product + "]";
	}

}
