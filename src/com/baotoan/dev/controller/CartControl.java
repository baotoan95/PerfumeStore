package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.entity.Cart;
import com.baotoan.dev.service.ProductDAOImpl;

/**
 * Servlet implementation class CartController
 */
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDAO = new ProductDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("ac");
		int productId = Integer.parseInt(request.getParameter("productID"));
		
		HttpSession session = request.getSession();
		if(action.equalsIgnoreCase("add")) {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Map<Integer, Cart> carts = (HashMap<Integer, Cart>)session.getAttribute("carts");
			Cart cart = new Cart();
			cart.setProduct(proDAO.getProductDetailById(productId));
			cart.setQuantity(quantity);
			cart.setProductName(request.getParameter("productName"));
			int status = 0; // 0: đã tồn tại, n: ok
			if(null == carts) {
				carts = new HashMap<Integer, Cart>();
				carts.put(productId, cart);
				session.setAttribute("carts", carts);
				status = carts.size();
			} else {
				if(!carts.containsKey(productId)) {
					carts.put(productId, cart);
					status = carts.size();
				}
			}
			
			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);
			
		} else if(action.equalsIgnoreCase("update")) {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Map<Integer, Cart> carts = (HashMap<Integer, Cart>)session.getAttribute("carts");
			JSONObject result = new JSONObject();
			
			if(quantity == 0) {
				carts.remove(productId);
				result.put("func", "remove");
			} else {
				Cart cart = carts.get(productId);
				cart.setQuantity(quantity);
				carts.put(productId, cart);
				result.put("pay", cart.getMonetized());
				result.put("func", "update");
			}
			
			result.put("status", carts.size()); // 0: giỏ hàng rỗng, -1: xóa ok, n: update
			result.put("totalPay", getTotalPay(carts));
			response.getWriter().print(result);
		} else if(action.equalsIgnoreCase("del")) {
			Map<Integer, Cart> carts = (HashMap<Integer, Cart>)session.getAttribute("carts");
			int status = 0;
			if(carts.remove(productId) != null) {
				status = carts.size();
			}
			JSONObject result = new JSONObject();
			result.put("status", status);
			
			result.put("totalPay", getTotalPay(carts));
			response.getWriter().print(result);
		}
	}
	
	private int getTotalPay(Map<Integer, Cart> carts) {
		Iterator<Integer> iter = carts.keySet().iterator();
		int totalPay = 0;
		for(int i = 0; i < carts.size(); i++) {
			totalPay += carts.get(iter.next()).getMonetized();
		}
		return totalPay;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
