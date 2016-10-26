$(document).ready(function(){
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