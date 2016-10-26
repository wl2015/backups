package com.cn.freemall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="message")
@DynamicInsert
@DynamicUpdate
public class MessageEntity {
  @Id
  @GeneratedValue
  @Column(name="message_id")
  public int messageId;//1、消息ID
  
  @Column(name="content")
  public String content;//2、消息内容
  
  @Column(name="send_id")
  public int sendId;//3、发消息用户ID
  
  @Column(name="receive_id")
  public int receiveId;//4、收消息用户ID
  
  @Column(name="time")
  public String time;//5、创建消息的时间
  
  @Column(name="first")
  public int first;//6、1表示第一条消息
  
  @Column(name="state")
  public int state;//7、0表示新消息，1表示已看的消息

  public int getMessageId() {
    return messageId;
  }

  public void setMessageId(int messageId) {
    this.messageId = messageId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getSendId() {
    return sendId;
  }

  public void setSendId(int sendId) {
    this.sendId = sendId;
  }

  public int getReceiveId() {
    return receiveId;
  }

  public void setReceiveId(int receiveId) {
    this.receiveId = receiveId;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getFirst() {
    return first;
  }

  public void setFirst(int first) {
    this.first = first;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

}
