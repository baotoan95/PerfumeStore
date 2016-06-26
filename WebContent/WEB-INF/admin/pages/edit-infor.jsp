<%@page import="com.baotoan.dev.entity.Information"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Thông tin</title>
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
			  <% Information infor = (Information)request.getAttribute("infor"); %>
			  <div class="form-group">
			    <label for="title">Tiêu đề</label>
			    <input type="text" class="form-control" id="title" name="title" value="<%= infor.getName() %>">
			  </div>
			  <div class="form-group">
			    <label for="author">Tác giả (nguồn)</label>
			    <input type="text" class="form-control" id="author" name="author" value="Manager" disabled="disabled">
			  </div>
			  <div class="form-group">
			    <label for="content">Nội dung</label>
			    <ckeditor:editor editor="editor" basePath="../resources/ckeditor" value="<%= infor.getContent() %>"></ckeditor:editor>
				<ckfinder:setupCKEditor basePath="../resources/ckfinder" editor="editor" />
			  </div>
			  <div class="form-group">
			  	<label for="status">Trạng thái</label>
			  	<select class="form-control" id="status">
			  		<option value="false" <%= infor.getStatus() == false ? "selected" : "" %>>Đang chờ</option>
			  		<option value="true" <%= infor.getStatus() ? "selected" : "" %>>Đã duyệt</option>
			  	</select>
			  </div>
			  <button id="save" class="btn btn-default">Lưu</button> <span id="message"></span>
			  <script type="text/javascript">
			  	$('#save').click(function(e) {
			  		var content = CKEDITOR.instances.editor.getData();
			  		var name = $('#title').val();
			  		var status = $('#status').val();
			  		
			  		var object = {ac: "save", name: name, status: status, id: <%= infor.getId() %>, content: content};
			  		
			  		$.post('infor', object, function(rsp){
						$('#message').html(rsp.message);
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
