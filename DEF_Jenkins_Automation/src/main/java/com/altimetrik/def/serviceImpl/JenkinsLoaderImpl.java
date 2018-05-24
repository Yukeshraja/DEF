package com.altimetrik.def.serviceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.altimetrik.def.controller.CredentialMgmtController;
import com.altimetrik.def.model.BuildDetails;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.ServerObj;
import com.altimetrik.def.service.CIServer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.JenkinsTriggerHelper;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.FolderJob;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.Queue;
import com.offbytwo.jenkins.model.QueueItem;
import com.offbytwo.jenkins.model.QueueReference;

@Component("jenkins")
public class JenkinsLoaderImpl implements CIServer {

	Logger logger = LoggerFactory.getLogger(JenkinsLoaderImpl.class);

	@Autowired
	private CredentialMgmtImpl credentialMgmtImpl;
	private Handlebars handlebars;
	private Date date = null;
	private Map<String, String> errorMap = null;

	/**
	 * @return the credentialMgmtImpl
	 */
	public CredentialMgmtImpl getCredentialMgmtImpl() {
		return credentialMgmtImpl;
	}

	/**
	 * @param credentialMgmtImpl
	 *            the credentialMgmtImpl to set
	 */
	public void setCredentialMgmtImpl(CredentialMgmtImpl credentialMgmtImpl) {
		this.credentialMgmtImpl = credentialMgmtImpl;
	}

	/**
	 * After the bean initialization, load the templates from the class path
	 */

