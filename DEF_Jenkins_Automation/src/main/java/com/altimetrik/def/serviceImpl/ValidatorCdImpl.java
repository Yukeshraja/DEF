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

import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.DefUrlValidator;
import com.altimetrik.def.service.ValidatorCD;
@Component
public class ValidatorCdImpl extends DefUrlValidator implements ValidatorCD {

	private final Logger logger = LoggerFactory.getLogger(DefServiceImpl.class);
	private final String ERROR_PROPERTIES_FILE_NAME = "/error.properties";
	Properties props = new Properties();

	@Override
	public CDResponse validateCD(CDModel cdModel) {
		// TODO Auto-generated method stub

		CDResponse response = new CDResponse();
		Response urlValidate = new Response();
		DefUrlValidator defUrlValidator = new DefUrlValidator() {};
		Map<String, String> errorMap = new HashMap<String, String>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			props.load(ValidatorCdImpl.class.getResourceAsStream(ERROR_PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			logger.info("Not able to read the properties file, exiting..");
			System.exit(-1);
		}

		if (StringUtils.isBlank(cdModel.getCdName())) {

			response.setStatus("FAILURE");
			logger.info(Constants.CD_NAME + props.getProperty(Constants.CD_NAME));
			errorMap.put(Constants.CD_NAME, props.getProperty(Constants.CD_NAME));
		}

		if (StringUtils.isBlank(cdModel.getDeployTo())) {

			response.setStatus("FAILURE");
			logger.info(Constants.CD_DEPLOY_TO + props.getProperty(Constants.CD_DEPLOY_TO));
			errorMap.put(Constants.CD_DEPLOY_TO, props.getProperty(Constants.CD_DEPLOY_TO));
		}
		if (cdModel.getDeployTo().equals("docker")) {

			if (StringUtils.isBlank(cdModel.getDockerImgRegistryPort())) {

				response.setStatus("FAILURE");
				logger.info(Constants.IMG_REG_SERVER_PORT + props.getProperty(Constants.IMG_REG_SERVER_PORT));
				errorMap.put(Constants.IMG_REG_SERVER_PORT, props.getProperty(Constants.IMG_REG_SERVER_PORT));

			}
			if (StringUtils.isBlank(cdModel.getDockerImgRegistryServer())) {

				response.setStatus("FAILURE");
				logger.info(Constants.IMG_REG_SERVER_DETAILS + props.getProperty(Constants.IMG_REG_SERVER_DETAILS));
				errorMap.put(Constants.IMG_REG_SERVER_DETAILS, props.getProperty(Constants.IMG_REG_SERVER_DETAILS));

			}
			//urlValidate = defUrlValidator.urlValidation(cdModel.getDockerImgRegistryServer());
			/*if(urlValidate.getStatus().equals("FAILURE"))
			{
				response.setStatus("FAILURE");
				response.setErrorMessage("Please Enter Proper Image Registery URL");
				errorMap.put("Error:", urlValidate.getError().get("Error:"));
			}*/
		}
		if (StringUtils.isBlank(cdModel.getDeploymentServer())) {

			response.setStatus("FAILURE");
			logger.info(Constants.DEP_SERVER_DETAILS + props.getProperty(Constants.DEP_SERVER_DETAILS));
			errorMap.put(Constants.DEP_SERVER_DETAILS, props.getProperty(Constants.DEP_SERVER_DETAILS));

		}
		//urlValidate = defUrlValidator.urlValidation(cdModel.getDeploymentServer());
		/*if(urlValidate.getStatus().equals("FAILURE"))
		{
			response.setStatus("FAILURE");
			response.setErrorMessage("Please Enter Proper Deployment Server URL");
			errorMap.put("Error:", urlValidate.getError().get("Error:"));
		}*/
		if (!StringUtils.isBlank(cdModel.getAuthType())) {

			if (StringUtils.isBlank(cdModel.getCredentialID())) {
				response.setStatus("FAILURE");
				logger.info(Constants.CD_CREDENTIAL_ID + props.getProperty(Constants.CD_CREDENTIAL_ID));
				errorMap.put(Constants.CD_CREDENTIAL_ID, props.getProperty(Constants.CD_CREDENTIAL_ID));
			}
		}
		response.setError(errorMap);
		return response;
	}
	

}
