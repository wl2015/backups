package com.cn.travel.service;

import java.util.List;

import com.cn.travel.bean.SightBean;
import com.cn.travel.entity.SightEntity;

public interface TestService {
  public boolean saveNewSightService(SightEntity sightEntity)throws Exception;
  
  public List<SightBean> getSightsService()throws Exception;
  
  public boolean updateSightAddress(SightBean sightBean)throws Exception;
}
