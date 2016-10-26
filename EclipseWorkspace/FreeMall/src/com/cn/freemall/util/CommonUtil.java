package com.cn.freemall.util;

import com.cn.freemall.bean.MessageBean;
import com.cn.freemall.bean.ProductBean;
import com.cn.freemall.bean.TypeBean;
import com.cn.freemall.bean.UserBean;
import com.cn.freemall.entity.MessageEntity;
import com.cn.freemall.entity.ProductEntity;
import com.cn.freemall.entity.TypeEntity;
import com.cn.freemall.entity.UserEntity;

public class CommonUtil {
  public static UserBean changeUserEntityToUserBean(UserEntity userEntity){
    UserBean userBean = new UserBean();
    userBean.setUserId(userEntity.getUserId());
    userBean.setUserName(userEntity.getUserName());
    userBean.setPassWord(userEntity.getPassWord());
    userBean.setPhoneNumber(userEntity.getPhoneNumber());
    userBean.setName(userEntity.getName());
    return userBean;
  }
  
  public static UserEntity changeUserBeanToUserEntity(UserBean userBean){
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(userBean.getUserId());
    userEntity.setUserName(userBean.getUserName());
    userEntity.setPassWord(userBean.getPassWord());
    userEntity.setPhoneNumber(userBean.getPhoneNumber());
    userEntity.setName(userBean.getName());
    return userEntity;
  }
  
  public static TypeBean changeTypeEntityToTypeBean(TypeEntity typeEntity){
    TypeBean typeBean = new TypeBean();
    typeBean.setTypeId(typeEntity.getTypeId());
    typeBean.setTypeName(typeEntity.getTypeName());
    return typeBean;
  }
  
  public static TypeEntity changeTypeBeanToTypeEntity(TypeBean typeBean){
    TypeEntity typeEntity = new TypeEntity();
    typeEntity.setTypeId(typeBean.getTypeId());
    typeEntity.setTypeName(typeBean.getTypeName());
    return typeEntity;
  }
  
  public static MessageBean changeMessageEntityToMessageBean(MessageEntity messageEntity){
    MessageBean messageBean = new MessageBean();
    messageBean.setMessageId(messageEntity.getMessageId());
    messageBean.setContent(messageEntity.getContent());
    messageBean.setSendId(messageEntity.getSendId());
    messageBean.setReceiveId(messageEntity.getReceiveId());
    messageBean.setTime(messageEntity.getTime());
    messageBean.setFirst(messageEntity.getFirst());
    messageBean.setState(messageEntity.getState());
    return messageBean;
  }
  
  public static MessageEntity changeMessageBeanToMessageEntity(MessageBean messageBean){
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setMessageId(messageBean.getMessageId());
    messageEntity.setContent(messageBean.getContent());
    messageEntity.setSendId(messageBean.getSendId());
    messageEntity.setReceiveId(messageBean.getReceiveId());
    messageEntity.setTime(messageBean.getTime());
    messageEntity.setFirst(messageBean.getFirst());
    messageEntity.setState(messageBean.getState());
    return messageEntity;
  }
  
  public static ProductBean changeProductEntityToProductBean(ProductEntity productEntity){
    ProductBean productBean = new ProductBean();
    productBean.setProductId(productEntity.getProductId());
    productBean.setUserId(productEntity.getUserId());
    productBean.setProductName(productEntity.getProductName());
    productBean.setPlace(productEntity.getPlace());
    productBean.setPrice(productEntity.getPrice());
    productBean.setIntro(productEntity.getIntro());
    productBean.setPic(productEntity.getPic());
    productBean.setTime(productEntity.getTime());
    productBean.setState(productEntity.getState());
    productBean.setTypeId(productEntity.getTypeId());
    return productBean;
  }
  
  public static ProductEntity changeProductBeanToProductEntity(ProductBean productBean){
    ProductEntity productEntity = new ProductEntity();
    productEntity.setProductId(productBean.getProductId());
    productEntity.setUserId(productBean.getUserId());
    productEntity.setProductName(productBean.getProductName());
    productEntity.setPlace(productBean.getPlace());
    productEntity.setPrice(productBean.getPrice());
    productEntity.setIntro(productBean.getIntro());
    productEntity.setPic(productBean.getPic());
    productEntity.setTime(productBean.getTime());
    productEntity.setState(productBean.getState());
    productEntity.setTypeId(productBean.getTypeId());
    return productEntity;
  }
}
