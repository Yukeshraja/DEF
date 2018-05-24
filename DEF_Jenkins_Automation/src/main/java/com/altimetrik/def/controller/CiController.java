package com.altimetrik.def.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.CiService;
import com.altimetrik.def.serviceImpl.JenkinsCiServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class CiController {

	private Logger logger = LoggerFactory.getLogger(CiController.class);

	@Autowired
	private CiService ciService;
	@Autowired
	private JenkinsCiServiceImpl jenkinsCiServiceImpl;

	/**
	 * @param ciService
	 *            the ciService to set
	 */
	public void setCiService(CiService ciService) {
		this.ciService = ciService;
	}

	/**
	 * @param jenkinsCiServiceImpl
	 *            the jenkinsCiServiceImpl to set
	 */
	public void setJenkinsCiServiceImpl(JenkinsCiServiceImpl jenkinsCiServiceImpl) {
		this.jenkinsCiServiceImpl = jenkinsCiServiceImpl;
	}

	@RequestMapping(value = "/createCi", method = RequestMethod.POST)
	public CiModelResponse createCi(@RequestBody CIModel ci) {
		logger.info("Inside createCi() Function of CiController");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		// response = ciService.createCi(ci);
		logger.info("Ending of createCi() Function of CiController");
		chkResponse = ciService.createCi(ci);

		return chkResponse;
	}

	@RequestMapping(value = "/updateCi/{name}", method = RequestMethod.PUT)
	public CiModelResponse updateCi(@RequestBody CIModel ci, @PathVariable String name) {
		logger.info("Inside updateCi() Function of CiController");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		// response = ciService.createCi(ci);
		chkResponse = ciService.updateCi(ci);
		logger.info("Ending of updateCi() Function of CiController");
		return chkResponse;
	}

	@RequestMapping(value = "/getAllCi", method = RequestMethod.GET)
	public CiModelResponse getAllCi() {
		logger.info("Inside getAllCi() Function of CiController");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		// response = ciService.createCi(ci);
		chkResponse = ciService.getAllCi();
		logger.info("Ending of getAllCi() Function of CiController");
		return chkResponse;
	}

	@RequestMapping(value = "/getOneCi/{name}", method = RequestMethod.GET)
	public CiModelResponse getOneCi(@PathVariable String name) {
		logger.info("Inside getOneCi() Function of CiController");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		// CIModel ciModel = new CIModel();
		// response = ciService.createCi(ci);
		chkResponse = ciService.getOneCi(name);
		logger.info("Ending of getOneCi() Function of CiController");
		return chkResponse;
	}

	@RequestMapping(value = "/deleteCi/{name}", method = RequestMethod.DELETE)
	public CiModelResponse deleteCi(@PathVariable String name) {
		logger.info("Inside deleteCi() Function of CiController");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		// CIModel ciModel = new CIModel();
		// response = ciService.createCi(ci);
		chkResponse = ciService.deleteCi(name);
		logger.info("Ending of deleteCi() Function of CiController");
		return chkResponse;
	}

	@RequestMapping(value = "/validateCi", method = RequestMethod.POST)
	public Response validateCi(@RequestBody CIModel ciModel) {
		logger.info("Inside validateCi() Function of CiController");
		Date date = new Date();
		Response chkResponse = new Response();
		// CIModel ciModel = new CIModel();
		// response = ciService.createCi(ci);
		chkResponse = jenkinsCiServiceImpl.ciValidateJob(ciModel);
		logger.info("Ending of validateCi() Function of CiController");
		return chkResponse;
	}

}
