
package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.OrderDAO;
import com.baotoan.dev.dao.PaymentDAO;
import com.baotoan.dev.dao.StatusDAO;
import com.baotoan.dev.entity.Order;
import com.baotoan.dev.service.OrderDAOImpl;
import com.baotoan.dev.service.PaymentDAOImpl;
import com.baotoan.dev.service.StatusDAOImpl;

/**
 * Servlet implementation class OrderAdminControl
 */
public class OrderAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAOImpl();
	private PaymentDAO paymentDAO = new PaymentDAOImpl();
	private StatusDAO sttDAO = new StatusDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		if(action.equalsIgnoreCase("all")) {
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = orderDAO.getOrders(page, 10, OrderDAOImpl.ALL);
			String pagination = ((String)result.get("html")).replaceAll("page", "orders?ac=all&page");
			List<Order> listOrders = (List<Order>) result.get("data");
			request.setAttribute("pagination", pagination);
			request.setAttribute("listOrders", listOrders);
			request.getRequestDispatcher("/WEB-INF/admin/pages/orders.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("unap")) {
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = orderDAO.getOrders(page, 10, OrderDAOImpl.BY_UNAPPROVED);
			String pagination = ((String)result.get("html")).replaceAll("page", "orders?ac=all&page");
			List<Order> listOrders = (List<Order>) result.get("data");
			request.setAttribute("pagination", pagination);
			request.setAttribute("listOrders", listOrders);
			request.getRequestDispatcher("/WEB-INF/admin/pages/orders.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("edit")) {
			String orderId = request.getParameter("id");
			Order order = orderDAO.getOrderById(orderId);
			request.setAttribute("payments", paymentDAO.getAll());
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-order.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		String id = request.getParameter("id");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		if("update".equalsIgnoreCase(action)) {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			int payment = Integer.parseInt(request.getParameter("payment"));
			int status = Integer.parseInt(request.getParameter("status"));
			Order order = orderDAO.getOrderById(id);
			order.setName(name);
			order.setAddress(address);
			order.setPhone(phone);
			order.setPayment(paymentDAO.getPaymentById(payment));
			order.setStatus(sttDAO.getStatusById(status));
			if(orderDAO.updateOrder(order)) {
				writer.print("Cập nhật thành công");
			} else {
				writer.print("Cập nhật chưa thành công");
			}
		} else {
			if(orderDAO.delOrderById(id)) {
				writer.print("ok");
			} else {
				writer.print("fail");
			}
		}
	}

}
