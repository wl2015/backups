package com.yc.dao;

import java.util.List;

import com.yc.bean.EvalueOrder;

public interface UserStarDao {
  public int updateUserStar(List<EvalueOrder> evalueOrderlist) throws Exception;//修改用户得分
}
