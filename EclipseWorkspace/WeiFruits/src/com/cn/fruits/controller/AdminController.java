 package com.cn.fruits.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.fruits.bean.AdminBean;
import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.service.AdminService;
import com.cn.fruits.util.Constants;
import com.cn.fruits.util.ValidateUtil;


@Controller
@RequestMapping("/admin")
public class AdminController {
  private static final Logger logger = Logger.getLogger(AdminController.class);
  
  @Inject
  public AdminService adminService;
  
  /**
   * 跳转到登录页面
   */
  @RequestMapping("/toLogin")
  public String toLogin() throws Exception {
    return Constants.PAGE_ADMIN_LOGIN;
  }
  
  /**
   * 处理登录
   * */
  @RequestMapping("/doLogin")
  public String doLogin(HttpServletRequest request, HttpServletResponse response)throws Exception{
    String returnValue = "";
    String userName = request.getParameter("username");
    String passWord =  request.getParameter("password");
    AdminBean admin = new AdminBean();
    admin.setAdminAccount(userName);
    admin.setAdminPassword(passWord);
    AdminBean loginAdmin = new AdminBean();
    loginAdmin = adminService.doLoginService(admin);
//    System.out.println(loginAdmin.getAdminId()+"hehe:"+loginAdmin.getAdminName());
    //登录成功
    if(loginAdmin != null  && loginAdmin.getAdminId() != 0){
      request.getSession().setAttribute("loginAdmin", loginAdmin);
      returnValue = "redirect:/admin/toAdminHome";
    }
    //登录失败
    else{
      request.setAttribute("userName", userName);
      request.setAttribute("passWord", passWord);
      returnValue = Constants.PAGE_ADMIN_LOGIN;
    }
    return returnValue;
  }
  
  /**
   * 跳转到管理员主页
   * */
  @RequestMapping("/toAdminHome")
  public String toAdminHome(HttpServletRequest request) throws Exception {
    String returnValue = "";
    AdminBean loginAdmin = new AdminBean();
    loginAdmin = (AdminBean) request.getSession().getAttribute("loginAdmin");
    if(loginAdmin != null){
      returnValue = Constants.PAGE_ADMIN_HOME;
    }
    else{
      returnValue = Constants.PAGE_ADMIN_LOGIN;
    }
    return returnValue;
  }
  
  /**
   * 跳转到商品（水果）管理主面
   * */
  @RequestMapping("/toGoodsManage")
  public String toGoods(HttpServletRequest request) throws Exception {
    return Constants.PAGE_ADMIN_GOODS;
  }
  
  /**
   * 跳转到上架商品
   */
  @RequestMapping("/toAddGoods")
  public String toAddGoods(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_GOODS_ADD;
  }
  
  /**
   * 跳转到下架商品
   */
  @RequestMapping("/toUnderGoods")
  public String toUnderGoods(HttpServletRequest request) throws Exception{
    String pageNum = request.getParameter("pageNum");
    List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
    if(ValidateUtil.strIsEmpty(pageNum)){
      fruitBeans = adminService.getFruitsByPageNumService(1);
      pageNum = "1";
    }
    else{
      fruitBeans = adminService.getFruitsByPageNumService(
          Integer.parseInt(pageNum));
    }
    request.setAttribute("pageNum", pageNum);
    request.setAttribute("fruitBeans", fruitBeans);
    return Constants.PAGE_ADMIN_GOODS_REDUCE;
  }
  
  /**
   * 跳转到现有商品
   */
  @RequestMapping("/toNowGoods")
  public String toNowGoods(HttpServletRequest request) throws Exception{
    String pageNum = request.getParameter("pageNum");
    List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
    if(ValidateUtil.strIsEmpty(pageNum)){
      fruitBeans = adminService.getFruitsByPageNumService(1);
      pageNum = "1";
    }
    else{
      fruitBeans = adminService.getFruitsByPageNumService(
          Integer.parseInt(pageNum));
    }
    request.setAttribute("pageNum", pageNum);
    request.setAttribute("fruitBeans", fruitBeans);
    return Constants.PAGE_ADMIN_GOODS_ALL;
  }
  
