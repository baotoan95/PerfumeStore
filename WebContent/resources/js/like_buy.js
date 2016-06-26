function like(element, productId, act) {
	var productName = $(element).attr('p_name');
	var imageUrl = $(element).attr('p_img');
	$(document).ready(function() {
		$.ajax({
			type: "GET",
			data: {
				"productID" : productId,
				"ac" : act
			},
			url: "LikeControl",
			dataType: "JSON",
			success: function(data) {
				if(data.status != 0 && data.status != -1) {
					if(act === "add") {
						notification('Bạn vừa thêm ' + productName + ' vào mục ưa thích', imageUrl, 'notif');
					} else {
						$(element).parent().parent().remove();
					}
					$('.numOfLikes').html('Mục ưa thích (' + data.status + ')');
				} else  if(data.status == -1) {
					notification('Có lỗi, vui lòng thử lại hoặc liên hệ với chúng tôi!', "", 'notif');
				} else {
					if(act === "add") {
						notification('Sản phẩm đã tồn tại trong mục ưa thích!', '', 'default');
					} else {
						$('.numOfLikes').html('Mục ưa thích (' + data.status + ')');
						$('table').remove();
						$('.content-w').append('<div class="error">Không có sản phẩm nào trong mục ưa thích</div>');
					}
				}
			}
		});
	});
}

function buy(element, productId, quantity, act) {
	var productName = $(element).attr('p_name');
	var imageUrl = $(element).attr('p_img');
	if(act === 'update') {
		quantity = $(element).val();
	}
	$(document).ready(function() {
		$.ajax({
			type: "GET",
			data: {
				"productID" : productId,
				"quantity" : quantity,
				"productName" : productName,
				"ac" : act
			},
			url: "CartControl",
			dataType: "JSON",
			success: function(data) {
				if(data.status != 0) {
					if(act === "add") {
						$('#cart-container').html('Có (' + data.status + ') sản phẩm');
						notification('Bạn vừa thêm ' + productName + ' vào giỏ hàng', imageUrl, 'notif');
					} else if(act === 'del') {
						updateCart(element, data);
					} else if(act === 'update') {
						if(data.func === 'remove') {
							$(element).parent().parent().remove();
						}
						$('#cart-container').html('Có (' + data.status + ') sản phẩm');
						$('#totalPay').html(data.totalPay);
						var totalOrderPay = parseInt(data.totalPay) + (isNaN(parseInt($('#deliverPay').html()) ? 0 : 0));
						$('#totalOrderPay').html(totalOrderPay);
						$(element).parent().next().html(data.pay);
					}
				} else {
					if(act === "add") {
						notification('Sản phẩm đã tồn tại trong giỏ hàng!', '', 'default');
					} else {
						updateCart(element, data);
						$('table').remove();
						$('#redirect').remove();
						$('.content-w').append('<div class="error">Không có sản phẩm nào trong giỏ hàng của bạn</div>');
					}
				}
			}
		});
	});
}

function updateCart(element, data) {
	$(element).parent().parent().remove();
	$('#totalPay').html(data.totalPay);
	$('#cart-container').html('Có (' + data.status + ') sản phẩm');
	var totalOrderPay = parseInt(data.totalPay) + (isNaN(parseInt($('#deliverPay').html()) ? 0 : 0));
	$('#totalOrderPay').html(totalOrderPay);
}