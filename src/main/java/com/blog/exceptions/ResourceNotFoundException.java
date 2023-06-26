package com.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String resourceName;
	private static String fieldName;
	private static int fieldValue;
	
	public ResourceNotFoundException(String resourcename , String fieldname,int fieldValue) {
		super(String.format("%s not found with %s:%s",resourceName, fieldName,fieldValue));
		this.resourceName =resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
			
	}

}
  