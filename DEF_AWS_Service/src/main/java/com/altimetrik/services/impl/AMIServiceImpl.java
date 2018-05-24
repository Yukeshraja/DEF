package com.altimetrik.services.impl;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.config.AmazonService;
import com.altimetrik.services.AMIService;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;

/**
 * @author nmuthusamy
 *
 */

@Service
public class AMIServiceImpl implements AMIService {

	@Autowired
	AmazonService amazonService;
	
	
	/**
	 * This method is used to get the list of Amazon Machine Image(AMI) with given filters like 
	 * architecture,platform,image-id,etc...
	 * @return DescribeImageResult -List of AMI 
	 */
	
	
	@Override
	public DescribeImagesResult getAMI() {
		
		DescribeImagesRequest describeImagesRequest = new DescribeImagesRequest().withFilters(new LinkedList<Filter>());
		//.withImageIds("ami-47205e28");
		describeImagesRequest.getFilters().add(new Filter().withName("description").withValues("*Linux*"));
		
		/* Below are the few available filters
		 * describeImagesRequest.getFilters().add(new Filter().withName("image-id").withValues("ami-8f8afde0"));
		 * describeImagesRequest.getFilters().add(new Filter().withName("architecture").withValues("x86_64"));
		 * describeImagesRequest.getFilters().add(new Filter().withName("platform").withValues("wind*"));
		 * describeImagesRequest.getFilters().add(new Filter().withName("state").withValues("available"));
		 */
		
		return amazonService.ec2().describeImages(describeImagesRequest);
	}

}
