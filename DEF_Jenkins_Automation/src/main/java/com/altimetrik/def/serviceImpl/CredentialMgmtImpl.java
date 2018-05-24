package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.def.controller.DefController;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Credential;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.repository.DefCredentialRepository;
import com.altimetrik.def.service.CredentialMgmtInt;

@Service
public class CredentialMgmtImpl implements CredentialMgmtInt {

	private final Logger logger = LoggerFactory.getLogger(CredentialMgmtImpl.class);
	private final static String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();
	private Date date = new Date();

	@Autowired
	private DefCredentialRepository credentialRepository;
	private Map<String, String> err;

	/**
	 * @return the credentialRepository
	 */
	public DefCredentialRepository getCredentialRepository() {
		return credentialRepository;
	}

	/**
	 * @param credentialRepository
	 *            the credentialRepository to set
	 */
	public void setCredentialRepository(DefCredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}

	@Override
	public CredentialResponse createCredentials(Credential credentials) {
		// TODO Auto-generated method stub
		CredentialResponse response = new CredentialResponse();
		err = new HashMap<String, String>();
		if (credentials.getPassword() != null) {
			String password = credentials.getPassword();
			String updatedPassword = Base64.getEncoder().encodeToString(password.getBytes());
			credentials.setPassword(updatedPassword);
		}
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			credentialRepository.save(credentials);
			response.setErrorMessage("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			err.put("Error : ", "Please Chceck The Error Message ");
			response.setError(err);
		}
		return response;
	}

	@Override
	public CredentialResponse fetchAllCredentials() {
		// TODO Auto-generated method stub
		CredentialResponse response = new CredentialResponse();
		err = new HashMap<String, String>();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			List<Credential> allCredentials = credentialRepository.findAll();
			for (Credential credential : allCredentials) {
				if (credential.getPassword() != null) {
					String password = new String(Base64.getDecoder().decode(credential.getPassword()));
					credential.setPassword(password);
				}
			}
			response.setResult(allCredentials);
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			err.put("Error : ", "Please Chceck The Error Message ");
			response.setError(err);

		}
		return response;
	}

	@Override
	public CredentialResponse fetchOneCredentials(String credentialName) {
		// TODO Auto-generated method stub
		CredentialResponse response = new CredentialResponse();
		err = new HashMap<String, String>();
		List<Credential> credentials = new ArrayList<Credential>();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			Credential credentialDetails = credentialRepository.findOne(credentialName);
			if (credentialDetails.getPassword() != null) {
				String password = credentialDetails.getPassword();
				String decodedPassword = new String(Base64.getDecoder().decode(password));
				credentialDetails.setPassword(decodedPassword);
			}
			// response.getResult().get(0).setPassword(decodedPassword);
			credentials.add(credentialDetails);
			response.setResult(credentials);
			response.setErrorMessage("NO ERROR");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			err.put("Error : ", "Please Chceck The Error Message ");
			response.setError(err);
		}
		// logger.info("Response : " + response);
		return response;
	}

	@Override
	public CredentialResponse updateCredentials(Credential credential) {
		// TODO Auto-generated method stub
		CredentialResponse response = new CredentialResponse();
		err = new HashMap<String, String>();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		String updatedPassword = Base64.getEncoder().encodeToString(credential.getPassword().getBytes());
		credential.setPassword(updatedPassword);
		try {
			credentialRepository.save(credential);
			response.setErrorMessage("NO ERRORS");
		} catch (Exception exception) {
			response.setErrorMessage(exception.getMessage());
			response.setStatus("FAILURE");
			err.put("Error : ", "Please Chceck The Error Message ");
			response.setError(err);
		}
		return response;
	}

	@Override
	public CredentialResponse deleteCredentials(String credentialName) {
		// TODO Auto-generated method stub
		CredentialResponse response = new CredentialResponse();
		err = new HashMap<String, String>();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			credentialRepository.delete(credentialName);
			response.setErrorMessage("NO ERROR");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			err.put("Error : ", "Please Chceck The Error Message ");
			response.setError(err);
		}
		return response;
	}

	@Override
	public CredentialResponse dataValidation(Credential credential) {
		logger.info("Inside Data Validation Method : ");
		CredentialResponse credentialResponse = new CredentialResponse();
		credentialResponse.setStatus("SUCCESS");
		credentialResponse.setErrorMessage(null);
		credentialResponse.setTimestamp(date.getTime());
		credentialResponse.setResult(null);
		credentialResponse.setError(null);
		Map<String, String> errorMap = new HashMap<String, String>();

		try {
			props.load(DefController.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file in the data validation method, exiting..");
			System.exit(-1);
		}

		if (StringUtils.isBlank(credential.getCredentialName())) {
			credentialResponse.setStatus("FAILURE");
			credentialResponse.setErrorMessage("Error Occured : ");
			errorMap.put(Constants.CRED_MGMT_NAME, props.getProperty(Constants.CRED_MGMT_NAME));

		} else if (StringUtils.isBlank(credential.getType())) {
			credentialResponse.setStatus("FAILURE");
			credentialResponse.setErrorMessage("Error Occured : ");
			errorMap.put(Constants.CRED_MGMT_TYPE, props.getProperty(Constants.CRED_MGMT_TYPE));
		} else if (credential.getType().equals("up")) {
			if (StringUtils.isBlank(credential.getUserName())) {
				logger.info("Checking User Name");
				credentialResponse.setStatus("FAILURE");
				credentialResponse.setErrorMessage("Error Occured : ");
				errorMap.put(Constants.CRED_MGMT_USER_NAME, props.getProperty(Constants.CRED_MGMT_USER_NAME));
			} else if (StringUtils.isBlank(credential.getPassword())) {
				credentialResponse.setStatus("FAILURE");
				credentialResponse.setErrorMessage("Error Occured : ");
				errorMap.put(Constants.CRED_MGMT_PASSWORD, props.getProperty(Constants.CRED_MGMT_PASSWORD));
			}
		} else if (credential.getType().equals("ups")) {
			/*
			 * if (StringUtils.isBlank(credential.getUserName())) {
			 * credentialResponse.setStatus("FAILURE");
			 * credentialResponse.setErrorMessage("Error Occured : ");
			 * errorMap.put(Constants.CRED_MGMT_USER_NAME,
			 * props.getProperty(Constants.CRED_MGMT_USER_NAME)); } else if
			 * (StringUtils.isBlank(credential.getPassword())) {
			 * credentialResponse.setStatus("FAILURE");
			 * credentialResponse.setErrorMessage("Error Occured : ");
			 * errorMap.put(Constants.CRED_MGMT_PASSWORD,
			 * props.getProperty(Constants.CRED_MGMT_PASSWORD)); } else
			 */
			if (StringUtils.isBlank(credential.getSshKey())) {
				credentialResponse.setStatus("FAILURE");
				credentialResponse.setErrorMessage("Error Occured : ");
				errorMap.put(Constants.CRED_MGMT_SSH_KEY, props.getProperty(Constants.CRED_MGMT_SSH_KEY));
			}

		} else if (credential.getType().equals("upss")) {
			if (StringUtils.isBlank(credential.getSecretKey())) {
				credentialResponse.setStatus("FAILURE");
				credentialResponse.setErrorMessage("Error Occured : ");
				errorMap.put(Constants.CRED_MGMT_SEC_KEY, props.getProperty(Constants.CRED_MGMT_SEC_KEY));
			}

		}
		credentialResponse.setError(errorMap);
		return credentialResponse;

	}

}
