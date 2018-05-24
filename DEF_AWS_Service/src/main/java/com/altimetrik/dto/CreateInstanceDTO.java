package com.altimetrik.dto;

import org.springframework.stereotype.Component;

@Component
public class CreateInstanceDTO {

	private String amiid;
	private String instanceType;
	private String securityGroupId;
	private String keyName;
	private String tagName;
	
	
	public String getAmiid() {
		return amiid;
	}
	public void setAmiid(String amiid) {
		this.amiid = amiid;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getSecurityGroupId() {
		return securityGroupId;
	}
	public void setSecurityGroupId(String securityGroupId) {
		this.securityGroupId = securityGroupId;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	
}
