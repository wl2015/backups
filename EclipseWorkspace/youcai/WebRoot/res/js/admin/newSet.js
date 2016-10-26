$(function(){
  
  /**
   * 确定新增
   */
  $('.sureNewSet').click(function(){
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
      var selectDish = [];//var obj = {} 相当于new Object()      var arr = [] 相当于new Array()
      var text="";
      var totalOriginalPrice = 0;
      var totalRetailPrice = 0;
      /**把每个被选中的菜品信息提取出来
       * <input type="text" value="0" 
        data='{"id":${item.dishId },"dishName":"${item.dishName }","price":${item.original_price },
        "dishPic":"${item.dishPic }","dishTaste":"${item.dishTaste }"}' />
       */
      $('li input').each(function(){
          if($(this).val() > 0){
            var data = $.parseJSON($(this).attr('data'));//jQuery.parseJSON()函数用于将格式完好的JSON字符串转为与之对应的JavaScript对象。
            //转化出来的data也就是Object对象（Javascript对象）                                             //所谓"格式完好"，就是要求指定的字符串必须符合严格的JSON格式，例如：属性名称必须加双引号、字符串值也必须用双引号。            
            var selectdish = new Object();
            selectdish.dishId=data.id;
/*            selectdish.dishName = data.dishName;
            selectdish.dishPic = data.dishPic;
            selectdish.original_price = data.price;
            selectdish.dishTaste = data.dishTaste;*/
            selectdish.num = parseInt($(this).val());//获取数量
            
            selectDish.push(selectdish);//push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。selectDish就是前面定义的一个数组
            totalOriginalPrice = totalOriginalPrice + data.originalPrice * selectdish.num;
            totalRetailPrice = totalRetailPrice + data.retailPrice * selectdish.num;
            text=text+'<span class="dishName">'+data.dishName+'</span><span class="dishNum">(<strong>'+selectdish.num+'</strong>)份</span><span class="add">+</span>';            
          }
      });
      
      //console.log((JSON.stringify(selectDish)));
      //console.log(totalPrice);
      $('#groupName').maxLength(25);
      $('.group').html(text.substr(0,text.length-26));
      $('#oldPrice').html(totalOriginalPrice);//预显示套餐原价（累加）
      $('#salePrice').html(totalRetailPrice);//预显示套餐售价（累加）
    $('.forSetName').show();
    /*$('#retailPrice').keyup(function(){
        var retailPrice = $('#retailPrice').val();
        $('#retailPrice').val(retailPrice.replace(/[\W]/g,''));
    });
*/    
    
    
    
    //取消添加
    $('.cancelSet').click(function(){
      $('.forSetName').hide();
        //移除遮罩层
         $('.mask').remove();
    });
    
    //确定添加
    $('.sureSet').click(function(){
      var picurl;
      //图片上传函数      
        var UPLOAD_URL_ROOT = "/youcai_uploads";
        var UPLOAD_URL_IMG = "/upload_json.jsp";
        $("#fileForm").ajaxSubmit({
          dataType:  'json',
                  url : UPLOAD_URL_ROOT + UPLOAD_URL_IMG,
                  type: 'post',
                  success:function(res){
                      if(res.error == 0) {                    
                        picurl = res.url;
                        submitFunction();
                         //alert('上传成功');
                      }
                      else{
                         alert(res.msg+"...............faile");
                         console.log(res);
                      }               
                  },
                  error:function(){
                     alert("网络连接失败，请稍后重试1");
                  }
        });
        //console.log('url.......................'+picurl);
       /**
        * 提交新增套餐信息，添加到数据库
        */
      var submitFunction = function(){
        var data = checkForm();
        if(data != null && selectDish && selectDish.length > 0){      
        $.ajax({
          url: WEBROOT + '/dish/addDishGroup.do',//'/dish/addDishGroup.do'
          type:'post',
          data:{
            'dishGroup':JSON.stringify(selectDish),//stringify()用于从一个对象解析出字符串，如  var a = {a:1,b:2} 结果：  JSON.stringify(a) 得 "{"a":1,"b":2}"
            'groupName':data.groupName,
            'retailPrice':data.retailPrice,
            'groupTypeId':data.groupType,
            //'oldPrice' :totalPrice,
            'groupPic': picurl,
            'groupIntro':data.groupIntro
          },          
          dataType:'json',
          //contentType:"application/json",
          success:function(res) {          
            $('.forSetName').hide();
            //移除遮罩层
            $('.mask').remove();          
          },
          error:function(){
            alert("网络异常，请稍后重试");
           }
        });
        }
        else{
          alert("请先选取菜品并完善表单");
        }
      }
      
  });
  });
  
  function checkForm(){
    var groupName = $('#groupName').val();
    //var retailPrice = $('#retailPrice').val();
    var groupType = $('#groupType').find("option:selected").attr('data');//返回属性值    返回被选元素的属性值。
    var groupIntro = $('#groupIntro').val();
    console.log(groupType);
    //var groupPic = $('#groupPic').val();
    if(!CHECK.isEmpty(groupName) && 
        !CHECK.isEmpty(groupType) && !CHECK.isEmpty(groupIntro)){
      return  data = {'groupName':groupName,'groupType':groupType,'groupIntro':groupIntro};      
    }else{
      return null;
    }
  }
  
});