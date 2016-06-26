package com.baotoan.dev.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.dev.entity.Order;
import com.baotoan.dev.entity.OrderDetail;

public interface OrderDAO {
	public boolean addOrder(Order order);
	public boolean delOrderById(String orderId);
	public boolean delOrderByUsername(String username);
	public boolean updateOrder(Order order);
	public Map<String, Object> getOrders(int currentPage, int numRecordPerPage, int getBy);
	public Order getOrderById(String orderId);
	
	public boolean addOrderDetail(OrderDetail orderDetail);
	public boolean delOrderDetailById(int id);
	public boolean delOrderDetailByUsername(String username);
	public boolean delOrderDetailByOrderId(String id);
	public boolean updateOrderDetail(OrderDetail orderDetail);
	public OrderDetail getOrderDetailById(int id);
	public List<OrderDetail> getOrderDetailByOrderId(String orderId);
}
