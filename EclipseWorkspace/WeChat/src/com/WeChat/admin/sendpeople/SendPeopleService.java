package com.WeChat.admin.sendpeople;


import java.util.List;

import com.WeChat.entity.SendPeople;

public interface SendPeopleService {
	//查询所有配送员数量
	public int getPeopleNum();
	//添加配送员
	public boolean addSendPeople(SendPeople sp);
	//删除配送员
	public boolean deleteSendPeople(SendPeople sp);
	//查询所有配送员
	public List<SendPeople> getallPeople(int pageNum);
	//修改配送员信息
	public boolean updateSendpeople(SendPeople sp);
	//根据id得到配送员信息
	public SendPeople getsendPeopleById(SendPeople sp);
	
}
