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
<title>��ʼ���</title>
</head>
<body style="Font-size:63%;" leftmargin="0">

<div class="head">
<iframe src="head.html" width="100%" frameborder="0"></iframe>
</div>

<div class="margin">


<!--����Ϊ�˵�����ϸ��Ϣ-->
	<div class="float-1 pb-1 margin-b-2"><img src="../picture/y1.png" style="width:3.5em"/></div>
	<div class="pt-1 float-1 color-3 font-s-3 pb-1 margin-b-2">ȷ�ϲ�Ʒ��ʹ���Ż�ȯ</div>
	<div style="clear:both"></div>
	<div>
		<div class="float-1 margin-r"><img src="../picture/warn.png"/></div>
		<div class="font-s-2 color-9 pt-3 ">
			<div class="float-1">�����ͼ۸񻹲</div><div class="float-1" id="left">6.00</div>
		</div>
	</div>
	<div style="clear:both"></div>
	<table class="table1">
		<tr class="tr1" id="as">
			<td class="td1" onclick="delete2('as');"><img src="../picture/delete.png" style="width:1.8em"/></td>
			<td class="td2 font-s-2 color-10">�޹��ٽ��ã�����100�ݣ�����:22.00;С��:22.00</td>
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
			<td class="td2 font-s-2 color-10">�޹��ٽ��ã�����100�ݣ�����:22.00;С��:22.00</td>
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
			<td class="td2 font-s-2 color-10">�޹��ٽ��ã�����100�ݣ�����:22.00;С��:22.00</td>
			<td class="td3">
			<div class="float-2  margin-t-4 border-13" style="display:none" id="over">����</div>
			<div id="as">
					<div class="border-min-12 float-2 margin-l-3" id="add" onclick="add('add','num','left');">+</div>
					<div  class="border-min-32 float-2 margin-l-3" id="num" >1</div>
					<div class="border-min-22 float-2"  onclick="jian2('num','as','add');">-</div>
			</div>
			</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td></td>
			<td>�Ͳͷ�</td>
			<td>6.00</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td></td>
			<td>����</td>
			<td class="color-11">��28.00</td>
		</tr>
	</table>

	<div class="margin-t-3 float-1 pb-1 margin-b-2"><img src="../picture/tuijian.png" style="width:3.5em"/></div>
	<div class="margin-t-3 float-1 color-3 font-s-3 pb-1 margin-b-2">�Ƽ�����</div>
	<div style="clear:both"></div>
	<div class="border-3"></div>

<!--����Ϊ�û��Ķ�����Ϣ-->

	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="../picture/y2.png" style="width:3.2em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">ȷ���Ͳ���Ϣ</div>
	<table class="table1">
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">ʳ������</td>
			<td class="td5 color-12">������������</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">��ϵ�绰</td>
			<td class="td5 color-12">15554</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">��ϸ��ַ</td>
			<td class="td5 color-12">�ɶ���Ϣ����ѧԺ���ո�У��</td>
		</tr>
		<tr class="tr2 font-s-2 color-10">
			<td class="td4">�Ͳ�ʱ��</td>
			<td class="td5 color-12"> 2014 �� 10 �� 10 �� 11:50</td>
		</tr>
	</table>
	<a href="choose_first.html" style="color:white;"><div class="border-8">����ѡ���Ͳ͵�ַ���Ͳ���Ϣ</div></a>
	<div class="border-3"></div>


<!--����Ϊѡ��֧����ʽ���ύ����-->

	<div class="pt-1 float-1 pb-1 margin-b-2"><img src="../picture/y3.png" style="width:3.5em"/></div>
	<div class="pt-3 float-1 color-3 font-s-3 pb-1 margin-b-2">ѡ��֧����ʽ</div>
	<div style="clear:both"></div>
	
	<div class="font-s-2 border-14" onclick="this.style.borderColor='red'" tabindex="0" onblur="this.style.borderColor='#DADADA'">
	<div class="float-1 pt-1 pl-3"><img src="../picture/money.png"/></div>
	<div  class="float-1">��������ֽ�</div></div>

	<div class="font-s-2 border-14" onclick="this.style.borderColor='red'" tabindex="0" onblur="this.style.borderColor='#DADADA'">΢��֧��</div>
	<div class="border-3"></div>

	<div class="margin-l-4 font-s-2 margin-b-2"><input type="checkbox" value="" onclick="show();" id="get"/>&nbsp;��ȡ��Ʊ</div>
	<div class="margin-l-4 font-s-2 margin-b-2" id="p1" style="display:none">��д��Ʊ̧ͷ</div>
	<div class="margin-l-6 font-s-2" id="p2" style="display:none">
	<input type="text" size=20 maxlength="60" style="color:gray;font-size:16px" value="��Ʊ̧ͷ" onfocus="if(this.value=='��Ʊ̧ͷ'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='��Ʊ̧ͷ'){this.value='��Ʊ̧ͷ';this.style.color='gray';}" class="input1"></div>
<div style="display:" id="">
	<div class="border-8-2">�����ύ����</div>
	<div class="margin-t-4 margin-l-4">
		<div class="float-1 margin-r"><img src="../picture/warn.png"/></div>
		<div class="font-s-2 color-9 pt-3 ">
			<div class="float-1">�����ͼ۸񻹲</div><div class="float-1" id="left">6.00</div>
		</div>
	</div>
</div>
	<div style="clear:both"></div>
<div style="display:" id="">
	<div class="float-1 margin-l-6 color-13 font-s-4 margin-t">��<font size=7>62</font>.00</div>
	<div style="clear:both"></div>
	<div class="margin-3 font-s-2" >
	<div><img src="../picture/warn2.png"/></div>
	<div>һ�������ύ������������������Ҫ�Ĳ�Ʒ������<font color="red">�����޸Ļ���ȡ������</font>���ɴ˸��������Ĳ��㣬���������</div></div>
	<a href="choose_first.html" style="color:white;"><div class="border-8">�ύ����</div></a>
</div>
</div>
	<div class="footer">
	<iframe src="footer.html" width="100%" height="250px" frameborder="0" scrolling=no style="margin:0px;padding:0px"></iframe>
	</div>
</div>
</html>

