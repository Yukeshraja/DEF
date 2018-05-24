package com.altimetrik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.services.AMIService;
import com.amazonaws.services.ec2.model.DescribeImagesResult;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class AMIController {

	@Autowired
	AMIService service;
	
	@RequestMapping(value = "/getAMIs" , produces = "application/json" , method = RequestMethod.GET)
	public @ResponseBody DescribeImagesResult getAMI(){
		return service.getAMI();
	}
}
