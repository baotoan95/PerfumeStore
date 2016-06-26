package com.baotoan.dev.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.dev.dao.CityDAO;
import com.baotoan.dev.dao.OrderDAO;
import com.baotoan.dev.dao.PaymentDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.dao.StandardConnection;
import com.baotoan.dev.dao.StatusDAO;
import com.baotoan.dev.entity.Order;
import com.baotoan.dev.entity.OrderDetail;
import com.baotoan.dev.utils.GenerateCode;

public class OrderDAOImpl extends StandardConnection implements OrderDAO {
	public static final int BY_UNAPPROVED = 1;
	public static final int BY_WAIT = 2;
	public static final int COMPLATE = 3;
	public static final int ALL = 4;
	private ProductDAO productDAO = new ProductDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();
	private PaymentDAO payDAO = new PaymentDAOImpl();
	private CityDAO cityDAO = new CityDAOImpl();

	@Override
	public boolean addOrder(Order order) {
		String sql = "INSERT INTO orders(id,name,phone,address,city,email,note,"
				+ "createDate,name_receiver,phone_receiver,address_receiver,"
				+ "city_receiver,sale,payment,totalPay,username,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, order.getId());
			pre.setString(2, order.getName());
			pre.setString(3, order.getPhone());
			pre.setString(4, order.getAddress());
			pre.setInt(5, order.getCity().getId());
			pre.setString(6, order.getEmail());
			pre.setString(7, order.getNote());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pre.setString(8, format.format(order.getCreateDate()));
			pre.setString(9, order.getName_receiver());
			pre.setString(10, order.getPhone_receiver());
			pre.setString(11, order.getAddress_receiver());
			pre.setInt(12, order.getCity_receiver().getId());
			pre.setString(13, order.getSale());
			pre.setInt(14, order.getPayment().getId());
			pre.setInt(15, order.getTotalPay());
			pre.setString(16, order.getUsername());
			pre.setInt(17, order.getStatus().getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delOrderById(String orderId) {
		String sql = "DELETE FROM orders WHERE id='" + orderId + "'";
		try {
			if(delOrderDetailByOrderId(orderId)) {
				return connection.getStatement().executeUpdate(sql) > 0;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delOrderByUsername(String username) {
		String sql = "DELETE FROM orders WHERE username=" + username;
		try {
			if(delOrderDetailByUsername(username)) {
				return connection.getStatement().executeUpdate(sql) > 0;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOrder(Order order) {
		String sql = "UPDATE orders SET name=?,phone=?,address=?,city=?,email=?,note=?,"
				+ "createDate=?,name_receiver=?,phone_receiver=?,address_receiver=?,"
				+ "city_receiver=?,sale=?,payment=?,totalPay=?,username=?,status=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, order.getName());
			pre.setString(2, order.getPhone());
			pre.setString(3, order.getAddress());
			pre.setInt(4, order.getCity().getId());
			pre.setString(5, order.getEmail());
			pre.setString(6, order.getNote());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pre.setString(7, format.format(order.getCreateDate()));
			pre.setString(8, order.getName_receiver());
			pre.setString(9, order.getPhone_receiver());
			pre.setString(10, order.getAddress_receiver());
			pre.setInt(11, order.getCity_receiver().getId());
			pre.setString(12, order.getSale());
			pre.setInt(13, order.getPayment().getId());
			pre.setInt(14, order.getTotalPay());
			pre.setString(15, order.getUsername());
			pre.setInt(16, order.getStatus().getId());
			pre.setString(17, order.getId());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Map<String, Object> getOrders(int currentPage, int numRecordPerPage, int getBy) {
		String by = "";
		switch(getBy) {
			case BY_UNAPPROVED: 
				by = " WHERE status = 1";
				break;
			case BY_WAIT:
				by = " WHERE status = 2";
				break;
			case COMPLATE:
				by = " WHERE status = 3";
				break;
		}
		
		String sql = "SELECT COUNT(*) FROM orders" + by;
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			ResultSet totalRecords = connection.getStatement().executeQuery(sql);
			totalRecords.next();
			int totalRecordResult = totalRecords.getInt(1);
			int numPageNeedShow = 5;
			String html = GenerateCode.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage);
			
			int recordStart = ((currentPage - 1) * numRecordPerPage);
			if(numRecordPerPage > totalRecordResult) {
				recordStart = 0;
				currentPage = 1;
			}
			sql = "SELECT * FROM orders" + by + " LIMIT " + recordStart + ", " + numRecordPerPage;
			ResultSet rs = connection.getStatement().executeQuery(sql);
			
			List<Order> data = new ArrayList<Order>();
			while(rs.next()) {
				data.add(getOrderById(rs.getString(1)));
			}
			result.put("html", html);
			result.put("data", data);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order getOrderById(String orderId) {
		System.out.println(orderId);
		releaseConnection();
		String sql = "SELECT * FROM orders WHERE id='" + orderId + "'";
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			if(rs.next()) {
				Order order = new Order();
				order.setId(rs.getString(1));
				order.setName(rs.getString(2));
				order.setPhone(rs.getString(3));
				order.setAddress(rs.getString(4));
				order.setCity(cityDAO.getCityById(rs.getInt(5)));
				order.setEmail(rs.getString(6));
				order.setNote(rs.getString(7));
				order.setCreateDate(rs.getDate(8));
				order.setName_receiver(rs.getString(9));
				order.setPhone_receiver(rs.getString(10));
				order.setAddress_receiver(rs.getString(11));
				order.setCity_receiver(cityDAO.getCityById(rs.getInt(12)));
				order.setSale(rs.getString(13));
				order.setPayment(payDAO.getPaymentById(rs.getInt(14)));
				order.setTotalPay(rs.getInt(15));
				order.setUsername(rs.getString(16));
				order.setStatus(statusDAO.getStatusById(rs.getInt(17)));
				order.setListOrderDetails(getOrderDetailByOrderId(order.getId()));
				return order;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		String sql = "INSERT INTO order_details(orderId,productId,quantity,pay) VALUES(?,?,?,?)";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, orderDetail.getOrderId());
			pre.setInt(2, orderDetail.getProduct().getId());
			pre.setInt(3, orderDetail.getQuantity());
			pre.setInt(4, orderDetail.getPay());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delOrderDetailById(int id) {
		String sql = "DELETE FROM order_details WHERE id=" + id;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delOrderDetailByUsername(String username) {
		String sql = "DELETE FROM order_details WHERE username=" + username;
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delOrderDetailByOrderId(String id) {
		String sql = "DELETE FROM order_details WHERE orderId='" + id + "'";
		try {
			return connection.getStatement().executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOrderDetail(OrderDetail orderDetail) {
		String sql = "UPDATE order_details SET orderId=?, productId=?, quantity=?, pay=? WHERE id=?";
		PreparedStatement pre = connection.getPreparedStatement(sql);
		try {
			pre.setString(1, orderDetail.getOrderId());
			pre.setInt(2, orderDetail.getProduct().getId());
			pre.setInt(3, orderDetail.getQuantity());
			pre.setInt(4, orderDetail.getPay());
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public OrderDetail getOrderDetailById(int id) {
		String sql = "SELECT * FROM order_details WHERE id=" + id;
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			rs.next();
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(rs.getInt(1));
			orderDetail.setOrderId(rs.getString(2));
			orderDetail.setProduct(productDAO.getProductDetailById(rs.getInt(3)));
			orderDetail.setQuantity(rs.getInt(4));
			orderDetail.setPay(rs.getInt(5));
			return orderDetail;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderDetail> getOrderDetailByOrderId(String orderId) {
		String sql = "SELECT * FROM order_details WHERE orderId='" + orderId + "'";
		try {
			ResultSet rs = connection.getStatement().executeQuery(sql);
			List<OrderDetail> result = new ArrayList<OrderDetail>();
			while(rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(rs.getInt(1));
				orderDetail.setOrderId(rs.getString(2));
				orderDetail.setProduct(productDAO.getProductDetailById(rs.getInt(3)));
				orderDetail.setQuantity(rs.getInt(4));
				orderDetail.setPay(rs.getInt(5));
				result.add(orderDetail);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
