$(function(){
	loadData('first');
	
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
	
	//批量打分
	$('.evalue-BTN').click(function(){
		 var evaluArr = new Array(); 
		 $('input[type="radio"]:checked').each(function(i, elem){		
			 var evalueOrder = new Object();
			 evalueOrder.cord=$(elem).val();
			 evalueOrder.orderId = $(elem).attr('name');
			 //alert('$(elem).val():'+$(elem).val()+".................$(elem).attr('name')："+$(elem).attr('name'));
			 evaluArr.push(evalueOrder);	
		 })
		 
		  if(evaluArr && evaluArr.length > 0){
		   $.ajax ({
				url: WEBROOT + '/merchant/evalueOrder.do',
				type:'post',
				data:JSON.stringify(evaluArr),
					/*{
					sureOrderArr:sureOrderArr
				},*/
				dataType:'json',
				contentType:"application/json",
				/*dataFilter: {
					s:['p']
				},*/
				
				
				success:function(res) {
					//批量加载历史订单数据
					/*addData(res.historyOrderlist);
					if(res.code == AJAX_FAIL_ALERT_CODE){
						alert(res.deletemsg);
					}*/
					//alert("sucess");
					if(res.code == AJAX_SUCCESS_ALERT_CODE){
						$('input[type="radio"]:checked').each(function(i, elem){		
							$(elem).parent().parent().html("已评价")	;
						 })
					}else{
						alert("打分失败，请稍后重试");
					}
					 
				},
				error:function(){
					alert("网络异常，请稍后重试");
			   }
		   })
		  }else{
			  alert("请先对订单打分");
		  }
	 });
	
	//批量删除
	 $(".dele-BTN").click(function(){
		 var OrderArr = new Array(); 
		 $('input[type="checkbox"]:checked').each(function(i, elem){
			
			 if($(elem).val() && !isNaN($(elem).val())){
				 OrderArr.push(parseInt($(elem).val()));		
			 }				 
		 })
		 
		  if(OrderArr && OrderArr.length > 0){
			  //console.log(OrderArr);
		   $.ajax ({
				url: WEBROOT + '/merchant/mdeleteOrder.do',
				type:'post',
				data:JSON.stringify(OrderArr),
					/*{
					sureOrderArr:sureOrderArr
				},*/
				dataType:'json',
				contentType:"application/json",
				/*dataFilter: {
					s:['p']
				},*/
				
				
				success:function(res) {
					//批量加载历史订单数据
					loadData("now");
					if(res.code == AJAX_FAIL_ALERT_CODE){
						alert(res.deletemsg);
					}					
				},
				error:function(){
					alert("网络异常，请稍后重试");
			   }
		   })
		  }else{
			  alert("请先勾选数据");
		  }
	 });
	 
	 
})

//获取并加载后台数据
function getPage(str){
	var pageNow = parseInt($('.pageNow').val());
	var pageTotal = parseInt($('.pageTotal').html());
	//alert('pageNow:'+pageNow+'.....................pageTotal:'+pageTotal);	
	var pageNum = 1;
	switch(str)
	{
		case 'first':
			pageNum = 1;
		  break;
		case 'pre':
			pageNum = parseInt(pageNow-1);
		  break;
		case 'next':
			pageNum = parseInt(pageNow+1);
			  break;
		case 'last':
			pageNum = pageTotal;
			  break;
		default:
			pageNum = pageNow;
	}
	if(pageNum < 1){
		pageNum = 1;
	}
	if(pageNum > pageTotal){
		pageNum = pageTotal;
	}
	return pageNum;
	
}
function loadData(str){
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
	
	var pagenum = getPage(str)? getPage(str):1;
	//alert(pagenum);
	$.ajax( {
		url : WEBROOT + '/merchant/mgetHistoryOrder.do',
		type : 'post',		
		/*dataFilter : {
			s : [ 'password2' ]
		},*/
		data:{
			'pageNum' : pagenum
		},		
		dataType : 'json',		
		success : function(res) {
			$('.pageNow').val(pagenum);
			$('.pageTotal').html(Math.ceil(res.total/10));
			if(pagenum == 1){
				$('.first').hide();
				$('.pre').hide();
			}
			else{
				$('.first').show();
				$('.pre').show();
			}
			if(pagenum == $('.pageTotal').html()){
				$('.next').hide();
				$('.last').hide();
			}
			else{
				$('.next').show();
				$('.last').show();
			}
			addData(res.rows);
			$('.loading').remove();	
		},
		error:function(){
			alert("网络异常，请稍后重试");
			$('.loading').remove();	
		}
		
	});
}


