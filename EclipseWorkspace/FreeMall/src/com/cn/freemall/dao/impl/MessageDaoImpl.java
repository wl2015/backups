package com.cn.freemall.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.freemall.bean.MessageBean;
import com.cn.freemall.bean.UserBean;
import com.cn.freemall.dao.MessageDao;
import com.cn.freemall.entity.MessageEntity;
import com.cn.freemall.entity.UserEntity;
import com.cn.freemall.util.CommonUtil;
import com.cn.freemall.util.DateUtil;

@Repository
public class MessageDaoImpl implements MessageDao {
  @Inject
  private SessionFactory sessionFactory;

  /**
   * 保存新的信息
   * */
  public boolean insertNewMessageDao(MessageEntity messageEntity) throws Exception {
    messageEntity.setTime(DateUtil.getNowTime());
    messageEntity.setFirst(0);
    Session session = sessionFactory.getCurrentSession();
    Serializable serializable = session.save(messageEntity);
    if(serializable != null){
      return true;
    }
    return false;
  }

  /**
   * 读取未读消息列表
   * */
  public List<MessageBean> selectUnReadMessageDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM MessageEntity WHERE state=0 AND receiveId=? ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    List<MessageEntity> messageEntities = query.list();
    List<MessageBean> messageBeans = new ArrayList<MessageBean>();
    for(int i=0; i < messageEntities.size();i++){
      MessageBean messageBean = new MessageBean();
      messageBean = CommonUtil.changeMessageEntityToMessageBean(
          messageEntities.get(i));
      messageBeans.add(messageBean);
    }
    return messageBeans;
  }

  /**
   * 读取已读消息列表
   * */
  public List<MessageBean> selectReadMessageDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM MessageEntity WHERE state=1 AND receiveId=? ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    List<MessageEntity> messageEntities = query.list();
    List<MessageBean> messageBeans = new ArrayList<MessageBean>();
    for(int i=0; i < messageEntities.size();i++){
      MessageBean messageBean = new MessageBean();
      messageBean = CommonUtil.changeMessageEntityToMessageBean(
          messageEntities.get(i));
      messageBeans.add(messageBean);
    }
    return messageBeans;
  }

  /**
   * 读取发送消息列表
   * */
  public List<MessageBean> selectSendMessageDao(int userId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM MessageEntity WHERE sendId=? ORDER BY time DESC";
    Query query = session.createQuery(Hql);
    query.setParameter(0, userId);
    List<MessageEntity> messageEntities = query.list();
    List<MessageBean> messageBeans = new ArrayList<MessageBean>();
    for(int i=0; i < messageEntities.size();i++){
      MessageBean messageBean = new MessageBean();
      messageBean = CommonUtil.changeMessageEntityToMessageBean(
          messageEntities.get(i));
      messageBeans.add(messageBean);
    }
    return messageBeans;
  }
  
  /**
   * 根据MessageId获取消息信息
   * */
  public MessageBean selectMessageByMessageIdDao(int messageId)throws Exception{
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM MessageEntity WHERE messageId = ?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, messageId);
    MessageEntity entity = (MessageEntity) query.uniqueResult();
    MessageBean messageBean = new MessageBean();
    if(entity != null){
      messageBean = CommonUtil.changeMessageEntityToMessageBean(entity);
    }
    return messageBean;
  }
  
  /**
   * 根据MessageId修改消息为已读消息信息
   * */
  public boolean updateMessageToReadedByMessageIdDao(int messageId)throws Exception{
    Session session = sessionFactory.getCurrentSession();
    String Hql = "UPDATE MessageEntity set state=1 WHERE messageId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, messageId);
    int result = query.executeUpdate();
    if(result != 1){
      return false;
    }
    return true;
  }
}
