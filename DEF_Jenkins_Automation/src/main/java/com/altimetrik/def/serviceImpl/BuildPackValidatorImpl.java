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

import com.altimetrik.def.model.Buildpacks;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.BuildPacksValidator;

@Component
public class BuildPackValidatorImpl implements BuildPacksValidator {

	private final Logger logger = LoggerFactory.getLogger(DefServiceImpl.class);
	private final String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response dataVaidation(Buildpacks input) {
		// TODO Auto-generated method stub
		Response response = new Response();
		Map<String, String> errorMap = new HashMap<String, String>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		int pomXmlIndex = input.getPomXml().indexOf("pom.xml");
		try {
			props.load(BuildPackValidatorImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}
		if (StringUtils.isBlank(input.getName())) {
			response.setStatus("FAILURE");
			response.setErrorMessage("Error");
			String property = props.getProperty(Constants.PROJECT_TYPE);
			logger.info(Constants.PROJECT_TYPE + property);
			errorMap.put(Constants.PROJECT_TYPE, property);
		}

		if (StringUtils.isBlank(input.getBuildtool())) {
			response.setStatus("FAILURE");
			String property = props.getProperty(Constants.BUILD_TOOL);
			logger.info(Constants.BUILD_TOOL + property);
			errorMap.put(Constants.BUILD_TOOL, property);
		}

		if (StringUtils.isBlank(input.getPomXml())) {
			response.setStatus("FAILURE");
			response.setErrorMessage("Error");
			String property = props.getProperty(Constants.BUILD_FILE_PATH);
			logger.info(Constants.BUILD_FILE_PATH + property);
			errorMap.put(Constants.BUILD_FILE_PATH, property);
		}

		if (pomXmlIndex == -1) {
			response.setStatus("FAILURE");
			response.setErrorMessage("Error");
			String property = props.getProperty(Constants.BUILD_FILE_POM_EXISTANCE);
			logger.info(Constants.BUILD_FILE_POM_EXISTANCE + property);
			errorMap.put(Constants.BUILD_FILE_POM_EXISTANCE, property);
		}
		response.setError(errorMap);
		return response;
	}

}
