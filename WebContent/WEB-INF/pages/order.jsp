<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.baotoan.dev.entity.Cart"%>
<%@page import="java.util.Map"%>
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
							<div class="news-title">Giỏ Hàng >> Thông Tin Đơn Hàng</div>
							<div class="news-content">
								Vui lòng điền đầy đủ các thông tin dưới đây.
								<div id="form">
									<form class="form-horizontal" action="order?ac=order" method="post">
										<span class="form-title">Thông tin cơ bản</span>
									  	<div class="form-group">
									    	<label for="fullName" class="col-sm-2 col-md-offset-1 control-label">Họ tên *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="fullName" name="fullName">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="phone" class="col-sm-2 col-md-offset-1 control-label">Số điện thoại *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="phone" name="phone">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="address" class="col-sm-2 col-md-offset-1 control-label">Địa chỉ</label>
									    	<div class="col-sm-8">
									      		<input type="text" class="form-control" id="address" name="address">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="city" class="col-sm-2 col-md-offset-1 control-label">Thành phố</label>
									    	<div class="col-sm-8">
									    		<select class="form-control" id="city" name="city">
									    		<c:forEach var="city" items="${requestScope.listCity }">
									    			<option value="${city.id }">${city.name }</option>
									    		</c:forEach>
									    		</select>
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="email" class="col-sm-2 col-md-offset-1 control-label">Email *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="email" name="email">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<div class="col-sm-12">
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
								                	@SuppressWarnings({ "unchecked" })
								                	Map<Integer, Cart> carts = (HashMap<Integer, Cart>)session.getAttribute("carts"); 
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
								                        <td><input type="text" value="<%= cart.getQuantity() %>" size="1"/></td>
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
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="sale" class="col-sm-2 col-md-offset-1 control-label">Mã khuyến mãi</label>
									    	<div class="col-sm-8">
									      		<input type="text" class="form-control" id="sale" name="sale">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="note" class="col-sm-2 col-md-offset-1 control-label">Ghi chú</label>
									    	<div class="col-sm-8">
									      		<textarea class="form-control" rows="4" id="note" name="note"></textarea>
									    	</div>
									  	</div>
									  	
									  	<span class="form-title">Thông tin giao hàng</span>
									  	<div class="form-group">
									    	<div class="col-sm-offset-3 col-sm-8">
									      		<input id="useDiff" onclick="check()" type="checkbox"/> Sử dụng thông tin khác thông tin trên
									    	</div>
									  	</div>
									  	<script type="text/javascript">
									  		function check() {
									  			if($("#useDiff").is(':checked')) {
									  				$('#different').show(300);
									  			} else {
									  				$('#different').hide(300);
									  			}
									  		}
									  	</script>
									  	<div id="different" style="display: none;">
										  	<div class="form-group">
										    	<label for="phone_receiver" class="col-sm-2 col-md-offset-1 control-label">Số điện thoại *</label>
										    	<div class="col-sm-8">
										      		<input type="text" onblur="check(this)" class="form-control" id="phone_receiver" name="phone_receiver">
										    	</div>
										  	</div>
										  	<div class="form-group">
										    	<label for="address_receiver" class="col-sm-2 col-md-offset-1 control-label">Địa chỉ</label>
										    	<div class="col-sm-8">
										      		<input type="text" class="form-control" id="address_receiver" name="address_receiver">
										    	</div>
										  	</div>
										  	<div class="form-group">
										    	<label for="city_receiver" class="col-sm-2 col-md-offset-1 control-label">Thành phố</label>
										    	<div class="col-sm-8">
										    		<select class="form-control" id="city_receiver" name="city_receiver">
										    		<c:forEach var="city" items="${requestScope.listCity }">
									    				<option value="${city.id }">${city.name }</option>
									    			</c:forEach>
										    		</select>
										    	</div>
										  	</div>
									  	</div>
									  	<span class="form-title">Hình thức thanh toán</span>
									  	<div class="form-group">
									    	<c:forEach var="payment" items="${requestScope.payments }">
									    	<div class="col-md-offset-1 col-sm-3">
									    		<input type="radio" value="${payment.id }" name="payment"> ${payment.name } :
									    	</div>
									    	<div class="col-sm-7">
									    		${payment.descript }
									    	</div>
									    	</c:forEach>
									  	</div>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	<button type="submit" name="ac" value="order" class="btn btn-default" id="buttonRegis">Tiếp tục</button>
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