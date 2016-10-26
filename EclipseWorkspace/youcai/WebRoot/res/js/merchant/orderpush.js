$(function () {
	getData();
	
	$(document).ready(function(){
		//alert(document.title+":document.title");
		if(document.title == '商家抢单'){
			run();
		}
		else{
			return;
		}
	});
	
	function run(){
		interval = setInterval(getData, "300002"); 
	}
	
	/*function getData(){
		  $.ajax ({
			  url: WEBROOT + '/merchant/mLunxunPushOrder.do',
			  type:'post',
			  dataType:'json',
			  contentType:"application/json",
			  success:function(res) {
				  loadpushorder(res.rows)
				  $(window.parent.document).find('#pushOrderNum').html(res.total);
			  },
			  error:function(){
				  alert("网络连接异常，请稍后重试");
			  }
		  });		  
	}*/
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

	  $.ajax ({
		  url: WEBROOT + '/merchant/mLunxunPushOrder.do',
		  type:'post',
		  dataType:'json',
		  contentType:"application/json",
		  success:function(res) {
			  loadpushorder(res.rows);
			  $('.loading').remove();
			  $(window.parent.document).find('#pushOrderNum').html(res.total);
		  },
		  error:function(){
			  alert("网络连接异常，请稍后重试");
			  $('.loading').remove();
		  }
	  });		  
}


   $(".sure-BTN").click(function(){
	   var sureOrderArr = new Array(); 
	   $('input[type="checkbox"]:checked').each(function(i, elem){
		   if( $(elem).val() && !isNaN($(elem).val())){
			   var sureorder = new Object();
			   sureorder.id = $(elem).val();		   
			   var spendTime = $(elem).parent().siblings(".TimeTd").find(".spendTime option:selected").val();
			   sureorder.spendTime = spendTime;
			   sureOrderArr.push(sureorder); 
		   }
		   
	   });	   
	  
	   if(sureOrderArr && sureOrderArr.length > 0){
		   //console.log(sureOrderArr);
		   $.ajax ({
				url: WEBROOT + '/merchant/mSureOrder.do',
				type:'post',
				data:JSON.stringify(sureOrderArr),					
				dataType:'json',
				contentType:"application/json",
				/*dataFilter: {
					s:['p']
				},*/
				success:function(res) {
					/* $('input[type="checkbox"]:checked').each(function(i, elem){
						 $(elem).parents('tr').remove();
					 });*/
					getData();
					 //$(window.parent.document).find('#pushOrderNum').html(0);
					//抢单失败处理
					if(res.code == AJAX_FAIL_ALERT_CODE) {
						//抢单成功,得到下一批可抢订单pushOrderList
						alert(res.msg);
					}					
				},
				error:function(res) {
					alert("网络连接异常，请稍后重试");
				}
			}); 
	   }
	   else{
		   alert("你还没有抢单");
	   }
   });
   
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
	
	
})


								
								
function loadpushorder(pushOrderList){
	var tbodyhtml="";
	if(pushOrderList && pushOrderList.length > 0){		
		$.each(pushOrderList, function(i, elem){
			var dishlist = elem.dishlist;
			tbodyhtml =  tbodyhtml+
			"<tr>"+
			'<td width="50%">';
			$.each(dishlist,function(i, elem2){
				tbodyhtml =  tbodyhtml+				
				'		<span>'+elem2.dishName+':'+elem2.number+'份</span><br/>';
			});
			tbodyhtml = tbodyhtml + 	
			'</td>'+
			'<td>'+elem.oId+'</td>'+
			'<td class="TimeTd">'+
			'	<select class="spendTime">'+	
			'		<option value="20">20分钟</option>'+
 			'		<option value="30">30分钟</option>'+
 			'		<option value="40">40分钟</option>'+
			'	</select>'+
			'</td>'+			
			'<td class="checkbox"><input type="checkbox"  value='+elem.goId+' class="sureOrder"></td>'+
			'</tr>';			
		});		
	}	
	else{
		tbodyhtml = tbodyhtml + '<tr><td colspan="4">暂无数据</td></tr>';
	}
	$('#tt tbody').html(tbodyhtml);
}