  /**
   * 跳转到未完成订单
   */
  @RequestMapping("/toOrderUn")
  public String toOrderUn(HttpServletRequest request) throws Exception{
    String pageNum = request.getParameter("pageNum");
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    if(ValidateUtil.strIsEmpty(pageNum)){
      ordersBeans = adminService.selectUnFinishedOrdersByPageNumService(1);
      pageNum = "1";
    }
    else{
      ordersBeans = adminService.selectUnFinishedOrdersByPageNumService(
          Integer.parseInt(pageNum));
    }
    request.setAttribute("ordersBeans", ordersBeans);
    request.setAttribute("pageNum", pageNum);
    return Constants.PAGE_ADMIN_ORDER_UNFINISHED;
  }
  
  /**
   * 跳转到未完成订单详情
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/toOrderUnDetails")
  public String toOrderUnDetails(HttpServletRequest request) throws Exception{
    String orderId = request.getParameter("orderId");
    if(!ValidateUtil.strIsEmpty(orderId)){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = adminService.getOrderInfoByOrderIdService(Integer.parseInt(orderId));
      request.setAttribute("ordersBean", ordersBean);
    }
    return Constants.PAGE_ADMIN_ORDER_UNFINISHED_DETAIL;
  }
  
  /**
   * 跳转到已完成订单
   */
  @RequestMapping("/toOrderEd")
  public String toOrderEd(HttpServletRequest request) throws Exception{
    String pageNum = request.getParameter("pageNum");
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    if(ValidateUtil.strIsEmpty(pageNum)){
      ordersBeans = adminService.selectFinishedOrdersByPageNumService(1);
      pageNum = "1";
    }
    else{
      ordersBeans = adminService.selectFinishedOrdersByPageNumService(
          Integer.parseInt(pageNum));
    }
    request.setAttribute("ordersBeans", ordersBeans);
    request.setAttribute("pageNum", pageNum);
    return Constants.PAGE_AMDIN_ORDER_FINISHED;
  }
  
  /**
   * 跳转到已完成订单详情
   * @param request
   * @return
   * @throws Exception
   */
  @RequestMapping("/toOrderEdDetails")
  public String toOrderEdDetails(HttpServletRequest request) throws Exception{
    String orderId = request.getParameter("orderId");
    if(!ValidateUtil.strIsEmpty(orderId)){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = adminService.getOrderInfoByOrderIdService(Integer.parseInt(orderId));
      request.setAttribute("ordersBean", ordersBean);
    }
    return Constants.PAGE_ADMIN_ORDER_FINISHED_DETAIL;
  }
  /**
   * 跳转到订单管理
   * */
  @RequestMapping("/toOrderManage")
  public String toOrder(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_ORDER;
  }
  
  /**
   * 跳转到校区管理
   */
  @RequestMapping("/toAreaManage")
  public String toArea(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_AREA;
  }
  
  /**
   * 跳转到添加大学
   */
  @RequestMapping("/toAddCollege")
  public String toAddCollege(HttpServletRequest request) throws Exception{
    List<CampusBean> campusBeans = new ArrayList<CampusBean>();
    campusBeans = adminService.getAllCampusesService();
    request.setAttribute("campusBeans", campusBeans);
    return Constants.PAGE_ADMIN_AREA_ADDCOLLEGE;
  }
  
  /**
   * 跳转到添加宿舍楼
   */
  @RequestMapping("/toAddBuilding")
  public String toAddBuilding(HttpServletRequest request) throws Exception{
    List<CampusBean> campusBeans = new ArrayList<CampusBean>();
    campusBeans = adminService.getAllCampusesService();
    request.setAttribute("campusBeans", campusBeans);
    return Constants.PAGE_ADMIN_AREA_ADDBUILDING;
  }
  
  /**
   * 跳转到添加大学里的宿舍
   */
  @RequestMapping("/toAddCollegeBuilding")
  public String toAddCollegeBuilding(HttpServletRequest request) throws Exception{
    List<DormitoryBean> dormitoryBeans = new ArrayList<DormitoryBean>();
    dormitoryBeans = adminService.getAllDormitoryService();
    request.setAttribute("dormitoryBeans", dormitoryBeans);
    return Constants.PAGE_ADMIN_AREA_ADDCOLLEGEBUILDING;
  }
  
  /**
   * 跳转到新建公告栏
   * */
  @RequestMapping("/toNewnotes")
  public String toNewnotes(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_NOTICE_ADD;
  }
  
