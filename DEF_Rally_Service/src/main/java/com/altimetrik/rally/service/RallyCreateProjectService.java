package com.altimetrik.rally.service;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.altimetrik.rally.exception.AltiRallyApplicationException;
import com.rallydev.rest.response.CreateResponse;


public interface RallyCreateProjectService {

	public CreateResponse createProject(HttpEntity<String> httpEntity) throws AltiRallyApplicationException;
	
}
