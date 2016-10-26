package com.cn.fruits.service;

import java.util.List;

import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.bean.UserBean;
import com.cn.fruits.entity.UserEntity;

/**
 * 用户模块，服务层接口
 */
public interface UserService {

  public List<FruitBean> getFruitsListService() throws Exception;

  public NoticeBean getNewestNoticeService() throws Exception;

  public List<CampusBean> getAllCampusessService() throws Exception;

  public List<DormitoryBean> getDormitoriesDaoByCampusIdService(int campusId) 
      throws Exception;
  
  public boolean saveNewOrdersService(OrdersBean ordersBean, 
      List<FruitBean> fruitBeans) throws Exception;
  
  public String getCampusNameByCampusIdService(int campusId)throws Exception;

  public boolean userNameisExistService(String userName)throws Exception;
  
  public int saveNewUserService(UserBean userBean) throws Exception;
  
  public int getUserIdByUserNameAndPassWordService(String userName,
  String passWord)throws Exception;
  
  public List<OrdersBean> getUnFinishOrdersByUserIdService(int userId)throws Exception;
  
  public List<OrdersBean> getFinishOrdersByUserIdService(int userId)throws Exception;
  
  public UserBean getUserByUserIdService(int userId)throws Exception;
  
  public boolean updateUserNameByUserIdService(int userId,String name)throws Exception;
}
