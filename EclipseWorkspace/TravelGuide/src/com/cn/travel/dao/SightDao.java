package com.cn.travel.dao;

import java.util.List;

import com.cn.travel.bean.SightBean;
import com.cn.travel.entity.SightEntity;

/**
 * 景区模块，数据库层接口
 * */
public interface SightDao {
  public boolean saveNewSightDao(SightEntity sightEntity)throws Exception;
  
  public List<SightBean> getSightByDistrictDao(String district)throws Exception;
  
  public SightBean selectSightsBySightIdDao(int sightId)throws Exception;
  
  public List<SightBean> selectSightsDao()throws Exception;
  
  public boolean updateAddressAndDistrictDao(SightEntity sightEntity)throws Exception;

  public List<SightBean> selectSightsBySightNameDao(String sightName)throws Exception;
}
