package com.altimetrik.def.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.def.model.Credential;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.serviceImpl.CredentialMgmtImpl;



@RestController
@RequestMapping("/")
public class CredentialMgmtController {

	private Logger logger = LoggerFactory.getLogger(CredentialMgmtController.class);
	
	@Autowired
	private CredentialMgmtImpl credentialService;

	/**
	 * @return the credentialService
	 */
	public CredentialMgmtImpl getCredentialService() {
		return credentialService;
	}

	/**
	 * @param credentialService
	 *            the credentialService to set
	 */
	public void setCredentialService(CredentialMgmtImpl credentialService) {
		this.credentialService = credentialService;
	}


	@RequestMapping(value = "/createCredential", method = RequestMethod.POST)
	public CredentialResponse createCredentials(@RequestBody Credential credential) {
		logger.info("Inside createCredentials() of CredentialMgmtController : ");
		
		CredentialResponse response = credentialService.dataValidation(credential);
		if (response.getStatus().equals("SUCCESS")) {
			response = credentialService.createCredentials(credential);
		}
		logger.info("createCredentials() method of CredentialMgmtController ended : ");
		return response;
	}

	@RequestMapping(value = "/getAllCredentials", method = RequestMethod.GET)
	public CredentialResponse fetchAllCredentials() {
		logger.info("Inside fetchAllCredentials() of CredentialMgmtController : ");
		CredentialResponse response = credentialService.fetchAllCredentials();
		logger.info("fetchAllCredentials() method of CredentialMgmtController ended : ");
		return response;
	}

	@RequestMapping(value = "/getOneCredentials/{credentialName}", method = RequestMethod.GET)
	public CredentialResponse fetchOneCredentials(@PathVariable String credentialName) {
		logger.info("Inside fetchOneCredentials() of CredentialMgmtController : ");
		CredentialResponse response = credentialService.fetchOneCredentials(credentialName);
		logger.info("fetchOneCredentials() method of CredentialMgmtController ended : ");
		return response;
	}

	@RequestMapping(value = "/updateCredential/{credentialName}", method = RequestMethod.PUT)
	public CredentialResponse updateCredential(@RequestBody Credential credential,
			@PathVariable String credentialName) {
		logger.info("Inside updateCredential() of CredentialMgmtController : ");
		CredentialResponse response = credentialService.dataValidation(credential);
		if (response.getStatus().equals("SUCCESS")) {
			response = credentialService.updateCredentials(credential);
		}
		logger.info("updateCredential() method of CredentialMgmtController ended : ");
		return response;
	}

	@RequestMapping(value = "/deleteCredential/{credentialName}", method = RequestMethod.DELETE)
	public CredentialResponse deleteCredential(@PathVariable String credentialName) {
		logger.info("Inside deleteCredential() of CredentialMgmtController : ");
		CredentialResponse response = credentialService.deleteCredentials(credentialName);
		logger.info("deleteCredential() method of CredentialMgmtController ended : ");
		return response;
	}
}
