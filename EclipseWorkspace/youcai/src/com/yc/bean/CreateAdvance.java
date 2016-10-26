package com.yc.bean;

import java.util.Date;

/**
 * 生成预付款统计
 * @author Administrator
 *
 */
public class CreateAdvance {
    
    private int createId;
    private int merchantId;
    private String shopName;
    private double totalMoney;
    private String timeSlot;//时间段
    private String createTime;//付款单生成时间
    
    private int createAdvanceStatus;//打款状态
    

    

    public int getCreateId() {
        return createId;
    }
    public void setCreateId(int createId) {
        this.createId = createId;
    }
    public double getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    public int getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
    public int getCreateAdvanceStatus() {
        return createAdvanceStatus;
    }
    public void setCreateAdvanceStatus(int createAdvanceStatus) {
        this.createAdvanceStatus = createAdvanceStatus;
    }
    
    
}
