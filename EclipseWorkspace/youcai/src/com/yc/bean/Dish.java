package com.yc.bean;

import java.util.List;

/**
 * 菜品实体类
 * @author Administrator
 *
 */
public class Dish {
    //菜品ID
    private int dishId;
    //菜品名
    private String dishName;
    //菜品简介
    private String dishIntro;
    //菜品详情
    private String dishDetail;
    //图片路径
    private String dishPic;
    //成本价（进价）
    private  double costPrice;
    //原价
    private double originalPrice;
    //售价（折后价）
    private  double retailPrice;
    //口味
    private String dishTaste;
    //总店的库存
    private int count;
    //属于分类
    private List<Type> typeList;
    
    //类别名称
    private String typeName; 
    //菜品份数(套餐的时候使用)
    private int num;
   

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }

    public Dish(){
    }
    
    public Dish(int dishId, String dishName, String dishIntro,String dishDetail, String dishPic,
        double retailPrice, String dishTaste){
      this.dishId = dishId;
      this.dishName = dishName;
      this.dishIntro = dishIntro;
      this.dishDetail = dishDetail;
      this.dishPic = dishPic;
      this.retailPrice = retailPrice;
      this.dishTaste = dishTaste;
    }

    public int getDishId() {
      return dishId;
    }

    public void setDishId(int dishId) {
      this.dishId = dishId;
    }

    public String getDishName() {
      return dishName;
    }

    public void setDishName(String dishName) {
      this.dishName = dishName;
    }

    public String getDishIntro() {
      return dishIntro;
    }

    public void setDishIntro(String dishIntro) {
      this.dishIntro = dishIntro;
    }

    public String getDishDetail() {
      return dishDetail;
    }

    public void setDishDetail(String dishDetail) {
      this.dishDetail = dishDetail;
    }

    public String getDishPic() {
      return dishPic;
    }

    public void setDishPic(String dishPic) {
      this.dishPic = dishPic;
    }

    public double getCostPrice() {
      return costPrice;
    }

    public void setCostPrice(double costPrice) {
      this.costPrice = costPrice;
    }

    public double getOriginalPrice() {
      return originalPrice;
    }

    public void setOriginalPrice(double original_price) {
      this.originalPrice = original_price;
    }

    public double getRetailPrice() {
      return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
      this.retailPrice = retailPrice;
    }

    public String getDishTaste() {
      return dishTaste;
    }

    public void setDishTaste(String dishTaste) {
      this.dishTaste = dishTaste;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public List<Type> getTypeList() {
      return typeList;
    }

    public void setTypeList(List<Type> typeList) {
      this.typeList = typeList;
    }
    
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
}
