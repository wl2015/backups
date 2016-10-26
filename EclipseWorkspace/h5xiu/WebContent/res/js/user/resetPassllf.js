//设置form里每个text框的默认值
function setDefalt(){
	$("#resetPassForm").find("input[type='text']").each(function(i, elem){		
		$(elem).data("defaultVal",$(elem).val());
	});
	//newPass
	$("#resetPassForm").find("input[name='newPass']").focus(function() {
		textInputFocus(this);
		$(this).attr('type', 'password');
		}).blur(function() {
			if(textInputBlur(this)==0){
				$(this).attr('type', 'text');				
			}
			checkePass(this);
		});
	//reNewPass
	$("#resetPassForm").find("input[name='reNewPass']").focus(function() {
		textInputFocus(this);
		$(this).attr('type', 'password');
		}).blur(function() {
			if(textInputBlur(this)==0){				
				$(this).attr('type', 'text');
			}
			checkePass(this);
		});
}

//得到焦点
var textInputFocus = function(node) {
	//alert($(node).val());
	var $this = $(node);
	$this.next('.alert-error').html('');
	if ($this.val() == $this.data("defaultVal")) {
		$this.val('');
	}
};
//失去焦点
var textInputBlur = function(node) {
	//alert("textInputBlur");
	var $this = $(node);
	if (jQuery.trim($this.val()).length == 0) {
		$this.next('.alert-error').html("请完善"+$this.data("defaultVal"));
		$this.val($this.data("defaultVal"));
		return 0;
	}
	return 1;
};
//判断为空
var isEmpty = function(obj) {
	if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
		return true;
	}
	return false;
};
/**
 * 3-4-4的验证手机号码
 * */
var isPhone = function(val) {		
//	var min = 11, max = 11, type = "0";
//	return validateInput(val, min, max,type);
	var vali = /(^\d{3}-\d{4}-\d{4}$)/;		
	if (vali.exec(val) === null) {			
		return false;
	}
	return true;
};
//提示错误信息
var alertError = function(node, msg) {		
	$(node).next('.alert-error').html(msg);		
};
//判断是有效的输入，检查长度，每个字符
function validateInput(str, min, max, type) {
	if (isEmpty(str)) {
		return false;
	}
	if (min && !isEmpty(min) && max && !isEmpty(max)) {
		var len = jQuery.trim(str).length;
		if (len < min || len > max) {
			return false;
		}
	}
	var vali = "/^[";
	if (type.indexOf("0") != -1) {
		vali += "0-9";
	}
	if (type.indexOf("a") != -1) {
		vali += "a-z";
	}
	if (type.indexOf("A") != -1) {
		vali += "A-Z";
	}
	if ((type.indexOf("S") != -1)){
		vali += "\\S";
	}
	if(type.indexOf("C") != -1){
		vali +="\u4E00-\u9FA5\uF900-\uFA2D";
	}
	if (type.indexOf("_") != -1) {
		vali += "_";
	}
	vali += "]{"+min+","+max+"}";
	vali += "$/";
	if (type == "e" || type == "E" ) {
		vali = "/^([a-zA-Z0-9_\-]+)@([a-zA-Z0-9_\-])+((\.[a-zA-Z0-9_\-]{2,3}){1,2})$/";
	}
	var patrn = eval(vali);
	if (!patrn.exec(str))
		return false;
	return true;
};
//验证验证码格式正确否
var checkSmsCode = function(node) {
	var $this = $(node);
	var isSmsCode = function(val) {
		var min = 6; max = 6; type='0';
		return validateInput(val, min, max,type);
	};
	if ($this.val() === $this.data("defaultVal") || !isSmsCode($this.val())) {
		var msg = '请输入正确的短信验证码';
		alertError(node, msg);
		
		return false;
	}
	
	return true;
};

