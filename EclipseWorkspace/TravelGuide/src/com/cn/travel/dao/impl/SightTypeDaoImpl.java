package com.cn.travel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.travel.bean.SightTypeRelationBean;
import com.cn.travel.dao.SightTypeRelationDao;
import com.cn.travel.entity.SightTypeRelationEntity;
import com.cn.travel.util.CommonUtil;

@Repository
public class SightTypeDaoImpl implements SightTypeRelationDao{
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 根据景点Id获取关系列表
   * */
  public List<SightTypeRelationBean> getRelationsBySightIdDao(int sightId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM SightTypeRelationEntity WHERE sightId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, sightId);
    List<SightTypeRelationEntity> relationEntities = query.list();
    List<SightTypeRelationBean> relationBeans = new ArrayList<SightTypeRelationBean>();
    if(relationEntities != null){
      for(int i=0;i<relationEntities.size();i++){
        SightTypeRelationBean relationBean = new SightTypeRelationBean();
        relationBean = CommonUtil.changeRelationEntityToRelationBean(
            relationEntities.get(i));
        relationBeans.add(relationBean);
      }
    }
    return relationBeans;
  }

  /**
   * 根据typeId获取关系列表
   * */
  public List<SightTypeRelationBean> selectRelationsByTypeIdDao(int typeId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM SightTypeRelationEntity WHERE typeId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, typeId);
    List<SightTypeRelationEntity> relationEntities = query.list();
    List<SightTypeRelationBean> relationBeans = new ArrayList<SightTypeRelationBean>();
    if(relationEntities != null){
      for(int i=0;i<relationEntities.size();i++){
        SightTypeRelationBean relationBean = new SightTypeRelationBean();
        relationBean = CommonUtil.changeRelationEntityToRelationBean(
            relationEntities.get(i));
        relationBeans.add(relationBean);
      }
    }
    return relationBeans;
  }
}
