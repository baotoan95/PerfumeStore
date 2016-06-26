<%@page import="java.util.HashMap"%>
<%@page import="com.baotoan.dev.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.4&appId=659851080824552";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<div id="header-wrapper">
	<div class="container">
		<div class="row">
			<div class="col-sm-3 logo-wrapper">
				<div id="logo"></div>
			</div>
			<div class="col-sm-5">
				<div id="form-wrapper">
					<form action="UserControl" method="POST">
						<input type="text" name="username" value="Tên đăng nhập" 
						onfocus="this.value = ''" onblur="if (this.value == '')this.value = 'Tên đăng nhập'" /> 
						<input type="password" name="password" value="Mật khẩu"
							onfocus="this.value = ''" onblur="if (this.value == '')this.value = 'Mật khẩu'" /> 
						<input type="hidden" name="btAction" value="Login"/>
						<input type="hidden" name="remember"/>
						<input type="submit" class="btn-login" value=" " />
						<c:if test="${empty sessionScope.user }">
						<a href="https://www.facebook.com/dialog/oauth?client_id=1527867264204402&redirect_uri=http://localhost:8080/PerfumeStore/CallbackServlet&state=123456789&scope=public_profile,email,user_friends"><img alt="Login with Facebook" src="resources/images/facebookbutton.png" style="heigth: 20px; width: 100px;"></a>
						</c:if>
					</form>
				<%
					int numOfLikes = 0;
					if(null != session.getAttribute("likes")) {
						numOfLikes = Integer.valueOf(((HashMap<Integer, Like>)session.getAttribute("likes")).size() + "");
					}
				%>
				<c:if test="${empty sessionScope.user }">
					<a href="login.html">Đăng nhập</a> | <a href="registry.html">Đăng ký</a> | <a href="remind.html">Quên mật khẩu?</a> | <a href="likes.html" class="numOfLikes">Mục ưa thích (<%= numOfLikes %>)</a>
				</c:if>
				<c:if test="${not empty sessionScope.user }">
					Xin chào <a href="profile.html">${sessionScope.user.name }</a> 
					| <a href="UserControl?ac=logout">Đăng xuất</a> | <a href="likes.html" class="numOfLikes">Mục ưa thích (<%= numOfLikes %>)</a>
				</c:if>
				</div>
			</div>
			<div class="col-sm-4">
				<div id="support-wrapper">
					<div class="cart-container">
						<div id="cart">
							<%
								int numOfCarts = 0;
								if(null != session.getAttribute("carts")) {
									numOfCarts = ((HashMap<Integer, Cart>)session.getAttribute("carts")).size();
								}
							%>
							<span class="glyphicon glyphicon-shopping-cart cart-icon"></span>
							<span class="cart-info"> <a href="cart.html" id="cart-container">Có (<%= numOfCarts %>) sản phẩm</a></span>
						</div>
					</div>
					<div id="support-container">
						<div class="pull-left s-left">
							<span class="support-free-icon">HOTLINE</span>
						</div>
						<div class="pull-right s-right">
							<span class="hotline-number">1900 69 69 10</span> <br /> 
							<span class="hotline-number">0902 750 447</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>