package com.yc.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yc.basic.exceptions.CustomException;
import com.yc.bean.Comment;
import com.yc.bean.Dish;
import com.yc.bean.DishGroup;
import com.yc.bean.Limits;
import com.yc.bean.Merchant;
import com.yc.bean.TargetTypeLink;
import com.yc.bean.Type;
import com.yc.service.DishService;
import com.yc.service.ManageMerService;
import com.yc.util.Constants;
import com.yc.util.Page;
import com.yc.util.ResultCode;
import com.yc.util.StringUtil;
import com.yc.util.SystemUtil;



/**
 *
 *菜品管理控制层 
 * @author 
 *
 */
@Controller
@RequestMapping("/dish")
public class DishController {

  @Autowired
  private DishService dishService;
  
  @Autowired
  private ManageMerService manageMerService;

//初始化Log4j的一个实例，让这个实例在以后的打印中，题头都带上这个类名
  Logger log=Logger.getLogger(DishController.class);
  /**
   * 菜品管理界面
   * @param request
   * @return
   */
  @RequestMapping("/show")
  public String dish(Model model,HttpServletRequest request) throws Exception{

      String pageNow = request.getParameter("pageNow");
      Page page = null;
      List<Dish> dishList = null;
      int totalCount = (int) dishService.getAllDishCount();
      if (pageNow != null) {
        page = new Page(totalCount, Integer.parseInt(pageNow));
        dishList = dishService.getAllDishs(page);
      } else {
        
        page = new Page(totalCount, 1);
        dishList = dishService.getAllDishs(page);
      }
      
      for (Dish dish : dishList) {
        dish.setTypeName(dishService.getTypeByDishId(dish.getDishId()));
      }
      
      model.addAttribute("dishList", dishList);
      model.addAttribute("page", page);
      
    return "admin/dish";
     
  }
  
  /**
   * 关键词查询菜品页面
   * @param request
   * @return
   */
  @RequestMapping("/queryDishByKeywords")
  public String queryDishByKeywords(Model model,HttpServletRequest request) throws Exception{
      
      Map<String,Object> map = new HashMap<String, Object>();
      
      String keywords = request.getParameter("keywords");
      keywords = new String(keywords.getBytes("iso8859-1"),"utf-8");//将get请求参数转码，否则是乱码
      
      request.getSession().setAttribute("keywords", keywords);
      
      String pageNow = request.getParameter("pageNow");
      Page page = null;
      List<Dish> dishList = null;
      int totalCount = (int) dishService.getDishByKeywordsCount(keywords);
      if (pageNow != null) {
        page = new Page(totalCount, Integer.parseInt(pageNow));
        map.put("page", page);
        map.put("keywords", keywords);
        dishList = dishService.queryDishByKeyWords(map);
      } else {
        page = new Page(totalCount, 1);
        map.put("page", page);
        map.put("keywords", keywords);
        dishList = dishService.queryDishByKeyWords(map);
      }
      
      model.addAttribute("dishList", dishList);
      model.addAttribute("page", page);
      
    return "admin/dishByKeywords";
     
  }
  
  /**
   * 新增菜品界面
   * @param request
   * @return
   */
  @RequestMapping("/add")
  public String add(HttpServletRequest request) throws Exception{

    List<Type> typeList = dishService.showDishType();
    
    request.getSession().setAttribute("typeList", typeList);
    
    return "admin/newDish";
  }

  /**
   * 获取单个菜品信息
   * @param request
   * @return
   */
  @RequestMapping("/getDishById")
  @ResponseBody
  public Map<String, Object> getDishById(HttpServletRequest request) throws Exception{
      
    Map<String, Object> resultMap = new HashMap<String, Object>();

    int id = Integer.parseInt(request.getParameter("id"));

    Dish dish = dishService.getDishInfoById(id);
    dish.setTypeName(dishService.getTypeByDishId(dish.getDishId()));
    System.out.println(dish.getDishId());
    System.out.println(dish.getDishName());
    System.out.println(dish.getDishIntro());
    System.out.println(dish.getDishTaste());
    
    List<Type> typeList = dishService.showDishType();
    
    resultMap.put("dish", dish);
    resultMap.put("typeList", typeList);

    return resultMap;
  }
  
