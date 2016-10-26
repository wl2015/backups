package com.yc.service;

import java.util.List;

import com.yc.bean.RefundMessage;

public interface RefundMessageService {
  public List<RefundMessage> getNotReadRefund(int merchantid)throws Exception;//商家查询还未读过的退单消息 
  public boolean flagRefundMessage(List<Integer> refundidList) throws Exception;//商家确认查看退单信息
}
