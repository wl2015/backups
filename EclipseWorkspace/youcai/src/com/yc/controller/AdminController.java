package com.yc.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.util.Constants;
import com.yc.util.CryptogramUtil;
import com.yc.util.DateUtil;
import com.yc.util.InfoMation;
import com.yc.util.Page;
import com.yc.util.ResultCode;
import com.yc.util.SystemUtil;
import com.yc.util.TimeUtil;
import com.yc.util.ValidateUtil;

import com.yc.basic.exceptions.CustomException;
import com.yc.bean.Admin;
import com.yc.bean.AdvanceDetail;
import com.yc.bean.CreateAdvance;
import com.yc.bean.Dish;
import com.yc.bean.Limits;
import com.yc.bean.Merchant;
import com.yc.bean.MerchantWithCount;
import com.yc.bean.MessageText;
import com.yc.bean.Order;
import com.yc.bean.PhoneCode;
import com.yc.bean.RefundOrder;
import com.yc.bean.Sales;
import com.yc.bean.SalesTotal;
import com.yc.bean.SalesList;

import com.yc.service.AdminService;
import com.yc.service.DishService;
import com.yc.service.InventoryService;
import com.yc.service.ManageMerService;
import com.yc.service.UserService;


/**
 *
 *管理员控制层 
 * @author 
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;
  
  @Autowired
  private ManageMerService manageMerService;
  
  @Autowired
  private DishService dishService;
  
  @Autowired
  private InventoryService inventoryService;
  
  @Autowired
  private UserService userService;
  
  
  //初始化Log4j的一个实例，让这个实例在以后的打印中，题头都带上这个类名
  Logger log=Logger.getLogger(AdminController.class);

  
/**  
 * 配置首页
 */
  /**
   * 进入点菜页面
   * @author wang lei
   * @param request
   * @throws Exception
   * */
  @RequestMapping("/index")
  public String toOrderDish(HttpServletRequest request) throws Exception{

    List<Dish> dishList = new ArrayList<Dish>();
    dishList = userService.getDishList();
    request.setAttribute("dishList", dishList);
    
    return "user/orderDish";
  }  

  
