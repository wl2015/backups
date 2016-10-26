package com.cn.fruits.dao;

import com.cn.fruits.bean.AdminBean;
import com.cn.fruits.entity.AdminEntity;

public interface AdminDao {
  public AdminBean doLoginDao(AdminEntity admin) throws Exception;
  
  public boolean updateAdminPassWordByAdminIdDao(int adminId, String passWord)
      throws Exception;
}
