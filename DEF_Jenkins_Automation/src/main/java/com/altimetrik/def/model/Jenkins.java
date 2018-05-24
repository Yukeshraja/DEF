
package com.altimetrik.def.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "JenkinsName", "JenkinsURL", "JenkinsUsername", "JenkinsPassword", "ArtifactoryUrl",
		"TargetReleaseSynatax", "TargetSnapshotSyntax", "credentialID", "authType", "jenkinsFolder" })
public class Jenkins {

	@JsonProperty("JenkinsName")
	private String JenkinsName;
	@JsonProperty("JenkinsURL")
	private String jenkinsURL;
	@JsonProperty("JenkinsUsername")
	private String jenkinsUsername;
	@JsonProperty("JenkinsPassword")
	private String jenkinsPassword;
	@JsonProperty("jenkinsFolder")
	private String jenkinsFolder;
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

	/**
	 * @return the jenkinsName
	 */
	public String getJenkinsName() {
		return JenkinsName;
	}

	/**
	 * @param jenkinsName
	 *            the jenkinsName to set
	 */
	public void setJenkinsName(String jenkinsName) {
		JenkinsName = jenkinsName;
	}

	@JsonProperty("JenkinsURL")
	public String getJenkinsURL() {
		return jenkinsURL;
	}

	@JsonProperty("JenkinsURL")
	public void setJenkinsURL(String jenkinsURL) {
		this.jenkinsURL = jenkinsURL;
	}

	@JsonProperty("JenkinsUsername")
	public String getJenkinsUsername() {
		return jenkinsUsername;
	}

	@JsonProperty("JenkinsUsername")
	public void setJenkinsUsername(String jenkinsUsername) {
		this.jenkinsUsername = jenkinsUsername;
	}

	@JsonProperty("JenkinsPassword")
	public String getJenkinsPassword() {
		return jenkinsPassword;
	}

	@JsonProperty("JenkinsPassword")
	public void setJenkinsPassword(String jenkinsPassword) {
		this.jenkinsPassword = jenkinsPassword;
	}

	/**
	 * @return the jenkinsFolder
	 */
	public String getJenkinsFolder() {
		return jenkinsFolder;
	}

	/**
	 * @param jenkinsFolder
	 *            the jenkinsFolder to set
	 */
	public void setJenkinsFolder(String jenkinsFolder) {
		this.jenkinsFolder = jenkinsFolder;
	}

	@JsonProperty("ArtifactoryUrl")
	public String getArtifactoryUrl() {
		return artifactoryUrl;
	}

	@JsonProperty("ArtifactoryUrl")
	public void setArtifactoryUrl(String artifactoryUrl) {
		this.artifactoryUrl = artifactoryUrl;
	}

	@JsonProperty("TargetReleaseSynatax")
	public String getTargetReleaseSynatax() {
		return targetReleaseSynatax;
	}

	@JsonProperty("TargetReleaseSynatax")
	public void setTargetReleaseSynatax(String targetReleaseSynatax) {
		this.targetReleaseSynatax = targetReleaseSynatax;
	}

	@JsonProperty("TargetSnapshotSyntax")
	public String getTargetSnapshotSyntax() {
		return targetSnapshotSyntax;
	}

	@JsonProperty("TargetSnapshotSyntax")
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
