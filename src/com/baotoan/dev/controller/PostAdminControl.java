package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.PostDAO;
import com.baotoan.dev.entity.Post;
import com.baotoan.dev.service.PostDAOImpl;

/**
 * Servlet implementation class PostAdminControl
 */
public class PostAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postDAO = new PostDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostAdminControl() {
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
			Map<String, Object> result = postDAO.getPosts(page, 10);
			request.setAttribute("pagination", ((String)result.get("pagination")).replaceAll("page", "posts?ac=all&page"));
			request.setAttribute("listPosts", result.get("data"));
			request.getRequestDispatcher("/WEB-INF/admin/pages/posts.jsp").forward(request, response);
			return;
		} else if(action.equalsIgnoreCase("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("post", postDAO.getPostById(id));
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-post.jsp").forward(request, response);
			return;
		} else if(action.equalsIgnoreCase("w")) {
			response.sendRedirect("w-post.html");
			return;
		} else if(action.equalsIgnoreCase("upp")) {
			request.setAttribute("listPosts", postDAO.getPostsUnapproved());
			request.getRequestDispatcher("/WEB-INF/admin/pages/posts.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		String action = request.getParameter("ac");
		PrintWriter pw = response.getWriter();
		if("save".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			boolean status = Boolean.parseBoolean(request.getParameter("status"));
			String content = request.getParameter("content");
			if(postDAO.updatePost(new Post(id, title, content, new Date(), status))) {
				pw.println("{\"message\":\"Đã lưu\"}");
				return;
			}
			pw.println("{\"message\":\"Lưu không thành công\"}");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			if(postDAO.delPost(id)) {
				pw.println("{\"message\":\"success\"}");
				return;
			} else {
				pw.println("{\"message\":\"Có lỗi\"}");
			}
		}
	}

}
