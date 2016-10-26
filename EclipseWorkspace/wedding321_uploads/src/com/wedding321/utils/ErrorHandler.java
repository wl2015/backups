package com.wedding321.utils;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class ErrorHandler {
	
	protected static Logger errorLogger = Logger.getLogger(ErrorHandler.class);
	
	public static JSONObject createErrorJSONInfo(String errorMsg) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", errorMsg);
		return obj;
	}
	
	public static void logError(String message, Exception ex) {
		errorLogger.error(message, ex);
	}
	
	public static String getUnknownWebSiteResponse(String requestContentPath) {
		
		return requestContentPath + "/" + Constants.VEDIO_PERVIEW_ERROR;
	}

}
