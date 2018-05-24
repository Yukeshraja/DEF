package com.altimetrik.services;

import com.amazonaws.services.ec2.model.DescribeRegionsResult;

/**
 * @author nmuthusamy
 *
 */
public interface RegionService {

	public DescribeRegionsResult getAvailableRegion();
}
