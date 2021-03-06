$(function(){
	//封面三角形、文字的动画变换
	$('.item-js-left-cover').mouseover(function(){
		var trigle=$(this).children('span');
		var covertext=$(this).children('p');
		$(trigle).removeClass('item-left-trigle2');
		$(trigle).toggleClass('item-left-trigle1');

		$(covertext).css({
			'top':'4px',
			'left':'2px'
		});
		$(covertext).html('更换封面');
	});
	$('.item-js-left-cover').mouseout(function(){
		var trigle=$(this).children('span');
		var covertext=$(this).children('p');
		$(trigle).removeClass('item-left-trigle1');
		$(trigle).toggleClass('item-left-trigle2');

		$(covertext).css({
		'top':'0px',
		'left':'0px'
		});
		$(covertext).html('封面');
	});

	//点击'描述'出现大编辑框
	$('.item-js-center-describe p').click(function(){
		var textarea=$(this).parent().children('textarea');
		var p=$(this);
		var pvalue=$(p).text();
		$(p).css('display','none');
		$(textarea).css('display','block');
		$(textarea).focus();
		$(textarea).val(pvalue);

		//失去焦点则变回小边框
		$(textarea).blur(function(){
			$(p).show();
			$(textarea).css('display','none');
			$(p).css('display','block');
			var tvalue=$(textarea).val();
			if(tvalue==""){
				$(p).text('这里是描述哦');
			}
			else{
				$(p).text(tvalue);
			}
		});
	});
	
	//点击题目 出现文本框
	$('.item-js-center h4').click(function(){
		var h4=$(this);
		var input=$(this).parent().children('input');
		var hvalue=$(h4).html();
		$(input).css('display','block');
		$(h4).css('display','none');
		$(input).focus();
		$(input).val(hvalue);

		$(input).blur(function(){
			$(input).css('display','none');
			$(h4).css('display','block');
			var ivalue=$(input).val();
			if(ivalue==""){
				alert('aaaaaaaaa');
				$(h4).html('标题');
			}
			else{
				$(h4).html(ivalue);
			}
		});
	});
	
	$('#item-newitem').click(function(){
		$('.item-hide').css('display','block');
	});

	$('.item-hide-bottom #cancel').click(function(){
		$('.item-hide').css('display','none');
	});
	
	//模式1与模式2之间的切换
	$('#model1').click(function(){
		$(this).css('borderColor','#45C2B3');
		$('#model2').css('borderColor','#9B9B9B');
	});

	$('#model2').click(function(){
		$(this).css('borderColor','#45C2B3');
		$('#model1').css('borderColor','#9B9B9B');
	});

	//点击马上添加后，则出现一系列的标签
	$('#item-hide-left-label-add').click(function(){
		var prev=$(this).prev();
		var next=$(this).next();
		$(this).css('display','none');
		$(prev).css('display','none');
		$(next).css('display','block');
		$('#item-hide-left-label li').css('display','block');

	});

	//标签点击后颜色改变，再次点击颜色再改变
	$('#item-hide-left-label li').click(function(){
		$(this).toggleClass('color');
	});
});