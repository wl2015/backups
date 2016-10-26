package com.yc.service;

import java.util.List;

import com.yc.bean.DishList;
import com.yc.bean.PushOrder;
import com.yc.bean.msureOrder;

public interface PushOrderService {
	 public List<PushOrder> selectPushOrder(int  merchantId) throws Exception;//商户获取可抢订单
	 public List<DishList> getDishlistByO_Id(int  orderId) throws Exception;//商户获取可抢订单
	 public boolean mSureOrder(List<msureOrder> msureOrders, List<Integer> goIdList) throws Exception;//商家确认抢单
	 public int getPushOrderCount(int  merchantId) throws Exception;//通过商家id获取可抢订单数量
	 
}
