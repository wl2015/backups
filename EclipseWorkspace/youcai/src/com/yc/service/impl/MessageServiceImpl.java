package com.yc.service.impl;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Message;
import com.yc.dao.MessageDao;
import com.yc.service.MessageService;
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
  @Autowired
  private MessageDao messageDao;
  /**
   * 商家获取还未读过的站内信
   * @param merchantid:商家id
   * @return List<Message>：返回站内信列表
   */
  @Override
  public List<Message> getMessageForMerchant(int merchantid) throws Exception {
   return messageDao.getMessageForMerchant(merchantid);
  }
  
  /**
   * 商家将站内信标记为已读
   * @param messageIds:站内信id
   * @return 返回操作成功与否
   */
  @Override
  public boolean flagMessage(List<Integer> messageIds) throws Exception {
    int updateDataNumber = messageDao.flagMessage(messageIds);
    boolean result = false;
    if(updateDataNumber > 0){
      result = true;
    }   
    return result;
  }
  
  /**
   * 商家获取还未读过的站内信数量
   * @param merchantid:商家id
   * @return 返回站内信数量
   */
  @Override
  public int getMessageCount(int merchantid) throws Exception {
    return messageDao.getMessageCount(merchantid);
  }

  
}
