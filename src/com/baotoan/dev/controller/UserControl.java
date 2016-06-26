package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.baotoan.dev.dao.LikeDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.entity.Like;
import com.baotoan.dev.entity.User;
import com.baotoan.dev.service.LikeDAOImpl;
import com.baotoan.dev.service.ProductDAOImpl;
import com.baotoan.dev.service.UserDAOImpl;
import com.baotoan.dev.utils.GenerateCode;
import com.baotoan.dev.utils.MailSenderUtil;

/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();
	private LikeDAO likeDAO = new LikeDAOImpl();
	private ProductDAO proDAO = new ProductDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControl() { }
    
    @SuppressWarnings("unchecked")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	String redirect = "";
    	String action = request.getParameter("ac");

    	if("act".equalsIgnoreCase(action)) { // Active account
    		String code = request.getParameter("c");
        	String username = request.getParameter("un");
        	if("ok".equalsIgnoreCase(userDAO.activeAccount(username, code))) {
        		request.setAttribute("message", "Tài khoản đã được xác thực thành công, vui lòng đăng nhập");
        	} else {
        		request.setAttribute("message", "Có lỗi, vui lòng liên hệ với chúng tôi");
        	}
        	redirect = "login.html";
    	} else if("getQuestion".equalsIgnoreCase(action)) { // Get secure question for remind password
    		PrintWriter out = response.getWriter();
    		String username = request.getParameter("username");
    		JSONObject rs = new JSONObject();
    		response.setContentType("application/json, charset=UTF-8");
    		response.setHeader("Cache-control", "no-cache, no-store");
    		String notice = "";
    		if((notice = userDAO.checkRequestRMPassword(username)).equalsIgnoreCase("ok")) {
    			User user = userDAO.getUserByUsername(username);
    			rs.put("status", "ok");
    			rs.put("question", user.getSecureQuestion());
    		} else {
    			rs.put("status", notice);
    		}
    		out.print(rs.toJSONString());
    		return;
    	} else if("rm".equalsIgnoreCase(action)) { // Remind password
    		String username = request.getParameter("u");
    		String email = request.getParameter("em");
    		System.out.println(email);
    		String newPass = GenerateCode.generate(10);
    		String message = "";
    		if((message = userDAO.remindPassword(username, newPass)).equalsIgnoreCase("ok")) {
    			MailSenderUtil.mailSender(new String[] {email}, "TGNH - Đặt lại mật khẩu", 
    					"Chào bạn,<br/>Chúng tôi đã đặt lại mật khẩu mới cho bạn<br/>"
    					+ "Mật khẩu mới là: " + newPass + "<br/>Để đăng nhập vào link dưới:<br/>"
    					+ request.getRequestURL() + "/login.html"
    	    			);
    			response.sendRedirect("login.html");
    			return;
    		} else {
    			request.setAttribute("message", message);
    			redirect = "login.html";
    		}
    	} else if("logout".equalsIgnoreCase(action)) {
    		// Remove session
    		HttpSession session = request.getSession();
    		session.removeAttribute("user");
    		
    		// Remove cookie
    		Cookie[] cookies = request.getCookies();
    		for(Cookie cookie : cookies) {
    			if(cookie.getName().equalsIgnoreCase("username")) {
    				cookie.setMaxAge(0);
    				response.addCookie(cookie);
    				break;
    			}
    		}
    		// Remove likes
    		session.removeAttribute("likes");
    		session.removeAttribute("numOfLikes");
    		
    		response.sendRedirect("home.html");
    		return;
    	} else {
    		response.sendRedirect("home.html");
    		return;
    	}
    	RequestDispatcher dis = request.getRequestDispatcher(redirect);
		dis.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("btAction");
		String username = GenerateCode.md5(request.getParameter("username"));
		
		String redirect = "home.html";
		
		if(action.equalsIgnoreCase("Registry")) { // Registry
			String password = GenerateCode.md5(request.getParameter("password"));
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String city = request.getParameter("city");
			String status = GenerateCode.generate(10);
			String secureQuestion = request.getParameter("question");
			String secureAnswer = request.getParameter("answer");
			if(userDAO.addUser(new User(username, password, fullName, address, city, phone, 0, new Date(), email, status, secureQuestion, secureAnswer, 1, false))) {
				redirect = "login.html";
				request.setAttribute("message", "Chúc mừng bạn đã đăng ký thành công, vui lòng kiểm tra email để xác thực tài khoản!");
				MailSenderUtil.mailSender(new String[] {email}, "Đăng ký thành công", 
						"Chào bạn " + fullName + ",<br/>Cảm ơn bạn đã tin dùng dịch vụ của chúng tôi<br/>"
						+ "Tên tài khoản: " + username + "<br/>"
						+ "Mật khẩu: " + password + "<br/>"
						+ "Vui lòng xác thực tài khoản bằng việc click vào link dưới"
						+ "<br/>" + request.getScheme() + "://" +
			             request.getServerName() + ":" +
			             request.getServerPort() +
			             request.getRequestURI() + "?" + "ac=act&un=" + username + "&c=" + status);
			} else {
				request.setAttribute("message", "Tên tài khoản đã tồn tại, vui lòng kiểm tra lại hoặc chọn tên tài khoản khác");
				redirect = "registry.html";
			}
		} else if("Login".equalsIgnoreCase(action)) { // Login
			String password = GenerateCode.md5(request.getParameter("password"));
			String message = "";
			if((message = userDAO.checkLogin(username, password, "NORMAL")).equalsIgnoreCase("ok")) {
				HttpSession session = request.getSession();
				User user = userDAO.getUserByUsername(username);
				session.setAttribute("user", user);
				
				if(request.getParameter("remember") != null) {
					Cookie cookieUser = new Cookie("username", user.getUsername());
					cookieUser.setMaxAge(36000);
					response.addCookie(cookieUser);
				}
				// Thêm like
				Map<Integer, Like> old_likes = (HashMap<Integer, Like>)session.getAttribute("likes");
				if(null != old_likes) {
					Iterator<Integer> key = old_likes.keySet().iterator();
					for (int i = 0; i < old_likes.size(); i++) {
						likeDAO.addLike(new Like(0, username, proDAO.getProductById(key.next())));
					}
				}
				List<Like> likes = likeDAO.getLikeByUsername(username);
				Map<Integer, Like> new_likes = new HashMap<Integer, Like>();
				for(Like like : likes) {
					new_likes.put(like.getProduct().getId(), like);
				}
				session.setAttribute("likes", new_likes);
				response.sendRedirect("home.html");
				return;
			} else {
				request.setAttribute("message", message);
				redirect = "login.html";
			}
		} else if("rqrmpass".equalsIgnoreCase(action)) {
    		String secureAnswer = request.getParameter("answer");
    		String email = request.getParameter("email");
    		String message = "";
    		if((message = userDAO.requestRMPassword(username, GenerateCode.generate(10), secureAnswer)).equalsIgnoreCase("ok")) {
    			MailSenderUtil.mailSender(new String[] {email}, "TGNH - Quên mật khẩu", 
				"Chào bạn, chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn, vui lòng vào link dưới đây để đặt lại:<br/>"
				+ request.getRequestURL() + "?ac=rm&u=" + username + "&em=" + email
    			);
    			request.setAttribute("notice", "Yêu cầu đã được gửi, vui lòng kiểm tra email của bạn!");
    			redirect = "notice.html";
    		} else {
    			System.out.println(message);
    			request.setAttribute("message", message);
    			redirect = "remind.html";
    		}
    	} else if("update".equalsIgnoreCase(action)) {
    		HttpSession session = request.getSession();
    		String password = request.getParameter("password");
    		String fullName = request.getParameter("fullName");
    		String email = request.getParameter("email");
    		String address = request.getParameter("address");
    		String phone = request.getParameter("phone");
    		String city = request.getParameter("city");
    		String secureQuestion = request.getParameter("question");
    		String secureAnswer = request.getParameter("answer");
    		
    		User user = (User)session.getAttribute("user");
    		if(user.isFBAcc()) {
    			userDAO.updateUser(new User(user.getUsername(), "", fullName, address, city, phone, user.getScore(), user.getRegistryDate(), email, user.getStatus(), "", "", 1, true));
    		} else {
    			userDAO.updateUser(new User(username, password, fullName, address, city, phone, user.getScore(), user.getRegistryDate(), email, user.getStatus(), secureQuestion, secureAnswer, 1, false));
    		}
    		session.setAttribute("user", userDAO.getUserByUsername(user.getUsername()));
			response.sendRedirect("profile.html");
			return;
    	}
		RequestDispatcher dis = request.getRequestDispatcher(redirect);
		dis.forward(request, response);
	}
	
}
