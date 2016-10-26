package com.cn.freemall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="product")
@DynamicInsert
@DynamicUpdate
public class ProductEntity {
  @Id
  @GeneratedValue
  @Column(name="product_id")
  public int productId;//1、商品ID
  
  @Column(name="user_id")
  public int userId;//2、用户ID
  
  @Column(name="product_name")
  public String productName;//3、商品名
  
  @Column(name="place")
  public String place;//4、地点
  
  @Column(name="price")
  public Double price;//5、价格

  @Column(name="intro")
  public String intro;//6、简介
  
  @Column(name="pic")
  public String pic;//7、图片地址
  
  @Column(name="time")
  public String time;//8、上架日期
  
  @Column(name="state")
  public int state;//9、0表示正在买，1表示已卖出，2表示下架商品
  
  @Column(name="type_id")
  public int typeId;//10、typeID

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

}
