package com.WeChat.admin.sendpeople;


import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.WeChat.entity.SendPeople;



public class SendPeopleAction extends com.opensymphony.xwork2.ActionSupport {
	private SendPeople operSendPeople;
	private List<SendPeople> sendPeopleList;
	 private HashMap<Integer,String> pageList=new HashMap<Integer,String>();
	private String returnMes;
	public String getReturnMes() {
		return returnMes;
	}
	private SendPeopleService service=new SendPeopleServiceImpl();
	private int pageSize=9;
	private int pageNum;
	private int totalpage;
	private int totalpeoplenum;
	public HashMap<Integer, String> getPageList() {
		return pageList;
	}
	public int getTotalpeoplenum() {
		return totalpeoplenum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	public int getTotalpage() {
		return totalpage;
	}
	//根据id得到配送员信息
	public String getsendPeopleById(){
		operSendPeople=service.getsendPeopleById(getOperSendPeople());
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
			pageNum=1;
			
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		//System.out.println(pageNum+".......................................action");
		}
		return "updatePeople";
	}
	//添加配送员
	public String addSendPeople(){
		boolean b=service.addSendPeople(getOperSendPeople());
		if(b){
        	returnMes="添加配送员成功";
        }
        else{
        	returnMes="添加配送员失败";
        }
		operSendPeople=null;
		/*if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
			pageNum=1;
			
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		//System.out.println(pageNum+".......................................action");
		}
		totalpeoplenum=service.getPeopleNum();
		totalpage=(totalpeoplenum-1)/pageSize +1;
		sendPeopleList=service.getallPeople(pageNum);
		for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}*/
		return "addPeople";
	}
	//删除配送员
	public String deleteSendPeople(){
		boolean b=service.deleteSendPeople(this.getOperSendPeople());
		if(b){
        	returnMes="删除配送员成功";
        }
        else{
        	returnMes="删除配送员失败";
        }
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
			pageNum=1;			
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		//System.out.println(pageNum+".......................................action");
		}
		totalpeoplenum=service.getPeopleNum();
		totalpage=(totalpeoplenum-1)/pageSize +1;
		sendPeopleList=service.getallPeople(pageNum);
		for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}
		return "peopleList";
	}
	//查询所有配送员
	public String getallPeople(){
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
			pageNum=1;			
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		//System.out.println(pageNum+".......................................action");
		}
		//System.out.println(pageNum+".......................................action");
		totalpeoplenum=service.getPeopleNum();
		totalpage=(totalpeoplenum-1)/pageSize +1;
		sendPeopleList=service.getallPeople(pageNum);
		for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}
		return "peopleList";
	}
	public List<SendPeople> getSendPeopleList() {
		return sendPeopleList;
	}
	public void setSendPeopleList(List<SendPeople> sendPeopleList) {
		this.sendPeopleList = sendPeopleList;
	}
	//修改配送员信息
	public String updateSendpeople(){
		//System.out.println(this.getOperSendPeople().getPeopleId()+".....................................action");
		boolean b=service.updateSendpeople(this.getOperSendPeople());
		if(b){
        	returnMes="修改配送员成功";
        }
        else{
        	returnMes="修改配送员失败";
        }
		//sendPeopleList=service.getallPeople(1);
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			if(getPageNum()!= 0){
    			pageNum=getPageNum();
    		}
			pageNum=1;			
		}
		else{
		pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		//System.out.println(pageNum+".......................................action");
		}
		totalpeoplenum=service.getPeopleNum();
		totalpage=(totalpeoplenum-1)/pageSize +1;
		sendPeopleList=service.getallPeople(pageNum);
		for(int i=1;i <=totalpage; i++){
    		pageList.put(i,"第"+i+"页");
    	}
		return "peopleList";
	}
	public SendPeople getOperSendPeople() {
		return operSendPeople;
	}
	public void setOperSendPeople(SendPeople operSendPeople) {
		this.operSendPeople = operSendPeople;
	}
	
}
