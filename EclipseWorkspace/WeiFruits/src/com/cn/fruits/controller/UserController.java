package com.cn.fruits.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.bean.UserBean;
import com.cn.fruits.service.UserService;
import com.cn.fruits.util.CommonUtil;
import com.cn.fruits.util.Constants;
import com.cn.fruits.util.ValidateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
  private static final Logger logger = Logger.getLogger(UserController.class);
  
  @Inject
  public UserService userService;
  
  /**
   * 微信授权处理
   * */
  //TODO
  public String getOpenId(HttpServletRequest request) throws Exception{
    String code = request.getParameter("code");
    return "redirect:/user/toHome";
  }
  
  /**
   * 跳转到用户登录页面
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/toLogin")
  public String toLogin(HttpServletRequest request) throws Exception{
    return Constants.PAGE_USER_LOGIN;
  }
  
  /**
   * 处理用户登录
   * */
  @RequestMapping("/doLogin")
  public String doLogin(HttpServletRequest request, HttpServletResponse response)throws Exception{
    String returnValue = Constants.PAGE_USER_LOGIN;
    String userName = request.getParameter("userName");
    String passWord = request.getParameter("passWord");
    if(!ValidateUtil.strIsEmpty(userName) && !ValidateUtil.strIsEmpty(passWord)){
      int userId = userService.getUserIdByUserNameAndPassWordService(
          userName, passWord);
      if(userId != 0){
        //把用户的userId存进cookie
        Cookie userCookie = new Cookie("UserId", String.valueOf(userId));
        userCookie.setMaxAge(60*60*24);//cookie生存周期1天
        response.addCookie(userCookie);
        returnValue = "redirect:/user/toHome";
      }
      else{
        request.setAttribute(Constants.RESULT, "用户名或密码错误");
      }
    }
    else{
      request.setAttribute(Constants.RESULT, "数据丢失");
    }
    return returnValue;
  }
  
  /**
   * 跳转到用户注册页面
   */
  @RequestMapping("/toRegist")
  public String toRegist(HttpServletRequest request) throws Exception{
    return Constants.PAGE_USER_REGIST;
  }
  
  /***
   * 用户注册
   * */
  @RequestMapping("/doRegist")
  public String doRegist(HttpServletRequest request,HttpServletResponse response) throws Exception{
    String returnValue = Constants.PAGE_USER_REGIST;
    String userName = request.getParameter("userName");
    String name = request.getParameter("name");
    String passWord = request.getParameter("passWord");
    if(!ValidateUtil.strIsEmpty(userName) && !ValidateUtil.strIsEmpty(name)
        && !ValidateUtil.strIsEmpty(passWord)){
      boolean bool = userService.userNameisExistService(userName);
      if(bool){
        request.setAttribute(Constants.RESULT, "改手机号已被注册");
      }
      else{
        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setName(name);
        userBean.setPassWord(passWord);
        int userId = userService.saveNewUserService(userBean);
        if(userId != 0){
          //把用户的userId存进cookie
          Cookie userCookie = new Cookie("UserId", String.valueOf(userId));
          userCookie.setMaxAge(60*60*24);//cookie生存周期1天
          response.addCookie(userCookie);
          returnValue = "redirect:/user/toHome";
        }
        else{
          request.setAttribute(Constants.RESULT, "注册失败");
        }
      }
    }
    else{
      request.setAttribute(Constants.RESULT, "传参失败");
    }
    return returnValue;
  }
  /**
   * 商店主页
   * @param request
   * @return
   */
  @RequestMapping("/toHome")
  public String toHome(HttpServletRequest request, HttpServletResponse response) throws Exception{
    return Constants.PAGE_USER_HOME;
  }
  
  /**
   *我的主页 
   */
  @RequestMapping("/toMyHome")
  public String toMyHome(HttpServletRequest request) throws Exception{
    int totalCount = 0;
    //公告栏
    NoticeBean noticeBean = userService.getNewestNoticeService();
    request.setAttribute("noticeBean", noticeBean);
    
    List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
    //读取商品
    fruitBeans = userService.getFruitsListService();
    //在页面上把购物车里已有商品的数量添加进商品列表
    List<FruitBean> orderList = (List<FruitBean>) request.getSession().getAttribute("orderList");
    if(orderList!=null){
      for(int i=0; i<orderList.size();i++){
        for(int j=0; j<fruitBeans.size();j++){
          if(orderList.get(i).getFruitId() == fruitBeans.get(j).getFruitId()){
            fruitBeans.remove(j);
            FruitBean fruitBean = new FruitBean();
            fruitBean = orderList.get(i);
            fruitBeans.add(j, fruitBean);
          }
        }
        totalCount = totalCount + orderList.get(i).getCount();
      }
    }
    request.setAttribute("totalCount", totalCount);
    request.setAttribute("fruitBeans", fruitBeans);
    return Constants.PAGE_USER_MYHOME;
  }
  
  
  
  /**
   * 我的订单
   * @param request
   * @return
   */
  @RequestMapping("/toOrder")
  public String toOrder(HttpServletRequest request)throws Exception{
    //获取userId,登陆过后才有
    Cookie[] cookies = request.getCookies();
    int userId = CommonUtil.getUserIdFromCookies(cookies);
    if(userId == 0){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    ordersBeans = userService.getUnFinishOrdersByUserIdService(userId);
    request.setAttribute("ordersBeans", ordersBeans);
    return Constants.PAGE_USER_ORDER;
  }
  
  /**
   * 我的订单——已完成订单
   */
  @RequestMapping("/FinishedOrder")
  public String toFinishedOrder(HttpServletRequest request)throws Exception{
    //获取userId,登陆过后才有
    Cookie[] cookies = request.getCookies();
    int userId = CommonUtil.getUserIdFromCookies(cookies);
    if(userId == 0){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    ordersBeans = userService.getFinishOrdersByUserIdService(userId);
    request.setAttribute("ordersBeans", ordersBeans);
    return Constants.PAGE_USER_FINISHED_ORDER;
  }
  
  /**
   * 我的购物车
   * @param request
   * @return
   */
  @RequestMapping("/toShopping")
  public String toShopping(HttpServletRequest request)throws Exception{
    //获取userId,登陆过后才有
    Cookie[] cookies = request.getCookies();
    int userId = CommonUtil.getUserIdFromCookies(cookies);
    if(userId == 0){
      return Constants.PAGE_USER_LOGIN;
    }
    
    OrdersBean ordersBean = new OrdersBean();
    ordersBean = null;
    request.setAttribute("ordersBean", ordersBean);
    
    List<CampusBean> campusBeans = userService.getAllCampusessService();
    request.setAttribute("campusBeans", campusBeans);
    
    List<DormitoryBean> dormitoryBeans = new ArrayList<DormitoryBean>();
    if(campusBeans != null){
      dormitoryBeans = userService.getDormitoriesDaoByCampusIdService(
          campusBeans.get(0).getCampusId());
    }
    request.setAttribute("dormitoryBeans", dormitoryBeans);
    
    return Constants.PAGE_USER_SHOPPING;
  }

  /**
   * 我的账户
   */
  @RequestMapping("/toAccount")
  public String toAccount(HttpServletRequest request)throws Exception{
    //获取userId,登陆过后才有
    Cookie[] cookies = request.getCookies();
    int userId = CommonUtil.getUserIdFromCookies(cookies);
    if(userId == 0){
      return Constants.PAGE_USER_LOGIN;
    }
    
    UserBean userBean = userService.getUserByUserIdService(userId);
    request.setAttribute("userBean", userBean);
    return Constants.PAGE_USER_ACCOUNT;
  }
  
  /**
   * 修改用户信息
   */
  @RequestMapping("/updateUserInfo")
  public String updateUserInfo(HttpServletRequest request)throws Exception{
    //获取userId,登陆过后才有
    Cookie[] cookies = request.getCookies();
    int userId = CommonUtil.getUserIdFromCookies(cookies);
    if(userId == 0){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String name = request.getParameter("name");
    if(!ValidateUtil.strIsEmpty(name)){
      boolean bool = userService.updateUserNameByUserIdService(userId, name);
      if(!bool){
        request.setAttribute(Constants.RESULT, "修改失败");
      }
    }
    else{
      request.setAttribute(Constants.RESULT, "数据丢失");
    }
    
    UserBean userBean = userService.getUserByUserIdService(userId);
    request.setAttribute("userBean", userBean);
    return Constants.PAGE_USER_ACCOUNT;
  }
  
  /**
   * 更新购物车
   * */
  @RequestMapping("/updateShoppingCar")
  @ResponseBody
  public Map<String, Object> updateShoppingCar(HttpServletRequest request)throws Exception{
    Map<String, Object> retrunMap = new HashMap<String, Object>();
    String fruitId = request.getParameter("fruitId");
    String fruitName = request.getParameter("fruitName");
    String fruitPrice = request.getParameter("fruitPrice");
    String fruitIntr = request.getParameter("fruitIntr");
    String fruitDetail = request.getParameter("fruitDetail");
    String fruitPic = request.getParameter("fruitPic");
    String count = request.getParameter("count");
    String sellCount = request.getParameter("sellCount");
    if(!ValidateUtil.strIsEmpty(fruitId) && !ValidateUtil.strIsEmpty(fruitName) && !ValidateUtil.strIsEmpty(fruitPrice)
        && !ValidateUtil.strIsEmpty(fruitIntr) && !ValidateUtil.strIsEmpty(fruitDetail) && !ValidateUtil.strIsEmpty(fruitPic)
        && !ValidateUtil.strIsEmpty(count) && !ValidateUtil.strIsEmpty(sellCount)){
      List<FruitBean> orderList = (List<FruitBean>) request.getSession().getAttribute("orderList");
      if(orderList == null){
        orderList = new ArrayList<FruitBean>();
      }
      FruitBean fruitBean = new FruitBean();
      fruitBean.setFruitId(Integer.parseInt(fruitId));
      fruitBean.setFruitName(fruitName);
      fruitBean.setFruitPrice(Double.valueOf(fruitPrice));
      fruitBean.setFruitIntr(fruitIntr);
      fruitBean.setFruitDetail(fruitDetail);
      fruitBean.setFruitPic(fruitPic);
      fruitBean.setCount(Integer.parseInt(count));
      fruitBean.setSellCount(Integer.parseInt(sellCount));
      //剔除之前订单里存在的菜品,并且统计购物车商品数量
      int totalCount = 0;
      for(int i=0; i<orderList.size();i++){
        if(orderList.get(i).getFruitId()==Integer.parseInt(fruitId)){
          orderList.remove(i);
          i=i-1;
        }
        else{
          totalCount = totalCount + orderList.get(i).getCount();
        }
      }
      totalCount = totalCount + fruitBean.getCount();
      if(Integer.parseInt(count)!=0){
        orderList.add(fruitBean);
      }
      request.getSession().setAttribute("orderList", orderList);
      retrunMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      retrunMap.put("fruitId", Integer.parseInt(fruitId));
      retrunMap.put("count", Integer.parseInt(count));
      retrunMap.put("totalCount", totalCount);
      for(int j=0; j<orderList.size();j++){
        System.out.println(orderList.get(j).getFruitName()+":"+orderList.get(j).getCount());
      }
    }
    else{
      retrunMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return retrunMap;
  }
  
  /**
   * 通过校区ID查询出该校区的所有宿舍楼
   * */
  @RequestMapping("/getDormitoriesByCampusId")
  @ResponseBody
  public Map<String, Object> getDormitoriesByCampusId(HttpServletRequest request)
      throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String campusId = request.getParameter("campusId");
    if(!ValidateUtil.strIsEmpty(campusId)){
      List<DormitoryBean> dormitoryBeans = new ArrayList<DormitoryBean>();
      dormitoryBeans = userService.getDormitoriesDaoByCampusIdService(
          Integer.parseInt(campusId));
      returnMap.put("dormitoryBeans", dormitoryBeans);
      returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 保存订单并跳转到支付页面
   * */
  @RequestMapping("/saveOrdersAndTurnToPayPage")
  public String saveOrdersAndTurnToPayPage(HttpServletRequest request)
      throws Exception{
    //获取userId,登陆过后才有
    Cookie[] cookies = request.getCookies();
    int userId = CommonUtil.getUserIdFromCookies(cookies);
    if(userId == 0){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String returnValue = Constants.PAGE_RESULT_USER;//失败返回的地址
    String message = request.getParameter("message");
    String payWay = request.getParameter("payWay");
    String chooseCode = request.getParameter("chooseCode");
    String oldCampusName = request.getParameter("oldCampusName");
    String oldDormitoryName = request.getParameter("oldDormitoryName");
    String oldAddress = request.getParameter("oldAddress");
    String oldReceiveName = request.getParameter("oldReceiveName");
    String oldReceivePhone = request.getParameter("oldReceivePhone");
    String newCampusId = request.getParameter("newCampusId");
    String newDormitoryName = request.getParameter("newDormitoryName");
    String newAddress = request.getParameter("newAddress");
    String newReceiveName = request.getParameter("newReceiveName");
    String newReceivePhone = request.getParameter("newReceivePhone");
    String receiveWay = request.getParameter("receiveWay");
    
    //获取菜单列表
    List<FruitBean> orderList = (List<FruitBean>) request.getSession().getAttribute("orderList");
    
    boolean bool = false;//订单保存成功返回true
    if(!ValidateUtil.strIsEmpty(payWay) && !ValidateUtil.strIsEmpty(chooseCode) 
        && !ValidateUtil.strIsEmpty(receiveWay)){
      //选择新的地址
      if(chooseCode.equals("checkNew")){
        
        if(!ValidateUtil.strIsEmpty(newCampusId) && !ValidateUtil.strIsEmpty(newDormitoryName)
            && !ValidateUtil.strIsEmpty(newAddress) && !ValidateUtil.strIsEmpty(newReceiveName)
            && !ValidateUtil.strIsEmpty(newReceivePhone)){
          String newCampusName = userService.getCampusNameByCampusIdService(
              Integer.parseInt(newCampusId));
          OrdersBean ordersBean = new OrdersBean();
          ordersBean.setUserId(userId);
          ordersBean.setReceiveName(newReceiveName);
          ordersBean.setReceivePhone(newReceivePhone);
          ordersBean.setCampusName(newCampusName);
          ordersBean.setDormitoryName(newDormitoryName);
          ordersBean.setAddress(newAddress);
          ordersBean.setPayWay(Integer.parseInt(payWay));
          ordersBean.setPayStatus(0);
          ordersBean.setReceiveWay(Integer.parseInt(receiveWay));
          ordersBean.setOrderStatus(0);
          ordersBean.setMessge(message);
          bool = userService.saveNewOrdersService(ordersBean, orderList);
        }//if(!ValidateUtil.strIsEmpty(newCampusName) && !ValidateUtil.strIsEmpty(newDormitoryName)
      }//if(chooseCode.equals("checkNew")){
      //选择老的地址
      else if(chooseCode.equals("checkOld")){
        if(!ValidateUtil.strIsEmpty(oldCampusName) && !ValidateUtil.strIsEmpty(oldDormitoryName)
            && !ValidateUtil.strIsEmpty(oldAddress) && !ValidateUtil.strIsEmpty(oldReceiveName)
            && !ValidateUtil.strIsEmpty(oldReceivePhone)){
          OrdersBean ordersBean = new OrdersBean();
          ordersBean.setUserId(1);
          ordersBean.setReceiveName(oldReceiveName);
          ordersBean.setReceivePhone(oldReceivePhone);
          ordersBean.setCampusName(oldCampusName);
          ordersBean.setDormitoryName(oldDormitoryName);
          ordersBean.setAddress(oldAddress);
          ordersBean.setPayWay(Integer.parseInt(payWay));
          ordersBean.setPayStatus(0);
          ordersBean.setReceiveWay(Integer.parseInt(receiveWay));
          ordersBean.setOrderStatus(0);
          ordersBean.setMessge(message);
          //保存订单
          bool = userService.saveNewOrdersService(ordersBean, orderList);//保存成功返回true，失败返回false
          //TODO 支付（1、payWay，0表示未支付，1表示微信支付，2表示支付宝支付 2、ordersBean里面存的是订单信息）
        }
      }//else if(chooseCode.equals("checkOld")){
    }
    
    if(bool){
      //TODO 目前是微支付，要支付的话需要调到支付页面
      request.setAttribute(Constants.RESULT, "下单成功");
      returnValue = Constants.PAGE_RESULT_USER;
      
    }
    else{
      request.setAttribute(Constants.RESULT, "下单失败");
      returnValue = Constants.PAGE_RESULT_USER;
    }
    return returnValue;
  }
}
