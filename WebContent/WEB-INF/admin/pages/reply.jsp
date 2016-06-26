<%@page import="com.baotoan.dev.entity.Information"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Contact</title>
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
        	<form action="contacts" method="post">
        		<input type="hidden" name="id" value="${contact.id }">
        		<input type="hidden" name="ac" value="reply">
			  <div class="form-group">
			    <label for="name">Tên:</label>
			    ${contact.name }
			  </div>
			  <div class="form-group">
			    <label for="to">Gửi tới</label>
			    ${contact.email }
			  </div>
			  <div class="form-group">
			    <label for="content">Nội dung</label>
			    <textarea rows="10" cols="100" id="content" class="ckeditor"></textarea>
			    <input type="hidden" id="contentSubmit" name="content">
				<script type="text/javascript">
				CKEDITOR.on('instanceCreated', function(e) {
				    e.editor.on('change', function (event) {
				    	var value = CKEDITOR.instances['content'].getData();
				        $('#contentSubmit').val(value);
				    });
				}); 
				</script>
			  </div>
			  <button onclick="submit();" class="btn btn-default">Gửi</button> <span id="message"></span>
			</form>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>
