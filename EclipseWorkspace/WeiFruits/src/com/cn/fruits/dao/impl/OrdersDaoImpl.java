package com.cn.fruits.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.dao.OrdersDao;
import com.cn.fruits.entity.OrdersEntity;
import com.cn.fruits.util.CommonUtil;
import com.cn.fruits.util.Constants;

@Repository
public class OrdersDaoImpl implements OrdersDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 保存订单并返回订单ID
   * 失败了返回0
   * */
  public int saveNewOrdersAndReturnOrdersIdDao(OrdersEntity ordersEntity) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(ordersEntity);
    if(serializable == null){
      return 0;
    }
    return ordersEntity.getOrderId();
  }

  /**
   * 根据页码获取已完成订单列表
   * */
  public List<OrdersBean> selectFinishedOrdersByPageNumDao(int pageNum) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM OrdersEntity WHERE orderStatus = 2";
    Query query = session.createQuery(Hql);
    query.setFirstResult((pageNum-1) * Constants.PAGE_NUMBER);
    query.setMaxResults(Constants.PAGE_NUMBER);
    List<OrdersEntity> ordersEntities = query.list();
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    for(int i=0;i<ordersEntities.size();i++){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = CommonUtil.changeOrdersEntityIntoOrdersBean(ordersEntities.get(i));
      ordersBeans.add(ordersBean);
    }
    return ordersBeans;
  }

  /**
   * 根据订单Id删除订单
   * */
  public boolean deleteOrderbyOrderIdDao(int orderId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "DELETE FROM OrdersEntity WHERE orderId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, orderId);
    int result = query.executeUpdate();
    if(result == 0 ){
      return false;
    }
    //删除订单所属的菜单
    else{
      String Hql2 = "DELETE FROM FruitListEntity WHERE orderId=?";
      Session session2 = sessionFactory.getCurrentSession();
      Query query2 = session2.createQuery(Hql2);
      query2.setParameter(0, orderId);
      int result2 = query2.executeUpdate();
      if(result2 == 0){
        return false;
      }
    }
    return true;
  }
  
  /**
   * 根据订单Id查询订单
   * */
  public OrdersBean selectOrderByOrderIdDao(int orderId)throws Exception{
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM OrdersEntity WHERE orderId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, orderId);
    OrdersEntity ordersEntity = (OrdersEntity) query.uniqueResult();
    OrdersBean ordersBean = new OrdersBean();
    ordersBean = CommonUtil.changeOrdersEntityIntoOrdersBean(ordersEntity);
    return ordersBean;
  }
  
  /**
   * 根据页码获取未完成订单列表
   * */
  public List<OrdersBean> selectUnFinishedOrdersByPageNumDao(int pageNum) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM OrdersEntity WHERE orderStatus = 1 OR orderStatus = 0";
    Query query = session.createQuery(Hql);
    query.setFirstResult((pageNum-1) * Constants.PAGE_NUMBER);
    query.setMaxResults(Constants.PAGE_NUMBER);
    List<OrdersEntity> ordersEntities = query.list();
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    for(int i=0;i<ordersEntities.size();i++){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = CommonUtil.changeOrdersEntityIntoOrdersBean(ordersEntities.get(i));
      ordersBeans.add(ordersBean);
    }
    return ordersBeans;
  }

  /**
   * 接受订单
   * */
  public boolean updateOrderStatusToAcceptByOrderIdDao(int orderId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE OrdersEntity O set O.orderStatus = 1 WHERE O.orderId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, orderId);
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }
  
  /**
   * 完成订单
   * */
  public boolean updateOrderStatusToFinishByOrderIdDao(int orderId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE OrdersEntity O set O.orderStatus = 2 WHERE O.orderId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, orderId);
    int result = query.executeUpdate();
    if(result == 0){
      return false;
    }
    return true;
  }

  /**
   * 根据用户Id获取未完成订单
   * */
  public List<OrdersBean> selectUnFinishOrdersByUserIdDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM OrdersEntity WHERE orderStatus != 2 AND userId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    List<OrdersEntity> ordersEntities = new ArrayList<OrdersEntity>();
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    ordersEntities = query.list();
    for(int i=0;i<ordersEntities.size();i++){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = CommonUtil.changeOrdersEntityIntoOrdersBean(
          ordersEntities.get(i));
      ordersBeans.add(ordersBean);
    }
    return ordersBeans;
  }
  
  /**
   * 根据用户Id获取未完成订单
   * */
  public List<OrdersBean> selectFinishOrdersByUserIdDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM OrdersEntity WHERE orderStatus = 2 AND userId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    List<OrdersEntity> ordersEntities = new ArrayList<OrdersEntity>();
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    ordersEntities = query.list();
    for(int i=0;i<ordersEntities.size();i++){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = CommonUtil.changeOrdersEntityIntoOrdersBean(
          ordersEntities.get(i));
      ordersBeans.add(ordersBean);
    }
    return ordersBeans;
  }

  /**
   *  获取还未接受订单的数量
   * */
  public int selectUnAceeptOrdersCountDao() throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM OrdersEntity WHERE orderStatus=0";
    Query query = session.createQuery(Hql);
    List<OrdersEntity> ordersEntities = query.list();
    int count = 0;
    if(ordersEntities != null){
      count = ordersEntities.size();
    }
    return count;
  }
}
