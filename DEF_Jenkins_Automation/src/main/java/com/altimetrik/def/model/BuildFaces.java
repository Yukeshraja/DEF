
package com.altimetrik.def.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Clean",
    /*"testClasses",
    "jar",
    "init",
    "wrapper",
    "projects",
    "properties",*/
    "Compile",
    "Build",
    "validate",
    "package",
    "install",
    "test",
    "deploy",
    "docker",
    "dockerImageName",
    "extraArgs",
    "dockerBuild"
})
public class BuildFaces {

    @JsonProperty("Clean")
    private Boolean clean;
    @JsonProperty("Build")
    private Boolean build;
/*    @JsonProperty("testClasses")
    private Boolean testClasses;
    @JsonProperty("jar")
    private Boolean jar;
    @JsonProperty("init")
    private Boolean init;
    @JsonProperty("wrapper")
    private Boolean wrapper;
    @JsonProperty("projects")
    private Boolean projects ;
    @JsonProperty("properties")
    private Boolean properties ;*/
    @JsonProperty("Compile")
    private Boolean compile;
    @JsonProperty("validate")
    private Boolean validate;
    @JsonProperty("package")
    private Boolean _package;
    @JsonProperty("install")
    private Boolean install;
    @JsonProperty("test")
    private Boolean test;
    @JsonProperty("deploy")
    private Boolean deploy;
    @JsonProperty("docker")
    private Boolean docker;
    @JsonProperty("dockerImageName")
    private String dockerImageName;
    @JsonProperty("extraArgs")
    private String extraArgs;
    @JsonProperty("dockerBuild")
    private Boolean dockerBuild;
    @JsonProperty("dockerFilePath")
    private String dockerFilePath;
    
	/**
	 * @return the clean
	 */
	public Boolean getClean() {
		return clean;
	}





	/**
	 * @param clean the clean to set
	 */
	public void setClean(Boolean clean) {
		this.clean = clean;
	}





	/**
	 * @return the build
	 */
	public Boolean getBuild() {
		return build;
	}





	/**
	 * @param build the build to set
	 */
	public void setBuild(Boolean build) {
		this.build = build;
	}





	/**
	 * @return the compile
	 */
	public Boolean getCompile() {
		return compile;
	}





	/**
	 * @param compile the compile to set
	 */
	public void setCompile(Boolean compile) {
		this.compile = compile;
	}





	/**
	 * @return the validate
	 */
	public Boolean getValidate() {
		return validate;
	}





	/**
	 * @param validate the validate to set
	 */
	public void setValidate(Boolean validate) {
		this.validate = validate;
	}





	/**
	 * @return the _package
	 */
	public Boolean get_package() {
		return _package;
	}





	/**
	 * @param _package the _package to set
	 */
	public void set_package(Boolean _package) {
		this._package = _package;
	}





	/**
	 * @return the install
	 */
	public Boolean getInstall() {
		return install;
	}





	/**
	 * @param install the install to set
	 */
	public void setInstall(Boolean install) {
		this.install = install;
	}





	/**
	 * @return the test
	 */
	public Boolean getTest() {
		return test;
	}





	/**
	 * @param test the test to set
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}





	/**
	 * @return the deploy
	 */
	public Boolean getDeploy() {
		return deploy;
	}





	/**
	 * @param deploy the deploy to set
	 */
	public void setDeploy(Boolean deploy) {
		this.deploy = deploy;
	}





	/**
	 * @return the docker
	 */
	public Boolean getDocker() {
		return docker;
	}





	/**
	 * @param docker the docker to set
	 */
	public void setDocker(Boolean docker) {
		this.docker = docker;
	}





	/**
	 * @return the dockerImageName
	 */
	public String getDockerImageName() {
		return dockerImageName;
	}





	/**
	 * @param dockerImageName the dockerImageName to set
	 */
	public void setDockerImageName(String dockerImageName) {
		this.dockerImageName = dockerImageName;
	}





	/**
	 * @return the extraArgs
	 */
	public String getExtraArgs() {
		return extraArgs;
	}





	/**
	 * @param extraArgs the extraArgs to set
	 */
	public void setExtraArgs(String extraArgs) {
		this.extraArgs = extraArgs;
	}





	/**
	 * @return the dockerBuild
	 */
	public Boolean getDockerBuild() {
		return dockerBuild;
	}





	/**
	 * @param dockerBuild the dockerBuild to set
	 */
	public void setDockerBuild(Boolean dockerBuild) {
		this.dockerBuild = dockerBuild;
	}


	


	/**
	 * @return the dockerFilePath
	 */
	public String getDockerFilePath() {
		return dockerFilePath;
	}





	/**
	 * @param dockerFilePath the dockerFilePath to set
	 */
	public void setDockerFilePath(String dockerFilePath) {
		this.dockerFilePath = dockerFilePath;
	}





	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	
}
