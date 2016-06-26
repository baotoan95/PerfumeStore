package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.InforDAO;
import com.baotoan.dev.entity.Information;
import com.baotoan.dev.service.InforDAOImpl;

/**
 * Servlet implementation class InforControl
 */
public class InforControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InforDAO inforDAO = new InforDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InforControl() {
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
		if(action.equalsIgnoreCase("all")) {
			List<Information> listInfor = (List<Information>)inforDAO.getAll();
			request.setAttribute("listInfor", listInfor);
			request.getRequestDispatcher("/WEB-INF/admin/pages/informations.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Information infor = inforDAO.getInfoById(id);
			request.setAttribute("infor", infor);
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-infor.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter prw = response.getWriter();
		if(action.equalsIgnoreCase("save")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String content = request.getParameter("content").replaceAll("<img",	"<img class=\"img-responsive\"");
			boolean status = Boolean.parseBoolean(request.getParameter("status"));
			String slug = request.getParameter("slug");
			if(inforDAO.updateInfo(new Information(id, name, content, status, slug))) {
				prw.println("{\"message\":\"Đã lưu\"}");
				return;
			} else {
				prw.println("{\"message\":\"Cập nhật chưa thành công\"}");
			}
		}
	}

}
