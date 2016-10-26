package com.wedding321.upload;

public class UploadException extends Exception {
	
	private String message = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4492179946450007884L;
	
	public UploadException(Exception ex) {
		super(ex);
		message = ex.getMessage();
	}
	
	public UploadException(String showMessage) {
		message = showMessage;
	}
	
	public UploadException(String showMessage, Exception ex) {
		super(ex);
		message = showMessage;
	}

	public String getShowMessage() {
		return message;
	}
	
}
