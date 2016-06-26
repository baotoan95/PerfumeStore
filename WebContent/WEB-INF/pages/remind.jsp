<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html !>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="autdor" content="baotoan95">
	<jsp:include page="/WEB-INF/templates/declare-resources.jsp"></jsp:include>
	<title>Quên mật khẩu</title>
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
							<div class="news-title">Đặt lại mật khẩu</div>
							<div class="news-content">
								Vui lòng điền đầy đủ thông tin bên dưới để chúng tôi đặt lại mật khẩu cho bạn
								<div id="form">
									<form class="form-horizontal" action="UserControl" method="post">
										<span class="form-title">Thông tin bắt buộc</span><br/>
										<span class="error">${requestScope.message }</span><br/><br/>
									  	<div class="form-group">
									    	<label for="name" class="col-sm-2 col-md-offset-1 control-label">Username *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="username" name="username">
									    	</div>
									  	</div>
									  	<div class="form-group">
									    	<label for="email" class="col-sm-2 col-md-offset-1 control-label">Email *</label>
									    	<div class="col-sm-8">
									      		<input type="text" onblur="check(this)" class="form-control" id="email" name="email">
									    	</div>
									  	</div>
									  	<div class="hidden-part" style="display: none;">
									  	<div class="form-group">
									    	<label for="secureQuestion" class="col-sm-3 control-label"></label>
									    	<div class="col-sm-8"><p id="secureQuestion"></p></div>
									  	</div>
									  	<div class="form-group">
									    	<label for="secureAnswer" class="col-sm-3 control-label">Câu trả lời *</label>
									    	<div class="col-sm-8">
									      		<input onblur="check(this)" type="text" id="secureAnswer" name="answer" class="form-control"/>
									    	</div>
									  	</div>
									  	</div>
									  	<div class="form-group">
										    <div class="col-sm-offset-3 col-sm-8">
										     	<button type="submit" name="btAction" value="rqrmpass" disabled="disabled" class="btn btn-default" id="buttonRemind">Hoàn tất</button>
										    </div>
										</div>
										<script type="text/javascript">
									    	var username = document.getElementById("username");
									    	var email = document.getElementById("email");
									    	var secureAnswer = document.getElementById("secureAnswer");
									    	var buttonRemind = document.getElementById("buttonRemind");
									    	
									    	function check(element) {
									    		if(element.value.length == 0) {
									    			element.style.background = "#FFDFF7";
										    	} else {
										    		element.style.background = "#AEFFB3";
										    	}
									    		if(username.value.length > 0) {
									    			$.ajax({
									                    url  	: "UserControl",
									                    type 	: "get",
									                    dateType: "json",
									                    data 	: {
									                    	"ac" : "getQuestion",	
									                        "username" : username.value
									                    },
									                    success : function (result){
									                    	if(result.status === "ok") {
									                    		$(".error").html("Sẵn sàng nhận yêu cầu");
										                    	$("#secureQuestion").html(result.question);
										                    	$(".hidden-part").fadeIn(300);
									                    	} else {
									                    		$(".error").html(result.status);
									                    	}
									                    }
									                });
									    		}
									    		if(username.value.length > 0 && email.value.length > 0 && secureAnswer.value.length > 0) {
									    			buttonRemind.removeAttribute("disabled");
									    		} else {
									    			buttonRemind.setAttribute("disabled", "disabled");
									    		}
									    	}
									    </script>
									</form>
								</div>
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