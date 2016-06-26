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
    <title>Danh sách đơn hàng chưa duyệt</title>
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
			<table class="table table-striped table-bordered table-hover table-condensed">
            	<thead>
                	<tr>
                    	<th>STT</th>
                        <th>Tên</th>
                        <th>Số điện thoại</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<tr style="height: 20px;">
                    	<td>2</td>
                		<td>Nguyễn Văn A</td>
                		<td>123435346</td>
                		<td>123.234 VND</td>
                		<td>Chưa duyệt</td>
                        <th><a href="${pageContext.request.contextPath }/admin/orders?ac=edit&id=1">Xem</a></th>
                        <th>Xóa</th>
               		</tr>
               		<tr style="height: 20px;">
                    	<td>2</td>
                		<td>Nguyễn Văn A</td>
                		<td>123435346</td>
                		<td>123.234 VND</td>
                		<td>Chưa duyệt</td>
                        <th><a href="${pageContext.request.contextPath }/admin/orders?ac=edit&id=1">Xem</a></th>
                        <th>Xóa</th>
               		</tr>
               		<tr style="height: 20px;">
                    	<td>2</td>
                		<td>Nguyễn Văn A</td>
                		<td>123435346</td>
                		<td>123.234 VND</td>
                		<td>Chưa duyệt</td>
                        <th><a href="${pageContext.request.contextPath }/admin/orders?ac=edit&id=1">Xem</a></th>
                        <th>Xóa</th>
               		</tr>
               		<tr style="height: 20px;">
                    	<td>2</td>
                		<td>Nguyễn Văn A</td>
                		<td>123435346</td>
                		<td>123.234 VND</td>
                		<td>Chưa duyệt duyệt</td>
                        <th><a href="${pageContext.request.contextPath }/admin/orders?ac=edit&id=1">Xem</a></th>
                        <th>Xóa</th>
               		</tr>
               		<tr style="height: 20px;">
                    	<td>2</td>
                		<td>Nguyễn Văn A</td>
                		<td>123435346</td>
                		<td>123.234 VND</td>
                		<td>Chưa duyệt</td>
                        <th><a href="${pageContext.request.contextPath }/admin/orders?ac=edit&id=1">Xem</a></th>
                        <th>Xóa</th>
               		</tr>
               	</tbody>
            </table>
            <div class="menu-right">
									<ul>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">Next</a></li>
									</ul>
								</div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>