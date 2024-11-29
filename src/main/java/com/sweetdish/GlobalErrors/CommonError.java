package com.sweetdish.GlobalErrors;

public class CommonError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public CommonError(String customMessage) {
	        super(customMessage);  // Pass the message to the parent RuntimeException
	    }
	
}
