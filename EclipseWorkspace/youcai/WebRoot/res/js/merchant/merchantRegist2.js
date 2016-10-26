
$(function() {
  //设置input框的最大长度
  $('#IDcard').maxLength(18);
  $('#linkphone').maxLength(11);
  $('#bankcode').maxLength(19);
  $('#password').maxLength(20);  
  $('#repeatPassword').maxLength(20);
  $("#shopname").maxLength(20);
  $('#place').maxLength(50);
  $('#linkman').maxLength(15);
    $('#password').keyup(function(){
        var password = $('#password').val();
        $('#password').val(password.replace(/[\W]/g,''));
      });
    $('#repeatPassword').keyup(function(){
        var password = $('#repeatPassword').val();
        $('#repeatPassword').val(password.replace(/[\W]/g,''));
      });
    
  /*$("#shopname").blur(function(){
    console.log($(this).val().length);
  });*/
  
  //baiBuMap
  var map = new BMap.Map("container");  // 创建地图实例 
  var markerlng = 104.07;
  var markerlat = 30.66;
  map.enableScrollWheelZoom();  //启用滚轮放大缩小
  map.addControl(new BMap.NavigationControl()); //添加控件：缩放地图的控件，默认在左上角；
  map.addControl(new BMap.MapTypeControl());  //添加控件：地图类型控件，默认在右上方；
  map.addControl(new BMap.ScaleControl());  //添加控件：地图显示比例的控件，默认在左下方；

  
  //经102°54′～104°53′ 纬30°05 ′～31°26′ 成都
  var point = new BMap.Point(104.07, 30.66);
  var marker = new BMap.Marker(point);        // 创建标注  
  marker.enableDragging(true); //设置图标可拖拽
  //为图标添加拖拽后的监听器
  marker.addEventListener('dragend', function(e){
    markerlng = e.point.lng;
    markerlat = e.point.lat;
    $("#markerlng").val(markerlng);
    $("#markerlat").val(markerlat);
    /*  alert( $("#markerlng").val()+"....#markerlng.val().....#markerlat。val：" + $("#markerlat").val());  */              
  });
  map.addOverlay(marker);  // 将标注添加到地图中
  //map.setCenter(point);
  //console.log(map.getCenter());
  map.centerAndZoom(point, 16);
  
  $("#markerlng").val(markerlng);
  $("#markerlat").val(markerlat);


  var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
        {"input" : "city"
        ,"location" : map
      });

  ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
      var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
          value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }    
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
        
        value = "";
        if (e.toitem.index > -1) {
          _value = e.toitem.value;
          value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }    
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        $("#searchResultPanel").html(str);
      });

  var myValue;
  ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
  var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    $("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);              
    $('input[name="place"]').val($("#city").val());
    toPosition();
  });
               
  // 重置地图中心点坐标和地图级别  
  function toPosition(){
    var local = new BMap.LocalSearch(map, { //智能搜索
          onSearchComplete: getPlace
      });
      local.search(myValue);
    function getPlace(){
      var pp = local.getResults().getPoi(0).point;
      map.setCenter(pp);
         map.centerAndZoom(pp,16);//设置中心和地图级别
         marker.setPosition(pp);
         $("#markerlng").val(pp.lng);
         $("#markerlat").val(pp.lat);
         
         //alert(pp.lng+"....position:lat:"+pp.lat);
    }                        
  } 
  //点击搜索按钮 搜索位置
  $('#jumpMap').click(function(){    
      var address = $('#city').val();
      var myGeo = map.Geocoder();
      // 将地址解析结果显示在地图上,并调整地图视野
      myGeo.getPoint(address, function(point){
        if (point) {
        map.setCenter(point);
         map.centerAndZoom(point,16);//设置中心和地图级别
         marker.setPosition(point);         
        }else{
        alert("请按省、市、县区、街道的顺序填写地址");
        }
      }, "成都市");
    });
  
  $('#city').blur(function(){
    toPosition();
  });
  
  //显示地图
  $('#showMap').click(function(){
    $('#baidumap').show();  
  });
  //关闭地图
  $('#closeMap').click(function(){
    $('#baidumap').hide();
  });
  //确定选址
  $('#surePos').click(function(){
    $('#baidumap').hide();
  });
  
  /**
   * 商家注册
   *  
   */
  $('#regist-BTN').click(function() {
    if( checkForm()){
      $.ajax({        
        url : WEBROOT + '/merchant/doRegist.do',
        type : 'post',
        data: $('#registForm'),
        dataType : 'json',
        success : function(res) {
          if (res.code == AJAX_SUCCESS_SKIP_CODE) {
            alert("注册成功");
            window.location = WEBROOT + '/merchant/toMerchantLogin.do';
          } else {
            alert(res.msg);
          }
        },
        error : function() {
          alert("网络异常，请稍后再试");
        }
        
      });
    }else{
      return false;
    }
    
  });

});

/**
 * 检查form表单数据的格式
 *  
 */
function checkForm() {

	/*var phone = $('#vertify-phone').val();
	var sms = $('#vertify-input').val();*/
	var email= $('#email').val();
	var linkman= $('#linkman').val();
	var IDcard= $('#IDcard').val();
	var linkphone= $('#linkphone').val();
	var bankcode= $('#bankcode').val();
	var password= $('#password').val();
	var repeatPassword= $('#repeatPassword').val();
	var shopname=$("#shopname").val();
	var place = $('input[name="place"]').val();
	
	if(CHECK.isEmpty(shopname) || shopname.length > 20 ){
		alert("店铺名是1-10个汉字");
		return false;
	}
	/*if(!CHECK.isPhoneNumber(phone)){
		alert("电话号码格式不对");
		return false;
	}
	if(!validateInput(sms, 6, 6, "0a")){
		alert("验证码格式不对");
		return false;
	}*/
	if(!CHECK.isEmail(email)){
		alert("email格式不对");
		return false;
	}
	if(CHECK.isEmpty(linkman) || linkman.length > 15){
		alert("联系人是1-8个汉字");
		return false;
	}
	if(!validateInput(IDcard, 15, 18, "0aA")){
		alert("身份证号格式不对");
		return false;
	}
	if(!CHECK.isPhoneNumber(linkphone)){
		alert("联系电话号格式不对");
		return false;
	}
	if(!validateInput(bankcode, 16, 19, "0")){
		alert("银行卡号格式不对");
		return false;
	}
	if(!CHECK.isPassword(password)){
		alert("密码格式不正确,请输入6-20位的数字或字母");
		return false;
	}
	if(!CHECK.isPassword(repeatPassword) || password!=repeatPassword){
		alert("确认密码格式不对或与密码不一致");
		return false;
	}
	if(CHECK.isEmpty(place) || place > 20){
		alert("地址为必填项,且不超过10个汉字");
		return false;
	}	
	if(sqlFilter(new Array(shopname,email,linkman,IDcard,linkphone,bankcode,password,place)) == false){
		return false;
	}
	
	return true;
	//doRegist();

}

