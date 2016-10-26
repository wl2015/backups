package com.cn.freemall.dao.impl;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.freemall.dao.RelationDao;
import com.cn.freemall.entity.RelationEntity;

@Repository
public class RelationDaoImpl implements RelationDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 保存新的关系表
   * */
  public boolean insertNewReationDao(RelationEntity relationEntity) throws Exception {
    Session session =sessionFactory.getCurrentSession();
    Serializable serializable = session.save(relationEntity);
    if(serializable != null){
      return true;
    }
    return false;
  }

  /**
   * 修改关系信息
   * */
  public boolean updateRelationDao(RelationEntity relationEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE RelationEntity set typeId=? WHERE productId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, relationEntity.getTypeId());
    query.setParameter(1, relationEntity.getProductId());
    int result = query.executeUpdate();
    if(result != 1){
      return false;
    }
    return true;
  }
}
