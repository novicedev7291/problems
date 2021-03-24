package com.kuldeep.library;

public class ApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1437284727147795198L;
	
	private int errorCode;
	
	public ApiException(String message){
		this(0, message);
	}
	
	public ApiException(int erroCode,String message) {
		super(message);
		this.errorCode = erroCode;
	}
	
	public ApiException(int errorCode, Throwable ex){
		super(ex);
		this.errorCode = errorCode;
	}
	
	public ApiException(int errorCode, String message, Throwable ex){
		super(message, ex);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
