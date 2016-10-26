function page(data){
	this.data = data;
	this.init();
}

page.prototype.init = function(data){
	var pagedata = data;
	this.pageeffect  = pagedata['effect']?  pagedata['effect']:'toup'; //当前页面效果
	this.bgcolor     = pagedata['bgcolor']? pagedata['bgcolor']:'black'; //当前页面背景颜色
	if( pagedata['bgpic'] && (typeof pagedata['bgpic'] == "string") ){ //背景图是否存在
	     this.lazy=pagedata['bgpic'];
	     this.bgpic=pagedata['bgpic'];
	}else{
	      this.lazy='null';
	      this.bgpic='null';
	}
	
	this.bgpictop    = pagedata['bgpictop']?    pagedata['bgpictop']:0;  //当前页面背景图离顶部位置
	this.bgpicleft   = pagedata['bgpicleft']?   pagedata['bgpicleft']:0; //当前页面背景图离左边位置
	this.bgpicheight = pagedata['bgpicheight']? pagedata['bgpicheight']:0; //当前页面背景图高度
	this.bgpicwidth  = pagedata['bgpicwidth']?  pagedata['bgpicwidth']:'auto'; //当前页面背景图宽度
	this.bgcropx     = pagedata['bgcropx']?     pagedata['bgcropx']:0;
	this.bgcropy     = pagedata['bgcropy']?     pagedata['bgcropy']:0;
	this.bgcropw     = pagedata['bgcropw']?     pagedata['bgcropw']:0;
	this.bgcroph     = pagedata['bgcroph']?     pagedata['bgcroph']:0;
	this.content     = pagedata['content']?     pagedata['content']:null; //当前页面内容，即当前页面由多少部分组成
	this.opacity     = pagedata['opacity']?     pagedata['opacity']:1;    //当前页面透明度
	this.rotate      = pagedata['rotate']?      pagedata['rotate']:0;     //当前页面旋转度
	this.blur        = pagedata['blur']?        pagedata['blur']:0; //当前页面模糊度
	
	this.page        = $('<div class="page"  effect="'+ this.pageeffect+'"> </div>');
	this.pagebg      = $('<div class="pagebg" style="overflow:hidden;background:'+ this.bgcolor+'"></div>');
	console.log('bg+'+this.bgpic+this.bgpic.length);
}