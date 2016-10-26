package com.yc.dao;

import java.util.List;

import com.yc.bean.DishList;

public interface DishListDao {
  public List<DishList>  getDishlistByO_Id(int oid) throws Exception; 
}
