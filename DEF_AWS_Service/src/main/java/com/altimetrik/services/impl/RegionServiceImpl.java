package com.altimetrik.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.services.RegionService;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;

/**
 * @author nmuthusamy
 *
 */

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	AmazonService amazonService;

	/**
	 * This method is used to fetch the available Regions were AWS servers are located
	 * @return DescribeRegionsResult
	 */
	@Override
	public DescribeRegionsResult getAvailableRegion() {

		DescribeRegionsResult regions_response = amazonService.ec2().describeRegions();
		
		return regions_response;
		
	}

}
