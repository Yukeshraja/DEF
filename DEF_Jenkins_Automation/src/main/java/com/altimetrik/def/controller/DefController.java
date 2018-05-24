package com.altimetrik.def.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.def.model.CD;
import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Constants;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.model.ScmResponse;
import com.altimetrik.def.service.CDService;
import com.altimetrik.def.service.CiService;
import com.altimetrik.def.service.DefLoader;
import com.altimetrik.def.service.DefService;
import com.altimetrik.def.service.ScmService;
import com.altimetrik.def.serviceImpl.CiServerManageJob;
import com.altimetrik.def.serviceImpl.DefValidatorImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class DefController {

	private Logger logger = LoggerFactory.getLogger(DefController.class);

	@Autowired
	private DefLoader defLoader;

	@Autowired
	private DefService defService;

	@Autowired
	DefValidatorImpl defValidatorImpl;

	@Autowired
	private CiServerManageJob ciServerManageJob;

	@Autowired
	private ScmService scmService;

	@Autowired
	private CiService ciService;

	@Autowired
	private CDService cDService;

	/**
	 * @return the defLoader
	 */
	public DefLoader getDefLoader() {
		return defLoader;
	}

	/**
	 * @param defLoader
	 *            the defLoader to set
	 */
	public void setDefLoader(DefLoader defLoader) {
		this.defLoader = defLoader;
	}

	/**
	 * @return the defService
	 */
	public DefService getDefService() {
		return defService;
	}

	/**
	 * @param defService
	 *            the defService to set
	 */
	public void setDefService(DefService defService) {
		this.defService = defService;
	}

	/**
	 * @return the defValidatorImpl
	 */
	public DefValidatorImpl getDefValidatorImpl() {
		return defValidatorImpl;
	}

	/**
	 * @param defValidatorImpl
	 *            the defValidatorImpl to set
	 */
	public void setDefValidatorImpl(DefValidatorImpl defValidatorImpl) {
		this.defValidatorImpl = defValidatorImpl;
	}

	/**
	 * @return the scmService
	 */
	public ScmService getScmService() {
		return scmService;
	}

	/**
	 * @param scmService
	 *            the scmService to set
	 */
	public void setScmService(ScmService scmService) {
		this.scmService = scmService;
	}

	/**
	 * @return the ciService
	 */
	public CiService getCiService() {
		return ciService;
	}

	/**
	 * @param ciService
	 *            the ciService to set
	 */
	public void setCiService(CiService ciService) {
		this.ciService = ciService;
	}

	/**
	 * @return the cDService
	 */
	public CDService getcDService() {
		return cDService;
	}

	/**
	 * @param cDService
	 *            the cDService to set
	 */
	public void setcDService(CDService cDService) {
		this.cDService = cDService;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param input
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */

	@RequestMapping(value = "/createJob", method = RequestMethod.POST)
	public Response createJob(HttpServletRequest request, HttpServletResponse response, @RequestBody Def input)
			throws JsonParseException, JsonMappingException, IOException, ParseException, URISyntaxException {
		logger.info("Inside DefController's Create Job Method, Job Name :  " + input.getName());
		// System.out.println(input);
		// Response chkResponse = new Response();
		// DefValidatorImpl defValidatorImpl = new DefValidatorImpl();
		Response chkResponse = defValidatorImpl.Validation(input);
		if (chkResponse.getStatus().equals("SUCCESS")) {
			CiModelResponse ciResponse = new CiModelResponse();
			ScmResponse scmResponse = scmService.getOneScm(input.getScm().getScmId());
			//input.setScm(scmResponse.getResult().get(0));
			if(scmResponse.getResult().get(0).getLink() != null)
				input.getScm().setLink(scmResponse.getResult().get(0).getLink());
			if(scmResponse.getResult().get(0).getScmId() != null)
				input.getScm().setScmId(scmResponse.getResult().get(0).getScmId());
			if(scmResponse.getResult().get(0).getAuthType() != null)
			{
				if(scmResponse.getResult().get(0).getCredentialID() != null)
				{
					input.getScm().setAuthType(scmResponse.getResult().get(0).getAuthType());
					input.getScm().setCredentialID(scmResponse.getResult().get(0).getCredentialID());
				}
			}
			ciResponse = ciService.getOneCi(input.getConnectTo());
			if (ciResponse.getResult().get(0).getCiType().equals("jenkins")) {
				if (ciResponse.getResult().get(0).getCiName() != null)
					//jenkins.setJenkinsName(input.getConnectTo());
					input.getJenkins().setJenkinsName(input.getConnectTo());
				if (ciResponse.getResult().get(0).getCiFolder() != null)
					input.getJenkins().setJenkinsFolder(ciResponse.getResult().get(0).getCiFolder());
				// jenkins.setJenkinsFolder(ciResponse.getResult().get(0).getCiFolder());
				if (ciResponse.getResult().get(0).getArtifactoryUrl() != null)
					input.getJenkins().setArtifactoryUrl(ciResponse.getResult().get(0).getArtifactoryUrl());
				// jenkins.setArtifactoryUrl(ciResponse.getResult().get(0).getArtifactoryUrl());
				if (ciResponse.getResult().get(0).getTargetReleaseSynatax() != null)
					input.getJenkins().setTargetReleaseSynatax(ciResponse.getResult().get(0).getTargetReleaseSynatax());
				// jenkins.setTargetReleaseSynatax(ciResponse.getResult().get(0).getTargetReleaseSynatax());
				if (ciResponse.getResult().get(0).getTargetSnapshotSyntax() != null)
					input.getJenkins().setTargetSnapshotSyntax(ciResponse.getResult().get(0).getTargetSnapshotSyntax());
				// jenkins.setTargetSnapshotSyntax(ciResponse.getResult().get(0).getTargetSnapshotSyntax());
				if (ciResponse.getResult().get(0).getCiURL() != null)
					input.getJenkins().setJenkinsURL(ciResponse.getResult().get(0).getCiURL());
				// jenkins.setJenkinsURL(ciResponse.getResult().get(0).getCiURL());
				/*
				 * if (ciResponse.getResult().get(0).getCiFolder() != null)
				 * jenkins.setJenkinsFolder(ciResponse.getResult().get(0).
				 * getCiFolder());
				 */
				if (ciResponse.getResult().get(0).getAuthType() != null) {
					if (ciResponse.getResult().get(0).getCredentialID() != null) {
						// jenkins.setAuthType(ciResponse.getResult().get(0).getAuthType());
						// jenkins.setCredentialID(ciResponse.getResult().get(0).getCredentialID());
						input.getJenkins().setAuthType(ciResponse.getResult().get(0).getAuthType());
						input.getJenkins().setCredentialID(ciResponse.getResult().get(0).getCredentialID());
					}
				}
				// input.setJenkins(jenkins);
			}
			CDResponse cDResponse = cDService.getOneCd(input.getCd().getCdName());
			if (cDResponse.getStatus().equals("SUCCESS")) {
				// CD cd = new CD();
				
				
				if (cDResponse.getResult() != null 
						&& cDResponse.getResult().size() > 0 
						&& cDResponse.getResult().get(0) != null )
				{
				
					CDModel cdModel = cDResponse.getResult().get(0);
					input.getCd().setDockerExposedPortNumber(cdModel.getDockerExposedPortNumber());
					input.getCd().setAppTargetPortNumber(cdModel.getAppTargetPortNumber());
						
						if (cdModel.getDeploymentServer() != null) {
							// cd.setDeploymentServer(cDResponse.getResult().get(0).getDeploymentServer());
							input.getCd().setDeploymentServer(cdModel.getDeploymentServer());
						}
						if (cdModel.getAuthType() != null) {
							if (cdModel.getCredentialID() != null) {
								// cd.setAuthType(cDResponse.getResult().get(0).getAuthType());
								// cd.setCredentialID(cDResponse.getResult().get(0).getCredentialID());
								input.getCd().setAuthType(cdModel.getAuthType());
								input.getCd().setCredentialID(cdModel.getCredentialID());
							}
						}
						if (cdModel.getDeployTo().equals("docker")) {
							if (cdModel.getDockerImgRegistryPort() != null) {
								input.getCd()
										.setDockerImgRegistryPort(cdModel.getDockerImgRegistryPort());
								// cd.setDockerImgRegistryPort(cDResponse.getResult().get(0).getDockerImgRegistryPort());
							}
							if (cdModel.getDockerImgRegistryServer() != null) {
								input.getCd()
										.setDockerImgRegistryServer(cdModel.getDockerImgRegistryServer());
								// cd.setDockerImgRegistryServer(cDResponse.getResult().get(0).getDockerImgRegistryServer());
							}
						}
				}
				// input.setCd(cd);
			}
			JobParams jobParams = new JobParams();
			jobParams.setDefInputValue(input);
			JobParams responseDetails = defLoader.createJob(jobParams);
			if (responseDetails.getReturnValue().getStatus().equals("SUCCESS")) {
				responseDetails.getDefInputValue().setStatus("SUCCESS");
				responseDetails.getReturnValue().setErrorMessage("No Error");
				// input.setStatus("SUCCESS");
			} else {
				responseDetails.getDefInputValue().setStatus("FAILURE");
				// input.setStatus("FAILURE");
				// defService.insertDetails(responseDetails.getDefInputValue());
			}
			// logger.info("Status :
			// "+responseDetails.getDefInputValue().getStatus());
			chkResponse = defService.insertDetails(responseDetails).getReturnValue();
			logger.info("Error Message : " + chkResponse.getErrorMessage());
			logger.info("Status : " + chkResponse.getStatus());
			logger.info("Timestamp : " + chkResponse.getTimestamp());
			// responseDetails.setReturnValue(insertResponse);
		}
		// return insertResponse.getReturnValue();
		logger.info("End of createJob() method of DefController");
		return chkResponse;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param input
	 * @param jobName
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */

	@RequestMapping(value = "/updateJob/{jobName}", method = RequestMethod.PUT)
	private Response updateJenkinsJob(HttpServletRequest request, HttpServletResponse response, @RequestBody Def input,
			@PathVariable String jobName) throws URISyntaxException, IOException {
		logger.info("Inside Update Job Method, Job Name :  " + input.getName());
		Response chkResponse = defValidatorImpl.Validation(input);
		if (chkResponse.getStatus().equals("SUCCESS")) {
			CiModelResponse ciResponse = new CiModelResponse();
			ScmResponse scmResponse = scmService.getOneScm(input.getScm().getScmId());			
			SCM scm = scmResponse.getResult().get(0);
			scm.setRepoBranch(input.getScm().getRepoBranch());
			input.setScm(scm);
			ciResponse = ciService.getOneCi(input.getConnectTo());
			if (ciResponse.getResult().get(0).getCiType().equals("jenkins")) {
				Jenkins jenkins = new Jenkins();
				if (ciResponse.getResult().get(0).getCiName() != null)
					jenkins.setJenkinsName(input.getConnectTo());
				if (ciResponse.getResult().get(0).getCiFolder() != null)
					input.getJenkins().setJenkinsFolder(ciResponse.getResult().get(0).getCiFolder());
				// jenkins.setJenkinsFolder(ciResponse.getResult().get(0).getCiFolder());
				if (ciResponse.getResult().get(0).getArtifactoryUrl() != null)
					input.getJenkins().setArtifactoryUrl(ciResponse.getResult().get(0).getArtifactoryUrl());
				// jenkins.setArtifactoryUrl(ciResponse.getResult().get(0).getArtifactoryUrl());
				if (ciResponse.getResult().get(0).getTargetReleaseSynatax() != null)
					input.getJenkins().setTargetReleaseSynatax(ciResponse.getResult().get(0).getTargetReleaseSynatax());
				// jenkins.setTargetReleaseSynatax(ciResponse.getResult().get(0).getTargetReleaseSynatax());
				if (ciResponse.getResult().get(0).getTargetSnapshotSyntax() != null)
					input.getJenkins().setTargetSnapshotSyntax(ciResponse.getResult().get(0).getTargetSnapshotSyntax());
				// jenkins.setTargetSnapshotSyntax(ciResponse.getResult().get(0).getTargetSnapshotSyntax());
				if (ciResponse.getResult().get(0).getCiURL() != null)
					input.getJenkins().setJenkinsURL(ciResponse.getResult().get(0).getCiURL());
				// jenkins.setJenkinsURL(ciResponse.getResult().get(0).getCiURL());
				/*
				 * if (ciResponse.getResult().get(0).getCiFolder() != null)
				 * jenkins.setJenkinsFolder(ciResponse.getResult().get(0).
				 * getCiFolder());
				 */
				if (ciResponse.getResult().get(0).getAuthType() != null) {
					if (ciResponse.getResult().get(0).getCredentialID() != null) {
						// jenkins.setAuthType(ciResponse.getResult().get(0).getAuthType());
						// jenkins.setCredentialID(ciResponse.getResult().get(0).getCredentialID());
						input.getJenkins().setAuthType(ciResponse.getResult().get(0).getAuthType());
						input.getJenkins().setCredentialID(ciResponse.getResult().get(0).getCredentialID());
					}
				}
				// input.setJenkins(jenkins);
			}
			CDResponse cDResponse = cDService.getOneCd(input.getCd().getCdName());
			if (cDResponse.getStatus().equals("SUCCESS")) {
//				CD cd = new CD();
				if (cDResponse.getResult() != null 
						&& cDResponse.getResult().size() > 0 
						&& cDResponse.getResult().get(0) != null )
				{
						CDModel cdModel = cDResponse.getResult().get(0);
						input.getCd().setDockerExposedPortNumber(cdModel.getDockerExposedPortNumber());
						input.getCd().setAppTargetPortNumber(cdModel.getAppTargetPortNumber());
						if (cdModel.getDeploymentServer() != null) {
							// cd.setDeploymentServer(cDResponse.getResult().get(0).getDeploymentServer());
							input.getCd().setDeploymentServer(cdModel.getDeploymentServer());
						}
						if (cdModel.getAuthType() != null) {
							if (cdModel.getCredentialID() != null) {
								// cd.setAuthType(cDResponse.getResult().get(0).getAuthType());
								// cd.setCredentialID(cDResponse.getResult().get(0).getCredentialID());
								input.getCd().setAuthType(cdModel.getAuthType());
								input.getCd().setCredentialID(cdModel.getCredentialID());
							}
						}
						if (cdModel.getDeployTo().equals("docker")) {
							if (cdModel.getDockerImgRegistryPort() != null) {
								input.getCd()
										.setDockerImgRegistryPort(cdModel.getDockerImgRegistryPort());
								// cd.setDockerImgRegistryPort(cDResponse.getResult().get(0).getDockerImgRegistryPort());
							}
							if (cdModel.getDockerImgRegistryServer() != null) {
								input.getCd()
										.setDockerImgRegistryServer(cdModel.getDockerImgRegistryServer());
								// cd.setDockerImgRegistryServer(cDResponse.getResult().get(0).getDockerImgRegistryServer());
							}
						}
				}

				JobParams jobParams = new JobParams();
				jobParams.setDefInputValue(input);

				JobParams jobParamsResponse = defLoader.updateJob(jobParams);

				if (jobParamsResponse.getReturnValue().getStatus().equals("SUCCESS")) {
					jobParamsResponse.getDefInputValue().setStatus("SUCCESS");
					jobParamsResponse.getReturnValue().setErrorMessage("No Error");
					// input.setStatus("SUCCESS");
				} else {
					jobParamsResponse.getDefInputValue().setStatus("FAILURE");
					// input.setStatus("FAILURE");
					// defService.insertDetails(responseDetails.getDefInputValue());
				}
				chkResponse = defService.updateDetails(jobParamsResponse).getReturnValue();
				logger.info("Error Message : " + chkResponse.getErrorMessage());
				logger.info("Status : " + chkResponse.getStatus());
				logger.info("Timestamp : " + chkResponse.getTimestamp());
			}
		}
		logger.info("Ending of updateJenkinsJob() Method of of DefController");
		return chkResponse;
	}

	/**
	 * 
	 * @return
	 * @throws URISyntaxException
	 */

	@RequestMapping(value = "/getAllJob", method = RequestMethod.GET)
	public Response getAllJob() throws URISyntaxException {
		logger.info("Inside DefController's getAllJob() Method : ");
		JobParams getAllJobDetails = defService.getAllJOb();
		logger.info("Time Stamp : " + getAllJobDetails.getReturnValue().getTimestamp());
		logger.info("Ending of DefController's getAllJob() Method and Status is : "
				+ getAllJobDetails.getReturnValue().getStatus());
		return getAllJobDetails.getReturnValue();
	}

	/**
	 * 
	 * @param jobName
	 * @return
	 * @throws URISyntaxException
	 */

	@RequestMapping(value = "/getOneJob/{jobName}", method = RequestMethod.GET)
	public Response getOneJobDetails(@PathVariable String jobName) throws URISyntaxException {
		logger.info("Inside DefController's getOneJobDetails() Method and Job Name Is : " + jobName);
		JobParams getOneJobDetails = defService.getOneJobDetails(jobName);
		logger.info("Time Stamp : " + getOneJobDetails.getReturnValue().getTimestamp());
		logger.info("Ending of DefController's getOneJobDetails() Method : ");
		return getOneJobDetails.getReturnValue();

	}

	/**
	 * 
	 * @param jobName
	 * @throws URISyntaxException
	 */

	@RequestMapping(value = "/deleteJob/{jobName}", method = RequestMethod.DELETE)
	private void deleteJobDetails(@PathVariable String jobName) throws URISyntaxException {
		logger.info("Inside Delete Job Method . Job Name : " + jobName);
		defService.deleteJobDetails(jobName);

	}

	@RequestMapping(value = "/startJob/{jobName}", method = RequestMethod.POST)
	private Response startJob(@PathVariable String jobName) throws URISyntaxException {
		logger.info("Inside startJob Job Method . Job Name : " + jobName);

		Response chkResponse = new Response();
		chkResponse.setStatus(Constants.SUCCESS);

		try {
			ciServerManageJob.startJob(jobName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			chkResponse.setStatus(Constants.FAILURE);
			chkResponse.setErrorMessage(e.getMessage());
		}

		logger.info("Inside startJob Job Method . Response : " + chkResponse);
		return chkResponse;
	}

	@RequestMapping(value = "/stopJob/{jobName}", method = RequestMethod.POST)
	private Response stopJob(@PathVariable String jobName) throws URISyntaxException {
		logger.info("Inside startJob Job Method . Job Name : " + jobName);

		Response chkResponse = new Response();
		chkResponse.setStatus(Constants.SUCCESS);

		try {
			ciServerManageJob.stopJob(jobName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			chkResponse.setStatus(Constants.FAILURE);
			chkResponse.setErrorMessage(e.getMessage());
		}

		logger.info("Inside startJob Job Method . Response : " + chkResponse);
		return chkResponse;
	}

	@RequestMapping(value = "/viewlogs/{jobName}", method = RequestMethod.GET)
	private Response viewlogs(@PathVariable String jobName) throws URISyntaxException {
		logger.info("Inside startJob Job Method . Job Name : " + jobName);

		Response chkResponse = new Response();
		chkResponse.setStatus(Constants.SUCCESS);

		try {
			chkResponse.setReturnValue(ciServerManageJob.getJobStatus(jobName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			chkResponse.setStatus(Constants.FAILURE);
			chkResponse.setErrorMessage(e.getMessage());
		}

		logger.info("Inside startJob Job Method . Response : " + chkResponse);
		return chkResponse;

	}

	@RequestMapping(value = "/jobStatus/{jobName}", method = RequestMethod.GET)
	private Response jobStatus(@PathVariable String jobName) throws URISyntaxException {
		logger.info("Inside startJob Job Method . Job Name : " + jobName);

		Response chkResponse = new Response();
		chkResponse.setStatus(Constants.SUCCESS);

		try {
			chkResponse.setReturnValue(ciServerManageJob.getJobStatus(jobName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			chkResponse.setStatus(Constants.FAILURE);
			chkResponse.setErrorMessage(e.getMessage());
		}

		logger.info("Inside startJob Job Method . Response : " + chkResponse);
		return chkResponse;
	}

}

/*
 * Map<String, Job> allJobs = null; List<String> jobNames = new
 * ArrayList<String>(); JenkinsServer jenkinsServer = jenkinsServer =
 * defLoader.jenkinsConnection("http://192.168.94.138:8082", "rangan",
 * "rangan123"); allJobs = defService.getAllJOb(jenkinsServer);
 * 
 * for (Map.Entry<String, Job> job : allJobs.entrySet()) {
 * 
 * // logger.info("Key : " + job.getKey()); jobNames.add(job.getKey()); }
 */
