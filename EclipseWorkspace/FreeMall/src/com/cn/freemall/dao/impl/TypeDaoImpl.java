package com.cn.freemall.dao.impl;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.freemall.dao.TypeDao;

@Repository
public class TypeDaoImpl implements TypeDao {
  @Inject
  private SessionFactory sessionFactory;
  
}