  /**
   * 保存新的公告
   * */
  @RequestMapping("/doSaveNewNotes")
  public String doSaveNewNotes(HttpServletRequest request) throws Exception{
    String noticeContent = request.getParameter("noticeContent");
    String result = "数据丢失";
    if(!ValidateUtil.strIsEmpty(noticeContent)){
      NoticeBean noticeBean = new NoticeBean();
      noticeBean.setNoticeContent(noticeContent);
      boolean bool = adminService.saveNoticeService(noticeBean);
      if(bool){
        result = "保存成功！点击‘历史公告栏’查看";
      }
      else{
        result = "保存失败！！！";
      }
    }
    request.setAttribute(Constants.RESULT, result);
    return Constants.PAGE_RESULT_ADMIN;
  }
  
  /**
   * 跳转到历史公告栏
   * */
  @RequestMapping("/toOldnotes")
  public String toOldnotes(HttpServletRequest request) throws Exception{
    List<NoticeBean> noticeBeans = new ArrayList<NoticeBean>();
    noticeBeans = adminService.getALlNoticesService();
    request.setAttribute("noticeBeans", noticeBeans);
    return Constants.PAGE_ADMIN_NOTICE_OLD;
  }
  
  /**
   * 删除公告
   * */
  @RequestMapping("/doDeleteNotice")
  @ResponseBody
  public Map<String, Object> doDeleteNotice(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String noticeId = request.getParameter("noticeId");
    if(!ValidateUtil.strIsEmpty(noticeId)){
      boolean bool = adminService.deleteNoticeByNoticeIdService(Integer.parseInt(noticeId));
      if(bool){
        returnMap.put("noticeId", noticeId);
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  /**
   * 跳转到账户管理
   * */
  @RequestMapping("/toAccountManage")
  public String toAccountManage(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_ACCOUNT ;
  }
  
  /**
   * 跳转到修改管理员账户
   */
  @RequestMapping("/toAccountEdit")
  public String toAccountEdit(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_EDIT_ACCOUNT;
  }
  
  /**
   * 跳转到添加管理员账户
   */
  @RequestMapping("/toAccountAdd")
  public String toAccountAdd(HttpServletRequest request) throws Exception{
    return Constants.PAGE_ADMIN_ADD_ACCOUNT;
  }
  
  /**
   * 跳转到现有商品
   */
  @RequestMapping("/toOldGoods")
  public String toOldGoods(HttpServletRequest request)throws Exception{
    String fruitId = request.getParameter("fruitId");
    FruitBean fruitBean = new FruitBean();
    fruitBean = adminService.getFruitByFruitIdService(Integer.parseInt(fruitId));
    if(fruitBean == null){
      request.setAttribute(Constants.RESULT, Constants.DATA_NULL);
      return Constants.PAGE_RESULT_ADMIN;
    }
    request.setAttribute("fruitBean", fruitBean);
    return Constants.PAGE_ADMIN_GOODS_ALL_DETAIL;
  }
  
  /**
   * 上架新的商品
   * */
  @RequestMapping("/addNewGood")
  public String addNewGood(HttpServletRequest request) throws Exception{
    String fruitName = request.getParameter("fruitName");
    String price = request.getParameter("price");
    String fruitIntro = request.getParameter("fruitIntro");
    String fruitDetail = request.getParameter("fruitDetail");
    String fruitPic = request.getParameter("hiddeUrl");
//    System.out.println("address:"+fruitPic);
    String result = "传值失败！！！";
    if(!ValidateUtil.strIsEmpty(fruitName) && !ValidateUtil.strIsEmpty(price)
        && !ValidateUtil.strIsEmpty(fruitIntro) && !ValidateUtil.strIsEmpty(fruitDetail)
        && !ValidateUtil.strIsEmpty(fruitPic)){
      FruitBean fruitBean = new FruitBean();
      fruitBean.setFruitName(fruitName);
      fruitBean.setFruitPrice(Double.valueOf(price));
      fruitBean.setFruitIntr(fruitIntro);
      fruitBean.setFruitDetail(fruitDetail);
      fruitBean.setFruitPic(fruitPic);
      boolean bool = adminService.saveFruitService(fruitBean);
      if(bool){
        result = "添加成功！请到‘现有商品’标签栏查看";
      }
      else{
        result = "添加失败，请重试！！！";
      }
    }
    request.setAttribute(Constants.RESULT, result);
    return Constants.PAGE_RESULT_ADMIN;
  }
  
  /**
   * 添加校区
   * */
  @RequestMapping("/addNewCampus")
  @ResponseBody
  public Map<String, Object> addNewCampus(HttpServletRequest request) throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String campusName = request.getParameter("campusName");
    System.out.println(campusName);
    if(!ValidateUtil.strIsEmpty(campusName)){
      boolean bool = adminService.saveNewCampusService(campusName);
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 删除校区
   * */
  @RequestMapping("/deleteCampus")
  @ResponseBody
  public Map<String, Object> deleteCampus(HttpServletRequest request) throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String campusId = request.getParameter("campusId");
    if(!ValidateUtil.strIsEmpty(campusId)){
      boolean bool = adminService.deleteCampusByCampusIdService(
          Integer.parseInt(campusId));
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 修改校区名称
   * */
  @RequestMapping("/updateCampusName")
  @ResponseBody
  public Map<String, Object> updateCampusName(HttpServletRequest request) throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String campusId = request.getParameter("campusId");
    String campusName = request.getParameter("campusName");
    if(!ValidateUtil.strIsEmpty(campusId) && !ValidateUtil.strIsEmpty(campusName)){
      CampusBean campusBean = new CampusBean();
      campusBean.setCampusId(Integer.parseInt(campusId));
      campusBean.setCampusName(campusName);
      boolean bool = adminService.updateCampusNameService(campusBean);
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 增加宿舍楼
   * */
  @RequestMapping("/addNewDormitory")
  public String addNewDormitory(HttpServletRequest request)throws Exception{
    String campusId = request.getParameter("campusId");
    String dormitoryName = request.getParameter("dormitoryName");
    String result = "数据丢失";
    if(!ValidateUtil.strIsEmpty(campusId) && !ValidateUtil.strIsEmpty(dormitoryName)){
      DormitoryBean dormitoryBean = new DormitoryBean();
      dormitoryBean.setCampusId(Integer.parseInt(campusId));
      dormitoryBean.setDormitoryName(dormitoryName);
      boolean bool = adminService.addNewDormitoryService(dormitoryBean);
      if(bool){
        result="保存成功！！！";
      }
      else{
        result="保存失败！！！";
      }
    }
    request.setAttribute(Constants.RESULT, result);
    return Constants.PAGE_RESULT_ADMIN;
  }
  
  /**
   * 删除宿舍楼
   * */
  @RequestMapping("/deleteDormitory")
  @ResponseBody
  public Map<String, Object> deleteDormitory(HttpServletRequest request) throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String dormitoryId = request.getParameter("dormitoryId");
    if(!ValidateUtil.strIsEmpty(dormitoryId)){
      boolean bool = adminService.deleteDormitoryByDormitoryIdService(
          Integer.parseInt(dormitoryId));
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 修改宿舍名称
   * */
  @RequestMapping("/updateDormitoryName")
  @ResponseBody
  public Map<String, Object> updateDormitoryName(HttpServletRequest request) throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String dormitoryId = request.getParameter("dormitoryId");
    String dormitoryName = request.getParameter("dormitoryName");
    if(!ValidateUtil.strIsEmpty(dormitoryId) && !ValidateUtil.strIsEmpty(dormitoryName)){
      DormitoryBean dormitoryBean = new DormitoryBean();
      dormitoryBean.setDormitoryId(Integer.parseInt(dormitoryId));
      dormitoryBean.setDormitoryName(dormitoryName);
      boolean bool = adminService.updateDormitoryNameService(dormitoryBean);
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 下架商品
   * */
  @RequestMapping("/deleteFruit")
  @ResponseBody
  public Map<String, Object> deleteFruit(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String fruitId = request.getParameter("fruitId");
    if(!ValidateUtil.strIsEmpty(fruitId)){
      boolean bool = adminService.deleteFruitByFruitIdService(
          Integer.parseInt(fruitId));
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 修改Fruit信息
   * */
  @RequestMapping("/updateFruitInfo")
  public String updateFruitInfo(HttpServletRequest request)throws Exception{
    String returnValue = Constants.PAGE_RESULT_ADMIN;
    String fruitId = request.getParameter("fruitId");
    String fruitName = request.getParameter("fruitName");
    String fruitPic = request.getParameter("hiddeUrl");
    String fruitIntro = request.getParameter("fruitIntro");
    String fruitPrice = request.getParameter("fruitPrice");
    String fruitDetail = request.getParameter("fruitDetail");
    if(!ValidateUtil.strIsEmpty(fruitId) && !ValidateUtil.strIsEmpty(fruitName)
        && !ValidateUtil.strIsEmpty(fruitPic) && !ValidateUtil.strIsEmpty(fruitIntro)
        && !ValidateUtil.strIsEmpty(fruitPrice) && !ValidateUtil.strIsEmpty(fruitDetail)){
      FruitBean fruitBean = new FruitBean();
      fruitBean.setFruitId(Integer.parseInt(fruitId));
      fruitBean.setFruitName(fruitName);
      fruitBean.setFruitPic(fruitPic);
      fruitBean.setFruitIntr(fruitIntro);
      fruitBean.setFruitPrice(Double.valueOf(fruitPrice));
      fruitBean.setFruitDetail(fruitDetail);
      boolean bool = adminService.updateFruitInfoByFruitIdService(fruitBean);
      if(bool){
        returnValue = "redirect:/admin/toNowGoods";//重新跳转到现有商品页面
      }
      else{
        request.setAttribute(Constants.RESULT, Constants.OPERATE_FAIL);
      }
    }
    else{
      request.setAttribute(Constants.RESULT, Constants.DATA_NULL);
    }
    return returnValue;
  }
  
  /**
   * 删除订单
   * */
  @RequestMapping("/doDeleteOrder")
  @ResponseBody
  public Map<String, Object> doDeleteOrder(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String orderId = request.getParameter("orderId");
    if(!ValidateUtil.strIsEmpty(orderId)){
      boolean bool = adminService.deleteOrderByOrderIdService(
          Integer.parseInt(orderId));
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  /**
   * 接受订单
   * */
  @RequestMapping("/doAcceptOrder")
  @ResponseBody
  public Map<String, Object> doAcceptOrder(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String orderId = request.getParameter("orderId");
    if(!ValidateUtil.strIsEmpty(orderId)){
      boolean bool = adminService.updateOrderStatusToAcceptByOrderIdService(
          Integer.parseInt(orderId));
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  
  /**
   * 完成订单
   * */
  @RequestMapping("/doFinishOrder")
  @ResponseBody
  public Map<String, Object> doFinishOrder(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String orderId = request.getParameter("orderId");
    if(!ValidateUtil.strIsEmpty(orderId)){
      boolean bool = adminService.updateOrderStatusToFinishByOrderIdService(
          Integer.parseInt(orderId));
      if(bool){
        returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
      }
      else{
        returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
      }
    }
    else{
      returnMap.put(Constants.RESULT, Constants.AJAX_FAIL_CODE);
    }
    return returnMap;
  }
  /**
   * 检查新的订单是否存在
   * */
  @RequestMapping("/checkNewOrderIsExist")
  @ResponseBody
  public Map<String, Object> checkNewOrderIsExist(HttpServletRequest request)
      throws Exception{
    System.out.println("aaaa");
    Map<String, Object> returnMap = new HashMap<String, Object>();
    int count = adminService.getUnAcceptOrdersCountService();
    returnMap.put("count", count);
    returnMap.put(Constants.RESULT, Constants.AJAX_SUCCESS_CODE);
    return returnMap;
  }
  
  /***
   * 管理员修改密码
   * */
  @RequestMapping("/changePassWord")
  public String changePassWord(HttpServletRequest request)throws Exception{
    String returnValue = Constants.PAGE_RESULT_ADMIN;
    String adminName = request.getParameter("adminName");
    String oldPass = request.getParameter("old_pass");
    String newPassWord = request.getParameter("new_pass");
    if(!ValidateUtil.strIsEmpty(adminName) && !ValidateUtil.strIsEmpty(oldPass)
        && !ValidateUtil.strIsEmpty(newPassWord)){
      AdminBean adminBean = new AdminBean();
      adminBean.setAdminAccount(adminName);
      adminBean.setAdminPassword(oldPass);
      AdminBean loginAdmin = adminService.doLoginService(adminBean);
      if(loginAdmin != null && loginAdmin.getAdminId() != 0){
        Boolean bool = adminService.changeAdminPassWordByAdminIdService(
            loginAdmin.getAdminId(),newPassWord);
        if(bool){
          request.setAttribute(Constants.RESULT, "保存成功");
        }
        else{
          request.setAttribute(Constants.RESULT, "保存失败");
        }
      }
      else{
        returnValue = Constants.PAGE_ADMIN_EDIT_ACCOUNT;
        request.setAttribute(Constants.RESULT, "账户或密码错误");
      }
    }
    else{
      request.setAttribute(Constants.RESULT, "页面上有空值");
    }
    return returnValue;
  }
  
  
}
