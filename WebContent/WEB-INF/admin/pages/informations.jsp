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
                    	<th>STT</th>
                        <th>Tiêu đề</th>
                        <th>Tình trạng</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<%
                	List<Information> listInfo = (List<Information>)request.getAttribute("listInfor");
                	for(int i = 0; i < listInfo.size(); i++) {
                		Information infor = (Information)listInfo.get(i);
                	%>
                	<tr style="height: 20px;">
                    	<td><%= i %></td>
                		<% 
                			String name = infor.getName();
                			if(name.length() > 50) {
                				name = name.substring(0, 49).trim();
                			}
                		%>
                		<td><%= name %></td>
                        <td><%= infor.getStatus() %></td>
                        <th><a href="${pageContext.request.contextPath }/admin/infor?ac=edit&id=<%= infor.getId() %>">Xem</a></th>
               		</tr>
               		<%
                	}
               		%>
               	</tbody>
            </table>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <jsp:include page="/WEB-INF/admin/templates/footer.jsp"></jsp:include>
    </div><!-- ./wrapper -->
    <jsp:include page="/WEB-INF/admin/templates/declare-resources-footer.jsp"></jsp:include>
  </body>
</html>