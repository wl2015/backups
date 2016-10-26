package com.cn.freemall.dao.impl;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.freemall.bean.UserBean;
import com.cn.freemall.dao.UserDao;
import com.cn.freemall.entity.UserEntity;
import com.cn.freemall.util.CommonUtil;

/**
 * 用户模块，数据库层接口实现类
 * */
@Repository
public class UserDaoImpl implements UserDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 注册新的用户
   * @param UserEntity
   * @return userId 如果注册失败就返回0
   * */
  public UserBean insertNewUserDao(UserEntity userEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(userEntity);
    UserBean userBean = new UserBean();
    if(serializable != null){
      userBean = CommonUtil.changeUserEntityToUserBean(userEntity);
    }
    return userBean;
  }

  /**
   * 根据用户名和密码查询用户
   * */
  public UserBean selectUserByUserNameAndPassWordDao(UserEntity userEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM UserEntity WHERE userName = ? AND passWord = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userEntity.getUserName());
    query.setParameter(1, userEntity.getPassWord());
    UserEntity entity = (UserEntity) query.uniqueResult();
    UserBean userBean = new UserBean();
    if(entity != null){
      userBean = CommonUtil.changeUserEntityToUserBean(entity);
    }
    return userBean;
  }
  
  /**
   * 检查用户名是否存在
   * 存在返回true，不存在返回false
   * */
  public boolean checkUserNameIsExistDao(String userName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM UserEntity WHERE userName = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userName);
    UserEntity userEntity = (UserEntity) query.uniqueResult();
    if(userEntity == null){
      return false;
    }
    return true;
  }

  /**
   * 根据用户ID获取用户信息
   * */
  public UserBean selectUserByUserIdDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM UserEntity WHERE userId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    UserEntity entity = (UserEntity) query.uniqueResult();
    UserBean userBean = new UserBean();
    if(entity != null){
      userBean = CommonUtil.changeUserEntityToUserBean(entity);
    }
    return userBean;
  }

  /***
   * 修改用户信息
   * */
  public boolean updateUserInfoDao(UserEntity userEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE UserEntity set passWord=?, phoneNumber=?, "
        + "name=? WHERE userId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userEntity.getPassWord());
    query.setParameter(1, userEntity.getPhoneNumber());
    query.setParameter(2, userEntity.getName());
    query.setParameter(3, userEntity.getUserId());
    int result = query.executeUpdate();
    if(result != 1){
      return false;
    }
    return true;
  }
}
