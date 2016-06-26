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
 * Servlet implementation class ProductControl
 */
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDAO = new ProductDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = proDAO.getProductById(id);
		request.setAttribute("product", product);
		Map<String, Object> data = proDAO.getProducts(ProductConstantValues.BY_MANUFACTURER, product.getCategory().getBrand().getId(), 1, 8); 
		List<Product> listRelateds = (List<Product>)data.get("data");
		request.setAttribute("listRelateds", listRelateds);
		request.getRequestDispatcher("WEB-INF/pages/view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
