package com.altimetrik.def.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import com.altimetrik.def.model.BuildDetails;
import com.altimetrik.def.model.JobParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;


public interface CIServer {

	/**
	 * After the bean initialization, load the templates from the class path
	 */
	@PostConstruct
	public void loadHandlebarTemplates();

	public Handlebars getHandlebars();

	public void setHandlebars(Handlebars handlebars);

	/**
	 * Get the compiled template
	 * @param templateName - name of the template to compile
	 * @return the compiled template
	 * @throws IOException
	 */
	public Template getTemplate(String templateName) throws IOException ;

	/**
	 * The Context for the template
	 * @param model
	 * @return
	 */
	public Context getContext(JsonNode model) ;
	public JobParams createJob(JobParams jobParams);
	public JobParams updateJob(JobParams jobParams);
	public void startJob(JobParams jobParams) throws Exception;
	public void stopJob(JobParams oneJobDetails) throws URISyntaxException;
	public BuildDetails getJobDetails(JobParams oneJobDetails) throws URISyntaxException;
	
}
