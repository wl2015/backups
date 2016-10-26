package com.cn.freemall.dao;

import com.cn.freemall.bean.UserBean;
import com.cn.freemall.entity.UserEntity;

/**
 * 用户模块，数据库层接口
 * */
public interface UserDao {
  public UserBean insertNewUserDao(UserEntity userEntity)throws Exception;
  
  public UserBean selectUserByUserNameAndPassWordDao(UserEntity userEntity)
      throws Exception;
  
  public boolean checkUserNameIsExistDao(String userName)throws Exception;
  
  public UserBean selectUserByUserIdDao(int userId)throws Exception;
  
  public boolean updateUserInfoDao(UserEntity userEntity)throws Exception;
}
