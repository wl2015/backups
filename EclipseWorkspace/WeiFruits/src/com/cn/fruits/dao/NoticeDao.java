package com.cn.fruits.dao;

import java.util.List;

import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.entity.NoticeEntity;

public interface NoticeDao {
  public boolean insertNewNoticeDao(NoticeEntity noticeEntity) throws Exception;
  
  public NoticeBean selectNewestNoticeDao() throws Exception;
  
  public List<NoticeBean> selectAllNoticesDao() throws Exception;
  
  public boolean deleteNoticeByNoticeIdDao(int noticeId) throws Exception;
}
