package com.altimetrik.dto;

import org.springframework.stereotype.Component;

@Component
public class SecurityGroupDTO {

	private String description;
	private String groupName;
	private String groupID;
	private String vpcID;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getVpcID() {
		return vpcID;
	}
	public void setVpcID(String vpcID) {
		this.vpcID = vpcID;
	}
	
}
