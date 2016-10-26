package com.WeChat.admin.refresh;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListen implements ServletContextListener{

	private Timer timer = null;
	
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		timer.cancel();
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		timer = new Timer(true);
		timer.schedule(new MyTask(), new Date(), 10*1000);
		
	}

}
