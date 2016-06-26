<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.baotoan.dev.entity.Cart"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					</div>
					<!-- End .left-content -->

					<!-- Right content -->
					<div class="col-sm-9 primary right-content">
						<div class="content-w">
							<div class="news-title">Giỏ Hàng >> Thông Tin Đơn Hàng</div>
							<div class="news-content">
								<div class="form-group">
									<div class="col-sm-12">
										<table class="table">
											<thead>
												<tr>
													<th>Thông tin người đặt hàng</th>
													<th>Thông tin người nhận hàng</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Họ tên: ${sessionScope.order.name }</td>
													<td>Họ tên: ${sessionScope.order.name_receiver }</td>
												</tr>
												<tr>
													<td>Điện thoại: ${sessionScope.order.phone }</td>
													<td>Điện thoại: ${sessionScope.order.phone_receiver }</td>
												</tr>
												<tr>
													<td>Địa chỉ: ${sessionScope.order.address }</td>
													<td>Địa chỉ: ${sessionScope.order.address_receiver }</td>
												</tr>
												<tr>
													<td>Tỉnh/Thành phố: ${sessionScope.order.city.name }</td>
													<td>Tỉnh/Thành phố: ${sessionScope.order.city_receiver.name }</td>
												</tr>
												<tr>
													<td colspan="2">Email: ${sessionScope.order.email }</td>
												</tr>
												<tr>
													<td colspan="2">Ghi chú: ${sessionScope.order.note }</td>
												</tr>
												<tr>
													<td colspan="2">Thanh toán: ${sessionScope.order.payment.name }</td>
												</tr>
											</tbody>
										</table>

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
													Map<Integer, Cart> carts = (HashMap<Integer, Cart>) session
															.getAttribute("carts");
													Iterator<Integer> keys = carts.keySet().iterator();
													int totalPay = 0;
													for (int i = 0; i < carts.size(); i++) {
														Cart cart = (Cart) carts.get(keys.next());
												%>
												<tr>
													<td><img class="img-responsive img-small"
														src="${pageContext.request.contextPath }/resources/<%= cart.getProduct().getImageUrl() %>"></td>
													<td><%=cart.getProductName()%></td>
													<td><%=cart.getProduct().getCapacity()%></td>
													<td><%=cart.getProduct().getPrice()%></td>
													<td><input type="text" value="<%=cart.getQuantity()%>"
														size="1" /></td>
													<td><%=cart.getProduct().getPrice() * cart.getQuantity()%></td>
													<%
														totalPay += (cart.getProduct().getPrice() * cart.getQuantity());
													%>
													<th><a href="#" p_name="<%=cart.getProductName()%>"
														p_img="<%=cart.getProduct().getImageUrl()%>"
														onclick="event.preventDefault(); buy(this, <%=cart.getProduct().getId()%>, 0, 'del');"><i
															class="glyphicon glyphicon-trash"></i></a></th>
												</tr>
												<%
													}
												%>
												<tr>
													<td colspan="3"></td>
													<td colspan="2">Tiền hàng</td>
													<td id="totalPay"><%=totalPay%></td>
													<td></td>
												</tr>
												<tr>
													<td colspan="3">(*) Phụ thu 25-30 nghìn phí giao hàng
														cho đơn hàng <= 600.000đ <br />Quý khách xem thêm Chính
														sách giao hàng tại đây (<a href="redirect?l=policy">chi
															tiết</a>)
													</td>
													<td colspan="2">Phí vận chuyển</td>
													<td id="deliverPay"></td>
													<td></td>
												</tr>
												<tr>
													<td colspan="3"></td>
													<td colspan="2">Tổng đơn hàng</td>
													<td id="totalOrderPay"><%=totalPay%></td>
													<td></td>
												</tr>
											</tbody>
										</table>
										<div class="form-group">
											<div class="col-sm-offset-10 col-sm-1">
												<a href="order?ac=confirm" class="btn btn-default">Hoàn Thành</a>
											</div>
										</div>
									</div>
								</div>
								<ul>
									<li>Sau khi nhận được đơn hàng của bạn, Thế Giới Nước Hoa
										sẽ gọi điện xác nhận và giao hàng cho bạn trong vòng 24h.</li>
									<li>Thế Giới Nước Hoa gói quà và chuyển phát nhanh miễn
										phí trên toàn quốc. (Xem Phương thức giao hàng)</li>
									<li>Thế Giới Nước Hoa cam kết:
										<ul>
											<li class="col-sm-offset-1">Hàng chính hãng 100%.</li>
											<li class="col-sm-offset-1">Chịu trách nhiệm xuất hóa
												đơn VAT (không thu phí xuất hóa đơn)</li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End content -->

		<!-- Footer -->
		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
		<!-- End footer -->
	</div>
</body>
</html>