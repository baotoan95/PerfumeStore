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
		<title>Hướng dẫn</title>
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
								<div class="news-title">Hướng dẫn mua hàng</div>
								<div class="news-content">Updating....</div>
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