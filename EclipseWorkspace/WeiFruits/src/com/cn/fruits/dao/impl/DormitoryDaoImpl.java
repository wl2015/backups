package com.cn.fruits.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.dao.DormitoryDao;
import com.cn.fruits.entity.DormitoryEntity;

@Repository
public class DormitoryDaoImpl implements DormitoryDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 添加新的宿舍楼
   * */
  public boolean insertNewDormitoryDao(DormitoryEntity dormitoryEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(dormitoryEntity);
    if(serializable != null){
      return true;
    }
    return false;
  }

  /**
   * 查询所有宿舍楼
   * */
  public List<DormitoryBean> seletDormitoriesDao() throws Exception {
    List<DormitoryEntity> dormitoryEntities = new ArrayList<DormitoryEntity>();
    List<DormitoryBean> dormitoryBeans = new ArrayList<DormitoryBean>();
    Session session = sessionFactory.getCurrentSession();
    String Hql= "FROM DormitoryEntity";
    Query query = session.createQuery(Hql);
    dormitoryEntities = query.list();
    for(int i=0;i<dormitoryEntities.size();i++){
      DormitoryBean dormitoryBean = new DormitoryBean();
      dormitoryBean.setCampusId(dormitoryEntities.get(i).getCampusId());
      dormitoryBean.setDormitoryId(dormitoryEntities.get(i).getDormitoryId());
      dormitoryBean.setDormitoryName(dormitoryEntities.get(i).getDormitoryName());
      dormitoryBeans.add(dormitoryBean);
    }
    return dormitoryBeans;
  }

  /**
   * 根据宿舍楼ID删除宿舍楼
   * */
  public boolean deleteDormitoryByDormitoryIdDao(int dormitoryId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "DELETE FROM DormitoryEntity D WHERE D.dormitoryId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, dormitoryId);
    int result = query.executeUpdate();
    if(result==0){
      return false;
    }
    return true;  }

  /**
   * 修改宿舍楼名
   * */
  public boolean updateDormitoryNameDao(DormitoryEntity dormitoryEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE DormitoryEntity D set D.dormitoryName = ? WHERE D.dormitoryId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, dormitoryEntity.getDormitoryName());
    query.setParameter(1, dormitoryEntity.getDormitoryId());
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }

  /**
   * 通过校区ID查询出该校区的所有宿舍楼
   * */
  public List<DormitoryBean> selectDormitoriesDaoByCampusIdDao(int campusId) throws Exception {
    List<DormitoryEntity> dormitoryEntities = new ArrayList<DormitoryEntity>();
    List<DormitoryBean> dormitoryBeans = new ArrayList<DormitoryBean>();
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM DormitoryEntity D WHERE D.campusId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, campusId);
    dormitoryEntities = query.list();
    for(int i=0;i<dormitoryEntities.size();i++){
      DormitoryBean dormitoryBean = new DormitoryBean();
      dormitoryBean.setDormitoryId(dormitoryEntities.get(i).getDormitoryId());
      dormitoryBean.setDormitoryName(dormitoryEntities.get(i).getDormitoryName());
      dormitoryBean.setCampusId(dormitoryEntities.get(i).getCampusId());
      dormitoryBeans.add(dormitoryBean);
    }
    return dormitoryBeans;
  }
}
