package com.baotoan.dev.entity;

public class Information {
	private int id;
	private String name;
	private String content;
	private boolean status;
	private String slug;

	public Information() {
	}

	public Information(int id, String name, String content, boolean status,
			String slug) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.status = status;
		this.slug = slug;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	@Override
	public String toString() {
		return "Information [id=" + id + ", name=" + name + ", content="
				+ content + ", status=" + status + ", slug=" + slug + "]";
	}

}
