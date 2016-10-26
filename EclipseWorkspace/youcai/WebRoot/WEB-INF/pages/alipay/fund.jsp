<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'fund.jsp' starting page</title>
    
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
  <script type="text/javascript" src="res/js/constants.min.js"></script>  
  </head>
  <script type="text/javascript">
    $(function(){
    $("#amount").focus();
    $("#doSubmit").click(function(){
        form1.submit();
    });
});
</script>
  <body>
     <form id="form1" action="aliapi/deposit.do" method="post" target="_blank">  
                <table cellpadding="10">  
                    <tr>  
                        <td>充值测试</td>  
                        <td class="balance" id="userBalance"></td>  
                    </tr>  
                    <tr>  
                        <td><i class="zfb"></i></td>  
                        <td style="padding-bottom: 0px;">亲爱的<span  
                            class="suppliment_user" id="suppliment_user"></span>,您可以使用支付宝充值积善分，请填写以下信息  
                        </td>  
                    </tr>  
                    <tr>  
                        <td></td>  
                        <td>
                                金额<input type="text" name="amount" id="amount">
                        </td>  
                    </tr>  
   
                    <tr>  
                        <td></td>  
                        <td><a href="javascript:void(0);" id="doSubmit" class="blank_btn">确认</a></td>  
                    </tr>  
                </table>  
    </form>  
  </body>
</html>
