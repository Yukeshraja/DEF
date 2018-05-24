package com.altimetrik.def.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class CIModel {

	@JsonProperty("ciName")
	@Id
	private String ciName;
	@JsonProperty("ciType")
	private String ciType;
	@JsonProperty("ciURL")
	private String ciURL;
	@JsonProperty("ciFolder")
	private String ciFolder;
	@JsonProperty("ArtifactoryUrl")
	private String artifactoryUrl;
	@JsonProperty("targetReleaseSynatax")
	private String targetReleaseSynatax;
	@JsonProperty("targetSnapshotSyntax")
	private String targetSnapshotSyntax;
	@JsonProperty("CredentialID")
	private String credentialID;
	@JsonProperty("authType")
	private String authType;
	@JsonProperty("status")
	private String status;

	/**
	 * @return the ciName
	 */
	public String getCiName() {
		return ciName;
	}

	/**
	 * @param ciName
	 *            the ciName to set
	 */
	public void setCiName(String ciName) {
		this.ciName = ciName;
	}

	/**
	 * @return the ciType
	 */
	public String getCiType() {
		return ciType;
	}

	/**
	 * @param ciType
	 *            the ciType to set
	 */
	public void setCiType(String ciType) {
		this.ciType = ciType;
	}

	/**
	 * @return the ciURL
	 */
	public String getCiURL() {
		return ciURL;
	}

	/**
	 * @param ciURL
	 *            the ciURL to set
	 */
	public void setCiURL(String ciURL) {
		this.ciURL = ciURL;
	}

	/**
	 * @return the ciFolder
	 */
	public String getCiFolder() {
		return ciFolder;
	}

	/**
	 * @param ciFolder
	 *            the ciFolder to set
	 */
	public void setCiFolder(String ciFolder) {
		this.ciFolder = ciFolder;
	}

	/**
	 * @return the artifactoryUrl
	 */
	public String getArtifactoryUrl() {
		return artifactoryUrl;
	}

	/**
	 * @param artifactoryUrl
	 *            the artifactoryUrl to set
	 */
	public void setArtifactoryUrl(String artifactoryUrl) {
		this.artifactoryUrl = artifactoryUrl;
	}

	/**
	 * @return the targetReleaseSynatax
	 */
	public String getTargetReleaseSynatax() {
		return targetReleaseSynatax;
	}

	/**
	 * @param targetReleaseSynatax
	 *            the targetReleaseSynatax to set
	 */
	public void setTargetReleaseSynatax(String targetReleaseSynatax) {
		this.targetReleaseSynatax = targetReleaseSynatax;
	}

	/**
	 * @return the targetSnapshotSyntax
	 */
	public String getTargetSnapshotSyntax() {
		return targetSnapshotSyntax;
	}

	/**
	 * @param targetSnapshotSyntax
	 *            the targetSnapshotSyntax to set
	 */
	public void setTargetSnapshotSyntax(String targetSnapshotSyntax) {
		this.targetSnapshotSyntax = targetSnapshotSyntax;
	}

	/**
	 * @return the credentialID
	 */
	public String getCredentialID() {
		return credentialID;
	}

	/**
	 * @param credentialID
	 *            the credentialID to set
	 */
	public void setCredentialID(String credentialID) {
		this.credentialID = credentialID;
	}

	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * @param authType
	 *            the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
