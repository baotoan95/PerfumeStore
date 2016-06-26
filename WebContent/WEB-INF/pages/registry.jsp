<%@page import="com.baotoan.dev.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html !>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="autdor" content="baotoan95">
<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
<title>Đăng ký</title>
</head>
<body>
	<!-- Chuyển hướng nếu đã đăng nhập -->
	<% 	
		User user = (User)session.getAttribute("user");
		String url = request.getRequestURL().toString();
		boolean check = url.substring(url.lastIndexOf("/") + 1, url.length()).equalsIgnoreCase("registry.html");
		if(user != null && check) {
			response.sendRedirect("home.html");
		}
	%>
	
	<div id="wrapper">
		<!-- Header -->
		<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
		<!-- End header -->
		
		<!-- Content -->
		<div id="content">
			<div class="container">
				<div class="row">
					<!-- Navigation -->
					<jsp:include page="/WEB-INF/templates/main-navigation.jsp"></jsp:include>
					<!-- End navigation -->
					
					<!-- Left content -->
					<div class="col-sm-3 primary left-content">
						<jsp:include page="/WEB-INF/templates/option-box.jsp"></jsp:include>
						<jsp:include page="/WEB-INF/templates/left-side.jsp"></jsp:include>
					</div> <!-- End .left-content -->
					
					<!-- Right content -->
					<div class="col-sm-9 primary right-content">
						<div class="content-w">
							<div class="news-title"><%= (user == null) ? "Đăng ký" : "Thông tin cá nhân" %></div>
							<div class="news-content">
								<%= (user == null) ? "Cảm ơn bạn đã quan tâm đến Thế Giới Nước Hoa. Để đăng ký vui lòng điền đầy đủ các thông tin dưới đây." : "" %>
								<div id="form">
									<form class="form-horizontal" action="UserControl" method="post">
										<span class="form-title">Thông tin cá nhân</span>
									  	<div class="form-group">
									    	<label for="name" class="col-sm-2 col-md-offset-1 control-label">Họ tên *</label>
									    	<div class="col-sm-8">
									      		<input type="text" value="${ sessionScope.user.name }" onblur="check(this)" class="form-control" id="fullName" name="fullName">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="email" class="col-sm-2 col-md-offset-1 control-label">Email *</label>
									    	<div class="col-sm-8">
									      		<input type="text" value="${sessionScope.user.email }" onblur="check(this)" class="form-control" id="email" name="email">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="address" class="col-sm-2 col-md-offset-1 control-label">Địa chỉ</label>
									    	<div class="col-sm-8">
									      		<input type="text" value="${sessionScope.user.address }" class="form-control" id="address" name="address">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="address" class="col-sm-2 col-md-offset-1 control-label">Thành phố</label>
									    	<div class="col-sm-8">
									    		<select class="form-control" id="city" name="city">
									    			<option value="HCM">HCM</option>
									    			<option value="Hà Nội">Hà Nội</option>
									    			<option value="Đà Nẵng">Đà Nẵng</option>
									    		</select>
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="phone" class="col-sm-2 col-md-offset-1 control-label">Số điện thoại *</label>
									    	<div class="col-sm-8">
									      		<input type="text" value="${sessionScope.user.phone }" onblur="check(this)" class="form-control" id="phone" name="phone">
									    	</div>
									  	</div>
									  	<% if(user == null || !user.isFBAcc()) { %>
									  	<span class="form-title">Thông tin bảo mật</span>
									  	<br/><span class="error">${requestScope.message }</span><br/><br/>
									  	<div class="form-group">
									    	<label for="username" class="col-sm-3 control-label">Tên đăng nhập *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="username" name="username">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="password" class="col-sm-2 col-md-offset-1 control-label">Mật khẩu *</label>
									    	<div class="col-sm-8">
									      		<input type="password" onblur="check(this)" class="form-control" id="password" name="password">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="confirm-pass" class="col-sm-3 control-label">Xác nhận mật khẩu *</label>
									    	<div class="col-sm-8">
									      		<input type="password" onblur="check(this)" class="form-control" id="confirm-pass" name="confirm-password">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="secureQuestion" class="col-sm-3 control-label">Câu hỏi bảo mật *</label>
									    	<div class="col-sm-8">
									      		<select id="secureQuestion" class="form-control" name="question">
									      			<option value="Tên của bố bạn là gì?">Tên của bố bạn là gì?</option>
									      			<option value="Món ăn yêu thích của bạn?">Quê bạn ở đâu?</option>
									      			<option value="Tên người yêu của bạn?">Tên người yêu của bạn?</option>
									      			<option value="Trường mẫu giáo của bạn tên gì?">Trường mẫu giáo của bạn tên gì?</option>
									      			<option value="Môn học bạn yêu thích nhất là gì?">Môn học bạn yêu thích nhất là gì?</option>
									      		</select>
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="secureAnswer" class="col-sm-3 control-label">Câu trả lời *</label>
									    	<div class="col-sm-8">
									      		<input onblur="check(this)" type="text" id="secureAnswer" name="answer" class="form-control"/>
									    	</div>
									  	</div>
									  	<%} %>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	Nhấn nút Đăng ký có nghĩa là bạn đã chấp nhận <a href="#">Điều khoản dịch vụ</a> của chúng tôi.
										    </div>
										</div>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	<button type="submit" name="btAction" value="<%= user != null ? "update" : "Registry" %>" <%= user != null ? "" : "disabled='disabled'" %> class="btn btn-default" id="buttonRegis">Đăng ký</button>
										    </div>
										</div>
										<% if(user == null) { %>
										<script type="text/javascript">
									    	var fullName = document.getElementById("fullName");
									    	var email = document.getElementById("email");
									    	var phone = document.getElementById("phone");
									    	var username = document.getElementById("username");
									    	var password = document.getElementById("password");
									    	var confirm_pass = document.getElementById("confirm-pass");
									    	var secureAnswer = document.getElementById("secureAnswer");
									    	var buttonRegis = document.getElementById("buttonRegis");
									    	
									    	
									    	function check(element) {
									    		if(element.value.length == 0) {
									    			element.style.background = "#FFDFF7";
										    	} else {
										    		element.style.background = "#AEFFB3";
										    	}
									    		if(password.value.length > 0 && confirm_pass.value.length > 0) {
									    			if(password.value === confirm_pass.value) {
									    				confirm_pass.style.background = "#AEFFB3";
									    			} else {
									    				confirm_pass.style.background = "#FFDFF7";
									    			}
									    		}
									    		if(fullName.value.length > 0 && email.value.length > 0 
									    				&& phone.value.length > 0 && username.value.length > 0 
									    				&& secureAnswer.value.length > 0) {
									    			buttonRegis.removeAttribute("disabled");
									    		} else {
									    			buttonRegis.setAttribute("disabled", "disabled");
									    		}
									    	}
									    </script>
									   	<%} %>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div> 
			</div>
		</div> <!-- End content -->
		
		<!-- Footer -->
		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
		<!-- End footer -->
	</div>
</body>
</html>