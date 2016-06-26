package com.baotoan.dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.baotoan.dev.dao.UserDAO;
import com.baotoan.dev.entity.User;
import com.baotoan.dev.service.UserDAOImpl;

@WebServlet("/CallbackServlet")
public class CallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAOImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (code != null && state != null) {
			if (state.equals("123456789")) {
				// Let's prepare our HTTP GET Request
				String endPoint = "/v2.4/oauth/access_token";
				Map<String, String> parameters = new HashMap<>();
				parameters.put("client_id", "1527867264204402");
				parameters.put("client_secret", "51ca05422c6a1f5a5dad77ddd70fab19");
				parameters.put("redirect_uri", "http://localhost:8080/PerfumeStore/CallbackServlet");
				parameters.put("code", code);

				String result = HttpRequestHandler.execute(endPoint,
						parameters, "GET");

				if (result != null) {
					// Let's extract the access token
					try {
						JSONParser parser = new JSONParser();
						JSONObject jsonToken = (JSONObject) parser
								.parse(result);

						String accessToken = (String) jsonToken
								.get("access_token");
						// Long expires = (Long)jsonToken.get("expires_in");

						// we now have a valid access and short-lived access
						// token.
						// let's use it to get some data from the user account:

						endPoint = "/me";
						parameters.clear();
						parameters.put("fields", "id,first_name,last_name,gender,email");
						parameters.put("access_token", accessToken);

						result = HttpRequestHandler.execute(endPoint, parameters, "GET");
						JSONObject jsonUserData = (JSONObject) parser.parse(result);
						String id = (String) jsonUserData.get("id");
						String firstName = (String) jsonUserData.get("first_name");
						String lastName = (String) jsonUserData.get("last_name");
//						String gender = (String) jsonUserData.get("gender");
						String email = (String) jsonUserData.get("email");
//						System.out.println(jsonUserData);

						User user = new User();
						user.setEmail(email);
						user.setUsername("user_" + id);
						user.setName(firstName + " " + lastName);
						user.setStatus("actived");
						user.setRegistryDate(new Date());
						user.setRole(1);
						user.setFBAcc(true);
						
						HttpSession session = request.getSession();
						if(userDAO.checkLogin(user.getUsername(), null, "facebook").equalsIgnoreCase("ok")) {
							session.setAttribute("user", user);
							response.sendRedirect("home.html");
						} else {
							if(userDAO.addUser(user)) {
								session.setAttribute("user", user);
								response.sendRedirect("home.html");
							} else {
								response.sendRedirect("registry.html");
							}
						}
						
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {// There problem has occurred when processing the
						// request
				}
			} else {// A third party has interfered into the login process
				out.println("Something went wrong: The authorization process cannot continue...");
			}
		} else {
			out.println("Something is going wrong...");
		}
	}
}