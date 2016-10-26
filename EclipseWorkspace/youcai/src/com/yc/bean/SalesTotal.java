package com.yc.bean;

public class SalesTotal {
    
    private int id;
    private int dishId;
    private double costSales;
    private String dishName;
    private int numTotal;
    private double costTotal;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDishId() {
        return dishId;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
    public double getCostSales() {
        return costSales;
    }
    public void setCostSales(double costSales) {
        this.costSales = costSales;
    }
    public String getDishName() {
        return dishName;
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
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
    
    
}
