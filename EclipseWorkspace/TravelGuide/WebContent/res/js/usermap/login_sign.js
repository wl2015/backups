$(document).ready(function(){
	$('#account_login').on('hidden.bs.modal', function(){
		document.getElementById('username').value = "";
		document.getElementById('pass').value = "";
		$('#llogin span').empty();
	});

	$('#account_sign').on('hidden.bs.modal', function(){
		document.getElementById('Susername').value = "";
		document.getElementById('Spass').value = "";
		document.getElementById('SSpass').value = "";
		$('#ssign span').empty();
	});
});

var LoginNote1 = "<span style='float:left;color:#CC0000'>用户名或密码错误，请重新输入！</span>";
var LoginSuccess = "<span style='float:left;color:#03C9A9'>登录成功，正在为您跳转......</span>"
var SignNote1 = "<span style='float:left;color:#CC0000'>请填写用户名！</span>";
var SignNote2 = "<span style='float:left;color:#CC0000'>用户名格式不正确！</span>";
var SignNote3 = "<span style='float:left;color:#CC0000'>请输入密码！</span>";
var SignNote4 = "<span style='float:left;color:#CC0000'>请输入符合要求的密码！</span>";
var SignNote5 = "<span style='float:left;color:#CC0000'>两次输入的密码不一致！</span>";
var SignNote6 = "<span style='float:left;color:#CC0000'>该用户名已被注册，请重新注册！</span>";
var SignNote7 = "<span style='float:left;color:#CC0000'>注册失败，请重新注册！</span>";
var SignSuccess = "<span style='float:left;color:#03C9A9'>注册成功！正在为您跳转......</span>";

function Login(){
	var LUserName = document.getElementById('username').value;
	var LPassWord = document.getElementById('pass').value;

	if(LUserName == "" || LPassWord == ""){
		$('#llogin span').empty();
		$('#llogin').append(LoginNote1);
	}
	else{
	  $.ajax({
	    url: WEBROOT+'/user/doLogin.do',
	    type: 'post',
	    data: {
	      username: LUserName,
	      pass: LPassWord
	    },
	    dataType: 'html',
	    success: function(res, status, xhr){
	      var user_id = $(res)[5].innerHTML;   //获取后台传过来的用户id
	      var user_name = $(res)[7].innerHTML;
	      var b = new Base64();
	      //console.log(hex_sha1(user_name));
	      if(user_id != 0){
	        $('#llogin span').empty();
	        $('#llogin').append(LoginSuccess);
	        setTimeout(function(){
	          window.location.href=WEBROOT+'/user/showAccountMap?username='+b.encode(user_name);
	        }, 2000);
	      }else{
	        $('#llogin span').empty();
	        $('#llogin').append(LoginNote1);
	      }
	    },
	    error: function(xhr, status, info){
	      alert("传值失败！");
	      console.log("error: "+info);
	    }
	  });
		//document.getElementById("loginForm").submit();
	}
}

function Sign(){
	var SUserName = document.getElementById('Susername').value;
	var SPassWord1 = document.getElementById('Spass').value;
	var SPassWord2 = document.getElementById('SSpass').value;

	if(SUserName == "" && SPassWord1 == "" && SPassWord2 == ""){
		$('#ssign span').empty();
		$('#ssign').append(SignNote1);
		return false;
	}

	if(SUserName != ""){
		if(!(/^[\u4e00-\u9fa50-9a-z]([\u4e00-\u9fa50-9a-z_\-\.]*[\u4e00-\u9fa50-9a-z])?$/).test(SUserName)){
			$('#ssign span').empty();
			$('#ssign').append(SignNote2);
			return false;
		}
		else if(SPassWord1 == ""){
			$('#ssign span').empty();
			$('#ssign').append(SignNote3);
			return false;
		}
		else if(SPassWord1 != "" && !(/^\w{5,17}$/).test(SPassWord1)){
			$('#ssign span').empty();
			$('#ssign').append(SignNote4);
			return false;
		}
		else if(SPassWord1 != "" && (/^\w{5,17}$/).test(SPassWord1) &&
					SPassWord2 == "" || SPassWord1 != SPassWord2){
			$('#ssign span').empty();
			$('#ssign').append(SignNote5);
			return false;
		}else{
		  $.ajax({
		    url: WEBROOT+'/user/doRegistUser.do',
		    type: 'post',
		    data: {
		      userName: SUserName,
		      passWord: SPassWord1
		    },
		    dataType: 'html',
		    success: function(res, status, xhr){
		      var user_regist = $(res)[5].innerHTML;
		      var user_id = $(res)[7].innerHTML;
		      if(user_regist){
		        $('#ssign span').empty();
		        $('#ssign').append(SignNote6);
		      }
		      else if(user_id == ""){
		        $('#ssign span').empty();
            $('#ssign').append(SignNote7);
		      //}
		      //else{
		        $('#ssign span').empty();
            $('#ssign').append(SignSuccess);
            setTimeout(function(){
              window.location.href = WEBROOT+'/user/showAccountMap.do';
            }, 2000);
		      }
		    },
		    error: function(xhr, status, info){
		      alert("传值失败！");
		      console.log("error: "+info);
		    }
		  });
			//document.getElementById("registForm").submit();
		}
	}
}
