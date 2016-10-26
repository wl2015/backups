package com.cn.fruits.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.dao.FruitDao;
import com.cn.fruits.entity.FruitEntity;
import com.cn.fruits.util.CommonUtil;
import com.cn.fruits.util.Constants;
import com.cn.fruits.util.DateUtil;

@Repository
public class FruitDaoImpl implements FruitDao {
  @Inject
  private SessionFactory sessionFactory;
  /**
   * 保存新的水果，保存成功返回true，失败返回false
   * @param FruitBean
   * @return true or false
   * */
  public boolean saveFruitDao(FruitEntity fruitEntity) throws Exception{
    Session session = sessionFactory.getCurrentSession();
    fruitEntity.setCreateTime(DateUtil.getNowTime());
    Serializable result = session.save(fruitEntity);
    if(result!=null){
      return true;
    }
    return false;
  }
  
  /**
   * 查询所有水果
   * */
  public List<FruitBean> selectAllFruitsDao() throws Exception{
    List<FruitEntity> fruitEntities = new ArrayList<FruitEntity>();
    List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
    String Hql = "FROM FruitEntity F WHERE F.fruitState = 0";
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(Hql);
    fruitEntities = query.list();
    for(int i=0; i < fruitEntities.size();i++){
      FruitBean fruitBean = new FruitBean();
      fruitBean = CommonUtil.changeFruitEntityIntoFruitBean(
          fruitEntities.get(i));
      fruitBeans.add(fruitBean);
    }
    return fruitBeans;
  }

  /**
   * 根据水果ID删除水果
   * 删除水果是把水果的状态改为下架
   * */
  public boolean deleteFruitByFruitIdDao(int fruitId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE FruitEntity F set F.fruitState = 1 WHERE F.fruitId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, fruitId);
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }

  /**
   * 根据FruitId查询Fruit信息
   * */
  public FruitBean selectFruitByFruitIdDao(int fruitId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "from FruitEntity F where F.fruitId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, fruitId);
    FruitEntity fruitEntity = (FruitEntity) query.uniqueResult();
    if(fruitEntity == null){
      return null;
    }
    FruitBean fruitBean = new FruitBean();
    fruitBean = CommonUtil.changeFruitEntityIntoFruitBean(fruitEntity);
    return fruitBean;
  }

  /**
   * 根据fruitId修改fruit信息
   * */
  public boolean updateFruitInfoByFruitIdDao(FruitBean fruitBean) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    System.out.println(fruitBean.getFruitName()+fruitBean.getFruitId()+fruitBean.getFruitPic()+
        fruitBean.getFruitDetail()+fruitBean.getFruitPrice()+fruitBean.getFruitIntr());
    String Hql = "UPDATE FruitEntity F set F.fruitName=?, F.fruitPic=?, "
        + "F.fruitIntr=?,  F.fruitPrice=?, F.fruitDetail=? WHERE F.fruitId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, fruitBean.getFruitName());
    query.setParameter(1, fruitBean.getFruitPic());
    query.setParameter(2, fruitBean.getFruitIntr());
    query.setParameter(3, fruitBean.getFruitPrice());
    query.setParameter(4, fruitBean.getFruitDetail());
    query.setParameter(5, fruitBean.getFruitId());
    int result = query.executeUpdate();
    if(result != 1){
      return false;
    }
    return true;
  }

  /***
   * 根据页码查询菜单列表
   * */
  public List<FruitBean> selectFruitsByPageNumDao(int pageNum) throws Exception {
    List<FruitEntity> fruitEntities = new ArrayList<FruitEntity>();
    List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
    String Hql = "FROM FruitEntity F WHERE F.fruitState = 0";
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(Hql);
    query.setFirstResult((pageNum-1) * Constants.PAGE_NUMBER);
    query.setMaxResults(Constants.PAGE_NUMBER);
    fruitEntities = query.list();
    for(int i=0; i < fruitEntities.size();i++){
      FruitBean fruitBean = new FruitBean();
      fruitBean = CommonUtil.changeFruitEntityIntoFruitBean(
          fruitEntities.get(i));
      fruitBeans.add(fruitBean);
    }
    return fruitBeans;
  }
}
