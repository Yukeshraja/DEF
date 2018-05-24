package com.altimetrik.dto;

import java.io.Serializable;

//@Component

public class AWSLoginCredDTO implements Serializable , Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 346634914919660918L;	
	private static AWSLoginCredDTO getInstance = null;
	
	private String aws_access_key_id;  
	private String aws_secret_access_key; 
	
	private AWSLoginCredDTO(){}
	
	/*
	 * Same Instance (getInstance) is returned for all the places were AWSLoginCredDTO is consumed
	 */
	public static AWSLoginCredDTO getAWSLoginCred(){
		
		if(getInstance == null){
			synchronized (AWSLoginCredDTO.class) {
					getInstance = new AWSLoginCredDTO();
			}
		}	
		return getInstance;
	}
		
	
	public void setAws_access_key_id(String aws_access_key_id) {
		this.aws_access_key_id = aws_access_key_id;
	}
	
	public String getAws_access_key_id() {
		return aws_access_key_id;
	}
	
	
	public   String getAws_secret_access_key() {
		return  this.aws_secret_access_key;
	}
	
	public  void setAws_secret_access_key(String aws_secret_access_key) {
		this.aws_secret_access_key = aws_secret_access_key;
	}
	
	
	public Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException(); 
	}
	
}
