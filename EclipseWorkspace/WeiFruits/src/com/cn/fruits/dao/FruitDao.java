package com.cn.fruits.dao;

import java.util.List;

import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.entity.FruitEntity;

public interface FruitDao {
  
  public boolean saveFruitDao(FruitEntity fruitEntity) throws Exception;
  
  public List<FruitBean> selectAllFruitsDao() throws Exception;
  
  public boolean deleteFruitByFruitIdDao(int fruitId)throws Exception;
  
  public FruitBean selectFruitByFruitIdDao(int fruitId)throws Exception;
  
  public boolean updateFruitInfoByFruitIdDao(FruitBean fruitBean)throws Exception;

  public List<FruitBean> selectFruitsByPageNumDao(int pageNum)throws Exception;
}
