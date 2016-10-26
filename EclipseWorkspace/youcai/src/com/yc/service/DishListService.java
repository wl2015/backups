package com.yc.service;

import java.util.List;

import com.yc.bean.DishList;

public interface DishListService {
  public List<DishList>  getDishlistByO_Id(int oid) throws Exception; 
}
