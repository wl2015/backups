package com.yc.basic.exceptions;

/**
 * 登录超时的异常
 * */
public class SessionTimeoutException extends Exception {

	private static final long serialVersionUID = 1L;
	
	//异常信息
  public String happenAtWhere;
  
	public SessionTimeoutException(String happenAtWhere) {
		
		super(happenAtWhere);
		this.happenAtWhere = happenAtWhere;
	}

    public String getHappenAtWhere() {
        return happenAtWhere;
    }

    public void setHappenAtWhere(String happenAtWhere) {
        this.happenAtWhere = happenAtWhere;
    }
	
	
}
