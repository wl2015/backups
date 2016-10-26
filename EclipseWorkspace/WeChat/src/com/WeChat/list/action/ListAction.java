package com.WeChat.list.action;

import java.util.ArrayList;
import java.util.List;
import com.WeChat.entity.Dish;
import com.WeChat.entity.UserInfo;
import com.WeChat.list.service.OrderListService;
import com.WeChat.list.service.OrderListServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAction extends ActionSupport{
	private List<Dish> dishlist;//菜单列表
	private List<Dish> orderlist2;
	private int textId;
	private int count;
	private float total;
	
	private UserInfo userinfo;
	
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<Dish> getOrderlist2() {
		return orderlist2;
	}
	public void setOrderlist2(List<Dish> orderlist2) {
		this.orderlist2 = orderlist2;
	}	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getTextId() {
		return textId;
	}
	public void setTextId(int textId) {
		this.textId = textId;
	}
	public List<Dish> getDishlist() {
		return dishlist;
	}
	public void setDishlist(List<Dish> dishlist) {
		this.dishlist = dishlist;
	}	
	
	/**
	 * 进入点餐列表
	 * @return
	 */
	public String orderList(){
		OrderListService orderlistService = new OrderListServiceImpl();
		dishlist = orderlistService.getDishList();
		return "listSuccess";
	}
	
	/**
	 * 更新购物车
	 * @return
	 */
	public String updatecar(){
		OrderListService orderlistService = new OrderListServiceImpl();
		orderlist2 = (ArrayList<Dish>)ActionContext.getContext().getSession().get("orderlist");
		if(orderlist2 == null){
			orderlist2 = new ArrayList<Dish>();
		}
		else{
			for(int i=0; i<orderlist2.size();i++){
				Dish d = new Dish();
				d=orderlist2.get(i);
				if(d.getDish_id()==getTextId()){
					orderlist2.remove(i);
					break;
				}
			}
		}
		if(getCount()!=0){
			Dish di = new Dish();
			di = orderlistService.getDishByDishId(getTextId());
			di.setDish_num(getCount());
			orderlist2.add(di);
			ActionContext.getContext().getSession().put("orderlist", orderlist2);
		}
		return "updateSuccess";
	}
	
	/**
	 * 显示购物车
	 * @return
	 */
	public String lastshow(){
		orderlist2 = (ArrayList<Dish>)ActionContext.getContext().getSession().get("orderlist");
		total=6;
		for(int i=0;i<orderlist2.size();i++){
			Dish di = new Dish();
			di=orderlist2.get(i);
			total=total+di.getDish_num()*di.getPrice();
		}
		
		userinfo = (UserInfo)ActionContext.getContext().getSession().get("UserInfo");
		
		return "lastshowSuccess";
	}
}