package com.altimetrik.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.dto.CreateInstanceDTO;
import com.altimetrik.dto.InstancesDTO;
import com.altimetrik.services.InstanceServices;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

/**
 * 
 * @author nmuthusamy
 * 
 */

@Service
public class InstanceServicesImpl implements InstanceServices {

	final static Logger logger = Logger.getLogger(InstanceServicesImpl.class);

	@Autowired
	AmazonService amazonService;

	JsonParser parser = new JsonParser();

	/**
	 * This method is used to get all the available Instances for the
	 * corresponding user
	 * 
	 * @return ListOfInstances
	 */
	@Override
	public List<InstancesDTO> getAllInstances() {
		logger.info("Getting all available instance method START...");
		logger.info("Getting Instances...");
		DescribeInstancesResult response = null;

		boolean done = false;

		DescribeInstancesRequest request = new DescribeInstancesRequest();
		List<InstancesDTO> instantList = new ArrayList<InstancesDTO>();
		InstancesDTO dto = null;

		response = amazonService.ec2().describeInstances(request);

		done = false;
		while (!done) {

			for (Reservation reservation : response.getReservations()) {
				for (Instance instance : reservation.getInstances()) {
					dto = new InstancesDTO();
					dto.setImageId(instance.getImageId());
					dto.setInstanceId(instance.getInstanceId());
					dto.setKeyName(instance.getKeyName());
					dto.setMonitoringState(instance.getMonitoring().getState());
					dto.setStatus(instance.getState().getName());
					instantList.add(dto);
				}
			}

			request.setNextToken(response.getNextToken());

			if (response.getNextToken() == null) {
				done = true;
			}
		}
		logger.info("Getting all available instance method END...");
		return instantList;

	}

	/**
	 * This method is used to Stop the Instance
	 * 
	 * @param instanceId
	 *            Id of Instance which needs to be stopped
	 * @return StopInstanceResult
	 */
	@Override
	public StopInstancesResult stopInstance(HttpEntity<String> httpEntity) {
		logger.info("Stop Instance with ID "
				+ parser.parse(httpEntity.getBody()).getAsJsonObject().get("InstanceId").getAsString()
				+ " Starting...");
		logger.info("Stopping Instance...");
		StopInstancesRequest request = new StopInstancesRequest()
				.withInstanceIds(parser.parse(httpEntity.getBody()).getAsJsonObject().get("InstanceId").getAsString());

		StopInstancesResult result = amazonService.ec2().stopInstances(request);

		logger.info("Stop Instance End...");
		return result;
	}

	/**
	 * This method is used to Start the Instance
	 * 
	 * @param instanceId
	 *            Id for the instance which needs to be started
	 * @return StartInstanceResult
	 */
	@Override
	public StartInstancesResult startInstance(HttpEntity<String> httpEntity) {
		logger.info("Start instance with ID "
				+ parser.parse(httpEntity.getBody()).getAsJsonObject().get("InstanceId").getAsString()
				+ " Starting...");
		logger.info("Starting Instance...");
		StartInstancesRequest request = new StartInstancesRequest()
				.withInstanceIds(parser.parse(httpEntity.getBody()).getAsJsonObject().get("InstanceId").getAsString());

		StartInstancesResult result = amazonService.ec2().startInstances(request);
		logger.info("Start instance End...");
		return result;
	}

	/**
	 * This method is used to Create new EC2 Instance
	 * 
	 * @param amiid
	 *            AMIID is the ID for the corresponding AMI
	 * @param instanceType
	 *            is the type of instance which needs to be created
	 * @param securityGroupId
	 *            SecuritygroupId for the Instance
	 * @param keyName
	 *            Key for the Instance
	 * @param tagName
	 *            Tag name for the Instance
	 * @return RunInstancesResult
	 */
	@Override
	public RunInstancesResult createInstance(CreateInstanceDTO createInstancedto) {

		logger.info("Instance creation process starting...");
		logger.info("Processing...");
		RunInstancesRequest run_request = new RunInstancesRequest().withImageId(createInstancedto.getAmiid())
				.withInstanceType(InstanceType.fromValue(createInstancedto.getInstanceType())).withMaxCount(1)
				.withSecurityGroupIds(createInstancedto.getSecurityGroupId())
				.withKeyName(createInstancedto.getKeyName()).withMinCount(1);

		RunInstancesResult run_response = amazonService.ec2().runInstances(run_request);
		logger.info("Instance created successfully...");
		logger.info("Instance created successfully with Instance ID = "
				+ run_response.getReservation().getInstances().get(0).getInstanceId());

		String instance_id = run_response.getReservation().getInstances().get(0).getInstanceId();


		logger.info("Tag Name creation for the created instance is Starting...");
		Tag tag = new Tag().withKey("Name").withValue(createInstancedto.getTagName());

		CreateTagsRequest tag_request = new CreateTagsRequest().withTags(tag).withResources(instance_id);
		amazonService.ec2().createTags(tag_request);

		logger.info("Instance with instanceID '" + run_response.getReservation().getInstances().get(0).getInstanceId()
				+ "' has been tagged with value '" + createInstancedto.getTagName() + " '");

		return run_response;
	}

	/**
	 * This method is used to Terminate the Instance and after some time
	 * Terminated instance will gets deleted automatically. Once an Instance is
	 * deleted it cannot be reverted back
	 * 
	 * @param instanceID
	 *            ID for the Instance which needs to be terminated
	 * @return TerminateInstancesResult
	 */
	@Override
	public TerminateInstancesResult terminateInstance(String instanceID) {

		logger.info("Terminating instance '" + instanceID + "' starting...");
		TerminateInstancesRequest request = new TerminateInstancesRequest().withInstanceIds(instanceID);
		TerminateInstancesResult result = amazonService.ec2().terminateInstances(request);

		logger.info("Instance '" + instanceID + " "
				+ result.getTerminatingInstances().get(0).getCurrentState().getName() + "...");

		logger.info("Terminating instance ending...");
		return result;
	}

}
