package com.cn.travel.dao;

import com.cn.travel.bean.UserBean;
import com.cn.travel.entity.UserEntity;

/**
 * 用户模块，数据库层接口
 * */
public interface UserDao {
  public UserBean insertNewUserDao(UserEntity userEntity)throws Exception;
  
  public UserBean selectUserByUserNameAndPassWordDao(UserEntity userEntity)
      throws Exception;
  
  public boolean checkUserNameIsExistDao(String userName)throws Exception;
}
