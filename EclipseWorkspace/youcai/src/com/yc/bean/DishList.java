package com.yc.bean;
/**
 * 菜单列表
 * */
public class DishList {
  //菜单列表id
  private int dishListId;
  //订单id
  private int oId;
  //菜品id
  private int dishId;
  //菜品单价
  private double price;
  //菜品数量
  private int number;
  //对应的菜
  private Dish dish;
  //菜名
  private String dishName;
  //总价
  private float totalMoney;
  
  public DishList(){
  }
  
  public DishList(int dishListId, int oId, int dishId, double price, int number){
    this.dishListId = dishListId;
    this.oId = oId;
    this.dishId = dishId;
    this.price = price;
    this.number = number;
  }
  
  public float getTotalMoney() {
    return totalMoney;
  }
  public void setTotalMoney(float totalMoney) {
    this.totalMoney = totalMoney;
  }
  public String getDishName() {
    return dishName;
  }
  public void setDishName(String dishName) {
    this.dishName = dishName;
  }
  public Dish getDish() {
    return dish;
  }
  public void setDish(Dish dish) {
    this.dish = dish;
  }
  public int getDishListId() {
    return dishListId;
  }
  public void setDishListId(int dishListId) {
    this.dishListId = dishListId;
  }
  public int getoId() {
    return oId;
  }
  public void setoId(int oId) {
    this.oId = oId;
  }
  public int getDishId() {
    return dishId;
  }
  public void setDishId(int dishId) {
    this.dishId = dishId;
  }
  public double getPrice() {
    return price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
}
