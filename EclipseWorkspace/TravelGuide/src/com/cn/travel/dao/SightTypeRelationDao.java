package com.cn.travel.dao;

import java.util.List;

import com.cn.travel.bean.SightTypeRelationBean;

public interface SightTypeRelationDao {
  
  public List<SightTypeRelationBean> getRelationsBySightIdDao(int sightId)
      throws Exception;
  
  public List<SightTypeRelationBean> selectRelationsByTypeIdDao(int typeId)
      throws Exception;
}
