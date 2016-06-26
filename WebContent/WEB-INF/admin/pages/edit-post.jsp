<%@page import="com.baotoan.dev.entity.Information"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Bài viết</title>
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
			    <label for="title">Tiêu đề</label>
			    <input type="text" class="form-control" id="title" name="title" value="${post.title }">
			  </div>
			  <div class="form-group">
			    <label for="author">Ngày cập nhật</label>
			    <input type="text" class="form-control" id="publishDate" name="publishDate" value="${post.publishDate }" disabled="disabled">
			  </div>
			  <div class="form-group">
			    <label for="content">Nội dung</label>
			    <ckeditor:editor editor="editor" basePath="../resources/ckeditor" value="${post.content }"></ckeditor:editor>
				<ckfinder:setupCKEditor basePath="../resources/ckfinder" editor="editor" />
			  </div>
			  <div class="form-group">
			  	<label for="status">Trạng thái</label>
			  	<select class="form-control" id="status" name="status">
			  		<option value="false">Đang chờ</option>
			  		<option value="true">Đã duyệt</option>
			  	</select>
			  </div>
			  <button id="save" class="btn btn-default">Lưu</button> <span id="message"></span>
			  <input type="hidden" id="id" value="${post.id }">
			  <script type="text/javascript">
			  	$('#save').click(function(e) {
			  		var content = CKEDITOR.instances.editor.getData();
			  		var name = $('#title').val();
			  		var status = $('select#status').val();
			  		var id = $('#id').val();
			  		
			  		var object = {ac: "save", title: name, status: status, id: id, content: content};
			  		
			  		$.post('posts', object, function(rsp){
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
