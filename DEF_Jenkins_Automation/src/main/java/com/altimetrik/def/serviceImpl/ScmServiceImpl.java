package com.altimetrik.def.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.model.ScmResponse;
import com.altimetrik.def.repository.ScmRepository;
import com.altimetrik.def.service.ScmService;

@Service
public class ScmServiceImpl implements ScmService {

	@Autowired
	private ScmRepository scmRepository;
	@Autowired
	private GitValidatorImpl gitValidatorImpl;
	
	private Logger logger = LoggerFactory.getLogger(ScmServiceImpl.class);

	/**
	 * @param scmRepository
	 *            the scmRepository to set
	 */
	public void setScmRepository(ScmRepository scmRepository) {
		this.scmRepository = scmRepository;
	}

	/**
	 * @param gitValidatorImpl
	 *            the gitValidatorImpl to set
	 */
	public void setGitValidatorImpl(GitValidatorImpl gitValidatorImpl) {
		this.gitValidatorImpl = gitValidatorImpl;
	}

	@Override
	public ScmResponse createScm(SCM scm) {
		// TODO Auto-generated method stub
		logger.info("Inside createScm() Function of ScmServiceImpl");
		ScmResponse response = new ScmResponse();
		Response chkResponse = new Response();
		Date date = new Date();
		response.setStatus("SUCCESS");
		scm.setStatus("Enabled");
		response.setTimestamp(date.getTime());
		chkResponse = gitValidatorImpl.chkDetailsValidation(scm);
		if (chkResponse.getStatus().equals("FAILURE")) {
			response.setStatus("FAILURE");
			response.setErrorMessage(chkResponse.getErrorMessage());
			response.setError(chkResponse.getError());
			scm.setStatus("Disabled");
		}
		try {
			scmRepository.save(scm);
			logger.info("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of createScm() Function of ScmServiceImpl");
		return response;
	}

	@Override
	public ScmResponse updateScm(SCM scm) {
		// TODO Auto-generated method stub
		logger.info("Inside updateScm() Function of ScmServiceImpl");
		ScmResponse response = new ScmResponse();
		Date date = new Date();
		response.setStatus("SUCCESS");
		scm.setStatus("Enabled");
		response.setTimestamp(date.getTime());
		Response chkResponse = new Response();
		chkResponse = gitValidatorImpl.chkDetailsValidation(scm);
		if (chkResponse.getStatus().equals("FAILURE")) {
			response.setStatus("FAILURE");
			response.setErrorMessage(chkResponse.getErrorMessage());
			response.setError(chkResponse.getError());
			scm.setStatus("Disabled");
		}
		try {
			scmRepository.save(scm);
			logger.info("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		logger.info("Ending of updateScm() Function of ScmServiceImpl");
		return response;
	}

	@Override
	public ScmResponse getAllScm() {
		// TODO Auto-generated method stub
		logger.info("Inside getAllScm() Function of ScmServiceImpl");
		ScmResponse response = new ScmResponse();
		List<SCM> scmList = new ArrayList<SCM>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			scmList = scmRepository.findAll();
			response.setErrorMessage("No Error");
			logger.info("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			logger.info(exception.getMessage());
		}
		response.setResult(scmList);
		logger.info("Ending of getAllScm() Function of ScmServiceImpl");
		return response;
	}

	@Override
	public ScmResponse getOneScm(String name) {
		// TODO Auto-generated method stub
		logger.info("Inside getOneScm() Function of ScmServiceImpl");
		ScmResponse response = new ScmResponse();
		SCM scm = new SCM();
		List<SCM> scmList = new ArrayList<SCM>();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			scm = scmRepository.findOne(name);
			scmList.add(scm);
			response.setErrorMessage("No Error");
			logger.info("No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			logger.info("Failure Getting Details");
			logger.info(exception.getMessage());
		}
		response.setResult(scmList);
		logger.info("Ending of deleteScm() Function of ScmServiceImpl");
		return response;
	}

	@Override
	public ScmResponse deleteScm(String name) {
		// TODO Auto-generated method stub
		logger.info("Inside deleteScm() Function of ScmServiceImpl");
		ScmResponse response = new ScmResponse();
		Date date = new Date();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		try {
			scmRepository.delete(name);
			response.setErrorMessage("No Error");
			logger.info("Success !! No Error");
		} catch (Exception exception) {
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
			logger.info("Failure Deleting");
			logger.info(exception.getMessage());
		}
		logger.info("Ending of deleteScm() Function of ScmServiceImpl");
		return response;
	}

}
