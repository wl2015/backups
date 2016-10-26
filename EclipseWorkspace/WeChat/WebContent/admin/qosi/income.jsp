<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/right.css"></link>
	<link rel="stylesheet" type="text/css" href="../../css/right.css"/>
	<script type="text/javascript" src="../../js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="../../js/jquery.datepicker.js"></script>
	<script type="text/javascript" src="../../js/date2.js"></script>
	<link type="text/css" href="../../css/datepicker.css" rel="stylesheet" />
	<script type="text/javascript">
		$(function() {
			$("#date1").datepicker();
			$("#date2").datepicker2();
		});
		
		function firstTime(){
			var selectValue=$("#select1").val();
			
			//alert("bbbbbb");
			//alert(selectValue);
			if(selectValue=="year"){
				document.getElementById("year").style.display="block";
				document.getElementById("month").style.display="none";
				document.getElementById("date").style.display="none";
			}
			else if(selectValue=="month"){
				document.getElementById("year").style.display="none";
				document.getElementById("month").style.display="block";
				document.getElementById("date").style.display="none";
			}
			else if(selectValue=="date"){
				document.getElementById("year").style.display="none";
				document.getElementById("month").style.display="none";
				document.getElementById("date").style.display="block";
			}
			else{
				document.getElementById("year").style.display="none";
				document.getElementById("month").style.display="none";
				document.getElementById("date").style.display="none";
			}
			//document.getElementById("reminder").style.display="none";
			var selectkey=$("#select1").find('option:selected').text();
			document.getElementById("periodtype").innerHTML=selectkey;
		}
	  	
		function displayTimeselect(){
			
			var selectValue=$("#select1").val();
			
			//alert(selectkey);
			if(selectValue=="year"){
				document.getElementById("year").style.display="block";
				document.getElementById("month").style.display="none";
				document.getElementById("date").style.display="none";
			}
			else if(selectValue=="month"){
				document.getElementById("year").style.display="none";
				document.getElementById("month").style.display="block";
				document.getElementById("date").style.display="none";
			}
			else if(selectValue=="date"){
				document.getElementById("year").style.display="none";
				document.getElementById("month").style.display="none";
				document.getElementById("date").style.display="block";
			}
			else{
				document.getElementById("year").style.display="none";
				document.getElementById("month").style.display="none";
				document.getElementById("date").style.display="none";
			}
			document.getElementById("reminder").style.display="none";
		}
		
		function checkeTime(){
		//alert("bbbbbbbbbbbb");
			var selectValue=$("#select1").val();
			
			//alert(selectValue);
			if(selectValue=="year"){
			var downyear=document.getElementById("downyear").value;
			var upyear=document.getElementById("upyear").value;
				if(downyear==""||upyear==""){
					alert("年份不能为空");
					return false;
				}
				if(isNaN(downyear)||isNaN(upyear)){
					alert("上限年或下限年必须为整数");
					return false;
				}
				if(parseInt(downyear)>parseInt(upyear)){
					alert("下限年不能大于上限年");
					return false;
				}
				return true;
			}
			else if(selectValue=="month"){
			var year1=document.getElementById("year1").value;
			var year2=document.getElementById("year2").value;
			var month1=document.getElementById("month1").value;
			var month2=document.getElementById("month2").value;
				if(year1==""||year2==""||month1==""||month2==""){
					alert("年份,月份不能为空");
					return false;
				}
				if(isNaN(year1)||isNaN(year2)||isNaN(month1)||isNaN(month2)){
					alert("年份,月份必须为整数");
					return false;
				}
				//var d3 = new Date(2009, 8, 8)
					var date1=new Date(parseInt(year1),parseInt(month1),1);
					var date2=new Date(parseInt(year2),parseInt(month2)+1,0);
					//alert(date1+date2);
					if(date1>date2){
						alert("下限年月份不能大于上限年月");
						return false;
					}
					return true;
			}
			
			else if(selectValue=="date"){
			var date3=document.getElementById("date1").value;
			var date4=document.getElementById("date2").value;
				if(date3==""||date4==""){
						alert("日期不能为空");
						return false;
					}
				if(date3>date4){
						alert("下限日期不能大于上限日期");
						return false;
					}					
				return true;
			}
			else{
			return false;
			}
		}
		
	</script>

  </head>
  
  <body onload="firstTime();">
   <form action="ProfitAction_getIncomeOfPayType.do" method="post">
 
	  <span>请选择查询方式：</span>
	  <span style="margin-right:20px"><s:select list="#{'year':'年','month':'月','date':'日'}" id="select1" name="timeType" listKey="key" listValue="value" onchange="displayTimeselect();" theme="simple"></s:select></span>

    <span id="date" style="display: none">
		<span>下限日：</span>
		<span style="margin-right:20px"><s:textfield name="date1" id="date1" theme="simple"  size="15"></s:textfield></span>
		<span>上限日：</span>
		<span><s:textfield name="date2" id="date2" theme="simple"  size="15"></s:textfield></span>
	</span>
	 
	<span id="year" style="display: block">
		<span>下限年：</span>
		<span style="margin-right:20px"><s:textfield name="downyear" id="downyear" theme="simple"  size="15"></s:textfield></span>
		<span>上限年：</span>
		<span><s:textfield name="upyear" id="upyear" theme="simple"  size="15"></s:textfield></span>
	  </span>

	<span id="month" style="display: none">
		<span>下限年：</span>
	 	<span><s:textfield name="year1" id="year1" theme="simple" size="8"></s:textfield></span>
	 	<span style="margin-left:10px">月：</span>
	 	<span><s:textfield name="month1" id="month1" theme="simple" size="8"></s:textfield></span>
	 	
	 	<span style="margin-left:20px">上限年：</span>
	  	<span><s:textfield  name="year2" id="year2" theme="simple" size="8"></s:textfield></span>
	  	<span style="margin-left:10px">月：</span>
	  	<span><s:textfield  name="month2" id="month2" theme="simple" size="8"></s:textfield></span>
	  </span>

	  <span  style="margin-left:10px">
 		<s:submit value="查询" onclick="return checkeTime();" theme="simple"/>
 	  </span>
 		
  </form>
  <div style="clear:both"></div>
  <s:if test="incomelist!=null && incomelist.size()>0">
	  <table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0">
			<tr>
				<td colspan="15" class="header">收入统计(单位：元)</td>
			</tr>
			<tr>
				<td class="td1" id="periodtype" width="25%"></td>
				<td class="td1" width="25%">线上收入</td>
				<td class="td1" width="25%">线下收入</td>
				<td class="td1" width="25%">总收入</td>
				
			</tr>
			 <s:iterator var="income" value="incomelist" >
			<tr>
				<td class="td2"><s:property value="#income.period"/></td>
				<td class="td2"><s:property value="#income.onlineIncome"/></td>
				<td class="td2"><s:property value="#income.offLineIncome"/></td>
				<td class="td2"><s:property value="#income.allIncome"/></td>
			</tr>
			</s:iterator>
		</table>
 	</s:if>
 	<s:elseif test="incomelist!=null && incomelist.size()==0">
 		<br/><span id="reminder"><s:property value='"该期间没有数据"'/></span>
 	</s:elseif>
  </body>
  
</html>
