package com.cn.changhong.restapi;

import java.io.Serializable;

/**
 * restapi结果，默认返回404
 * 
 * @author laiwei
 * @date 2015-11-5 上午10:54:47
 */
public class RestApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 440481315998426181L;

	/**
	 * 结果代码，0:成功,403:权限不够,404:没有数据,500:失败
	 */
	private int code = 0;

	/**
	 * 响应消息
	 */
	private String msg;

	/**
	 * 业务数据
	 */
	private Object data;

	public RestApiResponse() {
		this.code = 404;
	}

	public RestApiResponse(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}