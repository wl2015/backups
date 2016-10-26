package com.cn.fruits.dao.impl;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.dao.LoginUserDao;

@Repository
public class LoginUserDaoImpl implements LoginUserDao {
  @Inject
  private SessionFactory sessionFactory;
}
