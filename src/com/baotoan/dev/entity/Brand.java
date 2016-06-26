package com.baotoan.dev.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Brand implements Serializable {
	private int id;
	private String name;
	private String description;
	private String made_in;
	private String poster;
	private String avatarUrl;

	public Brand() {
	}

	public Brand(int id, String name, Post post, String description,
			String made_in, String poster, String avatarUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.made_in = made_in;
		this.poster = poster;
		this.avatarUrl = avatarUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMade_in() {
		return made_in;
	}

	public void setMade_in(String made_in) {
		this.made_in = made_in;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", description="
				+ description + ", made_in=" + made_in + ", poster=" + poster
				+ ", avatarUrl=" + avatarUrl + "]";
	}

}
