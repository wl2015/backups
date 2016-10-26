$(function () {
    // 限制使用了onlyNum类样式的控件只能输入数字
    //$('.onlyNum').onlyNum();
  //$('.vertify-input').onlyNum();
  //$('.vertify-phone').onlyNum();
    //限制使用了onlyAlpha类样式的控件只能输入字母
    //$(".onlyNumAlpha").onlyNumAlpha();
  
   });


 // 限制只能输入字母(只适用于IE浏览器)
 $.fn.onlyAlpha = function () {
     $(this).keypress(function (event) {
         var eventObj = event || e;
         var keyCode = eventObj.keyCode || eventObj.which;
         if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122))
            return true;
        else
             return false;
     }).focus(function () {
        this.style.imeMode = 'disabled';
     }).bind("paste", function () {
         var clipboard = window.clipboardData.getData("Text");
         if (/^[a-zA-Z]+$/.test(clipboard))
             return true;
        else
             return false;
     });
 };
 //过滤sql非法字符
 function sqlFilter(arrayobj){
	 var re3 = /select|update|delete|truncate|join|union|exec|insert|drop|count|from|create|destory|alter|or|like|count|chr|mid|master|char|declare|table|xp_cmdshell|’|"|;|>|<|--|%|;|'/i;
	// var re2 = /select|update|delete|truncate|join|union|exec|insert|drop|count|’|"|;|>|<|%/i;
	 //var re = /^\?(.*)(select%20|insert%20|delete%20from%20|count\(|drop%20table|update%20truncate%20|asc\(|mid\(|char\(|xp_cmdshell|exec%20master|net%20localgroup%20administrators|\"|:|net%20user|\|%20or%20)(.*)$/gi;
	var e;
	for(var i=0; i<arrayobj.length; i++){
		e = re3.test(arrayobj[i]);
		if(e) {
		    alert("你输入的数据中含有非法字符,请先去除其中的非法字符");
		    return false;
		}
	}	 
	 return true;
 }
// 限制只能输入数字(只适用于IE浏览器)

$.fn.onlyNum = function () {

    $(this).keypress(function (event) {
        var eventObj = event || e;
        var keyCode = eventObj.keyCode || eventObj.which;
        if ((keyCode >= 48 && keyCode <= 57 || keyCode==8 || keyCode==37 || keyCode==39 || keyCode==9))
            return true;
        else
            return false;
    }).focus(function () {
    //禁用输入法
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
    //获取剪切板的内容
        var clipboard = window.clipboardData.getData("Text");
        if (/^\d+$/.test(clipboard))
            return true;
        else
            return false;
    });
};


// 限制只能输入数字和字母(只适用于IE浏览器)

$.fn.onlyNumAlpha = function () {
    $(this).keypress(function (event) {
        var eventObj = event || e;
        var keyCode = eventObj.keyCode || eventObj.which;
        if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode==8 || keyCode==37 || keyCode==39 || keyCode==9)
            return true;
        else
            return false;
    }).focus(function () {
        this.style.imeMode = 'disabled';
    }).bind("paste", function () {
        var clipboard = window.clipboardData.getData("Text");
        if (/^(\d|[a-zA-Z])+$/.test(clipboard))
            return true;
        else
            return false;
    });
};

/*限制输入长度*/
$.fn.maxLength = function(max){
        this.each(function(){
            var type = this.tagName.toLowerCase();
            var inputType = this.type? this.type.toLowerCase() : null;
            if(type == "input" && inputType == "text" || inputType == "password"){
                //Apply the standard maxLength
                this.maxLength = max;
            }
            else if(type == "textarea"){
                this.onkeypress = function(e){
                    var ob = e || event;
                    var keyCode = ob.keyCode;
                    var hasSelection = document.selection? document.selection.createRange().text.length > 0 : this.selectionStart != this.selectionEnd;
                    return !(this.value.length >= max && (keyCode > 50 || keyCode == 32 || keyCode == 0 || keyCode == 13) && !ob.ctrlKey && !ob.altKey && !hasSelection);
                };
                this.onkeyup = function(){
                    if(this.value.length > max){
                        this.value = this.value.substring(0,max);
                    }
                };
            }
        });
    };