package com.altimetrik.services;

import com.amazonaws.services.ec2.model.DescribeImagesResult;

/**
 * @author nmuthusamy
 *
 */
public interface AMIService {

	public DescribeImagesResult getAMI();
}
