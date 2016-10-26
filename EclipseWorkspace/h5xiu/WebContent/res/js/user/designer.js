/*显示作品名称*/
function showItemName(){
	
	$('.designer-js-show li').mouseover(function(){
		$(this).find('span').show();
	});
	$('.designer-js-show li').mouseout(function(){
		$(this).find('span').hide();
	});
}

/*遮盖层开关的相关按钮*/
function showAndCloseBTN(){
	$('.designer-js-Btn').click(function(){
		$('#designer-background').css('display','block');
		$('#designer-bg-information').css('display','block');
	});
	$('.designer-js-Btnclose').click(function(){
		$('#designer-background').css('display','none');
		$('#designer-bg-information').css('display','none');
	});
	$('.designer-js-x').click(function(){
		$('#designer-background').css('display','none');
		$('#designer-bg-information').css('display','none');
	});
}