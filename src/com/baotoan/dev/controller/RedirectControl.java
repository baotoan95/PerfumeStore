package com.baotoan.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.InforDAO;
import com.baotoan.dev.service.InforDAOImpl;

/**
 * Servlet implementation class RedirectControl
 */
public class RedirectControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InforDAO inforDAO = new InforDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String link = request.getParameter("l");
		request.setAttribute("infor", inforDAO.getInfoBySlug(link));
		request.getRequestDispatcher("/WEB-INF/pages/infor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
