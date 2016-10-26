package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;







import com.yc.bean.Order;
import com.yc.dao.OrdersDao;
import com.yc.service.OrdersService;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	  private OrdersDao ordersDao;
	/*
	 * 商家查看历史订单
	 * @param merchantid:商家id
	 * @return List<Orders>商家的历史订单
	 */
	public List<Order> mSelectHistoryOrders(int merchantid, int startPosition, int pageSize)  throws Exception{
		return ordersDao.mSelectHistoryOrders(merchantid, startPosition, pageSize);
	}
	/*
   * 查询历史订单数量
   * @param merchantid:商家id
   * @return List<Orders>商家的历史订单
   */
	public int selectHistoryOrderCount(int merchantid)throws Exception{
	  return ordersDao.selectHistoryOrderCount(merchantid);
	}
	
	/*
	 * 商家批量删除历史订单
	 * @param orderids:历史订单id集合
	 * @return int 删除历史订单的条数
	 */
	public int deleteOrderM(List<Integer> orderids) throws Exception{
		return ordersDao.deleteOrderM(orderids);
	}
	
	/*
	 * 商家查看未完成订单
	 * @param merchantid:商家id
	 * @return List<Orders>商家的未完成订单
	 */
	public List<Order> selectProceOrderM(int merchantid) throws Exception{
		return ordersDao.selectProceOrderM(merchantid);
	}

  @Override
  public int getRefundMessageCount(int merchantid) throws Exception {
    return ordersDao.getRefundMessageCount(merchantid);
  }
}
