package com.yc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;









import com.yc.bean.Dish;
import com.yc.bean.DishGroup;
import com.yc.bean.Merchant;
import com.yc.bean.TargetTypeLink;
import com.yc.bean.Type;
import com.yc.dao.DishDAO;
import com.yc.service.DishService;
import com.yc.util.Page;

/**
 * 菜品管理service 实现类
 * @author 
 *
 */
@Service
public class DishServiceImpl implements DishService {

  @Autowired
  private DishDAO dishDao;


  //获得菜品总数量 
  public long getAllDishCount() throws Exception{
      return dishDao.getAllDishCount();
  }

  //增加菜品
  public void addDish(Dish dish) throws Exception{
      dishDao.addDish(dish);
  }
  //新菜品生成库存 
  public void addInventoryByDish(Map<String, Object> inventoryDishMap) throws Exception{
      dishDao.addInventoryByDish(inventoryDishMap);
  }
  
  //删除菜品
  public void deleteDish(int d_id) throws Exception{
      dishDao.deleteDish(d_id);
  }    
  //删除菜品库存
  public void deleteDishInventory(int dishId)throws Exception{
      dishDao.deleteDishInventory(dishId);
  }
  
  //修改菜品
  public void updateDish(Dish dish) throws Exception{
      dishDao.updateDish(dish);
  }  
  //查询菜品
    @Override
    public  List<Dish> showAllDishs() throws Exception{
        return dishDao.showAllDishs();
    }
  //查询菜品（分页）
    public  List<Dish> getAllDishs(Page page) throws Exception{
        return dishDao.getAllDishs(page);
    }

  //根据id获取菜品
    public Dish getDishById(int d_id) throws Exception{
        return dishDao.getDishById(d_id);
    }
    
  //根据id获取整个菜品信息
    public Dish getDishInfoById(int d_id) throws Exception {
        return dishDao.getDishInfoById(d_id);
    }

   //获得关键词查询的商家数量
    public long getDishByKeywordsCount(String keywords) throws Exception {
        return dishDao.getDishByKeywordsCount(keywords);
    }

    //关键词查询商家(分页)
    public List<Dish> queryDishByKeyWords(Map<String, Object> map)
            throws Exception {
        return dishDao.queryDishByKeyWords(map);
    }
    
    
/**
 * 菜品/套餐类别
 */
    //添加菜品/套餐类别
    public void addTypeForDish(Type type)throws Exception{
        dishDao.addTypeForDish(type);
    }
    //删除菜品/套餐类别
    public void deleteTypeForDish(int typeId)throws Exception{
        dishDao.deleteTypeForDish(typeId);
    }
    
    //查询所有菜品类别 (分页)
    public  List<Type> showAllDishType(Page page) throws Exception{
        return dishDao.showAllDishType(page);
    }
    //查询所有菜品类别 
    public  List<Type> showDishType() throws Exception{
        return dishDao.showDishType();
    }
    //查询所有套餐类别(分页)
    public  List<Type> showAllPackageType(Page page) throws Exception{
        return dishDao.showAllPackageType(page);
    }
    
    
    
    //获得菜品类别总数量 
    public long getDishTypeCount() throws Exception{
        return dishDao.getDishTypeCount();
    }
    //获得套餐类别总数量
    public long getPackageTypeCount() throws Exception{
        return dishDao.getPackageTypeCount();
    }
    
    //增加菜品类别链接记录
    public void addTargetTypeLink(TargetTypeLink targetTypeLink)throws Exception{
        dishDao.addTargetTypeLink(targetTypeLink);
    }
    //修改菜品类别链接
    public void modifyTargetTypeLink(TargetTypeLink targetTypeLink)throws Exception{
        dishDao.modifyTargetTypeLink(targetTypeLink);
    }
    
    
    //根据菜品Id查找相应类别名 
    public String getTypeByDishId(int targerId)throws Exception{
        return dishDao.getTypeByDishId(targerId);
    }
    
/**
 * 套餐管理
 */
  //添加套餐时，获取需要的菜品项
    public List<Dish> getDishForAddGroup()throws Exception{     
      return dishDao.getDishForAddGroup();
    }
    
  //添加套餐
    public boolean addDishGroup(DishGroup dishgroup)throws Exception{
      if(dishDao.addDishGroup(dishgroup) > 0){
        TargetTypeLink targetTypeLink = new TargetTypeLink();
        targetTypeLink.setTarget(2);
        targetTypeLink.setTargetId(dishgroup.getGroupId());
        targetTypeLink.setTypeId(dishgroup.getTypeId());
        dishDao.addTargetTypeLink(targetTypeLink);        
        return true;
      }else{
        return false;
      }
    }
    
    //查询所有套餐类别
    public List<Type> selectAllGroupType()throws Exception{
      return dishDao.selectAllGroupType();
    }
    
  //获取套餐列表
    public List<DishGroup> getDishGroupList() throws Exception{
      return dishDao.getDishGroupList();
    }
    
  //删除套餐
    public boolean deleteDishGroup(int groupId) throws Exception{
     int isDelete = dishDao.deleteDishGroup(groupId);
     if(isDelete > 0){
       return true;
     }else{
       return false;
     }
    }
}
