<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
  <link rel="stylesheet" type="text/css" href="../css/layout.css" />
  <script  language="javascript" type="text/javascript" src="../js/order.js"></script>
  <script src="js/jquery-1.4.2.min.js"></script>
 <script src="../../js/jquery-1.4.2.min.js"></script>
<title>本页面需用js或是jqery，设置选中的时间,及每一天所能显示的时间</title>

		<script>	
			function myfunction(){
				
				var years,months,days;
				var intYears,intMonths,intDays;
				var today;
				today = new Date(); //系统当前时间
				intYears = today.getFullYear(); //得到年份,getFullYear()比getYear()更普适
				
				intMonths = today.getMonth() + 1; //得到月份，要加1
				
				intDays1 = today.getDate()+1; //得到当天日期
				intDays2 = intDays1+1;//得到第明后天日期
				intDays3 = intDays1+2;
				
				years = intYears + "-";
				if(intMonths < 10 ){
				months = intMonths +"-";
				
				} else {
				months = intMonths +"-";
				}
				if(intDays < 10 ){
				days = "0" + intDays +" ";
				} else {
				days1 = intDays1 + " ";
				days2 = intDays2 + " ";
				days3 = intDays3 + " ";
				}
				
				//传入button的value值里，显示月和日
				timeString1 = days1+"日";
				timeString2 = days2+"日";
				timeString3 = days3+"日";
				
					String = intYears+"年"+intMonths+"月"+intDays1+"日";
				 String1 = intYears+"年"+intMonths+"月";
				 
				
				document.getElementById("1-day").innerHTML = timeString1;
				document.getElementById("2-day").innerHTML = timeString2;
				document.getElementById("3-day").innerHTML = timeString3;
				
				document.getElementById("day").innerHTML = String;
			}
			
			function select(obj){
			
				
				document.getElementById("day").innerHTML = String1+obj;
			}
				
		</script>
</head>
<body style="Font-size:63%" leftmargin="0"   onload="myfunction()">

<div class="head">
<iframe src="head.html" width="100%" frameborder="0"></iframe>
</div>

<div class="margin">
	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="../picture/y2.png" style="width:3.2em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">选择送餐的时间</div>
	<div style="clear:both" class=""></div>
	<div class="margin-2">
		<div class="border-10 float-1 margin-r" id="1-day" onclick="chooseD1('1-day','2-day','3-day');select(this.innerHTML)">10日</div>
		<div class="border-9 float-1 margin-r" id="2-day" onclick="chooseD2('1-day','2-day','3-day');select(this.innerHTML)">11日</div>
		<div class="border-9 float-1 margin-r" id="3-day" onclick="chooseD3('1-day','2-day','3-day');select(this.innerHTML)">12日</div>
		
		<div class="border-9 float-1 margin-r" id="day" style="display:none"></div>
		<div style="clear:both"></div>
		<div class="border-6"></div>
		
<div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:50</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">12:10</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">12:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 margin-r" onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">20:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		<div class="border-11 float-1 " onclick="location.href='addressAction_getByUserInfo.do?userInfo.userinfo_id=${param.idd}&time='+this.innerHTML+'&riqi='+day.innerHTML;return false;">11:30</div>
		
</div>
		<div style="clear:both"></div>
		<div><input type="button" value="上一步" class="button-3" onclick="location.replace('first.html');"/></div>
		
	</div>
</div>
<div class="footer">
<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no></iframe>
</div>

</body>
</html>