package com.cn.freemall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.freemall.bean.MessageBean;
import com.cn.freemall.bean.ProductBean;
import com.cn.freemall.bean.UserBean;
import com.cn.freemall.service.UserService;
import com.cn.freemall.util.Constants;
import com.cn.freemall.util.ValidateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
  @Inject
  public UserService userService;
  
  /**
   * 跳转到登陆页面
   * @author wang lei
   * @return 用户登录界面
   * */
  @RequestMapping("/toLogin")
  public String toLogin(HttpServletRequest request)throws Exception{
    String returnValue = Constants.PAGE_USER_LOGIN;
    return returnValue;
  }
  
  /**
   * 处理用户登陆
   * @author wang lei
   * */
  @RequestMapping("/doLogin")
  public String doLogin(HttpServletRequest request)throws Exception{
    String returnValue = "redirect:/user/toBuy";
    String userName = request.getParameter("userName");
    String passWord = request.getParameter("passWord");
    UserBean userBean = new UserBean();
    userBean.setUserName(userName);
    userBean.setPassWord(passWord);
    UserBean loginBean = userService.doLoginService(userBean);
    if(loginBean == null || loginBean.getUserId() == 0){
      returnValue = Constants.PAGE_USER_LOGIN;
      request.setAttribute(Constants.DATA_PARAMETER_RESULT, "用户名或密码错误");
    }
    else{
      request.getSession().setAttribute("loginBean", loginBean);
    }
    return returnValue;
  }
  
  /**
   * 跳转到注册页面
   * @author wang lei
   * */
  @RequestMapping("/toRegist")
  public String toRegist(HttpServletRequest request)throws Exception{
    String returnValue = Constants.PAGE_USER_REGIST;
    return returnValue;
  }
  
  /**
   * 处理注册
   * @author wang lei
   * */
  @RequestMapping("/doRegist")
  public String doRegist(HttpServletRequest request)throws Exception{
    String returnValue = Constants.PAGE_USER_REGIST;
    String userName = request.getParameter("userName");
    String passWord = request.getParameter("passWord");
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    UserBean userBean = new UserBean();
    userBean.setUserName(userName);
    userBean.setPassWord(passWord);
    userBean.setName(name);
    userBean.setPhoneNumber(phone);
    //判断数据是否传输成功
    if(!ValidateUtil.strIsEmpty(userName) && !ValidateUtil.strIsEmpty(passWord)
        && !ValidateUtil.strIsEmpty(name) && !ValidateUtil.strIsEmail(phone)){
      boolean bool = userService.checkUserNameIsExistService(userName);
      if(bool){
        request.setAttribute("userName", userName);
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute(Constants.DATA_PARAMETER_RESULT, "该用户名已被使用");
      }
      else{
        UserBean loginBean = userService.saveNewUserService(userBean);
        if(loginBean == null || loginBean.getUserId() == 0){
          request.setAttribute("userName", userName);
          request.setAttribute("name", name);
          request.setAttribute("phone", phone);
          request.setAttribute(Constants.DATA_PARAMETER_RESULT, "注册失败");
        }
        else{
          returnValue = "redirect:/user/toBuy";
          request.getSession().setAttribute("loginBean", loginBean);
        }
      }
    }//判断数据是否传输成功
    return returnValue;
  }
  
  /**
   * 退出登录
   * */
  @RequestMapping("exitLogin")
  public String exitLogin(HttpServletRequest request)throws Exception{
    request.getSession().invalidate();
    return Constants.PAGE_USER_LOGIN;
  }
  
  /**
   * 跳转到全部商品页面
   * */
  @RequestMapping("/toBuy")
  public String toBuy(HttpServletRequest request)throws Exception{
    String retrunValue = Constants.PAGE_USER_BUY;
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<ProductBean> productBeans = userService.getAllProductService();
    request.setAttribute("productBeans", productBeans);
    return retrunValue;
  }
  
  /**
   * 按分类显示商品列表
   * */
  @RequestMapping("/getProductsBytype")
  public String getProductsBytype(HttpServletRequest request)throws Exception{
    String returnValue = Constants.PAGE_USER_BUY;
    String typeId = request.getParameter("typeId");
    List<ProductBean> productBeans = userService.getProductsByTypeIdService(
        Integer.parseInt(typeId));
    request.setAttribute("productBeans", productBeans);
    return returnValue;
  }
  
  /**
   * 跳转到商品管理界面
   * */
  @RequestMapping("toManageGoods")
  public String toManageGoods(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<ProductBean> productBeans = userService.getProductsByUserService(loginBean);
    request.setAttribute("productBeans", productBeans);
    return Constants.PAGE_USER_MANAGE_GOODS;
  }
  
  /**
   * 上架商品
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/toAddGoods")
  public String toAddGoods(HttpServletRequest request) throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<ProductBean> productBeans = userService.getAllProductService();
    request.setAttribute("productBeans", productBeans);
    return Constants.PAGE_USER_ADD_GOODS;
  }
  
  /**
   * 添加新的商品
   * */
  @RequestMapping("/doAddProduct")
  public String doAddProduct(HttpServletRequest request)throws Exception{
    String returnValue = "redirect:/user/toManageGoods";
    String productName = request.getParameter("productName");
    String place = request.getParameter("place");
    String price = request.getParameter("price");
    String intro = request.getParameter("intro");
    String hiddeUrl = request.getParameter("hiddeUrl");
    String typeId = request.getParameter("typeId");
    
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    ProductBean productBean = new ProductBean();
    productBean.setUserId(loginBean.getUserId());
    productBean.setProductName(productName);
    productBean.setPlace(place);
    productBean.setPrice(Double.valueOf(price));
    productBean.setIntro(intro);
    productBean.setPic(hiddeUrl);
    productBean.setState(0);
    productBean.setTypeId(Integer.parseInt(typeId));
    boolean bool = userService.saveNewProductService(productBean);
    if(bool){
      System.out.println("save success");
    }
    else{
      System.out.println("save false");
    }
    return returnValue;
  }
  
  /**
   * 跳转到修改商品信息页面
   * */
  @RequestMapping("toUpdateGood")
  public String toUpdateGood(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String productId = request.getParameter("productId");
    System.out.println(productId);
    ProductBean productBean =  userService.getProductByProductIdService(
        Integer.parseInt(productId));
    productBean.setUserBean(loginBean);
    request.setAttribute("productBean", productBean);
    return Constants.PAGE_USER_UPDATE_GOODS;
  }
  
  /**
   * 修改商品信息
   * */
  @RequestMapping("/updateProductInfo")
  public String updateProductInfo(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String returnValue = Constants.PAGE_USER_RESULT;
    String productId = request.getParameter("productId");
    String productName = request.getParameter("productName");
    String place = request.getParameter("place");
    String price = request.getParameter("price");
    String intro = request.getParameter("intro");
    String hiddeUrl = request.getParameter("hiddeUrl");
    String typeId = request.getParameter("typeId");
    
    ProductBean productBean = new ProductBean();
    productBean.setProductId(Integer.parseInt(productId));
    productBean.setProductName(productName);
    productBean.setPlace(place);
    productBean.setPrice(Double.valueOf(price));
    productBean.setIntro(intro);
    productBean.setPic(hiddeUrl);
    productBean.setTypeId(Integer.parseInt(typeId));
    boolean bool = userService.updateProductInfoService(productBean);
    if(bool){
      returnValue = "redirect:/user/toManageGoods";
    }
    else{
      System.out.println("save false");
    }
    return returnValue;
  }
  
  /**
   * 下架商品
   * */
  @RequestMapping("deleteProduct")
  public String deleteProduct(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String productId = request.getParameter("productId");
    userService.deleteProductByProductIdService(Integer.parseInt(productId));
    return "redirect:/user/toManageGoods";
  }
  
  /**
   * 未读消息
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("toUnReadedMessages")
  public String toUnReadMessages(HttpServletRequest request) throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<MessageBean> messageBeans = userService.getUnReadedMessagesService(loginBean);
    request.setAttribute("messageBeans", messageBeans);
    return Constants.PAGE_USER_UNREADED_MESSAGES;
  }
  
  /**
   * 已读消息
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("toReadedMessages")
  public String toReadMessages(HttpServletRequest request) throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<MessageBean> messageBeans = userService.getReadedMessagesService(loginBean);
    request.setAttribute("messageBeans", messageBeans);
    return Constants.PAGE_USER_READED_MESSAGES;
  }
  
  /**
   * 发送的消息列表
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("toSendedMessages")
  public String toSendMessages(HttpServletRequest request) throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    List<MessageBean> messageBeans = userService.getSendedMessagesService(loginBean);
    request.setAttribute("messageBeans", messageBeans);
    return Constants.PAGE_USER_SENDED_MESSAGES;
  }
  
  /**
   * 消息详情
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("toMessageDetails")
  public String toMessageDetails(HttpServletRequest request) throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String messageId = request.getParameter("messageId");
    MessageBean messageBean = userService.toReadMessageService(
        Integer.parseInt(messageId));
    request.setAttribute("messageBean", messageBean);
    return Constants.PAGE_USER_MESSAGE_DETAILS;
  }
  
  /**
   * 跳转到发消息页面
   * */
  @RequestMapping("toSendMessage")
  public String toSendMessage(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String userId = request.getParameter("userId");
    UserBean userBean = userService.getUserByUserIdService(Integer.parseInt(userId));
    request.setAttribute("userBean", userBean);
    return Constants.PAGE_USER_SEND_MESSAGE;
  }
  
  /**
   * 保存留言
   * */
  @RequestMapping("saveMessage")
  public String saveMessage(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    String userId = request.getParameter("userId");
    String message = request.getParameter("message");
    MessageBean messageBean = new MessageBean();
    messageBean.setSendId(loginBean.getUserId());
    messageBean.setReceiveId(Integer.parseInt(userId));
    messageBean.setContent(message);
    boolean bool = userService.saveNewMessageService(messageBean);
    return "redirect:/user/toSendedMessages";
  }
  
  /**
   * 个人中心
   * */
  @RequestMapping("toUserCenter")
  public String toUserCenter(HttpServletRequest request)throws Exception{
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    if(loginBean == null){
      return Constants.PAGE_USER_LOGIN;
    }
    
    return Constants.PAGE_USER_PERSONAL_CENTER;
  }
  /**
   * 修改个人信息
   * */
  @RequestMapping("updateUserInfo")
  @ResponseBody
  public Map<String, Object> updateUserInfo(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String userId = request.getParameter("userId");
    String userName = request.getParameter("userName");
    String name = request.getParameter("name");
    String phoneNumber = request.getParameter("phoneNumber");
    String oldPSW = request.getParameter("oldPSW");
    String newPSW = request.getParameter("newPSW");
    if(!ValidateUtil.strIsEmpty(userId) && !ValidateUtil.strIsEmpty(userName) && 
        !ValidateUtil.strIsEmpty(name) && !ValidateUtil.strIsEmpty(phoneNumber)
        && !ValidateUtil.strIsEmpty(oldPSW) && !ValidateUtil.strIsEmpty(newPSW)){
      UserBean userBean = new UserBean();
      userBean.setUserId(Integer.parseInt(userId));
      userBean.setName(name);
      userBean.setPhoneNumber(phoneNumber);
      userBean.setPassWord(newPSW);
      boolean bool = userService.updateUserInfoService(userBean, oldPSW, userName);
      if(bool){
        returnMap.put(Constants.DATA_PARAMETER_RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.DATA_PARAMETER_RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.DATA_PARAMETER_RESULT, Constants.AJAX_FAIL_CODE);
    }
    
    return returnMap;
  }
  
  /**
   * 获取未读信息列表
   * */
  @RequestMapping("getUnReadedMessage")
  @ResponseBody
  public Map<String,Object> getUnReadedMessage(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    UserBean loginBean = (UserBean) request.getSession().getAttribute("loginBean");
    int count = userService.getUnReadedMessageCountService(loginBean.getUserId());
    returnMap.put("count", count);
    return returnMap;
  }
}
