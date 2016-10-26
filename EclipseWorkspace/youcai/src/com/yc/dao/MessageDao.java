package com.yc.dao;

import java.util.List;

import com.yc.bean.Message;

public interface MessageDao {
  public List<Message> getMessageForMerchant(int merchantid) throws Exception;//商家获取站内信
  public int flagMessage(List<Integer> messageIds) throws Exception;//商家标记已读过的站内信 
  public int getMessageCount(int merchantid) throws Exception;//通过商家id获取未读过的站内信数量
  
}
