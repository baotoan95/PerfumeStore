package com.baotoan.dev.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Post implements Serializable {
	private int id;
	private String title;
	private String content;
	private Date publishDate;
	private boolean status;

	public Post() {
	}

	public Post(int id, String title, String content, Date publishDate,
			boolean status) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.publishDate = publishDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content
				+ ", publishDate=" + publishDate + ", status=" + status + "]";
	}

}
