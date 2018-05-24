package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.BuildFaces;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.BuildFacesValidator;

@Component
public class BuildFacesValidatorImpl implements BuildFacesValidator {

	private static final Logger logger = LoggerFactory.getLogger(DefServiceImpl.class);
	private final static String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	private Map<String, String> errorMap;
	Properties props = new Properties();

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response buildCycleValidation(BuildFaces input) {
		// TODO Auto-generated method stub
		Response response = new Response();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			props.load(BuildFacesValidatorImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			response.setStatus("FAILURE");
			response.setErrorMessage(e.getMessage());
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		if (input.getClean().equals(false) && input.getCompile().equals(false) && input.getBuild().equals(false)
				&& input.getValidate().equals(false) && input.get_package().equals(false)
				&& input.getInstall().equals(false) && input.getTest().equals(false)
				&& input.getDeploy().equals(false)) {
			response.setStatus("FAILURE");
			logger.info(Constants.BUILD_COMMAND_CHECK + Constants.BUILD_COMMAND_CHECK);
			errorMap.put(Constants.BUILD_COMMAND_CHECK, props.getProperty(Constants.BUILD_COMMAND_CHECK));
		}
		response.setError(errorMap);
		return response;
	}

	@Override
	public Response dockerizationValidation(BuildFaces input) {
		// TODO Auto-generated method stub
		Response response = new Response();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		if (input.getDocker().equals(true) && input.getDockerBuild().equals(true)) {
			response.setStatus("FAILURE");
			logger.info(Constants.DOCKER_AND_DOCKERFILE_CHECK + Constants.DOCKER_AND_DOCKERFILE_CHECK);
			errorMap.put(Constants.DOCKER_AND_DOCKERFILE_CHECK,
					props.getProperty(Constants.DOCKER_AND_DOCKERFILE_CHECK));
		}
		if (input.getDocker().equals(true)) {

			if (StringUtils.isBlank(input.getDockerImageName())) {
				response.setStatus("FAILURE");
				logger.info(Constants.DOCKER_IMAGE_NAME + Constants.DOCKER_IMAGE_NAME);
				errorMap.put(Constants.DOCKER_IMAGE_NAME, props.getProperty(Constants.DOCKER_IMAGE_NAME));

			}

			if (StringUtils.isBlank(input.getDockerFilePath())) {
				response.setStatus("FAILURE");
				logger.info(Constants.DOCKER_IMAGE_NAME + Constants.DOCKER_IMAGE_NAME);
				errorMap.put(Constants.DOCKER_IMAGE_NAME, props.getProperty(Constants.DOCKER_IMAGE_NAME));

			}
		}
		response.setError(errorMap);
		return response;
	}

}
