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
        <div style="color: red;" class="form-group">
        	${requestScope.message }
        </div>
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
			  <div class="form-group">
			    <label for="name">Tên:</label>
			    ${contact.name }
			    <input type="hidden" name="id" value="${promotion.id }"/>
			  </div>
			  <div class="form-group">
			    <label for="author">Email:</label>
			    ${contact.email }
			  </div>
			  <div class="form-group">
			    <label for="author">Điện thoại:</label>
			    ${contact.phone }
			  </div>
			  <div class="form-group">
			    <label for="author">Ngày nhận:</label>
			    ${contact.sentDate }
			  </div>
			  <div class="form-group">
			    <label for="content">Nội dung</label>
			    <textarea disabled rows="5" class="form-control" cols="100" id="content">${contact.content }</textarea>
			  </div>
			  <div class="form-group">
			    <label for="status">Trạng thái</label>
			    <select name="status" class="form-control">
			    	<option value="false">Chưa Xem</option>
			    	<option value="true">Đã xem</option>
			    </select>
			  </div>
			  <input type="hidden" name="contactId" value="${contact.id }">
			  <button onclick="submit();" class="btn btn-default">Lưu</button> <span id="message"></span>
			</form>
			<a href="contacts?ac=reply&id=${contact.id }" class="btn btn-default">Trả lời</a>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>
