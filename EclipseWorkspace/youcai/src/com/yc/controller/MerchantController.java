package com.yc.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yc.basic.exceptions.SessionTimeoutException;
import com.yc.bean.Advance;
import com.yc.bean.Dish;
import com.yc.bean.DishGroup;
import com.yc.bean.DishList;
import com.yc.bean.EvalueOrder;
import com.yc.bean.Inventory;
import com.yc.bean.Merchant;
import com.yc.bean.Message;
import com.yc.bean.Order;
import com.yc.bean.PhoneCode;
import com.yc.bean.PushOrder;
import com.yc.bean.RefundMessage;
import com.yc.bean.Type;
import com.yc.bean.msureOrder;
import com.yc.dao.MerchantDao;
import com.yc.dao.OrdersDao;
import com.yc.dao.PushOrderDao;
import com.yc.service.CommonService;
import com.yc.service.DishListService;
import com.yc.service.DishService;
import com.yc.service.InventoryService;
import com.yc.service.MerchantSellService;
import com.yc.service.MerchantService;
import com.yc.service.MessageService;
import com.yc.service.OrdersService;
import com.yc.service.PushOrderService;
import com.yc.service.RefundMessageService;
import com.yc.service.UserStarService;
import com.yc.util.Constants;
import com.yc.util.CryptogramUtil;
import com.yc.util.DateUtil;
import com.yc.util.InfoMation;
import com.yc.util.StringUtil;
import com.yc.util.SystemUtil;
import com.yc.util.TimeUtil;
import com.yc.util.ValidateUtil;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
  @Autowired
  private MerchantService merchantservice;

  @Autowired
  private PushOrderService pushOrderService;

  @Autowired
  private OrdersService ordersService;

  @Autowired
  private CommonService commonservice;

  @Autowired
  private InventoryService inventoryService;
  
  @Autowired
  private MerchantSellService merchantsellService;
  
  @Autowired
  private MerchantDao merchantdao;
  
  @Autowired
  private OrdersDao ordersDao;
  
  @Autowired
  private PushOrderDao pushordersDao;
  
  @Autowired
  private RefundMessageService refundMessageService;
  
  @Autowired
  private DishListService dishListService;
  
  @Autowired
  private UserStarService userStarService;
  
  @Autowired
  private MessageService messageService;
  
  @Autowired
  private DishService dishService;

  Logger log = Logger.getLogger(MerchantController.class);
/**
 * test merchantcontroller
 * 
 * */
  /*@RequestMapping("/getMerchantList")
  @ResponseBody
  public Map<String, Object> getMerchantList(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    resultMap.put("rows", merchantdao.getMerchantList());
    return resultMap;
  }
  */
  @RequestMapping(value = "/toMessage")
  public String toMessage(HttpServletRequest request) {
    return "merchant/message";
  }
  
  @RequestMapping(value = "/toRefundMessage")
  public String toRefundMessage(HttpServletRequest request) {
    return "merchant/refundMessage";
  }
   
  @RequestMapping(value = "/toMerchantAdvance")
  public String toMerchantAdvance(HttpServletRequest request) {
    return "merchant/merchantSells";
  }
  
  
  @RequestMapping(value = "/toMerchantInventory")
  public String tomInventory(HttpServletRequest request) {
    return "merchant/mInventory";
  }

  @RequestMapping(value = "/toMerchantHistoryOrder")
  public String tomhistoryOrder(HttpServletRequest request) {
    return "merchant/mHistoryOrder";
  }

  @RequestMapping(value = "/toMerchantProcesOrder")
  public String toMProcesOrder(HttpServletRequest request) {
    return "merchant/procesOrder";
  }
  
  @RequestMapping(value = "/toMerchantPushOrder")
  public String toMerchantPushOrder(HttpServletRequest request) {
    return "merchant/pushOrders";
  }

  @RequestMapping(value = "/toMerchantLogin")
  public String toMerchantLogin(HttpServletRequest request) {
    return "merchant/mLogin";
  }
//注册第一步
  @RequestMapping(value = "/toMerchantRegist")
  public String toMerchantRegist1(HttpServletRequest request) {
    return "merchant/merchantRegist1";
  }
  //注册第二步
  @RequestMapping(value = "/toMerchantRegist2")
  public String toMerchantRegist2(HttpServletRequest request) {
    return "merchant/merchantRegist2";
  }
//找回密码第一步
  @RequestMapping(value = "/toResetPassword")
  public String toresetpassword(HttpServletRequest request) {
    return "merchant/resetPassword1";
  }
  
