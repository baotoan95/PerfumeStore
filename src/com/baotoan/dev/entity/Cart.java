package com.baotoan.dev.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cart implements Serializable {
	private String productName;
	private ProductDetail product;
	private int quantity;
	private int monetized;

	public Cart() {
	}

	public Cart(String productName, ProductDetail product, int quantity) {
		this.productName = productName;
		this.product = product;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductDetail getProduct() {
		return product;
	}

	public void setProduct(ProductDetail product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMonetized() {
		return product.getPrice() * quantity;
	}

	public void setMonetized(int monetized) {
		this.monetized = monetized;
	}

	@Override
	public String toString() {
		return "Cart [productName=" + productName + ", product="
				+ product + ", quantity=" + quantity + ", monetized="
				+ monetized + "]";
	}

}
