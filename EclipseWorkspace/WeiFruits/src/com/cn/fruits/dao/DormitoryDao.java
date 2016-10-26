package com.cn.fruits.dao;

import java.util.List;

import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.entity.CampusEntity;
import com.cn.fruits.entity.DormitoryEntity;

public interface DormitoryDao {

  public boolean insertNewDormitoryDao(DormitoryEntity dormitoryEntity) throws Exception;

  public List<DormitoryBean> seletDormitoriesDao() throws Exception;

  public boolean deleteDormitoryByDormitoryIdDao(int dormitoryId) throws Exception;

  public boolean updateDormitoryNameDao(DormitoryEntity dormitoryEntity) throws Exception;

  public List<DormitoryBean> selectDormitoriesDaoByCampusIdDao(int campusId) throws Exception;
}
