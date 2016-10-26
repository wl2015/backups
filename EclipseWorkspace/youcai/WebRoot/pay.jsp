<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String appId = request.getParameter("appid");
String timeStamp = request.getParameter("timeStamp");
String nonceStr = request.getParameter("nonceStr");
String packageValue = request.getParameter("package");
String paySign = request.getParameter("sign");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微信支付</title>
    
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->
    <script type="text/javascript" src="res/js/jQuery/jquery.min.js"></script>
  <script type="text/javascript" src="res/js/common.min.js"></script>  
  
  </head>
  
  <body>
    <br><br><br><br><br><br><br>
        <div style="text-align:center;size:40px;"><input type="button" style="width:200px;height:80px;" value="微信支付" onclick="callpay()"></div>
        <a style="text-align:center;size: 40px" href="http://www.jialiyoucai.com/youcai/user/toGetUnfinishedOrders.do">返回主页</a>
  </body>
  <script type="text/javascript">
    function callpay(){
     WeixinJSBridge.invoke('getBrandWCPayRequest',{
       "appId" : "<%=appId%>","timeStamp" : "<%=timeStamp%>", "nonceStr" : "<%=nonceStr%>", "package" : "<%=packageValue%>","signType" : "MD5", "paySign" : "<%=paySign%>" 
         },function(res){
        WeixinJSBridge.log(res.err_msg);
//         alert(res.err_code + res.err_desc + res.err_msg);
              if(res.err_msg == "get_brand_wcpay_request:ok"){  
                  alert("微信支付成功!");
                  window.location = 'http://www.jialiyoucai.com/user/payForOrder.do';
              }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
                  alert("用户取消支付!");  
              }else{  
                 alert("支付失败!");  
              }  
      })
    }
  </script>
  
</html>
