package com.altimetrik.dto;

import org.springframework.stereotype.Component;

@Component
public class S3BucketDTO {

	private String bucketName;
	private String message;
	private String region;
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
}
