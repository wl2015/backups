if (document.getElementById){ 
document.write('<style type="text/css">\n')
document.write('.submenu{display: none;}\n')
document.write('</style>\n')
}
function test(){
//登录
		var name = document.getElementById("name").value;
		var password = document.getElementById("password").value;
		if(name == ""){
			document.getElementById("message").innerHTML = "用户名不能为空";
		}
		else if (password == ""){
			document.getElementById("message").innerHTML = "密码不能为空";
		}
		else{
			document.getElementById("message").innerHTML = "√";
		}
}
function SwitchMenu(obj){
	if(document.getElementById){
	var el = document.getElementById(obj);
	var ar = document.getElementById("left").getElementsByTagName("span"); 
		if(el.style.display != "block"){ 
			for (var i=0; i<ar.length; i++){
				if (ar[i].className=="submenu") 
				ar[i].style.display = "none";
			}
			el.style.display = "block";
		}else{
			el.style.display = "none";
		}
	}
}

function killErrors() {
return true;
}

window.onerror = killErrors;
//以上为树的js代码

//下面为鼠标放到div上，发生的变化
function f1(t,s){
	document.getElementById(t).style.color="red";
	document.getElementById(t).style.paddingLeft="15px";
	document.getElementById(t).style.backgroundColor="#fbead0";
	document.getElementById(t).style.cursor="pointer";
}
function f2(t,s){
	document.getElementById(t).style.color="white";
	document.getElementById(t).style.paddingLeft="10px";
	document.getElementById(t).style.backgroundColor="";
}
//点击切换时，左边栏会隐藏
function mfk()
{
   if(shang.style.display=='none')
    {
     shang.style.display='';
    }
   else
   {
     shang.style.display='none';
   }
}
//当鼠标放到小图片上时，则出现大图片,离开，则隐藏
function show(bold)
{	document.getElementById(bold).style.display="block";
}
function hidden(bold)
{	document.getElementById(bold).style.display="none";
	alert("aaaaaaaaaaaaaaaaaaa");
}
//库存管理，选择日期，相应的样式发生变化
//选择送餐的日期，当选中日期为当前日期，则本日期框为橙色，其他为粉色
	function chooseD1(date1,date2,date3){
		document.getElementById(date1).style.backgroundColor="#FBAF4D";
		document.getElementById(date2).style.backgroundColor="#F7D2A8";
		document.getElementById(date3).style.backgroundColor="#F7D2A8";
	}
	//选择送餐的日期，当选中日期为当前日期+1，则本日期+1框为橙色，其他为粉色
	function chooseD2(date1,date2,date3){
		document.getElementById(date2).style.backgroundColor="#FBAF4D";
		document.getElementById(date1).style.backgroundColor="#F7D2A8";
		document.getElementById(date3).style.backgroundColor="#F7D2A8";
	}
	//选择送餐的日期，当选中日期为当前日期+2，则本日期+2框为橙色，其他为粉色
	function chooseD3(date1,date2,date3){
		document.getElementById(date3).style.backgroundColor="#FBAF4D";
		document.getElementById(date2).style.backgroundColor="#F7D2A8";
		document.getElementById(date1).style.backgroundColor="#F7D2A8";
	}
//在中间名为“点击切换”的部分，鼠标放到上面，则变为手掌
function shouZ(c){
	document.getElementById(c).style.cursor="pointer";}
//在添加地址的页面，若点击了添加地址，则出现添加地址那一栏，点击取消，则隐藏
function add(s){
	document.getElementById(s).style.display="block";
}
function quxiao(s){
	document.getElementById(s).style.display="none";
}
//点击修改地址后，则，出现文本框，
function xiugai(a,b,c){
	var s=document.getElementById(c).value;
	var s=s.replace( /^\s+|\s+$/g, "" );
	alert(s);
	if(s==="修改"){
		document.getElementById(a).style.display="none";
		document.getElementById(b).style.display="block";
		document.getElementById(b).value=document.getElementById(a).innerHtml;
		document.getElementById(c).value="保存";
	}
	else if(s==="保存"){
		document.getElementById(a).style.display="block";
		document.getElementById(b).style.display="none";
		document.getElementById(c).value="修改";


	}
}