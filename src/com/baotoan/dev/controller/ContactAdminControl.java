package com.baotoan.dev.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.ContactDAO;
import com.baotoan.dev.entity.Contact;
import com.baotoan.dev.service.ContactDAOImpl;
import com.baotoan.dev.utils.MailSenderUtil;

/**
 * Servlet implementation class ContactAdminControl
 */
public class ContactAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO = new ContactDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactAdminControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		if(action.equalsIgnoreCase("all")) {
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = contactDAO.getAll(page, 10);
			request.setAttribute("contacts", result.get("data"));
			request.setAttribute("pagination", ((String)result.get("pagination")).replaceAll("page", "contacts?ac=all&page"));
			request.getRequestDispatcher("/WEB-INF/admin/pages/contacts.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("view")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Contact contact = contactDAO.getContact(id);
			request.setAttribute("contact", contact);
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-contact.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("del")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String msg = "Lỗi hệ thống";
			if(contactDAO.delContact(id)) {
				msg = "success";
			}
			response.getWriter().print(msg);
		} else if(action.equalsIgnoreCase("reply")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("contact", contactDAO.getContact(id));
			request.getRequestDispatcher("/WEB-INF/admin/pages/reply.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("ac");
		if(action != null && "reply".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String content = request.getParameter("content");
			Contact contact = contactDAO.getContact(id);
			new MailSenderUtil().mailSender(new String[] {contact.getEmail()}, "Cảm ơn bạn đã góp ý", content);
			request.setAttribute("message", "Đã trả lời");
			request.setAttribute("contact", contact);
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-contact.jsp").forward(request, response);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("contactId"));
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		Contact contact = contactDAO.getContact(id);
		contact.setStatus(status);
		if(contactDAO.updateContact(contact)) {
			response.sendRedirect("contacts?ac=all&page=1");
			return;
		} else {
			request.setAttribute("message", "Cập nhật thất bại");
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-contact.jsp").forward(request, response);
		}
	}

}
