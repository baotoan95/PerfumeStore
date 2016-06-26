<%@page import="com.baotoan.dev.entity.Order"%>
<%@page import="com.baotoan.dev.entity.Information"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng</title>
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
            <li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
            <li class="active">Dashboard</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
        	<% if(((List<Order>)request.getAttribute("listOrders")).size() > 0) { %>
			<table class="table table-striped table-bordered table-condensed">
            	<thead>
                	<tr>
                    	<th>ID</th>
                        <th>Tên</th>
                        <th>Số điện thoại</th>
                        <th>Ngày đặt</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="order" items="${requestScope.listOrders }">
                	<tr>
                    	<td>${order.id }</td>
                		<td>${order.name }</td>
                		<td>${order.phone }</td>
                		<td>${order.createDate }</td>
                		<td>${order.totalPay } VND</td>
                		<td>${order.status.name }</td>
                        <th><a href="${pageContext.request.contextPath }/admin/orders?ac=edit&id=${order.id}">Xem</a></th>
                        <th><a href="#" data="${order.id }" class="delete">Xóa</a></th>
               		</tr>
               		</c:forEach>
               	</tbody>
            </table>
            <script type="text/javascript">
            	$('.delete').click(function(e) {
            		var element = $(this);
            		var object = {ac: 'del', id: $(this).attr('data')};
            		$.post('orders', object, function(rsp){
			  			if(rsp === 'ok') {
		            		$(element).parent().parent().remove();
			  				notification("Cập nhật thành công!!!","","default");
			  			} else {
			  				notification("Cập nhật chưa thành công!!!","","default");
			  			}
					});
            	});
            </script>
            <div class="menu-right">
				<ul>
					${requestScope.pagination }
				</ul>
			</div>
			<%} else {%>
			<div class="error">
        		Trống
        	</div>
        	<%} %>
			
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>