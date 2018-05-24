
package com.altimetrik.def.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Name", "TeamMembers", "Buildpacks", "BuildFaces", "SCM", "ConnectTo", "Jenkins", "Concourse",
		"Pm", "CD", "status" })
@Document
public class Def {

	@JsonProperty("Name")
	@Id
	private String name;
	@JsonProperty("status")
	private String status;
	@JsonProperty("TeamMembers")
	private List<Object> teamMembers = null;
	@JsonProperty("Buildpacks")
	private Buildpacks buildpacks;
	@JsonProperty("BuildFaces")
	private BuildFaces buildFaces;
	@JsonProperty("SCM")
	private SCM scm;
	@JsonProperty("ConnectTo")
	private String connectTo;
	@JsonProperty("Jenkins")
	private Jenkins jenkins;
	@JsonProperty("Concourse")
	private Concourse concourse;
	@JsonProperty("Pm")
	private Pm pm;
	@JsonProperty("CD")
	private CD cd;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the teamMembers
	 */
	public List<Object> getTeamMembers() {
		return teamMembers;
	}

	/**
	 * @param teamMembers
	 *            the teamMembers to set
	 */
	public void setTeamMembers(List<Object> teamMembers) {
		this.teamMembers = teamMembers;
	}

	/**
	 * @return the buildpacks
	 */
	public Buildpacks getBuildpacks() {
		return buildpacks;
	}

	/**
	 * @param buildpacks
	 *            the buildpacks to set
	 */
	public void setBuildpacks(Buildpacks buildpacks) {
		this.buildpacks = buildpacks;
	}

	/**
	 * @return the buildFaces
	 */
	public BuildFaces getBuildFaces() {
		return buildFaces;
	}

	/**
	 * @param buildFaces
	 *            the buildFaces to set
	 */
	public void setBuildFaces(BuildFaces buildFaces) {
		this.buildFaces = buildFaces;
	}

	/**
	 * @return the scm
	 */
	public SCM getScm() {
		return scm;
	}

	/**
	 * @param scm
	 *            the scm to set
	 */
	public void setScm(SCM scm) {
		this.scm = scm;
	}

	/**
	 * @return the connectTo
	 */
	public String getConnectTo() {
		return connectTo;
	}

	/**
	 * @param connectTo
	 *            the connectTo to set
	 */
	public void setConnectTo(String connectTo) {
		this.connectTo = connectTo;
	}

	/**
	 * @return the jenkins
	 */
	public Jenkins getJenkins() {
		return jenkins;
	}

	/**
	 * @param jenkins
	 *            the jenkins to set
	 */
	public void setJenkins(Jenkins jenkins) {
		this.jenkins = jenkins;
	}

	/**
	 * @return the concourse
	 */
	public Concourse getConcourse() {
		return concourse;
	}

	/**
	 * @param concourse
	 *            the concourse to set
	 */
	public void setConcourse(Concourse concourse) {
		this.concourse = concourse;
	}

	/**
	 * @return the pm
	 */
	public Pm getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(Pm pm) {
		this.pm = pm;
	}

	/**
	 * @return the cd
	 */
	public CD getCd() {
		return cd;
	}

	/**
	 * @param cd the cd to set
	 */
	public void setCd(CD cd) {
		this.cd = cd;
	}

	

}
