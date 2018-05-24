package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.Response;
import com.offbytwo.jenkins.JenkinsServer;

@Component
public class JenkinsCiServiceImpl{

	private final Logger logger = LoggerFactory.getLogger(JenkinsCiServiceImpl.class);
	private final String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();
	@Autowired
	private CredentialMgmtImpl credentialMgmtImpl;
	
	public Response ciValidateJob(CIModel input) {
		// TODO Auto-generated method stub

		Response response = new Response();
		Map<String, String> errorMap = new HashMap<String, String>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			props.load(JenkinsCiServiceImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		if (StringUtils.isBlank(input.getCiName())) {
			response.setStatus("FAILURE");
			logger.info(Constants.CI_NAME + ":" +props.getProperty(Constants.CI_NAME));
			errorMap.put(Constants.CI_NAME, props.getProperty(Constants.CI_NAME));

		}else if (StringUtils.isBlank(input.getCiURL())) {
			response.setStatus("FAILURE");
			logger.info(Constants.CI_LINK + ":" + props.getProperty(Constants.CI_LINK));
			errorMap.put(Constants.CI_LINK, props.getProperty(Constants.CI_LINK));

		} else if (StringUtils.isBlank(input.getArtifactoryUrl())) {

			response.setStatus("FAILURE");
			logger.info(Constants.ARTIFACTORY_URL + ":" + props.getProperty(Constants.ARTIFACTORY_URL));
			errorMap.put(Constants.ARTIFACTORY_URL, props.getProperty(Constants.ARTIFACTORY_URL));
		}

		else if (StringUtils.isBlank(input.getTargetReleaseSynatax())) {

			response.setStatus("FAILURE");
			logger.info(Constants.TARGET_RELEASE_SYNTAX + ":" + props.getProperty(Constants.TARGET_RELEASE_SYNTAX));
			errorMap.put(Constants.TARGET_RELEASE_SYNTAX, props.getProperty(Constants.TARGET_RELEASE_SYNTAX));

		}

		else if (StringUtils.isBlank(input.getTargetSnapshotSyntax())) {

			response.setStatus("FAILURE");
			logger.info(Constants.TARGET_SNAPSHOT_SYNTAX + ":" + props.getProperty(Constants.TARGET_SNAPSHOT_SYNTAX));
			errorMap.put(Constants.TARGET_SNAPSHOT_SYNTAX, props.getProperty(Constants.TARGET_SNAPSHOT_SYNTAX));

		} else if (!StringUtils.isBlank(input.getAuthType())) {

			if (StringUtils.isBlank(input.getCredentialID())) {

				response.setStatus("FAILURE");
				logger.info(Constants.CI_CREDENTIAL_ID + ":" + props.getProperty(Constants.CI_CREDENTIAL_ID));
				errorMap.put(Constants.CI_CREDENTIAL_ID, props.getProperty(Constants.CI_CREDENTIAL_ID));

			}
		}
		if(response.getStatus().equals("SUCCESS"))
		{
			response = urlValidator(input);
		}
		
		response.setError(errorMap);
		return response;
	}


	public Response ciValdatePermisson(Jenkins input) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response ciValidateFolder(String folderName) {
		// TODO Auto-generated method stub

		Response response = new Response();
		Map<String, String> errorMap = new HashMap<String, String>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			props.load(GitValidatorImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		if (StringUtils.isBlank(folderName)) {

			response.setStatus("FAILURE");
			logger.info(Constants.CI_CREDENTIAL_ID + Constants.CI_CREDENTIAL_ID);
			errorMap.put(Constants.CI_CREDENTIAL_ID, props.getProperty(Constants.CI_CREDENTIAL_ID));
		}
		response.setError(errorMap);
		return response;
	}

	public Response urlValidator(CIModel input) {
		// TODO Auto-generated method stub
		Date date = new Date();
		 JenkinsServer jenkins = null;
		 Response response = new Response();
		 Map<String, String> errorMap = new HashMap<String, String>();
		 response.setStatus("SUCCESS");
		 response.setTimestamp(date.getTime());
		 CredentialResponse credentialResponse = credentialMgmtImpl.fetchOneCredentials(input.getCredentialID());
		try {
			jenkins = new JenkinsServer(new URI(input.getCiURL()), credentialResponse.getResult().get(0).getUserName(), credentialResponse.getResult().get(0).getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
			errorMap.put("Error : ", e.getMessage());
		}
		if(response.getStatus().equals("SUCCESS"))
		{
			if(!jenkins.isRunning())
			{
				response.setStatus("FAILURE");
				response.setErrorMessage("Kindly Check The Jenkins URL or User Name and Password");
				errorMap.put("Error : ", "Kindly Check The Error Message");
			}
		}
		response.setError(errorMap);
		return response;
	}

	
}
