package com.cn.travel.util;

import com.cn.travel.bean.SightBean;
import com.cn.travel.bean.SightTypeRelationBean;
import com.cn.travel.bean.TypeBean;
import com.cn.travel.bean.UserBean;
import com.cn.travel.entity.*;
import com.cn.travel.entity.SightTypeRelationEntity;
import com.cn.travel.entity.UserEntity;

public class CommonUtil {
  public static SightBean changeSightEntityToSightBean(SightEntity sightEntity){
    SightBean sightBean = new SightBean();
    sightBean.setSightId(sightEntity.getSightId());
    sightBean.setSightName(sightEntity.getSightName());
    sightBean.setSightLng(sightEntity.getSightLng());
    sightBean.setSightLat(sightEntity.getSightLat());
    sightBean.setAddress(sightEntity.getAddress());
    sightBean.setSightIntro(sightEntity.getSightIntro());
    sightBean.setDistrict(sightEntity.getDistrict());
    return sightBean;
  }
  
  public static SightEntity changeSightBeanToSightEntity(SightBean sightBean){
    SightEntity sightEntity = new SightEntity();
    sightEntity.setSightId(sightBean.getSightId());
    sightEntity.setSightName(sightBean.getSightName());
    sightEntity.setSightLng(sightBean.getSightLng());
    sightEntity.setSightLat(sightBean.getSightLat());
    sightEntity.setAddress(sightBean.getAddress());
    sightEntity.setSightIntro(sightBean.getSightIntro());
    sightEntity.setDistrict(sightBean.getDistrict());
    return sightEntity;
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
  
  public static SightTypeRelationBean changeRelationEntityToRelationBean(
      SightTypeRelationEntity relationEntity){
    SightTypeRelationBean relationBean = new SightTypeRelationBean();
    relationBean.setRelationId(relationEntity.getRelationId());
    relationBean.setSightId(relationEntity.getSightId());
    relationBean.setTypeId(relationEntity.getTypeId());
    return relationBean;
  }
  
  public static SightTypeRelationEntity changeRelationBeanToRelationEntity(
      SightTypeRelationBean relationBean){
    SightTypeRelationEntity relationEntity = new SightTypeRelationEntity();
    relationEntity.setRelationId(relationBean.getRelationId());
    relationEntity.setSightId(relationBean.getSightId());
    relationEntity.setTypeId(relationBean.getTypeId());
    return relationEntity;
  }
  
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
}
