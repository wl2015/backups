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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>退款</title>
<link rel="stylesheet" type="text/css" href="res/css/user/refund.css">
<script type="text/javascript" charset="utf-8" src="res/js/jQuery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="res/js/user/refund.js"></script>
</head>
<body>
<div class="refund">
  <div class="refundRule">
    <ul>退款规则
      <li>1.20分钟以内退款，退返90%费用</li>
      <li>2.超过20分钟，不予退款</li>
      <li>3.超过90分钟还未手袋菜品，全额退款</li>
      <li>4.其他理由，酌情考虑</li>
      <li>注意：时间从下订单开始计算</li>
    </ul>
  </div>
  <div class="refundReason">
    <p>请写明退款理由(还可写<span id="leftCount">50</span>字)</p>
    <div>
    <form action="<%=path%>/user/applyRefund.do" method="post" name="refund">
      <input type="text" style="display: none;" name="orderId" value="${orderId }">
      <div style="display: none;" id="isAllowToCommit">yes</div>
      <textArea name="refundContent" id="Content"></textArea>
    </form>
    </div>
    <div class="submitRefund">提交申请</div>
  </div>
</div>
</body>
</html>