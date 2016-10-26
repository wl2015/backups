package com.WeChat.admin.sendpeople;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.WeChat.db.BaseDao;
import com.WeChat.entity.SendPeople;



public class SendPeopleDaoImpl extends BaseDao implements SendPeopleDao {
	int pageSize=9;
	//根据id得到配送员信息
	public SendPeople getsendPeopleById(SendPeople sp){
		ResultSet rs = super.query("select * from sendpeople where people_id=?", new Object[]{sp.getPeopleId()});
		SendPeople ap1=new SendPeople();
		try {
			if(rs.next()){
				ap1.setName(rs.getString("name"));
				ap1.setSex(rs.getString("sex"));
				ap1.setLinkWay(rs.getString("link_way"));
				ap1.setWeiXNumber(rs.getString("weix_number"));
				ap1.setPeopleId(rs.getInt("people_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ap1;
	}
	//添加配送员
	public boolean addSendPeople(SendPeople sp){
		String sql="insert into sendpeople values(null, ?, ?, ?, ?)";
		int rows = super.update(sql, new Object[]{
				sp.getName(),
				sp.getSex(),
				sp.getLinkWay(),
				sp.getWeiXNumber()
		});
		if(rows>0){
			return true;
		}
		return false;
	}
	//删除配送员
	public boolean deleteSendPeople(SendPeople sp){
		int rows = super.update("delete from sendpeople where people_id=?", new Object[]{sp.getPeopleId()});
		if(rows>0){
			return true;
		}
		return false;
	}
	//查询所有配送员数量
	public int getPeopleNum(){
		ResultSet rs = super.query("select count(0) from sendpeople", null);
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//查询所有配送员
	public List<SendPeople> getallPeople(int pageNum){
		//System.out.println(".....................1................daoImpl");
		ArrayList<SendPeople> sendpeopleList=new ArrayList<SendPeople>();
		ResultSet rs = super.query("select * from sendpeople order by people_id LIMIT "+pageSize*(pageNum-1)+","+pageSize,  null);
		try {
			
			while(rs.next()){	
				SendPeople ap1=new SendPeople();
				ap1.setPeopleId(rs.getInt("people_id"));
				ap1.setName(rs.getString("name"));
				ap1.setSex(rs.getString("sex"));
				ap1.setLinkWay(rs.getString("link_way"));
				ap1.setWeiXNumber(rs.getString("weix_number"));
				sendpeopleList.add(ap1);
			}
			
			//System.out.println(sendpeopleList.size()+".....................2................daoImpl");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(sendpeopleList.size()+"......................3..............daoImpl");
		return sendpeopleList;
	}
	//修改配送员信息
	public boolean updateSendpeople(SendPeople sp){
		System.out.println(sp.getPeopleId()	+"..............................daoimpl");
		int rows = super.update("update sendpeople set name=?, sex=?, link_way=?, weix_number=? where people_id=?", new Object[]{
				sp.getName(),
				sp.getSex(),
				sp.getLinkWay(),
				sp.getWeiXNumber(),
				sp.getPeopleId()			
		});
		if(rows>0){
			return true;
		}
		return false;
	}
}
