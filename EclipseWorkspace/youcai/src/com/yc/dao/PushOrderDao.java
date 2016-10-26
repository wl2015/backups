package com.yc.dao;


import java.util.List;

import com.yc.bean.DishList;
import com.yc.bean.PushOrder;
import com.yc.bean.msureOrder;

public interface PushOrderDao {
	 public List<PushOrder> SelectPushOrder(int  merchantId) throws Exception;//商户获取可抢订单
	 public List<DishList> getDishlistByO_Id(int  orderId) throws Exception;//商户获取可抢订单
	 public int mSureOrder(List<msureOrder> msureOrders) throws Exception;//商家确认抢单
	 public int getPushOrderCount(int  merchantId) throws Exception;//通过商家id获取可抢订单数量
	 public int deleteNotSureOrder(List<Integer> goIds)throws Exception;//每天删除该天以前的推送消息
}