//验证密码格式是否正确
var checkePass=function(node){
	var $this = $(node);
	var isPass = function(val) {
		var min = 6; max = 6; type='0aA';
		return validateInput(val, min, max,type);
	};
	if ($this.val() === $this.data("defaultVal") || !isPass($this.val())) {
		var msg = '密码格式不正确，密码由6个字符组成，且只能是数字或字母';
		alertError(node, msg);			
		return false;
	}
	
	return true;
};
//smsCode
function smsCodeBlur(node){
	textInputBlur(node);
	checkSmsCode(node);
}
//发送验证码
function clickCheckCode(node){
	var phoneNum=$("#resetPassForm").find("input[name='phoneNum']").val();
	var buttonVal=$(node).val();
	var $buttonNode=$(node);
	if(isEmpty(phoneNum)||!isPhone(phoneNum)){
		$(node).next('.alert-error').html("手机号码不正确");
		return 0;
	}
	$(node).unbind('click');
	$(node).val("正在发送...");
	$.ajax ({
		url: $.WEB_ROOT + '/getsmsCode?resetPass=1',
		type:'post',
		data:{
			p:phoneNum
		},
		dataType:'json',
		dataFilter: {
			s:['p']
		},
		success:function(res) {
			if(res.code == 1003) {
				//获取成功,执行延迟加载程序
				successCallBack(res.msg);
				$("#setPass").show();
			}else if (res.code == 1004) {
				whenErrorCallBack(res.msg);
			}else {
				whenErrorCallBack(res.msg);
			}
		},
		error:function(res) {
			whenErrorCallBack("连接错误，请重新发送验证码");
		}
	});
	//返回成功时的回调函数
	var  successCallBack = function(msg){
		 var waitTime = 60;
			var timerId = setInterval(timeRun, 1000);
			function timeRun() {
				if (waitTime == 0) {
					clearInterval(timerId);									
					$buttonNode.val(buttonVal);
					$buttonNode.click(clickCheckCode);
				} else {
					waitTime --;
					$buttonNode.val('短信已发送('+waitTime+')');
				}
			}
	 };
	 
	//发送验证码失败时要调用的函数
		var whenErrorCallBack = function(msg) {
			$(node).next('.alert-error').html(msg);				
			$buttonNode.val(buttonVal);
			$buttonNode.click(clickCheckCode);
		};	
}
//重置密码
function resetPass(node){
	var $form=$("#resetPassForm");
	var $pass=$form.find("input[name='newPass']");
	var $rePass=$form.find("input[name='reNewPass']");
	var $phone=$form.find("input[name='phoneNum']");
	var $smsCode=$form.find("input[name='smsCode']");
	if(!isPhone($phone.val())){
		$(node).next('.alert-error').html("电话号码有误");
		return 0;
	}

	if($pass.val() == $pass.data("defaultVal") || isEmpty($pass.val())){
		$(node).next('.alert-error').html("请先检查你的密码");
		return false;
	}
	if($rePass.val() == $rePass.data("defaultVal") || isEmpty($rePass.val())){
		$(node).next('.alert-error').html("请先确认密码");
		return false;
	}
	checkePass($form.find("input[name='newPass']"));
	checkePass($form.find("input[name='reNewPass']"));
	if($smsCode.val() == $smsCode.data("defaultVal") || isEmpty($smsCode.val()) || !checkSmsCode($smsCode)){
		$(node).next('.alert-error').html("验证码不对");
		return false;
	}
	if($pass.val() !=$rePass.val()){
		$(node).next('.alert-error').html("请先确认表单两次密码输入不一致");
		return false;
	}
	$(node).unbind('click');
	$(node).val("正在修改...");
	//表单合格后提交表单
	
	$.ajax ({
		url: $.WEB_ROOT + '/user/doResetPass',
		type:'post',
		data: $('#resetPassForm'),
		dataType:'json',
		success:function(res){
			$(node).click(resetPass);
			$(node).val('确认修改');
			if(res.code== 2005){				
				window.location.href = $.WEB_ROOT + '/user/login?msg='+res.msg;
			}
			else{
				$(node).next('.alert-error').html(res.msg);
			}					
		},
		error:function(){
			$(node).click(resetPass);
			$(node).val('确认修改');
			$(node).next('.alert-error').html("密码修改失败，请重新修改");
		}
	});		
}
 
