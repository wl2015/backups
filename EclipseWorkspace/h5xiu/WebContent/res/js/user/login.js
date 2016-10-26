﻿$(function() {
	$('.onlyNum').onlyNum();
	$('#input-phone').maxLength(11);
	$('.login-BTN')
			.click(
					function() {
						var phonenum = $('.login-information li:eq(0) input');
						var password = $('.login-information li:eq(1) input');
						if (CHECK.isPhoneNumber(phonenum.val())
								&& CHECK.isPassword(password.val())) {
							$
									.ajax( {
										url : $.WEB_ROOT + '/user/doLogin',
										type : 'post',
										dataType : 'json',
										data : {
											phoneNum : phonenum.val(),
											password : password.val()
										},
										dataFilter : {
											s : [ 'phoneNum', 'password' ]
										},
										success : function(res) {
											if (res.code == AJAX_SUCCESS_SKIP_CODE) {
												window.location = $.WEB_ROOT + '/user/userInfo';
											} else {
												alert(res.msg);
												window.location.href = "./login";
											}
										},
										error : function() {
											alert("系统繁忙，请重新登录");
										}
									});
						} else {
							alert("密码格式不对");
						}

					});
});

/**
 * 判断为空
 */
var isEmpty = function(obj) {
	if (obj == null || obj == undefined || jQuery.trim(obj).length == 0
			|| obj == "" || obj === "null") {
		return true;
	}
	return false;
};

/**
 * password密码框得到焦点设置
 */
function PassFocus(node) {
	tip.text("密    码");
	tip.css('background-color', '#42C2B3');
	$('.login-information li:eq(1)').css('border-color', '#F0F0F0');

}

/**
 * password密码框失去焦点设置
 */
function passBlur(node) {
	var tip = $('.login-information li:eq(1) span');
	if (!CHECK.isPassword($(node).val())) {
		tip.text('密码的长度应为6-16位');
		tip.css('background-color', '#FF9FAA');
		$('.login-information li:eq(1)').css('border-color', '#FF9FAA');
		return false;
	}
	return true;
}

/**
 * phone(login)登录电话框得到焦点设置
 * 
 * @param node
 */
function loginPhoneFocus(node) {
	var tip = $('.login-information li:eq(0) span');
	tip.text("电    话");
	tip.css('background-color', '#42C2B3');
	$('.login-information li:eq(0)').css('border-color', '#F0F0F0');
}
/**
 * phone(login)登录电话框失去焦点设置
 * 
 * @param node
 */
function loginPhoneBlur(node) {
	var $this = $(node);
	// 若电话号码格式正确则提交到数据库检查是否存在该号码
	if (CHECK.isPhoneNumber($this.val())) {
		// 从数据库中检查该号码是否存在
		$.ajax( {
			url : $.WEB_ROOT + '/user/checkePhone',
			type : 'post',
			data : {
				phoneNum : $this.val()
			},
			dataType : 'json',
			dataFilter : {
				s : [ 'phoneNum' ]
			},
			success : function(res) {
				if (res.code == AJAX_PHONENUM_NOTEXIST_CODE) {
					$('.login-information li:eq(0) span').html(res.msg);
					$('.login-information li:eq(0) span').css(
							'background-color', '#FF9FAA');
					$('.login-information li:eq(0)').css('border-color',
							'#FF9FAA');

					return false;
				}

				return true;
			}
		});
	} else {
		$('.login-information li:eq(0) span').html("请输入正确电话");
		$('.login-information li:eq(0) span')
				.css('background-color', '#FF9FAA');
		$('.login-information li:eq(0)').css('border-color', '#FF9FAA');
	}
	return true;
}