package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
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
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.repository.JenkinsRepository;
import com.altimetrik.def.service.DefService;

@Service
public class DefServiceImpl implements DefService {

	private static final Logger logger = LoggerFactory.getLogger(DefServiceImpl.class);
	private final static String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();
	private Map<String, String> errorMap;
	private Date date = null;

	@Autowired
	private JenkinsRepository defMongoRepository;

	/**
	 * @return the defMongoRepository
	 */
	public JenkinsRepository getDefMongoRepository() {
		return defMongoRepository;
	}

	/**
	 * @param defMongoRepository the defMongoRepository to set
	 */
	public void setDefMongoRepository(JenkinsRepository defMongoRepository) {
		this.defMongoRepository = defMongoRepository;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public JobParams insertDetails(JobParams configurationDetails) {
		//Response response = new Response();
		date = new Date();
		logger.info("Inside DefServiceImpl's insertDetails() method and Job Name is : "+configurationDetails.getDefInputValue().getName());
		if(configurationDetails.getReturnValue().getErrorMessage().equals("Bad Request")){
	
			configurationDetails.getReturnValue().setErrorMessage(" May Be The Job Name In The Jenkins Are Same !!! ");
		}else{
			try{
				defMongoRepository.save(configurationDetails.getDefInputValue());
			}catch(Exception exception){
				configurationDetails.getReturnValue().setErrorMessage(exception.getMessage());
				configurationDetails.getReturnValue().setStatus("FAILURE");
				configurationDetails.getReturnValue().setTimestamp(date.getTime());
				logger.info("Exception : " + exception);
				logger.info("Message : " + exception.getMessage());
				logger.info("Localozed Message : " + exception.getLocalizedMessage());
				logger.info("Cause : " + exception.getCause());
			}
		}
		logger.info("Ending of DefServiceImpl's insertDetails() function : ");
		return configurationDetails;
	}

	public JobParams updateDetails(JobParams input) {
		// TODO Auto-generated method stub
		logger.info("Inside DefServiceImpl's updateDetails() method and Job Name is : "+input.getDefInputValue().getName());
		date = new Date();
		if(input.getReturnValue().getErrorMessage().equals("Bad Request")){
			
			input.getReturnValue().setErrorMessage(" May Be The Job Name In The Jenkins Are Same !!! ");
		}else{
			try{
				defMongoRepository.save(input.getDefInputValue());
			}catch(Exception exception){
				input.getReturnValue().setErrorMessage(exception.getMessage());
				input.getReturnValue().setStatus("FAILURE");
				input.getReturnValue().setTimestamp(date.getTime());
				logger.info("Exception : " + exception);
				logger.info("Message : " + exception.getMessage());
				logger.info("Localozed Message : " + exception.getLocalizedMessage());
				logger.info("Cause : " + exception.getCause());
			}
		}
		logger.info("Ending of DefServiceImpl's updateDetails() function : ");
		return input;
	}

	public JobParams getOneJobDetails(String jobName) {
		// TODO Auto-generated method stub
		logger.info("Inside DefServiceImpl's getOneJobDetails() method and Job Name is : "+jobName);
		JobParams jobParams = new JobParams();
		Response response = new Response();
		date = new Date();
		response.setTimestamp(date.getTime());
		List<Def> OneJobDetails = new ArrayList<Def>();
		try{
			Def eachJobDetails = defMongoRepository.findOne(jobName);
			OneJobDetails.add(eachJobDetails);
			response.setResult(OneJobDetails);
			response.setErrorMessage("NO ERROR");
			logger.info("NO ERROR");
			response.setStatus("SUCCESS");
		}catch(Exception exception){
			//response.setResult(OneJobDetails);
			response.setErrorMessage(exception.getMessage());
			response.setStatus("FAILURE");
			logger.info("Exception : " + exception);
			logger.info("Message : " + exception.getMessage());
			logger.info("Localozed Message : " + exception.getLocalizedMessage());
			logger.info("Cause : " + exception.getCause());
		}
		jobParams.setReturnValue(response);
		logger.info("End of getOneJobDetails() method of DefServiceImpl.java : ");
		return jobParams;

	}

	public JobParams getAllJOb() {
		// TODO Auto-generated method stub
		logger.info("Inside DefServiceImpl's getAllJOb() method ");
		JobParams jobParams = new JobParams();
		Response response = new Response();
		date = new Date();
		List<Def> allJobs = null;
		try {
			allJobs = defMongoRepository.findAll();
			response.setStatus("SUCCESS");
			response.setErrorMessage("NO ERRORS");
			response.setResult(allJobs);
			response.setTimestamp(date.getTime());
		} catch (Exception exception) {
			logger.info("Exception : " + exception);
			logger.info("Message : " + exception.getMessage());
			logger.info("Localozed Message : " + exception.getLocalizedMessage());
			logger.info("Cause : " + exception.getCause());
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		}
		jobParams.setReturnValue(response);
		logger.info("End of DefServiceImpl's getAllJob() method : "); 
		return jobParams;
	}

	public void deleteJobDetails(String jobName) {
		// TODO Auto-generated method stub
		defMongoRepository.delete(jobName);
	}

	public Response nullChkJenkinsJob(Def input) {
		logger.info("Inside DefServiceImpl's nullChkJenkinsJob() method : ");
		Response response = new Response();
		try {
			props.load(DefController.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		logger.info("input : " + input);
		errorMap = new HashMap<String, String>();
		List<Def> result = new ArrayList<Def>();
		result.add(input);
		response.setStatus("SUCCESS");
		Date date = new Date();
		response.setTimestamp(date.getTime());
		if (StringUtils.isBlank(input.getName())) {
			response.setStatus("FAILURE");
			logger.info(Constants.PROJECT_NAME + Constants.PROJECT_NAME);
			errorMap.put(Constants.PROJECT_NAME, props.getProperty(Constants.PROJECT_NAME));
		}
		/*if (StringUtils.isBlank(input.getStatus())) {
			response.setStatus("FAILURE");
			errorMap.put(Constants.JOB_STATUS, props.getProperty(Constants.JOB_STATUS));
		}*/
		if (StringUtils.isBlank(input.getBuildpacks().getName())) {
			response.setStatus("FAILURE");
			logger.info(Constants.PROJECT_TYPE + Constants.PROJECT_TYPE);
			errorMap.put(Constants.PROJECT_TYPE, props.getProperty(Constants.PROJECT_TYPE));
		}

		if (StringUtils.isBlank(input.getBuildpacks().getBuildtool())) {
			response.setStatus("FAILURE");
			logger.info(Constants.BUILD_TOOL + Constants.BUILD_TOOL);
			errorMap.put(Constants.BUILD_TOOL, props.getProperty(Constants.BUILD_TOOL));
		}

		if (StringUtils.isBlank(input.getBuildpacks().getPomXml())) {
			response.setStatus("FAILURE");
			logger.info(Constants.BUILD_FILE_PATH + Constants.BUILD_FILE_PATH);
			errorMap.put(Constants.BUILD_FILE_PATH, props.getProperty(Constants.BUILD_FILE_PATH));
		}

		if (StringUtils.isBlank(input.getScm().getName())) {
			response.setStatus("FAILURE");
			logger.info(Constants.SCM_TYPE + Constants.SCM_TYPE);
			errorMap.put(Constants.SCM_TYPE, props.getProperty(Constants.SCM_TYPE));
		}

		if (StringUtils.isBlank(input.getScm().getLink())) {
			response.setStatus("FAILURE");
			logger.info(Constants.SCM_LINK + Constants.SCM_LINK);
			errorMap.put(Constants.SCM_LINK, props.getProperty(Constants.SCM_LINK));
		}

		if (StringUtils.isBlank(input.getScm().getRepoBranch())) {
			response.setStatus("FAILURE");
			logger.info(Constants.SCM_REPO_BRANCH + Constants.SCM_REPO_BRANCH);
			errorMap.put(Constants.SCM_REPO_BRANCH, props.getProperty(Constants.SCM_REPO_BRANCH));
		}
		if (!StringUtils.isBlank(input.getScm().getAuthType())) {

			if (StringUtils.isBlank(input.getScm().getCredentialID())) {
				response.setStatus("FAILURE");
				logger.info(Constants.SCM_CREDENTIAL_ID + Constants.SCM_CREDENTIAL_ID);
				errorMap.put(Constants.SCM_CREDENTIAL_ID, props.getProperty(Constants.SCM_CREDENTIAL_ID));
			}
		}
		if (input.getBuildFaces().getClean().equals(false) && input.getBuildFaces().getCompile().equals(false)
				&& input.getBuildFaces().getBuild().equals(false) && input.getBuildFaces().getValidate().equals(false)
				&& input.getBuildFaces().get_package().equals(false) && input.getBuildFaces().getInstall().equals(false)
				&& input.getBuildFaces().getTest().equals(false) && input.getBuildFaces().getDeploy().equals(false)) {
			response.setStatus("FAILURE");
			logger.info(Constants.BUILD_COMMAND_CHECK + Constants.BUILD_COMMAND_CHECK);
			errorMap.put(Constants.BUILD_COMMAND_CHECK, props.getProperty(Constants.BUILD_COMMAND_CHECK));
		}

		if (input.getBuildFaces().getDocker().equals(true) && input.getBuildFaces().getDockerBuild().equals(true)) {
			response.setStatus("FAILURE");
			logger.info(Constants.DOCKER_AND_DOCKERFILE_CHECK + Constants.DOCKER_AND_DOCKERFILE_CHECK);
			errorMap.put(Constants.DOCKER_AND_DOCKERFILE_CHECK,
					props.getProperty(Constants.DOCKER_AND_DOCKERFILE_CHECK));
		}
		if (input.getBuildFaces().getDocker().equals(true)) {

			if (StringUtils.isBlank(input.getBuildFaces().getDockerImageName())) {
				response.setStatus("FAILURE");
				logger.info(Constants.DOCKER_IMAGE_NAME + Constants.DOCKER_IMAGE_NAME);
				errorMap.put(Constants.DOCKER_IMAGE_NAME, props.getProperty(Constants.DOCKER_IMAGE_NAME));

			}

			if (StringUtils.isBlank(input.getBuildFaces().getDockerFilePath())) {
				response.setStatus("FAILURE");
				logger.info(Constants.DOCKER_IMAGE_NAME + Constants.DOCKER_IMAGE_NAME);
				errorMap.put(Constants.DOCKER_IMAGE_NAME, props.getProperty(Constants.DOCKER_IMAGE_NAME));

			}

			/*
			 * if (StringUtils.isBlank(input.getBuildFaces().getDockerServer()))
			 * {
			 * 
			 * response.setStatus("FAILURE");
			 * errorMap.put(Constants.DOCKER_SERVER,
			 * props.getProperty(Constants.DOCKER_SERVER));
			 * 
			 * }
			 * 
			 * if (input.getBuildFaces().getDockerPort() == 0) {
			 * response.setStatus("FAILURE");
			 * errorMap.put(Constants.DOCKER_PORT,
			 * props.getProperty(Constants.DOCKER_PORT));
			 * 
			 * }
			 */
			/*
			 * if (input.getBuildFaces().getAppPortNumber() == 0) {
			 * 
			 * response.setStatus("FAILURE");
			 * errorMap.put(Constants.APPLICATION_PORT_NUMBER,
			 * props.getProperty(Constants.APPLICATION_PORT_NUMBER));
			 * 
			 * }
			 * 
			 * if (input.getBuildFaces().getDockerPortNumber() == 0) {
			 * response.setStatus("FAILURE");
			 * errorMap.put(Constants.EXPOSED_DOCKER_PORT_NUMBER,
			 * props.getProperty(Constants.EXPOSED_DOCKER_PORT_NUMBER));
			 * 
			 * }
			 */
		}

		if (StringUtils.isBlank(input.getConnectTo())) {
			response.setStatus("FAILURE");
			logger.info(Constants.CI_TOOL_NAME + Constants.CI_TOOL_NAME);
			errorMap.put(Constants.CI_TOOL_NAME, props.getProperty(Constants.CI_TOOL_NAME));

		} else {

			if (StringUtils.isBlank(input.getJenkins().getJenkinsURL())) {
				response.setStatus("FAILURE");
				logger.info(Constants.CI_LINK + Constants.CI_LINK);
				errorMap.put(Constants.CI_LINK, props.getProperty(Constants.CI_LINK));

			}

			/*
			 * else if
			 * (StringUtils.isBlank(input.getJenkins().getJenkinsUsername())) {
			 * 
			 * response.setStatus("FAILURE");
			 * errorMap.put(Constants.CI_USER_NAME,
			 * props.getProperty(Constants.CI_USER_NAME)); }
			 * 
			 * else if
			 * (StringUtils.isBlank(input.getJenkins().getJenkinsPassword())) {
			 * 
			 * response.setStatus("FAILURE");
			 * errorMap.put(Constants.CI_PASSWORD,
			 * props.getProperty(Constants.CI_PASSWORD)); }
			 */

			else if (StringUtils.isBlank(input.getJenkins().getArtifactoryUrl())) {

				response.setStatus("FAILURE");
				logger.info(Constants.ARTIFACTORY_URL + Constants.ARTIFACTORY_URL);
				errorMap.put(Constants.ARTIFACTORY_URL, props.getProperty(Constants.ARTIFACTORY_URL));
			}

			else if (StringUtils.isBlank(input.getJenkins().getTargetReleaseSynatax())) {

				response.setStatus("FAILURE");
				logger.info(Constants.TARGET_RELEASE_SYNTAX + Constants.TARGET_RELEASE_SYNTAX);
				errorMap.put(Constants.TARGET_RELEASE_SYNTAX, props.getProperty(Constants.TARGET_RELEASE_SYNTAX));

			}

			else if (StringUtils.isBlank(input.getJenkins().getTargetSnapshotSyntax())) {

				response.setStatus("FAILURE");
				logger.info(Constants.TARGET_SNAPSHOT_SYNTAX + Constants.TARGET_SNAPSHOT_SYNTAX);
				errorMap.put(Constants.TARGET_SNAPSHOT_SYNTAX, props.getProperty(Constants.TARGET_SNAPSHOT_SYNTAX));

			} else if (!StringUtils.isBlank(input.getJenkins().getAuthType())) {

				if (StringUtils.isBlank(input.getJenkins().getCredentialID())) {

					response.setStatus("FAILURE");
					logger.info(Constants.CI_CREDENTIAL_ID + Constants.CI_CREDENTIAL_ID);
					errorMap.put(Constants.CI_CREDENTIAL_ID, props.getProperty(Constants.CI_CREDENTIAL_ID));

				}
			}
		}
		if (!StringUtils.isBlank(input.getCd().getDeployTo())) {

			if (input.getCd().getDeployTo().equals("docker")) {

			/*	if (StringUtils.isBlank(input.getcD().getDeployTo())) {
					
					response.setStatus("FAILURE");
					errorMap.put(Constants.CI_CREDENTIAL_ID, props.getProperty(Constants.CI_CREDENTIAL_ID));

				}*/
				if (StringUtils.isBlank(input.getCd().getAppTargetPortNumber())) {
					
					response.setStatus("FAILURE");
					logger.info(Constants.APPLICATION_PORT + Constants.APPLICATION_PORT);
					errorMap.put(Constants.APPLICATION_PORT, props.getProperty(Constants.APPLICATION_PORT));

				}
				if (StringUtils.isBlank(input.getCd().getDockerExposedPortNumber())) {
					
					response.setStatus("FAILURE");
					logger.info(Constants.DOCKER_EXPOSED_PORT + Constants.DOCKER_EXPOSED_PORT);
					errorMap.put(Constants.DOCKER_EXPOSED_PORT, props.getProperty(Constants.DOCKER_EXPOSED_PORT));

				}
				if (StringUtils.isBlank(input.getCd().getDockerImgRegistryPort())) {
					
					response.setStatus("FAILURE");
					logger.info(Constants.IMG_REG_SERVER_PORT + Constants.IMG_REG_SERVER_PORT);
					errorMap.put(Constants.IMG_REG_SERVER_PORT, props.getProperty(Constants.IMG_REG_SERVER_PORT));
				
				}
				if (StringUtils.isBlank(input.getCd().getDockerImgRegistryServer())) {
					
					response.setStatus("FAILURE");
					logger.info(Constants.IMG_REG_SERVER_DETAILS + Constants.IMG_REG_SERVER_DETAILS);
					errorMap.put(Constants.IMG_REG_SERVER_DETAILS, props.getProperty(Constants.IMG_REG_SERVER_DETAILS));
				
				}
				if (StringUtils.isBlank(input.getCd().getDeploymentServer())) {
					
					response.setStatus("FAILURE");
					logger.info(Constants.DEP_SERVER_DETAILS + Constants.DEP_SERVER_DETAILS);
					errorMap.put(Constants.DEP_SERVER_DETAILS, props.getProperty(Constants.DEP_SERVER_DETAILS));
				
				}
				if (!StringUtils.isBlank(input.getCd().getAuthType())) {
					
					if (StringUtils.isBlank(input.getCd().getCredentialID())) {
						response.setStatus("FAILURE");
						logger.info(Constants.CD_CREDENTIAL_ID + Constants.CD_CREDENTIAL_ID);
						errorMap.put(Constants.CD_CREDENTIAL_ID, props.getProperty(Constants.CD_CREDENTIAL_ID));
					}
				}
			}
		}
		logger.info("errorMap : " + errorMap);
		response.setError(errorMap);
		response.setResult(result);
		logger.info("Ending of DefServiceImpl's nullChkJenkinsJob() method : ");
		return response;

	}
}