/**
 * 管理员  
 */
  
  
  /**
   * 管理员登录页面
   * @param request
   * @return
   */
  @RequestMapping("/login")
  public String test(HttpServletRequest request) {
    return "admin/adminLogin";
  }
  /**
   * 返回管理员页面
   * @param request
   * @return
   */
  @RequestMapping("/back")
  public String back(HttpServletRequest request) {
    return "admin/adminIndex";
  }

  /**
   * 管理员登录
   * 
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "adminLogin")
  @ResponseBody
  public Map<String,Object> adminLogin(Model model,HttpServletRequest request)
      throws Exception {
      
//      /**
//     * 全局异常测试
//     */
//    int a = 19/0;
//    System.out.println(a);
    Map<String,Object> resultMap = new HashMap<String, Object>();
      
    Admin admin = new Admin();
    List<Limits> limitsList = null;
    
    String loginType = (String)request.getParameter("type");
    System.out.println(loginType);
    /**
     * 用手机登陆
     */
    if (loginType.equals("手机")) {
        String phone = request.getParameter("account");
        String password = InfoMation.encryptBasedDes(request.getParameter("password"));       
        admin.setAdminPhone(phone);
        admin.setAdminPassword(password);
       
        if (adminService.queryAdminForPhone(admin) != null) {

          Admin admins = adminService.phoneLogin(admin);
          String adminMail = InfoMation.decryptBasedDes(admins.getAdminMail());
          admins.setAdminMail(adminMail);
          
          limitsList = adminService.getAdminLimitsInfo(admins.getAdminId());//获得管理员权限列表
          
          int refundOrderCount = adminService.getRefundOrderCount();//得到用户退款申请提醒
          int verifyMerchantCount = (int)manageMerService.getAllVerifyMerchantCount();//得到新注册商家提醒

          request.getSession().setAttribute(Constants.LOGIN_SESSION_ADMIN, admins);
          request.getSession().setAttribute(Constants.ADMIN_LIMITS, limitsList);
          request.getSession().setAttribute("refundOrderCount", refundOrderCount);
          request.getSession().setAttribute("verifyMerchantCount", verifyMerchantCount);
          
          System.out.println(admins.getAdminId());
          System.out.println(admins.getAdminPhone());
          
          resultMap.put("code", "SUCCESS");
          
        } else {
         System.out.println("查无此人");
         
         model.addAttribute("LOGIN_ERROR", Constants.LOGIN_ERROR);//request级别的
         resultMap.put("code", "FAILE");
        }
    }    
    /**
     * 用账号登陆
     */
    else if (loginType.equals("账号")) {
        
        String account = request.getParameter("account");
        String password = InfoMation.encryptBasedDes(request.getParameter("password"));
        admin.setAdminAccount(account);
        admin.setAdminPassword(password);
        
        if (adminService.queryAdminForAccount(admin) != null) {

          Admin admins = adminService.accountLogin(admin);
          
          String adminMail = InfoMation.decryptBasedDes(admins.getAdminMail());
          admins.setAdminMail(adminMail);
          
          limitsList = adminService.getAdminLimitsInfo(admins.getAdminId());//获得管理员权限列表
          
          int refundOrderCount = adminService.getRefundOrderCount();//得到用户退款申请提醒
          int verifyMerchantCount = (int)manageMerService.getAllVerifyMerchantCount();//得到新注册商家提醒

          request.getSession().setAttribute(Constants.ADMIN_LIMITS, limitsList);
          request.getSession().setAttribute(Constants.LOGIN_SESSION_ADMIN, admins);
          request.getSession().setAttribute("refundOrderCount", refundOrderCount);
          request.getSession().setAttribute("verifyMerchantCount", verifyMerchantCount);
          
          System.out.println(admins.getAdminId());
          System.out.println(admins.getAdminAccount());
          
          resultMap.put("code", "SUCCESS");

        } else {
         model.addAttribute("LOGIN_ERROR", Constants.LOGIN_ERROR);
         System.out.println("查无此人");
         resultMap.put("code", "FAILE");
        }
    }
        return resultMap;
  }
  /**
   * 进入管理员主页
   * @param model
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/adminIndex")
  public String adminIndex(Model model,HttpServletRequest request)
          throws Exception{
      
      return "admin/adminIndex";
  }
  /**
   * 管理员退出，注销登录
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/loginOut")
  public String loginOut(HttpSession session)throws Exception{
      
      //清除session
      session.invalidate();
      
      return "redirect:/admin/login.do";
  }
  
  //管理员找回密码页面
  @RequestMapping("/findPassPage")
  public String findPassPage(HttpServletRequest request)throws Exception{
      
      return "admin/searchPassword1";
  }
  
  //管理员找回密码页面（第一步：获取验证）
  @RequestMapping("/findPassOne")
  @ResponseBody
  public Map<String, Object> findPassOne(HttpServletRequest request)
          throws Exception {
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      // 获得密钥
      String key = (String)request.getSession().getAttribute(Constants.ENCRYPT_CODE);
      String phoneNum = CryptogramUtil.decrypt(ValidateUtil.valiStrIsEmpty(request.getParameter("p")), key);
      phoneNum = ValidateUtil.valiStrIsPhone(phoneNum);
      phoneNum = SystemUtil.removeAllTagFromHtml(phoneNum);
      //String md5PhoneNum =  CryptogramUtil.md5(phoneNum);
      // phonenumIsExist表示该电话号码是否已存在于数据库中，1：表示已存在，0：表示还不存在
      int phonenumIsExist = adminService.getAdminPhone(phoneNum);
      // 注册时，验证数据库中存在该电话号码后，再发送验证码
      if (phonenumIsExist == 1) {
        resultMap = sendPhoneCode(request, phoneNum);
      }
      else //重置密码时，数据库中不存在电话号码，不发送验证码
      {
        resultMap.put("code", Constants.AJAX_FAIL_IDENTIFYING_CODE);
        resultMap.put("msg", "此手机号还未被管理员注册");
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
  

  
  
  //管理员找回密码页面（第二步：充值密码）
  @RequestMapping("/findPassTwo")
  public String findPassTwo(HttpServletRequest request) {
    return "admin/searchPassword2";
  }
  
  /**
   * 重置密码（找回密码）
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "doRepeatPass")
  @ResponseBody
  public Map<String,Object> doRepeatPass(HttpServletRequest request)
      throws Exception {
    
    Map<String,Object> resultMap = new HashMap<String,Object>();
    
    // 检查是否已发送验证码
    PhoneCode phoneCode = (PhoneCode) request.getSession().getAttribute(
        Constants.SEND_PHONE_CODE);
    if (phoneCode == null) {
      resultMap.put("code", Constants.AJAX_FAIL_ALERT_CODE);
      resultMap.put("msg", "未获取验证码或验证码超时，请重新获取验证码");
    }
    else //已发送验证码
    {
      //获取数据
      String password = request.getParameter("password");
      String repeatPassword = request.getParameter("repeatPassword");

      //session中的phoneCode验证合格
      if (password.equals(repeatPassword)) {
          //加密密码
          String newPassword = InfoMation.encryptBasedDes(password);    
        // 重置密码是否成功，true：成功，false：失败
        Admin admin = new Admin();
        admin.setAdminPhone(phoneCode.getPhone());
        admin.setAdminPassword(newPassword);

        // isRepeatpassword取值false（没有修改成功），true（修改成功）
        boolean isRepeatpassword = adminService.repeatPassword(admin);
       // 密码修改成功
        if (isRepeatpassword) {         
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
   * 管理员修改密码页面
   * @param request
   * @return
   */
  @RequestMapping("/modifyPassword")
  public String modifyPassword(HttpServletRequest request)throws Exception {

    return "admin/modifyPassword";
  }

  /**
   * 修改密码
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "doModifyPass")
  @ResponseBody
  public Map<String,Object> doModifyPass(HttpServletRequest request)
      throws Exception {
    
    Map<String,Object> resultMap = new HashMap<String,Object>();
    
    Admin admin = (Admin)request.getSession().getAttribute(Constants.LOGIN_SESSION_ADMIN);
    
    String  beforePass = InfoMation.encryptBasedDes(request.getParameter("beforePass"));
    String  afterPass = InfoMation.encryptBasedDes(request.getParameter("afterPass"));
    
    System.out.println("管理员id="+admin.getAdminId());
    System.out.println("beforePass="+beforePass);
    System.out.println("afterPass="+afterPass);
    
    int adminCount = adminService.isExistPassword(admin.getAdminId(),beforePass);
    
    if (adminCount != 0) {
       admin.setAdminPassword(afterPass);
       try {
           adminService.updateAdminPwd(admin);//修改密码
           request.getSession().invalidate();//清空session，重新登录
       } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("修改密码失败");
       }
       
    } else {
        resultMap.put("code", Constants.MODIFYPASSWORD_ERROR);//原密码不正确！
    }
    return resultMap;
  }
  
  /**
   * 管理员轮询新消息（审核商家，退款申请）
  */
  @RequestMapping("/adminLunxunMessage")
  @ResponseBody
  public Map<String, Object> adminLunxunMessage(HttpServletRequest request)throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    //获取需要展示在管理员主页的新消息（新商家审核，退款申请）
    int refundOrderCount = adminService.getRefundOrderCount();//得到用户退款申请提醒
    int verifyMerchantCount = (int)manageMerService.getAllVerifyMerchantCount();//得到新注册商家提醒
    //返回新消息（站内信，退单消息，抢单消息）
    resultMap.put("refundOrderCount", refundOrderCount);
    resultMap.put("verifyMerchantCount", verifyMerchantCount);

    return resultMap;
  }
  
  
  
