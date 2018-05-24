package com.altimetrik.services;

import com.altimetrik.dto.ElasticBeansTalkDTO;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationsResult;

/**
 * @author nmuthusamy
 *
 */
public interface ElasticBeansTalkService {

	public DescribeApplicationsResult getAllApplications();
	public CreateApplicationResult createApplication(ElasticBeansTalkDTO elasticBeansTalkDTO);
	public CreateEnvironmentResult createEnvironment();
	public void deployCode();
	
}
