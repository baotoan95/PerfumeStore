package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baotoan.dev.dao.CityDAO;
import com.baotoan.dev.dao.OrderDAO;
import com.baotoan.dev.dao.PaymentDAO;
import com.baotoan.dev.dao.StatusDAO;
import com.baotoan.dev.entity.Cart;
import com.baotoan.dev.entity.Order;
import com.baotoan.dev.entity.OrderDetail;
import com.baotoan.dev.entity.User;
import com.baotoan.dev.service.CityDAOImpl;
import com.baotoan.dev.service.OrderDAOImpl;
import com.baotoan.dev.service.PaymentDAOImpl;
import com.baotoan.dev.service.StatusDAOImpl;
import com.baotoan.dev.utils.GenerateCode;
import com.baotoan.dev.utils.MailSenderUtil;

/**
 * Servlet implementation class OrderControl
 */
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAOImpl();
	private CityDAO cityDAO = new CityDAOImpl();
	private PaymentDAO payDAO = new PaymentDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("ac");
		HttpSession session = request.getSession();
		
		if("order".equalsIgnoreCase(action)) {
			request.setAttribute("listCity", cityDAO.getAll());
			request.setAttribute("payments", payDAO.getAll());
			request.getRequestDispatcher("/WEB-INF/pages/order.jsp").forward(request, response);
		} else if("confirm".equalsIgnoreCase(action)) {
			Order order = (Order)session.getAttribute("order");
			if(null != session.getAttribute("user")) {
				order.setUsername(((User)session.getAttribute("user")).getUsername());
			}
			while(true) {
				if(orderDAO.addOrder(order)) {
					order.setTotalPay(addOrderDetails(session, order));
					orderDAO.updateOrder(order);
					String message = "Đơn hàng của bạn đã được gửi, chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất, cảm ơn!";
					MailSenderUtil.mailSender(new String[] {order.getEmail()}, "Đơn hàng", message);
					break;
				} else {
					order.setId(GenerateCode.generate(10));
				}
			}
			session.removeAttribute("order");
			session.removeAttribute("carts");
			request.setAttribute("notice", "Gửi đơn hàng thành công, vui lòng kiểm tra email của bạn!");
			request.getRequestDispatcher("notice.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String action = request.getParameter("ac");
		
		if("order".equalsIgnoreCase(action)) {
			String name = request.getParameter("fullName");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			int city = Integer.parseInt(request.getParameter("city"));
			String email = request.getParameter("email");
			int payment = Integer.parseInt(request.getParameter("payment"));
			String sale = request.getParameter("sale");
			String note = request.getParameter("note");
			String name_receiver = request.getParameter("name_receiver");
			String phone_receiver = request.getParameter("phone_receiver");
			String address_receiver = request.getParameter("address_receiver");
			int city_receiver = Integer.parseInt(request.getParameter("city_receiver"));
			
			Order order = new Order();
			order.setId(GenerateCode.generate(10));
			order.setName(name);
			order.setPhone(phone);
			order.setAddress(address);
			order.setCity(cityDAO.getCityById(city));
			order.setEmail(email);
			order.setNote(note);
			order.setCreateDate(new Date());
			if(name_receiver == null || name_receiver.trim().equalsIgnoreCase("")) {
				name_receiver = name;
			}
			if(phone_receiver == null || phone_receiver.trim().equalsIgnoreCase("")) {
				phone_receiver = phone;
			}
			if(city_receiver == 0) {
				city_receiver = city;
			}
			if(address_receiver == null || address_receiver.trim().equalsIgnoreCase("")) {
				address_receiver = address;
			}
			
			order.setName_receiver(name_receiver);
			order.setPhone_receiver(phone_receiver);
			order.setAddress_receiver(address_receiver);
			order.setCity_receiver(cityDAO.getCityById(city_receiver));
			order.setSale(sale);
			order.setPayment(payDAO.getPaymentById(payment));
			order.setStatus(statusDAO.getStatusById(1));
			
			session.setAttribute("order", order);
			response.sendRedirect("confirm.html");
		}
	}
	
	@SuppressWarnings("unchecked")
	private int addOrderDetails(HttpSession session, Order order) {
		int totalPay = 0;
		Map<Integer, Cart> carts = (HashMap<Integer, Cart>)session.getAttribute("carts");
		Iterator<Integer> keys = carts.keySet().iterator();
		List<OrderDetail> listOrderDetails = new ArrayList<OrderDetail>();
		for(int i = 0; i < carts.size(); i++) {
			Cart cart = (Cart)carts.get(keys.next());
			OrderDetail orderDetail = new OrderDetail(0, order.getId(), cart.getProduct(), cart.getQuantity(), cart.getProduct().getPrice() * cart.getQuantity()); 
			listOrderDetails.add(orderDetail);
			orderDAO.addOrderDetail(orderDetail);
			totalPay += orderDetail.getPay();
		}
		return totalPay;
	}

}
