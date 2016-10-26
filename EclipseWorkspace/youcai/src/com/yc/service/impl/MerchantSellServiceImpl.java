package com.yc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Advance;
import com.yc.bean.Merchant;
import com.yc.dao.MerchantDao;
import com.yc.dao.MerchantSellDao;
import com.yc.service.MerchantSellService;
import com.yc.service.MerchantService;
@Service
@Transactional
public class MerchantSellServiceImpl implements MerchantSellService {

  @Autowired
  private MerchantSellDao merchantSellDao;
 /***
  * 商家查询各状态下的销售情况
  *@param startDate：起始日期，endDate:截止日期（必选），
  *@param handelStatus结算状态,  refundStatus退单状态,  merchantId：商家ID
  *@return List<Advance> 返回销售数据列表
  */
  @Override
  public List<Advance> getMerchantAdvance(Date startDate, Date endDate,
      String handelStatus, String refundStatus, int merchantId)
      throws Exception {
    return merchantSellDao.getMerchantAdvance(startDate, endDate, handelStatus, refundStatus, merchantId);
  }

  /***
   * 商家查询不同状态下的销售总计
   *@param startDate：起始日期，endDate:截止日期（必选），
  *@param handelStatus结算状态,  refundStatus退单状态,  merchantId：商家ID
   *@return 销售总计
   */
  @Override
  public List<Advance> getMerchantTotalAdvance(Date startDate, Date endDate,
      String handelStatus, String refundStatus, int merchantId)
      throws Exception {
    return merchantSellDao.getMerchantTotalAdvance(startDate, endDate, handelStatus, refundStatus, merchantId);
  }
}
