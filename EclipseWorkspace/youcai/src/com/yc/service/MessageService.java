package com.yc.service;

import java.util.List;

import com.yc.bean.Message;

public interface MessageService {
  public List<Message> getMessageForMerchant(int merchantid) throws Exception;//商家获取站内信
  public boolean flagMessage(List<Integer> messageIds) throws Exception;//商家标记已读过的站内信
  public int getMessageCount(int merchantid) throws Exception;//通过商家id获取未读过的站内信数量
}
