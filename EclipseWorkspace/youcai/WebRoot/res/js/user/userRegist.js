$(function(){
  
  /**
   * 手机号是否已注册
   * */
  $('#phoneNum').blur(function(){
    var phone = $('#phoneNum').val();
    if($('#phoneNum').val().length!=11){
        $('#phoneNumIsNull').show();
      }
    else{
      $('#phoneNumIsNull').hide();
      queryPhoneNumIsExist(phone);
    }
  });
  /**
   * 判断输入用户名是否为空
   * */
  $('#userName').blur(function(){
    var userName = $('#userName').val();
    if(userName == ""){
      $('#NameIsNull').show();
    }
    else{
      $('#NameIsNull').hide();
    }
  });
  
  $('#psd').blur(function(){
    if($('#psd').val().length < 6){
      $('#passwordRule').show();
    }
    else{
      $('#passwordRule').hide();
    }
  });
  
  $('#repeatpsd').blur(function(){
    var psw1 = $('#psd').val();
    var psw2 = $('#repeatpsd').val();
    if(psw1!=psw2){
      $('#different').show();
    }
    else{
      $('#different').hide();
    }
  });
  
  $('#userName').keyup(function(){
      var name = $('#userName').val();
      $('#userName').val(name.replace(/[^\u4E00-\u9FA5/^a-zA-Z]/g,''));
  });
  
  //获取验证码
  $('#getMessageCode').click(function(){
    var isExist = $('#phoneNumIsRegist').html();
    var phoneNum = $('#phoneNum').val();
    if(phoneNum.length==11 && isExist=="该手机号未被注册"){
      getMessageCode(phoneNum);
    }
    else{
      alert("请先输入合适的手机号");
    }
    
  });
  
  //登录
  $('.registButton').click(function(){
    var isExist = $('#phoneNumIsRegist').html();
    var psw1 = $('#psd').val();
    var psw2 = $('#repeatpsd').val();
    var userName = $('#userName').val();
    var messageCode = $('#messageCode').val();
    var inputCode = $('#inputCode').val();
    if($('#phoneNum').val().length!=11){
      $('#phoneNumIsNull').show();
    }
    else if(isExist!="该手机号未被注册"){
      $('#phoneNumIsRegist').show();
    }
    else if(userName == ""){
        $('#NameIsNull').show();
      }
    else if(psw1.length<6 || psw1.length>20){
      $('#passwordRule').show();
    }
    else if(psw1 != psw2){
      $('#different').show();
    }
    else if(inputCode == ""){
      alert("请输入验证码")
    }
    else if(inputCode!=messageCode){
      alert("验证码输入不正确");
    }
    else {
      document.regist.submit();
    }
  });
});


/**
 * 传输手机号到后台，检验该手机号是否已被注册
 * 查询返回的结果为0或1，0表示该手机号为被注册，1表示该手机号已被注册
 */
function queryPhoneNumIsExist(phoneNum){
  $.ajax({
    url:WEBROOT+'/user/isExsit.do',
    type:'post',
    data:{
      phoneNum:phoneNum
    },
    success:function(res){
      if(res.resultCode==AJAX_CODE_SUCCESS){
        if(res.isExsit==0){
          $('#phoneNumIsRegist').hide();
          $('#phoneNumIsRegist').html("该手机号未被注册");
        }
        else if(res.isExsit==1){
          $('#phoneNumIsRegist').show();
          $('#phoneNumIsRegist').html("该手机号已被注册");
        }
      }
      else if(res.resultCode==AJAX_CODE_FAIL){
        alert("手机号是否存在验证失败，请重新输入");
      }
    }
  });
}

/**
 * 获取验证码
 * */
function getMessageCode(phoneNum){
  $.ajax({
      url:WEBROOT+'/user/getMessageCode.do',
      type:'post',
      data:{
        phoneNum:phoneNum
      },
      success:function(res){
        if(res.resultCode == AJAX_CODE_SUCCESS){
          $('#messageCode').val(res.messageCode);
          alert("验证码为123");
        }
        else{
          alert("获取验证码失败");
        }
      }
    });
}