/**
 * 站内信管理
 */
 
  
  /**
   * 编辑站内信页面
   * @param request
   * @return
   */
  @RequestMapping("/message")
  public String message(HttpServletRequest request)throws Exception {
     
    String datetime = TimeUtil.getCurrentDate();
    int month = Integer.parseInt(datetime.substring(5,7));//截取字符串中的月份并转化为Integer

    List<MessageText> messageTextList = adminService.getMessageTextList(month);//获得本月站内信
    
    request.getSession().setAttribute("messageTextList", messageTextList);
      
    return "admin/message";
  }

  /**
   * 发送站内信
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "writeMessage")
  @ResponseBody
  public Map<String,Object> writeMessage(HttpServletRequest request)
      throws Exception {
    
    Map<String,Object> resultMap = new HashMap<String,Object>();
    
    String message = request.getParameter("message");
    
    String createtime = TimeUtil.getCurrentTimeAndDate();
    System.out.println(createtime);
    MessageText msg = new MessageText();
    msg.setMessage(message);
    msg.setCreatetime(createtime);
   
    adminService.writeMessageText(msg);
    
    System.out.println("返回的新公告内容主键为："+msg.getTextId());
    
    List<Integer> merIdList = adminService.queryAllMerchantsId();//查找所有商家id，存入List
    
    Map<String, Object> messageList = new HashMap<String, Object>();//将商家idList 和 新公告内容主键存入Map，好进行MyBatis操作
  
    messageList.put("merIdList", merIdList);  
    messageList.put("textId", msg.getTextId());
  
    adminService.sendMessage(messageList);
    
    return resultMap;
  }
  
  
  
  
/**
 * 出货管理
 */
  
  
  /**
   * 进入出货单页面
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/sales")
  public String sales(Model model,HttpServletRequest request) throws Exception{
    
    List<Dish> dishList = dishService.showAllDishs();
    
    String datetime = TimeUtil.getCurrentDate();
    int month = Integer.parseInt(datetime.substring(5,7));//截取字符串中的月份并转化为Integer
    
    List<SalesList> saleList = adminService.getSalesList(month);//获得本月出货单
    
    model.addAttribute("dishList", dishList);
    model.addAttribute("saleList", saleList);
    
    return "admin/sales";
  }
  
  /**
   * 添加出货记录
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "addSales")
  @ResponseBody
  public Map<String,Object> addSales(HttpServletRequest request)
      throws Exception {
    
    Map<String,Object> resultMap = new HashMap<String,Object>();
      
    int dishId = Integer.parseInt(request.getParameter("dishId"));
    int merchantId = Integer.parseInt(request.getParameter("merId"));
    int salesNum = Integer.parseInt(request.getParameter("salesNum"));
   // Date financeTime = DateUtil.formatStringToDateDT(TimeUtil.getCurrentDate());
    Date financeTime = DateUtil.formatStringToDateDT(request.getParameter("salesTime"));
    
    System.out.println("dishId="+dishId+"merchantId="+merchantId+"salesNum="+salesNum+"\n\n"+"financeTime="+financeTime);
    
    Sales sales = new Sales();
    sales.setDishId(dishId);
    sales.setMerchantId(merchantId);
    sales.setSalesNum(salesNum);
        Dish dish = dishService.getDishInfoById(dishId);
        double costSales = salesNum * dish.getCostPrice();
        double retailSales = salesNum * dish.getRetailPrice();
        double profit = retailSales - costSales;
    sales.setCostSales(costSales);
    sales.setRetailSales(retailSales);
    sales.setProfit(profit);
    sales.setFinanceTime(financeTime);
   
    adminService.addSales(sales);//添加一条出货记录
    
    Map<String, Object> inventoryMap = new HashMap<String, Object>();
    inventoryMap.put("addNum", salesNum);
    inventoryMap.put("merchantId", merchantId);
    inventoryMap.put("dishId", dishId);
    
    inventoryService.modifyLimitCount(inventoryMap);//修改商家相应菜品库存上限
    
    resultMap.put("success", "success");
    
    return resultMap;
  }
  /**
   * 出货单搜索商家
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/searchMerList")
  @ResponseBody
  public Map<String, Object> searchMerList(HttpServletRequest request) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      List<Merchant> merchantList  = null;
      
      String keywords = request.getParameter("keywords");
      
      if (keywords == null || keywords.length() <= 0) {//如果传入的关键词为空，则直接显示所有商家
              merchantList = manageMerService.queryMerchants();
      } else {//当关键词不为空，通过关键词查找商家
          merchantList = manageMerService.queryMerchantsByKey(keywords);
      }
      
      resultMap.put("merchantList", merchantList);

      return resultMap;
  }
  
  
  
  
/**
 * 财务管理
 */
  
  /**
   * //财务管理页面
   * @param request
   * @return
   */
  @RequestMapping("/finance")
  public String finance(Model model,HttpServletRequest request)throws Exception {
    
    int refundOrderCount = adminService.getRefundOrderCount();//得到用户退款申请提醒
    model.addAttribute("refundOrderCount",refundOrderCount);
    
    return "admin/finance";
  }
  /**
   * 查询财务统计
   * @param request
   * @return
   */
  @RequestMapping("/queryFinance")
  @ResponseBody
  public Map<String, Object> query(HttpServletRequest request) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      Date dateFrom = DateUtil.formatStringToDateDT(request.getParameter("dateFrom"));
      Date dateTo = DateUtil.formatStringToDateDT(request.getParameter("dateTo"));
      
      System.out.println("dateFrom="+dateFrom +"dateTo="+dateTo);

      List<SalesTotal> salesTotalList = adminService.salesTotalList(dateFrom, dateTo);
      
      double totalMoney = 0;
      for (SalesTotal salesTotal : salesTotalList) {
          
        Dish dish = dishService.getDishInfoById(salesTotal.getDishId());
        salesTotal.setDishName(dish.getDishName());
        salesTotal.setCostSales(dish.getCostPrice());
        
        //统计总销售额
        totalMoney = totalMoney + salesTotal.getCostTotal();
      }
      
      resultMap.put("salesTotalList", salesTotalList);
      resultMap.put("totalMoney", totalMoney);
    
    return resultMap;
  }

  /**
   * 商家预付款单页面
   * @param request
   * @return
   */
  @RequestMapping("/createAdvanceList")
  public String createAdvanceList(HttpServletRequest request)throws Exception {
    
    List<CreateAdvance> createAdvanceList =  adminService.queryCreateAdvance();
    
    request.getSession().setAttribute("createAdvanceList", createAdvanceList);
    
    return "admin/createAdvanceList";
  }
  /**
   * 用户退款单页面
   * @param request
   * @return
   */
  @RequestMapping("/queryRefundOrderList")
  public String queryRefundOrderList(Model model,HttpServletRequest request)
          throws Exception {
    
    List<RefundOrder> refundOrderList =  adminService.queryRefundOrderList();
    
    int refundOrderCount = adminService.getRefundOrderCount();//得到用户退款申请提醒
    model.addAttribute("refundOrderCount",refundOrderCount);
    
    for (RefundOrder refundOrder : refundOrderList) {
        System.out.println("id="+refundOrder.getOrderId()+"userName="+refundOrder.getUserName()+"content"+refundOrder.getContent());
    }
    
    model.addAttribute("refundOrderList", refundOrderList);
    
    return "admin/userRefund";
  }
  
  /**
   * 用户退款详情
   * @param request
   * @return
   */
  @RequestMapping("/getRefundOrder")
  public String getRefundOrder(HttpServletRequest request)throws Exception {
    
    
    int orderId = Integer.parseInt(request.getParameter("orderId"));
    
    RefundOrder refundOrder = adminService.getRefundOrder(orderId);

    request.getSession().setAttribute("refundOrder", refundOrder);
    
    return "admin/refundInfo";
  }
  
  /**
   * 确认打款预付款单
   * @param request
   * @return
   */
  @RequestMapping("/payCreateAdvance")
  public String payCreateAdvance(HttpServletRequest request)
          throws Exception {
      
    int createId = Integer.parseInt(request.getParameter("id"));
    
    adminService.payCreateAdvance(createId);//更改付款状态
    
    return "forward:/admin/createAdvanceList.do";
  }
  
  
  /**
   * 确认退款给用户
   * @param request
   * @return
   */
  @RequestMapping("/payRefundOrder")
  public String payRefundOrder(HttpServletRequest request)
          throws Exception {
      
    int orderId = Integer.parseInt(request.getParameter("id"));
    
    adminService.payRefundOrder(orderId);//更改付款状态
    
    return "forward:/admin/queryRefundOrderList.do";
  }
  
  /**
   * 驳回用户退款申请
   * @param request
   * @return
   */
  @RequestMapping("/rejectRefundOrder")
  public String rejectRefundOrder(HttpServletRequest request)
          throws Exception {
      
    String id = request.getParameter("id");
    
    if (id != null) {
        int orderId = Integer.parseInt(id);
        
        adminService.rejectRefundOrder(orderId);//更改付款状态
        adminService.deleteRefundReason(orderId);//根据id删除退款申请的退款理由
        
    } else {
        throw new CustomException("订单Id的值为空！");
    }

    return "forward:/admin/queryRefundOrderList.do";
  }
  
  
  
  
  
