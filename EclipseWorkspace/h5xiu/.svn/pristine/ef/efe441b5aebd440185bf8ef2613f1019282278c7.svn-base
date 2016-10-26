function card(data, containner){
	this.data = data;
	this.containner = containner;
	this.init(data);
}
card.prototype.init = function(data){
	var card = this;
	
	this.pages = new Array();
	for(var i in data){
		console.log("第" + i + "个页面信息");
		var newpage = new page(data[i]);
		this.pages.push(newpage);
	}
}

card.prototype.fitscreen= function(scale){
}