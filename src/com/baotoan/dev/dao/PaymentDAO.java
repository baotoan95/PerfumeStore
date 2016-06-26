package com.baotoan.dev.dao;

import java.util.List;

import com.baotoan.dev.entity.Payment;

public interface PaymentDAO {
	public List<Payment> getAll();
	public Payment getPaymentById(int id);
}
