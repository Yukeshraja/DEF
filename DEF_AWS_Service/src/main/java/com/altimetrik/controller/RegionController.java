package com.altimetrik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.services.RegionService;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class RegionController {

	@Autowired 
	RegionService service;
	
	@RequestMapping(value = "/getAvailableRegion", produces = "application/json", 
			consumes = "application/json", method = RequestMethod.GET)
	public @ResponseBody DescribeRegionsResult getRegions(){
		
		return service.getAvailableRegion();
	}
	
}
