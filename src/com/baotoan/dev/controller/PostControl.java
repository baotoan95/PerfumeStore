package com.baotoan.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baotoan.dev.dao.PostDAO;
import com.baotoan.dev.entity.Post;
import com.baotoan.dev.service.PostDAOImpl;

/**
 * Servlet implementation class PostControl
 */
public class PostControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postDAO = new PostDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("id"));
		Post post = postDAO.getPostById(postId);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/pages/news.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
