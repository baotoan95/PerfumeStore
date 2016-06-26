<%@page import="com.baotoan.dev.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.baotoan.dev.service.CategoryDAOImpl"%>
<%@page import="com.baotoan.dev.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="nav-wrapper">
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-menu">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> <span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
			</div>

			<div class="navbar-collapse collapse" id="main-menu">
				<ul class="nav navbar-nav">
	            	<li><a href="home.html">Trang chủ</a></li>
	              	<li><a href="#">Cho Nữ<span class="caret"></span></a>
	              		<ul class="dropdown-menu">
	              		<%
	              			CategoryDAO cateDAO = new CategoryDAOImpl();
	              			List<Category> forWomen = cateDAO.getCategoriesByIntendedId(2);
	              			for(Category cate : forWomen) {
	              		%>
		                  	<li><a href="brand.html?ac=view&id=<%= cate.getBrand().getId() %>"><%= cate.getBrand().getName() %></a></li>
		                <%
	              			}
		                %>
	                	</ul>
	              	</li>
	            	<li><a href="#">Cho Nam<span class="caret"></span></a>
		              	<ul class="dropdown-menu">
		              	<%
			              	List<Category> forMen = cateDAO.getCategoriesByIntendedId(2);
	              			for(Category cate : forMen) {
		              	%>
		                  	<li><a href="brand.html?ac=view&id=<%= cate.getBrand().getId() %>"><%= cate.getBrand().getName() %></a></li>
		                <%
	              			}
		                %>
		                </ul>
	              	</li>
	              	<li><a href="${pageContext.request.contextPath }/redirect?l=intro">Giới thiệu</a></li>
	              	<li><a href="advisory.html">Tư vấn</a></li>
	              	<li><a href="contact.html">Liên hệ</a></li>
	            </ul>

				<!-- Search form -->
				<div id="search-form" class="navbar-right col-md-3">
					<form class="input-group" action="search.html" method="POST">
						<input type="hidden" value="tk" name="for">
						<input type="hidden" value="1" name="page">
				    	<input type="text" name="tk" class="form-control" placeholder="Tên nước hoa">
				      	<span class="input-group-btn">
				        	<button onclick="submit()" class="btn btn-default glyphicon glyphicon-search" id="btn-search" type="button"></button>
				      	</span>
				    </form>
				</div>
				<!-- End search form -->
			</div>
		</div>
	</div>
</div>