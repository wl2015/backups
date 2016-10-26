package com.WeChat.admin.sendpeople;

import java.util.List;

import com.WeChat.entity.SendPeople;



public class SendPeopleServiceImpl implements SendPeopleService {
    private SendPeopleDao sendpeopleDao;
   
    public void setSendPeopleDao(SendPeopleDao sendpeopleDao) {
		this.sendpeopleDao = sendpeopleDao;
	}
	public SendPeopleServiceImpl(){
		this.setSendPeopleDao(new SendPeopleDaoImpl());
	}
	//查询所有配送员数量
	public int getPeopleNum(){
		return sendpeopleDao.getPeopleNum();
	}
	public boolean addSendPeople(SendPeople sp) {
		// TODO Auto-generated method stub
		return sendpeopleDao.addSendPeople(sp);
	}

	public boolean deleteSendPeople(SendPeople sp) {
		// TODO Auto-generated method stub
		return sendpeopleDao.deleteSendPeople(sp);
	}

	public List<SendPeople> getallPeople(int pageNum) {
		// TODO Auto-generated method stub
		
		return sendpeopleDao.getallPeople(pageNum);
	}

	public boolean updateSendpeople(SendPeople sp) {
		// TODO Auto-generated method stub
		return sendpeopleDao.updateSendpeople(sp);
	}
	//根据id得到配送员信息
	public SendPeople getsendPeopleById(SendPeople sp){
		return sendpeopleDao.getsendPeopleById(sp);
	}

}
