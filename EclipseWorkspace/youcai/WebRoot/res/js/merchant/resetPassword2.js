$(function() {
	$('#password').maxLength(20);
	$('#repeatPassword').maxLength(20);	
	$('#password').keyup(function(){
		 var password = $('#password').val();
		 $('#password').val(password.replace(/[\W]/g,''));
	});
	$('#repeatPassword').keyup(function(){
		 var password = $('#repeatPassword').val();
		 $('#repeatPassword').val(password.replace(/[\W]/g,''));
	});
	  
	$('#reset-BTN').click(function() {
		var password = $('#password').val();
		var repeatPassword = $('#repeatPassword').val();
		
		if(CHECK.isPassword(password) && CHECK.isPassword(repeatPassword) && password == repeatPassword){
			$.ajax( {
				url : WEBROOT + '/merchant/doResetpassword.do',
				type : 'post',
				data : {
					password : password,
					repeatPassword : repeatPassword					
				},
				dataFilter : {
					s : [ 'password', 'repeatPassword' ]
				},
				dataType : 'json',
				success : function(res) {
					if (res.code == AJAX_SUCCESS_SKIP_CODE) {
						alert("密码修改成功");
						window.location = WEBROOT + '/merchant/toMerchantLogin.do';
						
					} else {
						$('#password').val("");
						$('#repeatPassword').val("");
						alert("密码重置失败，请重新修改");
					}
				},
				error : function() {
					alert("数据提交失败，请稍后重试");
				}
			});
		}
		else{
			alert("密码格式不正确或密码不一致,请输入6-20位的数字或字母");
		}
	})
})
