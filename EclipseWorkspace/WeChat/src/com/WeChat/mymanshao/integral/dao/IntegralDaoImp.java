package com.WeChat.mymanshao.integral.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.Integral;
import com.mysql.jdbc.ResultSet;

public class IntegralDaoImp extends BaseDao implements IntegralDao{

	/*
	 * 根据用户ID查找用户积分
	 * @see com.WeChat.mymanshao.integral.dao.IntegralDao#getIntegralInfo(int)
	 */
	public List<Integral> getIntegralInfo(int userId) {
		String sql = "SELECT * FROM integral WHERE i_userid=?";
		List<Integral> integralList = new ArrayList<Integral>();
		ResultSet rs = (ResultSet) super.query(sql, new Object[]{userId});
		
		try {
			while(rs.next()){
				Integral integral = new Integral();
				integral.setIntegralId(rs.getInt("i_userid"));
				integral.setIntegralCount(rs.getInt("i_count"));
				integral.setIntegralUserid(rs.getInt("i_userid"));
				integralList.add(integral);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();
		}
		return integralList;
	}

}
