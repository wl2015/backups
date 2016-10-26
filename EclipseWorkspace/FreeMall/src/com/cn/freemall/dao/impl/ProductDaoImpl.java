package com.cn.freemall.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.freemall.bean.ProductBean;
import com.cn.freemall.bean.UserBean;
import com.cn.freemall.dao.ProductDao;
import com.cn.freemall.entity.ProductEntity;
import com.cn.freemall.util.CommonUtil;
import com.cn.freemall.util.DateUtil;

@Repository
public class ProductDaoImpl implements ProductDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 获取所有上架的商品列表
   * */
  public List<ProductBean> selectAllProductDao() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM ProductEntity WHERE state=0 ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    List<ProductEntity> productEntities = query.list();
    List<ProductBean> productBeans = new ArrayList<ProductBean>();
    for(int i=0; i < productEntities.size();i++){
      ProductBean productBean = new ProductBean();
      productBean = CommonUtil.changeProductEntityToProductBean(
          productEntities.get(i));
      productBeans.add(productBean);
    }
    return productBeans;
  }

  /**
   * 保存新的商品
   * */
  public int insertNewProductDao(ProductEntity productEntity) throws Exception {
    productEntity.setTime(DateUtil.getNowTime());
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(productEntity);
    if(serializable != null){
      return productEntity.getProductId();
    }
    return 0;
  }

  /**
   * 根据类型Id查询上架商品
   * */
  public List<ProductBean> selectProductsByTypeIdDao(int typeId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM ProductEntity WHERE typeId = ? AND state=0 ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    query.setParameter(0, typeId);
    List<ProductEntity> productEntities = query.list();
    List<ProductBean> productBeans = new ArrayList<ProductBean>();
    for(int i=0; i < productEntities.size();i++){
      ProductBean productBean = new ProductBean();
      productBean = CommonUtil.changeProductEntityToProductBean(
          productEntities.get(i));
      productBeans.add(productBean);
    }
    return productBeans;
  }

  /**
   * 修改产品
   * */
  public boolean updateProductInfoDao(ProductEntity productEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE ProductEntity set productName=?, place=?, "
        + "price=?, intro=?, pic=?, typeId=? WHERE productId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, productEntity.getProductName());
    query.setParameter(1, productEntity.getPlace());
    query.setParameter(2, productEntity.getPrice());
    query.setParameter(3, productEntity.getIntro());
    query.setParameter(4, productEntity.getPic());
    query.setParameter(5, productEntity.getTypeId());
    query.setParameter(6, productEntity.getProductId());
    int result = query.executeUpdate();
    if(result != 1){
      return false;
    }
    return true;
  }
  
  /**
   * 根据userId查询商品列表
   * */
  public List<ProductBean> selectProductsByUserIdDao(int userId)throws Exception{
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM ProductEntity WHERE userId = ? ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    List<ProductEntity> productEntities = query.list();
    List<ProductBean> productBeans = new ArrayList<ProductBean>();
    for(int i=0; i < productEntities.size();i++){
      ProductBean productBean = new ProductBean();
      productBean = CommonUtil.changeProductEntityToProductBean(
          productEntities.get(i));
      productBeans.add(productBean);
    }
    return productBeans;
  }

  /**
   * 根据productId查询商品
   * */
  public ProductBean selectProductByProductIdDao(int productId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM ProductEntity WHERE productId = ? ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    query.setParameter(0, productId);
    ProductEntity productEntity = (ProductEntity) query.uniqueResult();
    ProductBean productBean = new ProductBean();
    if(productEntity != null){
      productBean = CommonUtil.changeProductEntityToProductBean(productEntity);
    }
    return productBean;
  }

  /**
   * 修改商品的状态
   * */
  public boolean updateStateByProductIdDao(int productId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE ProductEntity set state=2 WHERE productId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, productId);
    int result = query.executeUpdate();
    if(result != 1){
      return false;
    }
    return true;
  }
}
