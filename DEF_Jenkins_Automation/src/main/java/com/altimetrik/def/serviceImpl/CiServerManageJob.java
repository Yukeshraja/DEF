package com.altimetrik.def.serviceImpl;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.BuildDetails;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.CIServer;
import com.altimetrik.def.service.DefFactoryBean;

@Component
public class CiServerManageJob {
	
	@Autowired
	DefServiceImpl defServiceImpl;	
	
	@Autowired
	DefFactoryBean defFactoryBean;
	
	public void startJob(String jobName) throws Exception {
		JobParams oneJobDetails = defServiceImpl.getOneJobDetails(jobName);				
		oneJobDetails.setDefInputValue(oneJobDetails.getReturnValue().getResult().get(0));
		CIServer createServer = defFactoryBean.createServer(oneJobDetails);
		createServer.startJob(oneJobDetails);
	}
	
	public void stopJob(String jobName) throws URISyntaxException {
		JobParams oneJobDetails = defServiceImpl.getOneJobDetails(jobName);				
		oneJobDetails.setDefInputValue(oneJobDetails.getReturnValue().getResult().get(0));
		CIServer createServer = defFactoryBean.createServer(oneJobDetails);
		createServer.stopJob(oneJobDetails);
	}	
	
/*	public String viewJobLogs(String jobName) throws URISyntaxException {
		JobParams oneJobDetails = defServiceImpl.getOneJobDetails(jobName);				
		oneJobDetails.setDefInputValue(oneJobDetails.getReturnValue().getResult().get(0));
		CIServer createServer = defFactoryBean.createServer(oneJobDetails);
		return createServer.viewJobLogs(oneJobDetails);
	}*/	
	
	public BuildDetails getJobStatus(String jobName) throws URISyntaxException {
		JobParams oneJobDetails = defServiceImpl.getOneJobDetails(jobName);				
		oneJobDetails.setDefInputValue(oneJobDetails.getReturnValue().getResult().get(0));
		CIServer createServer = defFactoryBean.createServer(oneJobDetails);
		return createServer.getJobDetails(oneJobDetails);
	}

}
