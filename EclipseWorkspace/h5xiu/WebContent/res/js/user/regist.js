/**
 * 设置regist里的默认项
 */
$(function() {
	$('.vertify-input').onlyNum();
	$('.vertify-phone').onlyNum();
	$('#vertify-phone').maxLength(11);
	$('#vertify-input').maxLength(6);
	$('#input-password').maxLength(16);
	$('#regist-nickname').maxLength(20);
	$('#regist-password').maxLength(16);
	$('#regist-checkpassword').maxLength(16);

	$('#next-BTN').click(function() {
		var phone = $('#vertify-phone').val();
		var sms = $('#vertify-input').val();
		if (CHECK.isPhoneNumber(phone) && CHECK.isNumber(sms)) {
			$.ajax( {
				url : $.WEB_ROOT + '/user/checkeSmsCode',
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
						showRegistPage();
					} else {
						$('#vertify-phone').val("");
						$('#vertify-input').val("");
						alert(res.msg);
					}
				},
				error : function() {
					alert("数据提交失败，请稍后重试");
				}
			});
		}
	});
	$('#regist-BTN').click(
			function() {
				if (!CHECK.isEmpty($("#regist-nickname").val())
						&& CHECK.isPassword($("#regist-password").val())
						&& CHECK.isPassword($("#regist-checkpassword").val())
						&& $("#regist-password").val() == $(
								"#regist-checkpassword").val()) {
					doRegist();
				}
			});
	$("#vertify-phone").focus(function() {
		$(this).prev().html("电	话");
		$(this).prev().css('background-color', '#42C2B3');
		$(this).css('border-color', '#F0F0F0');
	}).blur(function() {
		var $phone = $(this);
		var $tip = $(this).prev();
		if (CHECK.isPhoneNumber($(this).val())) {
			// 从数据库中检查该号码是否存在
			$.ajax( {
				url : $.WEB_ROOT + '/user/checkePhone',
				type : 'post',
				data : {
					phoneNum : $(this).val()
				},
				dataType : 'json',
				dataFilter : {
					s : [ 'phoneNum' ]
				},
				success : function(res) {
					if (res.code == AJAX_PHONENUM_EXIST_CODE) {
						$tip.html(res.msg);
						$tip.css('background-color', '#FF9FAA');
						$phone.css('border-color', '#FF9FAA');
						return false;
					}
					return true;
				}
			});
		} else {
			$(this).prev().html("请输入正确电话");
			$(this).prev().css('background-color', '#FF9FAA');
			$(this).css('border-color', '#FF9FAA');
		}
	});
	$("#vertify-input").focus(function() {
		$(this).prev().html("验证码");
		$(this).prev().css('background-color', '#42C2B3');
		$(this).css('border-color', '#F0F0F0');
	}).blur(function() {
		if (!CHECK.isNumber($(this).val())) {
			$(this).prev().css('background-color', '#FF9FAA');
			$(this).css('border-color', '#FF9FAA');
			$(this).prev().html('请输入正确验证码');
		}
	});
	$("#regist-nickname").focus(function() {
		$(this).prev().html("昵	称");
		$(this).prev().css('background-color', '#42C2B3');
		$(this).css('border-color', '#F0F0F0');
	}).blur(function() {
		if (CHECK.isEmpty($(this).val())) {
			$(this).prev().css('background-color', '#FF9FAA');
			$(this).css('border-color', '#FF9FAA');
			$(this).prev().html('昵称不能为空');
		}
	});
	$("#regist-password").focus(function() {
		$(this).prev().html("密	码");
		$(this).prev().css('background-color', '#42C2B3');
		$(this).css('border-color', '#F0F0F0');
	}).blur(function() {
		if (!CHECK.isPassword($(this).val())) {
			$(this).prev().css('background-color', '#FF9FAA');
			$(this).css('border-color', '#FF9FAA');
			$(this).prev().html('密码的长度应为6-16位');
		}
	});
	$("#regist-checkpassword").focus(function() {
		$(this).prev().html("确认密码");
		$(this).prev().css('background-color', '#42C2B3');
		$(this).css('border-color', '#F0F0F0');
	}).blur(function() {
		if (!CHECK.isPassword($(this).val())) {
			$(this).prev().css('background-color', '#FF9FAA');
			$(this).css('border-color', '#FF9FAA');
			$(this).prev().html('密码的长度应为6-16位');
		}
		if ($(this).val() != $("#regist-password").val()) {
			$(this).prev().css('background-color', '#FF9FAA');
			$(this).css('border-color', '#FF9FAA');
			$(this).prev().html('密码不一致');
		}
	});

	$(".vertify-BTN").click(function clickCheckCode() {
		var $buttonNode = $(this);
		var buttonVal = $buttonNode.html();
		var phoneNode = $("#vertify-phone");
		var phoneNum = phoneNode.val();

		if (!CHECK.isPhoneNumber(phoneNum)) {
			alert("请输入正确的手机号");
			return;
		}
		$buttonNode.unbind('click');
		$buttonNode.css('background-color', '#808080');
		$buttonNode.html("正在发送...");
		phoneNode.attr('readonly', 'readonly');
		$.ajax( {
			url : $.WEB_ROOT + '/getsmsCode',
			type : 'post',
			data : {
				p : phoneNum
			},
			dataType : 'json',
			dataFilter : {
				s : [ 'p' ]
			},
			success : function(res) {
				if (res.code == 1003) {
					// 获取成功,执行延迟加载程序
			successCallback();
		} else if (res.code == AJAX_FAIL_IDENTIFYING_CODE) {
			whenErrorCallBack(res.msg);
		} else {
			whenErrorCallBack(res.msg);
		}
	},
	error : function(res) {
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
						$buttonNode.html(buttonVal);
						$buttonNode.click(clickCheckCode);
						$buttonNode.css('background-color', '#42C2B3');
					} else {
						waitTime--;
						$buttonNode.html('短信已发送(' + waitTime + ')');
					}
				}
			};
			// 发送验证码失败时要调用的函数
			var whenErrorCallBack = function(msg) {
				alert(msg);
				phoneNode.removeAttr('readonly');
				$buttonNode.html(buttonVal);
				$buttonNode.click(clickCheckCode);
				$buttonNode.css('background-color', '#42C2B3');
			}
		});

});

// 切换页面
function showRegistPage() {
	$('#regist-content').show();
	$('#vertify-content').hide();
}

/**
 * 去注册
 */
function doRegist() {
	// 表单合格后提交表单
	$.ajax( {
		url : $.WEB_ROOT + '/user/doRegist',
		type : 'post',
		data : $('#registForm'),
		dataType : 'json',
		success : function(res) {
			if (res.code == AJAX_SUCCESS_SKIP_CODE) {
				window.location = $.WEB_ROOT + '/user/login';
			} else {
				alert(res.msg);
			}
		},
		error : function() {
			alert("注册失败，请重新注册");
		}
	});
}