//找回密码第二步
  @RequestMapping(value = "/toResetPassword2")
  public String toResetPassword2(HttpServletRequest request) {
    return "merchant/resetPassword2";
  }
  
  /**
   * 商家退出登录
  */
  @RequestMapping("/backToLogin")
  public String backToLogin(HttpServletRequest request)throws Exception {    
    request.getSession().invalidate(); 
    return "merchant/mLogin";
  }
  
  @RequestMapping(value = "/Login")
  @ResponseBody
  public Map<String, Object> merchantLogin(HttpServletRequest request)
      throws Exception {

    Map<String, Object> result = new HashMap<String, Object>();

    //获得密钥 
   String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
   String password = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("password")), key);
   String name = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("name")), key);
   String md5Name = CryptogramUtil.md5(name);
   String md5Password = CryptogramUtil.md5(password);
   log.debug("name:"+name+"....................password:"+password+"..........."+request.getAttribute("password2"));
    
   Merchant loginmerchant=merchantservice.merchantLogin(md5Name, md5Password);
   
   if(loginmerchant != null){
     //将邮箱解密后再放到LOGIN_SESSION_MERCHANT中
      String merchantEmial = InfoMation.decryptBasedDes(loginmerchant.getMerchantMail());
      loginmerchant.setMerchantMail(merchantEmial);
      request.getSession().setAttribute(Constants.LOGIN_SESSION_MERCHANT, loginmerchant);
      result.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);       
      result.put("msg", "登陆成功!");
   } //if (loginmerchant != null)
    
    //如果商家登录成功，返回失败消息
    else {
      result.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      result.put("msg", "登陆失败!请先确认你已通过审核,并检查你的密码和用户名");
    }//if (loginmerchant != null)的 else 
    return result;
  }
  //到商家首页
  @RequestMapping(value = "/toMerchantIndex")
  public String toMerchantIndex(HttpServletRequest request) throws Exception { 
    Merchant merchantinfo = getSessionMerchant(request);
    request.setAttribute("merchant", merchantinfo);
    int merchantId = merchantinfo.getMerchantId();
    int messageNumber = messageService.getMessageCount(merchantId);
    int refundMessageNumber = ordersService.getRefundMessageCount(merchantId);
    int pushOrderNumber = pushOrderService.getPushOrderCount(merchantId);
    //返回新消息（站内信，退单消息，抢单消息）
    request.setAttribute("messageNumber", messageNumber);
    request.setAttribute("refundMessageNumber", refundMessageNumber);
    request.setAttribute("pushOrderNumber", pushOrderNumber);
   
    return "merchant/merchantIndex";
  }
  //商家个人中心
  @RequestMapping(value = "/getMerchantInfo")
  public ModelAndView merchantInfo(HttpServletRequest request) throws Exception {
    ModelAndView modelandview = new ModelAndView("merchant/merchantInfoDetail");

    Merchant merchantinfo = getSessionMerchant(request);
    
    modelandview.addObject("merchant", merchantinfo);
    return modelandview;
  }

  /**
   * 获取验证码(未注册情况下)
   * 
   * @throws Exception
   * @return
   */
  @RequestMapping("/getsmsCode")
  @ResponseBody
  public Map<String, Object> getsmsCode(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    // 获得密钥 
    String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
    //解密数据
    String phoneNum = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("p")), key);

    phoneNum = ValidateUtil.valiStrIsPhone(phoneNum);
    phoneNum = SystemUtil.removeAllTagFromHtml(phoneNum);
    String md5PhoneNum = CryptogramUtil.md5(phoneNum);
   // isPhoneExist表示该电话号码是否已存在于数据库中，1：表示已存在，0：表示还不存在
    int isPhoneExist = merchantservice.getPhonenum(md5PhoneNum);

    // 注册时，验证数据库中不存在该电话号码后，再发送验证码
    if (isPhoneExist == 0) {
      resultMap = sendPhoneCode(request, phoneNum);      
    }
    else //手机号在数据库中已存在,不能再使用该手机号注册
    {
      resultMap.put("code", Constants.AJAX_FAIL_IDENTIFYING_CODE);
      resultMap.put("msg", "此手机号已被注册");
    }// if (isPhoneExist == 0) 的else
    return resultMap;

  }

  /**
   * 第一步：验证‘手机号 验证码’是否正确
   * 
   * @throws Exception
   * @return
   */
  @RequestMapping("/checkeSmsCode")
  @ResponseBody
  public Map<String, Object> redgistStep1(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    PhoneCode phoneCode = (PhoneCode) request.getSession().getAttribute(
        Constants.SEND_PHONE_CODE);
    // 还未发送验证码
    if (phoneCode == null) {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "还未获取验证码,不能跳到下一步");
    }//(phoneCode == null) 
    else   // condition1：已发送验证码
    {
      // 获得密钥 
      String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
      //解密数据
      String phoneNum =  CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("phoneNum")), key);
      phoneNum = ValidateUtil.valiStrIsPhone(phoneNum);
      phoneNum = SystemUtil.removeAllTagFromHtml(phoneNum);
      String smsCode = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("smsCode")), key);
      String phoneodescode = String.valueOf(phoneCode.getCode());
      int nowTime = DateUtil.convertCurrentDTTMtoInt();
      // 验证码超时
      if (nowTime - phoneCode.getCreateTime() >= Constants.SEND_CODE_WAIT_TIME) {
          request.getSession().removeAttribute(Constants.SEND_PHONE_CODE);
          resultMap.put("code", Constants.SMSCODE_TIMEOUT_ERROR);
          resultMap.put("msg", "验证码超时，请重新获取验证码");
      }// (nowTime - phoneCode.getCreateTime() >= Constants.SEND_CODE_WAIT_TIME)        
      else //condition2：验证码未超时
      {
          // condition3：验证注册手机号与发送验证码的手机号是不一致
          if (phoneodescode.equals(smsCode) && phoneCode.getPhone().equals(phoneNum)) {
            resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
          } //if (phoneodescode.equals(smsCode) && phoneCode.getPhone().equals(phoneNum))
          else 
          {
            resultMap.put("code", Constants.SMSCODE_CHECKE_FAIL_ERROR);
            resultMap.put("msg", "验证失败，请重新填写验证码");
          }//if (phoneodescode.equals(smsCode) && phoneCode.getPhone().equals(phoneNum))的else
       }//if (nowTime - phoneCode.getCreateTime() >= Constants.SEND_CODE_WAIT_TIME)的else
    }//if (phoneCode == null)的else
    /*
     * String key = getRandomEncrptCode(request); String phoneNum =
     * CryptogramUtil
     * .decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("phoneNum"
     * )),key); phoneNum = SystemUtil.removeAllTagFromHtml(phoneNum); String
     * smsCode = CryptogramUtil.decrypt(request.getParameter("smsCode"),key);
     */
    return resultMap;
  }

  @RequestMapping("/doRegist")
  @ResponseBody
  public Map<String, Object> doRegist(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    // 检查是否已发送验证码
    PhoneCode phoneCode = (PhoneCode) request.getSession().getAttribute(
        Constants.SEND_PHONE_CODE);
   System.out.println((phoneCode != null) +":(phoneCode != null)/n/n");
    //已获取验证码
    if (phoneCode != null){
      Merchant merchant = getMerchantFromRequest(request, phoneCode);
     //用户注册数据无误
      if(merchant != null){      
        //返回1：注册成功， 0：注册不成功
        boolean isRegist = merchantservice.addmerchantRegist(merchant);
        //数据插入数据库成功，（注册成功）
        if (isRegist) {
          request.getSession().removeAttribute(Constants.SEND_PHONE_CODE);
          resultMap.put("code", Constants.AJAX_SUCCESS_SKIP_CODE);
        }//(isRegist == 1)        
        else//注册失败，(数据插入失败)
        {
          resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
          resultMap.put("msg", "注册失败，请核对您数据的正确性！");
        } //if (isRegist == 1)     
      }// if(password.equals(repeatPassword))      
      else//用户注册数据有误
      {
        resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
        resultMap.put("msg", "密码不一致");
      }//if(merchant != null)的else
    }//
    else//还未获取验证码
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "注册失败，请先获取验证码");
    }// if (phoneCode != null) 的else
   return resultMap;
  }
  
  public Merchant getMerchantFromRequest(HttpServletRequest request, PhoneCode phoneCode) throws Exception {
 // 构造Merchant商户对象
    Merchant merchant = new Merchant();
    // 获得密钥 
    String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
    String encodeData = request.getParameter("t");    
  //获取数据
    Map<String, Object> data = CryptogramUtil.decryptAll(encodeData, key);
    String password = SystemUtil.removeAllTagFromHtml((String) data.get("password"));
    String repeatPassword = (String)data.get("repeatPassword");
    //密码一致
    if(!password.equals(repeatPassword)){
      merchant = null;
    }
    else //密码不一致
    {

      String email = SystemUtil.removeAllTagFromHtml((String) data.get("email"));          
      
      String linkman = SystemUtil.removeAllTagFromHtml((String) data.get("linkman")); 
      String IDcard = SystemUtil.removeAllTagFromHtml((String) data.get("IDcard"));
      String linkphone = SystemUtil.removeAllTagFromHtml((String) data.get("linkphone"));   
      linkphone = ValidateUtil.valiStrIsPhone(linkphone);
      String bankcode = SystemUtil.removeAllTagFromHtml((String) data.get("bankcode"));
      String shopname = SystemUtil.removeAllTagFromHtml((String) data.get("shopname"));
      double m_lng = Float.parseFloat(SystemUtil.removeAllTagFromHtml((String) data.get("markerlng")));
      double m_lat = Float.parseFloat(SystemUtil.removeAllTagFromHtml((String) data.get("markerlat")));
      String m_address = SystemUtil.removeAllTagFromHtml((String) data.get("place"));  
      String m_intro = "";          
      String registTime = TimeUtil.getCurrentTimeAndDate();  
      String registIp = SystemUtil.getRequestIp(request);
      // 加密数据再存入数据库email,IDcard,bankcode采用des方式password，phone（linkphone未加密）采用MD5加密。
      email = InfoMation.encryptBasedDes(email);
      System.out.println("..........................email:....."+email);
      IDcard = InfoMation.encryptBasedDes(IDcard); 
      bankcode = InfoMation.encryptBasedDes(bankcode); 
      password = CryptogramUtil.md5(password);
      String phone = CryptogramUtil.md5(phoneCode.getPhone());
      //log.debug("\n\nemail:"+email+"...............IDcard:"+IDcard+"..........bankcode:"+bankcode+"..........password:"+password+".....phone:"+phone);
      merchant.setBankCard(bankcode);
      merchant.setBossName(linkman);
      merchant.setIdCard(IDcard);
      merchant.setLastLoginIp(registIp);
      merchant.setLastLoginTime(registTime);
      merchant.setLinkPhone(linkphone);
      merchant.setMerchantMail(email);  
      merchant.setMerchantPassword(password);
      merchant.setMerchantPhone(phone);  
      merchant.setShopName(shopname);
      merchant.setRegisterTime(registTime);
      merchant.setMerchantLng(m_lng);
      merchant.setMerchantLat(m_lat);
      merchant.setMerchantAddress(m_address);
      merchant.setMerchantIntro(m_intro);
    }
    return merchant;
  }

  /**
   * 获取验证码(已注册情况下)
   * 
   * @throws Exception
   * @return
   */
  @RequestMapping("/getsmsCodeToResetpassword")
  @ResponseBody
  public Map<String, Object> getsmsCodeToResetpassword(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    // 获得密钥
    String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
    String phoneNum = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("p")), key);
    phoneNum = ValidateUtil.valiStrIsPhone(phoneNum);
    phoneNum = SystemUtil.removeAllTagFromHtml(phoneNum);
    String md5PhoneNum =  CryptogramUtil.md5(phoneNum);
    // phonenumIsExist表示该电话号码是否已存在于数据库中，1：表示已存在，0：表示还不存在
    int phonenumIsExist = merchantservice.getPhonenum(md5PhoneNum);
    // 注册时，验证数据库中存在该电话号码后，再发送验证码
    if (phonenumIsExist == 1) {
      resultMap = sendPhoneCode(request, phoneNum);
    }
    else //重置密码时，数据库中不存在电话号码，不发送验证码
    {
      resultMap.put("code", Constants.AJAX_FAIL_IDENTIFYING_CODE);
      resultMap.put("msg", "此手机号还未注册");
    }// if (phonenumIsExist == 1)的else
    return resultMap;        
  }
  
  /*
   * 调用接口发送手机验证码
   * @param request:请求，phoneNum：要使用的电话号码
   * @return Map<String, Object> 发送验证码情况
   */
 public Map<String, Object> sendPhoneCode(HttpServletRequest request, String phoneNum)
  throws Exception {
   Map<String, Object> resultMap = new HashMap<String, Object>();
   // 随机生成验证码
   int identifyingCode = 111111;//SystemUtil.getPhoneCheckCode();
   System.out.println("\n\n\n ---- vertifyCode: " + identifyingCode);
   int now = DateUtil.convertCurrentDTTMtoInt();
   //session中不存在可用的SEND_PHONE_CODE
   if (null == request.getSession().getAttribute(Constants.SEND_PHONE_CODE)
       || (now - ((PhoneCode) request.getSession().getAttribute(
               Constants.SEND_PHONE_CODE)).getCreateTime() > Constants.SEND_CODE_WAIT_TIME)) {
     // 调用手机发送验证码方法(暂时不用)
     // String smsstring = commonservice.doSendPhoneCheckCode(phoneNum, identifyingCode);
     // System.out.println(smsstring+"......................\n\n");
     request.getSession().setAttribute(Constants.SEND_PHONE_CODE,
         new PhoneCode(phoneNum, identifyingCode, now));
     resultMap.put("code", Constants.AJAX_SUCESS_IDENTIFYING_CODE);
     resultMap.put("msg", "已发送");
   }
   else //session中已有可用的SEND_PHONE_CODE，不能重复发送验证码
   {
     // 先前验证码还未失效
     resultMap.put("code", Constants.AJAX_FAIL_IDENTIFYING_CODE);
     resultMap.put("msg", "请等待...");
   } /*if (null == request.getSession().getAttribute(Constants.SEND_PHONE_CODE)
       || (now - ((PhoneCode) request.getSession().getAttribute(
           Constants.SEND_PHONE_CODE)).getCreateTime() > Constants.SEND_CODE_WAIT_TIME)) 的else*/
   return resultMap;
 }

  /**
   * 登录忘记密码时，手机发送验证码方式重置密码
   * 
   * @throws Exception
   * @return
   */
  @RequestMapping("/doResetpassword")
  @ResponseBody
  public Map<String, Object> doResetpassword(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    // 检查是否已发送验证码
    PhoneCode phoneCode = (PhoneCode) request.getSession().getAttribute(
        Constants.SEND_PHONE_CODE);
    if (phoneCode == null) {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "未获取验证码或验证码超时，请重新获取验证码");
    }
    else //已发送验证码
    {
     /* //获取数据
      String phoneNum = (String) request.getParameter("phoneNum");
      phoneNum = SystemUtil.removeAllTagFromHtml(phoneNum);
      String smsCode = (String) request.getParameter("smsCode");*/
      String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
      String newPassword = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(
          (String) request.getParameter("password")), key);
      newPassword = SystemUtil.removeAllTagFromHtml(newPassword);
      String repeatNewPassword = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(
          (String) request.getParameter("repeatPassword")), key);
      //加密密码
      String md5NewPass = CryptogramUtil.md5(newPassword);           
      //加密手机号
      String md5phoneCodePhone = CryptogramUtil.md5(phoneCode.getPhone());
      //session中的phoneCode验证合格
      if (newPassword.equals(repeatNewPassword)) {
        // 重置密码是否成功，true：成功，false：失败
        Merchant merchant = new Merchant();
        merchant.setMerchantPassword(md5NewPass);
        merchant.setMerchantPhone(md5phoneCodePhone);
        // isResetpassword取值false（没有修改成功），true（修改成功）
        boolean isResetpassword = merchantservice.updateMerchantPassword(merchant);
       // 密码修改成功
        if (isResetpassword) {         
          request.getSession().removeAttribute(Constants.SEND_PHONE_CODE);
          resultMap.put("code", Constants.AJAX_SUCCESS_SKIP_CODE);
          resultMap.put("msg", "重置密码成功");
       }// if (isResetpassword == true) 
        else //数据库修改密码失败
        {
          resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
          resultMap.put("msg", "密码修改失败，请重新修改");
        }//else (isResetpassword == false) 
      }//if (newPassword.equals(repeatNewPassword))
      else{
        resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
        resultMap.put("msg", "密码修改失败，前后数据不一致");
      }/*else (newPassword.equals(repeatNewPassword)) */
    }//else (phoneCode == null) 
    
    return resultMap;

  }

  /**
   * 修改密码（已知原密码，并根据原密码进行修改）
   * 
   * @return
   * @throws Exception
   */
  @RequestMapping("/modifypassword")
  @ResponseBody
  public Map<String, Object> modifypassword(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();

    Merchant sessionMerchant = getSessionMerchant(request);
     String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);     
    
     //获取数据
     String oldPassword = CryptogramUtil.decrypt
         (ValidateUtil.valiStrIsEmpty((String)request.getParameter("oldPassword")),key); 
     String newPassword = CryptogramUtil.decrypt
     (ValidateUtil.valiStrIsEmpty((String)request.getParameter("newPassword")),key);
     String repeatNewPassword = CryptogramUtil.decrypt
         (ValidateUtil.valiStrIsEmpty((String)request.getParameter("repeatNewPassword" )),key);
     //去除标签
     oldPassword = SystemUtil.removeAllTagFromHtml(oldPassword); 
     newPassword =  SystemUtil.removeAllTagFromHtml(newPassword);
     //md5加密密码
     String md5oldPassword = CryptogramUtil.md5(oldPassword); 
     String md5newPassword = CryptogramUtil.md5(newPassword);

     String loginMerchantPassword = sessionMerchant.getMerchantPassword();
     log.debug("\n\n"+oldPassword+"..............oldPassword"+".key..........."+key);
     //两次新密码输入一致
     if(newPassword.equals(repeatNewPassword)){
      //原密码输入正确
      if(md5oldPassword.equals(loginMerchantPassword)){
        sessionMerchant.setMerchantPassword(md5newPassword);
        // passwordChanged标志是否修改成功 false:修改没成功，true：密码修改成功
        boolean passwordChanged = merchantservice.updateMerchantPassword(sessionMerchant);
        //密码修改成功
        if (passwordChanged) {
          request.getSession().setAttribute(Constants.LOGIN_SESSION_MERCHANT,
              sessionMerchant);
          resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
          resultMap.put("msg", "密码修改成功");
        } //if (passwordChanged == true)
        else{
          resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
          resultMap.put("msg", "密码修改失败");
        }//else (passwordChanged == false)
      }//if(oldPassword.equals(loginMerchantPassword))
      else  //原密码输入错误
      {
        resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
        resultMap.put("msg", "原密码错误");
      }// else (oldPassword.equals(loginMerchantPassword))

    }
    else //两次新密码输入不一致
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "两次新密码输入不一致");
    }  
    return resultMap;
  }

  /**
   * 修改简介
   * 
   * @return
   * @throws Exception
   */
  @RequestMapping("/modifyIntro")
  @ResponseBody
  public Map<String, Object> modifyIntro(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();

    /*Merchant sessionMerchant = (Merchant) request.getSession().getAttribute(
        Constants.LOGIN_SESSION_MERCHANT);*/
    Merchant sessionMerchant = getSessionMerchant(request);
   // String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);      
    //获取解密数据
    String newIntro = request.getParameter("newIntro");       
    newIntro = SystemUtil.removeAllTagFromHtml(newIntro);

    // TODO解密后续解密
    // modifystate(修改状态)  true:修改成功，false:修改失败
    boolean modifystate = merchantservice.updateMerchantIntro(
        sessionMerchant.getMerchantId(), newIntro);
    //简介修改成功
    if (modifystate) {
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
      resultMap.put("msg", "简介修改成功");
      sessionMerchant.setMerchantIntro(newIntro);
      request.getSession().setAttribute(Constants.LOGIN_SESSION_MERCHANT,
          newIntro);
    }// if (modifystate == true)
    else //简介修改失败
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "简介修改失败");
    }// else (modifystate == false)
   
    return resultMap;
  }
  
  /**
   * 修改图标
   * @return
   * @throws Exception
   */
  @RequestMapping("/changeLogo")
  @ResponseBody
  public Map<String, Object> changeLogo(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    String logoUrl = request.getParameter("logoUrl");
    /*Merchant sessionMerchant = (Merchant) request.getSession().getAttribute(
        Constants.LOGIN_SESSION_MERCHANT);*/
    Merchant sessionMerchant = getSessionMerchant(request);
 // isLogoUpdate(修改状态)  true:修改成功，false:修改失败
    boolean isLogoUpdate = merchantservice.updateMerchantLogo(sessionMerchant.getMerchantId(), logoUrl);
    if (isLogoUpdate) {
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
      resultMap.put("msg", "图标修改成功");
      sessionMerchant.setMerchantLogo(logoUrl);
      request.getSession().setAttribute(Constants.LOGIN_SESSION_MERCHANT,
          sessionMerchant);
    }// if (modifystate == true)
    else //简介修改失败
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "图标修改失败");
    }// else (modifystate == false)
   
    return resultMap;
  }

  /**
   * 商家获取可抢订单
   * 
   * @return
   * @throws Exception
   */
 /* @RequestMapping("/merchantSelectPushOrder")

  public Map<String, Object> merchantSelectPushOrder(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Merchant sessionMerchant = (Merchant) request.getSession().getAttribute(
        Constants.LOGIN_SESSION_MERCHANT);
    List<PushOrder> pushOrderList = pushOrderService.selectPushOrder(sessionMerchant.getMerchantId());   
    //有需要推送的订单
    resultMap.put("total",  (pushOrderList.size()>0)? pushOrderList.size():0);      
    resultMap.put("rows", pushOrderList);
    if (pushOrderList != null && pushOrderList.size() > 0) {
      modelandview.addObject("pushOrderList", pushOrderList);
    }
    else//暂时还没有推送的订单
    {
      modelandview.addObject("msg", "暂时还没有推送的订单");
    }//else (pushOrderList != null && pushOrderList.size() > 0)
    return resultMap;

  }*/

  /**
   * 商家轮询获取可抢订单
   * 
   * @throws Exception
   */
  @RequestMapping("/mLunxunPushOrder")
  @ResponseBody

  public Map<String, Object> mLunxunPushOrder(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Merchant sessionMerchant = getSessionMerchant(request);
    List<PushOrder> pushOrderList = pushOrderService.selectPushOrder(sessionMerchant.getMerchantId());
    List<Integer> goIdList = new ArrayList<Integer>();
  //有需要推送的订单
    resultMap.put("total",  (pushOrderList.size()>0)? pushOrderList.size():0);      
    resultMap.put("rows", pushOrderList);//goId
    //将所有本次查询出来推送订单消息的id存入session中，以便后续对未抢订单的删除
    for(int i=0; i<pushOrderList.size(); i++){
      goIdList.add(pushOrderList.get(i).getGoId());
    }
    request.getSession().setAttribute("goIdList", goIdList);
    
    if (pushOrderList != null && pushOrderList.size() > 0) {
      resultMap.put("pushOrderList", pushOrderList);
    }
    return resultMap;

  }

  /**
   * 商家抢单
   * 
   * @param msureOrders
   *          :商家的抢单列表信息
   * @return
   * @throws Exception
   */
  @RequestMapping("/mSureOrder")
  @ResponseBody

  public Map<String, Object> mSureOrder(
      @RequestBody List<msureOrder> merchantSureOrders, HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Merchant sessionMerchant = getSessionMerchant(request);
    int merchantId = sessionMerchant.getMerchantId();
    // 为确认抢单列表添加商家id
    /* for (int i = 0; i < merchantSureOrders.size(); i++) {
      merchantSureOrders.get(i).setMid(merchantId);
    }*/
    @SuppressWarnings("unchecked")
    List<Integer> goIdList = (ArrayList<Integer>)request.getSession().getAttribute("goIdList");  
    System.out.println(goIdList.get(0)+"........................");
   //获取订单列表
    boolean updatePushOrder = pushOrderService.mSureOrder(merchantSureOrders, goIdList);
    //List<PushOrder> pushOrderList = pushOrderService.selectPushOrder(merchantId);
   
    // 将新的可抢单数据返回
    /*if (pushOrderList != null && pushOrderList.size() > 0) {
      resultMap.put("total", (pushOrderList.size()>0)? pushOrderList.size():0);
      resultMap.put("rows", pushOrderList);
    }*/
    // 抢单成功
    if (updatePushOrder) {
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }// if (updatePushOrder == 0)
    else // 抢单失败
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "抢单失败,请重新抢单");      
    }   
    return resultMap;

  }

  /**
   * 商家获取历史单
   * 
   * @return Map<String, Object> 返回的历史订单列表
   * @throws Exception
   */
  @RequestMapping("/mgetHistoryOrder")
  @ResponseBody

  public Map<String, Object> mgetHistoryOrder(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    int pageSize = 10;
    Merchant sessionMerchant = getSessionMerchant(request);
    int merchantId = sessionMerchant.getMerchantId();
    int pageNum = Integer.parseInt(request.getParameter("pageNum"));
    System.out.println("............................................pageNum:"+pageNum);
    List<Order> historyOrderlist = ordersService.mSelectHistoryOrders(merchantId, (pageNum-1)*pageSize, pageSize);
    int historyOrderCount = ordersService.selectHistoryOrderCount(merchantId);
    // 将历史订单数据返回
    resultMap.put("total",  historyOrderCount);      
    resultMap.put("rows", historyOrderlist);
   /* if (historyOrderlist != null && historyOrderlist.size() > 0) {
      resultMap.put("historyOrderlist", historyOrderlist);
    }
    else //暂无历史订单数据
    {
      resultMap.put("msg", "暂无历史订单");
    }// else (historyOrderlist != null && historyOrderlist.size() > 0)
*/    return resultMap;

  }

  /**
   * 商家批量删除历史订单
   * 
   * @return Map<String, Object> 返回的历史订单列表
   * @throws Exception
   */
  @RequestMapping("/mdeleteOrder")

  @ResponseBody
  public Map<String, Object> mdeleteOrder(@RequestBody List<Integer> orderids,
      HttpServletRequest request) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
   /* Merchant sessionMerchant = (Merchant) request.getSession().getAttribute(
        Constants.LOGIN_SESSION_MERCHANT);*/
    // 批量删除订单 deletenNmber:删除订单的数量
    int deletenNmber = ordersService.deleteOrderM(orderids);
    /*// 获取历史订单
    List<Order> historyOrderlist = ordersService
        .mSelectHistoryOrders(sessionMerchant.getMerchantId());
    //返回历史订单
    if (historyOrderlist != null && historyOrderlist.size() > 0) {
      resultMap.put("historyOrderlist", historyOrderlist);
    }*/
    if (deletenNmber == 0) {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("deletemsg", "删除失败，请重新删除");
    } 
    else //删除成功
    {
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }//  else (deletenNmber == 0)
    return resultMap;

  }

  /**
   * 商家获取未完成订单
   * 
   * @return Map<String, Object> 返回的未完成订单
   * @throws Exception
   */
  @RequestMapping("/MselectProceOrder")
  @ResponseBody

  public Map<String, Object> MselectProceOrder(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Merchant sessionMerchant = getSessionMerchant(request);
    List<Order> proceOrderlist = ordersService.selectProceOrderM(sessionMerchant.getMerchantId());
    //将商家的未完成订单返回
    resultMap.put("total",  (proceOrderlist.size()>0)? proceOrderlist.size():0);      
    resultMap.put("rows", proceOrderlist);
   /* if (proceOrderlist != null && proceOrderlist.size() > 0) {
      resultMap.put("proceOrderlist", proceOrderlist);
    }*/
    return resultMap;

  }

  /**
   * 商家获取库存信息
   * 
   * @throws Exception
   */
  @RequestMapping("/MgetInventory")
  @ResponseBody

  public Map<String, Object> MgetInventory(HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Merchant sessionMerchant = getSessionMerchant(request);
    List<Inventory> inventorylist = inventoryService
        .getInventoryM(sessionMerchant.getMerchantId());
    //将商家的库存信息返回
    resultMap.put("total",  (inventorylist.size()>0)? inventorylist.size():0);      
    resultMap.put("rows", inventorylist);
   /* if (inventorylist != null && inventorylist.size() > 0) {
      resultMap.put("inventorylist", inventorylist);
    }*/
    return resultMap;

  }

  /**
   * 商家批量修改库存
   * 
   * @return Map<String, Object> 返回的历史订单列表
   * @throws Exception
   */
  @RequestMapping("/updateInventory")
  @ResponseBody
  public Map<String, Object> updateInventory(
      @RequestBody List<Inventory> inventorys, HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    Merchant sessionMerchant = getSessionMerchant(request);
    //为需要修改的库存数据添加修改时间
    for (int i = 0; i < inventorys.size(); i++) {
      inventorys.get(i).setiTime(TimeUtil.getCurrentTimeAndDate());
    }
    // updateNum:修改的库存数据条数
    int updateNum = inventoryService.updateInventoryM(inventorys);
    // 返回修改后的库存信息
    List<Inventory> inventorylist = inventoryService
        .getInventoryM(sessionMerchant.getMerchantId());
    if (inventorylist != null && inventorylist.size() > 0) {
      resultMap.put("rows", inventorylist);
      resultMap.put("total", (inventorylist.size()> 0 )? inventorylist.size() : 0 );
    }

    if (updateNum == 0) {
      resultMap.put("updatemsg", "数据修改失败");
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }
    else //库存信息修改失败
    {
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }// else (updateNum == 0)
    return resultMap;

  }
  
  /**
   * 商家根据条件查询销售额情况
   */
  @RequestMapping("/getMerchantSells")
  @ResponseBody
  public Map<String, Object> getMerchantSells2( HttpServletRequest request)
      throws Exception {
    //准备需要的返回数据对象
    Map<String, Object> resultMap = new HashMap<String, Object>();
    List<Advance> advanceList = null;
    List<Map<String, Object>> totallist=new ArrayList<Map<String, Object>>();
    List<Advance> totalAdvance = null;
    
  //准备查询销售额的条件数据
    Merchant sessionMerchant = getSessionMerchant(request);    
    Date startDate = DateUtil.formatStringToDateDT((request.getParameter("startDate") == null)? Constants.CURRENT_DATE_STRING:request.getParameter("startDate"));     
    Date endDate = DateUtil.formatStringToDateDT((request.getParameter("endDate") == null)? TimeUtil.getCurrentDate():request.getParameter("endDate"));
    //由于数据库的between and 不会将and后面的匹配日期查出来，这里多加了一天
    endDate = TimeUtil.addDays(endDate, 1);
    //int pageNum = (request.getParameter("pageNum") == null)? 1:Integer.parseInt(request.getParameter("pageNum"));
    String handelStatus = (request.getParameter("handelStatus") == null)? "all":request.getParameter("handelStatus");
    String refundStatus = (request.getParameter("refundStatus") == null)? "all":request.getParameter("refundStatus");
    int merchantId = sessionMerchant.getMerchantId();
    //多条件查询销售情况，得到销售数据
    advanceList = merchantsellService.getMerchantAdvance(startDate, endDate, handelStatus, refundStatus, merchantId);
    totalAdvance = merchantsellService.getMerchantTotalAdvance(startDate, endDate, handelStatus, refundStatus, merchantId);
    
    if(totalAdvance != null && totalAdvance.size() >0){
      Map<String, Object> totalMap = new HashMap<String, Object>();
      int totalOrderNum = 0;
      int totIncome=0;
     
      for(int i=0; i<totalAdvance.size(); i++){
        Map<String, Object> detailMap = new HashMap<String, Object>();
        String summayStr = new String(); 
        totalOrderNum =  totalOrderNum +totalAdvance.get(i).getOrderId();
        if(totalAdvance.get(i).getRefund() == 0){
          totIncome = totIncome + (int)totalAdvance.get(i).getMoney();
          summayStr = "未退订单总数:"+totalAdvance.get(i).getOrderId()+"+可得金额："+
          totalAdvance.get(i).getMoney();
        }else if(totalAdvance.get(i).getRefund() == 2){
          totIncome = totIncome + (int)(totalAdvance.get(i).getMoney() * 0.05);
          summayStr = "退单总数:"+totalAdvance.get(i).getOrderId()+
              " +总交易额:"+totalAdvance.get(i).getMoney()+"+可得金额:"+
              (int)(totalAdvance.get(i).getMoney()* 0.05);
        }else{
          summayStr = "退单申请中订单总数:"+totalAdvance.get(i).getOrderId()+
              "+总交易额:"+totalAdvance.get(i).getMoney();
        }
       
        detailMap.put("summary", summayStr);
        totallist.add(detailMap);
      }

      totalMap.put("summary", "订单总数:"+totalOrderNum+"+总可得金额："+totIncome);
      totallist.add(totalMap);
    }
    
    //封装返回的数据
          
    resultMap.put("rows", advanceList);
   
    //构造总计对象
          
    resultMap.put("footer", totallist);
    return resultMap;
  }
  
  /**
   * 商家查询还未读过的退单消息 
   * @param 
   */
  @RequestMapping("/getNotReadRefund")
  @ResponseBody
  public Map<String, Object> getNotReadRefund( HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    Merchant sessionMerchant = getSessionMerchant(request);
    int merchantId = sessionMerchant.getMerchantId();
   // merchantId = 1;//for test
    //refundlist :数据库中获得的退单消息列表
    List<RefundMessage> refundlist = refundMessageService.getNotReadRefund(merchantId);
    resultMap.put("total", (refundlist.size()>0)? refundlist.size():0);      
    resultMap.put("rows", refundlist);

   return resultMap;
  }
  
  /**
   * 商家通过orderid获取dishlist列表
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/getDishListByoid")
  @ResponseBody
  public Map<String, Object> getDishListByoid( HttpServletRequest request)
      throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    int oid =Integer.parseInt( request.getParameter("orderId"));
    //System.out.println("oid............"+oid);
    //数据库中通过订单id获取的菜单列表
    List<DishList> dishList = dishListService.getDishlistByO_Id(oid);
    resultMap.put("total", (dishList.size()>0)? dishList.size():0);      
    resultMap.put("rows", dishList);
    return resultMap;
  }
  
  /**
   * 将退单消息批量标记为已读
   * @param refundIds：退单消息id数组
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/flagRefund")
  @ResponseBody  
  public Map<String, Object> flagRefund(@RequestBody List<Integer> refundIds,
      HttpServletRequest request)throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    //isFlag:是否将退单消息标记为已读
    boolean isFlag = refundMessageService.flagRefundMessage(refundIds);
    //将退单消息标记为已读成功
    if(isFlag){
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }
    else //将退单消息标记为已读失败
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }
    return resultMap;
  }
  
  /**
   * 商家就某一订单给用户打分
   * @param evaluates（由订单号orderId与所打分数cord构成的对象组成的数组）
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/evalueOrder")
  @ResponseBody  
  public Map<String, Object> merchantEvalueOrder(@RequestBody List<EvalueOrder> evaluates,
      HttpServletRequest request)throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    //isEvaluate：商家对用户的打分结果，true：打分成功， false：打分失败
    boolean isEvaluate = userStarService.updateUserStar(evaluates);
    if(isEvaluate){
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }
    else //商家对用户的打分失败
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }
    return resultMap;
  }
  
  /**
   * 商家获取还未读过的站内信
  */
  @RequestMapping("/getMessage")
  @ResponseBody
  public Map<String, Object> getMessage(HttpServletRequest request)throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
   //获取商家id
    Merchant sessionMerchant = getSessionMerchant(request);
    int merchantId = sessionMerchant.getMerchantId();
    //通过商家id获取站内信
    List<Message> messageList = messageService.getMessageForMerchant(merchantId);
    //返回得到的站内信
    resultMap.put("total", (messageList.size()>0)? messageList.size():0);      
    resultMap.put("rows", messageList);
    return resultMap;
  }
  
  /**
   * 商家delete读过的站内信
  */
  @RequestMapping("/deleteMessage")
  @ResponseBody
  public Map<String, Object> deleteMessage(@RequestBody List<Integer> messageIds,
       HttpServletRequest request)throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    //标记已读站内信是否成功
    boolean deleteResult = messageService.flagMessage(messageIds);
  //标记已读站内信成功
    if(deleteResult){
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }
    else  //标记已读站内信失败
    {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }   
    return resultMap;
  }
  
  /**
   * 商家轮询新消息
  */
  @RequestMapping("/merchantLunxunMessage")
  @ResponseBody
  public Map<String, Object> merchantLunxunMessage(HttpServletRequest request)throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>(); 
      Merchant sessionMerchant = getSessionMerchant(request);
        int merchantId = sessionMerchant.getMerchantId();
       /* int messageNumber = messageService.getMessageCount(merchantId);
        int refundMessageNumber = ordersService.getRefundMessageCount(merchantId);*/
        int pushOrderNumber = pushOrderService.getPushOrderCount(merchantId);
        //返回新消息（站内信，退单消息，抢单消息）
        /*resultMap.put("messageNumber", messageNumber);
        resultMap.put("refundMessageNumber", refundMessageNumber);*/
        resultMap.put("pushOrderNumber", pushOrderNumber);
        return resultMap;
    
    
    //获取需要展示在商家主页的新消息（站内信，退单消息，抢单消息）
    
  }
  
  public Merchant getSessionMerchant(HttpServletRequest request) throws SessionTimeoutException{
    Merchant sessionMerchant = (Merchant) request.getSession().getAttribute(
        Constants.LOGIN_SESSION_MERCHANT);
    return sessionMerchant;
  }
  
  //......................................................................................toDish
