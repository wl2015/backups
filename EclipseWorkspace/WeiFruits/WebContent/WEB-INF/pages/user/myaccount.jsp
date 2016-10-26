<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
      + request.getServerPort() + path + "/";
%>
<html>
<head>
<title>我的账户</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=path %>/res/css/user/account.css"/>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/bootstrap.min.js"></script>
<style>
body{
  background: #FDE3A7;
  font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
}
</style>
</head>
<body>
  <h4>账户信息</h4>
  <hr>
  <div class="panel panel-default">
    <div class="panel-body">
      <ul>
        <li id="edit"><a href="#" onclick="Edit();">[修改信息]</a></li>
        <li id="out"><a href="#">[退出]</a></li>
      </ul>
      <hr>
      <div class="info">
        <form action="<%=path %>/user/updateUserInfo" method="post">
	        <div class="personal" id="username">
	          <label>注册手机号：</label>
	          <span>${userBean.userName }</span>
	        </div>
	        <div class="personal" id="usermobile">
	          <label>用户姓名：</label>
	          <span id="we_name">${userBean.name }</span>
	          <input type="text" value="" style="display:none" name="name" id="user_name"/>
	        </div>
        <button type="submit" class="btn btn-success" id="save" style="display:none">保存</button>
        </form>
        <div style="padding-top:10px">
          <p id="note">${result }</p>
        </div>
      </div>
    </div>
  </div>
  <script>
    $('#user_name').attr("onblur", "AntiSqlValid(this)");
    
    function Edit(){
      $('#save').css({'display':''});
      var we_name = $('#we_name').text();
      $('#we_name').css({'display':'none'});
      $('#user_name').val(we_name);
      $('#user_name').css({'display':''});
    }
    
    //防SQL注入
    function AntiSqlValid(field){
      var re = /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
      if(re.test(field.value)){
        $('#note').html("请您不要在参数中输入特殊字符和SQL关键字！");
        field.value = "";
        field.focus();
        return false;
      }
    }
  </script>
</body>
</html>