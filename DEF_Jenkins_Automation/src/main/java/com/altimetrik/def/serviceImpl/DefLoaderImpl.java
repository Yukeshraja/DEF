package com.altimetrik.def.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.CIServer;
import com.altimetrik.def.service.DefLoader;
import com.altimetrik.def.service.DefFactoryBean;


@Component
public class DefLoaderImpl implements DefLoader {

	@Autowired
	private DefFactoryBean depFactoryBean;
	
	private CIServer server;
	//private String serverRequested;

	
	
	/**
	 * @return the server
	 */
	public CIServer getServer() {
		return server;
	}

	/**
	 * @return the depFactoryBean
	 */
	public DefFactoryBean getDepFactoryBean() {
		return depFactoryBean;
	}

	/**
	 * @param depFactoryBean the depFactoryBean to set
	 */
	public void setDepFactoryBean(DefFactoryBean depFactoryBean) {
		this.depFactoryBean = depFactoryBean;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(CIServer server) {
		this.server = server;
	}

	/**
	 * 
	 */
	@Override
	public JobParams createJob(JobParams jobParams) {
		// TODO Auto-generated method stub
		server = depFactoryBean.createServer(jobParams);
		JobParams response = server.createJob(jobParams);
		return response;
	}

	/**
	 * 
	 */
	
	@Override
	public JobParams updateJob(JobParams jobParams) {
		// TODO Auto-generated method stub
		server = depFactoryBean.createServer(jobParams);
		JobParams jobParamsResponse = server.updateJob(jobParams);
		return jobParamsResponse;
	}

}