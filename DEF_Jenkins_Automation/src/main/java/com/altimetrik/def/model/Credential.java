package com.altimetrik.def.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Credential {
	
	/*@Id
	private Long id;*/
	@Id
	private String credentialName;
	private String type;
	private String userName;
	private String password;
	private String sshKey;
	private String secretKey;
	
/*	
	*//**
	 * @return the id
	 *//*
	public Long getId() {
		return id;
	}
	*//**
	 * @param id the id to set
	 *//*
	public void setId(Long id) {
		this.id = id;
	}*/
	/**
	 * @return the credentialName
	 */
	public String getCredentialName() {
		return credentialName;
	}
	/**
	 * @param credentialName the credentialName to set
	 */
	public void setCredentialName(String credentialName) {
		this.credentialName = credentialName;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the sshKey
	 */
	public String getSshKey() {
		return sshKey;
	}
	/**
	 * @param sshKey the sshKey to set
	 */
	public void setSshKey(String sshKey) {
		this.sshKey = sshKey;
	}
	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Credential [credentialName=" + credentialName + ", type=" + type + ", userName=" + userName
				+ ", password=" + password + ", sshKey=" + sshKey + ", secretKey=" + secretKey
				+ ", getCredentialName()=" + getCredentialName() + ", getType()=" + getType() + ", getUserName()="
				+ getUserName() + ", getPassword()=" + getPassword() + ", getSshKey()=" + getSshKey()
				+ ", getSecretKey()=" + getSecretKey() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	

}
