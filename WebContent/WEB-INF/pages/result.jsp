<%@page import="com.baotoan.dev.service.BrandDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.baotoan.dev.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html!>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="baotoan95">
<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
<title>Thế Giới Nước Hoa</title>
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
						<!-- Slider -->
						<div id="slider">
							<div id="carousel" class="carousel slide" data-ride="carousel">
							  	<!-- Indicators -->
							  	<ol class="carousel-indicators">
							    	<li data-target="#carousel" data-slide-to="0" class="active"></li>
							    	<li data-target="#carousel" data-slide-to="1"></li>
							  	</ol>
							
							  	<!-- Wrapper for slides -->
							  	<div class="carousel-inner" role="listbox">
							    	<div class="item active">
							      		<img src="resources/images/sliders/don-hang.png" class="img-responsive" style="width: 100%; height: 100%;" alt="...">
							      		<div class="carousel-caption"> </div>
							    	</div>
								    <div class="item">
								      	<img src="resources/images/sliders/qua-tang.png" class="img-responsive" style="width: 100%; height: 100%;" alt="...">
								      	<div class="carousel-caption"> </div>
								    </div>
							  	</div>
							
							  	<!-- Controls -->
							  	<a class="left carousel-control" href="#carousel" role="button" data-slide="prev">
							    	<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							    	<span class="sr-only">Previous</span>
							  	</a>
							  	<a class="right carousel-control" href="#carousel" role="button" data-slide="next">
							    	<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							    	<span class="sr-only">Next</span>
							  	</a>
							</div>
						</div> <!-- End slider -->
						
						<!-- Marquee -->
						<div id="marquee">
							<marquee behavior="scroll" direction="left" scrollamount="5" onmouseover="this.stop();" onmouseout="this.start();">
								<span style="color: white;">Thưởng Thức Các Món Tráng Miệng Tại</span> <span style="color: yellow">VUA TRÁNG MIỆNG - MR:DEE </span>(&nbsp;<a style="color: #fff;" href="http://www.thegioinuochoa.com.vn" onclick="window.open('http://muachung.vn/am-thuc-nha-hang/am-thuc-vua-trang-mieng-mr-dee-109987.html', '_blank', 'toolbar=yes, scrollbars=yes, resizable=yes, width=800, height=600'); return false">Xem Chi Tiết)</a>
							</marquee>
						</div>
						
							<c:if test="${empty requestScope.listProduct }">
								<div class="error">Không có sản phẩm nào</div>
							</c:if>
							<c:if test="${not empty requestScope.listProduct }">
						<div id="product">
							<div class="title col-sm-12"> 
								<span class="pull-left title-left">${requestScope.find }</span>
							</div>
							<div class="product-content">
								<c:forEach var="product" items="${requestScope.listProduct }">
								<!-- product item -->
								<div class="col-sm-3 product-item">
									<div class="images">
										<span class="icon-info"></span>
										<div class="img-product">
											<img alt="Product" data-original="resources/${product.avatar }" class="img-responsive lazy">
										</div>
									</div>
									<div class="info">
										<a href="product.html?id=${product.id }" class="info-name">${product.name }</a><br/>
										<a href="brand.html?ac=view&id=${product.category.brand.id }" class="info-band">${product.category.brand.name }</a>
										<p class="info-price">${product.price } VND</p>
										<div class="btn-func">
											<a onclick="event.preventDefault();" href="#"><i p_name="${product.name }" p_img="${product.avatar }" onclick="like(this, ${product.id }, 'add')" class="glyphicon glyphicon-heart"></i></a>
										</div>
									</div>
								</div> <!-- End product item -->
								</c:forEach>
							</div>
						</div>
						<div class="menu-right">
							<ul>
								<%= request.getAttribute("pagination") %>
							</ul>
						</div>
						</c:if>
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