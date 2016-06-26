<%@page import="com.baotoan.dev.service.ProductDAOImpl"%>
<%@page import="com.baotoan.dev.dao.ProductDAO"%>
<%@page import="com.baotoan.dev.service.OrderDAOImpl"%>
<%@page import="com.baotoan.dev.dao.OrderDAO"%>
<%@page import="com.baotoan.dev.entity.OrderDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.baotoan.dev.entity.Order"%>
<%@page import="com.baotoan.dev.entity.Information"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Đơn hàng</title>
    <jsp:include page="../templates/declare-resources.jsp"></jsp:include>
  </head>
  <body class="skin-blue sidebar-mini">
    <div class="wrapper">
      <jsp:include page="/WEB-INF/admin/templates/header.jsp"></jsp:include>
      <!-- Left side column. contains the logo and sidebar -->
      <jsp:include page="/WEB-INF/admin/templates/left-slider.jsp"></jsp:include>
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Dashboard
            <small>Control panel</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
        	<table class="table table-striped table-bordered table-condensed">
            	<thead>
                	<tr>
                    	<th>STT</th>
                        <th>Tên sản phẩm</th>
                        <th>Dung lượng</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                	<%
                	ProductDAO proDAO = new ProductDAOImpl();
                	Order order = (Order)request.getAttribute("order");
                	List<OrderDetail> listOrderDetail = order.getListOrderDetails();
                	int count = 1;
                	for(OrderDetail ord : listOrderDetail) {
                	%>
                	<tr>
                    	<td><%= count++ %></td>
                		<td><%= proDAO.getProductById(ord.getProduct().getProductId()).getName() %></td>
                		<td><%= ord.getProduct().getCapacity() %></td>
                		<td><%= ord.getQuantity() %></td>
                		<td><%= ord.getPay() %></td>
               		</tr>
               		<%
                	}
               		%>
               	</tbody>
            </table>
            <div class="form-group">
			    <label class="control-label" for="title">Tên</label>
			    <input type="text" class="form-control" id="name" name="name" value="${requestScope.order.name }">
			  </div>
			  <div class="form-group">
			    <label class="control-label" for="author">Số điện thoại</label>
			    <input type="text" class="form-control" id="phone" name="phone" value="${requestScope.order.phone }">
			  </div>
			  <div class="form-group">
			    <label class="control-label" for="author">Địa chỉ</label>
			    <input type="text" class="form-control" id="address" name="address" value="${requestScope.order.address_receiver }">
			  </div>
			  <div class="form-group">
			    <label class="control-label" for="author">Phương thức thanh toán</label>
			    <select id="payment" name="payment" class="form-control">
			    <c:forEach var="payment" items="${requestScope.payments }">
			    	<option value="${payment.id }">${payment.name }</option>
			    </c:forEach>
			    </select>
			  </div>
			  <div class="form-group">
			    <label class="control-label" for="status">Trạng thái</label>
			    <select id="status" name="status" class="form-control">
			    	<option value="1">Chưa duyệt</option>
			    	<option value="2">Đã duyệt</option>
			    	<option value="3">Đã giao</option>
			    </select>
			  </div>
			  <button data="${order.id }" id="save" class="btn btn-default">Lưu</button> <span id="message"></span>
			  <script type="text/javascript">
			  	$('#save').click(function(e) {
			  		var id = $('#save').attr('data');
			  		var name = $('#name').val();
			  		var phone = $('#phone').val();
			  		var address = $('#address').val();
			  		var payment = $('#payment').val();
			  		var status = $('#status').val();
			  		
			  		var object = {ac: "update", id: id, name: name, address: address, payment: payment, phone:phone, status: status};
			  		
			  		$.post('orders', object, function(rsp){
						$('#message').html(rsp);
					});
			  	});
			  </script>
        </section><!-- /.content -->
			  
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>
