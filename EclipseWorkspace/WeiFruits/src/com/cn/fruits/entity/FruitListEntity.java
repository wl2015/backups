package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/***
 * 水果菜单列表
 * */
@Entity
@Table(name="fruit_list")
@DynamicInsert
@DynamicUpdate
public class FruitListEntity {
  
  @Id
  @GeneratedValue
  @Column(name="fruit_list_id")
  public int fruitListId;//1、fruit_list的ID
  
  @Column(name="order_id")
  public int orderId;//2、orders表的ID
  
  @Column(name="fruit_id")
  public int fruitId;//3、fruit的ID
  
  @Column(name="count")
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
