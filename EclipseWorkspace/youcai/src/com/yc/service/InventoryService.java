package com.yc.service;

import java.util.List;
import java.util.Map;

import com.yc.bean.Inventory;

public interface InventoryService {
	//商家查看库存情况
		public  List<Inventory> getInventoryM(int  merchantId) throws Exception;
		//商家修改库存
		public  int updateInventoryM(List<Inventory> inventorylist) throws Exception;
		
		/**
	   * 修改商家库存上限量
	   */
		public void modifyLimitCount(Map<String, Object> inventoryMap)throws Exception;
}
