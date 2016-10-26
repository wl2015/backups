<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理商家详情</title>
<link rel="stylesheet" type="text/css" href="res/css/admin/merchantInfoManage.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
  <div class="merchantInfoManage">
    <div class="title">商家详情</div>
    <div class="list">
      <ul>
        <li>
          <strong>商家名</strong>
          <span>${merchant.shopName}</span>
        </li>
        <li class="aboutMerchant">
          <strong>商家简介</strong>
          <span>${merchant.merchantIntro}</span>
        </li>
        <li>
          <strong>地址</strong>
          <span>${merchant.merchantAddress}</span>
        </li>
        <li>
          <strong>联系方式</strong>
          <span>${merchant.linkPhone}</span>
        </li>
        <li>
          <strong>星级</strong>
          <span>${merchant.merchantStar}</span>
        </li>
        <li>
          <strong>短语评价</strong>
          <span>${merchant.merchantComment}</span>
        </li>
        <li>
          <strong>邮箱</strong>
          <span>${merchant.merchantMail}</span>
        </li>
        <li>
          <strong>负责人</strong>
          <span>${merchant.bossName}</span>
        </li>
        <li>
          <strong>负责人身份证号</strong>
          <span>${merchant.idCard}</span>
        </li>
        <li>
          <strong>银行卡</strong>
          <span>${merchant.bankCard}</span>
        </li>
        <li>
          <strong>状态</strong>
          <span>${merchant.merchantStatus == 1 ? '已审核' : '未审核'}</span>
        </li>
        <li>
          <strong>注册时间</strong>
          <span>${merchant.registerTime}</span>
        </li>
      </ul>
    </div>
    <div class="answer">
      <div class="delete"  onclick="deleteMerchant('${merchant.merchantId}')">注销商家</div>
      <div class="back" onclick="contentSet('manageMer/show.do')">返回</div>
    </div>
    
  </div>
</body>
</html>