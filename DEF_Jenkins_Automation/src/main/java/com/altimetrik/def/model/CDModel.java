package com.altimetrik.def.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deployTo",
    "dockerImgRegistryPort",
    "dockerImgRegistryServer",
    "deploymentServer",
    "authType",
    "credentialID"
})
@Document
public class CDModel {

	@JsonProperty("cdName")
	@Id
	private String cdName;
	@JsonProperty("deployTo")
	private String deployTo;
	@JsonProperty("deploymentServer")
	private String deploymentServer;
	 @JsonProperty("authType")
	private String authType;
	 @JsonProperty("CredentialID")
	private String CredentialID;
	@JsonProperty("dockerImgRegistryPort")
	private String dockerImgRegistryPort;
	@JsonProperty("dockerImgRegistryServer")
	private String dockerImgRegistryServer;
	@JsonProperty("status")
	private String status;
	@JsonProperty("dockerExposedPortNumber")
	private String dockerExposedPortNumber;
	@JsonProperty("appTargetPortNumber")
	private String appTargetPortNumber;
	
	
	
	/**
	 * @return the cdName
	 */
	public String getCdName() {
		return cdName;
	}
	/**
	 * @param cdName the cdName to set
	 */
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	/**
	 * @return the deployTo
	 */
	public String getDeployTo() {
		return deployTo;
	}
	/**
	 * @param deployTo the deployTo to set
	 */
	public void setDeployTo(String deployTo) {
		this.deployTo = deployTo;
	}
	/**
	 * @return the deploymentServer
	 */
	public String getDeploymentServer() {
		return deploymentServer;
	}
	/**
	 * @param deploymentServer the deploymentServer to set
	 */
	public void setDeploymentServer(String deploymentServer) {
		this.deploymentServer = deploymentServer;
	}
	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * @return the credentialID
	 */
	public String getCredentialID() {
		return CredentialID;
	}
	/**
	 * @param credentialID the credentialID to set
	 */
	public void setCredentialID(String credentialID) {
		CredentialID = credentialID;
	}
	/**
	 * @return the dockerImgRegistryPort
	 */
	public String getDockerImgRegistryPort() {
		return dockerImgRegistryPort;
	}
	/**
	 * @param dockerImgRegistryPort the dockerImgRegistryPort to set
	 */
	public void setDockerImgRegistryPort(String dockerImgRegistryPort) {
		this.dockerImgRegistryPort = dockerImgRegistryPort;
	}
	/**
	 * @return the dockerImgRegistryServer
	 */
	public String getDockerImgRegistryServer() {
		return dockerImgRegistryServer;
	}
	/**
	 * @param dockerImgRegistryServer the dockerImgRegistryServer to set
	 */
	public void setDockerImgRegistryServer(String dockerImgRegistryServer) {
		this.dockerImgRegistryServer = dockerImgRegistryServer;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDockerExposedPortNumber() {
		return dockerExposedPortNumber;
	}
	public void setDockerExposedPortNumber(String dockerExposedPortNumber) {
		this.dockerExposedPortNumber = dockerExposedPortNumber;
	}
	public String getAppTargetPortNumber() {
		return appTargetPortNumber;
	}
	public void setAppTargetPortNumber(String appTargetPortNumber) {
		this.appTargetPortNumber = appTargetPortNumber;
	}	
	
}
