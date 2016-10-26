var xmlHttp;
//创建xmlHttpRequest对象
function createXmlHttpRequest(){
	//IE
	if(window.ActiveXObject){
		try{
			//ie6+
			xmlHttp = new ActiveXObject("MSxml2.XMLHTTP");
		}catch(e){
			//ie6-(比如：ie5)
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}//Mozilla Firefox,chorme,非IE
	else if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}else{
		xmlHttp = false;
	}
}

//发起异步请求
function sendRequest(url){
	
	createXmlHttpRequest();
	
	//设置回调函数
	xmlHttp.onreadystatechange = function(){
		
		//如果成功地接受了服务器返回的结果
		if(xmlHttp.readyState == 4
				&& xmlHttp.status == 200){
			//获取服务器返回的文本|xml数据
			 var flag = xmlHttp.responseText;
			
			 if (flag) {
				//解析成json对象
				 var oj = eval(xmlHttp.responseText);
				 for (var i in oj) {
					 var id = oj[i].kucun_id;
					 var name = oj[i].dish_name;
					 var limit = oj[i].limitTop;
					 var remain = (oj[i].limitTop - oj[i].orderNum);
					 
					
					 
					 var html = "<tr id='temp'><td class='td2'>"+oj[i].dish_name+"</td>"
		 					+"<td class='td2' >"+oj[i].limitTop+"</td>"
		 					+"<td class='td2' ><a class='mr' style='margin-left:20px'  onclick='modify("+id+","+limit+")' href='#' >修改</a></td>"
		 					+"<td class='td2'>"+oj[i].orderNum+"</td>"
		 					+"<td class='td2' id='"+id+"' >"+remain+"</td></tr>"
		 					 			
					 $("#bbb").append(html);
					 
					 if (remain <= 10) {
						 
						    var rem = document.getElementById(id);
						   
							alert(name+"  库存剩余不足10份啦！");
			 				//alert(rem.innerHTML);
							rem.style.backgroundColor='#FFFF00';			
					 } 
				 }
			}
		}
	};
	
	xmlHttp.open("get",url,true);
	xmlHttp.send(null);
}
