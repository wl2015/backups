/**
 * 游客身份登录
 */
$(document).ready(function(){
  //点击对应的标签
  var td = document.querySelectorAll('table tr td span');
  var LabelInput = function(num){
    return function(){
      SelectLabel(num);
    }
  }
  
  for(var i=0; i<td.length; i++){
    td[i].onclick = LabelInput(i);
  }
  
  function SelectLabel(num){
    var labelvalue = document.querySelectorAll('table tr td')[num].innerHTML;
    document.querySelector('#labeltext').innerHTML = labelvalue;
  }
});

//获得景点对象并创建一个数组
function GetSight(obj){
  var sight_array = new Array;
  for(var i=0; i<obj.length; i++){
    var name = obj[i].querySelector('#sightname').innerHTML;
    var lng = obj[i].querySelector('#lng').innerHTML;
    var lat = obj[i].querySelector('#lat').innerHTML;
    var sight = {
      sightName: name,
      sightLng: lng,
      sightLat: lat
    };
    sight_array.push(sight);
  }
  return sight_array;
}

//上传到服务器
function SureLabel(){
  var labeltext = $('#labeltext span').html();
  $('#get').html("");
  //$('#mapshow').addClass('blur');
  //$('.loader').css({'display':''});
  map.clearOverlays();    //清除地图上所有覆盖物
  if(labeltext == undefined){
    alert("请先选择标签");
  }
  else{
    $('.table').css({'display':'none'});
    $('.btn-warning').css({'visibility':'hidden'});
    $('.container').append("<span id='note'>正在加载，请稍等。。。</span>");
    $.ajax({
      url: WEBROOT+'/user/getSightsByTypeName.do',
      type: 'post',
      data:{typeName: labeltext},
      success: function(res, status, xhr){
        var sight_obj = $(res).find('#sight');
        var getSightObj = GetSight(sight_obj);
        $('#get').append(sight_obj);
        //$('#mapiframe').contents().find('#con').html(res);  //父页面操作iframe
        //window.frames["mapiframe"].document.getElementById("con").innerHTML = res;
        return true;
      },
      error: function(xhr, status, info){
        alert("传值失败！");
        return false;
      },
      complete: function(){
        //$('.loader').css({'display':'none'});
        //$('#mapshow').removeClass('blur');
        $('.table').css({'display':''});
        $('.btn-warning').css({'visibility':'show'});
        $('.container #note').empty();
      }
    });
  }
}

//清空选择的label
function ClearLabel(){
  document.querySelector('#labeltext').innerHTML = "";
}

