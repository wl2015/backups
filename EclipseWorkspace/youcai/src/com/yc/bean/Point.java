package com.yc.bean;
/**
 * 搜索商家的范围
 * */
public class Point {
  private double lPointLeft;
  private double rPointLeft;
  private double upPointRight;
  private double downPointRight;
  
  public double getlPointLeft() {
    return lPointLeft;
  }
  public void setlPointLeft(double lPointLeft) {
    this.lPointLeft = lPointLeft;
  }
  public double getrPointLeft() {
    return rPointLeft;
  }
  public void setrPointLeft(double rPointLeft) {
    this.rPointLeft = rPointLeft;
  }
  public double getUpPointRight() {
    return upPointRight;
  }
  public void setUpPointRight(double upPointRight) {
    this.upPointRight = upPointRight;
  }
  public double getDownPointRight() {
    return downPointRight;
  }
  public void setDownPointRight(double downPointRight) {
    this.downPointRight = downPointRight;
  }

}
