	/*更新购物车数据*/
	function updatecar(textId,count){
			$.ajax({
					url:'ListAction_updatecar',
					type:'post',
					dataType:'json',
					data:{
						textId:textId,count:count
					},
					success:function(res){
					}
				});
		}

	/*当点击点餐按钮时 ，则开始点餐*/
		function order(dishId){
		document.getElementById("order"+dishId).style.display="none";
		document.getElementById("as"+dishId).style.display="";
		document.getElementById("num"+dishId).innerHTML="1";
		updatecar(dishId, 1);
	}

	/*当点击添加按钮时，若数量没有超过剩余量，则增加，否则，不能增加菜的数量，且增加按钮变为灰色*/
	function add(dishId){
		var leftNum=parseInt(document.getElementById("left"+dishId).innerHTML);
		var sum=parseInt(document.getElementById("num"+dishId).innerHTML);
		
		if(sum +1 == leftNum){
			document.getElementById("num"+dishId).innerHTML=sum+1;
			document.getElementById("add"+dishId).style.color="#BBBBBB";
			document.getElementById("add"+dishId).style.backgroundColor="#DADADA";
			document.getElementById("add"+dishId).style.borderColor="#BBBBBB";
			updatecar(dishId, sum+1);
		}
		else if(sum<leftNum){
			document.getElementById("add"+dishId).style.color="white";
			document.getElementById("add"+dishId).style.backgroundColor="#BC825C";
			document.getElementById("add"+dishId).style.borderColor="#CEA088";
			document.getElementById("num"+dishId).innerHTML=sum+1;
			updatecar(dishId, sum+1);
		}
	}
	/*当点击减少按钮时 ，若数量为1，再减少时，则又变为点菜按钮，否则，继续减；而且，增加按钮又会变为棕色*/
	function jian(dishId){
		var sum=document.getElementById("num"+dishId).innerHTML=parseInt(document.getElementById("num"+dishId).innerHTML)-1;
			document.getElementById("add"+dishId).style.color="white";
			document.getElementById("add"+dishId).style.backgroundColor="#BC825C";
			document.getElementById("add"+dishId).style.borderColor="#CEA088";
			updatecar(dishId, sum);
		if (sum<=0)
		{
			document.getElementById("as"+dishId).style.display="none";
			document.getElementById("order"+dishId).style.display="";
		}
	}
	//在order.html页面，当菜品的数量为1时，若再继续减，则删除这个菜品，及隐藏
	function jian2(num,as,add){
		var sum=document.getElementById(num).innerHTML=parseInt(document.getElementById(num).innerHTML)-1;
			document.getElementById(add).style.color="white";
			document.getElementById(add).style.backgroundColor="#BC825C";
			document.getElementById(add).style.borderColor="#CEA088";
		if (sum<=0)
		{
			document.getElementById(as).style.display="none";
			
		}
	}
	//等点击删除按钮式，则将菜品删除
	function delete2(vege){
			document.getElementById(vege).style.display="none";
			
	}
	//是否要删除送餐地址和送餐信息
	function delete3(address){
			if(confirm("确定要删除送餐地址吗?")){
				document.getElementById(address).style.display="none";
			}
	}
	//当点击搜索时，则出现可配送的地址
	function search(ad){
		document.getElementById(ad).style.display="block";
	}
	//选择配送地址
	function chooseP(ad){
		document.getElementById(ad).style.display="block";
	}
	//选择送餐的日期，当选中日期为当前日期，则本日期框为棕色，其他为灰色
	function chooseD1(date1,date2,date3){
		document.getElementById(date1).style.color="white";
		document.getElementById(date1).style.backgroundColor="#BB825B";
		document.getElementById(date2).style.color="black";
		document.getElementById(date2).style.backgroundColor="#DADADA";
		document.getElementById(date3).style.color="black";
		document.getElementById(date3).style.backgroundColor="#DADADA";
	}
	//选择送餐的日期，当选中日期为当前日期+1，则本日期+1框为棕色，其他为灰色
	function chooseD2(date1,date2,date3){
		document.getElementById(date2).style.color="white";
		document.getElementById(date2).style.backgroundColor="#BB825B";
		document.getElementById(date1).style.color="black";
		document.getElementById(date1).style.backgroundColor="#DADADA";
		document.getElementById(date3).style.color="black";
		document.getElementById(date3).style.backgroundColor="#DADADA";
	}
	//选择送餐的日期，当选中日期为当前日期+2，则本日期+2框为棕色，其他为灰色
	function chooseD3(date1,date2,date3){
		document.getElementById(date3).style.color="white";
		document.getElementById(date3).style.backgroundColor="#BB825B";
		document.getElementById(date2).style.color="black";
		document.getElementById(date2).style.backgroundColor="#DADADA";
		document.getElementById(date1).style.color="black";
		document.getElementById(date1).style.backgroundColor="#DADADA";
	}
	//以下是对页面点击小菜品的图，出现大图片的js代码
	//弹出隐藏层
	function ShowDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth;
	// bgdiv.style.height = $(document).height();
	$("#"+bg_div).height($(document).height());
	};
	//关闭弹出层
	function CloseDiv(show_div,bg_div)
	{
	document.getElementById(show_div).style.display='none';
	document.getElementById(bg_div).style.display='none';
	};
	//当餐厅放假时，则显示不营业的图片
	function noopen(){
		document.getElementById("rest").style.display='block';
		document.getElementById("bg").style.display='block';
		document.getElementById("pg").style.display='block';
		document.getElementById("wg").style.display='block';
	}
