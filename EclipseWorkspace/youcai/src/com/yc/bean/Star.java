package com.yc.bean;
//用户得分实体类
public class Star {
  //userStarId
  private int id;
  //用户Id
  private int userId;
  //总得分
  private int totalStar;
  //打分次数
  private int evalueNumber;
  //平均得分
  private float aveStar;
 
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getTotalStar() {
    return totalStar;
  }
  public void setTotalStar(int totalStar) {
    this.totalStar = totalStar;
  }
  public int getEvalueNumber() {
    return evalueNumber;
  }
  public void setEvalueNumber(int evalueNumber) {
    this.evalueNumber = evalueNumber;
  }
  public float getAveStar() {
    return aveStar;
  }
  public void setAveStar(float aveStar) {
    this.aveStar = aveStar;
  }
  public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  
}
