<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html!>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="autdor" content="baotoan95">
<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
<title>Liên hệ</title>
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
							<div class="news-title">Liên hệ</div>
							<div class="news-content">
								<div class="title">Công ty TNHH thế giới nước hoa</div>
								<p style="margin-top: 1em; font-weight: bold;" class="ct-p col-sm-10 col-sm-offset-1">Quý khách có thể liên hệ trực tiếp với Thế Giới Nước Hoa qua:</p>
								<ul class="ct-ul col-sm-9 col-sm-offset-2" style="clear: both;">
									<li>Hotline: 0902.750.447</li>
									<li>Điện thoại hoặc Yahoo các cửa hàng: Xem tại đây</li>
									<li>Email: info@thegioinuochoa.com.vn</li>
									<li>ĐC: Số 10 Cách Mạng Tháng 8, P. Bến Thành, Q.1, Tp HCM</li>
									<li>Hoặc điền đầy đủ thông tin dưới đây và gởi cho Thế Giới Nước Hoa, chúng tôi sẽ liên lạc với quý khách trong thời gian sớm nhất.</li>
								</ul>
								<div id="form">
									<form class="form-horizontal" action="contact" method="post">
										<input type="hidden" name="ac" value="add">
									  	<div class="form-group">
									    	<label for="name" class="col-sm-2 col-md-offset-1 control-label">Tên *</label>
									    	<div class="col-sm-8">
									      		<input type="text" name="name" class="form-control" id="name">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="email" class="col-sm-2 col-md-offset-1 control-label">Email *</label>
									    	<div class="col-sm-8">
									      		<input type="text" name="email" class="form-control" id="email">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="phone" class="col-sm-2 col-md-offset-1 control-label">Số điện thoại *</label>
									    	<div class="col-sm-8">
									      		<input type="text" name="phone" class="form-control" id="phone">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="phone" class="col-sm-2 col-md-offset-1 control-label">Nội dung *</label>
									    	<div class="col-sm-8">
									      		<textarea class="form-control" name="content" rows="5" id="content"></textarea>
									    	</div>
									  	</div>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	<button onclick="submit()" class="btn btn-default">Gửi</button>
										    </div>
										</div>
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