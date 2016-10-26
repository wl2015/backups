<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/right.css">
		<link rel="stylesheet" type="text/css" href="../../css/right.css">
		
		<SCRIPT type="text/javascript" src="js/ajax.js"> </SCRIPT>
	
		
		
		<script type="text/javascript" src="js/time.js"></script>
		<script type="text/javascript" src="../../js/time.js"></script>
		<script type="text/javascript" src="../js/time.js"></script>
		 
		<script  language="javascript" type="text/javascript" src="js/hover.js"></script>
		<script  language="javascript" type="text/javascript" src="../../js/hover.js"></script>
		
		<script src="js/jquery-1.4.2.min.js"></script>
		<script src="../../js/jquery-1.4.2.min.js"></script>
		
		<script>	
		window.onload=function()
								
		{
			
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
				timeString1 = months+days1+"日";
				timeString2 = months+days2+"日";;
				timeString3 = months+days3+"日";;
				
				oneD.value = timeString1;
				twoD.value = timeString2;
				threeD.value = timeString3;
				
				//传入search()里的日期参数
				timeS1 = years+months+days1;
				timeS2 = years+months+days2;
				timeS3 = years+months+days3;
				
				ttt1.innerHTML = timeS1;
				ttt2.innerHTML = timeS2;
				ttt3.innerHTML = timeS3;
				
		}
				
		function search(time){
	     
		    var ti = document.getElementById(time).innerHTML; 
		     $("#bbb").find("#temp").remove();
		     sendRequest("KucunAction_search.do?kc.dataTime="+ti); 
     	
	    }
	 	    
	 	function modify(id,limit){

			location.href="admin/inmana/saveInmana.jsp?kucunId="+id+"&limit="+limit;
		}
		</script>
	</head>
	<body>
	<table width="100%" cellspacing="2" cellpadding="4" bgcolor="#fbead0" id="bbb">
		<tr>
			<td colspan="15" class="header">库存管理</td>
		</tr>
		<tr  height="35" align="center">
			<td colspan="15">
				<div>
				<input type="button" value="" name="" class="border2" 
				id="oneD"   onclick="search('ttt1');chooseD1('oneD','twoD','threeD')"/>
				<input type="button" value="2" name="" class="border2"
				 id="twoD" onclick="search('ttt2');chooseD2('oneD','twoD','threeD');"/>
				<input type="button" value="3" name="" class="border2"
				 id="threeD" onclick="search('ttt3');chooseD3('oneD','twoD','threeD');"/>
				</div>
			</td>
		</tr>
		<tr>
			<td class="td1">菜品名</td>
			<td class="td1">当日上限</td>
			<td class="td1">修改上限</td>
			<td class="td1">已点份数</td>
			<td class="td1">剩余量</td>
			
		</tr>
		
	
	</table>
	<div id="ttt1" style="display: none">shijian</div>
	<div id="ttt2" style="display: none">shijian</div>
	<div id="ttt3" style="display: none">shijian</div>

	</body>
</html>