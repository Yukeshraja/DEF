/*package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.altimetrik.def.controller.DefController;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.ServerObj;
import com.altimetrik.def.service.CIServer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.GuavaTemplateCache;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.offbytwo.jenkins.JenkinsServer;


@Component
public class ConcourseLoaderImpl implements CIServer {
	
	Logger logger = LoggerFactory.getLogger(ConcourseLoaderImpl.class);


	private Handlebars handlebars;

	*//**
	 * After the bean initialization, load the templates from the class path
	 *//*
	@PostConstruct
	public void loadHandlebarTemplates() {
		TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".xml");
		final Cache<TemplateSource, Template> templateCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(1000).build();
		setHandlebars(new Handlebars(loader).with((new GuavaTemplateCache(templateCache))));
	}

	public Handlebars getHandlebars() {
		return handlebars;
	}

	public void setHandlebars(Handlebars handlebars) {
		this.handlebars = handlebars;
	}

	*//**
	 * Get the compiled template
	 * @param templateName - name of the template to compile
	 * @return the compiled template
	 * @throws IOException
	 *//*
	public Template getTemplate(String templateName) throws IOException {
		Template template = this.getHandlebars().compile(templateName);
		return template;
	}

	*//**
	 * The Context for the template
	 * @param model
	 * @return
	 *//*
	public Context getContext(JsonNode model) {

		Context context = Context
				.newBuilder(model)
				.resolver(JsonNodeValueResolver.INSTANCE,JavaBeanValueResolver.INSTANCE,FieldValueResolver.INSTANCE,
						MapValueResolver.INSTANCE,
						MethodValueResolver.INSTANCE)
				.build();
		return context;
	}
	
	public ServerObj jenkinsConnection(String jenkinsUrl, String jenkinsUserName, String jenkinsPassword) throws URISyntaxException{
		
		JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsUrl),jenkinsUserName, jenkinsPassword);
		return new ServerObj(jenkins);
	}
	
	public void createJob(ServerObj jenkinsServer, String fileBody, String jobName) throws URISyntaxException, IOException{
		
		//JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsUrl),jenkinsUserName, jenkinsPassword);
		JenkinsServer jenkins = (JenkinsServer) jenkinsServer.getServer();
		jenkins.createJob(jobName, fileBody, true);
	}

	public void updateJob(ServerObj jenkinsServer, String finalTemplate, String jobName) throws URISyntaxException, IOException {
		//JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsURL),jenkinsUsername, jenkinsPassword);
		JenkinsServer jenkins = (JenkinsServer) jenkinsServer.getServer();
		jenkins.updateJob(jobName, finalTemplate);
		
	}

	@Override
	public void createJob(JobParams jobParams) {
		
		JSONObject jsonObject = new JSONObject(jobParams.getDefInputValue());
		
		String value = jsonObject.toString(4);
		JsonNode jsonNode = null;
		try {
			jsonNode = new ObjectMapper().readValue(value, JsonNode.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Template template = null;
		try {
			template = getTemplate("template");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String finalTemplate = null;
		try {
			finalTemplate = template.apply(getContext(jsonNode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ServerObj jenkinsServer = null;
			jenkinsServer = jenkinsConnection(
					jobParams.getDefInputValue().getJenkins().getJenkinsURL(),
					jobParams.getDefInputValue().getJenkins().getJenkinsUsername(), 
					jobParams.getDefInputValue().getJenkins().getJenkinsPassword());
			createJob(jenkinsServer, finalTemplate, jobParams.getDefInputValue().getName());
		} catch (Exception exception) {
			logger.info("Exception : " + exception);
			logger.info("Message : " + exception.getMessage());
			logger.info("Localozed Message : " + exception.getLocalizedMessage());
			logger.info("Cause : " + exception.getCause());
		}
		
		jobParams.setReturnValue(finalTemplate);
	}

	@Override
	public void updateJob(JobParams jobParams) {
		JSONObject jsonObject = new JSONObject(jobParams.getDefInputValue());
		
		String value = jsonObject.toString(4);
		String finalTemplate = null;
		try {
			JsonNode jsonNode = new ObjectMapper().readValue(value, JsonNode.class);
			Template template = getTemplate("template");
			finalTemplate = template.apply(getContext(jsonNode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ServerObj jenkinsServer = null;
			jenkinsServer = jenkinsConnection(
								jobParams.getDefInputValue().getJenkins().getJenkinsURL(),
								jobParams.getDefInputValue().getJenkins().getJenkinsUsername(), 
								jobParams.getDefInputValue().getJenkins().getJenkinsPassword());
			updateJob(jenkinsServer, finalTemplate, jobParams.getDefInputValue().getName());
			jenkinsServer.enableJob(jobName);
		} catch (Exception exception) {
			logger.info("Exception : " + exception);
			logger.info("Message : " + exception.getMessage());
			logger.info("Localozed Message : " + exception.getLocalizedMessage());
			logger.info("Cause : " + exception.getCause());
		}
		
		jobParams.setReturnValue(finalTemplate);
		
	}
}
*/