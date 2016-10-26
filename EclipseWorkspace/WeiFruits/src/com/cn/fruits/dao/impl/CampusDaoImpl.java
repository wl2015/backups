package com.cn.fruits.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.dao.CampusDao;
import com.cn.fruits.entity.CampusEntity;

@Repository
public class CampusDaoImpl implements CampusDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   *  保存新的校区
   * */
  public boolean insertNewCampusDao(String campusName) throws Exception {
    CampusEntity campusEntity = new CampusEntity();
    campusEntity.setCampusName(campusName);
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(campusEntity);
    if(serializable != null){
      return true;
    }
    return false;
  }

  /**
   * 查询出所有校区
   * */
  public List<CampusBean> selectAllCampusesDao() throws Exception {
    List<CampusEntity> campusEntities = new ArrayList<CampusEntity>();
    List<CampusBean> campusBeans = new ArrayList<CampusBean>();
    Session session = sessionFactory.getCurrentSession();
    String Hql = "From CampusEntity";
    Query query = session.createQuery(Hql);
    campusEntities = query.list();
    for(int i=0;i<campusEntities.size();i++){
      CampusBean campusBean = new CampusBean();
      campusBean.setCampusId(campusEntities.get(i).getCampusId());
      campusBean.setCampusName(campusEntities.get(i).getCampusName());
      campusBeans.add(campusBean);
    }
    return campusBeans;
  }
  
  /**
   * 根据campusId删除校区
   * */
  public boolean deleteCampusByCampusIdDao(int campusId)throws Exception{
    Session session = sessionFactory.getCurrentSession();
    String Hql = "DELETE FROM CampusEntity C WHERE C.campusId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, campusId);
    int result = query.executeUpdate();
    if(result==0){
      return false;
    }
    //删除相应的校区的宿舍
    else{
      String Hql2 = "DELETE FROM DormitoryEntity WHERE campusId=?";
      Session session2 = sessionFactory.getCurrentSession();
      Query query2 = session2.createQuery(Hql2);
      query2.setParameter(0, campusId);
      int result2 = query2.executeUpdate();
      if(result2 == 0){
        return false;
      }
    }
    return true;
  }

  /**
   * 修改校区的名称
   * */
  public boolean updateCampusNameDao(CampusEntity campusEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE CampusEntity C set C.campusName = ? WHERE C.campusId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, campusEntity.getCampusName());
    query.setParameter(1, campusEntity.getCampusId());
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }

  /**
   * 根据校区Id查询校区名
   * */
  public String selectCampusNameByCampusIdDao(int campusId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "SELECT C.campusName FROM CampusEntity C WHERE C.campusId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, campusId);
    String campusName = (String) query.uniqueResult();
    return campusName;
  }
  
}
