package com.altimetrik.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import com.altimetrik.dto.CreateInstanceDTO;
import com.altimetrik.dto.InstancesDTO;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
/**
 * @author nmuthusamy
 *
 */
@Service
public interface InstanceServices {

	public List<InstancesDTO> getAllInstances();
	public StopInstancesResult stopInstance(HttpEntity<String> httpEntity);
	public StartInstancesResult startInstance(HttpEntity<String> httpEntity);
	public RunInstancesResult createInstance(CreateInstanceDTO createInstancedtos);
	public TerminateInstancesResult terminateInstance(String instanceID);
}
