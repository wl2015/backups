package com.cn.fruits.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.FruitListBean;
import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.bean.UserBean;
import com.cn.fruits.dao.CampusDao;
import com.cn.fruits.dao.DormitoryDao;
import com.cn.fruits.dao.FruitDao;
import com.cn.fruits.dao.FruitListDao;
import com.cn.fruits.dao.NoticeDao;
import com.cn.fruits.dao.OrdersDao;
import com.cn.fruits.dao.UserDao;
import com.cn.fruits.entity.FruitListEntity;
import com.cn.fruits.entity.OrdersEntity;
import com.cn.fruits.entity.UserEntity;
import com.cn.fruits.service.UserService;
import com.cn.fruits.util.CommonUtil;
import com.cn.fruits.util.Constants;
import com.cn.fruits.util.DateUtil;

/**
 * 用户模块，服务层接口实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Inject
  private UserDao userDao;
  @Inject
  private FruitDao fruitDao;
  @Inject
  private NoticeDao noticeDao;
  @Inject
  private CampusDao campusDao;
  @Inject
  private DormitoryDao dormitoryDao;
  @Inject
  private OrdersDao ordersDao;
  @Inject
  private FruitListDao fruitListDao;

  public List<FruitBean> getFruitsListService() throws Exception {
    return fruitDao.selectAllFruitsDao();
  }

  public NoticeBean getNewestNoticeService() throws Exception {
    return noticeDao.selectNewestNoticeDao();
  }

  /**
   * 获取所有的校区列表
   */
  public List<CampusBean> getAllCampusessService() throws Exception {
    return campusDao.selectAllCampusesDao();
  }

  /**
   * 通过校区ID查询出该校区的所有宿舍楼
   */
  public List<DormitoryBean> getDormitoriesDaoByCampusIdService(int campusId) throws Exception {
    return dormitoryDao.selectDormitoriesDaoByCampusIdDao(campusId);
  }

  /**
   * 保存新的订单
   * 成功返回True，失败返回false
   * */
  public boolean saveNewOrdersService(OrdersBean ordersBean, List<FruitBean> fruitBeans) throws Exception {
    //菜单列表如果为空，返回fasle
    if(fruitBeans == null){
      return false;
    }
    Double total = 0.0;
    for(int i=0;i<fruitBeans.size();i++){
      total = total + fruitBeans.get(i).getCount() * fruitBeans.get(i).getFruitPrice();
    }
    //添加配送费
    if(ordersBean.getReceiveWay() == Constants.DATA_ORDERS_RECEIVE_WAY_SEND){
      total = total + Constants.DATA_ORDERS_SEND_COST;
    }
    OrdersEntity ordersEntity = new OrdersEntity();
    ordersEntity.setUserId(ordersBean.getUserId());
    ordersEntity.setReceiveName(ordersBean.getReceiveName());
    ordersEntity.setReceivePhone(ordersBean.getReceivePhone());
    ordersEntity.setCampusName(ordersBean.getCampusName());
    ordersEntity.setDormitoryName(ordersBean.getDormitoryName());
    ordersEntity.setAddress(ordersBean.getAddress());
    ordersEntity.setMoney(total);
    ordersEntity.setOrderTime(DateUtil.getNowTime());
    ordersEntity.setPayWay(ordersBean.getPayWay());
    ordersEntity.setReceiveWay(ordersBean.getReceiveWay());
    ordersEntity.setPayStatus(ordersBean.getPayStatus());
    ordersEntity.setOrderStatus(ordersBean.getOrderStatus());
    ordersEntity.setMessge(ordersBean.getMessge());
    //保存订单
    int orderId = ordersDao.saveNewOrdersAndReturnOrdersIdDao(ordersEntity);
    if(orderId == 0){
      return false;
    }
    
    //把orderId添加进fruitlist
    List<FruitListEntity> fruitListEntities = new ArrayList<FruitListEntity>();
    for(int j=0;j<fruitBeans.size();j++){
      FruitListEntity fruitListEntity = new FruitListEntity();
      fruitListEntity.setFruitId(fruitBeans.get(j).getFruitId());
      fruitListEntity.setCount(fruitBeans.get(j).getCount());
      fruitListEntity.setOrderId(orderId);
      fruitListEntities.add(fruitListEntity);
    }
    boolean bool = fruitListDao.saveFruitListDao(fruitListEntities);
    if(!bool){
      return false;
    }
    return true;
  }

  /**
   * 根据校区Id获取校区名称
   * */
  public String getCampusNameByCampusIdService(int campusId) throws Exception {
    return campusDao.selectCampusNameByCampusIdDao(campusId);
  }

  /**
   * 该手机号码是否存在，存在返回true，不存在返回false
   * */
  public boolean userNameisExistService(String userName) throws Exception {
    return userDao.userNameisExistDao(userName);
  }

  /**
   *  注册新的用户
   *  注册成功返回用户id
   * */
  public int saveNewUserService(UserBean userBean) throws Exception {
    UserEntity userEntity = CommonUtil.changeUserBeanIntoUserEntity(userBean);
    return userDao.insertNewUserDao(userEntity);
  }

  /**
   * 根据用户名和密码查询用户id
   * */
  public int getUserIdByUserNameAndPassWordService(String userName, String passWord) throws Exception {
    return userDao.selectUserIdByUserNameAndPassWordDao(userName, passWord);
  }

  /**
   * 根据用户id获取未完成订单
   * */
  public List<OrdersBean> getUnFinishOrdersByUserIdService(int userId) throws Exception {
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    ordersBeans = ordersDao.selectUnFinishOrdersByUserIdDao(userId);
    for(int i=0;i<ordersBeans.size();i++){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = ordersBeans.get(i);
      List<FruitListBean> fruitListBeans = fruitListDao.selectFruitListsByOrderIdDao(
          ordersBean.getOrderId());
      List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
      for(int j=0;j<fruitListBeans.size();j++){
        FruitBean fruitBean = new FruitBean();
        fruitBean = fruitDao.selectFruitByFruitIdDao(
            fruitListBeans.get(j).getFruitId());
        fruitBean.setCount(fruitListBeans.get(j).getCount());
        fruitBeans.add(fruitBean);
      }
      ordersBean.setFruitBeans(fruitBeans);
      ordersBeans.remove(i);
      ordersBeans.add(i, ordersBean);
    }
    return ordersBeans;
  }

  /**
   * 根据用户id获取已完成订单
   * */
  public List<OrdersBean> getFinishOrdersByUserIdService(int userId) throws Exception {
    List<OrdersBean> ordersBeans = new ArrayList<OrdersBean>();
    ordersBeans = ordersDao.selectFinishOrdersByUserIdDao(userId);
    for(int i=0;i<ordersBeans.size();i++){
      OrdersBean ordersBean = new OrdersBean();
      ordersBean = ordersBeans.get(i);
      List<FruitListBean> fruitListBeans = fruitListDao.selectFruitListsByOrderIdDao(
          ordersBean.getOrderId());
      List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
      for(int j=0;j<fruitListBeans.size();j++){
        FruitBean fruitBean = new FruitBean();
        fruitBean = fruitDao.selectFruitByFruitIdDao(
            fruitListBeans.get(j).getFruitId());
        fruitBean.setCount(fruitListBeans.get(j).getCount());
        fruitBeans.add(fruitBean);
      }
      ordersBean.setFruitBeans(fruitBeans);
      ordersBeans.remove(i);
      ordersBeans.add(i, ordersBean);
    }
    return ordersBeans;
  }

  /**
   * 根据用户id获取用户信息
   * */
  public UserBean getUserByUserIdService(int userId) throws Exception {
    return userDao.selectUserByUserIdDao(userId);
  }
  
  /**
   * 根据userId修改用户名
   * */
  public boolean updateUserNameByUserIdService(int userId,String name)throws Exception{
    return userDao.updateUserNameByUserIdDao(userId, name);
  }
}
