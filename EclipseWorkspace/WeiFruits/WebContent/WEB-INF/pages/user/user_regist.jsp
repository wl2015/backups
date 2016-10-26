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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户注册页面</title>
<link rel="stylesheet" href="<%=path %>/res/css/bootstrap.min.css"/>
<script src="<%=path %>/res/js/jQuery/jquery.min.js"></script>
<script src="<%=path %>/res/js/user/login_regist.js"></script>
<style>
  *{
    padding: 0;
    margin: 0;
  }
  body {
    font-family: "Arial","Microsoft YaHei","Lucida Grande","宋体",sans-serif;
    background: #FDE3A7;
  }
  h4{
  text-align: center;
  font-size: 18px;
  margin-top: 15px;
  font-family: inherit;
  font-weight: 500;
  line-height: 1.1;
  color: inherit;
}
.con{
  width: 100%;
  height: 400px;
  /* border: 1px black solid; */
  text-align: center;
  margin: 30px 0;
}
label{
  float: left;
}
.form-group{
  margin-top: 20px;
}
</style>
</head>
<body>
  <h4>用户注册</h4>
  <hr>
  <div class="container">
    <div class="con">
      <form id="regist" action="<%=path %>/user/doRegist" method="post">
        <div class="form-group">
          <label>用户名：</label>
          <input type="text" name="userName" id="userName" class="form-control"
                    placeholder="此为手机号码" maxlength="11"/>
        </div>
        <div class="form-group">
          <label>用户姓名：</label>
          <input type="text" name="name" id="name" class="form-control"/>
        </div>
        <div class="form-group">
          <label>密码：</label>
          <input type="password" name="passWord" id="passWord" class="form-control"
                     placeholder="必须是6至16位数字、字母和特殊符号的组合" maxlength="20"/>
        </div>
      </form>
      <div style="text-align: center;padding-top:10px">
        <button type="submit" class="btn btn-success" id="userRegist">注册</button>
        <button class="btn btn-primary" id="goLogin" onclick="window.location.href='<%=path%>/user/toLogin'">已有账号，去登录</button>
      </div>
      <div id="rregist" style="padding-top:10px">
        <span style='color:#CC0000'>${result }</span>
      </div>
    </div>
  </div>
</body>
</html>