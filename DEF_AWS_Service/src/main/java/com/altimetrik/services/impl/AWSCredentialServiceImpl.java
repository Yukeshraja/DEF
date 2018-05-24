package com.altimetrik.services.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altimetrik.dto.AWSCredentialDTO;
import com.altimetrik.dto.AWSLoginCredDTO;
import com.altimetrik.services.AWSCredentialService;

/**
 * @author nmuthusamy
 *
 */

@Service
@Transactional
@Repository
public class AWSCredentialServiceImpl implements AWSCredentialService {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This methos is used to save new user with his aws credetials
	 * 
	 * @param awsCredentialsDTO
	 *            -which holds new username, awsCredentials
	 * @return awsCredentialDTO
	 */
	@Override
	public AWSCredentialDTO addCredential(AWSCredentialDTO awsCredentialDTO) {

		entityManager.persist(awsCredentialDTO);
		return awsCredentialDTO;
	}

	/**
	 * This method is used to get the aws credentials for the corresponding user
	 * 
	 * @param user_nam
	 *            -Username to fetch their aws credentials
	 */
	@Override
	public AWSCredentialDTO getAWSCredential(String user_name) {
		// It is a very simple method to get the aws credentials from local DB
		AWSLoginCredDTO dto = AWSLoginCredDTO.getAWSLoginCred();
		AWSCredentialDTO cred = (AWSCredentialDTO) entityManager
				.createNativeQuery("select * from aws_user_credentials a where a.user_name=:userName",
						AWSCredentialDTO.class)
				.setParameter("userName", user_name).getSingleResult();
		dto.setAws_access_key_id(cred.getAws_access_key_id());
		dto.setAws_secret_access_key(cred.getAws_secret_access_key());
		return cred;
	}

}
