package com.cn.fruits.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.dao.NoticeDao;
import com.cn.fruits.entity.NoticeEntity;
import com.cn.fruits.util.DateUtil;

@Repository
public class NoticeDaoImpl implements NoticeDao {
  @Inject
  private SessionFactory sessionFactory;
  
  /***
   * 保存新的公告
   * */
  public boolean insertNewNoticeDao(NoticeEntity noticeEntity) throws Exception{
    noticeEntity.setCreateTime(DateUtil.getNowTime());
    Session session = sessionFactory.getCurrentSession();
    Serializable result = session.save(noticeEntity);
    if(result != null){
      return true;
    }
    return false;
  }
  
  /**
   * 查询出最新的公告
   * */
  public NoticeBean selectNewestNoticeDao() throws Exception{
    NoticeBean noticeBean = new NoticeBean();
    NoticeEntity noticeEntity = new NoticeEntity();
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM NoticeEntity N ORDER BY N.createTime DESC";
    Query query = session.createQuery(Hql);
    query.setFirstResult(0);
    query.setMaxResults(1);
    noticeEntity = (NoticeEntity) query.uniqueResult();
    noticeBean.setNoticeId(noticeEntity.getNoticeId());
    noticeBean.setNoticeContent(noticeEntity.getNoticeContent());
    noticeBean.setCreateTime(noticeEntity.getCreateTime());
    return noticeBean;
  }
  
  /**
   * 查询所有公告
   * */
  public List<NoticeBean> selectAllNoticesDao() throws Exception{
    List<NoticeBean> noticeBeans = new ArrayList<NoticeBean>();
    List<NoticeEntity> noticeEntities = new ArrayList<NoticeEntity>();
    Session session = sessionFactory.getCurrentSession();
    String Hql = "FROM NoticeEntity N ORDER BY N.createTime DESC";
    Query query = session.createQuery(Hql);
    noticeEntities = query.list();
    for(int i=0;i<noticeEntities.size();i++){
      NoticeBean noticeBean = new NoticeBean();
      noticeBean.setNoticeId(noticeEntities.get(i).getNoticeId());
      noticeBean.setNoticeContent(noticeEntities.get(i).getNoticeContent());
      noticeBean.setCreateTime(noticeEntities.get(i).getCreateTime());
      noticeBeans.add(noticeBean);
    }
    return noticeBeans;
  }

  /**
   * 根据noticeId删除notice
   * */
  public boolean deleteNoticeByNoticeIdDao(int noticeId) throws Exception {
    Session session = sessionFactory.getCurrentSession();
    String Hql = "DELETE FROM NoticeEntity N WHERE N.noticeId=?";
    Query query = session.createQuery(Hql);
    query.setParameter(0, noticeId);
    int result = query.executeUpdate();
    System.out.println("结果:"+result);
    if(result == 0){
      return false;
    }
    return true;
  }
}
