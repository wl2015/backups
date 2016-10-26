$(function(){
	loadData();
})
//加载数据
function loadData(){
	//添加“正在加载数据”的图标效果
	$('.inner').prepend('<img class="loading" src="'+WEBROOT+'/res/img/load.gif"/>');
	var contentWidth = $('.inner').width();
	var imgWidth = $('.loading').width();
	$('.loading').css({
		"position":"absolute",
		"left":"300px",
		"top":"150px",
		"z-index":100
	});
	
	$.ajax( {
		url : WEBROOT+'/merchant/MselectProceOrder.do',
		type : 'post',		
		/*dataFilter : {
			s : [ 'password2' ]
		},*/
		
		dataType : 'json',		
		success : function(res) {
			addData(res.rows);
			$('.loading').remove();
		},
		error:function(){
			alert("网络异常，请稍后重试");
			$('.loading').remove();
		}
		
	});
}

//将数据加载到页面
function addData(proceOrderlist){
	//var proceOrderlist = res.proceOrderlist;
	var tbodyhtml;
	if(proceOrderlist && proceOrderlist.length > 0){
		$.each(proceOrderlist,function(i, elem){
			//追加2行数据,一行显示，一行默认隐藏
			var dishlist = elem.dishlist;
			tbodyhtml = tbodyhtml + '<tr>'+
			'<td><img class="button" src="'+ WEBROOT + '/res/img/edit_remove.png" onclick="showDetail(this)"></td>'+
			'	<td>'+elem.oId+'</td>'+
			'	<td>'+elem.user.userName+'</td>'+			
			'	<td>'+elem.user.userPhone+'</td>'+
			'	<td>'+elem.orderTime+'</td>'+
			'	<td>'+elem.spendTime+'</td>'+	
			'	<td>'+elem.address+'</td>'+	
			'	<td>'+elem.money+'</td>'+							
			'</tr>'+
			'<tr class="new">'+
			'<td class="newCol" colspan="8">'+
			'  <table>'+
			'    <tr>'+
			'   	<td colspan="2">订餐列表</td>'+
			'    </tr>'+
			'    <tr>'+
			'		<td>菜名</td>'+
			'		<td>份数</td>'+
			'    </tr>';
			$.each(dishlist,function(j, elem2){
				tbodyhtml = tbodyhtml + 
			'	<tr>'+
			'		<td>'+elem2.dishName+'</td>'+
			'		<td>'+elem2.number+'份</td>'+
			'	</tr>';			
			});
			
			tbodyhtml = tbodyhtml + 
			'	</table>'+
			' </td>'+
			'</tr>';
		});
	}
	else{
		tbodyhtml = tbodyhtml + '<tr><td colspan="8">暂无历史订单数据</td></tr>';
	}
	$('#tt tbody').html(tbodyhtml);
}

    
  //点击图案展开和收起切换
    function showDetail(elem){
    	$(elem).parents('tr').next().toggle();
    	var imgsrc = $(elem).attr('src');//http://localhost:8080/youcai/aboutMerchant/images/edit_add.png
    	var add = 'edit_add.png';
    	var remove = 'edit_remove.png';
    	if(imgsrc.indexOf(add) >= 0){
    		$(elem).attr("src", WEBROOT + "/res/img/edit_remove.png");
    	}else if(imgsrc.indexOf(remove) >= 0){
    		$(elem).attr("src", WEBROOT + "/res/img/edit_add.png");
    	}
    }


