
	//登录前检查登录表单
	function checkeForm(){
		var $phonenum=$("#adLoginForm").find("input[name='phoneNum']");
		var $pass=$("#adLoginForm").find("input[name='password']");
//		if(!checkePass($pass)){
//			return 0;
//		}
//		if(isPhone($phonenum.val()) && $pass.val() != $pass.data("defaultVal") && !isEmpty($pass.val())){
			return 1;
//	}
//		else{
//			$("#loginForm").find("#sbu").next().html("表单信息有误，请先检查表单");
//			alert("表单信息有误，请先检查表单");
//			return 0;
//		}
		
	}
	//登录
	function doAdLogin(){
		
		var $form=$("#adLoginForm");
		var $phoneNum=$form.find("input[name='phoneNum']");
		var $pass=$form.find("input[name='password']");
		//alert("phone："+$phoneNum.val()+"   pass："+$pass.val());		
		if(checkeForm() == 1){	
			//alert("is ajax.........."+$.WEB_ROOT + '/doLlfLogin');
			$.ajax ({
				url: $.WEB_ROOT + '/doAdLogin',
				type:'post',
				dataType:'json',
				data: $('#adLoginForm'),
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
						window.location = $.WEB_ROOT + '/index';
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
	
	
	
	
	
	