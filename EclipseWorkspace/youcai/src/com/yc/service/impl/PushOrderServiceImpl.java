package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.DishList;
import com.yc.bean.PushOrder;
import com.yc.bean.msureOrder;
import com.yc.dao.PushOrderDao;
import com.yc.service.PushOrderService;

@Service
@Transactional
public class PushOrderServiceImpl implements  PushOrderService{
	
	@Autowired
	private PushOrderDao pushOrderDao;
	/*
	 * 商户获取可抢订单
	 * @parameter merchantId 商户id
	 * @return List<PushOrder>  可抢订单列表
	 */
	 public List<PushOrder> selectPushOrder(int  merchantId) throws Exception{
		 return pushOrderDao.SelectPushOrder(merchantId);
	 }
	 /*
	 * 根据订单号获取订菜列表
	 * @parameter orderid 订单号
	 * @return List<DishList>  订菜列表
	 */
	 public List<DishList> getDishlistByO_Id(int  orderid) throws Exception{
		 return pushOrderDao.getDishlistByO_Id(orderid);
	 }
	 
	 	/*
		 * 商家确认抢单
		 * @parameter msureOrders 商家的确认抢单列表（包括mid：商家id，id：订单id，spendTime：配送时间）
		 * @return int  抢单完成情况 （1：完成，0:未完成）
		 */
	 @Transactional
	 public boolean mSureOrder(List<msureOrder> msureOrders, List<Integer> goIdList) throws Exception{
	   int isSure = pushOrderDao.mSureOrder(msureOrders);
	   //暂时不需要将没抢的订单删除
	  /* if(isSure > 0){
	      pushOrderDao.deleteNotSureOrder(goIdList);
	   }*/
	   if(isSure > 0){
	     return true;
	   }
	   else{
	     return false;
	   }		 		 
	 }
	 
	 /*
	   * 商户获取可抢订单数量
	   * @parameter merchantId 商户id
	   * @return 可抢订单数量
	   */
  @Override
  public int getPushOrderCount(int merchantId) throws Exception {
    return pushOrderDao.getPushOrderCount(merchantId);
  }
}
