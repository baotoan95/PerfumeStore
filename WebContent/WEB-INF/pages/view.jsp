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
<meta property="fb:app_id" content="1527867264204402">
<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
<title>${requestScope.product.name }</title>
<style type="text/css">
	.fb_iframe_widget,
	.fb_iframe_widget span,
	.fb_iframe_widget span iframe[style] {
	  min-width: 100% !important;
	  width: 100% !important;
	}
</style>
</head>
<body>
	<div id="fb-root"></div>
	<!-- Facebook comment -->
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=1527867264204402";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	<!-- End facebook plugin -->
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
												<img alt="Avatar" class="img-responsive" src="resources/${requestScope.product.avatar }">
											</div>
											<span class="views">Số lượt xem: ${requestScope.product.views } lượt</span>
											<div class="like"></div>
										</div>
										<div class="right-intro">
											<div class="brand">
												<img alt="Brand" src="resources/${requestScope.product.category.brand.poster }" class="img-responsive">
											</div>
											<div class="into-content">
												<p>
													<b>${requestScope.product.name }</b> ${requestScope.product.descript }
												</p>
											</div>
										</div>
									</div>
									
									<div class="detail">
										<table class="table table-responsive custom-table">
											<thead>
							    	        	<tr>
						                            <td>Dung tích</td>
						                            <td>Giá TGNH</td>
						                            <td>Giá thị trường</td>
						                            <td>Tiết kiệm</td>
								                    <td>Mua hàng</td>
							                    </tr>
						                    </thead>
						                    <tbody>
							                    <c:forEach var="proD" items="${requestScope.product.listDetail }">
							                    <tr>
							                        <td>${proD.capacity }</td>
							                        <td>${proD.price } VND</td>
							                        <td>${proD.market_price } VND</td>
							                        <td>${proD.market_price - proD.price } VND</td>
							                   		<td><a href="#" onclick="event.preventDefault();"><img p_name="${requestScope.product.name }" p_img="${proD.imageUrl }" onclick="buy(this, ${proD.id}, 1, 'add')" src="resources/images/gh.gif" alt="Mua"></a></td>
							                	</tr>
							                	</c:forEach>
						                	</tbody>
						                </table>
									</div>
								</div>
							</div>
							<!-- comment faceobok -->
							<div class="fb-comments fb_iframe_widget" data-href="" data-numposts="10" data-colorscheme="light" data-width="620" id="fb-cmt" fb-xfbml-state="rendered">
							<script type="text/javascript">
								$('.fb-comments').attr('data-href', window.location.href);
							</script>
							<!-- end comment facebook -->
							<div id="product">
								<div class="title col-sm-12"> 
									<span class="pull-left title-left">Các sản phẩm cùng loại</span>
									<span class="pull-right title-right"><a href="#">Xem tất cả</a></span>
								</div>
								<div class="product-content">
									<%
									List<Product> listRelateds = (List<Product>)request.getAttribute("listRelateds");
									for(Product product : listRelateds) {
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
											<a href="product.html?id=<%= product.getId() %>" class="info-band"><%= product.getCategory().getBrand().getName() %></a>
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