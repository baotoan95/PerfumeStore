package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.entity.Product;
import com.baotoan.dev.service.ProductDAOImpl;
import com.baotoan.dev.utils.ProductConstantValues;

/**
 * Servlet implementation class SearchControl
 */
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO prodDAO = new ProductDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("for");
		String find = "";
		
		if("manuf".equalsIgnoreCase(action)) {
			int brandId = Integer.parseInt(request.getParameter("manuf"));
			response.sendRedirect("brand.html?ac=view&id=" + brandId);
		} else if("price".equalsIgnoreCase(action)) {
			int price = Integer.parseInt(request.getParameter("p"));
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = prodDAO.getProducts(ProductConstantValues.BY_PRICE, price, page, 28);
			List<Product> listProduct = (List<Product>)result.get("data");
			String pagination = ((String)result.get("html")).replaceAll("page", "search.html?for=price&p=" + price + "&page");
			
			switch (price) {
			case 1:
				find = "< 300000";
				break;
			case 2:
				find = "300000 - 600000";
				break;
			case 3:
				find = "600000 - 900000";
				break;
			case 4:
				find = "Trên 900000";
				break;
			default:
				break;
			}
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("pagination", pagination);
			request.setAttribute("find", "Tìm theo giá " + find);
			request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
		} else if("pre".equalsIgnoreCase(action)) {
			String pre = request.getParameter("pre");
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = prodDAO.getProducts(ProductConstantValues.BY_CHAR, pre, page, 28);
			List<Product> listProduct = (List<Product>)result.get("data");
			String pagination = ((String)result.get("html")).replaceAll("page", "search.html?for=pre&pre=" + pre + "&page");
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("pagination", pagination);
			request.setAttribute("find", "Tìm theo prefix " + pre);
			request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
		} else {
			response.sendRedirect("home.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("for");
		
		if("tk".equalsIgnoreCase(action)) {
			String tk = request.getParameter("tk");
			int page = Integer.parseInt(request.getParameter("page"));
			
			Map<String, Object> result = prodDAO.getProducts(ProductConstantValues.BY_SEARCH, tk, page, 28);
			List<Product> listProduct = (List<Product>)result.get("data");
			String pagination = ((String)result.get("html")).replaceAll("page", "search.html?for=tk&tk=" + tk + "&page");
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("pagination", pagination);
			request.setAttribute("find", "Tìm theo tên " + tk);
			request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
		} else {
			response.sendRedirect("home.html");
		}
		
	}

}
