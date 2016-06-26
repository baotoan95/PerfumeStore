package com.baotoan.dev.entity;

import java.util.Date;

public class User {
	private String username;
	private String password;
	private String name;
	private String address;
	private String city;
	private String phone;
	private int score;
	private Date registryDate;
	private String email;
	private String status;
	private String secureQuestion;
	private String secureAnswer;
	private int role;
	private boolean isFBAcc;

	public User() {
	}

	public User(String username, String password, String name, String address,
			String city, String phone, int score, Date registryDate,
			String email, String status, String secureQuestion,
			String secureAnswer, int role, boolean isFBAcc) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.score = score;
		this.registryDate = registryDate;
		this.email = email;
		this.status = status;
		this.secureQuestion = secureQuestion;
		this.secureAnswer = secureAnswer;
		this.role = role;
		this.isFBAcc = isFBAcc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecureQuestion() {
		return secureQuestion;
	}

	public void setSecureQuestion(String secureQuestion) {
		this.secureQuestion = secureQuestion;
	}

	public String getSecureAnswer() {
		return secureAnswer;
	}

	public void setSecureAnswer(String secureAnswer) {
		this.secureAnswer = secureAnswer;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isFBAcc() {
		return isFBAcc;
	}

	public void setFBAcc(boolean isFBAcc) {
		this.isFBAcc = isFBAcc;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", name=" + name + ", address=" + address + ", city=" + city
				+ ", phone=" + phone + ", score=" + score + ", registryDate="
				+ registryDate + ", email=" + email + ", status=" + status
				+ ", secureQuestion=" + secureQuestion + ", secureAnswer="
				+ secureAnswer + ", role=" + role + ", isFBAcc=" + isFBAcc
				+ "]";
	}

}
