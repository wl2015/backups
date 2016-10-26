<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站内信</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/message.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/admin/message.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/jQuery/jquery.form.js"></script>
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script> 

</head>
<body>  
    <div class="message">
    <div class="messageLeft">
      <div class="left">公告栏</div>
      <div class="right">
        <textArea id="message"></textArea>
      </div>
      <div class="limit">
        你还可以输入<strong>400</strong>字
      </div>
      <div class="button" id="sendMsg">发送</div>
    </div>
    <div class="messageRight">
      <div class="title">本月站内信</div>
      <ul id="messageUl">
        <c:forEach items="${messageTextList }" var="item" varStatus="status">
        <li>
          <p class="messageId">公告栏<span>${item.textId }</span></p>
          <p class="messageContent" style="background-color:#EBEBEB">${item.message }</p>
          <p class="messageTime">${item.createtime }</p>
        </li>
        </c:forEach>
        
      </ul>
    </div>
  </div>
</body>
</html>