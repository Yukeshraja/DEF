package com.altimetrik.services.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.dto.ElasticBeansTalkDTO;
import com.altimetrik.services.ElasticBeansTalkService;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateStorageLocationResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationsResult;
import com.amazonaws.services.elasticbeanstalk.model.S3Location;
import com.amazonaws.services.elasticbeanstalk.model.UpdateEnvironmentRequest;
import com.amazonaws.services.elasticbeanstalk.model.UpdateEnvironmentResult;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

/**
 * @author nmuthusamy
 */
@Service
public class ElasticBeansTalkServiceImpl implements ElasticBeansTalkService {

	@Autowired
	AmazonService service;

	/**
	 * This method is used to list down all available Applications in AWS
	 * 
	 * @return DescribeApplicationsResult -list of available Applications
	 */
	@Override
	public DescribeApplicationsResult getAllApplications() {

		DescribeApplicationsResult result = service.beansTalk().describeApplications();
		return result;

	}

	/**
	 * This method is used to create a new Application with the user provided
	 * details
	 * 
	 * @param ElasticBeansTalkDTO
	 *            -this object contains Name and Description for the new
	 *            Application
	 * @return CreateApplicationResult -result object with details of the newly
	 *         created Application
	 */
	@Override
	public CreateApplicationResult createApplication(ElasticBeansTalkDTO elasticBeansTalkDTO) {

		CreateApplicationRequest createApplicationRequest = new CreateApplicationRequest()
				.withApplicationName(elasticBeansTalkDTO.getApplicationName())
				.withDescription(elasticBeansTalkDTO.getApplicationDescription());
		CreateApplicationResult createApplicationResult = service.beansTalk().createApplication(createApplicationRequest);
		return createApplicationResult;
	}

	/**
	 * This method is used to create new environment under the selected
	 * Application
	 * 
	 * @return CreateEnvironmentResult
	 */
	@Override
	public CreateEnvironmentResult createEnvironment() {

		// Here everything is hard coded for testing. All the hard coded values
		// needs to be replaced with the inputs from UI
		CreateEnvironmentRequest createEnvironmentRequest = new CreateEnvironmentRequest()
				.withApplicationName("MySampleApplicationThree").withEnvironmentName("MySampleApplicationThree-env")
				.withCNAMEPrefix("MySampleApplicationThree")
				// .withSolutionStackName("64bit Amazon Linux 2017.03 v2.5.1 running Java 8")
				.withSolutionStackName("64bit Amazon Linux 2017.03 v2.6.1 running Tomcat 8 Java 8")
				.withVersionLabel("Sample Application");
		return service.beansTalk().createEnvironment(createEnvironmentRequest);

	}

	/*
	 * deployCode() method is to deploy new war file by overwriting the existing
	 * sample file created during the creation of environment
	 */
	@Override
	public void deployCode() {

		// Create Environment
		/*
		 * CreateEnvironmentRequest envRequest = new
		 * CreateEnvironmentRequest("SampleApplication",
		 * "SampleApplication-env2"); envRequest.
		 * setSolutionStackName("64bit Amazon Linux 2017.03 v2.6.1 running Tomcat 8 Java 8"
		 * );
		 * 
		 * envRequest.setVersionLabel("Sample Application");
		 * service.beansTalk().createEnvironment(envRequest);
		 */
		/*
		 * if(true){ throw new RuntimeException("Something went wrong"); }
		 */

		// Here everything is hard coded for testing. Replace all the hard coded
		// values by getting inputs from UI

		// Create a storage location in s3 bucket
		// Read the war file and put in s3 bucket
		CreateStorageLocationResult location = service.beansTalk().createStorageLocation();
		String bucket = location.getS3Bucket();
		File file = new File("FirstServlet.war");
		PutObjectRequest object = new PutObjectRequest(bucket, "FirstServlet.war", file);
		PutObjectResult res = service.s3().putObject(object);
		System.out.println(res.getClass());

		// Create new application version with new file which needs to be
		// deployed
		CreateApplicationVersionRequest versionRequest = new CreateApplicationVersionRequest();
		versionRequest.setVersionLabel("First Servlet");
		versionRequest.setApplicationName("SampleApplication");
		S3Location s3 = new S3Location(bucket, "FirstServlet.war");
		versionRequest.setSourceBundle(s3);
		CreateApplicationVersionResult resu = service.beansTalk().createApplicationVersion(versionRequest);
		System.out.println(resu);

		// Update the environment with new application version
		UpdateEnvironmentRequest updateRequest = new UpdateEnvironmentRequest();
		updateRequest.setEnvironmentId("e-pm3emq9");
		updateRequest.setVersionLabel("First Servle");
		UpdateEnvironmentResult result = service.beansTalk().updateEnvironment(updateRequest);
		System.out.println(result);

		/*
		 * UpdateEnvironmentRequest req = new UpdateEnvironmentRequest()
		 * .withEnvironmentId("e-gn6aattrpe")
		 * .withVersionLabel("FirstServlet1"); UpdateEnvironmentResult res =
		 * service.beansTalk().updateEnvironment(req);
		 */
	}

}
