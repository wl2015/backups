package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.DishList;
import com.yc.dao.DishListDao;
import com.yc.dao.InventoryDao;
import com.yc.service.DishListService;
@Service
@Transactional
public class DishListServiceImpl implements DishListService {

  @Autowired
  private DishListDao dishListDao;
  @Override
  public List<DishList> getDishlistByO_Id(int oid) throws Exception {
    // TODO Auto-generated method stub
    return dishListDao.getDishlistByO_Id(oid);
  }

}
