package com.yc.dao;

import com.yc.bean.TestUser;


/**
 * 用户DAO层
 * @author RainsChan
 *
 */
public interface TestUserDAO {

  void regist(TestUser user) throws Exception;//注册
  
  TestUser login(TestUser user) throws Exception;//用户登录
  
  void updateUserPwd(TestUser user) throws Exception;//修改用户密码
  
  TestUser getUserInfoId(int id) throws Exception;//根据用户id查询信息
  
  TestUser queryUserForPhone(String phone) throws Exception;//根据用户名查询是否存在该用户
  
}
