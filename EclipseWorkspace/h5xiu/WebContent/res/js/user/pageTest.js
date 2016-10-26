$(function(){
	//showDesigner();
	//showHotDemo();
	showItem();
	$('#demo').click(function(){
		showDemo();
		var demo_block=$(".demo-block-cover");
		
		demo_block.mouseover(function(){
			$(this).css({"opacity":"1"}),
			$(".btn-content").css({"opacity":"1"});
		});
		demo_block.mouseout(function(){
			$(this).css({"opacity":"0"}),
			$(".btn-content").css({"opacity":"0"});
		});
		
	});
	
	$('#editor').click(function(){
		showDesigner();
		showItemName();
		showAndCloseBTN();
	});
	
	$('#hot').click(function(){
		showHotDemo();
		var qrcode = $(".qrcode");
		qrcode.mouseover(function(){
			$(this).css({"opacity":"1"});
		});
		qrcode.mouseout(function(){
			$(this).css({"opacity":"0"});			
		});
	});
	
	$('#program').click(function(){
		showItem();
	});
	
	$('#notice').click(function(){
		showDemand();
		$("#demand-ntitle").click(function(){
			if($("#demand-paragraph").css("display")=="none"){
				$("#demand-paragraph").css("display","block");
				$("#demand-nbtn").css("border-color","#F5F5F5 #F5F5F5 #000000 #F5F5F5");
				$("#demand-nbtn").css("top","-8px");
			}else{
				$("#demand-paragraph").css("display","none");
				$("#demand-nbtn").css("border-color","#000000 #F5F5F5 #F5F5F5 #F5F5F5");
				$("#demand-nbtn").css("top","8px");
			}
		});
		$("#demand-nbtn").click(function(){
			if($("#demand-paragraph").css("display")=="none"){
				$("#demand-paragraph").css("display","block");
				$("#demand-nbtn").css("border-color","#F5F5F5 #F5F5F5 #000000 #F5F5F5");
				$("#demand-nbtn").css("top","-8px");
			}else{
				$("#demand-paragraph").css("display","none");
				$("#demand-nbtn").css("border-color","#000000 #F5F5F5 #F5F5F5 #F5F5F5");
				$("#demand-nbtn").css("top","8px");
			}
		});
	});
	
	$('#userinfo').click(function(){
		showUserinfo();
	});
});
