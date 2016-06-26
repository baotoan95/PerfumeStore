package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.BrandDAO;
import com.baotoan.dev.dao.CategoryDAO;
import com.baotoan.dev.dao.IntendedDAO;
import com.baotoan.dev.entity.Category;
import com.baotoan.dev.service.BrandDAOImpl;
import com.baotoan.dev.service.CategoryDAOImpl;
import com.baotoan.dev.service.IntendedDAOImpl;

/**
 * Servlet implementation class CategoryAdminControl
 */
public class CategoryAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO cateDAO = new CategoryDAOImpl();
	private IntendedDAO intendedDAO = new IntendedDAOImpl();
	private BrandDAO brandDAO = new BrandDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryAdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		if(action.equalsIgnoreCase("all")) {
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = cateDAO.getCategories(page, 10);
			request.setAttribute("pagination", ((String)result.get("pagination")).replaceAll("page", "categories?ac=all&page"));
			request.setAttribute("listCategories", result.get("data"));
			request.getRequestDispatcher("/WEB-INF/admin/pages/categories.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("w")) {
			try {
				int cateId = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("category", cateDAO.getCategoryById(cateId));
			} catch (Exception e) {}
			request.setAttribute("rands", brandDAO.getAll());
			request.setAttribute("intendeds", intendedDAO.getAll());
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-category.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("del")) {
			int cateId = Integer.parseInt(request.getParameter("cateId"));
			PrintWriter writer = response.getWriter();
			if(cateDAO.delCategory(cateId)) {
				writer.print("ok");
			} else {
				writer.print("fail");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		int brandId = Integer.parseInt(request.getParameter("brand"));
		int intended = Integer.parseInt(request.getParameter("intended"));
		PrintWriter writer = response.getWriter();
		if(action.equalsIgnoreCase("save")) {
			int cateId = Integer.parseInt(request.getParameter("cateId"));
			if(cateDAO.updateCategory(new Category(cateId, brandDAO.getBrandById(brandId), intendedDAO.getIntendedById(intended)))) {
				writer.print("ok");
			} else {
				writer.print("fail");
			}
		} else if(action.equalsIgnoreCase("add")) {
			if(cateDAO.addCategory(new Category(0, brandDAO.getBrandById(brandId), intendedDAO.getIntendedById(intended)))) {
				writer.print("ok");
			} else {
				writer.print("fail");
			}
		}
	}

}
