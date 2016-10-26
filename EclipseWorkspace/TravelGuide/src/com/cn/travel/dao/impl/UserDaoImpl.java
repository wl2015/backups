package com.cn.travel.dao.impl;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.travel.bean.UserBean;
import com.cn.travel.dao.UserDao;
import com.cn.travel.entity.UserEntity;
import com.cn.travel.util.CommonUtil;

/**
 * 用户模块，数据库层接口实现类
 * */
@Repository
public class UserDaoImpl implements UserDao {
  
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 保存新的用户
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
  
}
