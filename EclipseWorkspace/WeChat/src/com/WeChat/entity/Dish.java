package com.WeChat.entity;


public class Dish {
	private int dish_id;//1、dish_id//INTEGER
	private String dish_name;//2、菜名(dish_name)//VARCHAR(45)
	private float price;//3、价格(price)//FLOAT
	private String pic;//4、图片地址(s_pic)//VARCHAR(100)
	private String dish_intro;//5、详细介绍(dish_intro)//MEDIUMTEXT
	private String dish_taste;//6、口味
	private int dish_num;//7、份数
	private int leftNum;//8、剩下多少份
	
	public int getDish_num() {
		return dish_num;
	}
	public void setDish_num(int dishNum) {
		dish_num = dishNum;
	}
	public String getDish_taste() {
		return dish_taste;
	}
	public void setDish_taste(String dishTaste) {
		dish_taste = dishTaste;
	}
	public int getDish_id() {
		return dish_id;
	}
	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDish_intro() {
		return dish_intro;
	}
	public void setDish_intro(String dish_intro) {
		this.dish_intro = dish_intro;
	}
	public int getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(int leftNum) {
		this.leftNum = leftNum;
	}
}