  /**
   * 查询菜品
   * @param request
   * @return
   */
  @RequestMapping("/queryDishs")
  @ResponseBody
  public Map<String, Object> query(HttpServletRequest request) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      List<Dish> dishList = dishService.showAllDishs();
      
      resultMap.put("dishList", dishList);
      
      
    return resultMap;
  }
  
  /**
   * 新增菜品
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/addDish")
  @ResponseBody
  public Map<String, Object> addDish(HttpServletRequest request) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      System.out.println("hello~~!!!!!!!!!!!!!!!!!!!!!!!");
      Dish dish = new Dish();
      String name = request.getParameter("name");
      String intro = request.getParameter("intro");
      String detail = request.getParameter("detail");
      String pic = SystemUtil.removeAllTagFromHtml(request.getParameter("pic"));
      Double costPrice = Double.parseDouble(request.getParameter("costPrice"));
      Double originalPrice = Double.parseDouble(request.getParameter("originalPrice"));
      Double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));
      String taste = request.getParameter("taste");
      
      int typeId = Integer.parseInt(request.getParameter("typeId"));

      dish.setDishName(name);
      dish.setDishIntro(intro);
      dish.setDishDetail(detail);
      dish.setDishPic(pic);
      dish.setCostPrice(costPrice);
      dish.setOriginalPrice(originalPrice);
      dish.setRetailPrice(retailPrice);
      dish.setDishTaste(taste);
      
      System.out.println("菜品名："+name+"简介："+intro+"详情:"+detail+"成本价："+costPrice+"零售价："+retailPrice+"口味"+taste);
      
      /**
       * 添加菜品
       */
      dishService.addDish(dish);//添加菜品
      
      /**
       * 新菜品存入库存
       */
      List<Merchant> merchantList = manageMerService.queryMerchants();//查找所有商家id，存入List
      
      Map<String, Object> inventoryDishMap = new HashMap<String, Object>();//将菜品idList 和 菜品id存入Map，好进行MyBatis操作
      inventoryDishMap.put("merchantList", merchantList);  
      inventoryDishMap.put("dishId",  dish.getDishId());
      
      dishService.addInventoryByDish(inventoryDishMap);//新菜品生成库存
      
      /**
       * 添加菜品类别记录
       */
      TargetTypeLink targetTypeLink = new TargetTypeLink();
      targetTypeLink.setTargetId(dish.getDishId());
      targetTypeLink.setTypeId(typeId);
      targetTypeLink.setTarget(1);
      
      dishService.addTargetTypeLink(targetTypeLink);
      
      resultMap.put("data", ResultCode.SUCCESS);
    
      return resultMap;
  }
  
  
  /**
   * 删除菜品
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/deleteDish")
  @ResponseBody
  public Map<String, Object> deleteDish(HttpServletRequest request) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      int id = Integer.parseInt(request.getParameter("id"));
      
      dishService.deleteDish(id);
      dishService.deleteDishInventory(id);
      
      resultMap.put("data", ResultCode.SUCCESS);
    
    return resultMap;
  }
  
  
  /**
   * 修改菜品
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/updateDish")
  @ResponseBody
  public Map<String, Object> updateDish(HttpServletRequest request) 
          throws Exception{
      System.out.println("这里是修改菜品控制层！！！");
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      Dish dish = new Dish();
      int id = Integer.parseInt(request.getParameter("id"));
      String name = request.getParameter("name");
      String intro = request.getParameter("intro");
      String detail = request.getParameter("detail");
      String pic = SystemUtil.removeAllTagFromHtml(request.getParameter("pic"));
      Double costPrice = Double.parseDouble(request.getParameter("costPrice"));
      Double originalPrice = Double.parseDouble(request.getParameter("originalPrice"));
      Double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));
      String taste = request.getParameter("taste");
      
      int typeId = Integer.parseInt(request.getParameter("typeId"));
      System.out.println("typeId=........"+typeId);

      System.out.println("菜品名："+name+"简介："+intro+"详情："+detail+"成本价："+costPrice+"原价："+originalPrice+"零售价："+retailPrice+"口味"+taste);
      
      dish.setDishId(id);
      dish.setDishName(name);
      dish.setDishIntro(intro);
      dish.setDishDetail(detail);
      dish.setDishPic(pic);
      dish.setCostPrice(costPrice);
      dish.setOriginalPrice(originalPrice);
      dish.setRetailPrice(retailPrice);
      dish.setDishTaste(taste);
      //修改菜品
      dishService.updateDish(dish);

      //修改菜品类别链接表
      TargetTypeLink targetTypeLink = new TargetTypeLink();
      targetTypeLink.setTargetId(id);
      targetTypeLink.setTypeId(typeId);
      
      dishService.modifyTargetTypeLink(targetTypeLink);
      
      resultMap.put("data", ResultCode.SUCCESS);
    
    return resultMap;
  }

  
/**
 *菜品/套餐类别管理
 */
  
  /**
   * 新增类别
   * @param request
   * @return
   */
  @RequestMapping("/addTypeForDish")
  @ResponseBody
  public Map<String,Object> addTypeForDish(HttpServletRequest request) 
          throws Exception{

    Map<String,Object> resultMap = new HashMap<String,Object>();
      
    String typeName = request.getParameter("typeName");
    int target = Integer.parseInt(request.getParameter("target"));
    
    Type type = new Type();
    type.setTypeName(typeName);
    type.setTarget(target);
    
    dishService.addTypeForDish(type);
    
    resultMap.put("target", target);

    return resultMap;
  }
  
  
  
  /**
   * 菜品/套餐类别页面(菜品类别列表)
   * @param model
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/TypeForDish")
  public String TypeForDish(Model model,HttpServletRequest request) 
          throws Exception{
      
    String pageNow = request.getParameter("pageNow");
    Page page = null;
    List<Type> typeList = null;
    int totalCount = (int) dishService.getDishTypeCount();
    if (pageNow != null) {
      page = new Page(totalCount, Integer.parseInt(pageNow));
      typeList = dishService.showAllDishType(page);
    } else {
      
      page = new Page(totalCount, 1);
      typeList = dishService.showAllDishType(page);
    }
    
    model.addAttribute("typeList", typeList);
    model.addAttribute("page", page);
      
    return "admin/TypeForDish";
     
  }
  
  
  /**
   * 菜品/套餐类别页面(套餐类别列表)
   * @param model
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/TypeForPackage")
  public String TypeForPackage(Model model,HttpServletRequest request) 
          throws Exception{
      
    String pageNow = request.getParameter("pageNow");
    Page page = null;
    List<Type> typeList = null;
    int totalCount = (int) dishService.getPackageTypeCount();
    if (pageNow != null) {
      page = new Page(totalCount, Integer.parseInt(pageNow));
      typeList = dishService.showAllPackageType(page);
    } else {
      
      page = new Page(totalCount, 1);
      typeList = dishService.showAllPackageType(page);
    }
    
    model.addAttribute("typeList", typeList);
    model.addAttribute("page", page);
      
    return "admin/TypeForPackage";
     
  }
  
  
  
  /**
   * 删除菜品/套餐类别
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/deleteType")
  @ResponseBody
  public Map<String, Object> deleteType(HttpServletRequest request) 
          throws Exception{
     
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      int typeId = Integer.parseInt(request.getParameter("typeId"));
      int target = Integer.parseInt(request.getParameter("target"));

      System.out.println("类别编号："+typeId);

      try {
          dishService.deleteTypeForDish(typeId);
    } catch (Exception e) {
        e.printStackTrace();
        throw new CustomException("删除类别失败");
    }
      
    resultMap.put("target", target);//获取类别，判断删除成功后跳转的页面
    
    return resultMap;
  }
  
//进入套餐添加页面
  @RequestMapping("/toAddForGroup")
 public ModelAndView toAddForGroup(HttpServletRequest request,HttpServletResponse response)
     throws Exception{
    ModelAndView resultMap = new ModelAndView();
    List<Dish> dishlist = dishService.getDishForAddGroup(); 
    List<Type> typelist = dishService.selectAllGroupType();
    resultMap.addObject("dishList", dishlist);
    resultMap.addObject("typelist", typelist);
    resultMap.setViewName("admin/newSet");
    return resultMap;
  }
//添加套餐
  @RequestMapping("/addDishGroup")
  @ResponseBody
 public Map<String, Object> addDishGroup(HttpServletRequest request)
     throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    String dishes = request.getParameter("dishGroup");
    String groupName = request.getParameter("groupName");
    String groupPic = request.getParameter("groupPic");
    int groupTypeId = Integer.parseInt(request.getParameter("groupTypeId"));
/*    Double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
    Double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));*/
    String groupIntro = request.getParameter("groupIntro");
    //System.out.println(".....................dishGroup:"+dishes);
    
    DishGroup dishgroup = new DishGroup();
    dishgroup.setDishes(dishes);
    dishgroup.setGroupIntro(groupIntro);
    dishgroup.setGroupName(groupName);
    dishgroup.setGroupPic(groupPic);
