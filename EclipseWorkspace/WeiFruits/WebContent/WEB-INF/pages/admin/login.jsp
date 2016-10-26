<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登录</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script type="text/javascript" src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<style>
  body{
    background: #FFDDAA;
    font-family:"Microsoft YaHei",微软雅黑,"Microsoft JhengHei",华文细黑,STHeiti,MingLiu;
    text-align: center;
  }
  .top{
    width: 100%;
    border-bottom: 3px solid red;
    color: #000000;
  }
  .loginarea{
    width: 500px;
    height: 300px;
    margin: 100px 420px;
    /* border: 1px solid #000; */
  }
  .loginarea #adminLogin{
    padding: 30px 60px;
  }
  label{
    float:left;
  }
  .loginarea p{
    color: red;
  }
</style>
</head>

<body>
  <div class="top">
    <h2>后台管理登录</h2>
  </div>
  
  <div class="loginarea">
    <!-- <p>用户名和密码不能为空</p> -->
    <form id="adminLogin" method="post" action="<%=path %>/admin/doLogin">
      <div class="form-group">
        <label for="name">用户名：</label>
        <input class="form-control" type="text" style="width:300px" id="user" 
                name="username" value="${userName}"/>
      </div>
      <br>
      <div class="form-group">
        <label for="password">密码：&nbsp;&nbsp;</label>
        <input class="form-control" type="password" style="width:300px" id="pass" 
                name="password" value="${passWord }"/>
      </div>
      
      <div>
        <button type="submit" class="btn btn-success" id="login">登录</button>
      </div>
    </form>
  </div>

  <script type="text/javascript">
    $(document).ready(function(){
      $('#login').click(function(){
        var user = $('#user').val();
        var pass = $('#pass').val();
        if(user == "" || pass == ""){
          alert("用户名和密码不能为空！");
          return false;
        }
        else if(user != "admin" && pass != "admin"){
          alert("用户名或密码不正确！");
          $('#user').val("");
          $('#pass').val("");
          return false;
        }
        else{
          //alert("登录成功！");
        }
      });
      
      $('#user').attr("onblur", "AntiSqlValid(this)");
      $('#pass').attr("onblur", "AntiSqlValid(this)");
    });
    
    //防SQL注入
    function AntiSqlValid(field){
      var re = /select|update|delete|exec|count|’|"|=|;|>|<|%/i;
      if(re.test(field.value)){
        alert("请您不要在参数中输入特殊字符和SQL关键字！");
        field.value = "";
        field.focus();
        return false;
      }
    }
  </script>
</body>
</html>