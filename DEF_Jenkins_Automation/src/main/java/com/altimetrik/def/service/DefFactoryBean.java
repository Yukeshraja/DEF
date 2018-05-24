package com.altimetrik.def.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.JobParams;


@Component
public class DefFactoryBean {

	/*  @Bean
	  @Autowired
	  @Scope("prototype") //As we want to create several beans with different args, right?
	  CIServer ciServerDyna(String name) {
	    return "Jenkins".equals(name) ? new JenkinsLoaderImpl() : null ;
	  }*/
	
	
		private CIServer ciServer;
	
		@Autowired
		@Qualifier("jenkins")
		private CIServer jenkinsServer;
	  
		private String serverRequested;
		/*
		@Bean(name={"jenkins"})
		public CIServer getCIServer1() {  
			  return new JenkinsLoaderImpl();
		}*/
		public CIServer createServer(JobParams jobParams) {
			// AnnotationConfigApplicationContext context = new
			// AnnotationConfigApplicationContext(DepFactoryBean.class);
			switch (getServerRequested(jobParams)) {
			case "Jenkins": {
				ciServer = jenkinsServer;
				// server = (CIServer) context.getBean("ciServerDyna", "Jenk
		}
	}
			return ciServer;
}			
			
		
		
		
		public String getServerRequested(JobParams jobParams) {
				if (jobParams.getDefInputValue().getJenkins().getJenkinsURL() != null &&
						jobParams.getDefInputValue().getJenkins().getAuthType() != null &&
						jobParams.getDefInputValue().getJenkins().getCredentialID() != null) {
					serverRequested = "Jenkins"; 
				} else {
					if (jobParams.getDefInputValue().getConcourse().getConcourseURL() != null &&
							jobParams.getDefInputValue().getConcourse().getConcourseUsername() != null &&
							jobParams.getDefInputValue().getConcourse().getConcoursePassword() != null) {
						serverRequested = "Concourse"; 
					} 
				}
				return serverRequested;
			}

			public void setServerRequested(String serverRequested) {
				this.serverRequested = serverRequested;
			}

}
