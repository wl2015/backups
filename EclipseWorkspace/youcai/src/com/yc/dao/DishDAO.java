package com.yc.dao;

import java.util.List;
import java.util.Map;

import com.yc.bean.Dish;
import com.yc.bean.DishGroup;
import com.yc.bean.TargetTypeLink;
import com.yc.bean.Type;
import com.yc.util.Page;




/**
 * 菜品DAO层
 * @author RainsChan
 *
 */
public interface DishDAO {

    
  /**
   * 菜品管理
   * @return
   * @throws Exception
   */
  //获得菜品总数量 
  public long getAllDishCount() throws Exception;
  //获得符合关键词搜索的商家总数
  public long getDishByKeywordsCount(String keywords) throws Exception;   
  
  //增加菜品
  public void addDish(Dish dish) throws Exception; 
  //新菜品生成库存 
  public void addInventoryByDish(Map<String, Object> inventoryDishMap) throws Exception;
  
 
  //删除菜品
  public void deleteDish(int d_id) throws Exception;   
  //删除菜品库存
  public void deleteDishInventory(int dishId)throws Exception;
  
  //修改菜品
  public void updateDish(Dish dish) throws Exception;  
  //查询菜品
  public  List<Dish> showAllDishs() throws Exception;
  //查询菜品（分页）
  public  List<Dish> getAllDishs(Page page) throws Exception;
  
  //关键字查询菜品(分页)
  public List<Dish> queryDishByKeyWords(Map<String,Object> map) throws Exception;
  
  //根据id获取菜品
  public Dish getDishById(int d_id) throws Exception;
  //根据id获取整个菜品信息
  public Dish getDishInfoById(int d_id) throws Exception;
  
  
  /**
   * 菜品/套餐类别
   */
  //添加菜品/套餐类别
  public void addTypeForDish(Type type)throws Exception;
  //删除菜品/套餐类别
  public void deleteTypeForDish(int typeId)throws Exception;
  //查询所有菜品类别 (分页)
  public  List<Type> showAllDishType(Page page) throws Exception;
  //查询所有菜品类别 
  public  List<Type> showDishType() throws Exception;
  //查询所有套餐类别 (分页)
  public  List<Type> showAllPackageType(Page page) throws Exception;
  
  //获得菜品类别总数量 
  public long getDishTypeCount() throws Exception;
  //获得套餐类别总数量
  public long getPackageTypeCount() throws Exception;
  
  //增加菜品类别链接记录
  public void addTargetTypeLink(TargetTypeLink targetTypeLink)throws Exception;
  //修改菜品类别链接
  public void modifyTargetTypeLink(TargetTypeLink targetTypeLink)throws Exception;
  
  //根据菜品Id查找相应类别名 
  public String getTypeByDishId(int targerId)throws Exception;
  
  /**
   * 套餐管理
   */
  //添加套餐时，获取需要的菜品项
  public List<Dish> getDishForAddGroup()throws Exception;
  
  //查询所有套餐类别
  public List<Type> selectAllGroupType()throws Exception;
  
  //添加套餐
  public int addDishGroup(DishGroup dishgroup)throws Exception;
  
  //获取套餐列表
  public List<DishGroup> getDishGroupList() throws Exception;
  
  //删除套餐
  public int deleteDishGroup(int groupId) throws Exception;
  
}
