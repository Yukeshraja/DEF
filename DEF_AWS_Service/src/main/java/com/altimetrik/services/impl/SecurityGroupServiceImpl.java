package com.altimetrik.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.dto.SecurityGroupDTO;
import com.altimetrik.services.SecurityGroupService;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.SecurityGroup;

/**
 * @author nmuthusamy
 *
 */

@Service
public class SecurityGroupServiceImpl implements SecurityGroupService {

	@Autowired
	AmazonService amazonService;

	/**
	 * This method is used to describe all available Security Group
	 * 
	 * @return securityGroupList -List of available security Group
	 */
	@Override
	public List<SecurityGroupDTO> getAllSecurityGroup() {

		List<SecurityGroupDTO> securityGroupList = new ArrayList<SecurityGroupDTO>();

		DescribeSecurityGroupsRequest request = new DescribeSecurityGroupsRequest();

		DescribeSecurityGroupsResult response = amazonService.ec2().describeSecurityGroups(request);

		for (SecurityGroup group : response.getSecurityGroups()) {
			SecurityGroupDTO dto = new SecurityGroupDTO();
			dto.setDescription(group.getDescription());
			dto.setGroupID(group.getGroupId());
			dto.setGroupName(group.getGroupName());
			dto.setVpcID(group.getVpcId());
			securityGroupList.add(dto);
		}

		return securityGroupList;
	}

	@Override
	public CreateSecurityGroupResult createSecurotyGroup(SecurityGroupDTO securityGroupDTO) {
		CreateSecurityGroupRequest create_request = new CreateSecurityGroupRequest()
				.withGroupName(securityGroupDTO.getGroupName()).withDescription(securityGroupDTO.getDescription());

		CreateSecurityGroupResult create_response = amazonService.ec2().createSecurityGroup(create_request);
		return create_response;
	}

	@Override
	public DeleteSecurityGroupResult deleteSecurityGroup(SecurityGroupDTO securityGroupDTO) {

		DeleteSecurityGroupRequest request = new DeleteSecurityGroupRequest()
				.withGroupId(securityGroupDTO.getGroupID());

		DeleteSecurityGroupResult response = amazonService.ec2().deleteSecurityGroup(request);
		
		return response;
	}

}
