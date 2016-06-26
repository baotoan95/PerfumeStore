package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.ContactDAO;
import com.baotoan.dev.entity.Contact;
import com.baotoan.dev.service.ContactDAOImpl;

/**
 * Servlet implementation class ContactControl
 */
public class ContactControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO = new ContactDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("ac");
		if(action.equalsIgnoreCase("add")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String content = request.getParameter("content");
			Contact contact = new Contact(0, name, email, phone, content, new Date(), false);
			if(contactDAO.addContact(contact)) {
				request.setAttribute("notice", "Cảm ơn bạn đã quan tâm tới Thế Giới Nước Hoa, chúng tôi sẽ cố gắng liên lạc trực tiếp với bạn nếu cần thiết, thân!!!");
				request.getRequestDispatcher("/WEB-INF/pages/notice.jsp").forward(request, response);
			} else {
				request.setAttribute("notice", "Xin lỗi, chức năng bị lỗi, vui lòng thử lại!!!");
				request.getRequestDispatcher("/WEB-INF/pages/notice.jsp").forward(request, response);
			}
		}
	}

}
