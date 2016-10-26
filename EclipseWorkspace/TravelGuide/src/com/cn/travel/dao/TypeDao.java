package com.cn.travel.dao;

import java.util.List;

import com.cn.travel.bean.TypeBean;

public interface TypeDao {
  public List<TypeBean> selectAllTypesDao()throws Exception;
  
  public TypeBean selectTypeByTypeIdDao(int typeId)throws Exception;
  
  public TypeBean selectTypeByTypeNameDao(String typeName)throws Exception;
}
