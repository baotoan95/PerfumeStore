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

import com.baotoan.dev.dao.PromotionDAO;
import com.baotoan.dev.entity.Promotion;
import com.baotoan.dev.service.PromotionDAOImpl;
import com.baotoan.dev.utils.Common;
import com.baotoan.dev.utils.Loader;

/**
 * Servlet implementation class PromotionAdminControl
 */
public class PromotionAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PromotionDAO proDao = new PromotionDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromotionAdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ac");
		if(action.equalsIgnoreCase("all")) {
			int page = Integer.parseInt((String)request.getParameter("page"));
			Map<String, Object> result = proDao.getPromotions(page, 10);
			request.setAttribute("pagination", ((String)result.get("pagination")).replaceAll("page", "promotions?ac=all&page"));
			request.setAttribute("listPromotions", result.get("data"));
			request.getRequestDispatcher("/WEB-INF/admin/pages/promotions.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("w")) {
			try {
				int promotionId = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("promotion", proDao.getPromotionById(promotionId));
			} catch (Exception e) {}
			request.getRequestDispatcher("/WEB-INF/admin/pages/edit-promotion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		String path = getServletContext().getRealPath("/") + "resources/images/";
		Map<String, String> result = Loader.upload(path, request);
		if(result.get("ac").equalsIgnoreCase("del")) {
			int id = Integer.parseInt(request.getParameter("id"));
			if(proDao.delPromotion(id)) {
				writer.print("ok");
			} else {
				writer.print("fail");
			}
			return;
		}
		String resourceAvatar = result.get("avatar");
		String  realPath = resourceAvatar.substring(resourceAvatar.indexOf("resources"), resourceAvatar.length());
		if(result.get("status").equalsIgnoreCase("true")) {
			String action = result.get("ac");
			String title = result.get("title");
			String desc = result.get("desc");
			int id = 0;
			try {
				id = Integer.parseInt(result.get("proId"));
			} catch (Exception e) {}
			String content = result.get("content");
			
			if(action.equalsIgnoreCase("save")) {
				Date startDate = new Date();
				Date endDate = new Date();
				try {
					startDate = Common.dateFormat.parse(result.get("startDate"));
					endDate = Common.dateFormat.parse(result.get("endDate"));
					if(proDao.updatePromotion(new Promotion(id, title, content, desc, realPath, startDate, endDate))) {
						writer.print("ok");
					}
				} catch (ParseException e) {
					e.printStackTrace();
					writer.print("fail");
				}
			} else if(action.equalsIgnoreCase("add")) {
				try {
					Date startDate = Common.dateFormat.parse(result.get("startDate"));
					Date endDate = Common.dateFormat.parse(result.get("endDate"));
					if(proDao.addPromotion(new Promotion(0, title, content, desc, realPath, startDate, endDate))) {
						writer.print("ok");
						return;
					} else {
						writer.print("fail");
					}
				} catch (ParseException e) {
					e.printStackTrace();
					writer.print("fail");
				}
			} else {
				writer.print("fail");
			}
		} else {
			writer.print("fail");
		}
	}

}
