package com.yc.bean;

import java.util.Date;

/**
 * 出货单实体类
 * @author Administrator
 *
 */
public class Sales {

    private int salesId;
    private int dishId;
    private int merchantId;
    private int salesNum;
    private double costSales;
    private double retailSales;
    private double profit;
    private Date financeTime;

    public int getSalesId() {
        return salesId;
    }
    public void setSalesId(int salesId) {
        this.salesId = salesId;
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
    public int getSalesNum() {
        return salesNum;
    }
    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }
    
    public Date getFinanceTime() {
        return financeTime;
    }
    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }
    public double getCostSales() {
        return costSales;
    }
    public void setCostSales(double costSales) {
        this.costSales = costSales;
    }
    public double getRetailSales() {
        return retailSales;
    }
    public void setRetailSales(double retailSales) {
        this.retailSales = retailSales;
    }
    public double getProfit() {
        return profit;
    }
    public void setProfit(double profit) {
        this.profit = profit;
    }
    
    
    
    
}
