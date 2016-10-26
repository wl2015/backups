package com.yc.bean;



public class Message {
    
    private int messageId;
    private int sendId;
    private int recId;
    private int textId;
    private int status;
    private String message;
    private String createtime;
    public String getMessage() {
      return message;
    }
    public void setMessage(String message) {
      this.message = message;
    }
    
    public String getCreatetime() {
      return createtime;
    }
    public void setCreatetime(String createtime) {
      this.createtime = createtime;
    }
    public int getMessageId() {
        return messageId;
    }
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
    public int getSendId() {
        return sendId;
    }
    public void setSendId(int sendId) {
        this.sendId = sendId;
    }
    public int getRecId() {
        return recId;
    }
    public void setRecId(int recId) {
        this.recId = recId;
    }
    public int getTextId() {
        return textId;
    }
    public void setTextId(int textId) {
        this.textId = textId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
