package com.altimetrik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altimetrik.dto.AWSCredentialDTO;
import com.altimetrik.services.AWSCredentialService;

/**
 * @author nmuthusamy
 *
 */
@Controller
public class AWSCredentialController {

	@Autowired
	AWSCredentialService service;

	@RequestMapping(value = "/addAWSCredential", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody AWSCredentialDTO addAWSCredential(@RequestBody AWSCredentialDTO awsCredentialDTO) {

		return service.addCredential(awsCredentialDTO);

	}

	/**
	 * This API need to execute before any other API 
	 * @param awsCredentialDTO
	 * @return AWSCredentialDTO
	 */
	@RequestMapping(value = "/getAWSCredential", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody AWSCredentialDTO getAWSCredential(@RequestBody AWSCredentialDTO awsCredentialDTO) {

		return service.getAWSCredential(awsCredentialDTO.getUser_name());

	}
}
