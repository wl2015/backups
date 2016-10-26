package com.cn.freemall.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.freemall.bean.MessageBean;
import com.cn.freemall.bean.ProductBean;
import com.cn.freemall.bean.UserBean;
import com.cn.freemall.dao.MessageDao;
import com.cn.freemall.dao.ProductDao;
import com.cn.freemall.dao.RelationDao;
import com.cn.freemall.dao.TypeDao;
import com.cn.freemall.dao.UserDao;
import com.cn.freemall.entity.ProductEntity;
import com.cn.freemall.entity.RelationEntity;
import com.cn.freemall.entity.UserEntity;
import com.cn.freemall.service.UserService;
import com.cn.freemall.util.CommonUtil;
import com.cn.freemall.util.ValidateUtil;

/**
 * 用户模块，服务层接口实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Inject
  private UserDao userDao;
  @Inject
  private ProductDao productDao;
  @Inject
  private MessageDao messageDao;
  @Inject
  private RelationDao relationDao;
  @Inject
  private TypeDao typeDao;

  /**
   * 注册新的用户
   * @param UserEntity
   * @return userId 如果注册失败就返回0
   * */
  public UserBean saveNewUserService(UserBean userBean) throws Exception {
    return userDao.insertNewUserDao(CommonUtil.changeUserBeanToUserEntity(userBean));
  }
  
  /**
   * 用户登陆
   * */
  public UserBean doLoginService(UserBean userBean) throws Exception {
    return userDao.selectUserByUserNameAndPassWordDao(
        CommonUtil.changeUserBeanToUserEntity(userBean));
  }

  /**
   * 检查用户名是否注册
   * */
  public boolean checkUserNameIsExistService(String userName) throws Exception {
    return userDao.checkUserNameIsExistDao(userName);
  }

  /**
   * 获取所有商品的信息
   * */
  public List<ProductBean> getAllProductService() throws Exception {
    List<ProductBean> productBeans = productDao.selectAllProductDao();
    for(int i=0; i<productBeans.size();i++){
      ProductBean productBean = new ProductBean();
      productBean = productBeans.get(i);
      productBean.setUserBean(userDao.selectUserByUserIdDao(
          productBean.getUserId()));
      productBeans.remove(i);
      productBeans.add(i, productBean);
    }
    return productBeans;
  }

  /**
   * 保存新的商品
   * @return productId
   * */
  public boolean saveNewProductService(ProductBean productBean) throws Exception {
    ProductEntity productEntity = CommonUtil.changeProductBeanToProductEntity(productBean);
    int productId = productDao.insertNewProductDao(productEntity);
    if(productId != 0){
      RelationEntity relationEntity = new RelationEntity();
      relationEntity.setProductId(productId);
      relationEntity.setTypeId(productBean.getTypeId());
      boolean bool = relationDao.insertNewReationDao(relationEntity);
      if(!bool){
        return false;
      }
    }
    else{
      return false;
    }
    return true;
  }

  /**
   * 根据类型查询商品
   * */
  public List<ProductBean> getProductsByTypeIdService(int typeId) throws Exception {
    List<ProductBean> productBeans = productDao.selectProductsByTypeIdDao(typeId);
    for(int i=0; i<productBeans.size();i++){
      ProductBean productBean = new ProductBean();
      productBean = productBeans.get(i);
      productBean.setUserBean(userDao.selectUserByUserIdDao(
          productBean.getUserId()));
      productBeans.remove(i);
      productBeans.add(i, productBean);
    }
    return productBeans;
  }

  /**
   * 修改商品信息
   * */
  public boolean updateProductInfoService(ProductBean productBean) throws Exception {
    boolean bool = productDao.updateProductInfoDao(
        CommonUtil.changeProductBeanToProductEntity(productBean));
    if(bool){
      RelationEntity relationEntity = new RelationEntity();
      relationEntity.setProductId(productBean.getProductId());
      relationEntity.setTypeId(productBean.getTypeId());
      bool = relationDao.updateRelationDao(relationEntity);
    }
    return bool;
  }

  /**
   * 查询用户自己发布的商品
   * */
  public List<ProductBean> getProductsByUserService(UserBean userBean) throws Exception {
    List<ProductBean> productBeans = productDao.selectProductsByUserIdDao(userBean.getUserId());
    for(int i=0;i<productBeans.size();i++){
      ProductBean productBean = new ProductBean();
      productBean = productBeans.get(i);
      productBean.setUserBean(userBean);
      productBeans.remove(i);
      productBeans.add(i, productBean);
    }
    return productBeans;
  }

  /**
   * 根据商品Id获取商品信息
   * */
  public ProductBean getProductByProductIdService(int productId) throws Exception {
    return productDao.selectProductByProductIdDao(productId);
  }

  /**
   * 下架商品
   * */
  public boolean deleteProductByProductIdService(int productId) throws Exception {
    return productDao.updateStateByProductIdDao(productId);
  }

  /**
   * 根据用户ID获取用户信息
   * */
  public UserBean getUserByUserIdService(int userId) throws Exception {
    return userDao.selectUserByUserIdDao(userId);
  }

  /**
   * 保存新的信息
   * */
  public boolean saveNewMessageService(MessageBean messageBean) throws Exception {
    return messageDao.insertNewMessageDao(CommonUtil.changeMessageBeanToMessageEntity(messageBean));
  }

  /**
   * 获取未读消息列表
   * */
  public List<MessageBean> getUnReadedMessagesService(UserBean loginBean) throws Exception {
    List<MessageBean> messageBeans = messageDao.selectUnReadMessageDao(
        loginBean.getUserId());
    for(int i=0;i<messageBeans.size();i++){
      MessageBean messageBean = new MessageBean();
      messageBean = messageBeans.get(i);
      UserBean userBean = userDao.selectUserByUserIdDao(messageBean.getSendId());
      messageBean.setSendUser(userBean);
      messageBean.setReceiveUser(loginBean);
      messageBeans.remove(i);
      messageBeans.add(i, messageBean);
    }
    return messageBeans;
  }

  /**
   * 获取已读消息列表
   * */
  public List<MessageBean> getReadedMessagesService(UserBean loginBean) throws Exception {
    List<MessageBean> messageBeans = messageDao.selectReadMessageDao(
        loginBean.getUserId());
    for(int i=0;i<messageBeans.size();i++){
      MessageBean messageBean = new MessageBean();
      messageBean = messageBeans.get(i);
      UserBean userBean = userDao.selectUserByUserIdDao(messageBean.getSendId());
      messageBean.setSendUser(userBean);
      messageBean.setReceiveUser(loginBean);
      messageBeans.remove(i);
      messageBeans.add(i, messageBean);
    }
    return messageBeans;
  }

  /**
   * 获取发送消息列表
   * */
  public List<MessageBean> getSendedMessagesService(UserBean loginBean) throws Exception {
    List<MessageBean> messageBeans = messageDao.selectSendMessageDao(
        loginBean.getUserId());
    for(int i=0;i<messageBeans.size();i++){
      MessageBean messageBean = new MessageBean();
      messageBean = messageBeans.get(i);
      UserBean userBean = userDao.selectUserByUserIdDao(messageBean.getReceiveId());
      messageBean.setSendUser(loginBean);
      messageBean.setReceiveUser(userBean);
      messageBeans.remove(i);
      messageBeans.add(i, messageBean);
    }
    return messageBeans;
  }

  /**
   * 读消息
   * */
  public MessageBean toReadMessageService(int messageId) throws Exception {
    MessageBean messageBean = messageDao.selectMessageByMessageIdDao(messageId);
    UserBean sendUser = userDao.selectUserByUserIdDao(messageBean.getSendId());
    UserBean receiveUser = userDao.selectUserByUserIdDao(messageBean.getReceiveId());
    messageBean.setSendUser(sendUser);
    messageBean.setReceiveUser(receiveUser);
    if(messageBean.getState() == 0){
      messageDao.updateMessageToReadedByMessageIdDao(messageId);
    }
    return messageBean;
  }

  /**
   *  修改用户信息
   * */
  public boolean updateUserInfoService(UserBean userBean, String oldPSW,String userName) throws Exception {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(userName);
    userEntity.setPassWord(oldPSW);
    UserBean checkUser = userDao.selectUserByUserNameAndPassWordDao(userEntity);
    if(checkUser != null && !ValidateUtil.strIsEmpty(checkUser.getName())){
      boolean bool = userDao.updateUserInfoDao(
          CommonUtil.changeUserBeanToUserEntity(userBean));
      if(bool){
        return true;
      }
    }
    return false;
  }

  /**
   * 获取未查看消息数量
   * */
  public int getUnReadedMessageCountService(int userId) throws Exception {
    List<MessageBean> messageBeans = messageDao.selectUnReadMessageDao(userId);
    return messageBeans.size();
  }
}
