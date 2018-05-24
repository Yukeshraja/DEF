package com.altimetrik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.dto.ElasticBeansTalkDTO;
import com.altimetrik.exception.AltiException;
import com.altimetrik.services.ElasticBeansTalkService;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationResult;
import com.amazonaws.services.elasticbeanstalk.model.CreateEnvironmentResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationsResult;

/**
 * @author nmuthusamy
 *
 */

@Controller
public class ElasticBeansTalkController {

	@Autowired
	ElasticBeansTalkService service;
	
	@RequestMapping(value = "/getAllApplication", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody DescribeApplicationsResult getAllApplication(){
		
		return service.getAllApplications();
	}
	
	@RequestMapping(value = "/createApplication", produces = "application/json",consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody CreateApplicationResult createApplication(@RequestBody ElasticBeansTalkDTO elasticBeansTalkDTO){
		
		 return service.createApplication(elasticBeansTalkDTO);
	}
	
	@RequestMapping(value = "/createEnvironment", produces = "application/json",consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody CreateEnvironmentResult createEnvironment(@RequestBody ElasticBeansTalkDTO elasticBeansTalkDTO){
		
		return service.createEnvironment();
	}
	
	@RequestMapping(value = "/deployCode", produces = "application/json", consumes = "application/json", method = RequestMethod.GET)
	public @ResponseBody void deployCode() throws AltiException{
		service.deployCode();
	}
}
