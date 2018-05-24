package com.altimetrik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.dto.SecurityGroupDTO;
import com.altimetrik.services.SecurityGroupService;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupResult;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class SecurityGroupController {

	@Autowired
	SecurityGroupService service;

	@RequestMapping(value = "/getAllSecurityGroup", produces = "application/json", 
			consumes = "application/json", method = RequestMethod.GET)
	private @ResponseBody List<SecurityGroupDTO> getAllSecurityGroup() {

		return service.getAllSecurityGroup();
	}

	@RequestMapping(value = "/createSecurityGroup", produces = "application/json", 
			consumes = "application/json", method = RequestMethod.POST)
	private @ResponseBody CreateSecurityGroupResult createSecurityGroup(@RequestBody SecurityGroupDTO securityGroupDTO){
		
		return service.createSecurotyGroup(securityGroupDTO);
	}
	
	@RequestMapping(value = "/deleteSecurityGroup", produces = "application/json", 
			consumes = "application/json", method = RequestMethod.POST)
	private @ResponseBody DeleteSecurityGroupResult deleteSecurityGroup(@RequestBody SecurityGroupDTO securityGroupDTO){
		
		return service.deleteSecurityGroup(securityGroupDTO);
	}
	
}
