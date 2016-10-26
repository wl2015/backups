$(function () {
   
	
	$('#name').maxLength(11);
	$('#password').maxLength(20);
	 $('#password').keyup(function(){
		    var password = $('#password').val();
		    $('#password').val(password.replace(/[\W]/g,''));
		  });
	
	$('.SUBMIT_BTN').click(function(){
		dologin();
	});
	/*$("#name").bind("keypress",function () {
	    $(this).val($(this).val().replace(/\D/g,''));	  
		//alert($(this).val());
	});*/
   });



function dologin(){
	var name = $('#name').val();
	var password = $('#password').val();
	//var password2 = $('#password2').val();
	if(!CHECK.isPhoneNumber(name)){
		alert("联系电话号格式不对");
		return false;
	}
	if(!CHECK.isPassword(password)){
		alert("密码格式不正确,请输入6-20位的数字或字母");
		return false;
	}	
	$.ajax( {
		url : WEBROOT + '/merchant/Login.do',
		type : 'post',
		data : {
			name : name,
			password:password
		},
		dataFilter : {
			s : [ 'name', 'password' ]
		},
		dataType : 'json',		
		success : function(res) {
			if(res.code == AJAX_SUCCESS_ALERT_CODE)
				window.location = WEBROOT + '/merchant/toMerchantIndex.do';
			else
				alert(res.msg);
			return;
		}
		
	});
}

/*function onlyNum() 
{ 
if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 
//考虑小键盘上的数字键 
    event.returnValue=false; 
} */