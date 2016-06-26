<%@page import="com.baotoan.dev.entity.Information"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Thể loại</title>
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
			    <label for="name">Tên thể loại</label>
			    <select class="form-control" id="brand" name="brand">
			  		<c:forEach var="brand" items="${requestScope.rands }">
			  		<option value="${brand.id }"  ${brand.id == requestScope.category.brand.id ? "selected" : "" }>${brand.name }</option>
			  		</c:forEach>
			  	</select>
			    <input type="hidden" id="cateId" name="cateId" value="${requestScope.category.id }"> 
			  </div>
			  <div class="form-group">
			  	<label for="intended">Dành cho</label>
			  	<select class="form-control" id="intended" name="intended">
			  		<c:forEach var="intended" items="${requestScope.intendeds }">
			  		<option value="${intended.id }"  ${intended.id == requestScope.category.intended.id ? "selected" : "" }>${intended.name }</option>
			  		</c:forEach>
			  	</select>
			  </div>
			  <button id="save" action='${not empty requestScope.category ? "save" : "add" }' class="btn btn-default">Lưu</button> <span id="message"></span>
			  <script type="text/javascript">
			  	$('#save').click(function(e) {
			  		var brand = $('#brand').val();
			  		var intended = $('#intended').val();
			  		var action = $(this).attr('action');
			  		var cateId = $('#cateId').val();
			  		
			  		var object = {ac: action, brand: brand, intended: intended, cateId: cateId};
			  		
			  		$.post('categories', object, function(rsp){
			  			if(rsp === 'ok') {
							notification("Cập nhật thành công", "", "default");
			  			} else {
			  				notification("Cập nhật chưa thành công", "", "default");
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
