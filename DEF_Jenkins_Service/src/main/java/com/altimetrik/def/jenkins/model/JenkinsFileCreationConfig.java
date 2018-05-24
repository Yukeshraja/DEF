package com.altimetrik.def.jenkins.model;

public class JenkinsFileCreationConfig {
    private String linux;
    private String windows;
    private String checkout;
    private String validate;
    private String compile;
    private String pkg;
    private String install;
    
	private String test;
    private String approve;    
    private String deploy;
    private String mailing;
    private String print;

   

	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	

	public String getLinux() {
		return linux;
	}
	public void setLinux(String linux) {
		this.linux = linux;
	}
	public String getWindows() {
		return windows;
	}
	public void setWindows(String windows) {
		this.windows = windows;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getCompile() {
		return compile;
	}
	public void setCompile(String compile) {
		this.compile = compile;
	}
	public String getInstall() {
		return install;
	}
	public void setInstall(String install) {
		this.install = install;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getDeploy() {
		return deploy;
	}
	public void setDeploy(String deploy) {
		this.deploy = deploy;
	}
	
	public String getMailing() {
		return mailing;
	}
	public void setMailing(String mailing) {
		this.mailing = mailing;
	}
	
    @Override
	public String toString() {
		return "Config [checkout=" + checkout + ", pkg=" + pkg + ", linux=" + linux + ", windows=" + windows + ", validate=" + validate + ", compile=" + compile + ", install=" + install +
				", test=" + test +", approve=" + approve +", deploy=" + deploy +"]";
		//", roles=" + Arrays.toString(roles)
	//			+ "]";
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
}