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
    <title>Danh sách khuyến mãi</title>
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
                        <th>Banner</th>
                        <th>Tiêu đề</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="promotion" items="${requestScope.listPromotions }">
                	<tr>
                    	<td>${promotion.id }</td>
                    	<td><img class="img-responsive img-small" src="${pageContext.request.contextPath }/${promotion.imageUrl }"></td>
                		<td>${promotion.title }</td>
                        <td>${promotion.startDate }</td>
                        <td>${promotion.endDate }</td>
                        <th><a href="promotions?ac=w&id=${promotion.id }">Xem</a></th>
                        <th><a href="" data="${promotion.id }" class="delete">Xóa</a></th>
               		</tr>
               		</c:forEach>
               	</tbody>
            </table>
            <script>
            	$('.delete').click(function(e) {
            		var item = (this);
			  		var id = $(this).attr('data');
			  		var object = {id: id, ac: "del"};
			  		
			  		$.post('promotions', object, function(rsp){
			  			if("ok" === rsp) {
			  				$(item).parent().parent().remove();
			  			} else {
			  				alert("Chưa xóa thành công");
			  			}
					});
			  	});
            </script>
            <div class="menu-right">
				<ul>${requestScope.pagination }</ul>
			</div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>