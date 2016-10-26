package com.WeChat.entity;

public class OrderDish {
	private int orderdish_id;//1、菜品种类（orderdish_id）//INTEGER
	private int dish_id;//2.菜品ID(dish_id)//INTEGER
	private int dish_num;//3、份数(dish_num)//INTEGER
	private int order_id;//4、订单id（order_id）//INTEGER
	private String dishName;//5.菜品名（并不放到数据库）
	private float price;//6.菜品单价（并不放到数据库）
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getOrderdish_id() {
		return orderdish_id;
	}
	public void setOrderdish_id(int orderdish_id) {
		this.orderdish_id = orderdish_id;
	}
	public int getDish_id() {
		return dish_id;
	}
	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	public int getDish_num() {
		return dish_num;
	}
	public void setDish_num(int dish_num) {
		this.dish_num = dish_num;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
}
