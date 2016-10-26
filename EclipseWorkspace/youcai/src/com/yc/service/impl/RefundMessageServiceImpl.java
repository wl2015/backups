package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.RefundMessage;
import com.yc.dao.PushOrderDao;
import com.yc.dao.RefundMessageDao;
import com.yc.service.RefundMessageService;
@Service
@Transactional
public class RefundMessageServiceImpl implements RefundMessageService {

  @Autowired
  private RefundMessageDao refundMessageDao;
  /**
   * 查找还未读过的退单消息
   * @param merchantid:商家id
   * @return 可以查看的退单消息列表
   */
  @Override
  public List<RefundMessage> getNotReadRefund(int merchantid) throws Exception {
    return refundMessageDao.getNotReadRefund(merchantid);
  }

  /**
   * 批量标记退单消息
   * @param refundidList：待标记的退单消息
   */
  @Override
  public boolean flagRefundMessage(List<Integer> refundidList) throws Exception {
     int isFlag = refundMessageDao.flagRefundMessage(refundidList);
     if(isFlag > 0){
       return true;
     }
     else{
       return false;
     }    
  }

}
