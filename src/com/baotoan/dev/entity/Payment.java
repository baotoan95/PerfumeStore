package com.baotoan.dev.entity;

public class Payment {
	private int id;
	private String name;
	private String descript;

	public Payment() {
	}

	public Payment(int id, String name, String descript) {
		this.id = id;
		this.name = name;
		this.descript = descript;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", name=" + name + ", descript="
				+ descript + "]";
	}

}
