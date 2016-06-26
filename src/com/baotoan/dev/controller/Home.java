package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.dao.UserDAO;
import com.baotoan.dev.entity.Product;
import com.baotoan.dev.service.ProductDAOImpl;
import com.baotoan.dev.service.UserDAOImpl;
import com.baotoan.dev.utils.ProductConstantValues;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home.html")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDAO = new ProductDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> latest = proDAO.getProducts(ProductConstantValues.BY_LATEST, "", 1, 8);
		Map<String, Object> bestSell = proDAO.getProducts(ProductConstantValues.BY_BESTSELL, "", 1, 8);
		Map<String, Object> gift = proDAO.getProducts(ProductConstantValues.BY_GIFT, "", 1, 8);
		Map<String, Object> views = proDAO.getProducts(ProductConstantValues.BY_TOPVIEW, "", 1, 8);
		
		List<Product> listLatest = (ArrayList<Product>)latest.get("data");
		List<Product> listBestSell = (ArrayList<Product>)bestSell.get("data");
		List<Product> listGift = (ArrayList<Product>)gift.get("data");
		List<Product> listTopViews = (ArrayList<Product>)views.get("data");
		
		request.setAttribute("listLatest", listLatest);
		request.setAttribute("listBestSell", listBestSell);
		request.setAttribute("listGift", listGift);
		request.setAttribute("listTopViews", listTopViews);
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase("username")) {
					HttpSession session = request.getSession();
					session.setAttribute("user", userDAO.getUserByUsername(cookie.getValue()));
					break;
				}
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
