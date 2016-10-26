package com.yc.bean;

//打分数据接收类
public class EvalueOrder {
  //评分的订单id
  private int orderId;
  //所打的分数
  private int cord;
  public int getOrderId() {
    return orderId;
  }
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  public int getCord() {
    return cord;
  }
  public void setCord(int cord) {
    this.cord = cord;
  }
}
