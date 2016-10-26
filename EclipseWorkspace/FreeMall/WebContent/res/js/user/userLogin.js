window.onload=function(){
//  var height = window.screen.height  ;
////  var content = document.getElementsByClassName('content');
//  var content = document.getElementById("content");
//  alert(document.body.clientHeight);
//  content.style.height = height;
////  content.style.backgroundColor="red";
}

/**
 * 登陆
 * */
function login(){
  var userName = document.getElementById("userName").value;
  var passWord = document.getElementById("passWord").value;
  if(userName.length < 6 || userName.length > 12){
    document.getElementById("result").innerHTML="用户名必须为6至12位";
  }
  else if(passWord.length < 6 || passWord.length > 20){
    document.getElementById("result").innerHTML="密码必须为6至20位";
  }
  else{
    document.getElementById("loginForm").submit();
  }
}
/**
 * 检查用户名和密码
 * */
function check(){
	
}