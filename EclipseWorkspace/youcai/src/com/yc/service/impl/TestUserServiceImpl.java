package com.yc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.TestUser;
import com.yc.dao.TestUserDAO;
import com.yc.service.TestUserService;
/**
 * 用户service 实现类
 * @author 
 *
 */
@Service
public class TestUserServiceImpl implements TestUserService {

  @Autowired
  private TestUserDAO userDao;

  /**
   * 用户注册
   */
  @Override
  public void userRegist(TestUser user) throws Exception {
    userDao.regist(user);
    
  }

  /**
   * 用户登录
   */
  @Override
  public TestUser userLogin(TestUser user) throws Exception {
    // TODO Auto-generated method stub
    TestUser userInfo =userDao.login(user);
    return userInfo;
  }

  /**
   *修改用户密码
   */
  @Override
  public void updateUserPwd(TestUser user) throws Exception {
    // TODO Auto-generated method stub
    userDao.updateUserPwd(user);
  }

  /**
   * 根据id查询用户信息
   */
  @Override
  public TestUser getUserInfoId(int id) throws Exception {
    // TODO Auto-generated method stub
    TestUser user=userDao.getUserInfoId(id);
    return user;
  }

  /**
   * 根据用户名查询是否有相同记录
   */
  @Override
  public TestUser queryUserForPhone(String phone) throws Exception {
    // TODO Auto-generated method stub
    TestUser user = userDao.queryUserForPhone(phone);
    return user;
  }


}
