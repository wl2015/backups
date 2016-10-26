package com.yc.dao;

import java.util.List;

import com.yc.bean.RefundMessage;

public interface RefundMessageDao {
  public List<RefundMessage> getNotReadRefund(int merchantid)throws Exception;//商家查询还未读过的退单消息 
  public int flagRefundMessage(List<Integer> refundidList) throws Exception;//商家确认查看退单信息
  public void insertMessage(int oderId) throws Exception;//插入一条退单信息
}
