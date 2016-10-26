/**
 * 用户登录和注册
 */
$(document).ready(function(){
  var loginNote1 = "<span style='color:#CC0000'>用户名和密码不能为空！</span>";
  
  var registNote1 = "<span style='color:#CC0000'>用户名格式不正确！</span>";
  var registNote2 = "<span style='color:#CC0000'>手机号不能为空！</span>";
  var registNote3 = "<span style='color:#CC0000'>手机号码格式不正确！</span>";
  var registNote4 = "<span style='color:#CC0000'>密码不能为空！</span>";
  var registNote5 = "<span style='color:#CC0000'>密码格式不正确！</span>";
  var registNote6 = "<span style='color:#CC0000'>请填写用户名！</span>";
  var registSuccess = "<span style='color:#1BBC9B'>注册成功！</span>";
  
  $('#userlogin').click(function(){
    var userName = document.getElementById('userName').value;
    var userPass = document.getElementById('passWord').value;
    if(userName == "" || userPass == ""){
      $('#llogin span').empty();
      $('#llogin').append(loginNote1);
      return false;
    }else{
      document.getElementById("login").submit();
    }
  });
  
  $('#userRegist').click(function(){
    var registName = document.getElementById('name').value;
    var registPhone = document.getElementById('userName').value;
    var registPass = document.getElementById('passWord').value;
    //密码正则
    var a = /(^[0-9_]*?((_+[0-9_]*?[0-9]+)|([0-9]+[0-9_]*?_+))[0-9_]*?$)|(^[a-zA-Z_]*?((_+[a-zA-Z_]*?[a-zA-Z]+)|([a-zA-Z]+[a-zA-Z_]*?_+))[a-zA-Z_]*?$)|(^[a-zA-Z0-9]*?(([0-9]+[a-zA-Z0-9]*?[a-zA-Z]+)|([a-zA-Z]+[a-zA-Z0-9]*?[0-9]+))[a-zA-Z0-9]*?$)/;
    var b = /^[\u4e00-\u9fa50-9a-z]([\u4e00-\u9fa50-9a-z_\-\.]*[\u4e00-\u9fa50-9a-z])?$/;       //用户名正则
    
    if(registName != ""){
      if(!b.test(registName)){
        $('#rregist span').empty();
        $('#rregist').append(registNote1);
        return false;
      }
      else if(registPhone == ""){
        $('#rregist span').empty();
        $('#rregist').append(registNote2);
        return false;
      }
      else if(registPhone != "" && !(/^\w{6,16}$/).test(registPhone)){
        $('#rregist span').empty();
        $('#rregist').append(registNote3);
        return false;
      }
      else if(registPhone != "" && (/^\w{6,16}$/).test(registPhone) && registPass == ""){
        $('#rregist span').empty();
        $('#rregist').append(registNote4);
        return false;
      }
      else if(registPhone != "" && (/^\w{6,16}$/).test(registPhone) && registPass != "" && !a.test(registPass)){
        $('#rregist span').empty();
        $('#rregist').append(registNote5);
        return false;
      }
      else{
        $('#rregist span').empty();
        $('#rregist').append(registSuccess);
        document.getElementById("regist").submit();
        return true;
      }
    }
    else if(registName == "" && registPhone == "" && registPass == ""){
      $('#rregist span').empty();
      $('#rregist').append(registNote6);
      return false;
    }
  });
});