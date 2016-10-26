package com.cn.fruits.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.cn.fruits.bean.FruitListBean;
import com.cn.fruits.dao.FruitListDao;
import com.cn.fruits.entity.FruitListEntity;
import com.cn.fruits.util.CommonUtil;

@Repository
public class FruitListDaoImpl implements FruitListDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 保存菜单列表
   * 成功返回true，失败返回false
   * */
  public boolean saveFruitListDao(List<FruitListEntity> fruitListEntities) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    for(int i=0;i<fruitListEntities.size();i++){
      Serializable serializable = session.save(fruitListEntities.get(i));
      if(serializable == null){
        return false;
      }
    }
    return true;
  }

  /**
   * 根据orderId获取订单菜品列表
   * */
  public List<FruitListBean> selectFruitListsByOrderIdDao(int orderId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM FruitListEntity WHERE orderId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, orderId);
    List<FruitListEntity> fruitListEntities = query.list();
    List<FruitListBean> fruitListBeans = new ArrayList<FruitListBean>();
    for(int i=0;i<fruitListEntities.size();i++){
      FruitListBean fruitListBean = new FruitListBean();
      fruitListBean = CommonUtil.changeFruitListEntityIntoFruitListBean(
          fruitListEntities.get(i));
      fruitListBeans.add(fruitListBean);
    }
    return fruitListBeans;
  }
}
