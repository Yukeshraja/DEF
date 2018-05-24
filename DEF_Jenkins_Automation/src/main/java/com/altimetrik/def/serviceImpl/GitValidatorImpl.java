package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altimetrik.def.controller.DefController;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.service.ScmValidator;

@Component
public class GitValidatorImpl implements ScmValidator {

	private final Logger logger = LoggerFactory.getLogger(DefServiceImpl.class);
	private final String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();
	@Autowired
	private CredentialMgmtImpl credentialMgmtImpl;
	private Map<String, String> errorMap = null;

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response chkDetailsValidation(SCM scm) {
		// TODO Auto-generated method stub
		logger.info("Inside chkDetailsValidation() Function of GitValidatorImpl");
		Response response = new Response();
		errorMap = new HashMap<String, String>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			props.load(DefController.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}
		if (StringUtils.isBlank(scm.getScmId())) {

			response.setStatus("FAILURE");
			logger.info(Constants.SCM_ID_CHECK + props.getProperty(Constants.SCM_ID_CHECK));
			errorMap.put(Constants.SCM_ID_CHECK, props.getProperty(Constants.SCM_ID_CHECK));
		}
		if (StringUtils.isBlank(scm.getName())) {
			response.setStatus("FAILURE");
			logger.info(Constants.SCM_TYPE + props.getProperty(Constants.SCM_TYPE));
			errorMap.put(Constants.SCM_TYPE, props.getProperty(Constants.SCM_TYPE));
		}

		if (StringUtils.isBlank(scm.getLink())) {
			response.setStatus("FAILURE");
			logger.info(Constants.SCM_LINK + props.getProperty(Constants.SCM_LINK));
			errorMap.put(Constants.SCM_LINK, props.getProperty(Constants.SCM_LINK));
		}

		/*if (StringUtils.isBlank(scm.getRepoBranch())) {
			response.setStatus("FAILURE");
			logger.info(Constants.SCM_REPO_BRANCH + props.getProperty(Constants.SCM_REPO_BRANCH));
			errorMap.put(Constants.SCM_REPO_BRANCH, props.getProperty(Constants.SCM_REPO_BRANCH));
		}*/
		if (!StringUtils.isBlank(scm.getAuthType())) {

			if (StringUtils.isBlank(scm.getCredentialID())) {
				response.setStatus("FAILURE");
				logger.info(Constants.SCM_CREDENTIAL_ID + props.getProperty(Constants.SCM_CREDENTIAL_ID));
				errorMap.put(Constants.SCM_CREDENTIAL_ID, props.getProperty(Constants.SCM_CREDENTIAL_ID));
			}

		}

		if (response.getStatus().equals("SUCCESS")) {
			response = urlDetailsValidation(scm);
		}
		logger.info("Ending Of chkDetailsValidation() Function of GitValidatorImpl");
		return response;
	}

	@Override
	public Response urlDetailsValidation(SCM scm) {
		// TODO Auto-generated method stub
		logger.info("Inside urlDetailsValidation() Function of GitValidatorImpl");
		Response response = new Response();
		errorMap = new HashMap<String, String>();
		Date date = new Date();
		CredentialResponse credentialResponse = credentialMgmtImpl.fetchOneCredentials(scm.getCredentialID());
		CredentialsProvider credentialsProvider = null;
		try {
			credentialsProvider = new UsernamePasswordCredentialsProvider(
					credentialResponse.getResult().get(0).getUserName(),
					credentialResponse.getResult().get(0).getPassword());
			logger.info("Credential Details Are Available in The Database");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage("Credential Details Are Not Available in The Database");
			logger.info("Credential Details Are Not Available in The Database");
		}
		Collection<Ref> refs = null;
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			refs = Git.lsRemoteRepository().setHeads(true).setCredentialsProvider(credentialsProvider)
					.setRemote(scm.getLink()).call();
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage("Kindly Check The Git URL or User Name and Password");
			errorMap.put("ERROR : ", e.getMessage());
			logger.info("Exception", Level.ERROR, e);

		} catch (TransportException e) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage("Kindly Check The Git URL or User Name and Password");
			errorMap.put("ERROR : ", e.getMessage());
			logger.info("Exception", Level.ERROR, e);

		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage("Kindly Check The Git URL or User Name and Password");
			errorMap.put("ERROR : ", e.getMessage());
			logger.info("Exception", Level.ERROR, e);

		}
		response.setError(errorMap);
		logger.info("Ending of urlDetailsValidation() Function of GitValidatorImpl");
		return response;
	}

}
