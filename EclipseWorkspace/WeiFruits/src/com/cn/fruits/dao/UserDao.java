package com.cn.fruits.dao;

import com.cn.fruits.bean.UserBean;
import com.cn.fruits.entity.UserEntity;

/**
 * 用户模块，数据库层接口
 * */
public interface UserDao {
  public boolean userNameisExistDao(String userName)throws Exception;
  
  public int insertNewUserDao(UserEntity userEntity) throws Exception;
  
  public int selectUserIdByUserNameAndPassWordDao(String userName,
      String passWord)throws Exception;
  
  public UserBean selectUserByUserIdDao(int userId) throws Exception;
  
  public boolean updateUserNameByUserIdDao(int userId,String name)
      throws Exception;
}
