package com.altimetrik.def.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.model.ScmResponse;
import com.altimetrik.def.service.ScmService;
import com.altimetrik.def.serviceImpl.DefValidatorImpl;
import com.altimetrik.def.serviceImpl.GitValidatorImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class ScmController {

	private Logger logger = LoggerFactory.getLogger(ScmController.class);

	@Autowired
	private ScmService scmService;
	@Autowired
	private DefValidatorImpl defValidatorImpl;
	@Autowired
	private GitValidatorImpl gitValidatorImpl;

	/**
	 * @param scmService
	 *            the scmService to set
	 */
	public void setScmService(ScmService scmService) {
		this.scmService = scmService;
	}

	/**
	 * @return the defValidatorImpl
	 */

	/**
	 * @param gitValidatorImpl
	 *            the gitValidatorImpl to set
	 */
	public void setGitValidatorImpl(GitValidatorImpl gitValidatorImpl) {
		this.gitValidatorImpl = gitValidatorImpl;
	}

	@RequestMapping(value = "/createScm", method = RequestMethod.POST)
	public ScmResponse createScm(@RequestBody SCM scm) {
		logger.info("Inside createScm() Function of ScmController");
		ScmResponse chkResponse = new ScmResponse();
		chkResponse = scmService.createScm(scm);
		logger.info("Ending of createScm() Function of ScmController");
		return chkResponse;
	}

	@RequestMapping(value = "/updateScm/{name}", method = RequestMethod.PUT)
	public ScmResponse updateScm(@RequestBody SCM scm, @PathVariable String name) {
		// TODO Auto-generated method stub
		logger.info("Inside updateScm() Function of ScmController");
		ScmResponse chkResponse = new ScmResponse();
		chkResponse = scmService.updateScm(scm);
		logger.info("Ending of updateScm() Function of ScmController");
		return chkResponse;

	}

	@RequestMapping(value = "/getAllScm", method = RequestMethod.GET)
	public ScmResponse getAllScm() {
		// TODO Auto-generated method stub
		logger.info("Inside getAllScm() Function of ScmController");
		ScmResponse chkResponse = new ScmResponse();
		chkResponse = scmService.getAllScm();
		logger.info("Ending of getAllScm() Function of ScmController");
		return chkResponse;
	}

	@RequestMapping(value = "/getOneScm/{name}", method = RequestMethod.GET)
	public ScmResponse getOneScm(@PathVariable String name) {
		// TODO Auto-generated method stub
		logger.info("Inside getOneScm() Function of ScmController");
		ScmResponse chkResponse = new ScmResponse();
		chkResponse = scmService.getOneScm(name);
		logger.info("Ending of getOneScm() Function of ScmController");
		return chkResponse;
	}

	@RequestMapping(value = "/deleteScm/{name}", method = RequestMethod.DELETE)
	public ScmResponse deleteScm(@PathVariable String name) {
		// TODO Auto-generated method stub
		logger.info("Inside deleteScm() Function of ScmController");
		ScmResponse chkResponse = new ScmResponse();
		chkResponse = scmService.deleteScm(name);
		logger.info("Ending of deleteScm() Function of ScmController");
		return chkResponse;
	}

	@RequestMapping(value = "/validateScm", method = RequestMethod.POST)
	public Response validateScm(@RequestBody SCM scm) {
		// TODO Auto-generated method stub
		logger.info("Inside validateScm() Function of ScmController");
		Response chkResponse = new Response();
		chkResponse = gitValidatorImpl.chkDetailsValidation(scm);
		logger.info("Ending of validateScm() Function of ScmController");
		return chkResponse;
	}

}
