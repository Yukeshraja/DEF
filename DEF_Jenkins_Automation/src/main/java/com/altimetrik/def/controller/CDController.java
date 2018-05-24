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

import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.service.CDService;
import com.altimetrik.def.serviceImpl.ValidatorCdImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class CDController {

	private Logger logger = LoggerFactory.getLogger(CDController.class);
	@Autowired
	private CDService cDService;
	@Autowired
	private ValidatorCdImpl validatorCdImpl;

	/**
	 * @param cDService
	 *            the cDService to set
	 */
	public void setcDService(CDService cDService) {
		this.cDService = cDService;
	}

	@RequestMapping(value = "/createCd", method = RequestMethod.POST)
	public CDResponse createCd(@RequestBody CDModel cdModel) {
		logger.info("Inside createCd() Function of CDController");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse.setTimestamp(date.getTime());
		cdResponse.setStatus("SUCCESS");
		cdResponse = cDService.createCd(cdModel);
		logger.info("Ending of createCd() Function of CDController");
		return cdResponse;
	}

	@RequestMapping(value = "/updateCd", method = RequestMethod.PUT)
	public CDResponse updateCd(@RequestBody CDModel cdModel) {
		logger.info("Inside updateCd() Function of CDController");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse.setTimestamp(date.getTime());
		cdResponse.setStatus("SUCCESS");
		cdResponse = cDService.updateCd(cdModel);
		logger.info("Ending of updateCd() Function of CDController");
		return cdResponse;
	}

	@RequestMapping(value = "/getAllCd", method = RequestMethod.GET)
	public CDResponse getAllCd() {
		logger.info("Inside getAllCd() Function of CDController");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse = cDService.getAllCd();
		logger.info("Ending of getAllCd() Function of CDController");
		return cdResponse;
	}

	@RequestMapping(value = "/getOneCd/{name}", method = RequestMethod.GET)
	public CDResponse getOneCd(@PathVariable String name) {
		logger.info("Inside getOneCd() Function of CDController");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse = cDService.getOneCd(name);
		logger.info("Ending of getOneCd() Function of CDController");
		return cdResponse;
	}

	@RequestMapping(value = "/deleteCd/{name}", method = RequestMethod.DELETE)
	public CDResponse deleteCd(@PathVariable String name) {
		logger.info("Inside deleteCd() Function of CDController");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse = cDService.deleteCd(name);
		logger.info("Ending of deleteCd() Function of CDController");
		return cdResponse;
	}

	@RequestMapping(value = "/validateCd", method = RequestMethod.POST)
	public CDResponse validateCd(@RequestBody CDModel cdModel) {

		CDResponse cdResponse = validatorCdImpl.validateCD(cdModel);
		return cdResponse;

	}
}
