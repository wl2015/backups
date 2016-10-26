package com.cn.freemall.dao;

import com.cn.freemall.entity.RelationEntity;

public interface RelationDao {
  public boolean insertNewReationDao(RelationEntity relationEntity)throws Exception;

  public boolean updateRelationDao(RelationEntity relationEntity)throws Exception;
}
