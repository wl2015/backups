$(function(){
	//console.log($('.delete'));
	$('.addSet').click(function(){
		$.ajax({
		    url:WEBROOT + '/dish/toAddForGroup.do',
		    type:'post',
		    dataType:'html',
		    success:function(data){
		      $('.content').html(data);
		    }
		  });
		//window.location = WEBROOT + '/dish/toAddForGroup.do';
	});
	$('li').each(function(){
		$(this).find('.add').last().remove();
	});
	
	$('.delete').each(function(){
		$(this).click(function(){	
			var removeElements = $(this).parent();
			$.ajax({
				url: WEBROOT + '/dish/deleteDishGroup.do',
				type:'post',
				data:{
					'groupId':$(this).attr('data')
				},					
				dataType:'json',
				//contentType:"application/json",
				success:function(res) {	
					console.log(res);
					//removeElements.remove();
					if(res.code == AJAX_SUCCESS_ALERT_CODE){
						//移除此项套餐					
						removeElements.remove();
						 /*测试将dishGroupList中的dishes字符串解析
						  * var grouplist = $.parseJSON(res.dishGroupList[0].dishes);
						 console.log(grouplist);*/
					}
					else{
						alert("删除失败，请稍后重试！");
					}										
				},
				 error:function(){
					  alert("网络连接异常，请稍后重试");
				  }
			})
		});
	});
})
