package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 公告
 * */
@Entity
@Table(name="notice")
@DynamicInsert
@DynamicUpdate
public class NoticeEntity {
  @Id
  @GeneratedValue
  @Column(name="notice_id")
  public int noticeId;//1、公告Id
  
  @Column(name="notice_content")
  public String noticeContent;//2、公告内容
  
  @Column(name="create_time")
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