/**
 * 预付款管理
 */

  
  /**
   * 预付款管理页面
   * @param request
   * @return
   */
  @RequestMapping("/advance")
  public String advance(HttpServletRequest request) {
    return "admin/advance";
  }
  /**
   * 预付款查询
   */
  @RequestMapping("/queryAdvance")
  @ResponseBody
  public Map<String, Object> queryAdvance(HttpServletRequest request) 
          throws Exception{
      
      Map<String, Object> resultMap = new HashMap<String, Object>();
      
      String tSlot = request.getParameter("timeSlot");
      
      System.out.println("Stirng时间段="+tSlot);
      
      int year = Integer.parseInt(tSlot.substring(0,4));//截取字符串中的年份并转化为Integer
      int month = Integer.parseInt(tSlot.substring(5,7));//截取字符串中的月份并转化为Integer
      System.out.println("截取年份时间段="+year);
      System.out.println("截取月份时间段="+month);


      List<Merchant> merchantList = manageMerService.queryMerchants();
      List<MerchantWithCount> merWithCountList =  new ArrayList<MerchantWithCount>();


      //统计每个商家指定月份的预付款数量
      for (Merchant merchant : merchantList) {
        
        int advanceCount = adminService.getAdvanceCountByMerchantId(merchant.getMerchantId(),year,month);

        MerchantWithCount merWithCount = new MerchantWithCount();
        merWithCount.setMerchant(merchant);
        merWithCount.setAdvanceCount(advanceCount);
       
        merWithCountList.add(merWithCount);  
      }
      
      //给merWithCountList做一次排序，预付款数量多的在前
      MerchantWithCount tempMerchant = new MerchantWithCount();
      for (int i = 0; i < merWithCountList.size()-1; i++) {
        for (int j = 1; j < merWithCountList.size()-i; j++) {
            if (merWithCountList.get(j-1).getAdvanceCount() < merWithCountList.get(j).getAdvanceCount()) {
                tempMerchant = merWithCountList.get(j-1);
                merWithCountList.set(j-1, merWithCountList.get(j));
                merWithCountList.set(j, tempMerchant);
            }
        }
        
    }
      
      resultMap.put("merWithCountList", merWithCountList);

    
    return resultMap;
  }


    /**
     *查询商家预付款详情
     */
    @RequestMapping(value = "queryAdvanceDetail")
    public String queryAdvanceDetail(HttpServletRequest request)
        throws Exception {
        
      //每次进入预付款详情页面时，判断支付时间跟当前时间差距大于24小时的预付款，自动设置为“已支付”
      Date nowTime = DateUtil.formatStringToDateDTTM(TimeUtil.getCurrentTimeAndDate());

      System.out.println("当前时间:="+nowTime.getTime());
      
      int merchantId = Integer.parseInt(request.getParameter("id")); 
      String tSlot = request.getParameter("month");
    
      int year = Integer.parseInt(tSlot.substring(0,4));//截取字符串中的年份并转化为Integer
      int month = Integer.parseInt(tSlot.substring(5,7));//截取字符串中的月份并转化为Integer
      System.out.println(merchantId+"........."+month);
      
      Merchant merchant = manageMerService.getMerchantById(merchantId);
      request.getSession().setAttribute("merchant", merchant);
      request.getSession().setAttribute("tSlot", tSlot);
      request.getSession().setAttribute("year", year);
      request.getSession().setAttribute("month", month);
      
      List<AdvanceDetail> advanceList = adminService.getShouldDoAdvance(merchantId,year,month);
      
     List<Integer> doUserStatusOrderIdList = new ArrayList<Integer>();
      for (AdvanceDetail advanceDetail : advanceList) {//循环找出支付时间与当前时间相差1天的订单id
          
          long time = Math.abs(nowTime.getTime() - DateUtil.formatStringToDateDTTM(advanceDetail.getAdvanceTime()).getTime());
          int day = (int) (time / (24 * 3600 * 1000));
          if (day > 0) {//预付款记录支付时间超过一天（与当前时间对比）
              System.out.println("符合自动确认收货条件的orderId"+advanceDetail.getOrderId());
              doUserStatusOrderIdList.add(advanceDetail.getOrderId());
        }
        System.out.println("相差天数"+day);
    }
      //当符合自动确认条件时
      if (doUserStatusOrderIdList.size() > 0) {
          System.out.println("doUserStatusOrderIdList.size()="+doUserStatusOrderIdList.size()+"你好世界");
        //自动更改订单“确认收货”  
          adminService.doUserStatusOrder(doUserStatusOrderIdList);//更改状态
      }
      
      List<AdvanceDetail> advanceDetailList = adminService.queryAdvance(merchantId,year,month);
      
      request.getSession().setAttribute("advanceDetailList", advanceDetailList);
    
      
      return "admin/doAdvance";
      
    }


    /**
     *生成预付款统计单
     */
    @RequestMapping(value = "createAdvance")
    public String createAdvance(HttpServletRequest request)
        throws Exception {
      
      int merchantId = Integer.parseInt(request.getParameter("id")); 
      int year = Integer.parseInt(request.getParameter("year")); 
      int month = Integer.parseInt(request.getParameter("month")); 
      System.out.println(merchantId);
      
      Merchant merchant = manageMerService.getMerchantById(merchantId);
      request.getSession().setAttribute("merchant", merchant);
      
      CreateAdvance createAdvance = adminService.createAdvanceByMerchantId(merchantId,year,month);
      
      createAdvance.setTimeSlot(year+"年"+month+"月");
      String createTime = TimeUtil.getCurrentTimeAndDate();
      createAdvance.setCreateTime(createTime);
    
    
      request.getSession().setAttribute("createAdvance", createAdvance);
    
      return "admin/createAdvance";
      
    }
    
    
    /**
     *添加预付款统计单(插入数据库)
     */
    @RequestMapping(value = "addCreateAdvance")
    @ResponseBody
    public Map<String, Object> addCreateAdvance(HttpServletRequest request)
        throws Exception {
    
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        int merchantId = Integer.parseInt(request.getParameter("merchantId")); 
        String shopName = request.getParameter("shopName");
        double totalMoney = Double.parseDouble(request.getParameter("totalMoney")); 
        String timeSlot = request.getParameter("timeSlot"); 
        int year = (int)request.getSession().getAttribute("year");
        int month = (int)request.getSession().getAttribute("month");
        String createTime = TimeUtil.getCurrentTimeAndDate();
      
        System.out.println(merchantId);
        System.out.println(year+"年"+month+"月");
        
        //totalMoney=0说明没有新的"可生成预付款单"
        if (totalMoney != 0) {
            CreateAdvance createAdvance = new CreateAdvance();
            
            createAdvance.setMerchantId(merchantId);
            createAdvance.setShopName(shopName);
            createAdvance.setTotalMoney(totalMoney);
            createAdvance.setTimeSlot(timeSlot);
            createAdvance.setCreateTime(createTime);
            
            adminService.addCreateAdvance(createAdvance);//添加付款单到数据库
            
            //更改生成预付款单后的预付款记录状态
            List<Integer> advanceOrderIdList = adminService.getAdvanceOrderIdList(year,month);//查出符合条件orderId
            
            for (Integer integer : advanceOrderIdList) {
                System.out.println("符合条件的OId"+integer);
            }
            if (advanceOrderIdList.size() > 0) {     
                adminService.updateAdvanceStatus(advanceOrderIdList);//更改状态
                
                resultMap.put("code", "SUCCESS");
            } else {
                resultMap.put("code", "FAILE");
            }
        } else {  
            resultMap.put("code", "FAILE");
        }
        return resultMap;
      
    }