/*//进入套餐添加页面
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
 public Map<String, Object> addDishGroup( HttpServletRequest request)
     throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    String dishes = request.getParameter("dishGroup");
    String groupName = request.getParameter("groupName");
    String groupPic = request.getParameter("groupPic");
    int groupTypeId = Integer.parseInt(request.getParameter("groupTypeId"));
    Double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
    Double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));
    String groupIntro = request.getParameter("groupIntro");
    //System.out.println(".....................dishGroup:"+dishes);
    
    DishGroup dishgroup = new DishGroup();
    dishgroup.setDishes(dishes);
    dishgroup.setGroupIntro(groupIntro);
    dishgroup.setGroupName(groupName);
    dishgroup.setGroupPic(groupPic);
    dishgroup.setOriginalPrice(oldPrice);
    dishgroup.setRetailPrice(retailPrice);
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
     List<DishGroup> dishGroupList = dishService.getDishGroupList();
     if(dishGroupList!=null && dishGroupList.size() > 0){
       for(int i=0; i<dishGroupList.size(); i++){
         dishGroupList.get(i).setDishList(StringUtil.jsonToList(dishGroupList.get(i).getDishes()));
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
 public Map<String, Object> deleteDishGroup( HttpServletRequest request)
     throws Exception{
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    String groupId = request.getParameter("groupId");
    System.out.println(".....................dishGroup:"+groupId);
    boolean isDelete = dishService.deleteDishGroup(Integer.parseInt(groupId));
    List<DishGroup> dishGroupList = dishService.getDishGroupList();
    resultMap.put("dishGroupList", dishGroupList);
    if(isDelete){
      resultMap.put("code", Constants.AJAX_SUCCESS_ALERT_CODE);
    }else{
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
    }
    return resultMap;
  }*/
  
}
