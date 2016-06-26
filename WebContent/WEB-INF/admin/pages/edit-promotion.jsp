<%@page import="com.baotoan.dev.entity.Information"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Khuyến mãi</title>
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
        	<form action="promotions" method="post" id="form-ajax" enctype="multipart/form-data">
			  <div class="form-group">
			    <label for="title">Tiêu đề</label>
			    <input type="text" class="form-control" id="title" name="title" value="${promotion.title }">
			    <input type="hidden" name="proId" value="${promotion.id }"/>
			  </div>
			  <div class="form-group">
			    <label for="author">Mô tả</label>
			    <input type="text" class="form-control" id="desc" name="desc" value="${promotion.descript }">
			  </div>
			  <div class="form-group">
			    <label for="author">Hình ảnh</label>
			    <input id="avatar" type="file" name="avatar" class="form-control">
			    <div id="preview"></div>
			    <script type="text/javascript">
					$('#avatar').change(function () {
						if (this.files && this.files[0]) {
				            var reader = new FileReader();
				            reader.onload = function (e) {
				                $('#preview').html('<img style=\"display: block; width: 300px !important; height: 300px !important; margin: 0px auto;\" class=\"img-responsive\" src=\"' + e.target.result + '\">');
				            }
				            reader.readAsDataURL(this.files[0]);
			        	}
					});
				</script>
			  </div>
			  <div class="form-group">
			    <label for="author">Ngày bắt đầu</label>
			    <input type="text" class="form-control" id="startDate" name="startDate" value="${promotion.startDate }">
			  </div>
			  <div class="form-group">
			    <label for="author">Ngày kết thúc</label>
			    <input type="text" class="form-control" id="endDate" name="endDate" value="${promotion.endDate }">
			  </div>
			  <div class="form-group">
			    <label for="content">Nội dung</label>
			    <textarea rows="5" cols="100" id="content" class="ckeditor"></textarea>
			    <input type="hidden" id="contentSubmit" name="content">
				<input type="hidden" name="ac" value='${not empty requestScope.promotion ? "save" : "add"}'>
				<script type="text/javascript">
				CKEDITOR.on('instanceCreated', function(e) {
				    e.editor.on('change', function (event) {
				    	var value = CKEDITOR.instances['content'].getData();
				        $('#contentSubmit').val(value);
				    });
				}); 
				</script>
			  </div>
			  <button id="save"  class="btn btn-default">Lưu</button> <span id="message"></span>
			 </form>
			  <script type="text/javascript">
			  		$('#form-ajax').ajaxForm({
			            success: function(msg) {
			                if(msg==='ok') {
			                	notification("Cập nhật thành công","","default");
			                } else {
			                	notification("Vui lòng điền đầy đủ thông tin","","default");
			                }
			            },
			            error: function(msg) {
			            	notification("Lỗi hệ thống","","default");
			            }
			        });
			  	
			  </script>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>
