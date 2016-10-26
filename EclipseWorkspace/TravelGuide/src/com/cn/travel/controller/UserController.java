package com.cn.travel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cn.travel.bean.SightBean;
import com.cn.travel.bean.TypeBean;
import com.cn.travel.bean.UserBean;
import com.cn.travel.service.UserService;
import com.cn.travel.util.Base64;


@Controller
@RequestMapping("/user")
public class UserController {

  @Inject
  public UserService userService;
  
  /**
   * 首页
   */
  @RequestMapping("Home")
  public String Home(HttpServletRequest request) throws Exception{
    return "user/home";
  }
  
  /**
   * 地图展示iframe
   */
  @RequestMapping("MapIframe")
  public String MapIframe(HttpServletRequest request) throws Exception{
    return "user/iframemap";
  }
  
  /**
   * 用户登录后地图展示页面
   */
  @RequestMapping("showAccountMap")
  public String ShowAccountMap(HttpServletRequest request) throws Exception{
    String userName = request.getParameter("username");
    String userName_base = Base64.Decode(userName);   //对用户名进行解密
    request.setAttribute("username", userName_base);
    return "user/accountmap";
  }
  
  /**
   * 访客模式地图展示页面
   */
  @RequestMapping("showVisitorMap")
  public String ShowVisitorMap(HttpServletRequest request) throws Exception{
    return "user/visitormap";
  }
  
  /**
   * 用户注册
   * */
  @RequestMapping("/doRegistUser")
  public String doRegistUser(HttpServletRequest request)throws Exception{
    String userName = request.getParameter("userName");
    String passWord = request.getParameter("passWord");
    UserBean userBean = new UserBean();
    userBean.setUserName(userName);
    userBean.setPassWord(passWord);
    boolean bool = userService.checkUserNameIsExistService(userName);
    if(bool){
      System.out.println("改用户名已经存在");
      request.setAttribute("bool", bool);
    }
    else{
      UserBean loginBean = userService.doRegistUserService(userBean);
      if(loginBean == null || loginBean.getUserId() ==0){
        System.out.println("注册失败");//具体操作，要改
        request.setAttribute("userId", loginBean.getUserId());
      }
      else{
        System.out.println("注册成功");
      }
    }
    return "user/doRegist";
  }
  
  /**
   * 获取全部标签
   * 需要获取标签列表，直接将下面的代码粘过去
   * */
  @RequestMapping("/getAllTypes")
  public String getAllTypes(HttpServletRequest request)throws Exception{
    List<TypeBean> typeBeans = new ArrayList<TypeBean>();
    typeBeans = userService.getAllTypesService();
    request.setAttribute("typeBeans", typeBeans);
    return "test/result";
  }
  
  /**
   * 根据地址查询景点，没有找到的话
   * */
  @RequestMapping("/getSightsByDistrict")
  public String getSightsByDistrict(HttpServletRequest request)throws Exception{
    String returnValue = "test/result";
//    String district = request.getParameter("district");//根据区域查询景点，区域名从
    String district = "双流县";
    List<SightBean> sightBeans = userService.getSightsByDistrictService(district);
    
    //测试数据是否被取出
    for(int i=0;i<sightBeans.size();i++){
      System.out.println(sightBeans.get(i).getSightId()+"typeSize"+sightBeans.get(i).getTypeBeans().size());
    }
    return returnValue;
  }
  
  /**
   * 根据标签Id查询景点
   * */
  @RequestMapping("/getSightsByTypeId")
  public String getSightsByTypeId(HttpServletRequest request)throws Exception{
    //String typeId = request.getParameter("typeId");
    int typeId=1;
    //根据标签Id查询景点
    List<SightBean> sightBeans = userService.getSightsByTypeIdService(typeId);
    
    //测试数据是否被取出
    for(int i=0;i<sightBeans.size();i++){
      System.out.println(sightBeans.get(i).getSightId()+"typeSize"+sightBeans.get(i).getTypeBeans().size());
    }
    return "test/result";
  }
  
  /**
   * 根据标签名称来查询标签
   * */
  @RequestMapping("/getSightsByTypeName")
  public String getSightsByTypeName(HttpServletRequest request)throws Exception{
    String typeName = request.getParameter("typeName");
    System.out.println(typeName);
    //String typeName = "标签11";
    List<SightBean> sightBeans = userService.getSightsByTypeNameService(typeName);
    //要是穿过来的标签名不是我们的数据库里面的标签。得到的sightBeans的size为0
    System.out.println("得到的地点个数："+sightBeans.size());
    //测试数据是否被取出
    for(int i=0;i<sightBeans.size();i++){
      int sightId = sightBeans.get(i).getSightId();
      String sightName = sightBeans.get(i).getSightName();
      Double sightLng = sightBeans.get(i).getSightLng();  //经度
      Double sightLat = sightBeans.get(i).getSightLat();  //纬度
      System.out.println("id："+sightId +"景点名称："+sightName+"经度："+sightLng+"纬度："+sightLat);
    }
    request.setAttribute("sightBeans", sightBeans);
    return "user/iframemap";
  }
  
  /**
   * 用户登录
   * */
  @RequestMapping("/doLogin")
  public String doLogin(HttpServletRequest request)throws Exception{
    String userName = request.getParameter("username");
    String passWord = request.getParameter("pass");
    UserBean userBean = new UserBean();
    userBean.setUserName(userName);
    userBean.setPassWord(passWord);
    UserBean loginBean = userService.doLoginService(userBean);
    request.getSession().setAttribute("loginBean", loginBean);
    request.setAttribute("userId", loginBean.getUserId());
    request.setAttribute("userName", loginBean.getUserName());
    return "user/doLogin";
  }
  
  /**
   * 根据景点名查询景点
   * */
  @RequestMapping("/getSightsBySightName")
  public String getSightsBySightName(HttpServletRequest request)throws Exception{
    String sightName = "山";
    List<SightBean> sightBeans = userService.getSightsBySightNameService(sightName);
    System.out.println(sightBeans.size());
    for(int i=0;i<sightBeans.size();i++){
      System.out.println(sightBeans.get(i).getSightName());
      System.out.println(sightBeans.get(i).getTypeBeans().size());
    }
    return "test/result";
  }
  
}