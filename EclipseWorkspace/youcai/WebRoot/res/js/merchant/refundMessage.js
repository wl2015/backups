$(function(){
	//全选复选框改变
	 $('#selectAll').change(function(){
		 if(this.checked){ 
			 $("input[type='checkbox']").each(function( index,elem){
				$(elem).prop("checked", true);
				$(elem).attr("checked", true);
			 });   
		    }else{  
		    	$("input[type='checkbox']").each(function( index,elem){
					$(elem).removeAttr("checked");
					$(elem).attr("checked", false);
				 }); 
		    }
	});
	
	//加载数据
	getData();
	
	//提交已读数据到后台
	$("#SURE_BTN").click(function(){
		getSelectionsAndFlag();
	});
});

//点击图案展开和收起切换
function showDetail(elem){
	$(elem).parents('tr').next().toggle();
	var imgsrc = $(elem).attr('src');
	var add = 'edit_add.png';
	var remove = 'edit_remove.png';
	if(imgsrc.indexOf(add) >= 0){
		$(elem).attr("src", WEBROOT + "/res/img/edit_remove.png");
	}else if(imgsrc.indexOf(remove) >= 0){
		$(elem).attr("src", WEBROOT + "/res/img/edit_add.png");
	}
}
/*//加载退单详情'getDishListByoid.do?orderId='+row.orderId
$('img .button').click(function(){
	if($(this).hasClass('isShow')){
		
	}
});*/

//获取页面数据
function getData(){
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
	
	$.ajax({
 	   url: WEBROOT + '/merchant/getNotReadRefund.do',
			type:'post',			
			dataType:'json',
			/*dataFilter: {
				s:['p']
			},*/
			success : function(res) {
				loadData(res.rows);
				$('.loading').remove();				
				$(window.parent.document).find('#refundNum').html(res.total);
			},
			error : function(res){
				alert("网络异常，请稍后再试");
				$('.loading').remove();	
			}
    });
}
//将获得的数据加载到页面
function loadData(rows){
	var tbodyhtml;
	if(rows && rows.length > 0){	
		//var oFragment = document.createDocumentFragment(); 
		//参考：http://www.cnblogs.com/yunfour/archive/2011/06/21/2085911.html
		$.each(rows,function(i, elem){
			var dishlist = elem.dishlist;
			
			tbodyhtml = tbodyhtml + '<tr>' +
			'<td><img class="button" src="'+ WEBROOT + '/res/img/edit_add.png'+'" onclick="showDetail(this)"/></td>'+
			'<td>'+ elem.refundId +'</td>'+
			'<td>'+ elem.orderId +'</td>'+
			'<td>'+ elem.userName +'</td>'+
			'<td>'+ elem.userPhone +'</td>'+
			'<td>'+ elem.totalmoney +'</td>'+
			'<td>'+ elem.penalty +'</td>'+
			'<td><input type="checkbox" value='+elem.refundId+' /></td>'+			
			'</tr>'+
			'<tr class="new">'+
			'<td class="newCol" colspan="8">'+
			'<table>'+
			'<tr>'+
				'<td colspan="4">订单详情</td>'+
			'</tr>'+
			'<tr>'+
				'<td>菜名</td>'+
				'<td>份数</td>'+
				'<td>价格(元/份)</td>'+
				'<td>总额(元)</td>'+
			'</tr>';
			$.each(dishlist,function(i, elem2){
				tbodyhtml =  tbodyhtml+
				'<tr>'+
				'<td>'+elem2.dishName+'</td>'+
				'<td>'+elem2.number+'</td>'+
				'<td>'+elem2.price+'</td>'+
				'<td>'+elem2.totalMoney+'</td>'+				
				'<tr/>';
			});
			tbodyhtml = tbodyhtml +
			'</table>'+
			'</td>'+
			'</tr>';
		});			
	}
	else{
		tbodyhtml = tbodyhtml + '<tr><td colspan="8">暂无数据</td></tr>';
	}
	$('#tt tbody').html(tbodyhtml);
}

//标记datagrid选中的多行数据为已读
 function getSelectionsAndFlag(){
	 //alert("into fun");
            var refundIds = [];
            $('input[type="checkbox"]:checked').each(function(i, elem){
            	if($(elem).val() && !isNaN($(elem).val())){
            		refundIds.push( parseInt($(elem).val()));
            	}            	
   		 });
            if(refundIds && refundIds.length > 0){
            	//console.log(refundIds);
	           $.ajax({
	        	   url: WEBROOT + '/merchant/flagRefund.do',
					type:'post',
					data:JSON.stringify(refundIds),					
					dataType:'json',
					contentType:"application/json",
					/*dataFilter: {
						s:['p']
					},*/
					success:function(res) {
						if(res.code == AJAX_SUCCESS_ALERT_CODE){
							getData();
						}
						else{
							alert("标记失败，请重新标记");
						}
					},
					error:function(res){
						alert("网络异常，请稍后再试");
					}
	           })
            }
            else{
            	alert("请先选取数据");
            }
        }