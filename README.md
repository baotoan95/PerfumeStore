## Giới thiệu
Website bán nước hoa

## Chức năng
- Sắp xếp sản phẩm theo thể loại.
- Giỏ hàng
- Quản lý admin
- Đăng nhập,...
- Gửi mail

## Công nghệ áp dụng
- JSP/Servlet
- JDBC connection pool
- jQuery/Ajax

## Hướng dẫn cài đặt
- Bước 1: Import perfume_world.sql
- Bước 2: Sửa thông tin truy cập vào csdl trong WebContent > META-INF > content.xml
- Bước 3: Config tomcat user trong folder Server > tomcat-users.xml. Thêm nội dung sau vào trong tag ```<tomcat-users>
    	```xml
	<role rolename="manager" />
	<role rolename="tomcat" />
	<role rolename="admin" />
	<user username="lelong" password="baotoan" roles="tomcat, manager, admin" />
	```
- Bước 4: Run project. Để truy cập và admin nhập url http://localhost:8080/PerfumeStore/admin với username: lelong và password là baotoan

## Tác giả
- [Ngô Bảo Toàn]

[//]:#
[Ngô Bảo Toàn]: <https://www.facebook.com/baotoan95>

## Lưu ý
Project được chia sẻ nhằm mục đích học hỏi, vui lòng liên hệ với tôi nếu có vấn đề gì trong project hoặc có giải pháp nào hay xin vui lòng chia sẻ. Cảm ơn!!!
