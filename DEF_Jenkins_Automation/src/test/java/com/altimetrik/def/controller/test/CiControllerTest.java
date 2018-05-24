package com.altimetrik.def.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.controller.CiController;
import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.service.CiService;
import com.altimetrik.def.serviceImpl.JenkinsCiServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CiControllerTest {

	private static Logger logger = LoggerFactory.getLogger(CiControllerTest.class);
	
	@Mock
	private CiService ciService;
	@Mock
	private JenkinsCiServiceImpl jenkinsCiServiceImpl;
	
	@Test
	public void createCiTest(){
	
		CiModelResponse chkResponse = new CiModelResponse();
		CiController ciController = new CiController();
		CIModel ciModel = new CIModel();
		chkResponse.setStatus("SUCCESS");
		given(ciService.createCi(ciModel)).willReturn(chkResponse);
		ciController.setCiService(ciService);
		CiModelResponse ciModelResponse = ciController.createCi(ciModel);
		assertEquals("SUCCESS", ciModelResponse.getStatus());
	}

	@Test
	public void updateCiTest(){
		
		CiModelResponse chkResponse = new CiModelResponse();
		CiController ciController = new CiController();
		CIModel ciModel = new CIModel();
		chkResponse.setStatus("SUCCESS");
		given(ciService.updateCi(ciModel)).willReturn(chkResponse);
		ciController.setCiService(ciService);
		CiModelResponse ciModelResponse = ciController.updateCi(ciModel, "Test");
		assertEquals("SUCCESS", ciModelResponse.getStatus());
	}
	
	@Test
	public void getAllCiTest(){
		
		CiModelResponse chkResponse = new CiModelResponse();
		CiController ciController = new CiController();	
		chkResponse.setStatus("SUCCESS");
		given(ciService.getAllCi()).willReturn(chkResponse);
		ciController.setCiService(ciService);
		CiModelResponse ciModelResponse = ciController.getAllCi();
		assertEquals("SUCCESS", ciModelResponse.getStatus());
	}
	
	@Test
	public void getOneCiTest(){
		
		CiModelResponse chkResponse = new CiModelResponse();
		CiController ciController = new CiController();	
		chkResponse.setStatus("SUCCESS");
		given(ciService.getOneCi("Test")).willReturn(chkResponse);
		ciController.setCiService(ciService);
		CiModelResponse ciModelResponse = ciController.getOneCi("Test");
		assertEquals("SUCCESS", ciModelResponse.getStatus());
	}
	
	@Test
	public void deleteCiTest(){
		
		CiModelResponse chkResponse = new CiModelResponse();
		CiController ciController = new CiController();	
		chkResponse.setStatus("SUCCESS");
		given(ciService.deleteCi("Test")).willReturn(chkResponse);
		ciController.setCiService(ciService);
		CiModelResponse ciModelResponse = ciController.deleteCi("Test");
		assertEquals("SUCCESS", ciModelResponse.getStatus());
	}
	
	@Test
	public void validateCi(){
		
		Response chkResponse = new Response();
		CIModel ciModel = new CIModel();
		CiController ciController = new CiController();	
		ciModel.setCiName("Test");
		ciModel.setStatus("SUCCESS");
		ciModel.setTargetReleaseSynatax("targetReleaseSynatax");
		ciModel.setTargetSnapshotSyntax("targetSnapshotSyntax");
		chkResponse.setStatus("SUCCESS");
		given(jenkinsCiServiceImpl.ciValidateJob(ciModel)).willReturn(chkResponse);
		ciController.setJenkinsCiServiceImpl(jenkinsCiServiceImpl);
		Response result = ciController.validateCi(ciModel);
		assertEquals("SUCCESS", result.getStatus());
	}
}
