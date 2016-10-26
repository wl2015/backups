package com.yc.bean;

/**
 * 本月出货单列表
 * @author Administrator
 *
 */
public class SalesList {
     
    private int salesId;
    private String shopName;
    private String dishName;
    private int salesNum;
    private String createTime;
    
    public int getSalesId() {
        return salesId;
    }
    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getDishName() {
        return dishName;
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    public int getSalesNum() {
        return salesNum;
    }
    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    
} 
