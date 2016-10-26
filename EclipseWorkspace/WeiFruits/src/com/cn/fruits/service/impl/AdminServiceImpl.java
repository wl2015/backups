package com.cn.fruits.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.fruits.bean.AdminBean;
import com.cn.fruits.bean.CampusBean;
import com.cn.fruits.bean.DormitoryBean;
import com.cn.fruits.bean.FruitBean;
import com.cn.fruits.bean.FruitListBean;
import com.cn.fruits.bean.NoticeBean;
import com.cn.fruits.bean.OrdersBean;
import com.cn.fruits.dao.AdminDao;
import com.cn.fruits.dao.CampusDao;
import com.cn.fruits.dao.DormitoryDao;
import com.cn.fruits.dao.FruitDao;
import com.cn.fruits.dao.FruitListDao;
import com.cn.fruits.dao.NoticeDao;
import com.cn.fruits.dao.OrdersDao;
import com.cn.fruits.entity.CampusEntity;
import com.cn.fruits.entity.DormitoryEntity;
import com.cn.fruits.entity.FruitEntity;
import com.cn.fruits.entity.NoticeEntity;
import com.cn.fruits.service.AdminService;
import com.cn.fruits.util.CommonUtil;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

  @Inject
  private AdminDao adminDao;
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
  
  public AdminBean doLoginService(AdminBean admin) throws Exception {
    AdminBean loginBean = new AdminBean();
    loginBean = adminDao.doLoginDao(CommonUtil.changeAdminBeanIntoAdminEntity(admin));
    return loginBean;
  }
  
  public boolean saveFruitService(FruitBean fruitBean) throws Exception{
    FruitEntity fruitEntity = new FruitEntity();
    fruitEntity.setFruitName(fruitBean.getFruitName());
    fruitEntity.setFruitPrice(fruitBean.getFruitPrice());
    fruitEntity.setFruitIntr(fruitBean.getFruitIntr());
    fruitEntity.setFruitDetail(fruitBean.getFruitDetail());
    fruitEntity.setFruitPic(fruitBean.getFruitPic());
    return fruitDao.saveFruitDao(fruitEntity);
  }
  
  /**
   * 保存新的公告，成功返回true，失败返回false
   * */
  public boolean saveNoticeService(NoticeBean noticeBean) throws Exception{
    NoticeEntity noticeEntity = new NoticeEntity();
    noticeEntity.setNoticeContent(noticeBean.getNoticeContent());
    return noticeDao.insertNewNoticeDao(noticeEntity);
  }

  /**
   * 获取所有的公告
   * */
  public List<NoticeBean> getALlNoticesService() throws Exception {
    return noticeDao.selectAllNoticesDao();
  }

  /**
   * 根据公告ID删除公告
   * */
  public boolean deleteNoticeByNoticeIdService(int noticeId) throws Exception {
    return noticeDao.deleteNoticeByNoticeIdDao(noticeId);
  }

  /**
   * 保存新的校区
   * */
  public boolean saveNewCampusService(String campusName) throws Exception {
    return campusDao.insertNewCampusDao(campusName);
  }

  /***
   * 获取所有校区
   * */
  public List<CampusBean> getAllCampusesService() throws Exception {
    return campusDao.selectAllCampusesDao();
  }

  /**
   * 根据campusId删除campus
   * */
  public boolean deleteCampusByCampusIdService(int campusId) throws Exception {
    return campusDao.deleteCampusByCampusIdDao(campusId);
  }

  /**
   * 修改campusName
   * */
  public boolean updateCampusNameService(CampusBean campusBean) throws Exception {
    CampusEntity campusEntity = new CampusEntity();
    campusEntity.setCampusId(campusBean.getCampusId());
    campusEntity.setCampusName(campusBean.getCampusName());
    return campusDao.updateCampusNameDao(campusEntity);
  }

  /**
   * 添加新的Dormitory（宿舍楼）
   * */
  public boolean addNewDormitoryService(DormitoryBean dormitoryBean) throws Exception {
    DormitoryEntity dormitoryEntity = new DormitoryEntity();
    dormitoryEntity.setCampusId(dormitoryBean.getCampusId());
    dormitoryEntity.setDormitoryName(dormitoryBean.getDormitoryName());
    return dormitoryDao.insertNewDormitoryDao(dormitoryEntity);
  }

  /**
   * 查询所有的宿舍楼
   * */
  public List<DormitoryBean> getAllDormitoryService() throws Exception {
    List<DormitoryBean> dormitoryBeans = new ArrayList<DormitoryBean>();
    dormitoryBeans = dormitoryDao.seletDormitoriesDao();
    for(int i=0;i<dormitoryBeans.size();i++){
      DormitoryBean dormitoryBean = new DormitoryBean();
      dormitoryBean = dormitoryBeans.get(i);
      dormitoryBean.setCampusName(campusDao.selectCampusNameByCampusIdDao(
          dormitoryBean.getCampusId()));
      dormitoryBeans.remove(i);
      dormitoryBeans.add(i, dormitoryBean);
    }
    return dormitoryBeans;
  }

  /**
   * 根据宿舍楼Id删除宿舍楼
   * */
  public boolean deleteDormitoryByDormitoryIdService(int dormitoryId) throws Exception {
    return dormitoryDao.deleteDormitoryByDormitoryIdDao(dormitoryId);
  }

  /**
   * 修改宿舍楼名
   * */
  public boolean updateDormitoryNameService(DormitoryBean dormitoryBean) throws Exception {
    DormitoryEntity dormitoryEntity = new DormitoryEntity();
    dormitoryEntity.setDormitoryId(dormitoryBean.getDormitoryId());
    dormitoryEntity.setDormitoryName(dormitoryBean.getDormitoryName());
    return dormitoryDao.updateDormitoryNameDao(dormitoryEntity);
  }

  /**
   * 根据页码获取菜品信息
   * */
  public List<FruitBean> getFruitsByPageNumService(int pageNum) throws Exception {
    return fruitDao.selectFruitsByPageNumDao(pageNum);
  }

  /**
   * 根据水果ID删除水果
   * 删除水果是把水果的状态改为下架
   * */
  public boolean deleteFruitByFruitIdService(int fruitId) throws Exception {
    return fruitDao.deleteFruitByFruitIdDao(fruitId);
  }

  /**
   * 根据fruitId查询fruit信息
   * */
  public FruitBean getFruitByFruitIdService(int fruitId) throws Exception {
    return fruitDao.selectFruitByFruitIdDao(fruitId);
  }

  /**
   * 根据fruitId修改fruit信息
   * */
  public boolean updateFruitInfoByFruitIdService(FruitBean fruitBean) throws Exception {
    return fruitDao.updateFruitInfoByFruitIdDao(fruitBean);
  }

  /**
   * 根据页码获取已完成订单列表
   * */
  public List<OrdersBean> selectFinishedOrdersByPageNumService(int pageNum) throws Exception {
    return ordersDao.selectFinishedOrdersByPageNumDao(pageNum);
  }

  /**
   * 根据orderId删除order
   * */
  public boolean deleteOrderByOrderIdService(int orderId) throws Exception {
    return ordersDao.deleteOrderbyOrderIdDao(orderId);
  }

  /**
   * 根据订单Id获取订单信息
   * */
  public OrdersBean getOrderInfoByOrderIdService(int orderId) throws Exception {
    OrdersBean ordersBean = new OrdersBean();
    ordersBean = ordersDao.selectOrderByOrderIdDao(orderId);
    List<FruitListBean> fruitListBeans = fruitListDao.selectFruitListsByOrderIdDao(orderId);
    List<FruitBean> fruitBeans = new ArrayList<FruitBean>();
    for(int i=0;i<fruitListBeans.size();i++){
      FruitBean fruitBean = new FruitBean();
      fruitBean = fruitDao.selectFruitByFruitIdDao(
          fruitListBeans.get(i).getFruitId());
      fruitBean.setCount(fruitListBeans.get(i).getCount());
      fruitBeans.add(fruitBean);
    }
    ordersBean.setFruitBeans(fruitBeans);
    return ordersBean;
  }

  /**
   * 根据页码获取未完成订单列表
   * */
  public List<OrdersBean> selectUnFinishedOrdersByPageNumService(int pageNum) throws Exception {
    return ordersDao.selectUnFinishedOrdersByPageNumDao(pageNum);
  }

  /**
   * 接受订单
   * */
  public boolean updateOrderStatusToAcceptByOrderIdService(int orderId) throws Exception {
    return ordersDao.updateOrderStatusToAcceptByOrderIdDao(orderId);
  }
  
  /**
   * 完成订单
   * */
  public boolean updateOrderStatusToFinishByOrderIdService(int orderId) throws Exception {
    return ordersDao.updateOrderStatusToFinishByOrderIdDao(orderId);
  }

  /**
   *  获取还未接受订单的数量
   * */
  public int getUnAcceptOrdersCountService() throws Exception {
    return ordersDao.selectUnAceeptOrdersCountDao();
  }

  /***
   *  根据adminId修改密码
   *  修改成功返回true，失败返回false
   * */
  public boolean changeAdminPassWordByAdminIdService(int adminId, String passWord) throws Exception {
    return adminDao.updateAdminPassWordByAdminIdDao(adminId, passWord);
  }
}
