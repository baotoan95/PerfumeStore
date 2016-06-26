<%@page import="com.baotoan.dev.entity.Product"%>
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
    <title>Danh sách sản phẩm</title>
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
                    	<th>ID</th>
                        <th>Hình mẫu</th>
                        <th>Tên</th>
                        <th>Ngày cập nhật</th>
                        <th>Lượt xem</th>
                        <th>Lượt mua</th>
                        <th>Xem/Sửa</th>
                        <th>Xóa</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	List<Product> listProduct = (List<Product>)request.getAttribute("listProduct");
                	for(Product product : listProduct) {
                %>
                	<tr>
                    	<td><%= product.getId() %></td>
                		<td><img class="img-responsive img-small" src="${pageContext.request.contextPath }/resources/<%= product.getAvatar() %>"></td>
                        <td><%= product.getName() %></td>
                        <td><%= product.getUpdateDay() %></td>
                        <td><%= product.getViews() %></td>
                        <td><%= product.getSellCount() %></td>
                        <th><a href="product?ac=w&id=<%= product.getId() %>"><i class="glyphicon glyphicon-list-alt"></i></a></th>
                        <th><a href=""><i class="glyphicon glyphicon-trash"></i></a></th>
               		</tr>
               	<%
                	}
               	%>
               	</tbody>
            </table>
            <div class="menu-right">
				<ul>
					<%= request.getAttribute("pagination") %>
				</ul>
			</div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>