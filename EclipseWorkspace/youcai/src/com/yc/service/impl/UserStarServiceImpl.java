package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.EvalueOrder;
import com.yc.dao.UserDAO;
import com.yc.dao.UserStarDao;
import com.yc.service.UserStarService;

@Service
@Transactional
public class UserStarServiceImpl implements UserStarService {

  @Autowired
  private UserStarDao userStarDao;
  /**
   * 商家就某一订单给用户打分时，修改用户得分信息
   */
  @Override
  public boolean updateUserStar(List<EvalueOrder> evalueOrderlist) throws Exception {
    int isUpdate = userStarDao.updateUserStar(evalueOrderlist);
    if(isUpdate > 0){
      return true;
    }
    else{
      return false;
    }
  }

}
