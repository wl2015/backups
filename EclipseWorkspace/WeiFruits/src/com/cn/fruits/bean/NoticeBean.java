package com.cn.fruits.bean;

/**
 * 公告
 * */
public class NoticeBean {
  public int noticeId;//1、公告Id
  
  public String noticeContent;//2、公告内容
  
  public String createTime;//3、创建时间

  public int getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(int noticeId) {
    this.noticeId = noticeId;
  }

  public String getNoticeContent() {
    return noticeContent;
  }

  public void setNoticeContent(String noticeContent) {
    this.noticeContent = noticeContent;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}
