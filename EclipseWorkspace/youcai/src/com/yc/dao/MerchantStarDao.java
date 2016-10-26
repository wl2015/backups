package com.yc.dao;



public interface MerchantStarDao {
  public int initMerchantStar(int merchantid) throws Exception;//初始化商家得分
  
  public int deleteMerchantStar(int merchantid) throws Exception;//删除商家得分
}
