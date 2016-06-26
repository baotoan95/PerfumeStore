package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.RoleDAO;
import com.baotoan.dev.dao.UserDAO;
import com.baotoan.dev.entity.User;
import com.baotoan.dev.service.RoleDAOImpl;
import com.baotoan.dev.service.UserDAOImpl;
import com.baotoan.dev.utils.Common;

/**
 * Servlet implementation class UserAdminControl
 */
public class UserAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAOImpl();
	private RoleDAO roleDAO = new RoleDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAdminControl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("ac");
		if (action.equalsIgnoreCase("all")) {
			int page = Integer.parseInt(request.getParameter("page"));
			Map<String, Object> result = userDAO.getUsers(page, 10);
			request.setAttribute("pagination", ((String) result
					.get("pagination")).replaceAll("page", "users?ac=all&page"));
			request.setAttribute("listUsers", result.get("data"));
			request.getRequestDispatcher("/WEB-INF/admin/pages/users.jsp")
					.forward(request, response);
		} else if (action.equalsIgnoreCase("edit")) {
			String username = request.getParameter("u");
			request.setAttribute("user", userDAO.getUserByUsername(username));
			request.setAttribute("listRole", roleDAO.getAll());
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-user.jsp")
					.forward(request, response);
		} 
//		else if (action.equalsIgnoreCase("add")) {
//			request.setAttribute("listRole", roleDAO.getAll());
//			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-user.jsp")
//					.forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");

		PrintWriter writer = response.getWriter();
		if (action.equalsIgnoreCase("save")) {
			if (userDAO.updateUser(getUserByRequest(request))) {
				writer.print("success");
			} else {
				writer.print("fail");
			}
		} 
//		else if (action.equalsIgnoreCase("add")) {
//			if(userDAO.addUser(getUserByRequest(request))) {
//				writer.print("success");
//			} else {
//				writer.print("fail");
//			}
//		} 
		else if(action.equalsIgnoreCase("del")) {
			String username = request.getParameter("username");
			if(userDAO.delUser(username)) {
				writer.print("success");
			} else {
				writer.print("fail");
			}
		}
	}
	
	private User getUserByRequest(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		String score = request.getParameter("score");
		String registryDate = request.getParameter("registryDate");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String secureQuest = request.getParameter("secureQuest");
		String secureAns = request.getParameter("secureAns");
		String role = request.getParameter("role");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setCity(city);
		user.setPhone(phone);
		try {
			user.setScore(Integer.parseInt(score));
		} catch (Exception e) {
			user.setScore(0);
		}
		Date date;
		try {
			date = Common.dateFormat.parse(registryDate);
			user.setRegistryDate(date);
		} catch (ParseException e) {
			user.setRegistryDate(new Date());
		}
		user.setRegistryDate(new Date());
		user.setEmail(email);
		user.setStatus(status);
		user.setSecureAnswer(secureAns);
		user.setSecureQuestion(secureQuest);
		user.setRole(Integer.parseInt(role));
		
		return user;
	}

}
