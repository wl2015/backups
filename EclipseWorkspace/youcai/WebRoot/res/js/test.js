$(function(){

  /**
   * 获取验证码，之后输入注册密码
   */
  $('#vertifyCode').click(function(){
    if ($('#vertify-phone').val()=="") {
      alert("请输入手机号！");
    } else {
      alert("true");
      
      
       
       $('#vertify-input').val("true");
       
       
       $('#regist-content').show();
       $('#vertify-content').hide();
    }
     
  });   
    
  /**
   * 注册账号
   */
  $('#registBtn').click(function(){
    alert("1111");
      var phonenum = $('#vertify-phone').val();
      var password = $('#regist-password').val();
      var nickname = $('#regist-nickname').val();
      if($('#vertify-input').val()=="true"){
        alert("2222");
        $.ajax ({
          url: WEBROOT + '/test/userRegist.do',
          type:'post',
          dataType:'json',
          data: {
            phoneNum:phonenum,
            passWord:password,
            nickName:nickname
          },
         
          success:function(res){
            alert("3333");
            if(res.data== ResultCode.SUCCESS){
              window.location = WEBROOT + '/test/test.do';
            }
            else{
              alert("跳转页面失败");
              
            }          
          },
          error:function(){
            alert("系统繁忙，请重新登录");
          }
        });
      }
      else{
        alert("验证码不对");
      }
      
      
    });
  
  
});





function test(){
  $.ajax({
    url:'/findAllProduct',
    type:'post',
    dataType:"json",
    success:function(res){
    
    }
  });
}