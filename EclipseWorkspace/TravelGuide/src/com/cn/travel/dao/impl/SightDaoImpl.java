package com.cn.travel.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.travel.bean.SightBean;
import com.cn.travel.dao.SightDao;
import com.cn.travel.entity.SightEntity;
import com.cn.travel.util.CommonUtil;

@Repository
public class SightDaoImpl implements SightDao {
  @Inject
  private SessionFactory sessionFactory;

  @Override
  public boolean saveNewSightDao(SightEntity sightEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(sightEntity);
    if(serializable == null){
      return false;
    }
    return true;
  }

  /**
   * 根据district查询景点
   * */
  public List<SightBean> getSightByDistrictDao(String district) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM SightEntity WHERE district=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, district);
    List<SightEntity> sightEntities = query.list();
    List<SightBean> sightBeans = new ArrayList<SightBean>();
    if(sightEntities != null){
      for(int i=0;i<sightEntities.size();i++){
        SightBean sightBean = new SightBean();
        sightBean = CommonUtil.changeSightEntityToSightBean(sightEntities.get(i));
        sightBeans.add(sightBean);
      }
    }
    return sightBeans;
  }

  /**
   * 根据标签Id查询景点信息
   * */
  public SightBean selectSightsBySightIdDao(int sightId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM SightEntity WHERE sightId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, sightId);
    SightEntity sightEntity = (SightEntity) query.uniqueResult();
    SightBean sightBean = new SightBean();
    if(sightEntity != null){
      sightBean = CommonUtil.changeSightEntityToSightBean(sightEntity);
    }
    return sightBean;
  }

  /**
   * 获取全部景点
   * */
  public List<SightBean> selectSightsDao() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM SightEntity";
    Query query = session.createQuery(Hql);
    List<SightEntity> sightEntities = query.list();
    List<SightBean> sightBeans = new ArrayList<SightBean>();
    for(int i=0;i<sightEntities.size();i++){
      SightBean sightBean = new SightBean();
      sightBean = CommonUtil.changeSightEntityToSightBean(sightEntities.get(i));
      sightBeans.add(sightBean);
    }
    return sightBeans;
  }

  /**
   * 修改地点
   * */
  public boolean updateAddressAndDistrictDao(SightEntity sightEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE SightEntity set address = ?, district = ? WHERE sightId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, sightEntity.getAddress());
    query.setParameter(1, sightEntity.getDistrict());
    query.setParameter(2, sightEntity.getSightId());
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }

  /**
   * 根据景点名查询景点
   * */
  public List<SightBean> selectSightsBySightNameDao(String sightName) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM SightEntity WHERE sightName like ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, "%"+sightName+"%");
    List<SightEntity> sightEntities = query.list();
    List<SightBean> sightBeans = new ArrayList<SightBean>();
    for(int i=0;i<sightEntities.size();i++){
      SightBean sightBean = new SightBean();
      sightBean = CommonUtil.changeSightEntityToSightBean(sightEntities.get(i));
      sightBeans.add(sightBean);
    }
    return sightBeans;
  }
}
