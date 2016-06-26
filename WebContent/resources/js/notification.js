function notification(notif, imgUrl, type) {
	var text = notif;
	
	if(type === "default") {
		imgUrl = "images/error.png"
	}
	
	//sinh id tự động cho notification-item ngẫu nhiên
	var d = new Date();
	var idForNotif = "notif"+d.getTime();
	
	//div notification-item
	var notiContent = '<div id="'+ idForNotif +'" class="notification-item">'
							+'<div class="notification-img">'
								+'<img src="/PerfumeStore/resources/'+ imgUrl +'" alt="notification-img"/>'
							+'</div>'
							+'<div class="notification-content">'
								+'<p>'+ text +'</p>'
							+'</div>'
							+'<span onclick="closeNotifFas(this)" class="btn-close-notification"></span>'
					  +'</div>';
	$('#notification-container').append(notiContent);
	playSound('notification');
	closeNotif('#'+idForNotif);
}

//=========== Common ===================
function closeNotifFas(e) {
	$(e).parent().remove();
}

function playSound(filename){ 
	$("#sound").html('<audio autoplay="autoplay"><source src="/PerfumeStore/resources/sounds/' + filename + '.mp3" type="audio/mpeg" /><source src="/StoreDigital/resources/sounds/' + filename + '.ogg" type="audio/ogg" /><embed hidden="true" autostart="true" loop="false" src="/StoreDigital/resources/sounds/' + filename +'.mp3" /></audio>');
}

var notifTimeout;
//remove notification
function closeNotif(idForNotif) {
	notifTimeout = setTimeout(function() {
		$(idForNotif).animate({opacity:'0'}, 500).delay(1000).animate({marginTop: '0'}, 0, function(){
			$(idForNotif).remove();
		});
	}, 10000);
};

//clear timeout when hover
function saveNotif() {
	clearTimeout(notifTimeout);
}
