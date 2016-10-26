$(document).ready(function(){

  
  /**
   * 关键词查询菜品按钮
   */
  $('#serachDishByKeywords').click(function(){
    
    var keywords = $('#key').val();
    alert(keywords);

    if (!isEmpty(keywords)) {
      //移除加载图
      $('.loading').remove();
      $('.content').removeClass('contentstyle');
      //关键词查询菜品
      contentSet("dish/queryDishByKeywords.do?keywords="+keywords+"");
    } else {
        alert("关键词不能为空！");
    }
    
  });

  /**
   * 增加菜品、ajaxSubmit上传图片
   */
  $('#sureAdd').click(function(){
    
    //alert("dd");
    
    var name = $('#newName').val();
    var intro = $('#newIntro').val();
    var detail = $('#newDetail').val();
    var costPrice = $('#newCostPrice').val();
    var originalPrice = $('#originalPrice').val();
    var pic = $('#uploadField').val();
    var retailPrice = $('#newRetailPrice').val();
    var taste = $('#newTaste').val();
    var typeId = $('#typeId').val();
    //alert(name+"name........."+intro+"intro.........");
    
    /**
     * 判断是输入是否为空
     */
    if (!isEmpty(name) && !isEmpty(intro) && !isEmpty(detail)  && !isEmpty(pic) &&
        !isEmpty(costPrice) && !isEmpty(originalPrice) && !isEmpty(retailPrice) && !isEmpty(taste)) {
      /**
       * 判断输入是否有非法字符
       */
      if (valiInput(name) && valiInput(intro) && valiInput(detail) && valiInput(taste)) {
        
        console.log("ssssss");
        var imgUploadUrl = '/youcai_uploads/upload_json.jsp';
        $('#uploader').ajaxSubmit({
              dataType:  'json',
              url : imgUploadUrl,
              type: 'post',
              data:{music:'media'},
              success:function(res){
                //alert("cg");
                addDish(res.url,name,intro,detail,costPrice,originalPrice,retailPrice,taste,typeId);    
                   console.log("success:" + res);
              },
              error: function(res) {
                console.log("error:" + res);
              }
          });
      }
      else {

        alert("含有非法字符");
      }
    }
    else {
      alert("请将信息填写完整！");
    }
  });
  
  
  /**
   * 给价钱输入框只能输入数字和小数
   */
  $("input[name='newCostPrice']").keyup(function(){     
    var tmptxt=$(this).val();     
    $(this).val(tmptxt.replace(/[^\d.]/g,''));     
      }).bind("paste",function(){     
          var tmptxt=$(this).val();     
      $(this).val(tmptxt.replace(/[^\d.]/g,''));     //.replace(/\D|^0/g,'')只能输入数字，不可以小数
   }).css("ime-mode", "disabled");    
  
  $("input[name='originalPrice']").keyup(function(){     
    var tmptxt=$(this).val();     
    $(this).val(tmptxt.replace(/[^\d.]/g,''));     
      }).bind("paste",function(){     
          var tmptxt=$(this).val();     
      $(this).val(tmptxt.replace(/[^\d.]/g,''));     //.replace(/\D|^0/g,'')只能输入数字，不可以小数
   }).css("ime-mode", "disabled"); 
  
  $("input[name='newRetailPrice']").keyup(function(){     
    var tmptxt=$(this).val();     
    $(this).val(tmptxt.replace(/[^\d.]/g,''));     
      }).bind("paste",function(){     
          var tmptxt=$(this).val();     
      $(this).val(tmptxt.replace(/[^\d.]/g,''));     //.replace(/\D|^0/g,'')只能输入数字，不可以小数
   }).css("ime-mode", "disabled");    

});


/**
 * 修改菜品按钮
 */
