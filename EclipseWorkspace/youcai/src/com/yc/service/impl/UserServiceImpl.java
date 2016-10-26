package com.yc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.yc.dao.UserDAO;
import com.yc.service.UserService;
import com.yc.util.ResultCode;
import com.yc.util.StringUtil;
import com.yc.util.TimeUtil;
import com.yc.util.UserCommonTools;
/**
 * 用户service 实现类
 * @author 
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDao;

  /**
   * 用户登录
   */
  @Override
  public User userLogin(User user) throws Exception {
    // TODO Auto-generated method stub
    User userInfo =userDao.login(user);
    return userInfo;
  }
  
  /**
   * 验证该手机号是否已被注册,0表示未注册，1表示已注册
   * */
  public int isPhoneNumExsit(String phoneNum) throws Exception{
    return userDao.isPhoneNumExsit(phoneNum);
  }
  
  /**
   * 用户注册
   */
  public void userRegist(User user) throws Exception {
    userDao.regist(user);
    int userId = user.getUserId();
    userDao.newUserStarRecordByUserId(userId);
  }
  
  /**
   * 修改用户姓名
   * */
  public void updateUserName(int userId, String userName) throws Exception {
    userDao.updateUserName(userId, userName);
  }

  /**
   * 修改用户邮箱
   * */
  public void updateUserMail(int userId, String userMail) throws Exception {
    userDao.updateUserMail(userId, userMail);
  }
  
  /**
   * 密码是否正确,正确返回1，错误返回0
   * */
  public int isCorrectUserPasswordByUserId(int userId, String userPassword)
    throws Exception {
    return userDao.isCorrectUserPasswordByUserId(userId, userPassword);
  }

  /**
   * 根据用户ID修改用户密码
   * */
  public void changeUserPasswordByUserId(int userId, String password)
      throws Exception {
    // TODO Auto-generated method stub
    userDao.changeUserPasswordByUserId(userId, password);
  }
  
  /**
   *获取菜品列表 
   * */
  public List<Dish> getDishList() throws Exception{
    return userDao.getDishList();
  }

  /**
   * 查询范围内的商家
   * */
  public List<Merchant> queryMerchantByPoint(Point point) throws Exception {
    return userDao.queryMerchantByPoint(point);
  }
  
  /**
   * 查询商家满足订单菜品的数量
   * */
  public int queryDishsCountWhichMerchantMeet(Map<String, Object> queryMap)
      throws Exception {
    return userDao.queryDishsCountWhichMerchantMeet(queryMap);
  }
  
  /***
   * 生成推送订单不包含菜品返回订单ID
   * */
  public int saveOrder(Order order,List<DishList> dishLists, List<Merchant> 
    merchants, double pointLeft, double pointRight, double leftDistance) 
        throws Exception {
    userDao.getOrderIdAndSaveOrder(order);
    int orderId = order.getoId();
    List<DishList> orderlist = new ArrayList<DishList>();
    for(int i=0; i<dishLists.size();i++){
      DishList dishList = new DishList();
      dishList = dishLists.get(i);
      dishList.setoId(orderId);
      orderlist.add(dishList);
    }
    userDao.saveDishList(orderlist);
    List<PushOrder> pushOrders = new ArrayList<PushOrder>();
    for(int i=0;i<merchants.size();i++){
      Merchant merchant = new Merchant();
      merchant = merchants.get(i);
      PushOrder pushOrder = new PushOrder();
      pushOrder.setoId(orderId);
      pushOrder.setmId(merchant.getMerchantId());
      int range = UserCommonTools.getMerchantRangeByTwoPointAndLeftDistance(
          pointLeft, pointRight, merchant.getMerchantLng(), 
          merchant.getMerchantLat(), leftDistance);
      pushOrder.setRange(range);
      pushOrders.add(pushOrder);
    }
    userDao.savePushOrder(pushOrders);
    return orderId;
  }

  /**
   * 查询已经已确认的商家信息列表
   * */
  public List<Merchant> querySuredMerchantListByOrderId(int orderId)
      throws Exception {
    return userDao.querySuredMerchantListByOrderId(orderId);
  }

  /**
   * 用户选择商家,不分单的情况
   * */
  public void chooseMerchant(int ordersId, int merchantId) throws Exception {
    userDao.chooseMerchant(ordersId, merchantId);
  }
  
  /**
   * get未完成订单列表
   * */
  public List<Order> getUnfinishedOrdersDetailsByUserId(int userId) throws Exception {
    List<Order> orderslist = userDao.getUnfinishedOrdersByUserId(userId);
    List<Order> returnOrderslist = new ArrayList<Order>();
    for(int i=0;i<orderslist.size();i++){
      Order order = new Order();
      order = orderslist.get(i);
      List<DishList> dishLists = userDao.getDishlistByOrderId(order.getoId());
        order.setDishlist(dishLists);
        returnOrderslist.add(order);
    }
    return returnOrderslist;
  }

  /**
   * 获取dishlists
   * */
  public List<DishList> getDishlistByOrderId(int orderId) throws Exception {
    return userDao.getDishlistByOrderId(orderId);
  }

  /**
   * get完成的订单列表
   * */
  public List<Order> getFinishedOrdersDetailsByUserId(int userId, int pageNum) throws Exception {
    List<Order> orderslist = userDao.getFinishedOrdersByUserId(userId, pageNum * 6);
    List<Order> returnOrderslist = new ArrayList<Order>();
    for(int i=0;i<orderslist.size();i++){
      Order order = new Order();
      order = orderslist.get(i);
      List<DishList> dishLists = userDao.getDishlistByOrderId(order.getoId());
      order.setDishlist(dishLists);
      returnOrderslist.add(order);
    }
    return returnOrderslist;
  }

  /**
   * 根据订单ID查询订单信息不含菜品信息
   * */
  public Order queryAllOrderInfoByOrderId(int orderId) throws Exception {
    Order order = new Order();
    order = userDao.queryOrderByOrderId(orderId);
    List<DishList> dishLists = userDao.getDishlistByOrderId(orderId);
    order.setDishlist(dishLists);
    return order;
  }

  /**
   * 获取好评列表
   * */
  public List<Comment> getGoodEvaluations() throws Exception {
    return userDao.getGoodEvaluations();
  }

  /**
   * 获取差评列表
   * */
  public List<Comment> getBadEvaluations() throws Exception {
    return userDao.getBadEvaluations();
  }

  /***
   * 根据订单ID查询商家ID
   * */
  public int getMerchantIdByOrderId(int orderId) throws Exception {
    return userDao.getMerchantIdByOrderId(orderId);
  }

  /**
   * 修改订单为用户确认收货
   * */
  public void userSureReceivedByOrderId(int orderId,int mark,
      String[] commentIds) throws Exception {
    int merchantId = userDao.getMerchantIdByOrderId(orderId);
    userDao.saveEvaluateMarkByMerchantId(merchantId, mark);
//    if(commentIds[0] != "noComments"){
      if(commentIds != null){
      for(int i=0; i<commentIds.length;i++){
        userDao.saveEvaluateCommentByMerchantId(merchantId, Integer.parseInt(commentIds[i]));
      }
    }
    MerchantStar merchantStar = userDao.getTotalStarAndEvaluateNumByMerchantId(merchantId);
    float star = (float)merchantStar.getTotalStar() / merchantStar.getEvaluateNumber();
    String comment = userDao.getMostCommentByMerchantId(merchantId);
    userDao.updateMerchantStarAndCommentByMerchantId(merchantId, star, comment);
    userDao.userSureReceivedByOrderId(orderId);
  }

  /**
   * 用户隐藏已完成订单
   * */
  public void hideFinishedOrderByOrderId(int orderId) throws Exception {
    userDao.hideFinishedOrderByOrderId(orderId);
  }

  /**
   * 修改订单状态为申请退款 
   * */
  public void applyRefund(int orderId) throws Exception {
    userDao.applyRefund(orderId);
  }

  /**
   * 记录退款理由
   * */
  public void recordRefundInFo(RefundReason refundReason) throws Exception {
    userDao.recordRefundInFo(refundReason);
  }

  /**
   * 根据用户注册电话号码修改用户密码
   * */
  public void changeUserPasswordByPhoneNum(String phoneNum, String password)
      throws Exception {
    userDao.changeUserPasswordByPhoneNum(phoneNum, password);
  }

  /**
   * 查询该范围内满足该菜品的商家数
   * */
  public int getMerchantsCountWhichMeetDish(Map<String, Object> queryMap)
      throws Exception {
    return userDao.getMerchantsCountWhichMeetDish(queryMap);
  }

  /**
   * 根据菜品Id查询菜品名
   * */
  public String getDishNameByDishId(int dishId) throws Exception {
    return userDao.getDishNameByDishId(dishId);
  }

  /**
   * 支付订单
   * 储存成功返回1，失败返回0
   * */
  public int payForOrder(int orderId) throws Exception {
    System.out.println("aaaaa"+orderId);
    int result=0;
    List<DishList> orderlist = userDao.getDishlistByOrderId(orderId);
    int merchantId = userDao.getMerchantIdByOrderId(orderId);
    Map<String, Object> queryMap = new HashMap<String, Object>();
    queryMap.put("merchantId", merchantId);
    queryMap.put("orderlist", orderlist);
    
    int meetNum = userDao.queryDishsCountWhichMerchantMeet(queryMap);
    if(meetNum == orderlist.size()){
      userDao.changeOrderPayStatus(orderId);
      Advance advance = new Advance();
      advance.setOrderId(orderId);
      advance.setAdvanceTime(TimeUtil.getCurrentTimeAndDate());
      userDao.advanceRecord(advance);
      for(int i=0;i<orderlist.size();i++){
        DishList dishList = new DishList();
        dishList = orderlist.get(i);
        userDao.modifyMerchantInventoryByDishLists(dishList.getNumber(),merchantId,dishList.getDishId());
      }
      result=1;
    }
    return result;
  }

  /**
   * 根据用户ID获取用户信息
   * */
  public User getUserByUserId(int userId) throws Exception {
    return userDao.getUserByUserId(userId);
  }

  /***
   * 获取运营模式
   * */
  public int getOperateMode() throws Exception{
    return userDao.getOperateMode();
  }

  /**
   * 单商家模式获取菜单列表
   * */
  public List<Dish> getDishListForSingleBusinessMode() throws Exception {
    List<Dish> dishList = userDao.getDishList();
    List<Dish> returnDishList = new ArrayList<Dish>();
    if(dishList != null){
      for(int i=0; i<dishList.size();i++){
        Dish dish = new Dish();
        dish = dishList.get(i);
        int count = userDao.getDishCountByDishIdAndMerchantId(dish.getDishId(), 1);
        List<Type> typelist = userDao.getTypesByTargetIdAndTargetType(
            dish.getDishId(), ResultCode.TARGET_DISH);
        dish.setCount(count);
        dish.setTypeList(typelist);
        returnDishList.add(dish);
      }
    }
    return returnDishList;
  }

  /**
   * 获取菜品的分类列表
   * */
  public List<Type> getDishTypeList() throws Exception {
    return userDao.getDishTypeList();
  }

  /**
   * 获取套餐的分类列表
   * */
  public List<Type> getDishGroupTypeList() throws Exception {
    return userDao.getDishGroupTypeList();
  }

  /**
   * 根据类型获取菜单列表
   * */
  public List<Dish> getDishListByTypeIdForSingleBusinessMode(int typeId) 
      throws Exception {
    List<Dish> dishList = new ArrayList<Dish>();
    dishList = userDao.getDishListByTypeId(typeId);
    List<Dish> returnDishList = new ArrayList<Dish>();
    if(dishList != null){
      for(int i=0; i<dishList.size();i++){
        Dish dish = new Dish();
        dish = dishList.get(i);
        int count = userDao.getDishCountByDishIdAndMerchantId(dish.getDishId(), 1);
        List<Type> typelist = userDao.getTypesByTargetIdAndTargetType(
            dish.getDishId(), ResultCode.TARGET_DISH);
        dish.setCount(count);
        dish.setTypeList(typelist);
        returnDishList.add(dish);
      }
    }
    return returnDishList;
  }

  /**
   * 根据类名获取typeId
   * */
  public Type getTypeByTypeName(String typeName) throws Exception {
    return userDao.getTypeByTypeName(typeName);
  }

  /**
   * 获取套餐列表
   * */
  public List<DishGroup> getDishGroupList() throws Exception {
    return userDao.getDishGroupList();
  }

  /**
   * 获取套餐列表里菜品的信息
   * */
  public List<DishGroup> getDishListForDishGroupList(List<DishGroup> dishGroups) 
      throws Exception {
    for(int i=0;i<dishGroups.size();i++){
      dishGroups.get(i).setDishList(StringUtil.jsonToList(dishGroups.get(i).getDishes()));
      double totalOriginalPrice = 0;//套餐总原价（累加）
      double totalRetailPrice = 0;//套餐总售价（累加）
      List<Dish> dishs = new ArrayList<Dish>();
      for (int j=0; j<dishGroups.get(i).getDishList().size(); j++) {
        Dish dish = dishGroups.get(i).getDishList().get(j);
        System.out.println("套餐名："+dishGroups.get(i).getGroupName()+"里面的菜品Id="+dish.getDishId()); 
        Dish singleDish = userDao.getDishInfoById(dish.getDishId());
        dish.setDishName(singleDish.getDishName());
        dish.setDishIntro(singleDish.getDishIntro());
        dish.setDishDetail(singleDish.getDishDetail());
        dish.setDishPic(singleDish.getDishPic());
        dish.setCostPrice(singleDish.getCostPrice());
        dish.setOriginalPrice(singleDish.getOriginalPrice());
        dish.setRetailPrice(singleDish.getRetailPrice());
        dish.setDishTaste(singleDish.getDishTaste());
        System.out.println("在设置后的菜品名字："+dish.getDishName() );
        dishs.add(dish);
        totalOriginalPrice = totalOriginalPrice + (dish.getOriginalPrice() * dish.getNum());//套餐总原价（累加）
        totalRetailPrice = totalRetailPrice + (dish.getRetailPrice() * dish.getNum());//套餐总售价（累加）
      }
      dishGroups.get(i).setDishList(dishs);
      List<Type> types = userDao.getTypesByTargetIdAndTargetType(
          dishGroups.get(i).getGroupId(), ResultCode.TARGET_DISHGROUP);
      dishGroups.get(i).setTypeList(types);
      dishGroups.get(i).setOriginalPrice(totalOriginalPrice);
      dishGroups.get(i).setRetailPrice(totalRetailPrice);
    }
    return dishGroups;
  }

  /***
   * 根据套餐Id获取套餐信息
   * */
  public DishGroup getDishGroupByGroupId(int groupId) throws Exception {
    return userDao.getDishGroupByGroupId(groupId);
  }

  /***
   * 根据菜品Id和商家Id获取库存
   * */
  public int getDishCountByDishIdAndMerchantId(int dishId, int MerchantId) throws Exception {
    return userDao.getDishCountByDishIdAndMerchantId(dishId, MerchantId);
  }

  /**
   * 通过商家id得到商家详情
   * */
  public Merchant getMerchantByMerchantId(int merchantId) throws Exception {
    return userDao.getMerchantByMerchantId(merchantId);
  }

  /**
   * 根据typeID获取套餐列表
   * */
  public List<DishGroup> getDishGroupListByTypeId(int typeId) throws Exception {
    return userDao.getDishGroupListByTypeId(typeId);
  }

  /**
   * 通过dish_id获取整个菜品信息
   * */
  public Dish getDishInfoById(int dishId) throws Exception {
    return userDao.getDishInfoById(dishId);
  }

  /**
   * 计算套餐的库存
   * */
  public List<DishGroup> findDishGroupsCount(List<DishGroup> dishGroups) throws Exception {
    List<DishGroup> newDishGroups = new ArrayList<DishGroup>();
    for(int i=0;i<dishGroups.size();i++){
      DishGroup dishGroup = dishGroups.get(i);
      int count = 0;
      for(int j=0; j<dishGroup.getDishList().size();j++){
        Dish dish = dishGroup.getDishList().get(j);
        int dishCount = userDao.getDishCountByDishIdAndMerchantId(dish.getDishId(), 1);
        if(count == 0){
          count = dishCount/dish.getNum();
        }
        else if(count > dishCount/dish.getNum()){
          count = dishCount/dish.getNum();
        }
      }
      dishGroup.setCount(count);
      newDishGroups.add(dishGroup);
    }
    return newDishGroups;
  }
  
  /**
   * 单商家模式修改商家为总店商家ID
   * */
  public void updateMerchantIdFromOrdersForSingle(int orderId) throws Exception {
     userDao.updateMerchantIdFromOrdersForSingle(orderId);
  }

}