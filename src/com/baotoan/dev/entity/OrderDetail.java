package com.baotoan.dev.entity;

public class OrderDetail {
	private int id;
	private String orderId;
	private ProductDetail product;
	private int quantity;
	private int pay;

	public OrderDetail() {
	}

	public OrderDetail(int id, String orderId, ProductDetail product,
			int quantity, int pay) {
		this.id = id;
		this.orderId = orderId;
		this.product = product;
		this.quantity = quantity;
		this.pay = pay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orderId=" + orderId + ", product="
				+ product + ", quantity=" + quantity + ", pay=" + pay + "]";
	}

}
