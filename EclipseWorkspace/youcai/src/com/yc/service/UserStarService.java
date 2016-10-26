package com.yc.service;

import java.util.List;

import com.yc.bean.EvalueOrder;

public interface UserStarService {
  public boolean updateUserStar(List<EvalueOrder> evalueOrderlist) throws Exception;//修改用户得分
}
