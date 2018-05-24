package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.Response;

@Component
public class DefValidatorImpl {

	private Logger logger = LoggerFactory.getLogger(DefValidatorImpl.class);
	private final static String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	private Map<String, String> errorMap = new HashMap();
	Properties props = new Properties();

	@Autowired
	private GitValidatorImpl gitValidatorImpl;
	@Autowired
	private BuildFacesValidatorImpl buildFacesValidatorImpl;
	@Autowired
	private BuildPackValidatorImpl buildPackValidatorImpl;
	@Autowired
	private JenkinsValidatorImpl jenkinsValidatorImpl;
	@Autowired
	private DockerValidatorImpl dockerValidatorImpl;

	/**
	 * @return the gitValidatorImpl
	 */
	public GitValidatorImpl getGitValidatorImpl() {
		return gitValidatorImpl;
	}

	/**
	 * @param gitValidatorImpl
	 *            the gitValidatorImpl to set
	 */
	public void setGitValidatorImpl(GitValidatorImpl gitValidatorImpl) {
		this.gitValidatorImpl = gitValidatorImpl;
	}

	/**
	 * @return the buildFacesValidatorImpl
	 */
	public BuildFacesValidatorImpl getBuildFacesValidatorImpl() {
		return buildFacesValidatorImpl;
	}

	/**
	 * @param buildFacesValidatorImpl
	 *            the buildFacesValidatorImpl to set
	 */
	public void setBuildFacesValidatorImpl(BuildFacesValidatorImpl buildFacesValidatorImpl) {
		this.buildFacesValidatorImpl = buildFacesValidatorImpl;
	}

	/**
	 * @return the buildPackValidatorImpl
	 */
	public BuildPackValidatorImpl getBuildPackValidatorImpl() {
		return buildPackValidatorImpl;
	}

	/**
	 * @param buildPackValidatorImpl
	 *            the buildPackValidatorImpl to set
	 */
	public void setBuildPackValidatorImpl(BuildPackValidatorImpl buildPackValidatorImpl) {
		this.buildPackValidatorImpl = buildPackValidatorImpl;
	}

	/**
	 * @return the jenkinsValidatorImpl
	 */
	public JenkinsValidatorImpl getJenkinsValidatorImpl() {
		return jenkinsValidatorImpl;
	}

	/**
	 * @param jenkinsValidatorImpl
	 *            the jenkinsValidatorImpl to set
	 */
	public void setJenkinsValidatorImpl(JenkinsValidatorImpl jenkinsValidatorImpl) {
		this.jenkinsValidatorImpl = jenkinsValidatorImpl;
	}

	/**
	 * @return the dockerValidatorImpl
	 */
	public DockerValidatorImpl getDockerValidatorImpl() {
		return dockerValidatorImpl;
	}

	/**
	 * @param dockerValidatorImpl
	 *            the dockerValidatorImpl to set
	 */
	public void setDockerValidatorImpl(DockerValidatorImpl dockerValidatorImpl) {
		this.dockerValidatorImpl = dockerValidatorImpl;
	}

	public Response Validation(Def input) {
		Response chkResponse = new Response();

		try {
			props.load(BuildFacesValidatorImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			chkResponse.setStatus("FAILURE");
			chkResponse.setErrorMessage(e.getMessage());
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		chkResponse = buildPackValidatorImpl.dataVaidation(input.getBuildpacks());
		if (chkResponse.getStatus().equals("SUCCESS")) {
			chkResponse = buildFacesValidatorImpl.buildCycleValidation(input.getBuildFaces());
			
			if (chkResponse.getStatus().equals("SUCCESS")) {
				if (errorMap == null) { errorMap = new HashMap();}
				if (StringUtils.isBlank(input.getScm().getName())) {
					chkResponse.setStatus("FAILURE");
					logger.info(Constants.SCM_TYPE + Constants.SCM_TYPE);
					errorMap.put(Constants.SCM_TYPE, props.getProperty(Constants.SCM_TYPE));
				}
				
				if (StringUtils.isBlank(input.getScm().getRepoBranch())) {
					chkResponse.setStatus("FAILURE");
					String property = props.getProperty(Constants.SCM_REPO_BRANCH);
					logger.info(Constants.SCM_REPO_BRANCH +":"+  property);
					errorMap.put(Constants.SCM_REPO_BRANCH, property);
				}
				if (StringUtils.isBlank(input.getConnectTo())) {
					chkResponse.setStatus("FAILURE");
					String property = props.getProperty(Constants.CI_NAME);
					logger.info(Constants.CI_NAME +":"+  property);
					errorMap.put(Constants.CI_NAME, property);
				}
				
				if (StringUtils.isBlank(input.getCd().getDeployTo())) {
					chkResponse.setStatus("FAILURE");
					String property = props.getProperty(Constants.CD_DEPLOY_TO);
					logger.info(Constants.CD_DEPLOY_TO +":"+ property);
					errorMap.put(Constants.CD_DEPLOY_TO, property);
				}
				
				if (StringUtils.isBlank(input.getCd().getCdName())) {
					chkResponse.setStatus("FAILURE");
					String property = props.getProperty(Constants.CD_NAME);
					logger.info(Constants.CD_NAME +":"+ property);
					errorMap.put(Constants.CD_NAME, property);
				}
			}
			/*
			 * if (chkResponse.getStatus().equals("SUCCESS") &&
			 * input.getBuildFaces().getDocker().equals(true)) { chkResponse =
			 * buildFacesValidatorImpl.dockerizationValidation(input.
			 * getBuildFaces()); } if
			 * (chkResponse.getStatus().equals("SUCCESS")) { chkResponse =
			 * gitValidatorImpl.chkDetailsValidation(input.getScm()); if
			 * (chkResponse.getStatus().equals("SUCCESS") &&
			 * input.getConnectTo().equals("jenkins")) { chkResponse =
			 * jenkinsValidatorImpl.ciValidateJob(input.getJenkins());
			 * 
			 * if(chkResponse.getStatus().equals("SUCCESS")) { chkResponse =
			 * jenkinsValidatorImpl.ciValidateFolder(input.getJenkins().
			 * getJenkinsFolder()); }
			 * 
			 * if (chkResponse.getStatus().equals("SUCCESS") &&
			 * input.getCd().getDeployTo().equals("docker")) {
			 * 
			 * chkResponse = dockerValidatorImpl.dataValidation(input.getCd());
			 * } }
			 * 
			 * }
			 */
		}
		logger.info("ErrorMap:"+ errorMap);
		chkResponse.setError(errorMap);
		return chkResponse;
	}
}
