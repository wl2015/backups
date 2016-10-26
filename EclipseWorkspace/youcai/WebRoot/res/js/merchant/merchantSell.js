$(function (){
	var now = new Date();
    var month = (now.getMonth() + 1);               
    var day = now.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    var today = now.getFullYear() + '-' + month + '-' + day;
    var firstDay = now.getFullYear() + '-' + month + '-01';
    $('#dateTo').val(today);
    $('#dateFrom').val(firstDay);
    

	 //初始化销售额列表视图
	 doSearch();	 
});


//设置日期格式
function myformatter(str){ 
	 var date = new Date(str);
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
}

function doSearch(){
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
	
	//不要用常规的js方法用在easyui组件上，一般都要使用easyui组建的相关方法
	var dateFrom = $('#dateFrom').val();
    var dateTo = $('#dateTo').val();
	var handelStatus = $('#handelStatus option:selected') .val();
	var refundStatus = $('#refundStatus option:selected').val();
	console.log(dateFrom+":dateFrom.....dateTo："+dateTo);
	//起始日期大于截止日期
	if(dateCompare(dateFrom, dateTo)  == 1){
		alert("起始日期不能大于截止日期");
		return false;
	}
	else{
		$.ajax( {
			url : WEBROOT + '/merchant/getMerchantSells.do',
			type : 'post',
			data : {
				startDate : myformatter(dateFrom),
				endDate:myformatter(dateTo),
				//pageNum:currPage,
				handelStatus:handelStatus,
				refundStatus:refundStatus
			},
			dataType : 'json',
			success : function(res) {
				loadData(res.rows, res.footer);	
				$('.loading').remove();
			},
			error: function(){
				alert("网络异常");
				$('.loading').remove();
			}
		});
	}	
}
//比较日期大小
function dateCompare(date1,date2){		
	var time1 = new Date(date1).getTime();
	var time2 = new Date(date2).getTime();
	if(time1 > time2){
		return 1;
	}else if(time1 == time2){
		return 2;
	}else{
		return 3;
	}
}
//将得到的数据加载到页面
/*<tr>
<th>订单号</th>
<th>订单金额</th>
<th>预付款时间</th>
<th>结算状态</th>
<th>退款状态</th>
</tr>*/
function loadData(rows, total){
	var refundStatus = ['正常', '退款申请中', '已退款'];
	adStatus = ['还未结算','已结算'];
	if(rows && rows.length > 0){
		var tbodyhtml;
		$.each(rows,function(i, elem){
			tbodyhtml = tbodyhtml + '<tr>' +
			'<td>'+ elem.orderId +'</td>'+
			'<td>'+ elem.money +'</td>'+
			'<td>'+ elem.advanceTime +'</td>'+
			'<td>'+ adStatus[elem.advanceStatus] +'</td>'+
			'<td>'+ refundStatus[elem.refund] +'</td>'+
			'</tr>';
		});	
				
		$.each(total,function(i, elem){
			tbodyhtml = tbodyhtml + '<tr>';
			if(i == 0){
				tbodyhtml = tbodyhtml +
				'<td rowspan="'+total.length+'">总计</td>';				
			}		
			tbodyhtml = tbodyhtml + '<td colspan="4">'+elem.summary+'</td>'+
			'</tr>';
						
		});
	}
	else{
		tbodyhtml = tbodyhtml + '<tr><td colspan="5">暂无数据</td></tr>';
	}
	$('#tt tbody').html(tbodyhtml);
}
