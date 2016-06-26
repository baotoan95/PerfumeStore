<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Người dùng</title>
    <jsp:include page="/WEB-INF/admin/templates/declare-resources.jsp"></jsp:include>
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
			  <div class="form-group">
			    <label for="username">Tên tài khoản</label>
			    <input type="text" class="form-control" ${not empty requestScope.user.username ? "disabled" : "" }
			     id="username" name="username" value="${requestScope.user.username }">
			  </div>
			  <div class="form-group">
			    <label for="password">Mật khẩu</label>
			    <input type="text" ${not empty requestScope.user.username ? "disabled" : "" } class="form-control" id="password" name="password" value="${requestScope.user.password }">
			  </div>
			  <div class="form-group">
			    <label for="name">Tên</label>
			    <input type="text" class="form-control" id="name" name="name" value="${requestScope.user.name }">
			  </div>
			  <div class="form-group">
			    <label for="address">Địa chỉ</label>
			    <input type="text" class="form-control" id="address" name="address" value="${requestScope.user.address }">
			  </div>
			  <div class="form-group">
			    <label for="city">Thành phố</label>
			    <input type="text" class="form-control" id="city" name="city" value="${requestScope.user.city }">
			  </div>
			  <div class="form-group">
			    <label for="phone">Số điện thoại</label>
			    <input type="text" class="form-control" id="phone" name="phone" value="${requestScope.user.phone }">
			  </div>
			  <div class="form-group">
			    <label for="score">Điểm</label>
			    <input type="text" class="form-control" id="score" name="score" value="${requestScope.user.score }">
			  </div>
			  <div class="form-group">
			    <label for="registryDate">Ngày đăng ký</label>
			    <input type="text" class="form-control" id="registryDate" name="registryDate" value="${requestScope.user.registryDate }">
			  </div>
			  <div class="form-group">
			    <label for="email">Email</label>
			    <input type="text" class="form-control" id="email" name="email" value="${requestScope.user.email }">
			  </div>
			  <div class="form-group">
			    <label for="secureQuest">Câu hỏi bảo mật</label>
			    <textarea class="form-control" id="secureQuest" name="secureQuest">${requestScope.user.secureQuestion }</textarea>
			  </div>
			  <div class="form-group">
			    <label for="secureAns">Câu trả lời bảo mật</label>
			    <textarea class="form-control" id="secureAns" name="secureAns">${requestScope.user.secureAnswer }</textarea>
			  </div>
			  <div class="form-group">
			    <label for="role">Vai trò</label>
			    <select class="form-control" name="role" id="role">
			    	<c:forEach var="role" items="${requestScope.listRole }">
			  		<option value="${role.id }">${role.name }</option>
			  		</c:forEach>
			  	</select>
			  </div>
			  <div class="form-group">
			  	<label for="status">Trạng thái</label>
			  	<select class="form-control" name="status" id="status">
			  		<option value="false">Chưa kích hoạt</option>
			  		<option value="true">Đã kích hoạt</option>
			  	</select>
			  </div>
			  <button id="save" action='${(not empty requestScope.user) ? "save" : "add" }' class="btn btn-default">Lưu</button> <span id="message"></span>
			  <script type="text/javascript">
			  	$('#save').click(function(e) {
			  		var username = $('#username').val();
			  		var pass = $('#password').val();
			  		var name = $('#name').val();
			  		var address = $('#address').val();
			  		var city = $('#city').val();
			  		var phone = $('#phone').val();
			  		var score = $('#score').val();
			  		var registryDate = $('#registryDate').val();
			  		var email = $('#email').val();
			  		var secureQuest = $('#secureQuest').val();
			  		var secureAns = $('#secureAns').val();
			  		var role = $('#role').val();
			  		var status = $('#status').val();
			  		
			  		var action = '';
			  		if($('#save').attr('action') === 'save') {
			  			action = 'save';
			  		} else {
			  			action = 'add';
			  		}
			  		
			  		var object = {ac: action, username: username, password: pass, name: name, address: address, city: city,
			  				phone: phone, score: score, registryDate: registryDate, email: email, secureQuest: secureQuest,
			  				secureAns: secureAns, role: role, status: status};
			  		
			  		$.post('users', object, function(rsp){
			  			if(rsp === 'success') {
			  				notification("Cập nhật thành công!!!","","default");
			  			} else {
			  				notification("Cập nhật chưa thành công!!!","","default");
			  			}
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
