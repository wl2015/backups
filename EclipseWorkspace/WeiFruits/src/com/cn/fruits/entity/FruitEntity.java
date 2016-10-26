package com.cn.fruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 水果
 * */
@Entity
@Table(name="fruit")
@DynamicInsert
@DynamicUpdate
public class FruitEntity {
  @Id
  @GeneratedValue
  @Column(name="fruit_id")
  public int fruitId;//1、fruit的ID
  
  @Column(name="fruit_name")
  public String fruitName;//2、水果名
  
  @Column(name="fruit_price")
  public Double fruitPrice;//3、水果的单位价格
  
  @Column(name="fruit_intr")
  public String fruitIntr;//4、水果的简介
  
  @Column(name="fruit_detail")
  public String fruitDetail;//5、水果的详情
  
  @Column(name="fruit_pic")
  public String fruitPic;//6、图片的路径
  
  @Column(name="create_time")
  public String createTime;//7、上架时间

  @Column(name="fruit_state")
  public int fruitState;//8、水果是否上架，1表示已经下架，0表示还在架上
  
  public int getFruitId() {
    return fruitId;
  }

  public void setFruitId(int fruitId) {
    this.fruitId = fruitId;
  }

  public String getFruitName() {
    return fruitName;
  }

  public void setFruitName(String fruitName) {
    this.fruitName = fruitName;
  }

  public Double getFruitPrice() {
    return fruitPrice;
  }

  public void setFruitPrice(Double fruitPrice) {
    this.fruitPrice = fruitPrice;
  }

  public String getFruitIntr() {
    return fruitIntr;
  }

  public void setFruitIntr(String fruitIntr) {
    this.fruitIntr = fruitIntr;
  }

  public String getFruitDetail() {
    return fruitDetail;
  }

  public void setFruitDetail(String fruitDetail) {
    this.fruitDetail = fruitDetail;
  }

  public String getFruitPic() {
    return fruitPic;
  }

  public void setFruitPic(String fruitPic) {
    this.fruitPic = fruitPic;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public int getFruitState() {
    return fruitState;
  }

  public void setFruitState(int fruitState) {
    this.fruitState = fruitState;
  }
  
}
