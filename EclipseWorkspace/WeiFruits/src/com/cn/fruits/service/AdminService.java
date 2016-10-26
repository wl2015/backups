package com.cn.fruits.service;

import java.util.List;

import com.cn.fruits.bean.AdminBean;
import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.bean.OrdersBean;

public interface AdminService {

  public AdminBean doLoginService(AdminBean admin) throws Exception;

  public boolean saveFruitService(FruitBean fruitBean) throws Exception;
  
  public boolean saveNoticeService(NoticeBean noticeBean) 
      throws Exception;
  
  public List<NoticeBean> getALlNoticesService() 
      throws Exception;
  
  public boolean deleteNoticeByNoticeIdService(int noticeId) throws Exception;
  
  public boolean saveNewCampusService(String campusName)
      throws Exception;
  
  public List<CampusBean> getAllCampusesService()throws Exception;
  
  public boolean deleteCampusByCampusIdService(int campusId)
      throws Exception;
  
  public boolean updateCampusNameService(CampusBean campusBean)
      throws Exception;

  public boolean addNewDormitoryService(DormitoryBean dormitoryBean)
      throws Exception;

  public List<DormitoryBean> getAllDormitoryService()throws Exception;
  
  public boolean deleteDormitoryByDormitoryIdService(int dormitoryId)
      throws Exception;

  public boolean updateDormitoryNameService(DormitoryBean dormitoryBean)
      throws Exception;

  public List<FruitBean> getFruitsByPageNumService(int pageNum)
      throws Exception;
  
  public boolean deleteFruitByFruitIdService(int fruitId)throws Exception;
  
  public FruitBean getFruitByFruitIdService(int fruitId)throws Exception;
  
  public boolean updateFruitInfoByFruitIdService(FruitBean fruitBean)
      throws Exception;
  
  public List<OrdersBean> selectFinishedOrdersByPageNumService(int pageNum) 
      throws Exception;

  public boolean deleteOrderByOrderIdService(int orderId)throws Exception;
  
  public OrdersBean getOrderInfoByOrderIdService(int orderId)throws Exception;
  
  public List<OrdersBean> selectUnFinishedOrdersByPageNumService(int pageNum)
      throws Exception;

  public boolean updateOrderStatusToAcceptByOrderIdService(int orderId)
      throws Exception;

  public boolean updateOrderStatusToFinishByOrderIdService(int orderId)
      throws Exception;

  public int getUnAcceptOrdersCountService()throws Exception;
  
  public boolean changeAdminPassWordByAdminIdService(int adminId,String passWord)
      throws Exception;
}
