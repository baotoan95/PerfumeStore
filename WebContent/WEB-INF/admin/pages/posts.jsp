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
    <title>Danh sách bài viết</title>
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
                        <th>Tiêu đề</th>
                        <th>Ngày cập nhật</th>
                        <th>Tình trạng</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="post" items="${requestScope.listPosts }">
                	<tr>
                    	<td>${post.id }</td>
                		<td>${post.title }</td>
                		<td>${post.publishDate }</td>
                        <td>${post.status }</td>
                        <th><a href="posts?ac=edit&id=${post.id }">Xem</a></th>
                        <th><a href="#" data="${post.id }" class="delete">Xóa</a></th>
               		</tr>
               		</c:forEach>
               	</tbody>
            </table>
            <script>
            	$('.delete').click(function(e) {
            		var item = (this);
			  		var id = $(this).attr('data');
			  		var object = {id: id, ac: "del"};
			  		
			  		$.post('posts', object, function(rsp){
			  			if("success" == rsp.message) {
			  				alert(rsp.message);
			  				$(item).parent().parent().remove();
			  			} else {
			  				alert(rsp.message);
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