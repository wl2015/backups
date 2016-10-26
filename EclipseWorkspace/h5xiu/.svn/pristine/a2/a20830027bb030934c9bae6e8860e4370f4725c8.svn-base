var UPLOAD_URL_ROOT = "/wedding321_uploads";
var UPLOAD_URL_CUT_IMG = "/create_logo_image.jsp";
var UPLOAD_URL_IMG = "/upload_json.jsp";
//点击修改时，出现表单
function showForm(node){	
			$(node).show();
		}

//判断为空
var isEmpty = function(obj) {
	if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
		return true;
	}
	return false;
};
//设置form里每个text框的默认值
function setDefalt(){
	$("#changeNameForm").find("input[type='text']").each(function(i, elem){		
		$(elem).data("defaultVal",$(elem).val());
	});
	$("#changePassForm").find("input[type='text']").each(function(i, elem){
			$(elem).data("defaultVal",$(elem).val());
		});
	
	$("#backToLogin").attr("href", $.WEB_ROOT + '/user/backToLogin');
	$("#changeNameForm").find("input[name='newName']").focus(function(){	
		textInputFocus(this);
		}).blur(function(){
			textInputBlur(this);
			});
	
	//oldPass
	
	$("#changePassForm").find("input[name='oldPass']").focus(function() {
		textInputFocus(this);
		$(this).attr('type', 'password');
		}).blur(function() {
			if(textInputBlur(this)==0){
				$(this).attr('type', 'text');
			}
			checkePass(this);
		});
	//newPass
	$("#changePassForm").find("input[name='newPass']").focus(function() {
		textInputFocus(this);
		$(this).attr('type', 'password');
		}).blur(function() {
			if(textInputBlur(this)==0){
				$(this).attr('type', 'text');
			}	
			checkePass(this);
		});
	//reNewPass
	$("#changePassForm").find("input[name='reNewPass']").focus(function() {
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

//提示错误信息
var alertError = function(node, msg) {		
	$(node).next('.alert-error').html(msg);		
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
		alertError(node,msg);
		return false;
	}
	
	return true;
};
//判断是有效的输入，检查长度，每个字符
function validateInput(str, min, max, type) {
	if (isEmpty(str)) {
		return false;
	}
	if (min && !isEmpty(min) && max && !isEmpty(max)) {
		var len = jQuery.trim(str).length;
		//alert(len+"......str.length");
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
//newName
// onfocus="textInputFocus(this)" onblur="textInputBlur(this)"

//doChangeName
function changeName(node){
	var $name=$("#changeNameForm").find("input[name='newName']");
	if(isEmpty($name.val()) || $name.val()==$name.data("defaultVal")){
		alert("请先修改你的昵称");
		return 0;
	}
	else{
		$.ajax ({
			url: $.WEB_ROOT + '/user/changeName',
			type:'post',
			data:{
				newname:$name.val()
			},
			dataType:'json',
			dataFilter: {
				s:['newname']
			},
			success:function(res) {									
					$("#name").html("用户昵称："+$name.val());
					$name.val("新昵称");
					$("#changeNameForm").hide();
					alert(res.msg);
			},
			error:function(res) {
				$(node).next('.alert-error').html(res.msg);
			}
		});
	}
}

function changePass(node){
	var $oldPass=$("#changePassForm").find("input[name='oldPass']");
	var $newPass=$("#changePassForm").find("input[name='newPass']");
	var $reNewPass=$("#changePassForm").find("input[name='reNewPass']");
	
	if(isEmpty($oldPass.val()) || $oldPass.val()== $oldPass.data("defaultVal")){
		alert("请先完善你的原密码");
		return false;
	}
	if(isEmpty($newPass.val()) || $newPass.val()== $newPass.data("defaultVal")){
		alert("请先完善你的新密码");
		return false;
	}
	if(isEmpty($reNewPass.val()) || $reNewPass.val()== $reNewPass.data("defaultVal")){
		alert("请先输入新密码");
		return false;
	}
	if(!checkePass($oldPass)||!checkePass($newPass)||!checkePass($reNewPass)){
		return false;
	}
	if($newPass.val() != $reNewPass.val()){
		alert("两次输入密码不一致");
		return false;
	}
	
	$.ajax ({
		url: $.WEB_ROOT + '/user/changePassword',
		type:'post',
		data: $('#changePassForm'),
		dataType:'json',		
		success:function(res) {
			$("#changePassForm").hide();
			//设置原密码框格式
			var $oldPass=$("#changePassForm").find("input[name='oldPass']");
			var $newPass=$("#changePassForm").find("input[name='newPass']");
			var $reNewPass=$("#changePassForm").find("input[name='reNewPass']");
			$oldPass.val("原密码");
			$oldPass.attr('type', 'text');
			$newPass.val("原密码");
			$newPass.attr('type', 'text');
			$reNewPass.val("原密码");
			$reNewPass.attr('type', 'text');
			
			alert(res.msg);
			
	},
		error:function(res) {
		$(node).next('.alert-error').html(res.msg);
	}
	});
	return true;
}

//修改头像
function changeIcon(){
	 $("#Uploader_img").click();
     uploadPicture();  
}
function uploadPicture(){      
    $("#Uploader_img").change(function() {    
        $("#fileForm").ajaxSubmit({
            dataType:  'json',
            url : UPLOAD_URL_ROOT + UPLOAD_URL_IMG,
            type: 'post',
            success:function(res){
                if(res.error == 0) {                 	 
                   doChangeInconInDataBase(res.url);
                }
                else{
                	 alert(res);
                }               
            },
            error:function(){
            	 alert("网络连接失败，请稍后重试");
            }
        });
    });
    
}

function doChangeInconInDataBase(str){
	$.ajax({
		url: $.WEB_ROOT + '/user/changeIcon',
		data:{
			s:str
		},
		type: 'post',
		dataType: 'json',
		success: function(res) {
				if(res.code == 2001){
					$("#logoIcon").attr("src",str); 
				} else {
					alert("系统处理异常！错误信息：" + res.message);
				}
		},
		error:function() {
			alert("数据连接失败，请稍后重试");
		}
	});
	 $(".showall").remove();
}
