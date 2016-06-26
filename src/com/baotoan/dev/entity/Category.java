package com.baotoan.dev.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private int id;
	private Brand brand;
	private Intended intended;

	public Category() {
	}

	public Category(int id, Brand brand, Intended intended) {
		this.id = id;
		this.brand = brand;
		this.intended = intended;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Intended getIntended() {
		return intended;
	}

	public void setIntended(Intended intended) {
		this.intended = intended;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", brand=" + brand + ", intended="
				+ intended + "]";
	}

}
