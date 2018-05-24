package com.altimetrik.def.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.repository.CdRepository;
import com.altimetrik.def.service.CDService;

@Service
public class CDServiceImpl implements CDService {

	@Autowired
	private CdRepository cdRepository;
	@Autowired
	private ValidatorCdImpl validatorCdImpl;

	private static Logger logger = LoggerFactory.getLogger(CDServiceImpl.class);
	

	/**
	 * @param cdRepository the cdRepository to set
	 */
	public void setCdRepository(CdRepository cdRepository) {
		this.cdRepository = cdRepository;
	}



	/**
	 * @param validatorCdImpl the validatorCdImpl to set
	 */
	public void setValidatorCdImpl(ValidatorCdImpl validatorCdImpl) {
		this.validatorCdImpl = validatorCdImpl;
	}

	@Override
	public CDResponse createCd(CDModel cd) {
		// TODO Auto-generated method stub
		Date date = new Date();
		logger.info("Inside createCd() Funtion of CdServiceImpl");
		//CDResponse cdResponse = new CDResponse();
		//cdResponse.setStatus("SUCCESS");
		//cdResponse.setTimestamp(date.getTime());
		CDResponse response =  validatorCdImpl.validateCD(cd);
		if(response.getStatus().equals("SUCCESS"))
		{
			cd.setStatus("ENABLED");
			logger.info("Enabled");
		}else{
			cd.setStatus("DISABLED");
			logger.info("Disabled");
		}
		try {
			cdRepository.save(cd);
			logger.info("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of createCd() Function of CdServiceImpl");
		return response;
	}

	@Override
	public CDResponse updateCd(CDModel cd) {
		// TODO Auto-generated method stub
		logger.info("Inside updateCd() Funtion of CdServiceImpl");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse.setStatus("SUCCESS");
		cdResponse.setTimestamp(date.getTime());
		try {
			cdRepository.save(cd);
			logger.info("No Error");
		} catch (Exception exception) {
			cdResponse.setStatus("FAILURE");
			cdResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of updateCd() Function of CdServiceImpl");
		return cdResponse;
	}

	@Override
	public CDResponse getAllCd() {
		logger.info("Inside getAllCd() Function of CdServiceImpl");
		// TODO Auto-generated method stub
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse.setTimestamp(date.getTime());
		cdResponse.setStatus("SUCCESS");
		List<CDModel> cdModels = new ArrayList<CDModel>();
		try {
			cdModels = cdRepository.findAll();
			logger.info("No Error");
		} catch (Exception exception) {
			cdResponse.setStatus("FAILURE");
			cdResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		cdResponse.setResult(cdModels);
		logger.info("Ending of getAllCd() Function of CdServiceImpl");
		return cdResponse;
	}

	@Override
	public CDResponse getOneCd(String name) {
		// TODO Auto-generated method stub
		logger.info("Inside getOneCd() Function of CdServiceImpl");
		CDModel cdModel = new CDModel();
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse.setTimestamp(date.getTime());
		cdResponse.setStatus("SUCCESS");
		List<CDModel> cdModels = new ArrayList<CDModel>();
		try {
			cdModel = cdRepository.findOne(name);
			logger.info("No Error");
		} catch (Exception exception) {
			cdResponse.setStatus("FAILURE");
			cdResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		cdModels.add(cdModel);
		cdResponse.setResult(cdModels);
		logger.info("Ending of getOneCd() Function of CdServiceImpl");
		return cdResponse;
	}

	@Override
	public CDResponse deleteCd(String CDId) {
		// TODO Auto-generated method stub
		logger.info("Inside deleteCd() Function of CdServiceImpl");
		Date date = new Date();
		CDResponse cdResponse = new CDResponse();
		cdResponse.setTimestamp(date.getTime());
		cdResponse.setStatus("SUCCESS");
		try {
			cdRepository.delete(CDId);
			logger.info("No Error");
		} catch (Exception exception) {
			cdResponse.setStatus("FAILURE");
			cdResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of deleteCd() Function of CdServiceImpl");
		return cdResponse;
	}

}
