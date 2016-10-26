package com.yc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yc.bean.Advance;

public interface MerchantSellService {
//商家查询 一定日期范围内（date）不同情况下的销售额情况
  public List<Advance> getMerchantAdvance(Date startDate,
      Date endDate, String handelStatus, String refundStatus, int merchantId) throws Exception;
//商家查询总计
  public List<Advance> getMerchantTotalAdvance(Date startDate,
      Date endDate, String handelStatus, String refundStatus, int merchantId) throws Exception;
}
