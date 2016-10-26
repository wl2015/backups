package com.yc.bean;
/**
 * 退款理由表
 * */
public class RefundReason {
  //退款理由Id
  private int reasonId;
  //订单ID
  private int orderId;
  //退款理由内容
  private String content;
  //申请退款时间
  private String time;
  
  public int getReasonId() {
    return reasonId;
  }
  public void setReasonId(int reasonId) {
    this.reasonId = reasonId;
  }
  public int getOrderId() {
    return orderId;
  }
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  
}
