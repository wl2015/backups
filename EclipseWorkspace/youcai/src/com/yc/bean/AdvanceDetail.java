package com.yc.bean;

/**
 * 单个商家预付款记录详情
 * @author Administrator
 *
 */
public class AdvanceDetail {
    
    private int merchantId;
    private int advanceId;
    private int orderId;
    private double money;
    private int userStatus;
    private int refund;
    private int advanceStatus;
    private String advanceTime;
   
    
    
    public int getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    public int getAdvanceId() {
        return advanceId;
    }
    public void setAdvanceId(int advanceId) {
        this.advanceId = advanceId;
    }
    public String getAdvanceTime() {
        return advanceTime;
    }
    public void setAdvanceTime(String advanceTime) {
        this.advanceTime = advanceTime;
    }
    public int getAdvanceStatus() {
        return advanceStatus;
    }
    public void setAdvanceStatus(int advanceStatus) {
        this.advanceStatus = advanceStatus;
    }
    public int getRefund() {
        return refund;
    }
    public void setRefund(int refund) {
        this.refund = refund;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    
    
    
}
