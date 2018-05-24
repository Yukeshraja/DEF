
package com.altimetrik.def.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bash",
    "deployTo",
    "appTargetPortNumber",
    "dockerExposedPortNumber",
    "dockerImgRegistryPort",
    "dockerImgRegistryServer",
    "deploymentServer",
    "authType",
    "credentialID"
})
public class CD {
	
	@JsonProperty("cdName")
	private String cdName;
    @JsonProperty("bash")
    private String bash;
    @JsonProperty("deployTo")
    private String deployTo;
    @JsonProperty("appTargetPortNumber")
    private String appTargetPortNumber;
    @JsonProperty("dockerExposedPortNumber")
    private String dockerExposedPortNumber;
    @JsonProperty("dockerImgRegistryPort")
    private String dockerImgRegistryPort;
    @JsonProperty("dockerImgRegistryServer")
    private String dockerImgRegistryServer;
    @JsonProperty("deploymentServer")
    private String deploymentServer;
    @JsonProperty("authType")
    private String authType;
    @JsonProperty("CredentialID")
    private String credentialID;
    
    
   
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
	 * @return the bash
	 */
	public String getBash() {
		return bash;
	}



	/**
	 * @param bash the bash to set
	 */
	public void setBash(String bash) {
		this.bash = bash;
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
	 * @return the appTargetPortNumber
	 */
	public String getAppTargetPortNumber() {
		return appTargetPortNumber;
	}



	/**
	 * @param appTargetPortNumber the appTargetPortNumber to set
	 */
	public void setAppTargetPortNumber(String appTargetPortNumber) {
		this.appTargetPortNumber = appTargetPortNumber;
	}



	/**
	 * @return the dockerExposedPortNumber
	 */
	public String getDockerExposedPortNumber() {
		return dockerExposedPortNumber;
	}



	/**
	 * @param dockerExposedPortNumber the dockerExposedPortNumber to set
	 */
	public void setDockerExposedPortNumber(String dockerExposedPortNumber) {
		this.dockerExposedPortNumber = dockerExposedPortNumber;
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
		return credentialID;
	}



	/**
	 * @param credentialID the credentialID to set
	 */
	public void setCredentialID(String credentialID) {
		this.credentialID = credentialID;
	}



	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
