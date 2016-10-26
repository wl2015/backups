package com.yc.bean;

import java.util.List;

public class DishGroup {
  private int groupId;
  private String groupName;
  private String dishes;
  private String groupIntro;
  private String groupPic;
  private double originalPrice;
  private double retailPrice;
  private List<Dish> dishList;
  private int typeId;
  //属于分类
  private List<Type> typeList;
  //总店的库存
  private int count;
  public int getTypeId() {
    return typeId;
  }
  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }
  public int getGroupId() {
    return groupId;
  }
  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }
  public String getGroupName() {
    return groupName;
  }
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
  public String getDishes() {
    return dishes;
  }
  public void setDishes(String dishes) {
    this.dishes = dishes;
  }
  public String getGroupIntro() {
    return groupIntro;
  }
  public void setGroupIntro(String groupIntro) {
    this.groupIntro = groupIntro;
  }
  public String getGroupPic() {
    return groupPic;
  }
  public void setGroupPic(String groupPic) {
    this.groupPic = groupPic;
  }
  public double getOriginalPrice() {
    return originalPrice;
  }
  public void setOriginalPrice(double originalPrice) {
    this.originalPrice = originalPrice;
  }
  public double getRetailPrice() {
    return retailPrice;
  }
  public void setRetailPrice(double retailPrice) {
    this.retailPrice = retailPrice;
  }
  public List<Dish> getDishList() {
    return dishList;
  }
  public void setDishList(List<Dish> dishList) {
    this.dishList = dishList;
  }
  public List<Type> getTypeList() {
    return typeList;
  }
  public void setTypeList(List<Type> typeList) {
    this.typeList = typeList;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  
}
