$(function(){
  /**
   * 登陆
   * */
  $('#goSubmit').click(function(){
    if($('#phoneNum').val().length != 11){
      alert("手机号输入不正确");
    }
    if($('#psd').val().length<6 || $('#psd').val().length>20){
      alert("密码是六至二十位");
    }
    else if($('#phoneNum').val().length == 11 && $('#psd').val().length>=6 && 
        $('#psd').val().length<20){
      document.login.submit();
    }
  });
  
  //忘记密码
  $('#forgetPass').click(function(){
    window.location=WEBROOT+'/user/toForgetPassword.do';
  });
  
  //注册
  $('#goRegist').click(function(){
    window.location=WEBROOT+'/user/toRegist.do';
  });
});