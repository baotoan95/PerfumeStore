<%@page import="java.util.List"%>
<%@page import="com.baotoan.dev.service.PostDAOImpl"%>
<%@page import="java.util.Map"%>
<%@page import="com.baotoan.dev.entity.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Banner information -->
<div id="banner-info-box">
	<a href="#"><img src="resources/images/directional/deliver.png" class="img-responsive" alt="Banner infor" /></a> 
	<a href="#"><img src="resources/images/directional/guide.png" class="img-responsive" alt="Banner infor" /></a> 
	<a href="#"><img src="resources/images/directional/xxx.png" class="img-responsive" alt="Banner infor" /></a> 
	<a href="#"><img src="resources/images/directional/gift.png" class="img-responsive" alt="Banner infor" /></a> 
	<a href="#"><img src="resources/images/directional/for-oto.png" class="img-responsive" alt="Banner infor" /></a>
</div>

<!-- News -->
<div id="news-box">
	<div class="news-box-title">Tin tức</div>
	<div class="news-box-content">
		<ul>
		<%
			Map<String, Object> result = new PostDAOImpl().getPosts(1, 8);
			List<Post> listPost = (List<Post>)result.get("data");
			for(Post post : listPost) {
		%>
			<li><a href="post.html?id=<%= post.getId() %>"><%=post.getTitle() %></a></li>
		<% 
			}
		%>
		<li><a href="#">Xem thêm</a></li>
		</ul>
	</div>
</div>
<!-- End news box -->

<!-- Partner -->
<div id="partner-box">
	<div class="partner-box-title">Đối tác liên kết</div>
	<div class="partner-box-content">
		<img alt="Partner" src="resources/images/partner/doji.png" class="img-responsive" /> 
		<img alt="Partner" src="resources/images/partner/kienlong.png" class="img-responsive" />
		<img alt="Partner" src="resources/images/partner/kaloo.png" class="img-responsive" /> 
		<img alt="Partner" src="resources/images/partner/standar.png" class="img-responsive" />
		<img alt="Partner" src="resources/images/partner/tienphong.png" class="img-responsive" /> 
		<img alt="Partner" src="resources/images/partner/vp.png" class="img-responsive" />
	</div>
</div>
<!-- End .partner-box -->