/**
 * 权限管理
 */

    /**
     * 获得管理员列表
     * @param request
     * @return
     */
    @RequestMapping("/limitPage")
    public String limitPage(Model model,HttpServletRequest request,HttpServletResponse response)
            throws Exception {
        System.out.println("................................................................");
        
        String pageNow = request.getParameter("pageNow");
        Page page = null;
        List<Admin> adminList = null;
        int totalCount = (int) adminService.getAllAdminCount();
        if (pageNow != null) {
          page = new Page(totalCount, Integer.parseInt(pageNow));
          adminList = adminService.getAdminList(page);
          
        } else {
          
          page = new Page(totalCount, 1);
          adminList = adminService.getAdminList(page);
          
          if (adminList == null || adminList.size() < 1) {
            //跳转错误提示页面
              request.setAttribute("errorMsg", "对不起，暂时没有管理员");
              request.getRequestDispatcher("/WEB-INF/pages/testError.jsp").forward(request, response);
          }
        }
        model.addAttribute("adminList", adminList);
        model.addAttribute("page", page);

      return "admin/authorization";
    }
    
    /**
     * 获取单个管理员信息
     * @param request
     * @return
     */
    @RequestMapping("/getAdminById")
    public String getAdminById(HttpServletRequest request,HttpServletResponse response) 
            throws Exception{

      String id = request.getParameter("id");
      
      if (id != null) {
          int adminId = Integer.parseInt(id);
          Admin admin = adminService.getAdminById(adminId);
          
        //将重要数据解密
          System.out.println("密码加密后："+admin.getAdminPassword());
          System.out.println("邮箱加密后："+admin.getAdminMail());

          try {
              String password = InfoMation.decryptBasedDes(admin.getAdminPassword());
              String mail = InfoMation.decryptBasedDes(admin.getAdminMail());
              
              System.out.println("身份证号解密后："+password);
              System.out.println("邮箱解密后："+mail);
              
              admin.setAdminPassword(password);
              admin.setAdminMail(mail);
              
              request.getSession().setAttribute("admin", admin);
              
              //储存管理员原先的手机号和账户，在修改时候做判断
              String adminAccount = admin.getAdminAccount();//账号
              String adminPhone = admin.getAdminPhone();//手机号      

              System.out.println(adminAccount);
              System.out.println(adminPhone);
              
              request.getSession().setAttribute("beforeAccount", adminAccount);
              request.getSession().setAttribute("beforePhone", adminPhone);

              
          } catch (Exception e) {
              e.printStackTrace();
              throw new CustomException("管理员信息解密失败！");
          }

      } else {
          throw new CustomException("管理员Id值为空！");
      }
      
      return "admin/singleAuth";
    }
    
    /**
     * 新增管理员界面
     * @param request
     * @return
     */
    @RequestMapping("/addAdmin")
    public String addAdmin(HttpServletRequest request) {

      return "admin/newAdmin";
    }
    
    /**
     * 创建管理员
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/doAddAdmin")
    @ResponseBody
    public Map<String,Object> doAddAdmin(HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        
        Map<String,Object> resultMap = new HashMap<String, Object>();
        
        Admin admin = new Admin();
        
       
       String adminAccount = request.getParameter("adminAccount");//账号
       String adminPassword = InfoMation.encryptBasedDes(request.getParameter("adminPassword"));//密码
       String adminName = request.getParameter("adminName");//姓名
       String adminPhone = request.getParameter("adminPhone");//手机号
       boolean isPhone = ValidateUtil.strIsPhone(adminPhone);
       String adminMail = InfoMation.encryptBasedDes(request.getParameter("adminMail")); //邮箱
       //System.out.println(".............................................email:..."+adminMail);
       String createTime = TimeUtil.getCurrentTimeAndDate(); //创建时间
  
       String limitSetList = request.getParameter("limitSetList");//权限id数组
       
       JSONArray array = new JSONArray(limitSetList);//储存为Json数组
       List<Integer> limitIdList = new ArrayList<Integer>();
       for (int i = 0; i < array.length(); i++) {
           limitIdList.add(Integer.parseInt(array.get(i).toString()));
       }
       System.out.println(adminAccount);
       System.out.println(adminPassword);
       System.out.println(adminName);
       System.out.println(adminPhone);
       System.out.println(adminMail);
       System.out.println(createTime);
       
       System.out.println(limitSetList);
       
       admin.setAdminAccount(adminAccount);
       admin.setAdminPassword(adminPassword);
       admin.setAdminName(adminName);
       admin.setAdminPhone(adminPhone);
       admin.setAdminMail(adminMail);
       admin.setCreateTime(createTime);
       
       if (isPhone) {//如果电话号码格式正确
            if (adminService.isExistAccount(admin) == 0) {//判断管理员账户是否已存在
                if (adminService.isExistPhone(admin) == 0) {//判断管理员手机号是否已存在
                    
                    adminService.addAdmin(admin);//创建管理员
                    System.out.println("新的管理员的ID= "+admin.getAdminId()+"...........");
                    
                    //权限列表（职位）
                    Map<String,Object> limitsListMap = new HashMap<String, Object>();

                    limitsListMap.put("limitIdList", limitIdList);  
                    limitsListMap.put("adminId", admin.getAdminId());
                  
                    adminService.registerLimits(limitsListMap);//注册权限
                    
                    resultMap.put("code", ResultCode.AJAX_CREATESUCCESS_CODE); 
             } else {
                 resultMap.put("code", ResultCode.AJAX_ISEXISTPHONE_CODE);
             }
           } else {
               resultMap.put("code", ResultCode.AJAX_ISEXISTACCOUNT_CODE); 
           }
    } else {
        resultMap.put("code", "手机号格式不正确");
    }

       return resultMap;
    }
    
    /**
     * 修改管理员信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public Map<String,Object> updateAdmin(HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        
        String beforeAccount = (String)request.getSession().getAttribute("beforeAccount");
        String beforePhone = (String)request.getSession().getAttribute("beforePhone");
        
        System.out.println("beforeAccount="+beforeAccount);
        System.out.println("beforePhone="+beforePhone);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        
        Admin admin = new Admin();
        
       int adminId = Integer.parseInt(request.getParameter("adminId"));
       System.out.println(adminId);
       
       String adminAccount = request.getParameter("adminAccount");//账号
       String adminPassword = InfoMation.encryptBasedDes(request.getParameter("adminPassword"));//密码  
       String adminName = request.getParameter("adminName");//姓名        
       String adminPhone = request.getParameter("adminPhone");//手机号      
       boolean isPhone = ValidateUtil.strIsPhone(adminPhone);
       String adminMail = InfoMation.encryptBasedDes(request.getParameter("adminMail")); //邮箱      
       
       String limitSetList = request.getParameter("limitSetList");//权限id数组
       
       JSONArray array = new JSONArray(limitSetList);//储存为Json数组
       List<Integer> limitIdList = new ArrayList<Integer>();
       for (int i = 0; i < array.length(); i++) {
           limitIdList.add(Integer.parseInt(array.get(i).toString()));
       }

       //判断编辑后的管理员账号和手机是否和先前一致
       boolean isSameBeforePhone =  (adminPhone.equals(beforePhone)) ;//如果手机号和原先一致
       boolean isSameBeforeAccount =  (adminAccount.equals(beforeAccount)) ;//如果账号和原先一致
       
       System.out.println("isSameBeforePhone="+isSameBeforePhone);
       System.out.println("isSameBeforeAccount="+isSameBeforeAccount);

       System.out.println(adminAccount);
       System.out.println(adminPassword);
       System.out.println(adminName);
       System.out.println(adminPhone);
       System.out.println(adminMail);
       System.out.println(limitSetList);
       
       admin.setAdminId(adminId);
       admin.setAdminAccount(adminAccount);
       admin.setAdminPassword(adminPassword);
       admin.setAdminName(adminName);
       admin.setAdminPhone(adminPhone);
       admin.setAdminMail(adminMail);
       
       if (isPhone) {//如果电话号码格式正确

           if (adminService.isExistAccount(admin) == 0 || isSameBeforeAccount) {//判断管理员账户是否已存在
               if (adminService.isExistPhone(admin) == 0 || isSameBeforePhone) {//判断管理员手机号是否已存在
                   
                   adminService.deleteLimitUser(adminId);//删除之前的权限
                   adminService.updateAdmin(admin);//修改管理员
                   
                   System.out.println("修改管理员的ID= "+admin.getAdminId()+"...........");
                   
                   //权限列表（职位）
                   Map<String,Object> limitsListMap = new HashMap<String, Object>();

                   limitsListMap.put("limitIdList", limitIdList);  
                   limitsListMap.put("adminId", admin.getAdminId());
                 
                   adminService.registerLimits(limitsListMap);//注册新的管理员权限
                   
                   resultMap.put("code", "修改管理员成功！"); 
               }else {
                   resultMap.put("code", ResultCode.AJAX_ISEXISTPHONE_CODE);
            }
           }else {
               resultMap.put("code", ResultCode.AJAX_ISEXISTACCOUNT_CODE); 
        }
        }else {
            resultMap.put("code", "手机号格式不正确");    
        }
       return resultMap;
    }
    
    
    /**
     * 删除管理员
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteAdmin")
    @ResponseBody
    public Map<String,Object> deleteAdmin(HttpServletRequest request,HttpServletResponse response)
            throws Exception{
        
       Map<String,Object> resultMap = new HashMap<String, Object>();

       int adminId = Integer.parseInt(request.getParameter("adminId"));
        
       adminService.deleteAdmin(adminId);
       adminService.deleteLimitUser(adminId);
       
       return resultMap;
    }    
}
