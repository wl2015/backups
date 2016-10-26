package com.yc.bean;
/**
 * 用户星级
 * */
public class MerchantStar {
  private int merchantStarId;
  private int merchantId;
  private int totalStar;
  private int evaluateNumber;
  public int getMerchantStarId() {
    return merchantStarId;
  }
  public void setMerchantStarId(int merchantStarId) {
    this.merchantStarId = merchantStarId;
  }
  public int getMerchantId() {
    return merchantId;
  }
  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }
  public int getTotalStar() {
    return totalStar;
  }
  public void setTotalStar(int totalStar) {
    this.totalStar = totalStar;
  }
  public int getEvaluateNumber() {
    return evaluateNumber;
  }
  public void setEvaluateNumber(int evaluateNumber) {
    this.evaluateNumber = evaluateNumber;
  }
  
}
