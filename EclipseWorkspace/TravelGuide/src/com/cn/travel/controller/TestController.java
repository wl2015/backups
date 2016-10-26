package com.cn.travel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.travel.bean.SightBean;
import com.cn.travel.bean.UserBean;
import com.cn.travel.entity.SightEntity;
import com.cn.travel.service.TestService;
import com.cn.travel.service.UserService;
import com.cn.travel.util.ValidateUtil;

@Controller
@RequestMapping("/test")
public class TestController {
  @Inject
  public TestService testService;
  
  @RequestMapping("toMap")
  public String toMap(HttpServletRequest request)throws Exception{
    return "/test/getMapInfo";
  }
  
  /**
   * 获取景点信息
   * */
  @RequestMapping("/getInfo")
  @ResponseBody
  public Map<String, Object> getInfo(HttpServletRequest request)throws Exception{
    Map<String, Object> returnMap = new HashMap<String, Object>();
    String lng = request.getParameter("lng");
    String lat = request.getParameter("lat");
    String address = request.getParameter("address");
    String district = request.getParameter("district");
    System.out.println("lng:"+lng);
    System.out.println("lat:"+lat);
    System.out.println("address:"+address);
    System.out.println("district:"+district);
    SightEntity sightEntity = new SightEntity();
    sightEntity.setSightName("new");
    sightEntity.setSightLng(Double.valueOf(lng));
    sightEntity.setSightLat(Double.valueOf(lat));
    sightEntity.setSightIntro("这是新的景点");
    sightEntity.setAddress(address);
    sightEntity.setDistrict(district);
    testService.saveNewSightService(sightEntity);
    return returnMap;
  }
  @RequestMapping("/toAddress")
  public String toAddress(HttpServletRequest request)throws Exception{
    List<SightBean> sightBeans = new ArrayList<SightBean>();
    sightBeans = testService.getSightsService();
    request.setAttribute("sightBeans", sightBeans);
    return "test/getAddress";
  }
  
  @RequestMapping("/upDateSight")
  @ResponseBody
  public Map<String, Object> upDateSight(HttpServletRequest request)throws Exception{
    Map<String, Object> retrunMap = new HashMap<String, Object>();
    String sightId = request.getParameter("sightId");
    String address = request.getParameter("address");
    String district = request.getParameter("district");
    SightBean sightBean = new SightBean();
    sightBean.setSightId(Integer.parseInt(sightId));
    sightBean.setAddress(address);
    sightBean.setDistrict(district);
    boolean bool = testService.updateSightAddress(sightBean);
    if(bool){
      retrunMap.put("result", "成功");
    }
    else{
      retrunMap.put("result", "失败");
    }
    return retrunMap;
  }
  
}
