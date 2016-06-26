<%@page import="com.baotoan.dev.entity.Like"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html!>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="autdor" content="baotoan95">
<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
<style type="text/css">
	tr td {
		font-weight: normal;
		font-size: 0.8em;
	}
</style>
<title>Mục ưu thích</title>
</head>
<body>
	<div id="wrapper">
		<!-- Header -->
		<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
		<!-- End header -->
		
		<!-- Content -->
		<div id="content">
			<div class="container">
				<div class="row">
					<!-- Navigation -->
					<jsp:include page="/WEB-INF/templates/main-navigation.jsp"></jsp:include>
					<!-- End navigation -->
					
					<!-- Left content -->
					<div class="col-sm-3 primary left-content">
						<jsp:include page="/WEB-INF/templates/option-box.jsp"></jsp:include>
						<jsp:include page="/WEB-INF/templates/left-side.jsp"></jsp:include>
					</div> <!-- End .left-content -->
					
					<!-- Right content -->
					<div class="col-sm-9 primary right-content">
						<div class="content-w">
							<div class="news-title">Danh mục ưa thích</div>
							<%
							@SuppressWarnings({ "unchecked" })
							Map<Integer, Like> likes = (HashMap<Integer, Like>)session.getAttribute("likes"); 
							if(null != likes && likes.size() > 0) {
							%>
							<table class="table table-striped table-bordered table-condensed list-products custom-table">
				            	<thead>
				                	<tr>
				                        <th>Hình</th>
				                        <th>Tên sản phẩm</th>
				                        <th>Xóa</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<%
				                	Iterator<Integer> keys = likes.keySet().iterator();
				                	int totalPay = 0;
				                	for(int i = 0; i < likes.size(); i++) {
				                		Like like = (Like)likes.get(keys.next());
				                	%>
				                	<tr>
				                		<td><img class="img-responsive img-small" src="${pageContext.request.contextPath }/resources/<%= like.getProduct().getAvatar() %>"></td>
				                        <td><a href="product.html?id=<%= like.getProduct().getId() %>"><%= like.getProduct().getName() %></a></td>
				                        <th><a href="#" p_img="<%= like.getProduct().getAvatar() %>" onclick="event.preventDefault(); like(this, <%= like.getProduct().getId() %>, 0, 'del');"><i p_name="<%= like.getProduct().getName() %>" class="glyphicon glyphicon-trash"></i></a></th>
				               		</tr>
				               		<%
				                	}
				               		%>
				               	</tbody>
				            </table>
				            <%
							} else {
				            %>
								<div class="error">Không có sản phẩm nào trong mục ưa thích</div>				            	
				            <%
							}
				            %>
						</div>
					</div>
				</div> 
			</div>
		</div> <!-- End content -->
		
		<!-- Footer -->
		<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
		<!-- End footer -->
	</div>
</body>
</html>