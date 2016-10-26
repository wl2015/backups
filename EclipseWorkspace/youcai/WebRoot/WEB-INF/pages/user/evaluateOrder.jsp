<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath %>>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="res/css/user/evaluateOrder.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/evaluateOrder.js"></script>
</head>
<body>
<div class="box">
  <form action="<%=path %>/user/confirmOrder.do" method="post" name="commentForm">
  <input type="text" style="display: none;" name="orderId" value="${orderId }"/>
  <div class="content">
    <div class="grade">
      对此次服务综合打分：<br/>
      <input type="radio" name="mark" value="1"/>1
      <input type="radio" name="mark" value="2"/>2
      <input type="radio" name="mark" value="3"/>3
      <input type="radio" name="mark" value="4"/>4
      <input type="radio" name="mark" value="5" checked="checked"/>5
    </div>
    <div class="markTitle"><span class="current" id="goods">好评</span>
    <span class="bad" id="bads">差评</span></div>
    <ul>
        <c:forEach items="${goodComments}" var="comment" varStatus="status">
        <li style="display: none;" id="good${status.index+1 }">
          <input type="checkbox" name="comment"  value="${comment.commentId}">
          ${comment.commentIntro }
        </li>
        </c:forEach>
        <c:forEach items="${badComments}" var="comment" varStatus="status">
        <li style="display: none;" id="bad${status.index+1 }">
          <input type="checkbox" name="comment" value="${comment.commentId}">
          ${comment.commentIntro }
        </li>
        </c:forEach>
        <li>
          <div style="display: none;" id="page">1</div>
          <div style="display: none;" id="goodCommentsNum">${goodCommentsNum }</div>
          <div style="display: none;" id="badCommentsNum">${badCommentsNum }</div>
          <span class="others" id="goodChange">换一批</span>
          <span class="others" style="display: none;" id="badChange">换一批</span>
        </li>
    </ul>
    <div class="receiveConfirm" onclick="document.commentForm.submit();">确认收货</div>
  </div>
  </form>
</div>
</body>
</html>