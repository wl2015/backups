package com.yc.dao;

import java.util.List;
import java.util.Map;

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


/**
 * 用户DAO层
 * @author RainsChan
 *
 */
public interface UserDAO {
  
  User login(User user) throws Exception;//用户登录
  
  int isPhoneNumExsit(String phoneNum) 
      throws Exception;//验证该手机号是否已被注册,0表示未注册，1表示已注册

  void regist(User user) throws Exception;//注册
  
  User getUserByUserId(int userId) throws Exception;//根据userId获取user
  
  void updateUserName(int userId, String userName) throws Exception;//修改用户姓名
  
  void updateUserMail(int userId, String userMail) throws Exception;//修改用户邮箱

  int isCorrectUserPasswordByUserId(int userId, String userPassword) 
      throws Exception;//密码是否正确,正确返回1，错误返回0
  
  void changeUserPasswordByUserId(int userId, String password) 
      throws Exception;//根据用户ID修改用户密码
  
  List<Dish> getDishList() throws Exception;//获取菜品列表
  
  List<Merchant> queryMerchantByPoint(Point point) 
      throws Exception;//查询范围内的商家
  
  int queryDishsCountWhichMerchantMeet(Map<String, Object> queryMap) 
      throws Exception;//查询商家满足菜品的数量
  
  int getOrderIdAndSaveOrder(Order order) 
      throws Exception;//生成推送订单不包含菜品返回订单ID

  void saveDishList(List<DishList> dishLists) throws Exception;//记录订单菜品列表
  
  void savePushOrder(List<PushOrder> pushOrders) throws Exception;//记录推送商家
  
  List<Merchant> querySuredMerchantListByOrderId(int orderId) 
      throws Exception;//查询已经已确认的商家信息列表
  
  void chooseMerchant(int orderId, int merchantId) 
      throws Exception;//用户选择商家,不分单的情况

  List<Order> getUnfinishedOrdersByUserId(int userId) 
      throws Exception;//get未完成订单列表

  List<DishList> getDishlistByOrderId(int orderId) 
      throws Exception;//获取dishlists

  List<Order> getFinishedOrdersByUserId(int userId, int numStart) 
      throws Exception;//get完成的订单列表

  Order queryOrderByOrderId(int orderId) 
      throws Exception;//根据订单ID查询订单信息不含菜品信息
  
  void changeOrderPayStatus(int orderId) throws Exception;//修改订单为支付状态
  
  List<Comment> getGoodEvaluations() throws Exception;//获取好评列表
  
  List<Comment> getBadEvaluations() throws Exception;//获取差评列表
  
  int getMerchantIdByOrderId(int orderId) throws Exception;//根据订单ID查询商家ID 
  
  void saveEvaluateMarkByMerchantId(int merchantId, int mark) 
      throws Exception;//保存评星
  
  void saveEvaluateCommentByMerchantId(int merchantId, int commentId) 
      throws Exception;//保存评语 
  
  MerchantStar getTotalStarAndEvaluateNumByMerchantId(int merchantId) 
      throws Exception;//获取用户总星级和评价次数
  
  String getMostCommentByMerchantId(int merchantId) 
      throws Exception;//获取对商家次数最多的评论ID

  void updateMerchantStarAndCommentByMerchantId(int merchantId, float star, 
      String comment) throws Exception;//修改商家星级和评语
  
  void userSureReceivedByOrderId(int orderId) throws Exception;//修改订单为用户确认收货
  
  void hideFinishedOrderByOrderId(int orderId) throws Exception;//用户隐藏已完成订单
  
  void applyRefund(int orderId) throws Exception;//修改订单状态为申请退款 
  
  void recordRefundInFo(RefundReason refundReason) throws Exception;//记录退款理由
  
  void changeUserPasswordByPhoneNum(String phoneNum,String password) 
      throws Exception;//根据用户注册电话号码修改用户密码
  
  int getMerchantsCountWhichMeetDish(Map<String, Object> queryMap) 
      throws Exception;//查询该范围内满足该菜品的商家数
  
  String getDishNameByDishId(int dishId) throws Exception;//根据菜品Id查询菜品名
  
  void advanceRecord(Advance advance) throws Exception;//生成预付款记录 
  
  void newUserStarRecordByUserId(int orderId) throws Exception;//新建用户评星记录
  
  void modifyMerchantInventoryByDishLists(int num, int merchantId, int dishId) 
      throws Exception;//点单成功后修改库存
//  void modifyMerchantInventoryByDishLists(Map<String, Object> queryMap) 
//      throws Exception;//点单成功后修改库存

  int getOperateMode() throws Exception;//获取运营模式
  
  int getDishCountByDishIdAndMerchantId(int dishId, int MerchantId) 
      throws Exception;//根据菜品Id和商家Id获取库存
  
  List<Type> getDishTypeList() throws Exception;//获取菜品的分类列表
  
  List<Type> getDishGroupTypeList() throws Exception;//获取套餐的分类列表
  
  List<Type> getTypesByTargetIdAndTargetType(int targetId,int targetType) 
      throws Exception;//根据目标Id和目标类型获取目标属于的分类集
  
  List<Dish> getDishListByTypeId(int typeId) throws Exception;//根据类型获取菜单列表
  
  Type getTypeByTypeName(String typeName) throws Exception;//根据类名获取typeId
  
  List<DishGroup> getDishGroupList() throws Exception;//获取套餐列表
  
  Dish getDishInfoById(int dishId) throws Exception;//通过dish_id获取整个菜品信息

  DishGroup getDishGroupByGroupId(int groupId) throws Exception;//根据套餐Id获取套餐信息
  
  Merchant getMerchantByMerchantId(int merchantId) throws Exception;//通过商家id得到商家详情 

  List<DishGroup> getDishGroupListByTypeId(int typeId) throws Exception;//根据typeID获取套餐列表

  void updateMerchantIdFromOrdersForSingle(int orderId) throws Exception;//单商家模式修改商家为总店商家ID
}