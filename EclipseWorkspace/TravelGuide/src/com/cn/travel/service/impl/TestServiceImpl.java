package com.cn.travel.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.travel.bean.SightBean;
import com.cn.travel.dao.SightDao;
import com.cn.travel.entity.SightEntity;
import com.cn.travel.service.TestService;
import com.cn.travel.util.CommonUtil;

@Service
@Transactional
public class TestServiceImpl implements TestService {
  @Inject
  private SightDao sightDao;

  @Override
  public boolean saveNewSightService(SightEntity sightEntity) throws Exception {
    return sightDao.saveNewSightDao(sightEntity);
  }

  @Override
  public List<SightBean> getSightsService() throws Exception {
    return sightDao.selectSightsDao();
  }

  public boolean updateSightAddress(SightBean sightBean) throws Exception {
    return sightDao.updateAddressAndDistrictDao(CommonUtil.changeSightBeanToSightEntity(sightBean));
  }
}
