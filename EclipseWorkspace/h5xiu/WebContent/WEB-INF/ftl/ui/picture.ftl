<#-- picture -->
<#macro img
		data="" 
		top="" 
		left="" 
		width=w!"640" 
		h="" rotate="0" opacity="1" show="" speed="" delay="" borderradius="" shape="" inw="" inh=""
		intop="" inleft="" picid="" stylecolor="" styleopacity="" con="" alt="" id="" shapecolor=""
		> 

<div class="elementout" style="z-index:0;height:0;width:0px;position:relative;opacity:"${data.opacity}";-webkit-transform:rotate(${data.rotate}deg);">
	<img src='${data.con}' style="position:absolute;width:"${datat.w}";top:"${data.h}"px;left:"${data.inleft}"px" />
	<div style="position:absolute;width:100%;height:100%;"></div>
</div>
</#macro>
