$(function() {
	
	/*$('#letterNum').click(function(){
		window.location = WEBROOT + '/merchant/toMessage.do';
	});
	$('#refundNum').click(function(){
		window.location = WEBROOT + '/merchant/toRefundMessage.do';
	});
	$('#pushOrderNum').click(function(){
		window.location = WEBROOT + '/merchant/toMerchantAdvance.do';
	});*/
	
	$('#oldPassword').maxLength(20);
	$('#newPassword').maxLength(20);
	$('#repeatNewPassword').maxLength(20);
	$('#oldPassword').keyup(function(){
	    var password = $('#oldPassword').val();
	    $('#oldPassword').val(password.replace(/[\W]/g,''));
	  });
	$('#newPassword').keyup(function(){
	    var password = $('#newPassword').val();
	    $('#newPassword').val(password.replace(/[\W]/g,''));
	  });	
	$('#repeatNewPassword').keyup(function(){
	    var password = $('#repeatNewPassword').val();
	    $('#repeatNewPassword').val(password.replace(/[\W]/g,''));
	  });
  
	if(CHECK.isEmpty($("#textIntro").html())){
		$("#textIntro").html("暂无简介");
	}
	
	
	
	//点击开启编辑
	$('#showmodifyIntro-BTN').click(function(){
		var value = $('#textIntro').html();
		//添加遮罩层
		var maskWidth = $(document).width();
		var maskHeight = $(document).height();
		$('<div class="mask"></div>').appendTo($('body'));
		$('.mask').css({
			"opacity":"0.4",
			"background":"grey",
			"position":"absolute",
			"left":0,
			"top":0,
			"width":maskWidth,
			"height":maskHeight,
			"z-index":1
		});
		$('.showEdit').show();
		$('.showEdit').find('textarea').val(value);
		
	});
	//取消编辑
	$('.showEdit .cancel').click(function(){
		$('.showEdit').hide();
		$('.mask').remove();
	});
	//保存编辑
	$('.showEdit .sure').click(function(){
		var text = $('.showEdit').find('textarea').val();
		/*提交简介修改*/
		if(CHECK.isEmpty(text)){
			alert("请输入简介内容")
			return false;
		}
		$.ajax({
			url: WEBROOT + "/merchant/modifyIntro.do",
			type: 'post',
			dataType: 'json',
			data:{
				newIntro:text
			},
			/*dataFilter : {
				s : [ 'newIntro']
			},*/
			success: function(res) {
				if (res.code == AJAX_SUCCESS_ALERT_CODE) {
					$('#textIntro').text(text);						
				}else if(res.code ==  AJAX_SESSION_TIMEOUT_CODE ){
					alert("登录信息超时，请重新登录后再操作！");
					return;
				}	
				else {
					alert(res.msg);
				}
			},
			error: function(res) {
				alert("网络连接异常，请稍后重试");
			}
		});		
		$('.showEdit').hide();
		$('.mask').remove();
		
	});
	//编辑中字体个数限制
	var maxLength = 200;
	$('.showEdit').find('textarea').keyup(function(){
		var len = $(this).val().length;
		$('.showEdit').find('strong').text(maxLength-len);
		if((maxLength-len)<0){
			$('.showEdit').find('strong').text('0');
			var val = $(this).val().substring(0,200);
			$(this).val(val);
		}
	});
	
	//点击修改密码
	$('#showModifyPass-BTN').click(function(){
		//添加遮罩层
		var maskWidth = $(document).width();
		var maskHeight = $(document).height();
		$('<div class="mask"></div>').appendTo($('body'));
		$('.mask').css({
			"opacity":"0.4",
			"background":"grey",
			"position":"absolute",
			"left":0,
			"top":0,
			"width":maskWidth,
			"height":maskHeight,
			"z-index":1
		});
		$('.modifyPassword').show();
		
	});
	//取消修改密码
	$('.modifyPassword .cancel').click(function(){
		$('#oldPassword').val("");
		$('#newPassword').val("");
		$('#repeatNewPassword').val("");
		$('.modifyPassword').hide();
		$('.mask').remove();
	});
	//提交新密码到后台
	$('#modifyPass-BTN').click(function(){
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var repeatNewPassword = $("#repeatNewPassword").val();
		//alert(oldPassword);
		if( CHECK.isPassword(oldPassword) && CHECK.isPassword(newPassword) &&
				CHECK.isPassword(repeatNewPassword) && repeatNewPassword == newPassword){
			$.ajax({				
				url : WEBROOT + '/merchant/modifypassword.do',
				type : 'post',
				data:{
					oldPassword : oldPassword,
					newPassword : newPassword,
					repeatNewPassword : repeatNewPassword
				},
				
				dataFilter : {
					s : ['oldPassword', 'newPassword', 'repeatNewPassword']
				},
				dataType : 'json',
				success : function(res) {
					if (res.code == AJAX_SUCCESS_ALERT_CODE) {
						alert("密码修改成功");
						$('#oldPassword').val("");
						$('#newPassword').val("");
						$('#repeatNewPassword').val("");
						$('.modifyPassword').hide();
						$('.mask').remove();
					} else if(res.code == AJAX_SESSION_TIMEOUT_CODE){
						alert("登录信息超时，请重新登录后再操作！");
						return;
					}else {
						alert(res.msg);
					}
				},
				error : function() {
					alert("密码修改失败，请重新修改");
				}
				
			});
		}else{
			alert("密码格式不正确,请输入6-20位的数字或字母");
			return false;
		}		
	});
	
	
});



