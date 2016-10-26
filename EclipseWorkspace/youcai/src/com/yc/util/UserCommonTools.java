package com.yc.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import com.yc.bean.DishList;
import com.yc.bean.Merchant;
import com.yc.bean.Order;
import com.yc.bean.Point;

public class UserCommonTools {
  /***
   * 返回搜索范围
   * range为千米数，range的1表示一千米
   * */
  public static Point getPointRangeFiveKm(double pointLeft,double pointRight,
      double leftDistance, double rightDistance, int range){
    Point point = new Point();
    double lPointLeft = pointLeft - leftDistance * range;
    double rPointLeft = pointLeft + leftDistance * range;
    double upPointRight = pointRight - rightDistance * range;
    double downPointRight = pointRight + rightDistance * range;
    
    point.setlPointLeft(lPointLeft);
    point.setrPointLeft(rPointLeft);
    point.setUpPointRight(upPointRight);
    point.setDownPointRight(downPointRight);
    
    return point;
  }
  /**
   * 获取横坐标的差值
   * */
  public static double getLeftDistanceByPointRight(double pointRight){
    double leftDistance = 0;
    if(pointRight>=15 && pointRight<25){
      leftDistance = 0.009571;
    }
    else if(pointRight>=25 && pointRight<35){
      leftDistance = 0.010386;
    }
    else if(pointRight>=35 && pointRight<45){
      leftDistance = 0.011740;
    }
    else if(pointRight>=45 && pointRight<55){
      leftDistance = 0.013991;
    }
    return leftDistance;
  }
  
  /**
   * 获取商家距离级别
   * */
  public static int getMerchantRangeByTwoPointAndLeftDistance(double pointLeft, 
      double pointRight,double merchantLeft,double merchantRight,
      double leftDistance){
    double rightDistance = 0.008994;
    int range = 0;
    int rangeLeft = 0;
    int rangeRight = 0;
    while(rangeLeft * leftDistance <= Math.abs(merchantLeft-pointLeft)){
      rangeLeft = rangeLeft + 1;
    }
    while(rangeRight * rightDistance <= Math.abs(merchantRight-pointRight)){
      rangeRight = rangeRight + 1;
    }
    if(rangeLeft > rangeRight){
      range = rangeLeft;
    }
    else{
      range = rangeRight;
    }
    return range;
  }
  
  /**
   * 计算订单总价
   * */
  public static double getTotalPrice(List<DishList> orderlist){
    double money = 0;
    for(int i=0; i<orderlist.size(); i++){
      money = money + orderlist.get(i).getPrice() * orderlist.get(i).getNumber();
    }
    return money;
  }
  
  /**
   * 下订单时封装将要存入数据库的order
   * */
  public static Order makeOrderToPush(int userId,String address, double pointLeft, 
      double pointRight, double totalMoney,double distance){
    Order order = new Order();
    order.setUserId(userId);
    order.setAddress(address);
    order.setOrderTime(TimeUtil.getCurrentTimeAndDate());
    order.setoLng(pointLeft);
    order.setoLat(pointRight);
    order.setMoney(totalMoney);
    order.setDistance(distance);
    return order;
  }
  
  /**
   * 筛选出最近的商家
   * */
  public static Merchant getMinDistanceMerchant(List<Merchant> merchants,
      double pointLeft,double pointRight, double leftDistance){
    Merchant minMerchant = new Merchant();
    int rangeMin=0;
    for(int j=0; j<merchants.size();j++){
      if(rangeMin==0){
        minMerchant = merchants.get(j);
        int range = UserCommonTools.getMerchantRangeByTwoPointAndLeftDistance(
            pointLeft, pointRight, minMerchant.getMerchantLng(), 
            minMerchant.getMerchantLat(), leftDistance);
        rangeMin=range;
      }
      else{
        Merchant merchant = merchants.get(j);
        int range = UserCommonTools.getMerchantRangeByTwoPointAndLeftDistance(
            pointLeft, pointRight, merchant.getMerchantLng(), 
          merchant.getMerchantLat(), leftDistance);
        if(range < rangeMin){
          rangeMin=range;
          minMerchant = merchants.get(j);
        }
      }
    }//for(int j=0; j<merchantListPartOne.size();j++)
    return minMerchant;
  }
  
