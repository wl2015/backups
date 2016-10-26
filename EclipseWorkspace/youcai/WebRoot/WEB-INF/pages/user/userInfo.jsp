<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath %>>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" type="text/css" href="res/css/user/userInfo.css">
<script type="text/javascript" charset="utf-8" src="res/js/constants.min.js"></script>
<script src="res/js/jQuery/jquery.min.js"></script>
<script src="res/js/user/userInfo.js"></script>
</head>
<body>
<ul class="content">
  <li>
    <span class="left">
      姓名:
    </span>
    <span class="center">
      <a id="nameA">${user.userName }</a>
      <input type="text" id="nameInput" name="userName" placeholder="请输入姓名" maxlength="6"/>
    </span>
    <span>
      <a id="nameModify">修改</a>
      <a id="nameSave">保存</a>
    </span>
  </li>
  <li>
    <span class="left">
      性别:
    </span>
    <span class="center">
      ${user.userSex }
    </span>
  </li>
  <li>
    <span class="left">
      邮箱:
    </span>
    <span class="center">
      <a id="emailA">${user.userMail }</a>
      <input type="text" id="emailInput" name="userMail" placeholder="请输入邮箱"/>
    </span>
    <span>
      <a id="emailModify">修改</a>
      <a id="emailSave">保存</a>
    </span>
  </li>
  <li>
    <span class="left">
      注册时间:
    </span>
    <span class="center">
      ${user.registTime }
    </span>
  </li>
  <li>
    <span class="left">
      星级:
    </span>
    <span class="center">
      ${user.star }
    </span>
  </li>
  <li>
    <span class="left">
      会员:
    </span>
    <span class="center">
      ${isVip }
    </span>
    <span class="right">
      升级
    </span>
  </li>
  <li class="button">
    <span class="modifyPass" onclick="window.location=
      '<%=path%>/user/toChangeUserPassword.do'">
      修改密码
    </span>
    <span class="returnHomepage" onclick="window.location=
      '<%=path%>/user/toUserMainPage.do'">
      返回主页
    </span>
  </li>
</ul>
</body>
</html>