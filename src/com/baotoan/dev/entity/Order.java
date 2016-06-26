package com.baotoan.dev.entity;

import java.util.Date;
import java.util.List;

public class Order {
	private String id;
	private String name;
	private String phone;
	private String address;
	private City city;
	private String email;
	private String note;
	private Date createDate;
	private String name_receiver;
	private String phone_receiver;
	private String address_receiver;
	private City city_receiver;
	private String sale;
	private Payment payment;
	private int totalPay;
	private String username;
	private Status status;
	private List<OrderDetail> listOrderDetails;

	public Order() {
	}

	public Order(String id, String name, String phone, String address,
			City city, String email, String note, Date createDate,
			String name_receiver, String phone_receiver,
			String address_receiver, City city_receiver, String sale,
			Payment payment, int totalPay, String username, Status status) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.email = email;
		this.note = note;
		this.createDate = createDate;
		this.name_receiver = name_receiver;
		this.phone_receiver = phone_receiver;
		this.address_receiver = address_receiver;
		this.city_receiver = city_receiver;
		this.sale = sale;
		this.payment = payment;
		this.totalPay = totalPay;
		this.username = username;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getName_receiver() {
		return name_receiver;
	}

	public void setName_receiver(String name_receiver) {
		this.name_receiver = name_receiver;
	}

	public String getPhone_receiver() {
		return phone_receiver;
	}

	public void setPhone_receiver(String phone_receiver) {
		this.phone_receiver = phone_receiver;
	}

	public String getAddress_receiver() {
		return address_receiver;
	}

	public void setAddress_receiver(String address_receiver) {
		this.address_receiver = address_receiver;
	}

	public City getCity_receiver() {
		return city_receiver;
	}

	public void setCity_receiver(City city_receiver) {
		this.city_receiver = city_receiver;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<OrderDetail> getListOrderDetails() {
		return listOrderDetails;
	}

	public void setListOrderDetails(List<OrderDetail> listOrderDetails) {
		this.listOrderDetails = listOrderDetails;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", city=" + city + ", email="
				+ email + ", note=" + note + ", createDate=" + createDate
				+ ", name_receiver=" + name_receiver + ", phone_receiver="
				+ phone_receiver + ", address_receiver=" + address_receiver
				+ ", city_receiver=" + city_receiver + ", sale=" + sale
				+ ", payment=" + payment + ", totalPay=" + totalPay
				+ ", username=" + username + ", status=" + status
				+ ", listOrderDetails=" + listOrderDetails + "]";
	}

}