/*    dishgroup.setOriginalPrice(oldPrice);
    dishgroup.setRetailPrice(retailPrice);*/
    dishgroup.setTypeId(groupTypeId);
    boolean isinsert = dishService.addDishGroup(dishgroup);
    
    
    if(isinsert){
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }else{
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }
    return resultMap;
  }
  
  //进入套餐管理页面
  @RequestMapping("/toGroupList")
  public ModelAndView toGroupList(HttpServletRequest request)
      throws Exception{
     ModelAndView resultMap = new ModelAndView();
     List<DishGroup> dishGroupList = dishService.getDishGroupList();//获取套餐列表
     
     if(dishGroupList!=null && dishGroupList.size() > 0){
       /**
        * 循环操作每个套餐记录  
        */
       for(int i=0; i<dishGroupList.size(); i++){
         //把数据库存的json字符串转化为菜品DishList对象（通过StringUtil的jsonToList）
         dishGroupList.get(i).setDishList(StringUtil.jsonToList(dishGroupList.get(i).getDishes()));
         
         double totalOriginalPrice = 0;//套餐总原价（累加）
         double totalRetailPrice = 0;//套餐总售价（累加）
         /**
          * 因为数据库Json只存了菜品Id和num，这里需要通过dishId取出其余所有信息
          */
         for (Dish dish : dishGroupList.get(i).getDishList()) {
            System.out.println("套餐名："+dishGroupList.get(i).getGroupName()+"里面的菜品Id="+dish.getDishId()); 
            Dish singleDish = dishService.getDishInfoById(dish.getDishId());
            dish.setDishName(singleDish.getDishName());
            dish.setDishIntro(singleDish.getDishIntro());
            dish.setDishDetail(singleDish.getDishDetail());
            dish.setDishPic(singleDish.getDishPic());
            dish.setCostPrice(singleDish.getCostPrice());
            dish.setOriginalPrice(singleDish.getOriginalPrice());
            dish.setRetailPrice(singleDish.getRetailPrice());
            dish.setDishTaste(singleDish.getDishTaste());
            System.out.println("在设置后的菜品名字："+dish.getDishName() );
            
            totalOriginalPrice = totalOriginalPrice + (dish.getOriginalPrice() * dish.getNum());//套餐总原价（累加）
            totalRetailPrice = totalRetailPrice + (dish.getRetailPrice() * dish.getNum());//套餐总售价（累加）
        }
         
         dishGroupList.get(i).setOriginalPrice(totalOriginalPrice);
         dishGroupList.get(i).setRetailPrice(totalRetailPrice);
         
         System.out.println("套餐总原价（累加）="+totalOriginalPrice);
         System.out.println("套餐总售价（累加）="+totalRetailPrice);
       }
       System.out.println("\n\n.........................success\n\n");
     }

     
     resultMap.addObject("dishGroupList", dishGroupList);
     resultMap.setViewName("admin/setPage");
     return resultMap;
   }
  
//删除套餐
  @RequestMapping("/deleteDishGroup")
  @ResponseBody
 public Map<String, Object> deleteDishGroup( HttpServletRequest request, HttpServletResponse response)
     throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    String groupId = request.getParameter("groupId");
    System.out.println(".....................dishGroup:"+groupId);
    boolean isDelete = dishService.deleteDishGroup(Integer.parseInt(groupId));
   /* 测试将dishGroupList数据传给js文件，解析json字符串
    * List<DishGroup> dishGroupList = dishService.getDishGroupList();
    resultMap.put("dishGroupList", dishGroupList);*/
    if(isDelete){
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }else{
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }
    return resultMap;
  }
  
}
