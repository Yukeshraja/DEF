package com.altimetrik.def.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.repository.CiRepository;
import com.altimetrik.def.service.CiService;

@Service
public class CiServiceImpl implements CiService {

	@Autowired
	private CiRepository ciRepository;
	@Autowired
	private JenkinsCiServiceImpl jenkinsCiServiceImpl;
	private Logger logger = LoggerFactory.getLogger(CiServiceImpl.class);

	/**
	 * @param ciRepository
	 *            the ciRepository to set
	 */
	public void setCiRepository(CiRepository ciRepository) {
		this.ciRepository = ciRepository;
	}


	/**
	 * @param jenkinsCiServiceImpl
	 *            the jenkinsCiServiceImpl to set
	 */
	public void setJenkinsCiServiceImpl(JenkinsCiServiceImpl jenkinsCiServiceImpl) {
		this.jenkinsCiServiceImpl = jenkinsCiServiceImpl;
	}

	@Override
	public CiModelResponse createCi(CIModel ciModel) {
		// TODO Auto-generated method stub
		logger.info("Inside createCi() Function of CiServiceImpl");
		Date date = new Date();
		Response jenkinsResponse = jenkinsCiServiceImpl.ciValidateJob(ciModel);
		CiModelResponse chkResponse = new CiModelResponse();
		chkResponse.setTimestamp(date.getTime());
		if (jenkinsResponse.getStatus().equals("SUCCESS")) {
			try {
				ciModel.setStatus("ENABLED");
				ciRepository.save(ciModel);
				chkResponse.setStatus("SUCCESS");
				chkResponse.setErrorMessage("No Error");
				logger.info("No Error");
			} catch (Exception exception) {
				chkResponse.setStatus("FAILURE");
				chkResponse.setErrorMessage(exception.getMessage());
				logger.info(exception.getMessage());
			}
		} else {
			ciModel.setStatus("DISABLED");
			ciRepository.save(ciModel);
			chkResponse.setStatus("FAILURE");
			chkResponse.setErrorMessage(jenkinsResponse.getErrorMessage());
			chkResponse.setError(jenkinsResponse.getError());
			logger.info("DISABLED");
		}
		return chkResponse;
	}

	@Override
	public CiModelResponse updateCi(CIModel ciModel) {
		// TODO Auto-generated method stub
		logger.info("Inside updateCi() Function of CiServiceImpl");
		Date date = new Date();
		Response jenkinsResponse = jenkinsCiServiceImpl.ciValidateJob(ciModel);
		CiModelResponse chkResponse = new CiModelResponse();
		chkResponse.setTimestamp(date.getTime());
		if (jenkinsResponse.getStatus().equals("SUCCESS")) {
			try {
				ciModel.setStatus("ENABLED");
				ciRepository.save(ciModel);
				chkResponse.setStatus("SUCCESS");
				chkResponse.setErrorMessage("No Error");
				logger.info("No Error");
			} catch (Exception exception) {
				logger.info(exception.getMessage());
				chkResponse.setStatus("FAILURE");
				chkResponse.setErrorMessage(exception.getMessage());
			}
		} else {
			ciModel.setStatus("DISABLED");
			logger.info("DISABLED");
			ciRepository.save(ciModel);
			chkResponse.setStatus("FAILURE");
			chkResponse.setErrorMessage(jenkinsResponse.getErrorMessage());
			chkResponse.setError(jenkinsResponse.getError());
		}
		logger.info("Ending of updateCi() Function of CiServiceImpl");
		return chkResponse;
	}

	@Override
	public CiModelResponse getAllCi() {
		// TODO Auto-generated method stub
		logger.info("Inside getAllCi() Function of CiServiceImpl");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		chkResponse.setStatus("SUCCESS");
		chkResponse.setTimestamp(date.getTime());
		try {
			List<CIModel> ciModels = ciRepository.findAll();
			chkResponse.setResult(ciModels);
			logger.info("No Error");
		} catch (Exception exception) {
			chkResponse.setStatus("FAILURE");
			chkResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of getAllCi() Function of CiServiceImpl");
		return chkResponse;
	}

	@Override
	public CiModelResponse getOneCi(String ciName) {
		// TODO Auto-generated method stub
		logger.info("Inside getOneCi() Function of CiServiceImpl");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		chkResponse.setStatus("SUCCESS");
		chkResponse.setTimestamp(date.getTime());
		List<CIModel> ciModels = new ArrayList<CIModel>();
		try {
			CIModel ciModel = ciRepository.findOne(ciName);
			ciModels.add(ciModel);
			chkResponse.setResult(ciModels);
			logger.info("No Error");
		} catch (Exception exception) {
			chkResponse.setStatus("FAILURE");
			chkResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of getOneCi() Function of CiServiceImpl");
		return chkResponse;
	}

	@Override
	public CiModelResponse deleteCi(String ciName) {
		// TODO Auto-generated method stub
		logger.info("Inside deleteCi() Function of CiServiceImpl");
		Date date = new Date();
		CiModelResponse chkResponse = new CiModelResponse();
		chkResponse.setStatus("SUCCESS");
		chkResponse.setTimestamp(date.getTime());
		try {
			ciRepository.delete(ciName);
			logger.info("Success !! No Error");
		} catch (Exception exception) {
			chkResponse.setStatus("FAILURE");
			chkResponse.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of deleteCi() Function of CiServiceImpl");
		return chkResponse;
	}

}
