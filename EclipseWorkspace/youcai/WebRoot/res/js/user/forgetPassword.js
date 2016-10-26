$(function(){
  //先验证手机号是否存在，再发送验证码
  $('#getMessageCode').click(function(){
    var phoneNum = $('#phoneNum').val();
    if(phoneNum.length==11){
      queryPhoneNumIsExist(phoneNum);
    }
    else{
      alert("请输入正确的手机号");
    }
  });
  
  //下一步
  $('.sure').click(function(){
    var messageCode = $('#messageCode').val();
      var inputCode = $('#inputCode').val();
      var phoneNum = $('#phoneNum').val();
    if(phoneNum.length!=11){
      alert("请输入正确的手机号");
    }
    else if(inputCode == ""){
      alert("请输入验证码");
      }
    else if(inputCode!=messageCode){
      alert("验证码输入不正确");
    }
    else{
      document.prove.submit();
      
    }
  });
});
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
          alert("手机号未被注册");
        }
        else{
          getMessageCode(phoneNum);
        }
      }
      else if(res.resultCode==AJAX_CODE_FAIL){
        alert("手机号是否存在验证失败，请重新输入");
      }
    }
  });
}