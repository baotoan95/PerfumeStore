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
<title>Đăng nhập</title>
</head>
<body>
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
							<div class="news-title">Đăng nhập</div>
							<div class="news-content">
								Nếu bạn đã có một tài khoản, vui lòng đăng nhập. Nếu chưa hãy <a href="registry.html">Đăng ký</a> để nhận được nhiều ưu đãi.
								<div id="form">
									<form class="form-horizontal" action="UserControl" method="post">
										<span class="form-title">Thông tin đăng nhập</span><br/>
										<span class="error">${requestScope.message }</span><br/><br/>
									  	<div class="form-group">
									    	<label for="name" class="col-sm-2 col-md-offset-1 control-label">Username *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="username" name="username">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="email" class="col-sm-2 col-md-offset-1 control-label">Password *</label>
									    	<div class="col-sm-8">
									      		<input type="password" onblur="check(this)" class="form-control" id="password" name="password">
									    	</div>
									  	</div>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	<input name="remember" type="checkbox"/> Ghi nhớ mật khẩu
										    </div>
										</div>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	<button type="submit" name="btAction" value="Login" disabled="disabled" class="btn btn-default" id="buttonLogin">Đăng nhập</button>
										    </div>
										</div>
										<script type="text/javascript">
									    	var username = document.getElementById("username");
									    	var password = document.getElementById("password");
									    	var buttonLogin = document.getElementById("buttonLogin");
									    	
									    	function check(element) {
									    		if(element.value.length == 0) {
									    			element.style.background = "#FFDFF7";
										    	} else {
										    		element.style.background = "#AEFFB3";
										    	}
									    		if(username.value.length > 0 && password.value.length > 0) {
									    			buttonLogin.removeAttribute("disabled");
									    		} else {
									    			buttonLogin.setAttribute("disabled", "disabled");
									    		}
									    	}
									    </script>
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