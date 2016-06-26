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
<title>Tư vấn</title>
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
							<div class="news-title">Giới thiệu</div>
							<div class="news-content">
								<b>1. Giới thiệu</b>
								<p>Thế Giới Nước Hoa được thành lập vào năm 2007, nhờ sự yêu mến, 
								quan tâm của đông đảo khách hàng gần xa nên chỉ sau hơn 5 năm ra đời, 
								Thế Giới Nước Hoa đã trở thành một địa chỉ mua sắm nước hoa, mỹ phẩm cao cấp, 
								chính hãng quen thuộc và đáng tin cậy của khách hàng trên cả nước.</p>


								<p>Với hệ thống 11 cửa hàng được đặt tại trung tâm các thành phố lớn, 
								mỗi ngày thu hút gần 1500 lượt khách đến tham quan và mua sắm; 
								website trực tuyến có hơn 6000 lượt truy cập mỗi ngày, 
								Thế Giới Nước Hoa chính thức trở thành doanh nghiệp phân phối nước hoa và mỹ phẩm chính hãng số 1 tại Việt Nam. 
								Không dừng lại ở đó, Thế Giới Nước Hoa còn có tham vọng vươn xa hơn nữa, 
								nhằm mang những hương thơm quyến rũ đến với tất cả mọi người trên khắp cả nước trong thời gian sắp tới.</p>
								<div class="show-image">
									<img alt="9.jpg" src="${pageContext.request.contextPath }/resources/images/show/9.jpg" class="img-responsive">
									<div class="img-des">Showroom với phong cách chuyên nghiệp từ cách trang trí bên ngoài...</div>
								</div>
								<p>Hiện nay, Thế Giới Nước Hoa có hàng ngàn sản phẩm nước hoa cao cấp của 
								hầu hết những thương hiệu thời trang trứ danh như Chanel, Christian Dior, 
								Bvlgari, Gucci, Victoria's Secret, Lancôme, Hugo Boss…, 
								đáp ứng đầy đủ nhu cầu sử dụng nước hoa ngày càng cao của mọi đối 
								tượng khách hàng. Thế Giới Nước Hoa được khách hàng mệnh danh là 
								“Thiên đường của những mùi hương” bởi những điểm ưu việt mà tại Việt Nam 
								chưa có một địa chỉ bán nước hoa, mỹ phẩm nào sánh được: sản phẩm cực kỳ đa dạng, 
								giá cả rất cạnh tranh, chất lượng sản phẩm đảm bảo chính hãng, đặc biệt, 
								đội ngũ nhân viên xinh đẹp được đào tạo một cách bài bản, chuyên nghiệp, 
								sẵn sàng phục vụ hết mình vì sự hài lòng cao nhất của khách hàng.</p>
								<div class="show-image">
									<img alt="DSC02320.JPG" src="resources/images/show/DSC02320.JPG" class="img-responsive">
									<div class="img-des">... đến cách trưng bày sản phẩm bên trong...</div>
								</div>
								<b>2. Vì sao nên mua hàng ở Thế Giới Nước Hoa?</b><br/><br/>
								<b>a. Sản phẩm phong phú, đa dạng</b><br/><br/>
								<p>Với hơn 1500 loại nước hoa của hầu hết các thương hiệu nổi tiếng trên thế giới 
								đang có mặt tại Thế Giới Nước Hoa, Quý khách dễ dàng chọn được cho mình sản phẩm ưng ý nhất. 
								Trong xu hướng lựa chọn nước hoa ngày nay, những sản phẩm mới nhất, có hương thơm tuyệt nhất, 
								thiết kế độc đáo được mọi người đặc biệt yêu thích. 
								Do đó, Thế Giới Nước Hoa luôn cập nhật những dòng nước hoa mới nhất trên thị trường để 
								đáp ứng nhu cầu của Quý khách hàng.</p>
								<div class="show-image">
									<img alt="s6-1.jpg" src="resources/images/show/s6-1.jpg" class="img-responsive">
									<div class="img-des">Hàng ngàn sản phẩm nước hoa cao cấp tại Thế Giới Nước Hoa</div>
								</div>
								<b>b. Chất lượng sản phẩm và dịch vụ tốt</b>
								<p>Tất cả các sản phẩm đang được bán tại Thế Giới Nước Hoa là hàng nhập khẩu chính hãng, 
								có hóa đơn, chứng từ, trên mỗi sản phẩm đều có tem chống hàng giả. 
								Thế Giới Nước Hoa làm yên tâm khách hàng bằng chế độ bảo hành mùi hương hợp lý, 
								Quý khách có thể đổi hoặc trả hàng nếu thật sự không hài lòng 100% về chất lượng sản phẩm. 
								Ngoài ra, Thế Giới Nước Hoa còn có dịch vụ gói quà và giao hàng miễn phí đến tận tay khách hàng 
								trên toàn quốc một cách nhanh chóng nhất.</p>
								<b>c. Đội ngũ nhân viên với phong cách làm việc chuyên nghiệp</b>
								<p>Đội ngũ nhân viên bán hàng và tư vấn viên của Thế Giới Nước Hoa trẻ trung, 
								năng động, được đào tạo bài bản với phong cách làm việc chuyên nghiệp, nhiệt tình. 
								Nếu Quý khách lần đầu sử dụng nước hoa hoặc chưa tự tin chọn cho mình lọ nước hoa phù hợp hoặc 
								Quý khách muốn thay đổi, làm mới bản thân bằng những mùi hương mới, nhân viên của Thế Giới Nước Hoa 
								sẵn sàng hỗ trợ hết mình để Quý khách chọn được sản phẩm vừa ý nhất.</p>
								<div class="show-image">
									<img alt="2.jpg" src="resources/images/show/2.jpg" class="img-responsive">
									<div class="img-des">Hình ảnh trẻ trung, năng động của nhân viên bán hàng</div>
								</div>
								<b>d. Giá cả cạnh tranh</b>
								<p>Đến với Thế Giới Nước Hoa, Quý khách hàng có cơ hội lựa chọn những sản phẩm nước hoa chính hiệu 
								với mức giá hợp lý, phù hợp với túi tiền của mọi người. 
								Thế Giới Nước Hoa cam kết cung cấp cho Quý khách nước hoa chất lượng tốt nhất với giá cạnh tranh 
								trên thị trường.</p>
								<div class="show-image">
									<img alt="s7.3.jpg" src="resources/images/show/s7.3.jpg" class="img-responsive">
									<div class="img-des">Thế Giới Nước Hoa được rất đông khách hàng yêu mến và tin tưởng</div>
								</div>
								<b>e. Có hệ thống showroom để xem sản phẩm và thử mùi</b>
								<p>Các showroom của Thế Giới Nước Hoa được đặt tại những con đường thời trang của 
								trung tâm các thành phố lớn như  Tp. Hồ Chí Minh, Hà Nội, Hải Phòng, rất thuận 
								tiện cho khách hàng đến tham quan và mua sắm. Tại đây, Quý khách hàng được tự do 
								lựa chọn sản phẩm và thử mùi đến khi chọn được lọ nước hoa có kiểu dáng và hương thơm 
								ưng ý nhất. Showroom mở cửa từ 8h đến 22h liên tục 7 ngày trong tuần.</p>
								<b>f. Đặt hàng qua website trực tuyến dễ dàng, nhanh chóng</b>
								<p>Website chính thức của công ty www.thegioinuochoa.com.vn đăng tải hình ảnh, 
								mô tả, giá cả của hàng ngàn loại nước hoa và chi tiết về hệ thống showroom của Thế Giới Nước Hoa. 
								Chỉ với một cái click chuột vào website, Quý khách hàng dễ dàng tìm được sản phẩm mình yêu thích, 
								biết về các loại nước hoa mới nhất trên thị trường và đặt hàng nhanh chóng. 
								Dù đang ở đâu, vào bất cứ lúc nào trong ngày, 
								Quý khách cũng có thể đặt hàng và nhận hàng trong thời gian sớm nhất. 
								Nhanh chóng, miễn phí, đáng tin cậy, đặt hàng trực tuyến là phương cách 
								tốt nhất nếu bạn không có thời gian hoặc ở xa các cửa hàng của Thế Giới Nước Hoa.</p>
								<div class="show-image">
									<img alt="s6-4.jpg" src="resources/images/show/s6-4.jpg" class="img-responsive">
									<div class="img-des">Sự chuyên nghiệp của nhân viên tư vấn</div>
								</div>
								<p>Bằng bản lĩnh và khát vọng xây dựng một thương hiệu Việt mang phong cách quốc tế, 
								Thế Giới Nước Hoa đã và đang nỗ lực không ngừng để mang đến cho khách hàng những sản phẩm 
								và chất lượng dịch vụ tốt nhất.</p>
							</div>
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