  /**
   * 对范围内的商家进行排序
   * */
  public static List<Merchant> sortMerchants(List<Merchant> merchants){
    List<Merchant> newMerchants = new ArrayList<Merchant>();
    List<Merchant> merchantsInRangeThree = new ArrayList<Merchant>();
    List<Merchant> merchantsOutRangeThree = new ArrayList<Merchant>();
    //将商家分成两组，3公里内，3公里外
    for(int i=0; i<merchants.size();i++){
      Merchant merchant = new Merchant();
      int range = merchants.get(i).getPushOrder().getRange();
      if(range<=3){
        merchant = merchants.get(i);
        merchantsInRangeThree.add(merchant);
      }
      else if(3<range){
        merchant = merchants.get(i);
        merchantsOutRangeThree.add(merchant);
      }
    }
    
  //冒泡排序，对3公里以内的商家，根据评价等级进行排序,评价高在前
    if(merchantsInRangeThree.size()>1){
      for(int m=0;m<merchantsInRangeThree.size();m++){
        for(int n=0;n<merchantsInRangeThree.size()-1;n++){
          Merchant mHigh = new Merchant();
          Merchant mLow = new Merchant();
          Merchant mMiddle = new Merchant();
          mHigh = merchantsInRangeThree.get(n);
          mLow = merchantsInRangeThree.get(n+1);
          if(mHigh.getMerchantStar()<mLow.getMerchantStar()){
            mMiddle = mLow;
            mLow = mHigh;
            mHigh = mMiddle;
          }
          merchantsInRangeThree.set(n, mHigh);
          merchantsInRangeThree.set(n+1, mLow);
        }//for(int n=0;m<merchantsInRangeThree.size();n++)
      }//for(int m=0;m<merchantsInRangeThree.size();m++)
    }//if(merchantsInRangeThree.size()>1)
    
    if(merchantsInRangeThree.size()>0){
      newMerchants = merchantsInRangeThree;
    }
    
  //冒泡排序，对3公里以外的商家，根据评价等级进行排序,评价高在前
    if(merchantsOutRangeThree.size()>1){
      for(int m=0;m<merchantsOutRangeThree.size();m++){
        for(int n=0;n<merchantsOutRangeThree.size()-1;n++){
          Merchant mHigh = new Merchant();
          Merchant mLow = new Merchant();
          Merchant mMiddle = new Merchant();
          mHigh = merchantsOutRangeThree.get(n);
          mLow = merchantsOutRangeThree.get(n+1);
          if(mHigh.getMerchantStar()<mLow.getMerchantStar()){
            mMiddle = mLow;
            mLow = mHigh;
            mHigh = mMiddle;
          }
          merchantsOutRangeThree.set(n, mHigh);
          merchantsOutRangeThree.set(n+1, mLow);
        }//for(int n=0;m<merchantsOutRangeThree.size();n++)
      }//for(int m=0;m<merchantsOutRangeThree.size();m++)
    }//if(merchantsOutRangeThree.size()>1)
    if(merchantsOutRangeThree.size()!=0){
      for(int i=0;i<merchantsOutRangeThree.size();i++){
        Merchant merchant = new Merchant();
        merchant = merchantsOutRangeThree.get(i);
        newMerchants.add(merchant);
      }
    }
    
    return newMerchants;
  }
  
  public static int getUserIdFromCookies(Cookie[] cookies){
    Cookie myCookie=null;
    for(int i=0;i<cookies.length;i++) {
      if(cookies[i].getName().equals("userId")) {
        myCookie=cookies[i];
        break;
       }
      }
    return Integer.parseInt(myCookie.getValue());
  }
  
  public static int getOrderIdFromCookies(Cookie[] cookies){
    Cookie myCookie=null;
    for(int i=0;i<cookies.length;i++) {
      if(cookies[i].getName().equals("orderId")) {
        myCookie=cookies[i];
        break;
       }
      }
    return Integer.parseInt(myCookie.getValue());
  }
}
