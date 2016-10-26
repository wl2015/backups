package com.yc.service;

import java.util.List;

import com.yc.bean.Order;

public interface OrdersService {
	public List<Order> mSelectHistoryOrders(int merchantid, int startPosition, int pageSize) throws Exception;//商家查看历史订单
	public int selectHistoryOrderCount(int merchantid)throws Exception;//查询历史订单数量
	public int deleteOrderM(List<Integer> orderids) throws Exception;//商家删除历史订单
	public List<Order> selectProceOrderM(int merchantid) throws Exception;//商家查看未完成订单
	public int getRefundMessageCount(int merchantid) throws Exception;//通过商家id获取未读过的退单信息数量
}
