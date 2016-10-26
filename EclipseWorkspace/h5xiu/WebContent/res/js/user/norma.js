	//name昵称框设置
		function NameBlur(node){
			textInputBlur(node);
			var $name=$(node);
			if($name.val() == $name.data("defaultVal") || isEmpty($name.val())){
				var msg="请填写昵称";
				alertError(node, msg);
				return false;
			}
			return true;
		}
		
		//password密码框设置
		function PassFocus(node){
			textInputFocus(node);
			if($(node).val == $(node).data("defaultVal") || isEmpty($(node).val())){				
				$(node).attr('type','password');	
				//alert("set attr");
			}
		}
		
		function passBlur(node){
			textInputBlur(node);
			if($(node).val() == $(node).data("defaultVal") || isEmpty($(node).val())){
				var msg="请填写密码";
				alertError(node, msg);
				$(node).attr('type','text');				
				return false;
			}
			checkePass(node);
			return true;
		}
		//rePass确认密码框设置
		function rePassBlur(node){
			textInputBlur(node);
			var pass=$("#registForm").find("input[name='password']").val();
			
			if($(node).val() == $(node).data("defaultVal") || isEmpty($(node).val())){
				var msg="请完善确认密码";
				alertError(node, msg);
				$(node).attr('type','text');				
				return false;
			}
			checkePass(node);
			if(pass != $(node).val()){
				var msg="两次密码输入不一致";
				alertError(node, msg);								
				return false;
			}
			
			return true;			
		}
		
		//phone(regist)注册电话框设置
		function phoneKeydown(node) {		
			var val = $(node).val();			
			if (val.length == 3) {				
				$(node).val(val+'-');
			}
			if (val.length == 8) {
				$(node).val(val+'-');
			}
		}
		
		function phoneBlur(node){
			textInputBlur(node);
			
			//检查电话号码	
			var $this = $(node);
			
			if($this.val() == $this.data("defaultVal") ||  isEmpty($(node).val())){	
				var msg = "请输入电话号码";
				alertError($(node).next(), msg);							
				return false;
			}
			if (!isPhone($this.val())) {
				var msg = '请输入正确的电话号码';
				alertError($(node).next(), msg);				
				return false;
			}			
			return true;	
		}
		
		function PhoneFocus(node){
			var $this = $(node);
			$this.next().next('.alert-error').html('');
			if ($this.val() == $this.data("defaultVal")) {
				$this.val('');
			}
		}
		
		//phone(login)登录电话框设置
		function loginPhoneFocus(node){
			var $this = $(node);
			$this.next('.alert-error').html('');
			if ($this.val() == $this.data("defaultVal")) {
				$this.val('');
			}
		}
		
		function loginPhoneBlur(node){
			textInputBlur(node);			
			//检查电话号码	
			var $this = $(node);
			
			if($this.val() == $this.data("defaultVal") ||  isEmpty($(node).val())){	
				var msg = "请输入电话号码";
				alertError($(node), msg);							
				return false;
			}
			if (!isPhone($this.val())) {
				var msg = '请输入正确的电话号码';
				alertError($(node), msg);				
				return false;
			}
			//从数据库中检查该号码是否存在
			$.ajax({
				url: $.WEB_ROOT + '/user/checkePhone',
				type:'post',
				data:{
					phoneNum:$this.val()
				},
				dataType:'json',
				dataFilter: {
					s:['phoneNum']
				},
				success:function(res) {				
					if(res.code == 2003){
						$(node).next('.alert-error').html(res.msg);
						return false;
					}					
				},
				error:function(res) {
					if(res.code == 2003){
						$(node).next('.alert-error').html(res.msg);
						return false;
					}					
				}
			});
			return true;
		}
		
		
		//smsCode验证码框设置
		function smsCodeBlur(node){
			textInputBlur(node);
			checkSmsCode(node);
		}
		//判断为空
	var isEmpty = function(obj) {
		if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
			return true;
		}
		return false;
	};
	//设置regist里每个text框的默认值
	function registDefaltVal(){
		$("#registForm").find("input[type='text']").each(function(i, elem){
			$(elem).data("defaultVal",$(elem).val());
		});
		$("#registForm1").find("input[type='text']").each(function(i, elem){
			$(elem).data("defaultVal",$(elem).val());
		});
		$("#nextB").unbind('click');
		$("#goLogin").attr("href",$.WEB_ROOT + '/user/login');
	}
	//设置login里每个text框的默认值
	function loginDefaltVal(){
		$("#loginForm").find("input[type='text']").each(function(i, elem){
			$(elem).data("defaultVal",$(elem).val());
		});
		$("#goRegist").attr("href",$.WEB_ROOT + '/user/regist');
	}
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
	
	
	/**
	 * 需要改成3-4-4的验证
	 * */
	var isPhone = function(val) {		
//		var min = 11, max = 11, type = "0";
//		return validateInput(val, min, max,type);
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
	//得到焦点
	var textInputFocus = function(node) {			
		var $this = $(node);
		$this.next('.alert-error').html('');
		if ($this.val() == $this.data("defaultVal")) {
			$this.val('');
		}
	};
	//失去焦点
	var textInputBlur = function(node) {
		var $this = $(node);
		if (jQuery.trim($this.val()).length == 0) {
			$this.val($this.data("defaultVal"));
		}
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
	
	//提交手机号码到‘找回密码’页面
	function toresetPass(node){
		
		var $phoneNode=$("#loginForm").find("input[name='phoneNum']");
		
		if(isEmpty($phoneNode.val())|| !isPhone($phoneNode.val())){
			$phoneNode.next(".alert-error").html("请输入正确的电话号码");
			return 0;
		}
		var formNode="<form id='reserPass' action='"+$.WEB_ROOT +"/user/toresetPass' method='post'></form>"
		$phoneNode.wrap(formNode);
		$("#reserPass").submit();
		$phoneNode.unwrap();
		//$(node).attr('href',$.WEB_ROOT + '/user/toresetPass?phoneNum='+$phoneNode.val()); 
		
		return 1;
	}
	
	//登录前检查登录表单
	function checkeForm(){
		var $phonenum=$("#loginForm").find("input[name='phoneNum']");
		var $pass=$("#loginForm").find("input[name='password']");
		if(!checkePass($pass)){
			return 0;
		}
		if(isPhone($phonenum.val()) && $pass.val() != $pass.data("defaultVal") && !isEmpty($pass.val())){
			return 1;
		}
		else{
			$("#loginForm").find("#sbu").next().html("表单信息有误，请先检查表单");
			alert("表单信息有误，请先检查表单");
			return 0;
		}
		
	}
	
	//登录
	function doLogin(){
		
		var $form=$("#loginForm");
		var $phoneNum=$form.find("input[name='phoneNum']");
		var $pass=$form.find("input[name='password']");
			
		if(checkeForm() == 1){				
			$.ajax ({
				url: $.WEB_ROOT + '/user/doLogin',
				type:'post',
				dataType:'json',
				data: $('#loginForm'),
				/*data:{
					p:$phoneNum.val(),
					w:$pass.val(),
				},*/
				//对隐蔽数据加密
				/*dataFilter: {
					s:['p','w'],
					//g:['p','w']
				},*/
				success:function(res){
					if(res.code== 2005){
						window.location = $.WEB_ROOT + '/user/userInfo';
					}
					else{
						$("#sbu").next().html(res.msg);
					}					
				},
				error:function(){
					$("#sbu").next().html("系统繁忙，请重新登录");
				}
			});
		}
	}
	
	//去注册
	function doRegist(){		
		//注册前检查表单
		var $form=$("#registForm");
		var $name=$form.find("input[name='name']");
		var $pass=$form.find("input[name='password']");
		var $rePass=$form.find("input[name='rePassword']");
		/*//对于注册信息在同一页面的注册
		var $phone=$form.find("input[name='phoneNum']");
		var $smsCode=$form.find("input[name='smsCode']");*/
		
		if($name.val() == $name.data("defaultVal") || isEmpty($name.val())){			
			$("#sbu").next().html("请先确认昵称");
			return false;
		}
		if($pass.val() == $pass.data("defaultVal") || isEmpty($pass.val())){
			$("#sbu").next().html("请先检查你的密码");
			return false;
		}
		
		if($rePass.val() == $rePass.data("defaultVal") || isEmpty($rePass.val())){
			$("#sbu").next().html("请先确认密码");
			return false;
		}
		checkePass($pass);
		checkePass($rePass);
		if($pass.val() !=$rePass.val()){
			$("#sbu").next().html("表单两次密码输入不一致");
			return false;
		}
		//alert("验证成功");
		//表单合格后提交表单
			$.ajax ({
				url: $.WEB_ROOT + '/user/doRegist',
				type:'post',
				data: $('#registForm'),
				dataType:'json',
				success:function(res){
					if(res.code== 2005){
						window.location = $.WEB_ROOT + '/user/login?msg='+res.msg;
					}
					else{
						$("#sbu").next().html(res.msg);
					}					
				},
				error:function(){
					$("#sbu").next().html("注册失败，请重新注册");
				}
			});			
	}
	
	
	
	//checkCode(手机发送验证码)
	function clickCheckCode(node){
		var $buttonNode=$(node);
		var buttonVal=$buttonNode.val();
		var phoneNode=$("#registForm1").find("input[name='phoneNum']");
		var phoneNum=phoneNode.val();
		if(!isPhone(phoneNum)){
			return;
		}
		$(node).unbind('click');
		$(node).val("正在发送...");
		
			phoneNode.attr('readonly','readonly');			
			$.ajax ({
				url: $.WEB_ROOT + '/getsmsCode',
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
						successCallback();
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
			
			//发送验证码成功时要调用的函数
			var successCallback = function() {
				var waitTime = 60;
				var timerId = setInterval(timeRun, 1000);
				function timeRun() {
					if (waitTime == 0) {
						clearInterval(timerId);
						phoneNode.removeAttr('readonly');				
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
				alertError("#checkCode", msg);
				phoneNode.removeAttr('readonly');		
				$buttonNode.val(buttonVal);
				$buttonNode.click(clickCheckCode);
			};
		
	}
	
	//提交验证码（注册分多步进行时，先验证手机验证码）
	function submitSmsCode(node){
		var registForm1=$("#registForm1");
		var $phone=registForm1.find("input[name='phoneNum']");
		var $smsCode=registForm1.find("input[name='smsCode']");
		
		if($phone.val() == $phone.data("defaultVal") || isEmpty($phone.val()) || !isPhone($phone.val())){
			$(node).next().html("请先确认电话号码");
			return false;
		}
		if($smsCode.val() == $smsCode.data("defaultVal") || isEmpty($smsCode.val())){
			$(node).next().html("请先输入表单验证码");
			return false;
		}
		//表单合格后提交表单
		$.ajax ({
			url: $.WEB_ROOT + '/user/registStep1',
			type:'post',
			data: $('#registForm1'),
			dataType:'json',
			success:function(res){
				if(res.code== 2001){
					$(node).next().html(res.msg);
					$("#nextB").click(showRegistStep2);
				}
				else{
					$(node).next().html(res.msg);
				}					
			},
			error:function(){
				$(node).next().html("数据提交失败，请稍后重试");
			}
		});		
	}
	
	//显示验证成功之后需要的第二步注册界面
	function showRegistStep2(){
		$('#step1').hide();
		$('#step2').show();		
	}
	
	
	
	