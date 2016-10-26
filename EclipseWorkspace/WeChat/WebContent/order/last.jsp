<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE  html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
 <link rel="stylesheet" type="text/css" href="../css/menuList.css" />
 <link rel="stylesheet" type="text/css" href="../css/layout.css" />
 <script src="../js/jquery-1.4.2.min.js"></script>
 <script  language="javascript" type="text/javascript" src="../js/order.js"></script>
 <script>
	function show(){
	var get = document.getElementById("get");
	var p1 =document.getElementById("p1");
	var p2 =document.getElementById("p2");
	if(get.checked){
		p1.style.display = "block";
		p2.style.display = "block";
	}else{
		p1.style.display = "none";
		p2.style.display = "none";
		} 
		
	}
 </script>
<title>开始点餐</title>
</head>
<body style="Font-size:63%;" leftmargin="0">

<div class="head">
<iframe src="head.html" width="100%" frameborder="0"></iframe>
</div>

<div class="margin">


<!--以下为菜单的详细信息-->
	<div class="float-1 pb-1 margin-b-2"><img src="../picture/y1.png" style="width:3.5em"/></div>
	<div class="pt-1 float-1 color-3 font-s-3 pb-1 margin-b-2">确认菜品并使用优惠券</div>
	<div style="clear:both"></div>
	<div>
		<div class="float-1 margin-r"><img src="../picture/warn.png"/></div>
		<div class="font-s-2 color-9 pt-3 ">
			<div class="float-1">离起送价格还差￥</div><div class="float-1" id="left">6.00</div>
		</div>
	</div>
	<div style="clear:both"></div>
	<table class="table1">
		<tr class="tr1" id="as">
			<td class="td1" onclick="delete2('as');"><img src="../picture/delete.png" style="width:1.8em"/></td>
			<td class="td2 font-s-2 color-10">无骨藤椒兔（还有100份）单价:22.00;小计:22.00</td>
			<td class="td3">
			<div id="as">
					<div class="border-min-12 float-2 margin-l-3" id="add" onclick="add('add','num','left');">+</div>
					<div  class="border-min-32 float-2 margin-l-3" id="num" >1</div>
					<div class="border-min-22 float-2"  onclick="jian2('num','as','ad');">-</div>
			</div>
			</td>
		</tr>
		<tr class="tr1" id="as">
			<td class="td1" onclick="delete2('as');"><img src="../picture/delete.png" style="width:1.8em"/></td>
			<td class="td2 font-s-2 color-10">无骨藤椒兔（还有100份）单价:22.00;小计:22.00</td>
			<td class="td3">
			<div id="as">
					<div class="border-min-12 float-2 margin-l-3" id="add" onclick="add('add','num','left');">+</div>
					<div  class="border-min-32 float-2 margin-l-3" id="num" >1</div>
					<div class="border-min-22 float-2"  onclick="jian2('num','as','add');">-</div>
			</div>
			</td>
		</tr>
		<tr class="tr1" id="as">
			<td class="td1" onclick="delete2('as');"><img src="../picture/delete.png" style="width:1.8em"/></td>
			<td class="td2 font-s-2 color-10">无骨藤椒兔（还有100份）单价:22.00;小计:22.00</td>
			<td class="td3">
			<div class="float-2  margin-t-4 border-13" style="display:none" id="over">售罄</div>
			<div id="as">
					<div class="border-min-12 float-2 margin-l-3" id="add" onclick="add('add','num','left');">+</div>
					<div  class="border-min-32 float-2 margin-l-3" id="num" >1</div>
					<div class="border-min-22 float-2"  onclick="jian2('num','as','add');">-</div>
			</div>
			</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td></td>
			<td>送餐费</td>
			<td>6.00</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td></td>
			<td>共计</td>
			<td class="color-11">￥28.00</td>
		</tr>
	</table>

	<div class="margin-t-3 float-1 pb-1 margin-b-2"><img src="../picture/tuijian.png" style="width:3.5em"/></div>
	<div class="margin-t-3 float-1 color-3 font-s-3 pb-1 margin-b-2">推荐搭配</div>
	<div style="clear:both"></div>
	<div class="border-3"></div>

<!--以下为用户的订餐信息-->

	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="../picture/y2.png" style="width:3.2em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">确认送餐信息</div>
	<table class="table1">
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">食客姓名</td>
			<td class="td5 color-12">啊啊（先生）</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">联系电话</td>
			<td class="td5 color-12">15554</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">详细地址</td>
			<td class="td5 color-12">成都信息工程学院航空港校区</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">送餐时间</td>
			<td class="td5 color-12"> 2014 年 10 月 10 日 11:50</td>
		</tr>
	</table>
	<a href="choose_first.html" style="color:white;"><div class="border-8">重新选择送餐地址和送餐信息</div></a>
	<div class="border-3"></div>


<!--以下为选择支付方式并提交订单-->

	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="../picture/y3.png" style="width:3.5em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">选择支付方式</div>
	<div style="clear:both"></div>
	
	<div class="font-s-2 border-14" onclick="this.style.borderColor='red'" tabindex="0" onblur="this.style.borderColor='#DADADA'">
	<div class="float-1 pt-1 pl-3"><img src="../picture/money.png"/></div>
	<div  class="float-1">货到付款（现金）</div></div>

	<div class="font-s-2 border-14" onclick="this.style.borderColor='red'" tabindex="0" onblur="this.style.borderColor='#DADADA'">微信支付</div>
	<div class="border-3"></div>

	<div class="margin-l-4 font-s-2 margin-b-2"><input type="checkbox" value="" onclick="show();" id="get"/>&nbsp;获取发票</div>
	<div class="margin-l-4 font-s-2 margin-b-2" id="p1" style="display:none">填写发票抬头</div>
	<div class="margin-l-6 font-s-2" id="p2" style="display:none">
	<input type="text" size=20 maxlength="60" style="color:gray;font-size:16px" value="发票抬头" onfocus="if(this.value=='发票抬头'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='发票抬头'){this.value='发票抬头';this.style.color='gray';}" class="input1"></div>
<div style="display:" id="">
	<div class="border-8-2">不能提交订单</div>
	<div class="margin-t-4 margin-l-4">
		<div class="float-1 margin-r"><img src="../picture/warn.png"/></div>
		<div class="font-s-2 color-9 pt-3 ">
			<div class="float-1">离起送价格还差￥</div><div class="float-1" id="left">6.00</div>
		</div>
	</div>
</div>
	<div style="clear:both"></div>
<div style="display:" id="">
	<div class="float-1 margin-l-6 color-13 font-s-4 margin-t">￥<font size=7>62</font>.00</div>
	<div style="clear:both"></div>
	<div class="margin-3 font-s-2" >
	<div><img src="../picture/warn2.png"/></div>
	<div>一旦订单提交，厨房将会制作你需要的菜品，所以<font color="red">不能修改或者取消订单</font>，由此给您带来的不便，敬请包含。</div></div>
	<a href="choose_first.html" style="color:white;"><div class="border-8">提交订单</div></a>
</div>
</div>
	<div class="footer">
	<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
	</div>
</div>
</html>

