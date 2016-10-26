package com.cn.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.travel.bean.SightBean;
import com.cn.travel.bean.SightTypeRelationBean;
import com.cn.travel.bean.TypeBean;
import com.cn.travel.bean.UserBean;
import com.cn.travel.dao.SightDao;
import com.cn.travel.dao.SightTypeRelationDao;
import com.cn.travel.dao.TypeDao;
import com.cn.travel.dao.UserDao;
import com.cn.travel.entity.UserEntity;
import com.cn.travel.service.UserService;
import com.cn.travel.util.CommonUtil;

/**
 * 用户模块，服务层接口实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Inject
  private UserDao userDao;

  @Inject
  private TypeDao typeDao;
  
  @Inject
  private SightTypeRelationDao sightTypeRelationDao;
  
  @Inject
  private SightDao sightDao;
  /**
   * 注册用户
   * */
  public UserBean doRegistUserService(UserBean userBean) throws Exception {
    return userDao.insertNewUserDao(CommonUtil.changeUserBeanToUserEntity(userBean));
  }

  /***
   * 获取标签列表
   * */
  public List<TypeBean> getAllTypesService() throws Exception {
    return typeDao.selectAllTypesDao();
  }

  /**
   * 根据地区获取景点列表
   * */
  public List<SightBean> getSightsByDistrictService(String district) throws Exception {
    List<SightBean> sightBeans = sightDao.getSightByDistrictDao(district);
    //查找景点的标签列表
    for(int i=0;i<sightBeans.size();i++){
      SightBean sightBean = new SightBean();
      sightBean = sightBeans.get(i);
      List<SightTypeRelationBean> relationBeans = sightTypeRelationDao.
          getRelationsBySightIdDao(sightBean.getSightId());
      List<TypeBean> typeBeans = new ArrayList<TypeBean>();
      for(int j=0;j<relationBeans.size();j++){
        TypeBean typeBean = typeDao.selectTypeByTypeIdDao(
            relationBeans.get(j).getTypeId());
        typeBeans.add(typeBean);
      }
      sightBean.setTypeBeans(typeBeans);
      sightBeans.remove(i);
      sightBeans.add(i, sightBean);;
    }
    return sightBeans;
  }

  /**
   * 根据typeId查询景点
   * */
  public List<SightBean> getSightsByTypeIdService(int typeId) throws Exception {
    List<SightTypeRelationBean> sightTypeRelationBeans = sightTypeRelationDao.
        selectRelationsByTypeIdDao(typeId);
    List<SightBean> sightBeans = new ArrayList<SightBean>();
    for(int m=0;m<sightTypeRelationBeans.size();m++){
      SightBean bean = new SightBean();
      bean = sightDao.selectSightsBySightIdDao(sightTypeRelationBeans.get(m).getSightId());
      sightBeans.add(bean);
    }
    //查找景点的标签列表
    for(int i=0;i<sightBeans.size();i++){
      SightBean sightBean = new SightBean();
      sightBean = sightBeans.get(i);
      List<SightTypeRelationBean> relationBeans = sightTypeRelationDao.
          getRelationsBySightIdDao(sightBean.getSightId());
      List<TypeBean> typeBeans = new ArrayList<TypeBean>();
      for(int j=0;j<relationBeans.size();j++){
        TypeBean typeBean = typeDao.selectTypeByTypeIdDao(
            relationBeans.get(j).getTypeId());
        typeBeans.add(typeBean);
      }
      sightBean.setTypeBeans(typeBeans);
      sightBeans.remove(i);
      sightBeans.add(i, sightBean);;
    }
    return sightBeans;
  }

  /**
   * 根据标签名获取景点
   * */
  public List<SightBean> getSightsByTypeNameService(String typeName) throws Exception {
    TypeBean inType = new TypeBean();
    //根据标签名获取标签Id
    inType = typeDao.selectTypeByTypeNameDao(typeName);
    List<SightBean> sightBeans = new ArrayList<SightBean>();
    if(inType != null && inType.getTypeId() != 0){
      sightBeans = getSightsByTypeIdService(inType.getTypeId());
    }
    return sightBeans;
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
   * 根据景点名查询景点
   * */
  public List<SightBean> getSightsBySightNameService(String sightName) throws Exception {
    List<SightBean> sightBeans = sightDao.selectSightsBySightNameDao(sightName);
    //查找景点的标签列表
    for(int i=0;i<sightBeans.size();i++){
      SightBean sightBean = new SightBean();
      sightBean = sightBeans.get(i);
      List<SightTypeRelationBean> relationBeans = sightTypeRelationDao.
          getRelationsBySightIdDao(sightBean.getSightId());
      List<TypeBean> typeBeans = new ArrayList<TypeBean>();
      for(int j=0;j<relationBeans.size();j++){
        TypeBean typeBean = typeDao.selectTypeByTypeIdDao(
            relationBeans.get(j).getTypeId());
        typeBeans.add(typeBean);
      }
      sightBean.setTypeBeans(typeBeans);
      sightBeans.remove(i);
      sightBeans.add(i, sightBean);;
    }
    return sightBeans;
  }
}
