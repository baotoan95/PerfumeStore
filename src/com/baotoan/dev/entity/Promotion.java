package com.baotoan.dev.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Promotion implements Serializable {
	private int id;
	private String title;
	private String content;
	private String descript;
	private String imageUrl;
	private Date startDate;
	private Date endDate;

	public Promotion() {
	}

	public Promotion(int id, String title, String content, String descript,
			String imageUrl, Date startDate, Date endDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.descript = descript;
		this.imageUrl = imageUrl;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", title=" + title + ", content="
				+ content + ", descript=" + descript + ", imageUrl=" + imageUrl
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
