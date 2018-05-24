package com.altimetrik.def.jenkins.model;

public class AllInputs {

	String url;
	String name;
	String trigger;
	String targets;
	String mavenName;
	String jvmOptions;
	String pom;
	String usePrivateRepository;
	String path;
	String sonarMavenOpts;
	String sonarRootPom;
	String sonarUsePrivateRepository;
	String sonarRecipientList;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public String getTargets() {
		return targets;
	}
	public void setTargets(String targets) {
		this.targets = targets;
	}
	public String getMavenName() {
		return mavenName;
	}
	public void setMavenName(String mavenName) {
		this.mavenName = mavenName;
	}
	public String getJvmOptions() {
		return jvmOptions;
	}
	public void setJvmOptions(String jvmOptions) {
		this.jvmOptions = jvmOptions;
	}
	public String getPom() {
		return pom;
	}
	public void setPom(String pom) {
		this.pom = pom;
	}
	public String getUsePrivateRepository() {
		return usePrivateRepository;
	}
	public void setUsePrivateRepository(String usePrivateRepository) {
		this.usePrivateRepository = usePrivateRepository;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSonarMavenOpts() {
		return sonarMavenOpts;
	}
	public void setSonarMavenOpts(String sonarMavenOpts) {
		this.sonarMavenOpts = sonarMavenOpts;
	}
	public String getSonarRootPom() {
		return sonarRootPom;
	}
	public void setSonarRootPom(String sonarRootPom) {
		this.sonarRootPom = sonarRootPom;
	}
	public String getSonarUsePrivateRepository() {
		return sonarUsePrivateRepository;
	}
	public void setSonarUsePrivateRepository(String sonarUsePrivateRepository) {
		this.sonarUsePrivateRepository = sonarUsePrivateRepository;
	}
	public String getSonarRecipientList() {
		return sonarRecipientList;
	}
	public void setSonarRecipientList(String sonarRecipientList) {
		this.sonarRecipientList = sonarRecipientList;
	}


}
