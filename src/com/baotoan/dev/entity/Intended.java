package com.baotoan.dev.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Intended implements Serializable {
	private int id;
	private String name;

	public Intended() {
	}

	public Intended(int id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Intended [id=" + id + ", name=" + name + "]";
	}

}