	@PostConstruct
	public void loadHandlebarTemplates() {
		TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".xml");
		final Cache<TemplateSource, Template> templateCache = CacheBuilder.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(1000).build();
		setHandlebars(new Handlebars(loader).with((new GuavaTemplateCache(templateCache))));

	}

	public Handlebars getHandlebars() {
		return handlebars;
	}

	public void setHandlebars(Handlebars handlebars) {
		this.handlebars = handlebars;
	}

	/**
	 * Get the compiled template
	 * 
	 * @param templateName
	 *            - name of the template to compile
	 * @return the compiled template
	 * @throws IOException
	 */
	public Template getTemplate(String templateName) throws IOException {
		logger.info("Template Name :" + templateName);
		loadHandlebarTemplates();
		Template template = this.getHandlebars().compile(templateName);
		logger.info("Template Description :" + template);
		return template;
	}

	/**
	 * The Context for the template
	 * 
	 * @param model
	 * @return
	 */
	public Context getContext(JsonNode model) {

		Context context = Context.newBuilder(model)
				.resolver(JsonNodeValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE, FieldValueResolver.INSTANCE,
						MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
				.build();
		return context;
	}

	public ServerObj jenkinsConnection(String jenkinsUrl, String jenkinsUserName, String jenkinsPassword)
			throws URISyntaxException {
		Response response = new Response();
		JenkinsServer jenkins = null;
		date = new Date();
		response.setTimestamp(date.getTime());
		try {
			jenkins = new JenkinsServer(new URI(jenkinsUrl), jenkinsUserName, jenkinsPassword);
			response.setStatus("SUCCESS");
			response.setErrorMessage("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		}
		return new ServerObj(jenkins);
	}

	public void createJob(String jenkinsUrl, String jenkinsFolder, ServerObj jenkinsServer, String fileBody,
			JobParams jobParams) throws URISyntaxException, IOException {

		logger.info(" Inside Create Job : " + MessageFormat.format("Jenkins URL {0} , Folder {1} , JenkinsServer {2}, FileBody {3}, JobParams {4}",
							new Object[] {jenkinsUrl, jenkinsFolder, jenkinsServer, fileBody, jobParams} ));
		// JenkinsServer jenkins = new JenkinsServer(new
		// URI(jenkinsUrl),jenkinsUserName, jenkinsPassword);
		JenkinsServer jenkins = (JenkinsServer) jenkinsServer.getServer();
		// jenkins.createFolder("/job/DEF/foldertest");
		// jenkins.createFolder("/job/DEF/foldertest", true);
		// String jenkinsURL1 = "https://pipeline.altimetrik.com/job/DEF/";
		
		
		
/*		
		try {
			jenkins.createJob(
					new FolderJob(
							jobParams.getDefInputValue().getJenkins().getJenkinsFolder(), 
							jenkinsUrl),
							jobParams.getDefInputValue().getName(), 
							fileBody, true);
			
			logger.info("Jenkins Job creation Successful: ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Jenkins Job creation Error : " + e.getMessage());
		}
*/
		
		jenkins.createJob( createFolder(jobParams), jobParams.getDefInputValue().getName(), fileBody, true);
		
		logger.info(" Done Create Job : " + MessageFormat.format("Jenkins getName {0} ", new Object[] {jobParams.getDefInputValue().getName()} ));
		
/*		String jenkinsUrlFirstPart = "";
		int indexOfSplitChar = jenkinsUrl.indexOf('?');
		if (indexOfSplitChar != -1) {
			logger.info("Index of The Character ? : " + indexOfSplitChar);
			jenkinsUrlFirstPart = jenkinsUrl.substring(0, indexOfSplitChar);
			logger.info("jenkinsUrlFirstPart : " + jenkinsUrlFirstPart);
		} else {
			jenkinsUrlFirstPart = jenkinsUrl;
		}

		if (StringUtils.isBlank(jobParams.getDefInputValue().getJenkins().getJenkinsFolder())) {
			jenkins.createJob(jobParams.getDefInputValue().getName(), fileBody, true);
		} else {
			jenkinsUrlFirstPart += jenkinsFolder;
			logger.info("jenkinsUrlFirstPart after concatinating : " + jenkinsUrlFirstPart);
			jenkins.createJob(
					new FolderJob(jobParams.getDefInputValue().getJenkins().getJenkinsFolder(), jenkinsUrlFirstPart),
					jobParams.getDefInputValue().getName(), fileBody, true);
		}
*/
		//
		// logger.info("Jenkins Jobs List : " + jenkins.getJobs(new
		// FolderJob("DEF", jenkinsUrl), jobParams));

	}

	public JobParams updateJob(String jenkinsUrl, ServerObj jenkinsServer, String finalTemplate, String jobName,
			JobParams jobParams) throws URISyntaxException, IOException {
		// JenkinsServer jenkins = new JenkinsServer(new
		// URI(jenkinsURL),jenkinsUsername, jenkinsPassword);
		// JobParams jobParams = new JobParams();
		Response response = new Response();
		Def def = new Def();
		date = new Date();
		response.setTimestamp(date.getTime());
		try {
			JenkinsServer jenkins = (JenkinsServer) jenkinsServer.getServer();

			/*			String jenkinsUrlFirstPart = "";
						int indexOfSplitChar = jenkinsUrl.indexOf('?');
						if (indexOfSplitChar != -1) {
							logger.info("Index of The Character ? : " + indexOfSplitChar);
							jenkinsUrlFirstPart = jenkinsUrl.substring(0, indexOfSplitChar);
							logger.info("jenkinsUrlFirstPart : " + jenkinsUrlFirstPart);
						} else {
							jenkinsUrlFirstPart = jenkinsUrl;
						}
			*/
			/*
			 * int indexOfSplitChar = jenkinsUrl.indexOf('?');
			 * logger.info("Index of The Character ? : " + indexOfSplitChar);
			 * String jenkinsUrlFirstPart = ""; jenkinsUrlFirstPart =
			 * jenkinsUrl.substring(0, indexOfSplitChar);
			 * logger.info("jenkinsUrlFirstPart : " + jenkinsUrlFirstPart);
			 * jenkinsUrlFirstPart += "/job/DEF/";
			 * logger.info("jenkinsUrlFirstPart after concatinating : " +
			 * jenkinsUrlFirstPart);
			 */
				/*			if (StringUtils.isBlank(jobParams.getDefInputValue().getJenkins().getJenkinsFolder())) {
								logger.info("jenkinsUrlFirstPart Update : "+jenkinsUrlFirstPart);
								jenkins.updateJob(jobParams.getDefInputValue().getName(), finalTemplate, true);
							} else {
								jenkinsUrlFirstPart += jobParams.getDefInputValue().getJenkins().getJenkinsFolder();
								jenkins.updateJob(new FolderJob(jobParams.getDefInputValue().getJenkins().getJenkinsFolder(),
										jenkinsUrlFirstPart), jobParams.getDefInputValue().getName(), finalTemplate, true);
							}
				*/
			
			jenkins.updateJob( createFolder(jobParams), jobParams.getDefInputValue().getName(), finalTemplate, true);
			
			// jenkins.updateJob(jobName, finalTemplate);
			response.setStatus("SUCCESS");
			response.setErrorMessage("NO ERROR");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		}
		jobParams.setReturnValue(response);
		return jobParams;

	}

	@Override
	public JobParams createJob(JobParams jobParams) {
		date = new Date();
		errorMap = new HashMap<String, String>();
		JSONObject jsonObject = new JSONObject(jobParams.getDefInputValue());
		Response response = new Response();
		String value = jsonObject.toString(4);
		JsonNode jsonNode = null;
		response.setTimestamp(date.getTime());
		response.setStatus("SUCCESS");
		try {
			jsonNode = new ObjectMapper().readValue(value, JsonNode.class);
		} catch (JsonParseException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			errorMap.put("Error : ", "Please Check The Error Message");
		} catch (JsonMappingException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			errorMap.put("Error : ", "Please Check The Error Message");
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			errorMap.put("Error : ", "Please Check The Error Message");
		}

		Template template = null;
		try {
			template = getTemplate("templateaws");
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			errorMap.put("Error : ", "Please Check The Error Message");
		}
		String finalTemplate = null;
		try {
			finalTemplate = template.apply(getContext(jsonNode));
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			errorMap.put("Error : ", "Please Check The Error Message");
			exception.printStackTrace();
		}

		try {
			ServerObj jenkinsServer = null;
			CredentialResponse credentialResponse = credentialMgmtImpl
					.fetchOneCredentials(jobParams.getDefInputValue().getJenkins().getCredentialID());
			jenkinsServer = jenkinsConnection(jobParams.getDefInputValue().getJenkins().getJenkinsURL(),
					credentialResponse.getResult().get(0).getUserName(),
					credentialResponse.getResult().get(0).getPassword());
			createJob(jobParams.getDefInputValue().getJenkins().getJenkinsURL(),
					jobParams.getDefInputValue().getJenkins().getJenkinsFolder(), jenkinsServer, finalTemplate,
					jobParams);
		} catch (Exception exception) {
			logger.info("Exception : " + exception);
			logger.info("Message : " + exception.getMessage());
			logger.info("Localozed Message : " + exception.getLocalizedMessage());
			logger.info("Cause : " + exception.getCause());

			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			errorMap.put("Error : ", "Please Check The Error Message");
			response.setError(errorMap);
		}

		jobParams.setReturnValue(response);
		return jobParams;
	}

	@Override
	public JobParams updateJob(JobParams jobParams) {
		JSONObject jsonObject = new JSONObject(jobParams.getDefInputValue());

		String value = jsonObject.toString(4);
		String finalTemplate = null;
		JobParams jobParamsResponse = new JobParams();
		Response response = new Response();
		date = new Date();
		response.setTimestamp(date.getTime());
		try {
			JsonNode jsonNode = new ObjectMapper().readValue(value, JsonNode.class);
			Template template = getTemplate("templateaws");
			finalTemplate = template.apply(getContext(jsonNode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ServerObj jenkinsServer = null;
			CredentialResponse credentialResponse = credentialMgmtImpl
					.fetchOneCredentials(jobParams.getDefInputValue().getJenkins().getCredentialID());
			jenkinsServer = jenkinsConnection(jobParams.getDefInputValue().getJenkins().getJenkinsURL(),
					credentialResponse.getResult().get(0).getUserName(),
					credentialResponse.getResult().get(0).getPassword());
			jobParamsResponse = updateJob(jobParams.getDefInputValue().getJenkins().getJenkinsURL(), jenkinsServer,
					finalTemplate, jobParams.getDefInputValue().getName(), jobParams);
		} catch (Exception exception) {
			logger.info("Exception : " + exception);
			logger.info("Message : " + exception.getMessage());
			logger.info("Localozed Message : " + exception.getLocalizedMessage());
			logger.info("Cause : " + exception.getCause());
			response.setErrorMessage(exception.getMessage());
			response.setStatus("FAILURE");
			jobParamsResponse.setReturnValue(response);
		}
		jobParamsResponse.setDefInputValue(jobParams.getDefInputValue());
		return jobParamsResponse;

	}

	@Override
	public void startJob(JobParams jobParams) throws Exception {
		ServerObj jenkinsServer = null;
		Def defInputValue = jobParams.getDefInputValue();
		Jenkins jenkins = defInputValue.getJenkins();
		String jenkinsURL = jenkins.getJenkinsURL();

		if (jenkins.getCredentialID() != null) {

			CredentialResponse credentialResponse = credentialMgmtImpl.fetchOneCredentials(jenkins.getCredentialID());
			try {
				jenkinsServer = jenkinsConnection(jenkinsURL, credentialResponse.getResult().get(0).getUserName(),
						credentialResponse.getResult().get(0).getPassword());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				logger.info(" Error Message: " + e.getMessage());
			}
		} else {
			logger.info(" Jenkins info not found / not proper Config found");
			throw new Exception("  Jenkins info not found / not proper Config found");
		}

		JenkinsServer server = (JenkinsServer) jenkinsServer.getServer();
		JenkinsTriggerHelper jenkinsTriggerHelper = new JenkinsTriggerHelper(server);
		BuildWithDetails triggerJobAndWaitUntilFinished = null;
		
		String consoleOutputText = null;
		try {

				if (defInputValue.getJenkins().getJenkinsFolder() == null) {
						triggerJobAndWaitUntilFinished = jenkinsTriggerHelper
								.triggerJobAndWaitUntilFinished(defInputValue.getName(), true);			
					
				
				} else {
					
				    JobWithDetails job = server.getJob(	createFolder(jobParams), defInputValue.getName());
				    QueueReference queueRef = job.build(true);
		
				    triggerJobAndWaitUntilFinished = triggerJobAndWaitUntilFinished(server, jobParams, queueRef);
				}
				
				
				consoleOutputText = triggerJobAndWaitUntilFinished.getConsoleOutputText();
				logger.info(" consoleOutputText " + consoleOutputText);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			logger.info(" Error Message: " + e.getMessage());
		}


	}
	
    private BuildWithDetails triggerJobAndWaitUntilFinished(JenkinsServer server, JobParams jobParams, QueueReference queueRef)
            throws IOException, InterruptedException {
    	String jobName = jobParams.getDefInputValue().getName();
    	JobWithDetails job = getJob(server, jobParams, jobName);
        QueueItem queueItem = server.getQueueItem(queueRef);
        while (!queueItem.isCancelled() && job.isInQueue()) {
            // TODO: May be we should make this configurable?
            Thread.sleep(200);
            job = getJob(server, jobParams, jobName);
            queueItem = server.getQueueItem(queueRef);
        }

        if (queueItem.isCancelled()) {
            // TODO: Check if this is ok?
            // We will get the details of the last build. NOT of the cancelled
            // build, cause there is no information about that available cause
            // it does not exist.
            BuildWithDetails result = new BuildWithDetails(job.getLastBuild().details());
            // TODO: Should we add more information here?
            result.setResult(BuildResult.CANCELLED);
            return result;
        }

        job = getJob(server, jobParams, jobName);
        Build lastBuild = job.getLastBuild();

        boolean isBuilding = lastBuild.details().isBuilding();
        while (isBuilding) {
            // TODO: May be we should make this configurable?
            Thread.sleep(200);
            isBuilding = lastBuild.details().isBuilding();
        }

        return lastBuild.details();
    }

	private JobWithDetails getJob(JenkinsServer server, JobParams jobParams, String jobName) throws IOException {
		JobWithDetails job;
		if (jobParams.getDefInputValue().getJenkins().getJenkinsFolder() == null) {
			job = server.getJob( jobName);
    	} else {    		
    		job = server.getJob(	createFolder(jobParams), jobName);
    	}
		return job;
	}
	
	private FolderJob createFolder(JobParams jobParams) {
		String jenkinsUrlFirstPart = "";
		String jenkinsUrl = jobParams.getDefInputValue().getJenkins().getJenkinsURL();
		int indexOfSplitChar = jenkinsUrl.indexOf('?');
		if (indexOfSplitChar != -1) {
			logger.info("Index of The Character ? : " + indexOfSplitChar);
			jenkinsUrlFirstPart = jenkinsUrl.substring(0, indexOfSplitChar);
			logger.info("jenkinsUrlFirstPart : " + jenkinsUrlFirstPart);
		} else {
			jenkinsUrlFirstPart = jenkinsUrl;
		}

		String jenkinsFolder = jobParams.getDefInputValue().getJenkins().getJenkinsFolder();
		return new FolderJob(jenkinsFolder, jenkinsUrlFirstPart+jenkinsFolder);
	}
	
	private String getJenkinsURL(JobParams jobParams) {
		String jenkinsUrlFirstPart = "";
		String jenkinsUrl = jobParams.getDefInputValue().getJenkins().getJenkinsURL();
		int indexOfSplitChar = jenkinsUrl.indexOf('?');
		if (indexOfSplitChar != -1) {
			logger.info("Index of The Character ? : " + indexOfSplitChar);
			jenkinsUrlFirstPart = jenkinsUrl.substring(0, indexOfSplitChar);
			logger.info("jenkinsUrlFirstPart : " + jenkinsUrlFirstPart);
		} else {
			jenkinsUrlFirstPart = jenkinsUrl;
		}

		return jenkinsUrlFirstPart;
	}	

	@Override
	public void stopJob(JobParams jobParams) throws URISyntaxException {
		logger.info(" Entering stopJob  ");
		Def defInputValue = jobParams.getDefInputValue();
		Jenkins jenkins = defInputValue.getJenkins();
		String jenkinsURL = jenkins.getJenkinsURL(); // "http://rangan:rangan123@192.168.91.61:8080/";//;
		URI uri = new URI(jenkinsURL);
		CredentialResponse credentialResponse = credentialMgmtImpl.fetchOneCredentials(jenkins.getCredentialID());

		JenkinsHttpClient jenkinsHttpClient = new JenkinsHttpClient(uri,
				credentialResponse.getResult().get(0).getUserName(),
				credentialResponse.getResult().get(0).getPassword());
		
		ServerObj serverObj = jenkinsConnection(jenkinsURL, credentialResponse.getResult().get(0).getUserName(),
				credentialResponse.getResult().get(0).getPassword());

		JenkinsServer jenkinsServer = (JenkinsServer) serverObj.getServer();
		JobWithDetails job = null;
		try {
//			job = jenkinsServer.getJob( createFolder(jobParams), defInputValue.getName()); //defInputValue.getName());
			job = getJob(jenkinsServer, jobParams, defInputValue.getName());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Build lastBuild = job.getLastBuild();
		int currnetBuildNumber = lastBuild.getNumber();
		String path = null;
		try {
			path = createFolder(jobParams).getUrl() + "/job/" + defInputValue.getName() + "/" + currnetBuildNumber + "/stop";
			logger.info(" stopJob Requset URI path:  " + path);
			jenkinsHttpClient.post(path, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.info(" stopJob with buildNumber error probably build was not started ");
			logger.info(" stopJob Error Message : " + e.getMessage());
		}

		logger.info(" stopJob  with buildNumber is ended ");

		try {
			path = getJenkinsURL(jobParams) + "/queue/api/json";
			String stringQueue = jenkinsHttpClient.get(path);
			logger.info(" stopJob Requset URI path:  " + path);
			Queue queue = getQueueItems(stringQueue, jobParams);
			if (queue == null) {
				try {
					Thread.sleep(2000);
					queue = getQueueItems(stringQueue, jobParams);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (queue != null) {
				List<QueueItem> items = queue.getItems();
				if (items != null) {
					for (QueueItem objValue : items) {
						logger.info(" stopJob objValue.getUrl()): " + objValue.getTask().getUrl());
						logger.info(" stopJob objValue.getId()): " + objValue.getId());
						int indexOf = objValue.getTask().getUrl().indexOf("/" + defInputValue.getName() + "/");
						logger.info(" stopJob objValue.geturl() indexOf ): " + indexOf);
						if (indexOf > 1) {
							jenkinsHttpClient.post(jenkinsURL + "queue/cancelItem?id=" + objValue.getId(), true);
						}
					}
				}				
			}

			logger.info(" Exiting stopJob  " + stringQueue);


		} catch (IOException e2) {
			e2.printStackTrace();
		}

		logger.info(" Stop is completed ");
	}
	
	public Queue getQueueItems(String stringQueue, JobParams jobParams) {
			
			ObjectMapper mapper = new ObjectMapper();
			// map json to Queue
			Queue queue = null;
			String string = null;
			try {
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				queue = mapper.readValue(stringQueue, Queue.class);
				string = queue != null ? 						
						((queue.getItems() != null && queue.getItems().size() > 0) ? 
								queue.getItems().get(0).getUrl() : null ) : null;
				logger.info(" stopJob queue: " + string);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		return string != null ? queue : null;
	}

	@Override
	public BuildDetails getJobDetails(JobParams jobParams) throws URISyntaxException {
		Def defInputValue = jobParams.getDefInputValue();
		Jenkins jenkins = defInputValue.getJenkins();
		String jenkinsURL = jenkins.getJenkinsURL();
		CredentialResponse credentialResponse = credentialMgmtImpl.fetchOneCredentials(jenkins.getCredentialID());

		ServerObj serverObj = jenkinsConnection(jenkinsURL, credentialResponse.getResult().get(0).getUserName(),
				credentialResponse.getResult().get(0).getPassword());

		JenkinsServer jenkinsServer = (JenkinsServer) serverObj.getServer();

		JobWithDetails job = null;
		try {
//			job = jenkinsServer.getJob(defInputValue.getName());
			job = getJob(jenkinsServer, jobParams, defInputValue.getName());
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.info(" Error Message: " + e1.getMessage());
		}
		Build lastBuild = job.getLastBuild();

		BuildWithDetails details = null;

		try {
			details = lastBuild.details();
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.info(" Error Message: " + e1.getMessage());
		}

		BuildDetails consoleOutputText = new BuildDetails();
		BeanUtils.copyProperties(details, consoleOutputText);
		return consoleOutputText;
	}

}
