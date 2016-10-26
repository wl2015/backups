package com.yc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.basic.exceptions.CustomException;
import com.yc.bean.Advance;
import com.yc.bean.Comment;
import com.yc.bean.Dish;
import com.yc.bean.DishGroup;
import com.yc.bean.DishList;
import com.yc.bean.Merchant;
import com.yc.bean.MerchantStar;
import com.yc.bean.Order;
import com.yc.bean.Point;
import com.yc.bean.PushOrder;
import com.yc.bean.RefundReason;
import com.yc.bean.Type;
import com.yc.bean.User;
import com.yc.service.UserService;
import com.yc.util.ResultCode;
import com.yc.util.StringUtil;
import com.yc.util.TimeUtil;
import com.yc.util.UserCommonTools;
import com.yc.util.ValidateUtil;


/**
 *
 *用户控制层 
 * @author wang lei
 *
 */
@Controller
public class UserController {
  
  private Logger logger = Logger.getLogger(UserController.class);

  @Autowired
  private UserService userService;
  
/**  
 * 配置首页
 */
    /**
     * 进入点菜页面(首页)
     * @author wang lei
     * @param request
     * @throws Exception
     * */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) throws Exception{
      List<Dish> dishList = new ArrayList<Dish>();
      dishList = userService.getDishList();
      request.setAttribute("dishList", dishList);
      return "user/orderDish";    }  
    
  /**
   * 清空session的值，再跳转到登录
   * */
  @RequestMapping("/user/toUserLogin")
  public String toUserLogin(HttpServletRequest request) throws Exception{
    request.getSession().invalidate();
    return "redirect:/user/toLogin.do";
  }
  /**
   * 跳转到用户登录页面
   * @author wang lei
   * @throws Exception
   * */
  @RequestMapping("/user/toLogin")
  public String toLogin() {
    return "user/userLogin";
  }
  /**
   * 用户登录
   * @author wang lei
   * @param request
   * @return loginMsg
   * @throws Exception
   */
  @RequestMapping("/user/userLogin")
  public String userLogin(HttpServletRequest request,HttpServletResponse response)
      throws Exception {
    String returnValue = "";
    User u = new User();
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
    if (!ValidateUtil.strIsEmpty(phone) && !ValidateUtil.strIsEmpty(password)) {
      u.setUserPhone(phone);
      u.setUserPassword(password);
      u = userService.userLogin(u);
    } else {
        throw new CustomException("手机或密码为空！");
    }
    
    //判断用户名和密码是否输入正确
    if(u == null){
      String error = "用户名或密码错误";
      request.setAttribute("error", error);
      returnValue = "user/userLogin";
    }//if(u==null)
    //成功登录
    else{
      Cookie userId = new Cookie("userId", String.valueOf(u.getUserId()));
      userId.setMaxAge(60*60*24);//cookie生存周期1天
      response.addCookie(userId);
      List<DishList> orderlist =  (ArrayList<DishList>)request.getSession().
          getAttribute("orderList");
      if(orderlist != null){
        returnValue = "redirect:/user/querySingleOrManyToOrder.do";
      }
      else{
        returnValue = "user/userMainPage"; 
      }
    }//else
    return returnValue;
  }
  
  /**
   * 进入用户主页
   * @author wang lei
   * */
  @RequestMapping("/user/toUserMainPage")
  public String toUserMainPage(HttpServletRequest request){
    request.getSession().invalidate();
    return "user/userMainPage";
  }
  /**
   * 调转到用户注册页面
   * @author wang lei
   * */
  @RequestMapping("/user/toRegist")
  public String toRegist(){
    return "user/userRegist";
  }
  
  /**
   * 获取短信验证码
   * @author wang lei
   * */
  @RequestMapping("user/getMessageCode")
  @ResponseBody
  //TODO 调取短信接口
  public Map<String, Object> getMessageCode(HttpServletRequest request) throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String phoneNum = request.getParameter("phoneNum");
    if(!ValidateUtil.strIsEmpty(phoneNum)){
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
      resultMap.put("messageCode", "123");
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 验证该手机号是否已被注册
   * @author wang lei
   * @param request
   * @return resultMap
   * @throws Exception
   * */
  @RequestMapping("/user/isExsit")
  @ResponseBody
  public Map<String,Object> isExsit(HttpServletRequest request) throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String phoneNum = request.getParameter("phoneNum");
    if(!ValidateUtil.strIsEmpty(phoneNum)){
      int isExsit = (int)userService.isPhoneNumExsit(phoneNum);//返回结果为0、1，0未注册，1已注册
      resultMap.put("resultCode",ResultCode.AJAX_CODE_SUCCESS);
      resultMap.put("isExsit", isExsit);
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }

  /**
   * 用户注册
   * @author wang lei
   * @param request
   * @throws Exception
   */
  @RequestMapping("/user/userRegist")
  public String userRegist(HttpServletRequest request)
      throws Exception {
    String returnValue = "";
    String name = (String)request.getParameter("name");
    String phoneNum = (String)request.getParameter("phone");
    String passWord = (String)request.getParameter("passWord");
    String repeatPassWord = (String)request.getParameter("repeatPassWord");
    String sex = (String)request.getParameter("sex");
    if(!ValidateUtil.strIsEmpty(name) && !ValidateUtil.strIsEmpty(phoneNum) && 
        !ValidateUtil.strIsEmpty(passWord) && !ValidateUtil.strIsEmpty(repeatPassWord) 
        && !ValidateUtil.strIsEmpty(sex)){
      int isExsit = (int)userService.isPhoneNumExsit(phoneNum);
      if(isExsit==ResultCode.PHONENUM_ISEXIST_YES){
        returnValue = "user/userRegist";
      }//改手机号已被注册
      else if(!passWord.equals(repeatPassWord)){
        returnValue = "user/userRegist";
      }//两次密码不相等
      else if(name.equals(null)){
        returnValue = "user/userRegist";
      }//名字为空
      else{
        User u = new User();
        u.setUserPhone(phoneNum);
        u.setUserName(name);
        if(sex.equals("0")){
          u.setUserSex("男");
        }else{u.setUserSex("女");}
        u.setUserPassword(passWord);
        u.setRegistTime(TimeUtil.getCurrentTimeAndDate());
        userService.userRegist(u);
        returnValue = "user/userLogin";
      }
    }//判断值是否全部传过来了
    else{
      throw new CustomException("有数据没传输过来");
    }
    return returnValue;
  }
  
  /**
   * 用户的个人信息管理
   * @author wang lei
   * @param request
   * */
  @RequestMapping("/user/toUserInfoManage")
  public String toUserInfoManage(HttpServletRequest request) throws Exception{
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    User u = userService.getUserByUserId(userId);
    request.setAttribute("user", u);
    String isVip="";
    if(u.getVip()==0){
      isVip="否";
    }//if(u.getVip()==0)
    else if(u.getVip()==1){
      isVip="是";
    }//else if(u.getVip()==1)
    request.setAttribute("isVip", isVip);
    return "user/userInfo";
  }
  
  /**
   * 修改用户的姓名
   * @author wang lei
   * @param request
   * @return resultMap
   * @throws Exception
   * */
  @RequestMapping("/user/updateUserName")
  @ResponseBody
  public Map<String, Object> updateUserName(HttpServletRequest request,HttpServletResponse response) 
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String name = (String)request.getParameter("name");
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    if(!ValidateUtil.strIsEmpty(name)){
      userService.updateUserName(userId, name);
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
      resultMap.put("name", name);
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 修改用户的邮箱
   * @author wang lei
   * @param request
   * @return resultMap
   * @throws Exception
   * */
  @RequestMapping("/user/updateUserMail")
  @ResponseBody
  public Map<String, Object> updateUserMail(HttpServletRequest request) 
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String mail = (String)request.getParameter("mail");
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    if(!ValidateUtil.strIsEmpty(mail)){
      userService.updateUserMail(userId, mail);
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
      resultMap.put("mail", mail);
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 跳转到修改用户密码界面
   * @author wang lei
   * */
  @RequestMapping("/user/toChangeUserPassword")
  public String toChangeUserPassword(){
    return "user/userChangePassword";
  }
  
  /**
   * 修改用户密码
   * @author wang lei
   * @param request
   * @return different,error
   * @throws Exception
   * */
  @RequestMapping("/user/changeUserPassword")
  public String changeUserPassword(HttpServletRequest request) throws Exception{
    String returnValue = "redirect:/user/toUserInfoManage.do";
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    String oldPassword = request.getParameter("oldPassword");
    String password = request.getParameter("password");
    String repeatPassword = request.getParameter("repeatPassword");
    if(!ValidateUtil.strIsEmpty(oldPassword) && !ValidateUtil.strIsEmpty(password) 
     && !ValidateUtil.strIsEmpty(repeatPassword)){
      //判断原密码是否正确
      int isCorrect = userService.isCorrectUserPasswordByUserId(userId, 
          oldPassword);
      //原密码正确
      if(isCorrect==1){
        //两次新密码相同
        if(password.equals(repeatPassword)&&password.length()>=6&&password.length()<=20){
          userService.changeUserPasswordByUserId(userId, password);
        }//if(newPassword1.equals(newPassword2))
        //两次新密码不相同
        else{
          String error = "新密码不符合规范";
          request.setAttribute("error", error);
          returnValue = "user/userChangePassword";
        }//else,if(newPassword1.equals(newPassword2))的else
      }//if(isCorrect==1)
      //原密码不正确
      else{
        String error = "原密码输入不正确";
        request.setAttribute("error", error);
        returnValue = "user/userChangePassword";
      }//else,if(isCorrect==1)的else
    }//判断值是否全部传输到后台
    else{
      throw new CustomException("传值数值丢失");
    }
    return returnValue;
  }

  /**
   * 进入地图选址（点餐的第一步，选择送餐地址）
   * @author wang lei
   * */
  @RequestMapping("user/toChooseSendAddress")
  public String toChooseSendAddress(HttpServletRequest request){
    request.getSession().invalidate();
    return "user/chooseSendAddress";
  }
  
  /**
   * 将地图的百度坐标和文字详细位置存放在session中
   * @author wang lei
   * @param request
   * @return resultMap
   * @throws Exception
   * */
  @RequestMapping("user/putAddressInformationIntoSession")
  @ResponseBody
  public Map<String, Object> putAddressInformationIntoSession(HttpServletRequest request)
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String pointLeft = (String)request.getParameter("pointLeft");
    String pointRight = (String)request.getParameter("pointRight");
    String address = (String)request.getParameter("address");
    String distance = (String)request.getParameter("distance");
    if(!ValidateUtil.strIsEmpty(pointLeft) && !ValidateUtil.strIsEmpty(pointRight) 
      && !ValidateUtil.strIsEmpty(address) && !ValidateUtil.strIsEmpty(distance)){
      request.getSession().setAttribute("pointLeft", pointLeft);
      request.getSession().setAttribute("pointRight", pointRight);
      request.getSession().setAttribute("address", address);
      request.getSession().setAttribute("distance", distance);
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  /**
   * 进入点菜页面
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/toOrderDish")
  public String toOrderDish(HttpServletRequest request) throws Exception{
    String returnValue = "user/orderDish";
    String address = (String) request.getSession().getAttribute("address");
    if(ValidateUtil.strIsEmpty(address)){
      returnValue = "redirect:/user/toChooseSendAddress.do";
    }
    else{
      int mode = userService.getOperateMode();
      List<Type> dishTypeList = userService.getDishTypeList();
      List<Type> dishGroupTypeList = userService.getDishGroupTypeList();
      request.setAttribute("dishTypeList", dishTypeList);
      request.setAttribute("dishGroupTypeList", dishGroupTypeList);
      if(mode == ResultCode.OPERATE_MODE_SINGLE){
        List<Dish> dishList = new ArrayList<Dish>();
        dishList = userService.getDishListForSingleBusinessMode();
        request.setAttribute("dishList", dishList);
        returnValue = "user/orderDishForSingleBusiness";
      }
      else if(mode == ResultCode.OPERATE_MODE_MANY){
        List<Dish> dishList = new ArrayList<Dish>();
        dishList = userService.getDishList();
        request.setAttribute("dishList", dishList);
      }
    }
    return returnValue;
  }
  
  /**
   * 获取全部菜单列表
   * */
  @RequestMapping("user/getAllDishes")
  @ResponseBody
  public Map<String, Object> getAllDishes(HttpServletRequest request) 
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    int mode = userService.getOperateMode();
    if(mode == ResultCode.OPERATE_MODE_SINGLE){
      List<Dish> dishList = new ArrayList<Dish>();
      dishList = userService.getDishListForSingleBusinessMode();
      resultMap.put("dishList", dishList);
    }
    else if(mode == ResultCode.OPERATE_MODE_MANY){
      List<Dish> dishList = new ArrayList<Dish>();
      dishList = userService.getDishList();
      resultMap.put("dishList", dishList);
    }
    return resultMap;
  }
  
  /***
   * 获取全部套餐列表
   * */
  @RequestMapping("user/getAllDishGroups")
  @ResponseBody
  public Map<String, Object> getAllDishGroups(HttpServletRequest request) 
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    int mode = userService.getOperateMode();
    List<DishGroup> dishGroups = userService.getDishGroupList();
    dishGroups = userService.getDishListForDishGroupList(dishGroups);
    if(mode == ResultCode.OPERATE_MODE_SINGLE){
      dishGroups = userService.findDishGroupsCount(dishGroups);
    }
    resultMap.put("dishGroupList", dishGroups);
    return resultMap;
  }
  
  /***
   * 按分类查找菜品或套餐列表
   * */
  @RequestMapping("user/getDishOrDishGroupListByTypeName")
  @ResponseBody
  public Map<String, Object> getDishOrDishGroupListByTypeName(
      HttpServletRequest request) throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String typeName = request.getParameter("typeName");
    if(!ValidateUtil.strIsEmpty(typeName)){
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
      int mode = userService.getOperateMode();
      Type type = userService.getTypeByTypeName(typeName);
      System.out.println(typeName+":"+type.getTypeId());
      if(mode == ResultCode.OPERATE_MODE_SINGLE){
        if(type.getTarget()==ResultCode.TARGET_DISH){
          resultMap.put("target", ResultCode.TARGET_DISH);
          List<Dish> dishList = new ArrayList<Dish>();
          dishList = userService.getDishListByTypeIdForSingleBusinessMode(type.getTypeId());
          resultMap.put("dishList", dishList);
        }
        else if(type.getTarget()==ResultCode.TARGET_DISHGROUP){
          System.out.println("aaaaaa");
          resultMap.put("target", ResultCode.TARGET_DISHGROUP);
          List<DishGroup> dishGroups = userService.getDishGroupListByTypeId(type.getTypeId());
          System.out.println("bbbbbb");
          dishGroups = userService.getDishListForDishGroupList(dishGroups);
          System.out.println("cccccc");
          dishGroups = userService.findDishGroupsCount(dishGroups);
          System.out.println("dddddddddd");
          resultMap.put("dishGroupList", dishGroups);
        }
        
      }
      else if(mode == ResultCode.OPERATE_MODE_MANY){
        List<Dish> dishList = new ArrayList<Dish>();
        resultMap.put("dishList", dishList);
      }
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 更新购物车
   * @author wang lei
   * @param request
   * @return resultMap
   * @throws Exception
   * */
  @RequestMapping("user/updateShoppingCar")
  @ResponseBody
  public Map<String, Object> updateShoppingCar(HttpServletRequest request) 
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    if(!ValidateUtil.strIsEmpty(request.getParameter("targetId")) && 
        !ValidateUtil.strIsEmpty(request.getParameter("price")) &&
        !ValidateUtil.strIsEmpty(request.getParameter("number")) && 
        !ValidateUtil.strIsEmpty(request.getParameter("targetType"))){
      int targetType = Integer.parseInt(request.getParameter("targetType"));
      int canNotMeet = 0;//是否满足
      //处理菜品
      if(targetType==ResultCode.TARGET_DISH){
        int dishId = Integer.parseInt(request.getParameter("targetId"));
        double price = Double.valueOf(request.getParameter("price"));
        int number = Integer.parseInt(request.getParameter("number"));
        //获取session中的菜单列表
        List<DishList> orderList = (ArrayList<DishList>)request.getSession().
            getAttribute("orderList");
        //session还没有菜单列表，实例化一个菜单列表
        if(orderList == null){
          orderList = new ArrayList<DishList>();
        }//if(orderList == null)
        else{
          DishList dishList = new DishList();
          //循环遍历菜单列表，直到把有新变动的菜品从菜单列表中删除
          for(int i = 0; i < orderList.size(); i++){
            dishList = orderList.get(i);
            if(dishList.getDishId() == dishId){
              orderList.remove(i);
              break;
            }//if(dishList.getDishId() == dishId)
          }//for(int i = 0; i < orderList.size(); i++)
        }//else,if(orderList == null)
        
        //把新变动且菜品数量不为0的菜品加入菜单列表
        if(number != 0){
          DishList dishList = new DishList();
          dishList.setDishId(dishId);
          dishList.setPrice(price);
          dishList.setNumber(number);
          orderList.add(dishList);
          request.getSession().setAttribute("orderList", orderList);
        }//if(number != 0)
        resultMap.put("canNotMeet", canNotMeet);
        resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
      }
      
      
      //处理套餐
      else if(targetType == ResultCode.TARGET_DISHGROUP){
        int groupId = Integer.parseInt(request.getParameter("targetId"));
        double price = Double.valueOf(request.getParameter("price"));
        int number = Integer.parseInt(request.getParameter("number"));
        DishGroup dishGroup = userService.getDishGroupByGroupId(groupId);
        List<Dish> dishs = StringUtil.jsonToList(dishGroup.getDishes());
        //获取session中的菜单列表
        List<DishList> orderList = (ArrayList<DishList>)request.getSession().
            getAttribute("orderList");
        //session还没有菜单列表，实例化一个菜单列表
        if(orderList == null){
          orderList = new ArrayList<DishList>();
        }//if(orderList == null)
        
        //单商家模式判断是否满足库存
        int mode = userService.getOperateMode();
        if(mode == ResultCode.OPERATE_MODE_SINGLE){
          for(int i=0;i<orderList.size();i++){
            DishList dishList = orderList.get(i);
            for(int j=0;j<dishs.size();j++){
              if(dishList.getDishId()==dishs.get(j).getDishId()){
                int count = userService.getDishCountByDishIdAndMerchantId(
                    dishList.getDishId(),1);
                if(count < (dishList.getNumber() + dishs.get(j).getNum()*number)){
                  canNotMeet = 1;
                }
              }
            }
          }
        }
        if(canNotMeet == 0){
          List<DishList> dishLists = new ArrayList<DishList>();
          for(int i=0;i<dishs.size();i++){
            DishList dishList = new DishList();
            dishList.setDishId(dishs.get(i).getDishId());
            int oldCount=0;
            for(int j=0;j<orderList.size();j++){
              if(dishList.getDishId() == orderList.get(j).getDishId()){
                oldCount = orderList.get(j).getNumber();
                orderList.remove(j);
              }
            }
            dishList.setNumber(oldCount+dishs.get(i).getNum()*number);
            Dish dish = new Dish();
            dish = userService.getDishInfoById(dishs.get(i).getDishId());
            dishList.setPrice(dish.getRetailPrice());
            System.out.println("进来了");
            System.out.println("菜品ID"+dishList.getDishId());
            orderList.add(dishList);
            request.getSession().setAttribute("orderList", orderList);
          }
          resultMap.put("canNotMeet", canNotMeet);
        }
        else if(canNotMeet == 1){
          resultMap.put("groupId", groupId);
          resultMap.put("canNotMeet", canNotMeet);
        }
        resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
      }
     
    }//判断值是否传输过来
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 判断运营模式是单商家还是多商家
   * @author wang lei
   * */
  @RequestMapping("user/querySingleOrManyToOrder")
  public String querySingleOrManyToOrder(HttpServletRequest request) 
      throws Exception{
    String returnValue = "";
    int mode = userService.getOperateMode();
    if(mode == ResultCode.OPERATE_MODE_SINGLE){
      returnValue = "redirect:/user/orderForSingle.do";
    }
    else if(mode == ResultCode.OPERATE_MODE_MANY){
      returnValue = "redirect:/user/toPushOrderListToMerchant.do";
    }
    return returnValue;
  }
  
  /**
   * 单商家模式下定单
   * @author wang lei
   * */
  @RequestMapping("user/orderForSingle")
  public String orderForSingle(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String returnValue = "redirect:/user/recordChoseMerchant.do";
    double pointLeft = Double.valueOf((String) request.getSession().
        getAttribute("pointLeft"));
    double pointRight = Double.valueOf((String) request.getSession().
        getAttribute("pointRight"));
    //取出订单菜品列表
    List<DishList> orderlist =  (ArrayList<DishList>)request.getSession().
        getAttribute("orderList");
    Double distance = Double.valueOf((String) request.getSession().getAttribute("distance"));
    String address = (String) request.getSession().getAttribute("address");
    double leftDistance = UserCommonTools.getLeftDistanceByPointRight(
        pointRight);//一千米，横坐标的差值
    
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    
    Order order = new Order();
    double totalMoney = UserCommonTools.getTotalPrice(orderlist);
    order = UserCommonTools.makeOrderToPush(userId, address,
        pointLeft, pointRight, totalMoney,distance);
    Merchant merchant = userService.getMerchantByMerchantId(1);
    List<Merchant> merchantList = new ArrayList<Merchant>();
    merchantList.add(merchant);
    userService.saveOrder(order, orderlist, merchantList, pointLeft, 
        pointRight,leftDistance);
    int orderId = order.getoId();//订单Id
    System.out.println("得到orderId"+orderId);
    
    userService.updateMerchantIdFromOrdersForSingle(orderId);
    Cookie cookieOrderId = new Cookie("userId", String.valueOf(orderId));
    cookieOrderId.setMaxAge(60*10);//cookie生存周期1天
    response.addCookie(cookieOrderId);
    int result = userService.payForOrder(orderId);
    System.out.println("result"+ result+"orderId"+orderId);
    request.getSession().setAttribute("orderId", orderId);
    return returnValue;
  }
  /**
   * 向符合条件的商家推送订单，然后进入等待商家确认界面
   * 商家范围搜索，目前搜出来的是一个正方形的区域：以送餐点为中心分别向上下左右推6千米的坐标距离。
   * 简介：该层逻辑上有三层。第一层：判断该地区附近有无商家，1、有则获取该地区的商家列表，2、无则跳出；第二层：1、根
   * 据订单筛选出符合条件的商家，2、如果有符合条件的商家就储存订单信息，3、如果没有就进行分单（进入第三层)；第三层:
   * 1、进行分单，依顺序把订单一的菜放如订单二，直到有商家满足订单一，分单完成。筛选出满足订单一的商家，并选出其中距
   * 离最近的商家（此部分放在分单过程中完成）2、筛选出满足订单二的商家，并选出其中距离最近的商家。3、判断满足订单一
   * 的商家和满足订单二的商家是否存在，存在则记录两个订单信息。4、不存在则返回推送失败页面
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/toPushOrderListToMerchant")
  public String toPushOrderListToMerchant(HttpServletRequest request) 
      throws Exception{
    String returnValue = "user/waiting";
    //取出送餐坐标点
    double pointLeft = Double.valueOf((String) request.getSession().
        getAttribute("pointLeft"));
    double pointRight = Double.valueOf((String) request.getSession().
        getAttribute("pointRight"));
    //取出订单菜品列表
    List<DishList> orderlist =  (ArrayList<DishList>)request.getSession().
        getAttribute("orderList");
    
    //取出用户信息
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    String address = (String) request.getSession().getAttribute("address");
    Double distance = Double.valueOf((String) request.getSession().getAttribute("distance"));
    
    double rightDistance = 0.008994;//一千米,纵坐标的差值
    double leftDistance = UserCommonTools.getLeftDistanceByPointRight(
        pointRight);//一千米，横坐标的差值
    
    Point point = UserCommonTools.getPointRangeFiveKm(pointLeft, pointRight, 
        leftDistance, rightDistance, 6);//6千米的坐标范围
    //获取商家Id列表
    List<Merchant> merchantList = userService.queryMerchantByPoint(point);
    List<Merchant> merchantSeparate = new ArrayList<Merchant>();
    for(int i=0; i<merchantList.size();i++){
      Merchant merchant = merchantList.get(i);
      merchantSeparate.add(merchant);
    }

    /**
     * 范围内没有商家
     * */
    if(merchantList.size() == 0){
      returnValue = "user/resultForPushOrders";//没有满足条件的商家
    }
    else{
      int ordersState=0;//订单状态，不分单
      /**
       *剔除不满足条件的商家
       *逻辑上属于第二层，剔除不满足条件的商家
       * */
      for(int i=0;i<merchantList.size();i++){
        Merchant merchant = merchantList.get(i);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("merchantId", merchant.getMerchantId());
        queryMap.put("orderlist", orderlist);
        //查出该商家满足条件的菜品数量
        int meetCount = userService.queryDishsCountWhichMerchantMeet(queryMap);
        if(meetCount!=orderlist.size()){
          merchantList.remove(i);
          i=i-1;
        }
      }//for(int i=0;i<merchantList.size();i++)剔除不满足订单的商家
      /**
       * 有满足的商家，把商家记录进数据库
       * 逻辑上属于第二层，存在满足条件的商家
       * */
      if(merchantList.size()!=0){
        //记录是否分单，0表示不分单，1表示分单
        request.setAttribute("ordersState", ordersState);
        Order order = new Order();
        double totalMoney = UserCommonTools.getTotalPrice(orderlist);
        order = UserCommonTools.makeOrderToPush(userId, address,
            pointLeft, pointRight, totalMoney,distance);
        userService.saveOrder(order, orderlist, merchantList, pointLeft, 
            pointRight,leftDistance);
        int orderId = order.getoId();//订单Id
        request.getSession().setAttribute("orderId", orderId);
      }
      else{
        if(orderlist.size() > 1 && merchantSeparate.size() > 1){
          ordersState=1;//订单状态为分单状态
        }
        else{
          ordersState=3;//下单失败
          returnValue = "user/resultForPushOrders";//下单失败
        }
      }
      
      if(ordersState==1){
        List<DishList> orderlistPartTwo = new ArrayList<DishList>();
        List<Merchant> merchantListPartOne = new ArrayList<Merchant>();
        List<Merchant> merchantListPartTwo = new ArrayList<Merchant>();
        Merchant merchantOne = null;
        Merchant merchantTwo = null;
        /**
         * 把订单分成两份,挑选出订单一的商家
         * 得到订单一的商家merchantOne和订单一的菜单列表orderlist
         * 逻辑上属于第三层，一次把订单一的菜放入订单二，直到订单一有商家满足条件，并选出最近的一个商家进行推送
         * */
        for(int i=orderlist.size()-1;i>0;i--){
          for(int j=0; j<merchantSeparate.size();j++){
            Merchant merchant = merchantSeparate.get(j);
            merchantListPartOne.add(merchant);
          }
          DishList dishList = orderlist.get(i);
          orderlist.remove(i);
          orderlistPartTwo.add(dishList);
          
          //查看是否有商家满足订单一，并得到满足条件的商家列表
          for(int j=0;j<merchantListPartOne.size();j++){
            Merchant merchant = merchantListPartOne.get(j);
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("merchantId", merchant.getMerchantId());
            queryMap.put("orderlist", orderlist);
            //查出该商家满足条件的菜品数量
            int meetCount = userService.queryDishsCountWhichMerchantMeet(queryMap);
            if(meetCount!=orderlist.size()){
              merchantListPartOne.remove(j);
              j=j-1;
            }//if(meetCount!=orderlist.size())
          }//for(int j=0;j<merchantList.size();j++)
          //满足订单一的商家列表不为空
          if(merchantListPartOne.size() != 0){
            //筛选出订单一最近的商家
            merchantOne = UserCommonTools.getMinDistanceMerchant(
                merchantListPartOne, pointLeft, pointRight, leftDistance);
            break;
          }//if(merchantListPartOne!=null)
        }//for(int i=orderlist.size()-1;i>0;i--)
        
        /**
         * 查出满足订单二的商家列表
         * 得到订单二的推送商家merchantTwo和订单二的推送菜单列表orderlistPartTwo
         * 逻辑上属于第三层，把不满足订单二的商家剔除，并选出最近的一个商家进行推送
         * */
        
        for(int j=0; j<merchantSeparate.size();j++){
          Merchant merchant = merchantSeparate.get(j);
          merchantListPartTwo.add(merchant);
        }
        for(int j=0;j<merchantListPartTwo.size();j++){
          Merchant merchant = merchantListPartTwo.get(j);
          Map<String, Object> queryMap = new HashMap<String, Object>();
          queryMap.put("merchantId", merchant.getMerchantId());
          queryMap.put("orderlist", orderlistPartTwo);
          //查出该商家满足条件的菜品数量
          int meetCount = userService.queryDishsCountWhichMerchantMeet(queryMap);
          if(meetCount!=orderlistPartTwo.size()){
            merchantListPartTwo.remove(j);
            j=j-1;
          }//if(meetCount!=orderlist.size())
        }//for(int j=0;j<merchantList.size();j++)
        //筛选出订单二最近的商家
        if(merchantListPartTwo.size() != 0){
          merchantTwo = UserCommonTools.getMinDistanceMerchant(
              merchantListPartTwo, pointLeft, pointRight, leftDistance);
        }
        /**
         * 订单成功，记录进数据库
         * 逻辑上属于第三层，如果得到merchantOne和merchantTwo,就表示分单成功。
         * */
        if(merchantOne != null && merchantTwo != null){
          request.setAttribute("ordersState", ordersState);
          Order orderOne = new Order();
          double orderOneTotalMoney = UserCommonTools.getTotalPrice(orderlist);
          orderOne =  UserCommonTools.makeOrderToPush(userId, address, 
              pointLeft, pointRight, orderOneTotalMoney,distance);
          List<Merchant> merchantsOne = new ArrayList<Merchant>();
          merchantsOne.add(merchantOne);
          int orderOneId = userService.saveOrder(orderOne, orderlist, 
              merchantsOne, pointLeft, pointRight, leftDistance);
          
          Order orderTwo = new Order();
          double orderTwoTotalMoney = UserCommonTools.getTotalPrice(
              orderlistPartTwo);
          orderTwo = UserCommonTools.makeOrderToPush(userId, address, 
              pointLeft, pointRight, orderTwoTotalMoney,distance);
          List<Merchant> merchantsTwo = new ArrayList<Merchant>();
          merchantsTwo.add(merchantTwo);
          int orderTwoId = userService.saveOrder(orderTwo, orderlistPartTwo, 
              merchantsTwo, pointLeft, pointRight, leftDistance);
          
          request.getSession().setAttribute("orderOneId", orderOneId);
          request.getSession().setAttribute("orderTwoId", orderTwoId);
        }//if(merchantOne != null && merchantTwo != null)
        
        else{
          for(int i=0; i<orderlistPartTwo.size();i++){
            DishList dishList = orderlistPartTwo.get(i);
            orderlist.add(dishList);
          }//把订单重新合并到一起
          ordersState=3;
          returnValue = "user/resultForPushOrders";//下单失败
        }//分单失败
      }//if(ordersState==1)
      
      //下单失败
      if(ordersState==3){
        List<Dish> dishlist = new ArrayList<Dish>();
        for(int i=0;i<orderlist.size();i++){
          DishList dishList = orderlist.get(i);
          Map<String, Object> queryMap = new HashMap<String, Object>();
          queryMap.put("dishId", dishList.getDishId());
          queryMap.put("dishNum", dishList.getNumber());
          queryMap.put("merchants", merchantSeparate);
          int count = userService.getMerchantsCountWhichMeetDish(queryMap);
          if(count==0){
            Dish dish = new Dish();
            dish.setDishName(userService.getDishNameByDishId(
                dishList.getDishId()));
            dishlist.add(dish);
          }
        }
        String isHaveAllDishes="yes";
        if(dishlist.size() != 0){
          request.setAttribute("dishlist", dishlist);
          isHaveAllDishes = "no";
        }
        request.setAttribute("isHaveAllDishes", isHaveAllDishes);
      }
    }//有商家
    return returnValue;
  }
  
  /**
   * 给商家重新排序,然后进入用户选择商家界面
   * 正常情况下，没有分单
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/toChooseMerchant")
  public String toChooseMerchant(HttpServletRequest request) throws Exception{
    String returnValue = "user/chooseMerchant";
    int orderId = (Integer) request.getSession().getAttribute("orderId");
    List<Merchant> merchants = new ArrayList<Merchant>();
    merchants = userService.querySuredMerchantListByOrderId(orderId);
    if(merchants.size() != 0){
      if(merchants.size() > 1){
        merchants = UserCommonTools.sortMerchants(merchants);
      }
      request.getSession().setAttribute("merchants", merchants);
    }
    else{
      request.setAttribute("isMerchantAccpet", "no");
      returnValue = "user/resultForPushOrders";
    }
    
    return returnValue;
  }
  
  /**
   * 提取推送商家信息
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/pushMerchantInfo")
  @ResponseBody
  public Map<String, Object> pushMerchantInfo(HttpServletRequest request) 
      throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    double pointLeft =  Double.parseDouble((String) request.getSession().
        getAttribute("pointLeft"));
    double pointRight =  Double.parseDouble((String) request.getSession().
        getAttribute("pointRight"));
    List<Merchant> merchants = (List<Merchant>) request.getSession().
        getAttribute("merchants");
    
    if(!ValidateUtil.strIsEmpty(request.getParameter("state"))){
      int state =Integer.parseInt(request.getParameter("state"));
      resultMap.put("pointLeft", pointLeft);
      resultMap.put("pointRight", pointRight);
      if(state<merchants.size()){
        resultMap.put("state", state);
        resultMap.put("merchant", merchants.get(state));
      }
      else {
        resultMap.put("state", 0);
        resultMap.put("merchant", merchants.get(0));
      }
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
    }//值是否取出
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 进入选择商家界面
   * 分单的情况下
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/toChooseMerchants")
  public String toChooseMerchants(HttpServletRequest request) throws Exception{
    String returnValue="user/chooseMerchants";
    int orderOneId = (Integer) request.getSession().getAttribute("orderOneId");
    int orderTwoId = (Integer) request.getSession().getAttribute("orderTwoId");
    List<Merchant> merchantOnelist = userService.querySuredMerchantListByOrderId(orderOneId);
    List<Merchant> merchantTwolist = userService.querySuredMerchantListByOrderId(orderTwoId);
    if(merchantOnelist.size() != 0 && merchantTwolist.size() != 0){
      Merchant merchantOne = merchantOnelist.get(0);
      Merchant merchantTwo = merchantTwolist.get(0);
      List<DishList> dishListsOne = userService.getDishlistByOrderId(orderOneId);
      List<DishList> dishlistsTwo = userService.getDishlistByOrderId(orderTwoId);
      merchantOne.setDishlist(dishListsOne);
      merchantTwo.setDishlist(dishlistsTwo);
      request.setAttribute("merchantOne", merchantOne);
      request.setAttribute("merchantTwo", merchantTwo);
    }
    else{
      request.setAttribute("isMerchantAccpet", "no");
      returnValue = "user/resultForPushOrders";
    }
    return returnValue;
  }
  
  /**
   * 记录选中的商家，并跳转到确认订单
   * 不分单的情况下
   * @author wang lei
   * @param
   * @throws Exception
   * */
  @RequestMapping("user/recordChoseMerchant")
  public String recordChoseMerchant(HttpServletRequest request) 
      throws Exception{
    int mode = userService.getOperateMode();
    int merchantId = 1;
    if(mode == ResultCode.OPERATE_MODE_MANY){
      merchantId = Integer.parseInt(request.getParameter("merchantId"));
    }
    int orderId = (Integer) request.getSession().getAttribute("orderId");
    userService.chooseMerchant(orderId, merchantId);
    Order order = new Order();
    order = userService.queryAllOrderInfoByOrderId(orderId);
    request.setAttribute("order", order);
    request.setAttribute("orderState", 0);
    return "user/orderInfo";
  }
  
  /**
   * 记录选中的商家，并跳转到确认订单(先显示订单一的信息)
   * 分单的情况下
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/recordChoseMerchants")
  public String recordChoseMerchants(HttpServletRequest request) 
      throws Exception{
    int merchantOneId = Integer.parseInt(request.getParameter("merchantOneId"));
    int merchantTwoId = Integer.parseInt(request.getParameter("merchantTwoId"));
    
    if(!ValidateUtil.strIsEmpty(request.getParameter("merchantOneId")) && 
        !ValidateUtil.strIsEmpty(request.getParameter("merchantTwoId"))){
      
      int orderOneId = (Integer) request.getSession().getAttribute("orderOneId");
      int orderTwoId = (Integer) request.getSession().getAttribute("orderTwoId");
      
      userService.chooseMerchant( orderOneId,merchantOneId);
      userService.chooseMerchant(orderTwoId,merchantTwoId);
      
      Order order = userService.queryAllOrderInfoByOrderId(orderOneId);
      request.setAttribute("order", order);
      request.setAttribute("orderState", 1);
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return "user/orderInfo";
  }
  
  /**
   * 显示订单二的详情
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("user/showOrderTwo")
  public String showOrderTwo(HttpServletRequest request) throws Exception{
    int orderTwoId = (Integer) request.getSession().getAttribute("orderTwoId");
    Order order = userService.queryAllOrderInfoByOrderId(orderTwoId);
    request.getSession().removeAttribute("payState");//删除订单1的状态值
    request.setAttribute("order", order);
    request.setAttribute("orderState", 2);
    return "user/orderInfo";
  }
  
  /**
   * 支付
   * 不分单的情况
   * @author wang lei
   * */
  @RequestMapping("user/payForOrder")
  public String payForOrder(HttpServletRequest request) throws Exception{
    /*
         支付宝调用代码
    String resultValue = "alipay/index";
<<<<<<< HEAD
=======
    int orderId = (Integer) request.getSession().getAttribute("payState");*/
    //TODO 支付宝接口
    String resultValue = "user/resultForAlipay";
//    int orderId = (Integer) request.getSession().getAttribute("orderId");
    Cookie[] cookies = request.getCookies();
    int orderId = UserCommonTools.getOrderIdFromCookies(cookies);
    int result = userService.payForOrder(orderId);
    if(result==0){
      resultValue = "user/failureForPay";
    }
    return resultValue;
  }
  
  /**
   * 支付，第一单
   * 分单的情况
   * @author wang lei
   * */
  @RequestMapping("user/payForOrderOne")
  public String payForOrderOne(HttpServletRequest request) throws Exception{
    String resultValue = "user/resultForAlipay";
    int orderId = (Integer) request.getSession().getAttribute("orderOneId");
    int result = userService.payForOrder(orderId);
    if(result==0){
      resultValue = "user/failureForPay";
    }
    else{
      request.setAttribute("payState", 1);
    }
    return resultValue;
  }
  /**
   * 支付，第二单
   * 分单的情况
   * @author wang lei
   * */
  @RequestMapping("user/payForOrderTwo")
  public String payForOrderTwo(HttpServletRequest request) throws Exception{
    String resultValue = "user/resultForAlipay";
    int orderId = (Integer) request.getSession().getAttribute("orderTwoId");
    int result = userService.payForOrder(orderId);
    if(result==0){
      resultValue = "user/failureForPay";
    }
    return resultValue;
  }
  
  /**
   * 未完成订单管理
   * @author wang lei
   * @param request
   * */
  @RequestMapping("user/toGetUnfinishedOrders")
  public String toGetUnfinishedOrders(HttpServletRequest request) throws Exception{
    String returnValue = "user/unfinishedOrders";
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    List<Order> orderslist = userService.getUnfinishedOrdersDetailsByUserId( 
        userId);
    request.setAttribute("orderslist", orderslist);
    return returnValue;
  }
  
  /**
   * 已完成订单管理
   * @author wang lei
   * @param request
   * */
  @RequestMapping("user/toGetFinishedOrders")
  public String toGetFinishedOrders(HttpServletRequest request) throws Exception{
    String returnValue = "user/finishedOrders";
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    List<Order> orderslist = userService.getFinishedOrdersDetailsByUserId( 
        userId,0);
    request.setAttribute("orderslist", orderslist);
    return returnValue;
  }
  
  /**
   * 加载已完成订单的下一页
   * @author wang lei
   * */
  @RequestMapping("user/getMoreFinishedOrderToShow")
  @ResponseBody
  public Map<String, Object> getMoreFinishedOrderToShow(HttpServletRequest 
      request) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Cookie[] cookies = request.getCookies();
    int userId = UserCommonTools.getUserIdFromCookies(cookies);
    int pageNum = Integer.parseInt(request.getParameter("pageNum"));
    if(!ValidateUtil.strIsEmpty(request.getParameter("pageNum"))){
      List<Order> orderslist = userService.getFinishedOrdersDetailsByUserId(
          userId, pageNum);
      resultMap.put("orderslist", orderslist);
      if(orderslist.size()!=0){
        resultMap.put("noResult", 0);
      }
      if(orderslist.size()==0){
        resultMap.put("noResult", 1);
      }
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    
    return resultMap;
  }
  
  /***
   * 跳转到确认收货和评价界面
   * @author wang lei
   * */
  @RequestMapping("user/toEvaluateOrder")
  public String toEvaluateOrder(HttpServletRequest request) throws Exception{
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    if(!ValidateUtil.strIsEmpty(request.getParameter("orderId"))){
      List<Comment> goodComments = new ArrayList<Comment>();
      List<Comment> badComments = new ArrayList<Comment>();
      goodComments = userService.getGoodEvaluations();
      badComments = userService.getBadEvaluations();
      request.setAttribute("goodComments", goodComments);
      request.setAttribute("badComments", badComments);
      request.setAttribute("goodCommentsNum", goodComments.size());
      request.setAttribute("badCommentsNum", badComments.size());
      request.setAttribute("orderId", orderId);
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return "user/evaluateOrder";
  }
  
  /**
   * 确认收货评价
   * @author wang lei
   * */
  @RequestMapping("user/confirmOrder")
  public String confirmOrder(HttpServletRequest request) throws Exception{
    String[] commentIds = request.getParameterValues("comment");
    int mark = Integer.parseInt(request.getParameter("mark"));
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    if(!ValidateUtil.strIsEmpty(request.getParameter("mark")) && 
        !ValidateUtil.strIsEmpty(request.getParameter("orderId"))){
//      if(commentIds == null){
//        commentIds = new String[]{"noComments"};
//      }
      userService.userSureReceivedByOrderId(orderId,mark,commentIds);
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return "redirect:/user/toGetUnfinishedOrders.do";
  }
  
  /**
   * 用户隐藏已完成订单
   * @author wang lei
   * */
  @RequestMapping("user/hideFinishedOrder")
  @ResponseBody
  public Map<String, Object> hideFinishedOrder(HttpServletRequest request) throws Exception{
    Map<String, Object> resultMap = new  HashMap<String, Object>();
    int orderId  = Integer.parseInt(request.getParameter("orderId"));
    if(!ValidateUtil.strIsEmpty(request.getParameter("orderId"))){
      userService.hideFinishedOrderByOrderId(orderId);
      resultMap.put("orderId", orderId);
      resultMap.put("resultCode", ResultCode.AJAX_CODE_SUCCESS);
    }
    else{
      resultMap.put("resultCode", ResultCode.AJAX_CODE_FAIL);
    }
    return resultMap;
  }
  
  /**
   * 跳转到申请退款界面
   * @author wang lei
   * */
  @RequestMapping("user/toApplyRefund")
  public String toApplyRefund(HttpServletRequest request) throws Exception{
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    if(!ValidateUtil.strIsEmpty(request.getParameter("orderId"))){
      request.setAttribute("orderId", orderId);
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return "user/refund";
  }
  
  /**
   * 申请退款
   * @author wang lei
   * */
  @RequestMapping("user/applyRefund")
  public String applyRefund(HttpServletRequest request) throws Exception{
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    String refundContent = request.getParameter("refundContent");
    if(!ValidateUtil.strIsEmpty(request.getParameter("orderId")) && 
        !ValidateUtil.strIsEmpty(refundContent)){
      String time = TimeUtil.getCurrentTimeAndDate();
      RefundReason refundReason = new RefundReason();
      refundReason.setOrderId(orderId);
      refundReason.setContent(refundContent);
      refundReason.setTime(time);
      userService.applyRefund(orderId);
      userService.recordRefundInFo(refundReason);
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return "user/successForApplyRefund";
  }
  
  /**
   * 跳转到忘记密码的短信验证界面
   * @author wang lei
   * */
  @RequestMapping("user/toForgetPassword")
  public String toForgetPassword() throws Exception{
    return "user/forgetPassword";
  }
  
  /**
   * 验证密码是否输入正确，正确后跳转到设置新密码界面，不正确回到短信验证界面
   * @author wang lei
   * */
  @RequestMapping("user/toFindPassword")
  public String toFindPassword(HttpServletRequest request) throws Exception{
    String returnValue = "user/findPassword";
    String phoneNum = request.getParameter("phoneNum");
    String messageCode = request.getParameter("messageCode");
    if(!ValidateUtil.strIsEmpty(phoneNum) && !ValidateUtil.strIsEmpty(messageCode)){
      int isExsit = userService.isPhoneNumExsit(phoneNum);
      if(isExsit==ResultCode.PHONENUM_ISEXIST_NO){
        request.setAttribute("noExsit", 1);
        returnValue="redirect:/user/toForgetPassword.do";
      }
      request.setAttribute("phoneNum", phoneNum);
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return returnValue;
  }
  
  /**
   * 保存新密码
   * @author wang lei
   * */
  @RequestMapping("user/saveNewPassword")
  public String saveNewPassword(HttpServletRequest request) throws Exception{
    String returnValue="redirect:/user/toForgetPassword.do";
    String phoneNum = request.getParameter("phoneNum");
    String password = request.getParameter("password");
    String repeatPassword = request.getParameter("repeatPassword");
    if(!ValidateUtil.strIsEmpty(phoneNum) && !ValidateUtil.strIsEmpty(password) 
        && !ValidateUtil.strIsEmpty(repeatPassword)){
      int isExsit = userService.isPhoneNumExsit(phoneNum);
      if(isExsit == ResultCode.PHONENUM_ISEXIST_YES){
        if(password.equals(repeatPassword)){
          userService.changeUserPasswordByPhoneNum(phoneNum, password);
        }
        else{
          returnValue="user/findPassword";
          request.setAttribute("phoneNum", phoneNum);
        }
        returnValue="user/successForFindPassword";
      }
    }
    else{
      throw new CustomException("数据传输丢失");
    }
    return returnValue;
  }
  
  /**
   * 清空购物车
   * @author wang lei
   * 
   * */
  @RequestMapping("user/clearShoppingCar")
  public String clearShoppingCar(HttpServletRequest request) throws Exception{
    request.getSession().removeAttribute("orderList");
    return "redirect:/user/toOrderDish.do";
  }
  
  /**
   * 退出登录
   * @author wanglei;
   * */
  @RequestMapping("user/outLogin")
  public String outLogin(HttpServletRequest request, HttpServletResponse 
      response) throws Exception{
    request.getSession().invalidate();
    Cookie cookie = new Cookie("userId", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/user/toLogin.do";
  }
}