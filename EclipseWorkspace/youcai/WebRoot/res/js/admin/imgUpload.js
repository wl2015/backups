$(document).ready(function(){
  //alert("aaaaa");
  
  
  /**
   * 增加菜品、ajaxSubmit上传图片
   */
  $('#addDish').click(function(){
    
    //alert("dd");
    
    var name = $('#name').val();
    var intro = $('#intro').val();
    var costPrice = $('#costPrice').val();
    var pic = $('#uploadField').val();
    var retailPrice = $('#retailPrice').val();
    var taste = $('#taste').val();
    alert(name+"name........."+intro+"intro.........");
    
    /**
     * 判断是输入是否为空
     */
    if (!isEmpty(name) && !isEmpty(intro)  && !isEmpty(pic) &&
        !isEmpty(costPrice) && !isEmpty(retailPrice) && !isEmpty(taste)) {
      /**
       * 判断输入是否有非法字符
       */
      if (valiInput(name) && valiInput(intro) && valiInput(taste)) {
        
        //console.log("ssssss");
        var imgUploadUrl = '/wedding321_uploads/upload_json.jsp';
        $('#uploader').ajaxSubmit({
              dataType:  'json',
              url : imgUploadUrl,
              type: 'post',
              data:{music:'media'},
              success:function(res){
                //alert("cg");
                doAdvertiseInDataBase(res.url,name,intro,costPrice,retailPrice,taste);    
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
});


function doAdvertiseInDataBase(str,name,intro,costPrice,retailPrice,taste){
  //alert("doAdvertise!!!");
  alert(str);
  $.ajax({
    url : WEBROOT + "/dish/addDish.do",
    data:{
      name:name,
      intro:intro,
      pic:str,
      costPrice:costPrice,
      retailPrice:retailPrice,
      taste:taste
    },
    type: 'post',
    dataType: 'json',
    success: function(res) {
      //alert(str+"11111111111111");
      if(res.data == "0"){
        window.location = WEB_ROOT + '/dish/show.do';
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
   * 预览图片
   * @param file
   */
  function previewImage(file)  
  {  
    var MAXWIDTH  = 100;  
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
  


  
  