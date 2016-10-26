package com.WeChat.admin.Dish;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.Dish;


public class DishDaoImpl extends BaseDao implements DishDao {
	int pageSize=3;
	//查询一条dish信息通过id
	public Dish getDishByid(Dish d){
		ResultSet rs = super.query("select * from dish where dish_id=?", new Object[]{d.getDish_id()});
		Dish di=null;
		try {
			if(rs.next()){
				di=new Dish();
				di.setPic(rs.getString("pic"));
				di.setDish_id(rs.getInt("dish_id"));
				di.setDish_intro(rs.getString("dish_intro"));
				di.setDish_name(rs.getString("dish_name"));
				di.setPrice(rs.getFloat("price"));
				di.setDish_taste(rs.getString("dish_taste"));
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			 
			super.close();
		}
		return di;
	}
	//得到菜单数目
	public int getDishNum(){
		ResultSet rs = super.query("select count(0) from dish ", null);
		try {
			if(rs.next()){return rs.getInt(1);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	//得到所有的菜单
	public ArrayList<Dish> getAllDish(int pageNum) {
		ArrayList<Dish> dishList=new ArrayList<Dish>();
		// TODO Auto-generated method stub
		ResultSet rs = super.query("select * from dish LIMIT "+pageSize*(pageNum-1)+","+pageSize, null);
		try {
			while(rs.next()){
				Dish di=new Dish();
				di.setPic(rs.getString("pic"));
				di.setDish_id(rs.getInt("dish_id"));
				di.setDish_intro(rs.getString("dish_intro"));
				di.setDish_name(rs.getString("dish_name"));
				di.setPrice(rs.getFloat("price"));
				di.setDish_taste(rs.getString("dish_taste"));
				dishList.add(di);
			}
			return dishList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			 
			super.close();
		}
		return null;
	}

	
	//向dish表里添加一条dish数据
	public boolean setDish(Dish d) {
		// TODO Auto-generated method stub
		int rows=super.update("insert into dish values(null,?,?,?,?,?)", new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getPic(),
				
				d.getDish_intro(),
				d.getDish_taste()
				
		});
		super.close();
		if(rows>0){
			return true;
		}
		return false;
	}

	
	//根据dishid删除该条菜单信息
	public boolean deleteDish(Dish d){
		System.out.println(d.getDish_id()+"--------------------------dishdaoimpl");
		int rows=super.update("delete from dish where dish_id=?", new Object[]{
				d.getDish_id()
		});
		super.close();
		if(rows>0){
			System.out.println("true"+"--------------------------dishdaoimpl");
			return true;
		}
		System.out.println("false"+"--------------------------dishdaoimpl");
		return false;
	}
	
	//修改一条菜单信息
	public boolean modifyDish(Dish d){
		/*String sql = "update product set serialnumber=?,name=?,brand=?,model=?,price="
			+ product.getPrice()
			+ ",picture=?,description=? where productID="
			+ product.getProductID();
	String[] param = new String[] { product.getSerialNumber(),
			product.getName(), product.getBrand(), product.getModel(),
			product.getPicture(), product.getDescription() };
	return this.executeSQL(sql, param);*/
		//UPDATE users SET age = 24, name = 'Mike' WHERE id = 123;
		/*Object[] objects=null;
		StringBuffer sql= new StringBuffer("update dish set dish_name=?, price=?, dish_intro=?");
		if(d.getS_pic()==null && d.getB_pic()==null){sql= sql.append(" where dish_id=?"); 
		objects=new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getDish_intro(),				
				d.getDish_id()
		};}
		else if(d.getS_pic()!=null && d.getB_pic()==null){sql= sql.append(", s_pic=? where dish_id=?");
		objects=new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getDish_intro(),	
				d.getS_pic(),
				d.getDish_id()
		};}

		else if(d.getS_pic()==null && d.getB_pic()!=null){sql= sql.append(", b_pic=? where dish_id=?");
		objects=new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getDish_intro(),	
				d.getB_pic(),
				d.getDish_id()
		};
		}
		else{sql= sql.append(", s_pic=?, b_pic=? where dish_id=?");
		objects=new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getDish_intro(),	
				d.getS_pic(),
				d.getB_pic(),
				d.getDish_id()
		};
		}*/
		
		/*int rows=super.update("update dish set dish_name=?, price=?, s_pic=?, b_pic=?, dish_intro=? where dish_id=?", new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getS_pic(),
				d.getB_pic(),
				d.getDish_intro(),				
				d.getDish_id()
		});*/
		Object[] objects=null;
		StringBuffer sql= new StringBuffer("update dish set dish_name=?, price=?, dish_intro=?, dish_taste=?");
		if(d.getPic()==null){sql= sql.append(" where dish_id=?"); 
		objects=new Object[]{
				d.getDish_name(),
				d.getPrice(),
				d.getDish_intro(),	
				d.getDish_taste(),
				d.getDish_id()
		};}
		else{
			sql= sql.append(", pic=? where dish_id=?");
			objects=new Object[]{
					d.getDish_name(),
					d.getPrice(),
					d.getDish_intro(),	
					d.getDish_taste(),
					d.getPic(),
					d.getDish_id()
			};
		}
		int rows=super.update(sql.toString(),objects);
		super.close();
		if(rows>0){
			return true;
		}
		return false;
	}
	//获取所有的菜品名
	public HashMap<String,String> getDishNameList(){
		HashMap<String,String> dishNameList=new HashMap<String,String>();
		String sql="select dish_name from dish";
		try {
			ResultSet rs=super.query(sql, null);
			while(rs.next()){
				dishNameList.put(rs.getString("dish_name"), rs.getString("dish_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		super.close();
		return dishNameList;
	}
}
