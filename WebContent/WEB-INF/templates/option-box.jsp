<%@page import="com.baotoan.dev.entity.Brand"%>
<%@page import="com.baotoan.dev.service.BrandDAOImpl"%>
<%@page import="com.baotoan.dev.dao.BrandDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Options -->
<div id="option-box">
	<div class="box-option-title">Tùy chọn</div>
	<div class="box-option-content">
		<select onchange="submitBrand(this.value)" class="form-control brand-select">
			<option>Chọn nhãn hiệu</option>
			<%
			BrandDAO brandDAO = new BrandDAOImpl();
			for(Brand brand : brandDAO.getAll()) {
			%>
			<option value="<%= brand.getId() %>"><%= brand.getName() %></option>
			<%
			}
			%>
		</select>
		<select onchange="submitPrice(this.value)" class="form-control price-select">
			<option value="0">Tìm theo giá</option>
			<option value="1">Dưới 300.000</option>
			<option value="2">300.000 - 600.000</option>
			<option value="3">600.000 - 900.000</option>
			<option value="4">Trên 900.000</option>
		</select>
		<script type="text/javascript">
			function submitBrand(value) {
				window.location.href='search.html?for=manuf&manuf=' + value;
			}
			function submitPrice(value) {
				window.location.href='search.html?for=price&p=' + value + '&page=1';
			}
		</script>
	</div>

	<div class="box-title">Tìm theo kí tự</div>
	<div class="search-alphabet">
		<img class="alphabet-select" class="img-responsive" usemap="#MapS" />
		<map name="MapS" id="MapS">
			<area shape="rect" coords="184,62,209,88" href="search.html?for=pre&pre=@&page=1" alt="">
			<area shape="rect" coords="184,34,209,60" href="search.html?for=pre&pre=r&page=1" alt="">
			<area shape="rect" coords="159,61,184,87" href="search.html?for=pre&pre=z&page=1" alt="">
			<area shape="rect" coords="159,32,184,58" href="search.html?for=pre&pre=q&page=1" alt="">
			<area shape="rect" coords="138,62,157,88" href="search.html?for=pre&pre=y&page=1" alt="">
			<area shape="rect" coords="138,33,157,59" href="search.html?for=pre&pre=p&page=1" alt="">
			<area shape="rect" coords="114,61,139,87" href="search.html?for=pre&pre=x&page=1" alt="">
			<area shape="rect" coords="114,32,139,58" href="search.html?for=pre&pre=o&page=1" alt="">
			<area shape="rect" coords="96,33,112,61" href="search.html?for=pre&pre=n&page=1" alt="">
			<area shape="rect" coords="96,60,112,88" href="search.html?for=pre&pre=w&page=1" alt="">
			<area shape="rect" coords="69,61,94,87" href="search.html?for=pre&pre=v&page=1" alt="">
			<area shape="rect" coords="69,32,94,58" href="search.html?for=pre&pre=m&page=1" alt="">
			<area shape="rect" coords="45,62,70,88" href="search.html?for=pre&pre=u&page=1" alt="">
			<area shape="rect" coords="45,33,70,59" href="search.html?for=pre&pre=l&page=1" alt="">
			<area shape="rect" coords="29,60,45,87" href="search.html?for=pre&pre=t&page=1" alt="">
			<area shape="rect" coords="29,33,45,60" href="search.html?for=pre&pre=k&page=1" alt="">
			<area shape="rect" coords="2,32,27,58" href="search.html?for=pre&pre=j&page=1" alt="">
			<area shape="rect" coords="2,60,27,86" href="search.html?for=pre&pre=s&page=1" alt="">
			<area shape="rect" coords="184,4,209,30" href="search.html?for=pre&pre=i&page=1" alt="">
			<area shape="rect" coords="159,4,184,30" href="search.html?for=pre&pre=h&page=1" alt="">
			<area shape="rect" coords="138,4,157,30" href="search.html?for=pre&pre=g&page=1" alt="">
			<area shape="rect" coords="114,4,139,30" href="search.html?for=pre&pre=f&page=1" alt="">
			<area shape="rect" coords="96,2,112,30" href="search.html?for=pre&pre=e&page=1" alt="">
			<area shape="rect" coords="69,4,94,30" href="search.html?for=pre&pre=d&page=1" alt="">
			<area shape="rect" coords="45,4,70,30" href="search.html?for=pre&pre=c&page=1" alt="">
			<area shape="rect" coords="29,3,45,30" href="search.html?for=pre&pre=b&page=1" alt="">
			<area shape="rect" coords="2,4,27,30" href="search.html?for=pre&pre=a&page=1" alt="">
			<area shape="rect" coords="140,59,141,60" href="search.html?for=pre&pre=&page=1" alt="">
		</map>
	</div>
</div>
<!-- End option -->