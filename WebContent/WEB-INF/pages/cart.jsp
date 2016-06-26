<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.baotoan.dev.entity.Cart"%>
<%@page import="java.util.Map"%>
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
<style type="text/css">
	tr td {
		font-weight: normal;
		font-size: 0.8em;
	}
</style>
<title>Giỏ hàng</title>
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
							<div class="news-title">Giỏ hàng của bạn</div>
							<%
							@SuppressWarnings({ "unchecked" })
							Map<Integer, Cart> carts = (HashMap<Integer, Cart>)session.getAttribute("carts"); 
							if(null != carts && carts.size() > 0) {
							%>
							<table class="table table-striped table-bordered table-condensed custom-table">
				            	<thead>
				                	<tr>
				                        <th>Hình</th>
				                        <th>Tên sản phẩm</th>
				                        <th>Dung tích</th>
				                        <th>Giá</th>
				                        <th>Số lượng</th>
				                        <th>Thành tiền</th>
				                        <th>Xóa</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<%
				                	Iterator<Integer> keys = carts.keySet().iterator();
				                	int totalPay = 0;
				                	for(int i = 0; i < carts.size(); i++) {
				                		Cart cart = (Cart)carts.get(keys.next());
				                	%>
				                	<tr>
				                		<td><img class="img-responsive img-small" src="${pageContext.request.contextPath }/resources/<%= cart.getProduct().getImageUrl() %>"></td>
				                        <td><%= cart.getProductName() %></td>
				                        <td><%= cart.getProduct().getCapacity() %></td>
				                        <td><%= cart.getProduct().getPrice() %></td>
				                        <td><input type="text" onblur="event.preventDefault(); buy(this, <%= cart.getProduct().getId() %>, 0, 'update');" value="<%= cart.getQuantity() %>" size="1"/></td>
				                        <td><%= cart.getProduct().getPrice() * cart.getQuantity() %></td>
				                        <% totalPay += (cart.getProduct().getPrice() * cart.getQuantity()); %>
				                        <th><a href="#" p_name="<%= cart.getProductName() %>" p_img="<%= cart.getProduct().getImageUrl() %>" onclick="event.preventDefault(); buy(this, <%= cart.getProduct().getId() %>, 0, 'del');"><i class="glyphicon glyphicon-trash"></i></a></th>
				               		</tr>
				               		<%
				                	}
				               		%>
				               		<tr>
				               			<td colspan="3"></td>
				                        <td colspan="2">Tiền hàng</td>
				                        <td id="totalPay"><%= totalPay %></td>
				                        <td></td>
				               		</tr>
				               		<tr>
				               			<td colspan="3">
				               			(*) Phụ thu 25-30 nghìn phí giao hàng cho đơn hàng <= 600.000đ 
										<br/>Quý khách xem thêm Chính sách giao hàng tại đây (<a href="redirect?l=policy">chi tiết</a>)
										</td>
				                        <td colspan="2">Phí vận chuyển</td>
				                        <td id="deliverPay"></td>
				                        <td></td>
				               		</tr>
				               		<tr>
				               			<td colspan="3"></td>
				                        <td colspan="2">Tổng đơn hàng</td>
				                        <td id="totalOrderPay"><%= totalPay %></td>
				                        <td></td>
				               		</tr>
				               	</tbody>
				            </table>
				            <div id="redirect" style="float: right; margin-right: 10px;">
					            <a href="home.html" class="btn btn-primary">Quay lại cửa hàng</a>
					            <a href="order?ac=order" class="btn btn-success">Đặt hàng</a>
				            </div>
				            <%
							} else {
				            %>
								<div class="error">Không có sản phẩm nào trong giỏ hàng của bạn</div>				            	
				            <%
							}
				            %>
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