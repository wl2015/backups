package com.yc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Inventory;
import com.yc.dao.InventoryDao;
import com.yc.service.InventoryService;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService{
	 @Autowired
	  private InventoryDao inventoryDao;
	 
	/*商家查看库存情况
	 * @param  merchantId：商家id
	 * @return List<Inventory> 该商家的库存集合
	 */
		public List<Inventory> getInventoryM(int  merchantId) throws Exception{
			return inventoryDao.getInventoryM(merchantId);
		}
		
		/*商家修改库存
		 * @param  inventorylist：库存列表
		 * @return int  修改的库存数据条数
		 */
		public  int updateInventoryM(List<Inventory> inventorylist) throws Exception{
			return inventoryDao.updateInventoryM(inventorylist);
		}
		
		/**
	   * 修改商家库存上限量
	   */
	  public void modifyLimitCount(Map<String, Object> inventoryMap)throws Exception{
	      inventoryDao.modifyLimitCount(inventoryMap);
	  }
}
