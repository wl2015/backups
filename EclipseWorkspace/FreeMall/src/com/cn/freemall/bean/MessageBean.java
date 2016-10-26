package com.cn.freemall.bean;

public class MessageBean {
  public int messageId;//1、消息ID
  
  public String content;//2、消息内容
  
  public int sendId;//3、发消息用户ID
  
  public int receiveId;//4、收消息用户ID
  
  public String time;//5、创建消息的时间
  
  public int first;//6、1表示第一条消息
  
  public int state;//7、0表示新消息，1表示已看的消息
  
  public UserBean sendUser;//8
  
  public UserBean receiveUser;//9

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

  public UserBean getSendUser() {
    return sendUser;
  }

  public void setSendUser(UserBean sendUser) {
    this.sendUser = sendUser;
  }

  public UserBean getReceiveUser() {
    return receiveUser;
  }

  public void setReceiveUser(UserBean receiveUser) {
    this.receiveUser = receiveUser;
  }

}
