package com.cn.fruits.dao.impl;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.UserBean;
import com.cn.fruits.dao.UserDao;
import com.cn.fruits.entity.UserEntity;
import com.cn.fruits.util.CommonUtil;

/**
 * 用户模块，数据库层接口实现类
 * */
@Repository
public class UserDaoImpl implements UserDao {
  
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 该手机号码是否存在，存在返回true，不存在返回false
   * */
  public boolean userNameisExistDao(String userName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM UserEntity WHERE userName = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userName);
    UserEntity userEntity =  (UserEntity) query.uniqueResult();
    if(userEntity!=null){
      return true;
    }
    return false;
  }

  /**
   *  注册新的用户
   *  注册成功返回用户id
   * */
  public int insertNewUserDao(UserEntity userEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    session.save(userEntity);
    return userEntity.getUserId();
  }
  
  /**
   * 根据用户名和密码查询用户id
   * */
  public int selectUserIdByUserNameAndPassWordDao(String userName,
      String passWord)throws Exception{
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM UserEntity U WHERE U.userName=? AND "
        + "U.passWord=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userName);
    query.setParameter(1, passWord);
    UserEntity userEntity = (UserEntity) query.uniqueResult();
    int userId = 0;
    if(userEntity != null){
      userId = userEntity.getUserId();
    }
    System.out.println(userId);
    return userId;
  }

  /**
   * 根据用户Id获取用户
   * */
  public UserBean selectUserByUserIdDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM UserEntity WHERE userId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    UserEntity userEntity = (UserEntity) query.uniqueResult();
    UserBean userBean = new UserBean();
    userBean = CommonUtil.changeUserEntityIntoUserBean(userEntity);
    return userBean;
  }

  /**
   * 根据userId修改用户名
   * */
  public boolean updateUserNameByUserIdDao(int userId, String name) throws Exception {
    Session session =sessionFactory.getCurrentSession();
    String Hql = "UPDATE UserEntity U set U.name=? WHERE U.userId=?,";
    Query query = session.createQuery(Hql);
    query.setParameter(0, name);
    query.setParameter(1, userId);
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }
}
