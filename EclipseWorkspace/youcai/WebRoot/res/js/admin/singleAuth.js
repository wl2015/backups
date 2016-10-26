$(function(){
  //点击编辑信息开启编辑模式
  $('.edit').click(function(){
    //alert($('.leftContent input').length);
    $('.leftContent .canEdit').each(function(){
      $(this).removeAttr('disabled');
    });
    $(this).hide();
    $('.saveEdit').show();
  });
  //点击保存关闭编辑模式
  $('.saveEdit').click(function(){
    $('.edit').show();
    $(this).hide();
    $('.leftContent .canEdit').each(function(){
      $(this).attr('disabled','true');
    });
  });
});