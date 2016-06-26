package com.baotoan.dev.entity;

import java.util.Date;

public class Contact {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String content;
	private Date sentDate;
	private boolean status;

	public Contact() {
	}

	public Contact(int id, String name, String email, String phone,
			String content, Date sentDate, boolean status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.content = content;
		this.sentDate = sentDate;
		this.status = status;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", content=" + content + ", sentDate="
				+ sentDate + ", status=" + status + "]";
	}

}
