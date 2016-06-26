<%@page import="com.baotoan.dev.controller.SessionCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="footer">
	<div class="container">
		<div class="row footer-top">
			<div class="col-sm-3">
				<!-- Statistic -->
				<div id="statistic-box">
					<%
					SessionCounter sessionCounter = (SessionCounter)session.getAttribute("counter");
					int onlineVistor = 1;
					int totalVistor = 1;
					if(null != sessionCounter) {
						onlineVistor = sessionCounter.getStatistic();
						totalVistor = sessionCounter.getTotalVistor();
					}
					%>
					<span class="statistic">Tổng số lượng truy cập: <%= totalVistor %></span><br /> 
					<span class="statistic">Đang trực tuyến: <%= onlineVistor %></span>
				</div>
				<!-- End statistic -->
			</div>
			<div class="col-sm-9">
				<!-- Links -->
				<div class="links col-sm-12">
					<ul>
						<li><a href="home.html">Trang chủ</a></li>
						<li><a href="${pageContext.request.contextPath }/redirect?l=intro">Giới thiệu</a></li>
						<li><a href="#">Phân phối sỉ</a></li>
						<li><a href="#">Thanh toán</a></li>
						<li><a href="#">Giao hàng</a></li>
						<li><a href="#">Khuyến mãi</a></li>
						<li><a href="contact.html">Liên hệ</a></li>
						<li><a href="#">Tuyển dụng</a></li>
					</ul>
				</div>
				<!-- End links -->
			</div>
		</div>
	</div>

	<div class="col-sm-12 footer-content">
		<div class="container">
			<div class="row">
				<!-- Footer links -->
				<div id="footer-links">
					<div class="col-sm-2 info-item">
						<div class="title">Giới thiệu</div>
						<div class="content">
							<ul>
								<li><a href="${pageContext.request.contextPath }/redirect?l=intro">Giới thiệu về TGNH</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=standpoint">Quan điểm kinh doanh</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=copyright">Bản quyền và sở hữu</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=provision">Điều khoản sử dụng</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2 info-item">
						<div class="title">Chính sách công ty</div>
						<div class="content">
							<ul>
								<li><a href="${pageContext.request.contextPath }/redirect?l=policy">Chính sách giao hàng</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=policy">Chính sách bảo hành</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=policy#">Chính sách đổi trả</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=partner">Đối tác Liên kết</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2 info-item">
						<div class="title">Trợ giúp</div>
						<div class="content">
							<ul>
								<li><a href="${pageContext.request.contextPath }/redirect?l=guide">Hướng dẫn mua hàng</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=payment">Phương thức thanh toán</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=gift">Gói quà miễn phí</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-2 info-item">
						<div class="title">Thông tin khác</div>
						<div class="content">
							<ul>
								<li><a href="${pageContext.request.contextPath }/redirect?l=news">Thông tin báo chí</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=membercard">Member card</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=giftvoucher">Gift voucher</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=promotion">Khuyến mãi</a></li>
								<li><a href="${pageContext.request.contextPath }/redirect?l=recruit">Tuyến dụng</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-4 info-item" style="padding-top: 10px;">
						<div class="fb-page" data-href="https://www.facebook.com/worldofperfumeforyou" data-width="250" 
						data-height="250" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" 
						data-show-facepile="true" data-show-posts="false"><div class="fb-xfbml-parse-ignore">
						<blockquote cite="https://www.facebook.com/worldofperfumeforyou">
						<a href="https://www.facebook.com/worldofperfumeforyou">World Of Perfume</a>
						</blockquote></div></div>
					</div>
				</div>
				<!-- End footer-links -->

				<!-- Information -->
				<div id="information">
					<div class="col-sm-6">
						<div class="title">
							<div class="infor">www.thegioinuochoa.com.vn</div>
						</div>
						<div class="info">
							<p>Copyright &copy; 2007 - 2014 Công ty TNHH THẾ GIỚI NƯỚC HOA.</p>
							<p>Giấy chứng nhận ĐKKD số: 0306063631 do Sở Kế Hoạch và Đầu Tư TPHCM cấp lần đầu ngày 02/10/2008.</p>
							<p>Địa chỉ: 259 Nguyễn Trãi, Phường Nguyễn Cư Trinh, Quận 1, TP Hồ Chí Minh</p>
							<p>Điện thoại: 08 6678 5621</p>
						</div>
					</div>

					<div class="col-sm-6 com-mem">
						<div class="title">
							<div class="infor">Công ty thành viên</div>
						</div>
						<div class="info">
							<img style="background: yellow; float: left;" alt="mem" src="resources/images/com-mem/mrdee.png" class="img-responsive">
							<img style="background: green; float: right;" alt="mem" src="resources/images/com-mem/xaxa.png" class="img-responsive">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Notification -->
	<div id="notification-container"></div>
	<div id="sound"></div>
</div>