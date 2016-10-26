package com.yc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.yc.bean.Advance;


public interface MerchantSellDao {
  //商家查询 一定日期范围内（date）不同情况下的销售额情况
  //（0：satrtDate，1：endDate，2：ad_status，3：refundStatus，4：merchantId, 5:satrtData, 6:endData ）
  public List<Advance> getMerchantAdvance(@Param("startDate") Date startDate,
      @Param("endDate") Date endDate,  @Param("handelStatus") String handelStatus,
      @Param("refundStatus")String refundStatus,  @Param("merchantId") int merchantId) throws Exception;
  //商家查询总计
  public List<Advance> getMerchantTotalAdvance(@Param("startDate") Date startDate,
      @Param("endDate") Date endDate,  @Param("handelStatus") String handelStatus,
      @Param("refundStatus")String refundStatus,  @Param("merchantId") int merchantId) throws Exception;
}
