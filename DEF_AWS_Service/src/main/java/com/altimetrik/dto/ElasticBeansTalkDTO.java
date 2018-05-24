package com.altimetrik.dto;

import org.springframework.stereotype.Component;

@Component
public class ElasticBeansTalkDTO {

	private String applicationName;
	private String applicationDescription;
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationDescription() {
		return applicationDescription;
	}
	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}
	
	
}
