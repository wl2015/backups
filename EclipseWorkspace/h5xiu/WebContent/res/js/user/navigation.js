//点击选项变颜色

$(function(){
	
	$('#program').addClass('clickdown');
	
	$('#program').click(function(){
		$('.clickdown').removeClass('clickdown');
		$(this).addClass('clickdown');
	});
	
	$('#editor').click(function(){
		$('.clickdown').removeClass('clickdown');
		$(this).addClass('clickdown');
	});
	
	$('#demo').click(function(){
		$('.clickdown').removeClass('clickdown');
		$(this).addClass('clickdown');
	});
	
	$('#hot').click(function(){
		$('.clickdown').removeClass('clickdown');
		$(this).addClass('clickdown');
	});
	
	$('#notice').click(function(){
		$('.clickdown').removeClass('clickdown');
		$(this).addClass('clickdown');
	});
	
	$('#help').click(function(){
		$('.clickdown').removeClass('clickdown');
		$(this).addClass('clickdown');
	});
});