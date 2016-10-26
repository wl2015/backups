package com.cn.fruits.util;

import javax.servlet.http.Cookie;

import com.cn.fruits.bean.AdminBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.FruitListBean;
import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.bean.UserBean;
import com.cn.fruits.entity.AdminEntity;
import com.cn.fruits.entity.FruitEntity;
import com.cn.fruits.entity.FruitListEntity;
import com.cn.fruits.entity.OrdersEntity;
import com.cn.fruits.entity.UserEntity;

public class CommonUtil {
  /**
   * 把AdminBean转换成AdminEntity
   * */
  public static AdminEntity changeAdminBeanIntoAdminEntity(AdminBean adminBean){
    AdminEntity adminEntity = new AdminEntity();
    adminEntity.setAdminId(adminBean.getAdminId());
    adminEntity.setAdminAccount(adminBean.getAdminAccount());
    adminEntity.setAdminName(adminBean.getAdminName());
    adminEntity.setAdminPassword(adminBean.getAdminPassword());
    adminEntity.setAdminLimit(adminBean.getAdminLimit());
    return adminEntity;
  }
  
  /**
   * 把AdminEntity转换成AdminBean
   * */
  public static AdminBean changeAdminEntityIntoAdminBean(AdminEntity adminEntity){
    AdminBean adminBean = new AdminBean();
    adminBean.setAdminId(adminEntity.getAdminId());
    adminBean.setAdminAccount(adminEntity.getAdminAccount());
    adminBean.setAdminName(adminEntity.getAdminName());
    adminBean.setAdminPassword(adminEntity.getAdminPassword());
    adminBean.setAdminLimit(adminEntity.getAdminLimit());
    return adminBean;
  }
  
  /**
   * 把UserBean转换成UserEntity
   * */
  public static UserEntity changeUserBeanIntoUserEntity(UserBean userBean){
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(userBean.getUserId());
    userEntity.setLoginUserId(userBean.getLoginUserId());
    userEntity.setUserName(userBean.getUserName());
    userEntity.setPassWord(userBean.getPassWord());
    userEntity.setName(userBean.getName());
    return userEntity;
  }
  
  /**
   * 把UserEntity转换成UserBean
   * */
  public static UserBean changeUserEntityIntoUserBean(UserEntity userEntity){
    UserBean userBean = new UserBean();
    userBean.setUserId(userEntity.getUserId());
    userBean.setLoginUserId(userEntity.getLoginUserId());
    userBean.setUserName(userEntity.getUserName());
    userBean.setPassWord(userEntity.getPassWord());
    userBean.setName(userEntity.getName());
    return userBean;
  }
  
  /**
   * 把OrdersEntity转换成OrdersBean
   * */
  public static OrdersBean changeOrdersEntityIntoOrdersBean(OrdersEntity ordersEntity){
    OrdersBean ordersBean = new OrdersBean();
    ordersBean.setOrderId(ordersEntity.getOrderId());
    ordersBean.setUserId(ordersEntity.getUserId());
    ordersBean.setReceiveName(ordersEntity.getReceiveName());
    ordersBean.setReceivePhone(ordersEntity.getReceivePhone());
    ordersBean.setCampusName(ordersEntity.getCampusName());
    ordersBean.setDormitoryName(ordersEntity.getDormitoryName());
    ordersBean.setAddress(ordersEntity.getAddress());
    ordersBean.setMoney(ordersEntity.getMoney());
    ordersBean.setOrderTime(ordersEntity.getOrderTime());
    ordersBean.setPayWay(ordersEntity.getPayWay());
    ordersBean.setReceiveWay(ordersEntity.getReceiveWay());
    ordersBean.setPayStatus(ordersEntity.getPayStatus());
    ordersBean.setOrderStatus(ordersEntity.getOrderStatus());
    ordersBean.setMessge(ordersEntity.getMessge());
    return ordersBean;
  }
  
  /**
   * 把FruitListEntity转换成FruitListBean
   * */
  public static FruitListBean changeFruitListEntityIntoFruitListBean(FruitListEntity fruitListEntity){
    FruitListBean fruitListBean = new FruitListBean();
    fruitListBean.setFruitListId(fruitListEntity.getFruitListId());
    fruitListBean.setOrderId(fruitListEntity.getOrderId());
    fruitListBean.setFruitId(fruitListEntity.getFruitId());
    fruitListBean.setCount(fruitListEntity.getCount());
    return fruitListBean;
  }
  
  /**
   * 把FruitEntity转换成FruitBean
   * */
  public static FruitBean changeFruitEntityIntoFruitBean(FruitEntity fruitEntity){
    FruitBean fruitBean = new FruitBean();
    fruitBean.setFruitId(fruitEntity.getFruitId());
    fruitBean.setFruitName(fruitEntity.getFruitName());
    fruitBean.setFruitPrice(fruitEntity.getFruitPrice());
    fruitBean.setFruitIntr(fruitEntity.getFruitIntr());
    fruitBean.setFruitDetail(fruitEntity.getFruitDetail());
    fruitBean.setFruitPic(fruitEntity.getFruitPic());
    fruitBean.setCreateTime(fruitEntity.getCreateTime());
    fruitBean.setFruitState(fruitEntity.getFruitState());
    return fruitBean;
  }
  /**
   * 从cookies里面获取userId
   * 如果不存在，返回0
   * */
  public static int getUserIdFromCookies(Cookie[] cookies){
    Cookie myCookie=null;
    int userId = 0;
    for(int i=0;i<cookies.length;i++) {
      if(cookies[i].getName().equals("UserId")) {
        myCookie=cookies[i];
        userId = Integer.parseInt(myCookie.getValue());
        break;
       }
      }
    return userId;
  }
}

