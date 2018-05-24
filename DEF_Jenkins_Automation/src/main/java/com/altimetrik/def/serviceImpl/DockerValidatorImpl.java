package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.altimetrik.def.model.CD;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.CdValidator;

@Component
public class DockerValidatorImpl implements CdValidator {

	private final Logger logger = LoggerFactory.getLogger(DockerValidatorImpl.class);
	private final String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response dataValidation(CD input) {
		// TODO Auto-generated method stub
		Response response = new Response();
		Map<String, String> errorMap = new HashMap<String, String>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			props.load(DockerValidatorImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		if (!StringUtils.isBlank(input.getDeployTo())) {

			if (input.getDeployTo().equals("docker")) {

				/*
				 * if (StringUtils.isBlank(input.getcD().getDeployTo())) {
				 * 
				 * response.setStatus("FAILURE");
				 * errorMap.put(Constants.CI_CREDENTIAL_ID,
				 * props.getProperty(Constants.CI_CREDENTIAL_ID));
				 * 
				 * }
				 */
				if (StringUtils.isBlank(input.getAppTargetPortNumber())) {

					response.setStatus("FAILURE");
					logger.info(Constants.APPLICATION_PORT + Constants.APPLICATION_PORT);
					errorMap.put(Constants.APPLICATION_PORT, props.getProperty(Constants.APPLICATION_PORT));

				}
				if (StringUtils.isBlank(input.getDockerExposedPortNumber())) {

					response.setStatus("FAILURE");
					logger.info(Constants.DOCKER_EXPOSED_PORT + Constants.DOCKER_EXPOSED_PORT);
					errorMap.put(Constants.DOCKER_EXPOSED_PORT, props.getProperty(Constants.DOCKER_EXPOSED_PORT));

				}
				if (StringUtils.isBlank(input.getDockerImgRegistryPort())) {

					response.setStatus("FAILURE");
					logger.info(Constants.IMG_REG_SERVER_PORT + Constants.IMG_REG_SERVER_PORT);
					errorMap.put(Constants.IMG_REG_SERVER_PORT, props.getProperty(Constants.IMG_REG_SERVER_PORT));

				}
				if (StringUtils.isBlank(input.getDockerImgRegistryServer())) {

					response.setStatus("FAILURE");
					logger.info(Constants.IMG_REG_SERVER_DETAILS + Constants.IMG_REG_SERVER_DETAILS);
					errorMap.put(Constants.IMG_REG_SERVER_DETAILS, props.getProperty(Constants.IMG_REG_SERVER_DETAILS));

				}
			}
		}
		response.setError(errorMap);
		return response;
	}
}