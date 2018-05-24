package com.altimetrik.services;

import com.altimetrik.dto.AWSCredentialDTO;

/**
 * @author nmuthusamy
 *
 */
public interface AWSCredentialService {

	public AWSCredentialDTO addCredential(AWSCredentialDTO awsCredentialDTO);
	public AWSCredentialDTO getAWSCredential(String user_name);
}
