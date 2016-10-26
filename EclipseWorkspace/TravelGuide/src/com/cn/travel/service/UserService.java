package com.cn.travel.service;

import java.util.List;

import com.cn.travel.bean.SightBean;
import com.cn.travel.bean.TypeBean;
import com.cn.travel.bean.UserBean;
import com.cn.travel.entity.UserEntity;

/**
 * 用户模块，服务层接口
 */
public interface UserService {
  public UserBean doRegistUserService(UserBean userBean)throws Exception;
  
  public List<TypeBean> getAllTypesService()throws Exception;
  
  public List<SightBean> getSightsByDistrictService(String district)throws Exception;
  
  public List<SightBean> getSightsByTypeIdService(int typeId)throws Exception;
  
  public List<SightBean> getSightsByTypeNameService(String typeName)throws Exception;
  
  public UserBean doLoginService(UserBean userBean)throws Exception;
  
  public boolean checkUserNameIsExistService(String userName)throws Exception;

  public List<SightBean> getSightsBySightNameService(String sightName)throws Exception;
}
