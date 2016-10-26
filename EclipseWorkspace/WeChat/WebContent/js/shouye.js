function getAllHeight(fours,pictures){
	var sHeight;
	if (document.compatMode == "BackCompat") { 
	cWidth = document.body.clientWidth; 
	cHeight = document.body.clientHeight; 
	sWidth = document.body.scrollWidth; 
	sHeight = document.body.scrollHeight; 
	sLeft = document.body.scrollLeft; 
	sTop = document.body.scrollTop; 
	} 
	else { //document.compatMode == "CSS1Compat" 
	cWidth = document.documentElement.clientWidth; 
	cHeight = document.documentElement.clientHeight; 
	sWidth = document.documentElement.scrollWidth; 
	sHeight = document.documentElement.scrollHeight; 
	sLeft = document.documentElement.scrollLeft == 0 ? document.body.scrollLeft : document.documentElement.scrollLeft; 
	sTop = document.documentElement.scrollTop == 0 ? document.body.scrollTop : document.documentElement.scrollTop; 
	} 
	alert(sHeight);
	var h1=document.getElementById(pictures).style.height+'px';
	var h2=sHeight-h1;
	alert(h1);
	//让四个小框的高度与宽度一致
}
function getHeight(){
	var height = document.compatMode=="CSS1Compat" ? 
		document.documentElement.scrollHeight : document.body.scrollHeight; 
	alert(height);
}