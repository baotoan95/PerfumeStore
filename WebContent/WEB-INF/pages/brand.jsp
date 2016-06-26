<%@page import="com.baotoan.dev.entity.Brand"%>
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
<meta name="autdor" content="baotoan95">
<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
<title>Thương hiệu</title>
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
						<div id="present">
							<div class="breadcrumbs">
								<ul>
									<li><a href="#">Trang chủ</a></li>
									<li><a href="#">Nhãn hiệu</a></li>
									<li><a href="#">Dành cho</a></li>
									<li><a href="#">Tên sản phẩm</a></li>
								</ul>
							</div>
							<div class="present-content">
								<div class="intro">
									<div class="left-intro">
										<div class="avatar">
											<img alt="Avatar" class="img-responsive" src="resources/${requestScope.brand.avatarUrl }">
										</div>
									</div>
									<div class="right-intro">
										<div class="brand">
											<img alt="Brand" src="resources/${requestScope.brand.poster }" class="img-responsive">
										</div>
										<div class="into-content">
											<p>
												${requestScope.brand.description }
											</p>
										</div>
									</div>
								</div>
								<div class="comment">
									<div class="fb-comments" data-href="https://www.facebook.com/worldofperfumeforyou/" data-width="100%" data-numposts="5"></div>
								</div>
							</div>
						</div>
						<div id="product">
							<div class="title col-sm-12"> 
								<span class="pull-left title-left">Danh sách sản phẩm</span>
							</div>
							<div class="product-content">
								<%
								List<Product> listProduct = (List<Product>)request.getAttribute("listProduct");
								for(Product product : listProduct) {
								%>
								<!-- product item -->
								<div class="col-sm-3 product-item">
									<div class="images">
										<span class="icon-info"></span>
										<div class="img-product">
											<img alt="Product" data-original="resources/<%= product.getAvatar() %>" class="img-responsive lazy">
										</div>
									</div>
									<div class="info">
										<a href="product.html?id=<%= product.getId() %>" class="info-name"><%= product.getName() %></a><br/>
										<a href="brand.html?ac=view&id=<%= product.getCategory().getBrand().getId() %>" class="info-band"><%= product.getCategory().getBrand().getName() %></a>
										<p class="info-price"><%= product.getListDetail().get(0).getPrice() %> VND</p>
										<div class="btn-func">
											<a onclick="event.preventDefault();" href="#"><i p_name="<%= product.getName() %>" p_img="<%= product.getAvatar() %>" onclick="like(this, <%= product.getId() %>, 'add')" class="glyphicon glyphicon-heart"></i></a>
										</div>
									</div>
								</div> <!-- End product item -->
								<%
								}
								%>
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