function sureEdit(){
//alert("sureEdit");
  var id = $('#id').val();
  var name = $('#name').val();
  var intro = $('#intro').val();
  var detail = $('#detail').val();
  var pic = $('#picture').val();
  var picfile = $('#uploadField').val();
  var costPrice = $('#costPrice').val();
  var originalPrice = $('#originalPrice').val();
  var retailPrice = $('#retailPrice').val();
  var taste = $('#taste').val();
  var dishTypeId = $('#type').val();
  //alert(dishTypeId);
/*  alert(id+"id"+name+"name........."+intro+"intro.........\n"+pic+"pic......\n"
      +picfile+"picfile......"+costPrice+"costPrice....."+originalPrice+"originalPrice......"+retailPrice
      +"retailPrice....."+taste+"taste....");*/
  
  /**
   * 判断输入是否为空
   */
  if (isEmpty(pic) || (!isEmpty(pic) && !isEmpty(picfile))) {//如果没有图片或者 预览框+文件上传都有图片
    if (!isEmpty(name) && !isEmpty(intro) && !isEmpty(detail) && !isEmpty(picfile) && 
        !isEmpty(costPrice) && !isEmpty(originalPrice) && !isEmpty(retailPrice) && !isEmpty(taste)) {
      /**
       * 判断输入是否有非法字符
       */
      if (valiInput(name) && valiInput(intro) && valiInput(detail) && valiInput(taste)) {
        //alert("ssssssssssssss");
        console.log("ssssss");
        var imgUploadUrl = '/youcai_uploads/upload_json.jsp';
        //alert(imgUploadUrl);
        $('#uploader').ajaxSubmit({
              dataType:  'json',
              url : imgUploadUrl,
              type: 'post',
              data:{music:'media'},
              success:function(res){
               //alert("cg");
                
                updateDish(id,name,intro,detail,res.url,costPrice,originalPrice,retailPrice,taste,dishTypeId);
                
                console.log("success:" + res);
              },
              error: function(res) {
                console.log("error:" + res);
              }
          });
      }
      else {

        alert("含有非法字符");
      }
    }
    else {
      alert("请将信息填写完整！");
    }
  }
  else if(!isEmpty(pic) && isEmpty(picfile)){//如果本身就有图片 ,没有修改上传图片）
    
    
    if (!isEmpty(name) && !isEmpty(intro) && 
        !isEmpty(costPrice) && !isEmpty(originalPrice) && !isEmpty(retailPrice) && !isEmpty(taste)) {
      /**
       * 判断输入是否有非法字符
       */
      if (valiInput(name) && valiInput(intro) && valiInput(taste)) {
        //alert("ssssssssssssss");
        updateDish(id,name,intro,detail,pic,costPrice,originalPrice,retailPrice,taste,dishTypeId);
      }
      else {

        alert("含有非法字符");
      }
    }
    else {
      alert("请将信息填写完整！");
    }
  } 

 
}


//点击编辑弹出编辑框
function editDish(id){
  //添加遮罩层
    var maskWidth = $(document).width();
    var maskHeight = $(document).height();
    $('<div class="mask"></div>').appendTo($('body'));
    $('.mask').css({
      "opacity":"0.4",
      "background":"grey",
      "position":"absolute",
      "left":0,
      "top":0,
      "width":maskWidth,
      "height":maskHeight,
      "z-index":1
    });


  //alert(id);
  
  //alert("弹出编辑菜品栏");
  $.ajax({
    url : WEBROOT + "/dish/getDishById.do?id="+id,
    type: "post",
    dataType: "json",
    success: function(res) {

      //alert("ajax返回成功！"+res.dish+res.dish.dishName);
      
      showEditDish(res.dish,res.typeList);

      $('.editDishes').show().css("z-index",2000);
      
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
  
  
  
  //弹出修改菜品的框框
  function showEditDish(dish,typeList){    
    //拼接菜品类型选项
    var DISHTYPE = '';
    $(typeList).each(function(index){
      
      var typeId = this.typeId;
      var typeName = this.typeName;
      //var DISHTYPE= ' <option value="'+typeId+'">'+typeName+'</option>';
      DISHTYPE += ' <option value="'+typeId+'">'+typeName+'</option>';
    });
    
    var PREVIEW_CONTENT= ' <ul>'
      + '                                <li><strong>菜品名</strong><input type="text" id="name" name="name" value="'+dish.dishName+'"/></li>'
      + '                                <li><strong>菜品简介</strong><input type="text" id="intro" name="intro" value="'+dish.dishIntro+'"/></li>'
      + '                                <li class="textA"><strong>菜品详情</strong><textArea id="detail" name="detail">'+dish.dishDetail+'</textArea></li>'
      + '                                <li class="imgShow">'
      + '                                   <strong>图片</strong>'
      + '                                   <span id="pic">'
      + '                                     <div id="preview">'
      + '                                       <img alt="图片预览" id="imghead" width=150 height=100 border=0 src="'+dish.dishPic+'"> '
      + '                                       <input type="hidden" value="'+dish.dishPic+'" id="picture"/>'
      + '                                     </div>'
      + '                                   </span>'
      + '                                   <form action="" method="post" id="uploader" enctype="multipart/form-data">'
      + '                                       <input onchange="previewImage(this)" id="uploadField" type="file" name="file"/>'
      + '                                       <input type="hidden" name="dir" value="media"/>'
      + '                                   </form>'
      + '                                 </li>'
      + '                                 <li><strong>成本价</strong><input type="text" id="costPrice" name="costPrice"  value="'+dish.costPrice+'"/></li>'
      + '                                 <li class="twoInline">'+'<strong>原价</strong>'+'<input type="text" id="originalPrice" name="originalPrice" value="'+dish.originalPrice+'"/>'+'<strong class="two">零售价</strong>'+'<input type="text" id="retailPrice" name="retailPrice" value="'+dish.retailPrice+'"/>'+'</li>'
      + '                                 <li class="twoInline">'+'<strong>口味</strong>'+'<input type="text" id="taste" name="taste" value="'+dish.dishTaste+'"/>'
      +'<strong class="two">类别</strong>'+'<select id="type">'+DISHTYPE+'</select><font>当前类别：'+dish.typeName+'</font>'+'</li>'
      + '                   </ul>'
      + '                   <div class="twoButton">'
      + '                       	<div id="cancelEdit">取消编辑</div>'
      + '                   	<div id="sureEdit" onclick=sureEdit() >确定修改</div>'
      + '					</div>'	
      + '                   <input type="hidden" value="'+dish.dishId+'" id="id"/> ';

    $("#editDrag").html(PREVIEW_CONTENT);
    
    
    
    
    //点击编辑框上的取消编辑隐藏编辑框
    $('#cancelEdit').click(function(){
      $('.editDishes').hide();
    //移除遮罩层
      $('.mask').remove();

    });
    
    
    /**
     * 给价钱输入框只能输入数字和小数
     */
    $("input[name='costPrice']").keyup(function(){     
      var tmptxt=$(this).val();     
      $(this).val(tmptxt.replace(/[^\d.]/g,''));     
        }).bind("paste",function(){     
            var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/[^\d.]/g,''));     //.replace(/\D|^0/g,'')只能输入数字，不可以小数
     }).css("ime-mode", "disabled");    
    
    $("input[name='retailPrice']").keyup(function(){     
      var tmptxt=$(this).val();     
      $(this).val(tmptxt.replace(/[^\d.]/g,''));     
        }).bind("paste",function(){     
            var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/[^\d.]/g,''));     //.replace(/\D|^0/g,'')只能输入数字，不可以小数
     }).css("ime-mode", "disabled");    
  }

  
  
}

