package com.yc.service;
import com.yc.bean.TestUser;
/**
 * baseUserService
 * @author
 *
 */
public interface TestUserService {
  
  void userRegist(TestUser user) throws Exception;//用户注册
  
  TestUser userLogin(TestUser user) throws Exception;//用户登录
  
  void updateUserPwd(TestUser user) throws Exception;//修改用户密码
  
  TestUser queryUserForPhone(String phone) throws Exception;//根据用户名查询是否存在该用户
  
  TestUser getUserInfoId(int id) throws Exception;//根据用户id查询用户信息
  

}
