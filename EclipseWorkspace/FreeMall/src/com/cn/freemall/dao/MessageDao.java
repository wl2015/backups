package com.cn.freemall.dao;

import java.util.List;

import com.cn.freemall.bean.MessageBean;
import com.cn.freemall.entity.MessageEntity;

public interface MessageDao {
  public boolean insertNewMessageDao(MessageEntity messageEntity)throws Exception;

  public List<MessageBean> selectUnReadMessageDao(int userId)throws Exception;
  
  public List<MessageBean> selectReadMessageDao(int userId)throws Exception;
  
  public List<MessageBean> selectSendMessageDao(int userId)throws Exception;

  public MessageBean selectMessageByMessageIdDao(int messageId)throws Exception;

  public boolean updateMessageToReadedByMessageIdDao(int messageId)throws Exception;
}
