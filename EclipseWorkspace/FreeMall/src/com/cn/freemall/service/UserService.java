package com.cn.freemall.service;

import java.util.List;

import com.cn.freemall.bean.MessageBean;
import com.cn.freemall.bean.ProductBean;
import com.cn.freemall.bean.UserBean;

/**
 * 用户服务层
 * */
public interface UserService {
  public UserBean saveNewUserService(UserBean userBean)throws Exception;
  
  public UserBean doLoginService(UserBean userBean)throws Exception;
  
  public boolean checkUserNameIsExistService(String userName)throws Exception;
  
  public List<ProductBean> getAllProductService()throws Exception;
  
  public boolean saveNewProductService(ProductBean productBean)throws Exception;

  public List<ProductBean> getProductsByTypeIdService(int typeId)throws Exception;

  public boolean updateProductInfoService(ProductBean productBean)throws Exception;

  public List<ProductBean> getProductsByUserService(UserBean userBean)throws Exception;

  public ProductBean getProductByProductIdService(int productId)throws Exception;
  
  public boolean deleteProductByProductIdService(int productId)throws Exception;

  public UserBean getUserByUserIdService(int userId)throws Exception;
  
  public boolean saveNewMessageService(MessageBean messageBean)throws Exception;

  public List<MessageBean> getUnReadedMessagesService(UserBean loginBean)throws Exception;
  
  public List<MessageBean> getReadedMessagesService(UserBean loginBean)throws Exception;
  
  public List<MessageBean> getSendedMessagesService(UserBean loginBean)throws Exception;

  public MessageBean toReadMessageService(int messageId)throws Exception;

  public boolean updateUserInfoService(UserBean userBean,String oldPSW,String userName)throws Exception;

  public int getUnReadedMessageCountService(int userId)throws Exception;
}
