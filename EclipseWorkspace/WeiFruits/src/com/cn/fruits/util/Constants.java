package com.cn.fruits.util;

public class Constants {
  
  //第三方用户唯一凭证
  public static String appid = "wx1992d13458b079ff";
  // 第三方用户唯一凭证密钥
  public static String appsecret = "abd150d2d78863ae94e37d01757577ae";
  //商户ID
  public static String mch_id="";
  //获取openId
  public static String oauth_url = "https://api.weixin.qq.com/sns/oauth/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

  /***
   * 常用参数
   * */
  
  public static final String RESULT = "result";
  //快递的配送费
  public static final Double DATA_ORDERS_SEND_COST = 0.0;
  //快递的配送方式,送货上门
  public static final int DATA_ORDERS_RECEIVE_WAY_SEND = 0;
  //快递的配送方式，顾客自取
  public static final int DATA_ORDERS_RECEIVE_WAY_SELF = 1;
  //错误信息回馈，数据为空
  public static final String DATA_NULL = "数据为空";
  //操作成功
  public static final String OPERATE_SUCCESS = "操作成功";
  //操作失败
  public static final String OPERATE_FAIL = "操作成功";
  //分页，每一页显示的数据数量
  public static final int PAGE_NUMBER = 10;
  /**
   * Ajax
   * */
  //ajax数据传输中丢失
  public static final int AJAX_VALIDATE_FAILE_CODE = 5001;
  //ajax处理成功
  public static final int AJAX_SUCCESS_CODE = 2001;
  //ajax处理失败
  public static final int AJAX_FAIL_CODE = 2003;
  /**
   * common页面
   * */
  //结果页面
  public static final String PAGE_RESULT_USER = "/common/ResultOfUser";
  public static final String PAGE_RESULT_ADMIN = "/common/ResultOfAdmin";
  
  /**
   * user页面
   * */
  //用户登录
  public static final String PAGE_USER_LOGIN = "user/user_login";
  //用户注册
  public static final String PAGE_USER_REGIST="user/user_regist";
  //用户主页
  public static final String PAGE_USER_HOME = "user/userhome";
  //商场主页
  public static final String PAGE_USER_MYHOME="user/myhome";
  //购物车页面
  public static final String PAGE_USER_SHOPPING = "user/myshopping";
  //订单页面
  public static final String PAGE_USER_ORDER = "user/myorder";
  //已完成订单页面
  public static final String PAGE_USER_FINISHED_ORDER = "user/myorder_ed";
  //我的账户页面
  public static final String PAGE_USER_ACCOUNT = "user/myaccount";
  
  /**
   * admin页面
   * */
  //管理员登录页面
  public static final String PAGE_ADMIN_LOGIN = "/admin/login";
  //管理员主页 
  public static final String PAGE_ADMIN_HOME = "/admin/adminhome";
  //商品页面
  public static final String PAGE_ADMIN_GOODS = "/admin/goods";
  //上架商品页面
  public static final String PAGE_ADMIN_GOODS_ADD = "/admin/goods_add";
  //下架商品页面
  public static final String PAGE_ADMIN_GOODS_REDUCE = "/admin/goods_under";
  //所有商品显示页面
  public static final String PAGE_ADMIN_GOODS_ALL = "/admin/goods_now";
  //所有商品详情信息
  public static final String PAGE_ADMIN_GOODS_ALL_DETAIL ="/admin/goods_now_details";
  //新建公告页面
  public static final String PAGE_ADMIN_NOTICE_ADD = "/admin/newnotes";
  //历史公告栏
  public static final String PAGE_ADMIN_NOTICE_OLD = "/admin/oldnotes";
  //订单管理
  public static final String PAGE_ADMIN_ORDER = "/admin/orders";
  //未完成订单
  public static final String PAGE_ADMIN_ORDER_UNFINISHED = "/admin/order_un";
  //已完成订单
  public static final String PAGE_AMDIN_ORDER_FINISHED = "/admin/order_ed";
  //已完成订单详情
  public static final String PAGE_ADMIN_ORDER_FINISHED_DETAIL = "/admin/order_ed_details";
  //未完成订单详情
  public static final String PAGE_ADMIN_ORDER_UNFINISHED_DETAIL = "admin/order_un_details";
  //校区管理
  public static final String PAGE_ADMIN_AREA = "admin/area";
  //添加大学
  public static final String PAGE_ADMIN_AREA_ADDCOLLEGE = "admin/add_college";
  //添加宿舍楼
  public static final String PAGE_ADMIN_AREA_ADDBUILDING = "/admin/add_building";
  //添加大学宿舍楼
  public static final String PAGE_ADMIN_AREA_ADDCOLLEGEBUILDING = "/admin/add_college_building";
  //账户信息管理
  public static final String PAGE_ADMIN_ACCOUNT = "admin/account";
  //添加管理员
  public static final String PAGE_ADMIN_ADD_ACCOUNT = "admin/account_add";
  //修改管理员
  public static final String  PAGE_ADMIN_EDIT_ACCOUNT = "admin/account_edit";
}
