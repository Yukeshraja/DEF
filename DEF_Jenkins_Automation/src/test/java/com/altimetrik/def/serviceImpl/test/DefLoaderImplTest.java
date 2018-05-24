package com.altimetrik.def.serviceImpl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.CIServer;
import com.altimetrik.def.service.DefFactoryBean;
import com.altimetrik.def.serviceImpl.DefLoaderImpl;
import com.altimetrik.def.serviceImpl.JenkinsLoaderImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DefLoaderImplTest {

	@Mock
	private CIServer server;
	
	@Mock
	private DefFactoryBean depFactoryBean;
	
	@Test
	public void createJobTest(){
		JobParams jobParams = new JobParams();
		Def def = new Def();
		Response response = new Response();
		Jenkins jenkins = new Jenkins();
		DefLoaderImpl defLoaderImpl = new DefLoaderImpl();
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		response.setStatus("SUCCESS");
		response.setErrorMessage("NO ERROR");
		def.setJenkins(jenkins);
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(depFactoryBean.createServer(jobParams)).willReturn(server);
		given(server.createJob(jobParams)).willReturn(jobParams);
		defLoaderImpl.setDepFactoryBean(depFactoryBean);
		defLoaderImpl.setServer(server);
		JobParams returnRespone = defLoaderImpl.createJob(jobParams);
		assertEquals("SUCCESS", returnRespone.getReturnValue().getStatus());
		
	}

	@Test
	public void updateJobTest(){
		JobParams jobParams = new JobParams();
		Def def = new Def();
		Response response = new Response();
		Jenkins jenkins = new Jenkins();
		DefLoaderImpl defLoaderImpl = new DefLoaderImpl();
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		response.setStatus("SUCCESS");
		response.setErrorMessage("NO ERROR");
		def.setJenkins(jenkins);
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(depFactoryBean.createServer(jobParams)).willReturn(server);
		given(server.updateJob(jobParams)).willReturn(jobParams);
		defLoaderImpl.setDepFactoryBean(depFactoryBean);
		defLoaderImpl.setServer(server);
		JobParams returnRespone = defLoaderImpl.updateJob(jobParams);
		assertEquals("SUCCESS", returnRespone.getReturnValue().getStatus());
		
	}
	
	/*	
	@Test
	public void updateJobTest(){
		
		JobParams jobParams = new JobParams();
		Def def = new Def();
		Date date = new Date();
		Jenkins jenkins = new Jenkins();
		DefLoaderImpl defLoaderImpl = new DefLoaderImpl();
		Response response = new Response();
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		def.setJenkins(jenkins);
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		defLoaderImpl.createServer(jobParams);
		//server = new JenkinsLoaderImpl();
		given(server.createJob(jobParams)).willReturn(jobParams);
		defLoaderImpl.setServer(server);
		JobParams returnResponse = defLoaderImpl.updateJob(jobParams);
		assertEquals("SUCCESS", returnResponse.getReturnValue().getStatus());
	}*/
}