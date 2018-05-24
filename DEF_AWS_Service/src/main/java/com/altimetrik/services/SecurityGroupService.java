package com.altimetrik.services;

import java.util.List;

import com.altimetrik.dto.SecurityGroupDTO;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupResult;

/**
 * @author nmuthusamy
 *
 */
public interface SecurityGroupService {

	public List<SecurityGroupDTO> getAllSecurityGroup();
	public CreateSecurityGroupResult createSecurotyGroup(SecurityGroupDTO securityGroupDTO);
	public DeleteSecurityGroupResult deleteSecurityGroup(SecurityGroupDTO securityGroupDTO);
}
