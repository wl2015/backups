package com.cn.travel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.travel.entity.*;
import com.cn.travel.bean.TypeBean;
import com.cn.travel.dao.TypeDao;
import com.cn.travel.util.CommonUtil;

@Repository
public class TypeDaoImpl implements TypeDao {
  @Inject
  private SessionFactory sessionFactory;

  /***
   * 获取标签列表
   * */
  public List<TypeBean> selectAllTypesDao() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM TypeEntity";
    Query query = session.createQuery(Hql);
    List<TypeEntity> typeEntities = query.list();
    List<TypeBean> typeBeans = new ArrayList<TypeBean>();
    if(typeEntities == null){
      return typeBeans;
    }
    for(int i=0;i<typeEntities.size();i++){
      TypeBean typeBean = new TypeBean();
      typeBean = CommonUtil.changeTypeEntityToTypeBean(typeEntities.get(i));
      typeBeans.add(typeBean);
    }
    return typeBeans;
  }

  /**
   * 根据typeId获取标签
   * */
  public TypeBean selectTypeByTypeIdDao(int typeId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM TypeEntity WHERE typeId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, typeId);
    TypeEntity typeEntity = (TypeEntity) query.uniqueResult();
    TypeBean typeBean = new TypeBean();
    if(typeEntity != null){
      typeBean = CommonUtil.changeTypeEntityToTypeBean(typeEntity);
    }
    return typeBean;
  }

  /***
   * 根据标签名获取标签
   * */
  public TypeBean selectTypeByTypeNameDao(String typeName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM TypeEntity WHERE typeName=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, typeName);
    TypeEntity typeEntity = (TypeEntity) query.uniqueResult();
    TypeBean typeBean = new TypeBean();
    if(typeEntity != null){
      typeBean = CommonUtil.changeTypeEntityToTypeBean(typeEntity);
    }
    return typeBean;
  }
}
