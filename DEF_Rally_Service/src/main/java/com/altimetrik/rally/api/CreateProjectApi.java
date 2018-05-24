package com.altimetrik.rally.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.rally.exception.AltiRallyApplicationException;
import com.altimetrik.rally.service.RallyCreateProjectService;
import com.rallydev.rest.response.CreateResponse;
import com.altimetrik.rally.dto.ErrorResponse;

@RestController
@RequestMapping(value = "/rally")

/* Loading yml file from class path */

public class CreateProjectApi {

	final static Logger logger = Logger.getLogger(CreateProjectApi.class);

	@Autowired
	RallyCreateProjectService createProjectService;

	@RequestMapping(value = "/createProject", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public CreateResponse createProject(HttpEntity<String> httpEntity) throws AltiRallyApplicationException {

		logger.info("Inside createProject() API...");
		return createProjectService.createProject(httpEntity);

	}

	@ExceptionHandler(AltiRallyApplicationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		logger.info("Exception Occured..");
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setMessage(ex.getMessage());
		ResponseEntity<ErrorResponse> errorResponse = new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
		return errorResponse;
	}

}
