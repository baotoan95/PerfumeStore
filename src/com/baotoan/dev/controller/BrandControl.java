package com.baotoan.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.BrandDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.entity.Brand;
import com.baotoan.dev.service.BrandDAOImpl;
import com.baotoan.dev.service.ProductDAOImpl;
import com.baotoan.dev.utils.ProductConstantValues;

/**
 * Servlet implementation class BrandControl
 */
public class BrandControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BrandDAO brandDAO = new BrandDAOImpl();
	private ProductDAO proDAO = new ProductDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrandControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		if("view".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Brand brand = brandDAO.getBrandById(id);
			request.setAttribute("brand", brand);
			request.setAttribute("listProduct", proDAO.getProducts(ProductConstantValues.BY_MANUFACTURER, id, 1, 100).get("data"));
			request.getRequestDispatcher("/WEB-INF/pages/brand.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
