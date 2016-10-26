package com.WeChat.admin.refresh;


import java.util.TimerTask;

import com.WeChat.admin.waitesure.action.WaiteSureAction;

public class MyTask extends TimerTask{

	@Override
	public void run() {
		WaiteSureAction waitesure = new WaiteSureAction();
		
		try {
			waitesure.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