/**
 * ajax显示菜品列表
 *//*
function showDishs() {
  //alert("显示ddd菜品");
  $.ajax({
    url : WEBROOT + "/dish/queryDishs.do",
    type: "post",
    dataType: "json",
    success: function(res) {

      alert("ajax返回成功！");

      loadInfo(res.dishList);
      
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
}

*//**
 * 做循环的函数
 * @param data
 *//*
function loadInfo(data){ 
  
  $(data).each(function(index){
    var id = this.dishId;
    var name = this.dishName;
    var intro = this.dishIntro;
    var pic = this.dishPic;
    var costPrice = this.costPrice;
    var retailPrice = this.retailPrice;
    var taste = this.dishTaste;
    
   
    var PREVIEW_CONTENT= ' <li class="oneDish">'
      + '                                <div class="dishLeft">'
      + '                                   <img src='+pic+'  width="80%" height="40px">'
      + '                                 </div>'
      + '                                <div class="contentRight">'
      + '                                   <ul class="contentList">'
      + '                                       <li><strong>菜品名：</strong><span>'+name+'</span></li>'
      + '                                       <li><strong>简介：</strong><span>'+intro+'</span></li>'
      + '                                       <li><strong>成本价：</strong><span>'+costPrice+'</span></li>'
      + '                                       <li><strong>零售价：</strong><span>'+retailPrice+'</span></li>'
      + '                                       <li><strong>口味：</strong><span>'+taste+'</span></li>'
      + '                                   </ul>'
      + '                                </div>'
      + '                                <div class="operate">'
      + '                                   <div class="editDish" onclick=editDish('+id+')>编辑</div>'
      + '                                   <div onclick=deleteDish('+id+')>删除</div>'
      + '                                </div>'
      + '                   </li>';


    $("#dishList").append(PREVIEW_CONTENT);
  });
}
*/

/**
 * 增加菜品
 * @param str
 * @param name
 * @param intro
 * @param costPrice
 * @param retailPrice
 * @param taste
 */
function addDish(str,name,intro,detail,costPrice,originalPrice,retailPrice,taste,typeId){
 
  //alert(str);
  $.ajax({
    url : WEBROOT + "/dish/addDish.do",
    data:{
      name:name,
      intro:intro,
      detail:detail,
      pic:str,
      costPrice:costPrice,
      originalPrice:originalPrice,
      retailPrice:retailPrice,
      taste:taste,
      typeId:typeId
    },
    type: 'post',
    dataType: 'json',
    success: function(res) {
     // alert(str+"11111111111111");
      if(res.data == "0"){
        alert("添加菜品成功！！！");
        contentSet("dish/show.do");// window.location = WEBROOT + '/dish/show.do';
      }
      else{
        alert("跳转页面失败"); 
      }          
        
    },
    error:function() {
      alert("数据连接失败，请稍后重试");
    }
      });
}
  



/**
 * ajax修改菜品
 */

