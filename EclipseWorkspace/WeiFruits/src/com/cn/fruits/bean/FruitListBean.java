package com.cn.fruits.bean;

/***
 * 水果菜单列表
 * */
public class FruitListBean {
  
  public int fruitListId;//1、fruit_list的ID
  
  public int orderId;//2、orders表的ID
  
  public int fruitId;//3、fruit的ID
  
  public int count;//4、点的份数

  public int getFruitListId() {
    return fruitListId;
  }

  public void setFruitListId(int fruitListId) {
    this.fruitListId = fruitListId;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getFruitId() {
    return fruitId;
  }

  public void setFruitId(int fruitId) {
    this.fruitId = fruitId;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
  
}
