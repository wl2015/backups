package com.yc.bean;

import java.util.Date;

/**
 * 财务实体类
 * @author Administrator
 *
 */
public class Finance {

    private long financeId;
    private int dishId;
    private int merchantId;
    private int numTotal;
    private double costTotal;
    private double retailTotal;
    private double profitTotal;
    private Date financeTime;
    public long getFinanceId() {
        return financeId;
    }
    public void setFinanceId(long financeId) {
        this.financeId = financeId;
    }
    public int getDishId() {
        return dishId;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
    public int getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
    
    public int getNumTotal() {
        return numTotal;
    }
    public void setNumTotal(int numTotal) {
        this.numTotal = numTotal;
    }
    public double getCostTotal() {
        return costTotal;
    }
    public void setCostTotal(double costTotal) {
        this.costTotal = costTotal;
    }
    public double getRetailTotal() {
        return retailTotal;
    }
    public void setRetailTotal(double retailTotal) {
        this.retailTotal = retailTotal;
    }
    public double getProfitTotal() {
        return profitTotal;
    }
    public void setProfitTotal(double profitTotal) {
        this.profitTotal = profitTotal;
    }
    public Date getFinanceTime() {
        return financeTime;
    }
    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }
    
    
    
    
}