function updateDish(id,name,intro,detail,pic,costPrice,originalPrice,retailPrice,taste,dishTypeId){
  //alert("ajax修改菜品");
  //alert(dishTypeId);
  $.ajax({
    url : WEBROOT + "/dish/updateDish.do",
    type: "post",
    dataType: "json",
    data: {
      id:id,
      name:name,
      intro:intro,
      detail:detail,
      pic:pic,
      costPrice:costPrice,
      originalPrice:originalPrice,
      retailPrice:retailPrice,
      taste:taste,
      typeId:dishTypeId
    },
    success: function(res) {
      alert("修改菜品成功！");

      if(res.data == "0"){
      //移除遮罩层
        $('.mask').remove();
        contentSet("dish/show.do");//window.location = WEBROOT + '/dish/show.do';
      }
      else{
        alert("跳转页面失败"); 
      }          
    },

    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
}
/**
 * ajax删除菜品
 */
function deleteDish(id){
  $.ajax({
    url : WEBROOT + "/dish/deleteDish.do",
    type: "post",
    dataType: "json",
    data:{id:id},
    success: function(res) {
      alert("菜品删除成功!");
      if(res.data == "0"){
        $("#dishList").find(".oneDish").remove();
        //showDishs();
        contentSet('dish/show.do');
      }
      else{
        alert("跳转页面失败"); 
      }    
    },
    error:function(){
      alert("系统繁忙，请稍后操作");
    }
  });
}



  
/**
 * 验证输入框是否有非法字符
 */ 
var valiInput = function(obj){
  
  var patrn=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;  
  
  if (patrn.test(obj)) {
    
    $("#input-body").find("#errorMsg").html("含有非法字符");
    return false;
  } else {
    $("#input-body").find("#errorMsg").html("通过");
    return true;  
  }
};


/**
 * 判断为空
 */
var isEmpty = function(obj) {
  if (obj == null || obj == undefined || jQuery.trim(obj).length == 0 || obj == "" || obj === "null") {
    return true;
  }
  return false;
};





/**
 * 预览图片
 * @param file
 */
function previewImage(file)  
{  
  var MAXWIDTH  = 150;  
  var MAXHEIGHT = 100;  
  var div = document.getElementById('preview');  
  if (file.files && file.files[0])  
  {  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.onload = function(){  //成功地装载了图像时调用的事件处理程序
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
      img.width = rect.width;  
      img.height = rect.height;  
      img.style.marginLeft = rect.left+'px';  
      img.style.marginTop = rect.top+'px';  
    }  
    var reader = new FileReader();  
    reader.onload = function(evt){img.src = evt.target.result;}  
    reader.readAsDataURL(file.files[0]);  
  }  
  else  
  {  
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';  
    file.select();  
    var src = document.selection.createRange().text;  
    div.innerHTML = '<img id=imghead>';  
    var img = document.getElementById('imghead');  
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;  
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);  
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);  
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";  
  }  
}  
function clacImgZoomParam( maxWidth, maxHeight, width, height ){  
    var param = {top:0, left:0, width:width, height:height};  
    if( width>maxWidth || height>maxHeight )  
    {  
        rateWidth = width / maxWidth;  
        rateHeight = height / maxHeight;  
          
        if( rateWidth > rateHeight )  
        {  
            param.width =  maxWidth;  
            param.height = Math.round(height / rateWidth);  
        }else  
        {  
            param.width = Math.round(width / rateHeight);  
            param.height = maxHeight;  
        }  
    }  
      
    param.left = Math.round((maxWidth - param.width) / 2);  
    param.top = Math.round((maxHeight - param.height) / 2);  
    return param;  
}  

//拖拽函数
function drag(dv){
  var disX = 0;
  var disY = 0;
  dv.onmousedown = function(ev){
    var oEvent = ev || event;
    disX = oEvent.clientX-dv.offsetLeft;
    disY = oEvent.clientY-dv.offsetTop;
    document.onmousemove = function(ev){
      var oEvent = ev || event;
      var l = oEvent.clientX-disX;
      var t = oEvent.clientY-disY;
      //50表示;自动吸附，当距离边侧50px时，自动吸附到边侧
      if(l<0){   //左边
        l=0;
      }else if(l>document.documentElement.clientWidth-dv.offsetWidth){  //右边
        l=document.documentElement.clientWidth-dv.offsetWidth;
      }
      if(t<0){  //上
        t=0;
      }else if(t>document.documentElement.clientHeight-dv.offsetHeight){  //下
        t=document.documentElement.clientHeight-dv.offsetHeight;
      }
      dv.style.left = l+'px';
      dv.style.top = t+'px';
    };
    document.onmouseup = function(){
      document.onmousemove=null;
      document.onmouseup=null;
    };
    oEvent.stopPropagation();//阻止冒泡，当前div上的东西还是可以操作
  };    
}



