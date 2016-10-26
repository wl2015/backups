$(function() {
	$('.vertifyPic').attr("src", WEBROOT+"/res/img/num"+Math.floor(Math.random()*(10-1)+1)+".png");
	$('#vertifyCode').maxLength(1);
	$('#vertify-phone').maxLength(11);
	$('#vertify-input').maxLength(6);	
	$('.vertifyPic').click(function(){
		$(this).attr("src", WEBROOT+"/res/img/num"+Math.floor(Math.random()*(10-1)+1)+".png");
	});
	$('#vertifyCode').blur(function(){
		var txtvertify = $('#vertifyCode').val();
		var picUrl = $('.vertifyPic').attr('src');
		if(picUrl.indexOf(txtvertify) < 1){
			alert("你的校验码输入错误");
		}		
	});
	//发送手机验证码
	$(".vertify-BTN").click(function clickCheckCode() {
		var $buttonNode = $(this);
		var buttonVal = $buttonNode.val();
		var phoneNode = $("#vertify-phone");
		var phoneNum = phoneNode.val();
		var txtvertify = $('#vertifyCode').val();
		var picUrl = $('.vertifyPic').attr('src');
		
		if(picUrl.indexOf(txtvertify) < 1){
			alert("你的校验码输入错误");
			return;
		}
		if (!CHECK.isPhoneNumber(phoneNum)) {
			alert("请输入正确的手机号");
			return;
		}
		$buttonNode.unbind('click');
		$buttonNode.css('background-color', '#808080');
		$buttonNode.val("正在发送...");
		$buttonNode.parent().css("width", "87px");
		phoneNode.attr('readonly', 'readonly');
		$.ajax( {
			url : WEBROOT + '/merchant/getsmsCodeToResetpassword.do',
			type : 'post',
			data : {
				p : phoneNum
			},
			dataType : 'json',
			dataFilter : {
				s : [ 'p' ]
			},
			success : function(res) {
				if (res.code == AJAX_SUCESS_IDENTIFYING_CODE) {
					// 获取成功,执行延迟加载程序
					successCallback();
					//alert("success");
				} else if (res.code == AJAX_FAIL_IDENTIFYING_CODE) {
					whenErrorCallBack(res.msg);
					//alert("failed");
				} else {
					whenErrorCallBack(res.msg);
				}
			},
			error : function(res) {
				//alert("连接错误，请重新发送验证码");
				whenErrorCallBack("连接错误，请重新发送验证码");
			}
		});

		// 发送验证码成功时要调用的函数
			var successCallback = function() {
				var waitTime = 60;
				var timerId = setInterval(timeRun, 1000);
				function timeRun() {
					if (waitTime == 0) {
						clearInterval(timerId);
						phoneNode.removeAttr('readonly');
						$buttonNode.val(buttonVal);
						$buttonNode.parent().css("width", "83px");
						$buttonNode.click(clickCheckCode);
						$buttonNode.css('background-color', '#42C2B3');
					} else {
						waitTime--;
						$buttonNode.val('短信已发送(' + waitTime + ')');
					}
				}
			};
			// 发送验证码失败时要调用的函数
			var whenErrorCallBack = function(msg) {
				alert(msg);
				phoneNode.removeAttr('readonly');
				$buttonNode.val(buttonVal);
				$buttonNode.parent().css("width", "130px");
				$buttonNode.click(clickCheckCode);
				$buttonNode.css('background-color', '#42C2B3');
			}
		});
	
	//跳到下一步（做相应的验证）
	$('#next-BTN').click(function() {
		var phone = $('#vertify-phone').val();
		var sms = $('#vertify-input').val();
		if (CHECK.isPhoneNumber(phone) && CHECK.isNumber(sms)) {
			$.ajax( {
				url : WEBROOT + '/merchant/checkeSmsCode.do',
				type : 'post',
				data : {
					phoneNum : phone,
					smsCode : sms
				},
				dataFilter : {
					s : [ 'phoneNum', 'smsCode' ]
				},
				dataType : 'json',
				success : function(res) {
					if (res.code == AJAX_SUCCESS_ALERT_CODE) {
						window.location = WEBROOT + '/merchant/toResetPassword2.do';	
						//alert("success");
					} else {
						//$('#vertify-phone').val("");
						$('#vertify-input').val("");
						alert(res.msg);
					}
				},
				error : function() {
					alert("数据提交失败，请稍后重试");
				}
			});
		}
		else{
			alert("你的验证码或手机格号码式不对");
		}
	});
	
})
