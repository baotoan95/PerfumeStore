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
    <title>Danh sách người dùng</title>
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
                        <th>Thành phố</th>
                        <th>Điện thoại</th>
                        <th>Trạng thái</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="user" items="${requestScope.listUsers }">
                	<tr>
                    	<td>${user.username }</td>
                		<td>${user.name }</td>
                		<td>${user.city }</td>
                		<td>${user.phone }</td>
                        <td>${user.status }</td>
                        <th><a href="users?ac=edit&u=${user.username }">Xem</a></th>
                        <th><a href="#" onclick="delUser('${user.username}', this)">Xóa</a></th>
               		</tr>
               		</c:forEach>
               	</tbody>
            </table>
            <script type="text/javascript">
            	function delUser(user, element) {
            		var object = {ac: 'del', username: user};
            		$.post('users', object, function(rsp){
			  			if(rsp === 'success') {
		            		$(element).parent().parent().remove();
			  				notification("Cập nhật thành công!!!","","default");
			  			} else {
			  				notification("Cập nhật chưa thành công!!!","","default");
			  			}
					});
            	}
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