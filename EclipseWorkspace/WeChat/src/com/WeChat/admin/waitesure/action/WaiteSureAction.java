package com.WeChat.admin.waitesure.action;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.WeChat.entity.OrderForm;
import com.WeChat.admin.waitesure.dao.WaiteSureDaoImp;
import com.WeChat.admin.waitesure.service.WaiteSureService;
import com.WeChat.admin.waitesure.service.WaiteSureServiceImp;

public class WaiteSureAction extends ActionSupport{
	
	private WaiteSureService waiteservice = new WaiteSureServiceImp();
	private List<OrderForm> orderlist;
	private OrderForm orderinfo;
	private String orderformId;
	private int orderid;
	
	private HashMap<Integer,String> pageList=new HashMap<Integer,String>();


	public HashMap<Integer, String> getPageList() {
		return pageList;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public int getTotalnum() {
		return totalnum;
	}

		private int pageSize=3;//锛�鑷繁璁剧疆锛�
		private int pageNum;
		private int totalpage;
		private int totalnum;
		
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getOrderformId() {
		return orderformId;
	}

	public void setOrderformId(String orderformId) {
		this.orderformId = orderformId;
	}

	public OrderForm getOrderinfo() {
		return orderinfo;
	}

	public void setOrderinfo(OrderForm orderinfo) {
		this.orderinfo = orderinfo;
	}

	public List<OrderForm> getOrderform() {
		return orderlist;
	}
	
	//鏄剧ず鎵�湁璁㈠崟淇℃伅
	@Override
	public String execute() throws Exception {
		if(ServletActionContext.getRequest().getParameter("pageNum")==null){
			pageNum=1;			
		}
		else{
			pageNum=Integer.parseInt(ServletActionContext.getRequest().getParameter("pageNum"));
		}
		totalnum=new WaiteSureDaoImp().getTotalnum();//锛�璇ユ柟娉曟槸鑾峰彇鏁版嵁鎬绘暟鑷繁鍐欒鍒板搴旂殑dao锛宒aoimpl锛孲ervice锛宻erviceimpl閲屾坊鍔狅級
		totalpage=(totalnum-1)/pageSize +1;
		orderlist = waiteservice.doOrderForm(getPageNum());
	
		for(int i=1;i <=totalpage; i++){
    		pageList.put(i, "第"+i+"页");
    	}
		
		return "success";
	}
	
	

	//鏍规嵁璁㈠崟ID鏄剧ず璇︾粏淇℃伅
	public String getOrderInfoById(){
		setOrderinfo(waiteservice.getInfoById(orderformId));
		return "orderinfo";
	}
	
	//鏍规嵁璁㈠崟ID鍒犻櫎璇ユ潯淇℃伅
	public String DeleteOrderformById(){
		waiteservice.DodeleteByorderId(Integer.parseInt(this.orderformId));
		return "deleteorderform";
	}
	
	//淇敼璁㈠崟琛ㄧ殑鐘舵�鍊�
	public String UpdateOrder(){
		waiteservice.Update(orderid);
		return "updatesuccess";
	}
}
