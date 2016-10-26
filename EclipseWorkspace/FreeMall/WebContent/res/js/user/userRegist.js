window.onload=function(){
//  var height = window.screen.height  ;
////  var content = document.getElementsByClassName('content');
//  var content = document.getElementById("content");
//  alert(document.body.clientHeight);
//  content.style.height = height;
////  content.style.backgroundColor="red";
}

/**
 * 注册
 * */
function Regist(){
  var userName = document.getElementById("userName").value;
  var passWord = document.getElementById("passWord").value;
  var psd = document.getElementById("psd").value;
  var name = document.getElementById("name").value;
  var phone = document.getElementById("phone").value;
  var result = document.getElementById("result");
  if(userName.length < 6 || userName.length > 12){
    result.innerHTML = "用户名必须为6至12位";
  }
  else if(passWord.length < 6 || passWord.length > 20){
    result.innerHTML = "密码必须为6至20位";
  }
  else if(psd != passWord){
    result.innerHTML = "两次密码必须相同";
  }
  else if(name.length < 1){
    result.innerHTML = "姓名不能为空";
  }
  else if(name.length > 10){
    result.innerHTML = "姓名太长";
  }
  else if(!phone.match(PHONE_TEST)){
    result.innerHTML = "请输入正确的手机号码";
  }
  else{
    document.getElementById("registForm").submit();
  }
}
/**
 * 检查用户名和密码
 * */
function check(){
  
}