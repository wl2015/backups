$(function () {
	//系统消息图标及数字  
	var a1w = $('.img1').siblings('a').width();//a的宽度
	$('.img1').css("left",a1w);//图片left为a的宽度
	var img1w = $('.img1').width()/2;//图片一半宽度
	var n1w = ($('#letterNum').width())/2;//数字一半宽度
	var n1 = (a1w + img1w-n1w)+'px';//数字左边距  注意：width()方法获取的没有px，css()获取的有
	$('#letterNum').css("left",n1);
	//客户退单消息图标及数字
	var a2w = $('.img2').siblings('a').width();//a的宽度
	$('.img2').css("left",a2w);//图片left为a的宽度
	var img2w = $('.img2').width()/2;//图片一半宽度
	var n2w = ($('#refundNum').width())/2;//数字一半宽度
	var n2 = (a2w + img2w-n2w)+'px';//数字左边距
	$('#refundNum').css("left",n2);
	//客户退单消息图标及数字
	var a3w = $('.img3').siblings('a').width();//a的宽度
	$('.img3').css("left",a3w);//图片left为a的宽度
	var img3w = $('.img3').width()/2;//图片一半宽度
	var n3w = ($('#pushOrderNum').width())/2;//数字一半宽度
	var n3 = (a3w + img3w-n3w)+'px';//数字左边距
	$('#pushOrderNum').css("left",n3);
	
	//图片的top
	var liWidth = $('.list').find('li').height();//任何一个li 的高度
	var img1Height = $('.img1').height();//图片高度
	var img2Height = $('.img2').height();
	var img3Height = $('.img3').height();
	var n1Height = $('#letterNum').height();//数字高度
	var n2Height = $('#refundNum').height();
	var n3Height = $('#pushOrderNum').height();
	var img1Top = (liWidth-img1Height)/2+'px';//图片top
	var img2Top = (liWidth-img2Height)/2+'px';
	var img3Top = (liWidth-img3Height)/2+'px';
	var n1Top = (liWidth-n1Height)/2+'px';//数字top
	var n2Top = (liWidth-n2Height)/2+'px';
	var n3Top = (liWidth-n3Height)/2+'px';
	$('.img1').css("top",img1Top);
	$('.img2').css("top",img2Top);
	$('.img3').css("top",img3Top);
	$('#letterNum').css("top",n1Top);
	$('#refundNum').css("top",n2Top);
	$('#pushOrderNum').css("top",n3Top);
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
	
	//批量删除站内消息
	$('#delete-BTN').click(function(){
		 var messageIdArry = [];

		 $('input[type="checkbox"]:checked').each(function(i, elem){
			 if( $(elem).val() && !isNaN($(elem).val())){
				 messageIdArry.push( parseInt($(elem).val()));
			 }
		 });
         
         if(messageIdArry && messageIdArry.length > 0){
        	 console.log(messageIdArry);
	           $.ajax({
	        	   url: WEBROOT + '/merchant/deleteMessage.do',
					type:'post',
					data:JSON.stringify(messageIdArry),					
					dataType:'json',
					contentType:"application/json",
					/*dataFilter: {
						s:['p']
					},*/
					success : function(res) {
						if(res.code == AJAX_SUCCESS_ALERT_CODE){
							getData();							
						}
						else{
							alert("删除失败，请重新删除");
						}
					},
					error : function(res){
						alert("网络异常，请稍后再试");
					}
	           })
         }
         else{
         	alert("请先选取数据");         	
         }
 	});
})

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
 	   url: WEBROOT + '/merchant/getMessage.do',
			type:'post',			
			dataType:'json',
			/*dataFilter: {
				s:['p']
			},*/
			success : function(res) {
				if(res.code && res.code == AJAX_SESSION_TIMEOUT_CODE){
					alert(data.msg);
				}else{
					loadData(res.rows);
					 $('.loading').remove();
					$(window.parent.document).find('#letterNum').html(res.total);
				}
				
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
		$.each(rows,function(i, elem){
			tbodyhtml = tbodyhtml + '<tr>' +
			'<td>'+ elem.messageId +'</td>'+
			'<td>'+ elem.message +'</td>'+
			'<td>'+ elem.createtime +'</td>'+
			'<td><input type="checkbox" value='+elem.messageId+' /></td>'+			
			'</tr>';
		});			
	}
	else{
		tbodyhtml = tbodyhtml + '<tr><td colspan="4">暂无数据</td></tr>';
	}
	$('#tt tbody').html(tbodyhtml);
}
