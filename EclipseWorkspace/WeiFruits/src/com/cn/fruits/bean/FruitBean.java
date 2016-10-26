package com.cn.fruits.bean;

import javax.persistence.Column;

/**
 * 水果
 * */
public class FruitBean {
  public int fruitId;//1、fruit的ID
  
  public String fruitName;//2、水果名
  
  public Double fruitPrice;//3、水果的单位价格
  
  public String fruitIntr;//4、水果的简介
  
  public String fruitDetail;//5、水果的详情
  
  public String fruitPic;//6、图片的路径
  
  public String createTime;//7、上架时间
  
  public int fruitState;//8、水果是否上架，1表示已经下架，0表示还在架上
  
  public int count;//9、点的份数
  
  public int sellCount;//10、销量

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

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getSellCount() {
    return sellCount;
  }

  public void setSellCount(int sellCount) {
    this.sellCount = sellCount;
  }
  
}
