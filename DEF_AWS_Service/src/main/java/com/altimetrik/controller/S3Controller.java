package com.altimetrik.controller;

import java.util.List;

import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.dto.S3BucketDTO;
import com.altimetrik.services.S3Service;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class S3Controller {

	@Autowired
	S3Service service;
	
	@RequestMapping(value = "/getAllBuckets", produces ="application/json", consumes = "application/json",
			method=RequestMethod.GET)
	public @ResponseBody List<Bucket> getAllBuckets(){
		
		return service.getAllS3Bucket();
	}
	
	@RequestMapping(value = "/createBucket", produces ="application/json", consumes = "application/json",
			method=RequestMethod.POST)
	public @ResponseBody S3BucketDTO createBucket(@RequestBody S3BucketDTO s3BucketDTO, HttpEntity httpEntity){
		
		return service.createBucket(s3BucketDTO);
	}
	
}
