package com.cn.fruits.dao;

import java.util.List;

import com.cn.fruits.bean.FruitListBean;
import com.cn.fruits.entity.FruitListEntity;

public interface FruitListDao {
  public boolean saveFruitListDao(List<FruitListEntity> fruitListEntities) throws Exception;

  public List<FruitListBean> selectFruitListsByOrderIdDao(int orderId)throws Exception;
}
