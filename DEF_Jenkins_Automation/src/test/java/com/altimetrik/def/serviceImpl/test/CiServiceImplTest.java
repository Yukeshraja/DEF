package com.altimetrik.def.serviceImpl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.repository.CiRepository;
import com.altimetrik.def.serviceImpl.CiServiceImpl;
import com.altimetrik.def.serviceImpl.JenkinsCiServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CiServiceImplTest {

	@Mock
	private CiRepository ciRepository;
	@Mock
	private JenkinsCiServiceImpl jenkinsCiServiceImpl;

	private static Logger logger = LoggerFactory.getLogger(CiServiceImplTest.class);

	@Test
	public void createCiTestSuccess() {

		Response jenkinsResponse = new Response();
		CIModel ciModel = new CIModel();
		jenkinsResponse.setStatus("SUCCESS");
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		ciModel.setCiName("Name");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(jenkinsResponse);
		given(ciRepository.save(ciModel)).willReturn(ciModel);
		ciServiceImpl.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		ciServiceImpl.setCiRepository(ciRepository);
		CiModelResponse chkResponse = ciServiceImpl.createCi(ciModel);
		assertEquals("SUCCESS", chkResponse.getStatus());

	}

	@Test
	public void createCiTestFailure() {

		Response jenkinsResponse = new Response();
		CIModel ciModel = new CIModel();
		jenkinsResponse.setStatus("SUCCESS");
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		ciModel.setCiName("Name");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(jenkinsResponse);
		given(ciRepository.save(ciModel)).willThrow(NullPointerException.class);
		ciServiceImpl.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		ciServiceImpl.setCiRepository(ciRepository);
		CiModelResponse chkResponse = ciServiceImpl.createCi(ciModel);
		assertEquals("FAILURE", chkResponse.getStatus());
	}

	@Test
	public void createCiTestValidateJobFailure() {

		Response jenkinsResponse = new Response();
		CIModel ciModel = new CIModel();
		jenkinsResponse.setStatus("FAILURE");
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		ciModel.setCiName("Name");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(jenkinsResponse);
		given(ciRepository.save(ciModel)).willReturn(ciModel);
		ciServiceImpl.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		ciServiceImpl.setCiRepository(ciRepository);
		CiModelResponse chkResponse = ciServiceImpl.createCi(ciModel);
		assertEquals("FAILURE", chkResponse.getStatus());
	}

	@Test
	public void updateCiTestSuccess() {

		Response jenkinsResponse = new Response();
		CIModel ciModel = new CIModel();
		jenkinsResponse.setStatus("SUCCESS");
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		ciModel.setCiName("Name");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(jenkinsResponse);
		given(ciRepository.save(ciModel)).willReturn(ciModel);
		ciServiceImpl.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		ciServiceImpl.setCiRepository(ciRepository);
		CiModelResponse chkResponse = ciServiceImpl.updateCi(ciModel);
		assertEquals("SUCCESS", chkResponse.getStatus());

	}

	@Test
	public void updateCiTestFailure() {

		Response jenkinsResponse = new Response();
		CIModel ciModel = new CIModel();
		jenkinsResponse.setStatus("SUCCESS");
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		ciModel.setCiName("Name");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(jenkinsResponse);
		given(ciRepository.save(ciModel)).willThrow(NullPointerException.class);
		ciServiceImpl.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		ciServiceImpl.setCiRepository(ciRepository);
		CiModelResponse chkResponse = ciServiceImpl.updateCi(ciModel);
		assertEquals("FAILURE", chkResponse.getStatus());
	}

	@Test
	public void updateCiTestValidateJobFailure() {

		Response jenkinsResponse = new Response();
		CIModel ciModel = new CIModel();
		jenkinsResponse.setStatus("FAILURE");
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		ciModel.setCiName("Name");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(jenkinsResponse);
		given(ciRepository.save(ciModel)).willReturn(ciModel);
		ciServiceImpl.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		ciServiceImpl.setCiRepository(ciRepository);
		CiModelResponse chkResponse = ciServiceImpl.updateCi(ciModel);
		assertEquals("FAILURE", chkResponse.getStatus());
	}

	@Test
	public void getAllCiSuccessTest() {
		CiModelResponse chkResponse = new CiModelResponse();
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		chkResponse.setStatus("SUCCESS");
		List<CIModel> ciModels = new ArrayList<CIModel>();
		given(ciRepository.findAll()).willReturn(ciModels);
		ciServiceImpl.setCiRepository(ciRepository);
		chkResponse = ciServiceImpl.getAllCi();
		assertEquals("SUCCESS", chkResponse.getStatus());

	}
	
	@Test
	public void getAllCiFailureTest() {
		CiModelResponse chkResponse = new CiModelResponse();
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		chkResponse.setStatus("SUCCESS");
		List<CIModel> ciModels = new ArrayList<CIModel>();
		given(ciRepository.findAll()).willThrow(NullPointerException.class);
		ciServiceImpl.setCiRepository(ciRepository);
		chkResponse = ciServiceImpl.getAllCi();
		assertEquals("FAILURE", chkResponse.getStatus());

	}
	
	@Test
	public void getOneCiSuccessTest() {
		CiModelResponse chkResponse = new CiModelResponse();
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		chkResponse.setStatus("SUCCESS");
		CIModel ciModel = new CIModel();
		given(ciRepository.findOne("Test")).willReturn(ciModel);
		ciServiceImpl.setCiRepository(ciRepository);
		chkResponse = ciServiceImpl.getOneCi("Test");
		assertEquals("SUCCESS", chkResponse.getStatus());

	}
	
	@Test
	public void getOneCiFailureTest() {
		CiModelResponse chkResponse = new CiModelResponse();
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		chkResponse.setStatus("SUCCESS");
		CIModel ciModel = new CIModel();
		given(ciRepository.findOne("Test")).willThrow(NullPointerException.class);
		ciServiceImpl.setCiRepository(ciRepository);
		chkResponse = ciServiceImpl.getOneCi("Test");
		assertEquals("FAILURE", chkResponse.getStatus());

	}
	
	@Test
	public void deleteCiSuccessTest(){
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		CiModelResponse chkResponse = new CiModelResponse();
		doNothing().when(ciRepository).delete("Test");
		ciServiceImpl.setCiRepository(ciRepository);
		chkResponse = ciServiceImpl.deleteCi("Test");
		assertEquals("SUCCESS", chkResponse.getStatus());
	}
	
/*	@Test
	public void deleteCiFailureTest(){
		CiServiceImpl ciServiceImpl = new CiServiceImpl();
		CiModelResponse chkResponse = new CiModelResponse();
		doNothing().when(ciRepository).delete("Test");
		ciServiceImpl.setCiRepository(ciRepository);
		chkResponse = ciServiceImpl.deleteCi("Test");
		assertEquals("FAILURE", chkResponse.getStatus());
	}*/
}
