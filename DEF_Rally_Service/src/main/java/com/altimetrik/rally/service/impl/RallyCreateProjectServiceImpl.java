package com.altimetrik.rally.service.impl;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.rally.exception.AltiRallyApplicationException;
import com.altimetrik.rally.service.RallyCreateProjectService;
import com.google.gson.JsonParser;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.response.CreateResponse;

@Service
@PropertySource("classpath:rally.yml")
public class RallyCreateProjectServiceImpl implements RallyCreateProjectService {
	
	final static Logger logger = Logger.getLogger(RallyCreateProjectServiceImpl.class);

	/* Reading values from yml file */
	@Value("${properties.rallyapikey}")
	private String apikey;
	@Value("${properties.rallylink}")
	private String rallylink;

	@Override
	public CreateResponse createProject(HttpEntity<String> httpEntity) throws AltiRallyApplicationException{
		CreateResponse createResponse = null;
		try {

			// Create and configure a new instance of RallyRestApi
			logger.info("Communicating to URL 'https://rally1.rallydev.com'...");
			RallyRestApi restApi = new RallyRestApi(new URI(rallylink), apikey);
			logger.info("Successfully communicated to URL 'https://rally1.rallydev.com'...");
			
			JsonParser parser = new JsonParser();
			CreateRequest request = new CreateRequest("Project", parser.parse(
					httpEntity.getBody()).getAsJsonObject());
			createResponse = restApi.create(request);
			logger.info("Got response for create project RallyRestApi...");
			restApi.close();
			logger.info("Terminated Rally connection...");

		} catch (Exception e) {
			logger.error("Exception Occured...");
			throw new AltiRallyApplicationException("Internal Server Error");
		}
		return createResponse;
	}

}