function addData(historyOrderlist){
	var tbodyhtml = "";
	if(historyOrderlist && historyOrderlist.length > 0){
		$.each(historyOrderlist,function(i, elem){
			//追加2行数据,一行显示，一行默认隐藏
			var dishlist = elem.dishlist;
			tbodyhtml = tbodyhtml + 
			'<tr>'+
			'<td><img class="button" src="'+ WEBROOT + '/res/img/edit_add.png" onclick="showDetail(this)"></td>'+
			'<td>'+elem.oId+'</td>' +
			'<td>'+elem.user.userName+'</td>'+
			'<td>'+elem.orderTime+'</td>'+
			'<td>'+elem.money+'</td>'+
			/*打分*/
			'<td>';
			if(elem.uEvaluate == 1){
				tbodyhtml = tbodyhtml +'<span>已评价</span>';
			}else{
				tbodyhtml = tbodyhtml + addradio(elem.oId);
			}
			tbodyhtml = tbodyhtml +
			'</td>'+
			/*删除选择*/
			'<td><input type="checkbox" value='+elem.oId+' /></td>'+			
			'</tr>'+
			'<tr class="new">'+
			'<td class="newCol" colspan="7">'+
				'<table>'+
					'<tr>'+
						'<td class="phone" colspan="3">订餐者联系电话：<span>'+elem.user.userPhone+'</span></td>'+
				    '</tr>'+
				    '<tr>'+
					'<td class="address" colspan="3">送餐地址：<span>'+elem.address+'</span></td>'+
				    '</tr>'+
				    '<tr>'+
					'<td colspan="3">订餐详情</td>'+
				    '</tr>'+
				    '<tr>'+
					'<td>菜名</td>'+
					'<td>单价</td>'+
					'<td>分数</td>'+
				    '</tr>';			
			
			$.each(dishlist,function(j, elem2){
				tbodyhtml = tbodyhtml + 
				'<tr>'+
				'<td>'+elem2.dishName+'</td>'+
				'<td>'+elem2.price+'</td>'+
				'<td>'+elem2.number+'</td>'+
				'</tr>';
			});
			tbodyhtml = tbodyhtml + 
			'</table>'+
			'</td>'+
			'</tr>';
		});
	}
	else{
		tbodyhtml = tbodyhtml + 
		'<tr>'+
		'<td colspan="7">暂无数据</td>'+
		'</tr>';
	}
	$('#tt tbody').html(tbodyhtml);
	
	function addradio(oId){		
		var tbodyhtml = 
		'<span><input name='+oId+' type="radio" value="1" />1 </span> '+
		'<span><input name='+oId+' type="radio" value="2" />2 </span> '+ 
		'<span><input name='+oId+' type="radio" value="3" />3 </span> '+
		'<span><input name='+oId+' type="radio" value="4" />4 </span> '+
		'<span><input name='+oId+' type="radio" value="5" />5 </span> ';	/* checked="checked" */
		return tbodyhtml;
	}

}

function toggledetail(node){
	/*alert($(node).html());
	alert($(node).parent().parent().next().html());
	//$(node).parent().parent().next().show();
	alert($(node).hasClass("active"));*/
    //通过判断按钮btn有没有active这个class名判断是否已经点击过
    if($(node).hasClass("active")){
	    //如果有了active，假设已经点击过了
	    //执行你的代码
    	$(node).parent().parent().next().hide();	
	    //把active去掉
	    $(node).removeClass("active");
    }else{
	    //没有active，假设还没有点击过	    	
	    //执行你的代码
	    $(node).addClass("active");
	    $(node).parent().parent().next().show();
    }
}

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

