function showUserinfo(){
	var userinfoHtml=  '<div class="user">'
		+ '        <span class="user-title">'
		+ '            账户信息'
		+ '        </span>'
		+ '        <span class="clearfix user-content">'
		+ '            <div class="user-logo">'
		+ '                <img class="user-logo-img" src="'+$.WEB_ROOT+'/res/img/test/1.jpg">'
		+ '                </img>'
		+ '                <div class="user-logo-cover">'
		+ '                    <span class="user-logo-trigle"></span>'
		+ '                    <p class="user-logo-covertext">'
		+ '                        头像'
		+ '                    </p>'
		+ '                </div>'
		+ '            </div>'
		+ '            <div class="user-information">'
		+ '                <table>'
		+ '                    <tr>'
		+ '                        <th width="60" height="40">'
		+ '                            作者昵称'
		+ '                        </th>'
		+ '                        <td width="60" height="40"></td>'
		+ '                        <td width="650" height="40" id="userNiceName">'
		+ '                            未设置'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                    <tr>'
		+ '                        <th width="60" height="40">'
		+ '                            用户账号'
		+ '                        </th>'
		+ '                        <td width="60" height="40"></td>'
		+ '                        <td width="650" height="40" class="clearfix">    '
		+ '                            <div class="num">    '
		+ '                                <p id="userAccount">'
		+ '                                    1425830942@qq.com'
		+ '                                </p>'
		+ '                                <a id="editpsw">'
		+ '                                    修改密码'
		+ '                                </a>'
		+ '                            </div>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                    <tr class="data">'
		+ '                        <td colspan="3" height="40">'
		+ '                            <p>完善资料，立即奖励50元代金券</p>'
		+ '                            <a id="data">马上填写></a>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                    <tr>'
		+ '                        <td colspan="3"  width="300">'
		+ '                            <span class="border"></span>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                </table>'
		+ '                <table>'
		+ '                    <tr>'
		+ '                        <th width="60" height="40">'
		+ '                            <div class="th">用户余额</div>'
		+ '                        </th>'
		+ '                        <td width="60" height="40">'
		+ '                        </td>'
		+ '                        <td width="650" height="40">'
		+ '                            <div class="upay" id="userSurplurpay">'
		+ '                                <strong>'
		+ '                                    0'
		+ '                                </strong>'
		+ '                                <div id="imgbt1"></div>'
		+ '                            </div>'
		+ '                            <div class="upay-choose clearfix">'
		+ '                                <div class="title">'
		+ '                                    <span>'
		+ '                                        1'
		+ '                                    </span>'
		+ '                                    金额选择'
		+ '                                </div>'
		+ '                                <ul class="choosepack">'
		+ '                                    <li data-recharge="500">'
		+ '                                        <div class="card card2">'
		+ '                                            ￥500'
		+ '                                        </div>'
		+ '                                        <p></p>'
		+ '                                    </li>'
		+ '                                    <li data-recharge="2000">'
		+ '                                        <div class="card">'
		+ '                                            ￥2000'
		+ '                                        </div>'
		+ '                                        <p>'
		+ '                                            赠送300'
		+ '                                        </p>'
		+ '                                    </li>'
		+ '                                    <li data-recharge="5000">'
		+ '                                        <div class="card">'
		+ '                                            ￥5000'
		+ '                                        </div>'
		+ '                                        <p>    '
		+ '                                            赠送1200'
		+ '                                        </p>'
		+ '                                    </li>'
		+ '                                    <li data-recharge="10000">'
		+ '                                        <div class="card">'
		+ '                                            ￥10000'
		+ '                                        </div>'
		+ '                                        <p>'
		+ '                                            赠送3000'
		+ '                                        </p>'
		+ '                                    </li>'
		+ '                                </ul>'
		+ '                                <div class="othermoney clearfix">'
		+ '                                    <span class="whatfuck">'
		+ '                                        ¥    '
		+ '                                    </span>'
		+ '                                    <input class="othermoney-input" type="text" placeholder="其他金额" name="other"></input>'
		+ '                                    <div>'
		+ '                                        额外赠送 '
		+ '                                        <strong>'
		+ '                                            ¥<span class="othermoney-gift">0</span>'
		+ '                                        </strong>'
		+ '                                    </div>'
		+ '                                </div>'
		+ '                                <div class="title">'
		+ '                                    <span>'
		+ '                                        2'
		+ '                                    </span>'
		+ '                                    支付方式'
		+ '                                </div>'
		+ '                                <ul class="payway">'
		+ '                                    <li data-recharge="alipay">'
		+ '                                        <div class="card card2">'
		+ '                                            支付宝'
		+ '                                        </div>'
		+ '                                    </li>'
		+ '                                    <li data-recharge="transfer">'
		+ '                                        <div class="card">'
		+ '                                            银行转账'
		+ '                                        </div>'
		+ '                                    </li>'
		+ '                                </ul>'
		+ '                                <div class="yesno">'
		+ '                                    <span class="yes">'
		+ '                                        确认支付'
		+ '                                    </span>'
		+ '                                    <span class="no">'
		+ '                                        取消'
		+ '                                    </span>'
		+ '                                    <span class="help">'
		+ '                                        <a href="#">'
		+ '                                            需要帮助'
		+ '                                        </a>'
		+ '                                    </span>'
		+ '                                </div>'
		+ '                            </div>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                    <tr>'
		+ '                        <th width="60" height="40">'
		+ '                            <div class="th">用户类型</div>'
		+ '                        </th>'
		+ '                        <td width="60" height="40">'
		+ '                        </td>'
		+ '                        <td width="650" height="40">'
		+ '                            <div class="uchange">'
		+ '                                <span id="userType">'
		+ '                                    个人免费版'
		+ '                                </span>'
		+ '                                <div id="imgbt2"></div>'
		+ '                            </div>'
		+ '                            <div class="uchange-choose clearfix">'
		+ '                                <div class="title">'
		+ '                                    <span>'
		+ '                                        1'
		+ '                                    </span>'
		+ '                                    方案选择'
		+ '                                </div>'
		+ '                                <ul class="choosescheme" id="choose">'
		+ '                                    <li data-price="0" data-type="free_user">'
		+ '                                        <div class="card scheme card2">'
		+ '                                            <img title="免费版" src="'+$.WEB_ROOT+'/res/img/test/free_user.png"></img>'
		+ '                                        </div>'
		+ '                                    </li>'
		+ '                                    <li data-price="98" data-type="experience">'
		+ '                                        <div class="card scheme">'
		+ '                                            <img title="体验版" src="'+$.WEB_ROOT+'/res/img/test/experience.png"></img>'
		+ '                                        </div>'
		+ '                                    </li>'
		+ '                                    <li  data-price="480" data-type="advanced">'
		+ '                                        <div class="card scheme">'
		+ '                                            <img title="高级版" src="'+$.WEB_ROOT+'/res/img/test/advanced.png"></img>'
		+ '                                        </div>'
		+ '                                    </li>'
		+ '                                    <li data-price="1800" data-type="professional">'
		+ '                                        <div class="card scheme">'
		+ '                                            <img title="企业版" src="'+$.WEB_ROOT+'/res/img/test/professional.png"></img>'
		+ '                                        </div>'
		+ '                                    </li>'
		+ '                                </ul>'
		+ '                                <span class="money">'
		+ '                                    支付'
		+ '                                    <strong id="money">'
		+ '                                        ¥0/月'
		+ '                                    </strong>'
		+ '                                    <a>'
		+ '                                        方案详情'
		+ '                                    </a>'
		+ '                                </span>'
		+ '                                <div class="yesno">'
		+ '                                    <span class="yes">'
		+ '                                        确认升级'
		+ '                                    </span>'
		+ '                                    <span class="no">'
		+ '                                        取消'
		+ '                                    </span>'
		+ '                                    <span class="help">'
		+ '                                        <a href="#">'
		+ '                                            需要帮助'
		+ '                                        </a>'
		+ '                                    </span>'
		+ '                                </div>'
		+ '                            </div>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                    <tr>'
		+ '                        <th width="60" height="40">'
		+ '                            <div class="th">'
		+ '                                账单摘要'
		+ '                            </div>'
		+ '                        </th>'
		+ '                        <td width="60" height="40">'
		+ '                        </td>'
		+ '                        <td width="650" height="40">'
		+ '                            <div class="ucheck">'
		+ '                                <a>'
		+ '                                    查看详情'
		+ '                                </a>'
		+ '                            </div>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                    <tr>'
		+ '                        <td colspan="3"  width="300">'
		+ '                            <span class="border"></span>'
		+ '                        </td>'
		+ '                    </tr>'
		+ '                </table>'
		+ '            </div>'
		+ '        </span>    '
		+ '        <div class="user-hide">'
		+ '            <span class="bg"></span>'
		+ '            <div class="user-hide-editpsw">'
		+ '                <div class="header">'
		+ '                    <h4>'
		+ '                        修改密码 '
		+ '                    </h4>'
		+ '                    <p id="editpsw-cancel">'
		+ '                        x'
		+ '                    </p>'
		+ '                </div>'
		+ '                <div class="body">'
		+ '                    <table>'
		+ '                        <tr>    '
		+ '                            <th width="60" height="32">'
		+ '                                原密码'
		+ '                            </th>'
		+ '                            <td width="60" height="32"></td>'
		+ '                            <td width="335" height="32">'
		+ '                                <input type="password" placeholder="原密码" id="oldPass"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr>    '
		+ '                            <th width="60" height="32">'
		+ '                                新密码'
		+ '                            </th>'
		+ '                            <td width="60" height="32"></td>'
		+ '                            <td width="335" height="32">'
		+ '                                <input type="password" placeholder="新密码" id="newPass"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr>    '
		+ '                            <th>'
		+ '                                确认密码'
		+ '                            </th>'
		+ '                            <td></td>'
		+ '                            <td>'
		+ '                                <input type="password" placeholder="确认密码" id="reNewPass"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                    </table>'
		+ '                </div>'
		+ '                <div class="footer">'
		+ '                    <span class="sure">'
		+ '                        确定'
		+ '                    </span>'
		+ '                    <span class="cancel">'
		+ '                        取消'
		+ '                    </span>'
		+ '                </div>'
		+ '            </div>'
		+ '            <div class="user-hide-data">'
		+ '                <div class="header">'
		+ '                    <h4>'
		+ '                        完善信息'
		+ '                    </h4>'
		+ '                    <p id="data-cancel">'
		+ '                        x'
		+ '                    </p>'
		+ '                </div>'
		+ ' 			<form id="completeInfoForm" method="post">'
		+ '                <div class="body">'
		+ '                    <table>'
		+ '                        <tr>    '
		+ '                            <th width="60" height="32">'
		+ '                                作者昵称'
		+ '                            </th>'
		+ '                            <td width="60" height="32"></td>'
		+ '                            <td width="335" height="32">'
		+ '                                <input type="text" placeholder="未设置" name="writerName"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr>    '
		+ '                            <th width="60" height="32">'
		+ '                                机构名称'
		+ '                            </th>'
		+ '                            <td width="60" height="32"></td>'
		+ '                            <td width="335" height="32">'
		+ '                                <input type="text" placeholder="机构名称" name="organization"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr>    '
		+ '                            <th>'
		+ '                                姓名'
		+ '                            </th>'
		+ '                            <td></td>'
		+ '                            <td>'
		+ '                                <input type="text" placeholder="姓名" name="name"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr>    '
		+ '                            <th>'
		+ '                                手机'
		+ '                            </th>'
		+ '                            <td></td>'
		+ '                            <td>'
		+ '                                <input type="text" placeholder="手机" name="phoneNum"/>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr height="32">    '
		+ '                            <th>'
		+ '                                城市'
		+ '                            </th>'
		+ '                            <td></td>'
		+ '                            <td>'
		+ '                                <select name="province">'
		+ '                                    <option value="volvo">'
		+ '                                        选择省份'
		+ '                                    </option>'
		+ '                                    <option value="saab">'
		+ '                                        四川'
		+ '                                    </option>'
		+ '                                    <option value="opel">'
		+ '                                        北京'
		+ '                                    </option>'
		+ '                                    <option value="audi">'
		+ '                                        天津'
		+ '                                    </option>'
		+ '                                </select>'
		+ '                                <select name="city">'
		+ '                                    <option value="volvo">'
		+ '                                        '
		+ '                                    </option>'
		+ '                                    <option value="saab">'
		+ '                                        成都'
		+ '                                    </option>'
		+ '                                    <option value="opel">'
		+ '                                        广元'
		+ '                                    </option>'
		+ '                                    <option value="audi">'
		+ '                                        绵阳'
		+ '                                    </option>'
		+ '                                </select>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                        <tr height="32">    '
		+ '                            <th>'
		+ '                                行业'
		+ '                            </th>'
		+ '                            <td></td>'
		+ '                            <td>'
		+ '                                <select name="profession">'
		+ '                                    <option value="volvo">'
		+ '                                        互联网'
		+ '                                    </option>'
		+ '                                    <option value="saab">'
		+ '                                        汽车'
		+ '                                    </option>'
		+ '                                    <option value="opel">'
		+ '                                        服装'
		+ '                                    </option>'
		+ '                                    <option value="audi">'
		+ '                                        家电'
		+ '                                    </option>'
		+ '                                </select>'
		+ '                            </td>'
		+ '                        </tr>'
		+ '                    </table>'
		+ '                </div>'
		+ ' 			</form>'
		+ '                <div class="footer">'
		+ '                    <span class="sure">'
		+ '                        确定'
		+ '                    </span>'
		+ '                    <span class="cancel">'
		+ '                        取消'
		+ '                    </span>'
		+ '                </div>'
		+ '            </div>'
		+ '            <div class="user-hide-bill">'
		+ '                <div class="header">'
		+ '                    <h4>'
		+ '                        账单明细'
		+ '                    </h4>'
		+ '                    <p id="bill-cancel">'
		+ '                        x'
		+ '                    </p>'
		+ '                </div>'
		+ '                <div class="body">'
		+ '                    <span class="history-change">'
		+ '                        <p class="p2" id="buy">'
		+ '                            购买历史'
		+ '                        </p>'
		+ '                        <p class="p1" id="recharge">'
		+ '                            充值历史'
		+ '                        </p>'
		+ '                    </span>'
		+ '                    <div id="history-buy" class="history-buy">'
		+ '                        <table width="568" height="36">'
		+ '                            <tr>'
		+ '                                <td>'
		+ '                                    日期'
		+ '                                </td>'
		+ '                                <td class="borderleft">'
		+ '                                    商品'
		+ '                                </td>'
		+ '                                <td class="borderleft">'
		+ '                                    单号'
		+ '                                </td>'
		+ '                                <td class="borderleft">'
		+ '                                    总计'
		+ '                                </td>'
		+ '                            </tr>'
		+ '                        </table>'
		+ '                        <p id="get-bill" class="get-bill">'
		+ '                            索要发票'
		+ '                        </p>'
		+ '                        <div id="bill-message" class="bill-message">'
		+ '                            <strong>'
		+ '                                联系我们'
		+ '                            </strong>'
		+ '                            <br/>'
		+ '                            请提供以下信息到：'
		+ '                            <a>'
		+ '                                aaaa@qq.com'
		+ '                            </a>'
		+ '                            <br/>'
		+ '                            账号邮箱、发票抬头、联系人、电话、地址、开票金额'
		+ '                            <br/>'
		+ '                            我们将在3个工作日内为您开具并寄送发票（增值税普通发票）'
		+ '                        </div>'
		+ '                    </div>'
		+ '                    <div id="history-bill" class="history-bill">'
		+ '                        <table width="568" height="36">'
		+ '                            <tr >'
		+ '                                <td>'
		+ '                                    日期'
		+ '                                </td>'
		+ '                                <td class="borderleft">'
		+ '                                    时间'
		+ '                                </td>'
		+ '                                <td class="borderleft">'
		+ '                                    单号'
		+ '                                </td>'
		+ '                                <td class="borderleft">'
		+ '                                    总计'
		+ '                                </td>'
		+ '                            </tr>'
		+ '                        </table>'
		+ '                    </div>'
		+ '                </div>'
		+ '            </div>        '
		+ '            <div class="user-hide-plan">'
		+ '                <div class="body">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/plan.png"></img>'
		+ '                </div>'
		+ '                <div class="footer">'
		+ '                    <input type="button" value="关闭"></input>'
		+ '                </div>'
		+ '            </div>'
		+ '        </div>    '
		+ '        <div class="user-changelogo">'
		+ '            <ul class="allpic">'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/1.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                '
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/1.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/1.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '                <li class="pic clearfix">'
		+ '                    <img src="'+$.WEB_ROOT+'/res/img/test/3.jpg"></img>'
		+ '                    <span>dd</span>'
		+ '                </li>'
		+ '            </ul>'
		+ '            <div class="tailor"></div>'
		+ '            <div class="button">'
		+ '                <input type="button" value="上传" class="left"></input>'
		+ '                <input type="button" value="取消" class="right" id="button-cancel"></input>'
		+ '                <input type="button" value="确认" class="right"></input>'
		+ '            </div>'
		+ '        </div>';
	$('#common-content').html(userinfoHtml);
	var userlogoImg=$(".user-logo-img");
	var userniceName=$("#userNiceName");
	var userType=$("#userType");
	var userAccount=$("#userAccount");
	/*<p id="userAccount">'
	+ '                                    1425830942@qq.com'
	+ '                                </p>'*/
		/*<div class="upay" id="userSurplurpay">'
	+ '                                <strong>'
	+ '                                    0'
	+ '                                </strong>'
	+ '                                <div id="imgbt1"></div>'
	+ '                            </div>
	//用户余额
*/	var userSurplurpay=$("#userSurplurpay strong");
	//从数据库中获取用户信息
	$.ajax({
		url: $.WEB_ROOT + '/user/getUserInfo',
		type:'post',		
		dataType:'json',		
		success:function(res) {				
			if(res.code == AJAX_SUCCESS_ALERT_CODE){
				userlogoImg.attr("src",res.user.userHeadImg);
				userniceName.html(res.user.userName);
				userType.html(res.user.userStatus);
				userAccount.html(res.user.phoneNum);
				userSurplurpay.html(res.user.accountMoney);				
			}		
		}
	});
	//封面三角形、文字的动画变换
	$('.user-logo-cover').mouseover(function(){
		var trigle=$(this).children('span');
		var covertext=$(this).children('p');
		$(trigle).removeClass('user-logo-trigle2');
		$(trigle).toggleClass('user-logo-trigle1');

		$(covertext).css({
			'top':'4px',
			'left':'2px'
		});
		$(covertext).html('更换头像');
		$(covertext).click(function(){
			//修改头像code
		});
	});
	/*//修改头像
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
	    
	}*/
	$('.user-logo-cover').mouseout(function(){
		var trigle=$(this).children('span');
		var covertext=$(this).children('p');
		$(trigle).removeClass('user-logo-trigle1');
		$(trigle).toggleClass('user-logo-trigle2');

		$(covertext).css({
		'top':'0px',
		'left':'0px'
		});
		$(covertext).html('头像');
	});
	
	//‘账户充值’与‘更改方案’的背景图的变换,隐藏与显示
	$('.user-information #imgbt1').click(function(){
		if($('.user-information .upay-choose').css('display')=='none'){
			$('.user-information .upay-choose').show(600);
			$('.user-information .uchange-choose').hide(600);
			$('.user-information #imgbt1').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-2.jpg") no-repeat scroll right bottom transparent');
			$('.user-information #imgbt2').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-1.jpg") no-repeat scroll -92px 0px transparent');
		}
		else{
			$('.user-information .upay-choose').hide(600);
			$('.user-information #imgbt1').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-1.jpg") no-repeat scroll 0px 0px transparent');
		}
	});

	$(".user-information #imgbt2").click(function(){
		if($('.user-information .uchange-choose').css('display')=='none'){
			$('.user-information .uchange-choose').show(600);
			$('.user-information .upay-choose').hide(600);
			$('.user-information #imgbt1').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-1.jpg") no-repeat scroll 0px 0px transparent');
			$('.user-information #imgbt2').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-3.jpg") no-repeat scroll right bottom transparent');
		}else{
			$('.user-information .uchange-choose').hide(600);
			$('.user-information #imgbt2').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-1.jpg")no-repeat scroll -92px 0px transparent');
		
		}
	});

	$('.user-information .upay-choose .no').click(function(){
		$('.user-information .upay-choose').hide(600);
		$('.user-information #imgbt1').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-1.jpg") no-repeat scroll 0px 0px transparent');
	});

	$('.user-information .uchange-choose .no').click(function(){
		$('.user-information .uchange-choose').hide(600);
		$('.user-information #imgbt2').css('background','url("'+$.WEB_ROOT+'/res/img/test/uinfo-1.jpg")no-repeat scroll -92px 0px transparent');
	});

	
	//‘用户余额’中‘金额选择’与‘支付方式’的颜色变换
	$('.user-information .choosepack li').click(function(){
		$(this).children('div').addClass('card2');
		$(this).children('p').css('color','#44C3B4');
		$(this).prevAll('li').children('div').removeClass('card2');
		$(this).prevAll('li').children('p').css('color','#969696');
		$(this).nextAll('li').children('div').removeClass('card2');
		$(this).nextAll('li').children('p').css('color','#969696');
	});
	
	$('.user-information .payway li').click(function(){
		$(this).children('div').addClass('card2');
		$(this).prevAll('li').children('div').removeClass('card2');
		$(this).nextAll('li').children('div').removeClass('card2');
	});

	
	//‘用户类型’中‘方案选择’的颜色变换
	$('.user-information #choose li').click(function(){
		$(this).children('div').addClass('card2');
		$(this).prevAll('li').children('div').removeClass('card2');
		$(this).nextAll('li').children('div').removeClass('card2');
		var data_type=$(this).attr("data-type");	
		if(data_type=='free_user'){
			$('.user-information #money').html('¥0/月');
		}
		else if(data_type=='experience'){
			$('.user-information #money').html('¥98/月');
		}
		else if(data_type=='advanced'){
			$('.user-information #money').html('¥480/月');
		}
		else if(data_type=='professional'){
			$('.user-information #money').html('¥1800/月');
		}
	});

	//更改方案中‘方案详情的显示与隐蔽’
	$('.user-information .uchange-choose .money a').click(function(){
		$('.user-hide').css('display','block');
		$('.user-hide-plan').css('display','block');
	});
	
	$('.user-hide .user-hide-plan input').click(function(){
		$('.user-hide').css('display','none');
		$('.user-hide-plan').css('display','none');
	});
	//修改密码的显示与隐藏
	$('.user-information #editpsw').click(function(){
		$('.user-hide').css('display','block');
		$('.user-hide-editpsw').css('display','block');				
	});
	//提交密码修改操作
	$('.user-hide-editpsw .sure').click(function(){		
		var oldPass=$('#oldPass').val();
		var newPass=$('#newPass').val();
		var reNewPass=$('#reNewPass').val();
		if(!CHECK.isPassword(oldPass) || !CHECK.isPassword(newPass) || !CHECK.isPassword(reNewPass)){
			alert("密码的长度应为6-16位");
		}
		if(newPass != reNewPass){
			alert("两次新密码不一致");
		}
		if(CHECK.isPassword(oldPass) && CHECK.isPassword(newPass) &&
				CHECK.isPassword(reNewPass) && newPass == reNewPass){
			$.ajax ({
				url: $.WEB_ROOT + '/user/changePassword',
				type:'post',
				data: {
					oldPass:oldPass,
					newPass:newPass,
					reNewPass:reNewPass
				},
				dataFilter: {
					s:['oldPass','newPass','reNewPass']
				},
				dataType:'json',		
				success:function(res) {
					alert(res.msg);
					//AJAX_SUCCESS_ALERT_CODE修改成功
					//AJAX_FAIL_ALERT_CODE修改失败
					if(res.code==AJAX_SUCCESS_ALERT_CODE){
						$('.user-hide').css('display','none');
						$('.user-hide-editpsw').css('display','none');
					}												
				},
				error:function() {
					alert("系统繁忙，请稍后重试");
				}
			});
		}								
	});
	
	//取消密码修改操作
	$('.user-hide-editpsw .cancel').click(function(){
		$('.user-hide').css('display','none');
		$('.user-hide-editpsw').css('display','none');
	});

	$('#editpsw-cancel').click(function(){
		$('.user-hide').css('display','none');
		$('.user-hide-editpsw').css('display','none');
	});

	//信息完善的隐藏与显示
	$('.user-information #data').click(function(){
		$('.user-hide').css('display','block');
		$('.user-hide-data').css('display','block');
	});
	$('.user-hide-data #data-cancel').click(function(){
		$('.user-hide').css('display','none');
		$('.user-hide-data').css('display','none');
	});
	$('.user-hide-data .cancel').click(function(){
		$('.user-hide').css('display','none');
		$('.user-hide-data').css('display','none');
	});
	$('.user-hide-data .sure').click(function(){
		//获取表单数据
		var niceName=$('.user-hide-data .body').find("input").eq(0);
		var organization=$('.user-hide-data .body').find("input").eq(1);
		var name=$('.user-hide-data .body').find("input").eq(2);
		var phoneNum=$('.user-hide-data .body').find("input").eq(3);
		var provincenode=$('.user-hide-data .body').find("select").eq(0);
		var citynode=$('.user-hide-data .body').find("select").eq(1);
		var professionnode=$('.user-hide-data .body').find("select").eq(2);
		//alert(organization.val()+"   bbbbb");
		/*获取select 选中的 text : 
		$("#ddlregtype").find("option:selected").text(); 

		获取select选中的 value: 
		$("#ddlregtype ").val();*/ 
		var province=provincenode.find("option:selected").text();
		var city=citynode.find("option:selected").text();
		var profession=professionnode.find("option:selected").text();
		//获取数据，验证数据，数据错误提示，提交数据
		if(CHECK.isEmpty(niceName.val()) || CHECK.isEmpty(organization.val()) || CHECK.isEmpty(name.val())
				|| CHECK.isPhoneNumber(phoneNum.val()) || CHECK.isEmpty(province) || CHECK.isEmpty(city)
				|| CHECK.isEmpty(profession)){
			alert("请先完善表单");
		}
		else{
			$.ajax ({				
				url: $.WEB_ROOT + '/user/completeInfo',
				type:'post',
				data:$('#completeInfoForm'),
				/*data: {
					oldPass:oldPass,
					newPass:newPass,
					reNewPass:reNewPass
				},
				dataFilter: {
					s:['oldPass','newPass','reNewPass']
				},*/
				dataType:'json',		
				success:function(res) {
				}
			});
		}		
	});

	//账单明细的显示与隐藏
	$('.user-information .ucheck').click(function(){
		$('.user-hide').css('display','block');
		$('.user-hide-bill').css('display','block');
	});
	
	$('.user-hide-bill #bill-cancel').click(function(){
		$('.user-hide').css('display','none');
		$('.user-hide-bill').css('display','none');
	});

	//‘购买历史’与‘充值历史’交换
	$('.user-hide-bill #recharge').click(function(){
		if($(this).is('.p1')){
			$(this).removeClass('p1');
			$(this).toggleClass('p2');
			$('.user-hide-bill #buy').removeClass('p2');
			$('.user-hide-bill #buy').addClass('p1');
			$('.user-hide-bill #history-bill').css('display','block');
			$('.user-hide-bill #history-buy').css('display','none');
		}
	});

	$('.user-hide-bill #buy').click(function(){
		if($(this).is('.p1')){
			$(this).removeClass('p1');
			$(this).toggleClass('p2');
			$('.user-hide-bill #recharge').removeClass('p2');
			$('.user-hide-bill #recharge').addClass('p1');
			$('.user-hide-bill #history-bill').css('display','none');
			$('.user-hide-bill #history-buy').css('display','block');			
		}
	});

	//点击‘索要发票’，隐藏或显示信息
	$('.user-hide-bill #get-bill').click(function(){
		$('.user-hide-bill #bill-message').toggle(300);
	});
	
	//更换头像
	$('.user-logo .user-logo-cover').click(function(){
		$('.user-changelogo').css('display','block');
	});
	
	$('.user-changelogo #button-cancel').click(function(){
		$('.user-changelogo').css('display','none');
	});
	
	//‘金额选择’中的‘额外赠送’
	$('.othermoney-input').onlyNum();
	chooseMoney();
	/*
	    $("input[name='other']").keyup(function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0/g,''));     
    }).bind("paste",function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0/g,''));     
    }).css("ime-mode", "disabled");    
	*/
}

function chooseMoney(){
	var inputmoney = $('.othermoney-input');
	var giftmoney = $('.othermoney-gift');

	inputmoney.bind('input propertychange', function(){
		if(inputmoney.val() < 500){
			giftmoney.html('0');
		}
		if(inputmoney.val() >=500 && inputmoney.val() < 2000){
			var gift = parseInt(inputmoney.val() * 0.15);
			alert(gift);
			giftmoney.html(gift);
		}
		if(inputmoney.val() >=2000 && inputmoney.val() < 5000){
			var gift = parseInt(inputmoney.val() * 0.24);
			giftmoney.html(gift);
		}
		if(inputmoney.val() >=5000){
			var gift = parseInt(inputmoney.val() * 0.3);
			giftmoney.html(gift);
		}
	});
	
}