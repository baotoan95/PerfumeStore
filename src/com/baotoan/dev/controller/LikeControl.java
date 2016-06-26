package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.baotoan.dev.dao.LikeDAO;
import com.baotoan.dev.dao.ProductDAO;
import com.baotoan.dev.service.LikeDAOImpl;
import com.baotoan.dev.service.ProductDAOImpl;
import com.baotoan.dev.entity.Like;
import com.baotoan.dev.entity.User;

/**
 * Servlet implementation class LikeController
 */
@WebServlet("/LikeControl")
public class LikeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LikeDAO likeDAO = new LikeDAOImpl();
	private ProductDAO proDAO = new ProductDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		int productID = Integer.parseInt(request.getParameter("productID"));
		HttpSession session = request.getSession();
		Map<Integer, Like> likes = (HashMap<Integer, Like>)session.getAttribute("likes");
		int result = 0; // 0: false; n: true
		User user = (User)session.getAttribute("user");
		
		if(action.equalsIgnoreCase("add")) {
			if(null != user) { // Đã đăng nhập
				String username = user.getUsername();
				Like like = new Like(0, username, proDAO.getProductById(productID));
				if(likeDAO.addLike(like)) {
					result = likeDAO.countLikes(username);
					List<Like> own_likes = likeDAO.getLikeByUsername(username);
					Map<Integer, Like> new_likes = new HashMap<Integer, Like>();
					for(Like like_ : own_likes) {
						new_likes.put(like_.getProduct().getId(), like_);
					}
					session.setAttribute("likes", new_likes);
				} else {
					result = 0;
				}
			} else { // Chưa đăng nhập
				Like like = new Like(0, null, proDAO.getProductById(productID));
				if(likes != null) { // Đã có danh sách like
					if(!likes.containsKey(productID)) {
						likes.put(productID, like);
						result = likes.size();
					} else {
						result = 0;
					}
				} else { // Chưa có danh sách like
					Map<Integer, Like> new_likes = new HashMap<Integer, Like>();
					new_likes.put(productID, like);
					session.setAttribute("likes", new_likes);
					result = new_likes.size();
				}
			}
		} else { // Xóa mục ưa thích
			if(null != user && !likeDAO.delLike(user.getUsername(), productID)) { // Đã đăng nhập
				if(likeDAO.delLike(user.getUsername(), productID)) {
					if(likes.remove(productID) != null) {
						result = likes.size();
					} else {
						result = -1;
					}
				} else {
					result = -1;
				}
			} else { // Chưa đăng nhập
				if(likes.remove(productID) != null) {
					result = likes.size();
				} else {
					result = -1;
				}
			}
		}
		JSONObject object = new JSONObject();
		object.put("status", result);
		PrintWriter pw = response.getWriter();
		pw.print(object.toString());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
