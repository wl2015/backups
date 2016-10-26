package com.cn.fruits.dao;

import java.util.List;

import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.entity.OrdersEntity;

public interface OrdersDao {
  public int saveNewOrdersAndReturnOrdersIdDao(OrdersEntity ordersEntity) throws Exception;
  
  public List<OrdersBean> selectFinishedOrdersByPageNumDao(int pageNum)throws Exception;

  public boolean deleteOrderbyOrderIdDao(int orderId)throws Exception;
  
  public OrdersBean selectOrderByOrderIdDao(int orderId)throws Exception;
  
  public List<OrdersBean> selectUnFinishedOrdersByPageNumDao(int pageNum) throws Exception;

  public boolean updateOrderStatusToAcceptByOrderIdDao(int orderId)throws Exception;
  
  public boolean updateOrderStatusToFinishByOrderIdDao(int orderId)throws Exception;

  public List<OrdersBean> selectUnFinishOrdersByUserIdDao(int userId)throws Exception;
  
  public List<OrdersBean> selectFinishOrdersByUserIdDao(int userId)throws Exception;

  public int selectUnAceeptOrdersCountDao()throws Exception;
}
