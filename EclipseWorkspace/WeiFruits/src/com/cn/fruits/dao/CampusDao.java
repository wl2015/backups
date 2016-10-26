package com.cn.fruits.dao;

import java.util.List;

import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.entity.CampusEntity;

public interface CampusDao {
  public boolean insertNewCampusDao(String campusName)throws Exception;
  
  public List<CampusBean> selectAllCampusesDao() throws Exception;

  public boolean deleteCampusByCampusIdDao(int campusId)throws Exception;

  public boolean updateCampusNameDao(CampusEntity campusEntity)throws Exception;
  
  public String selectCampusNameByCampusIdDao(int campusId)throws Exception;
}
