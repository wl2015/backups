<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核商家详细信息</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="res/css/admin/merchantInfo.css">

  <script type="text/javascript" src="res/js/jQuery/jquery.js"></script>
  <script type="text/javascript" src="res/js/admin/merchant.js?verson=2"></script> 
  <script type="text/javascript" src="res/js/common.min.js"></script>     
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
</head>
<body>
 <div class="merchantInfo" >
  <div class="title">新注册商家详情</div>
  <div class="list">
   <ul>
    <li>
     <strong>商家名</strong>
     <span>${verifyMerchant.shopName}</span><input type="hidden" id="shopName" value="${verifyMerchant.shopName}">
    </li>
    <li class="aboutMerchant">
     <strong>商家简介</strong>
     <span>${verifyMerchant.merchantIntro}</span><input type="hidden" id="merchantIntro" value="${verifyMerchant.merchantIntro}">
    </li>
    <li>
     <strong>地址</strong>
     <span>${verifyMerchant.merchantAddress}</span><input type="hidden" id="merchantAddress" value="${verifyMerchant.merchantAddress}">
     
    </li>
    <li>
     <strong>联系方式</strong>
     <span>${verifyMerchant.linkPhone}</span><input type="hidden" id="linkPhone" value="${verifyMerchant.linkPhone}">
    </li>
    <li>
     <strong>邮箱</strong>
     <span>${verifyMerchant.merchantMail}</span><input type="hidden" id="merchantMail" value="${verifyMerchant.merchantMail}">
    </li>
    <li>
     <strong>负责人</strong>
     <span>${verifyMerchant.bossName}</span><input type="hidden" id="bossName" value="${verifyMerchant.bossName}">
    </li>
    <li>
     <strong>负责人身份证号</strong>
     <span>${verifyMerchant.idCard}</span><input type="hidden" id="idCard" value="${verifyMerchant.idCard}">
    </li>
    <li>
     <strong>银行卡</strong>
     <span>${verifyMerchant.bankCard}</span><input type="hidden" id="bankCard" value="${verifyMerchant.bankCard}">
    </li>
    <li>
     <strong>状态</strong>
     <span>${verifyMerchant.merchantStatus == 0 ? '未审核' : '已驳回'}</span>
    </li>
    <c:if test="${verifyMerchant.merchantStatus == 2}">
    <li>
     <strong>驳回理由</strong>
     <span><textarea cols="50" rows="3">${rejectReason.content }</textarea></span>
    </li>
    <li>
     <strong>驳回时间</strong>
     <span>${rejectReason.time }</span>
    </li>
    </c:if>
   </ul>
  </div>
  <div class="answer">
    <input type="hidden" id="merchantId" value="${verifyMerchant.merchantId}">
     <c:if test="${verifyMerchant.merchantStatus == 0}">
          <div class="disagree">驳回申请</div>
          <form action="manageMer/verifyMerchant.do">
          <input type="hidden" value="${verifyMerchant.merchantId}" id="id"/>
          <div class="agree" onclick="verifyPass()">通过审核</div>
          </form>
     </c:if>    
  </div>
  <div class="back" href="javascript:void(0);" onclick="contentSet('manageMer/showVerify.do');">返回</div>
  <c:choose>
      <c:when test="${verifyMerchant.merchantStatus == 2}">
         <div style="margin:-25px 0 0 85%"><a style="text-decoration:none;" href="javascript:void(0);" onclick="contentSet('manageMer/deleteVerifyMerchant.do?id=${verifyMerchant.merchantId}')" >删除申请</a></div>
      </c:when>
  </c:choose>
 
 </div>
 
  <div class="reason">
    <div class="backReason">驳回理由：</div>
    <textArea id="rejectReson"></textArea>
    <div class="cancelReason">取消提交</div>
    <div class="submitReason" id="verifyReject">提交理由</div>
 </div>
</body>
</html>