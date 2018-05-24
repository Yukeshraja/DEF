package com.altimetrik.def.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.controller.CDController;
import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.service.CDService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CdControllerTest {

	@Mock
	private CDService cDService;

	@Test
	public void createCdTest() {
		CDResponse cdResponse = new CDResponse();
		cdResponse.setStatus("SUCCESS");
		CDModel cdModel = new CDModel();
		CDController cDController = new CDController();
		cdModel.setCdName("CD");
		given(cDService.createCd(cdModel)).willReturn(cdResponse);
		cDController.setcDService(cDService);
		CDResponse result = cDController.createCd(cdModel);
		assertEquals("SUCCESS", result.getStatus());
	}
	
	@Test
	public void updateCdTest(){
		CDResponse cdResponse = new CDResponse();
		cdResponse.setStatus("SUCCESS");
		CDModel cdModel = new CDModel();
		CDController cDController = new CDController();
		cdModel.setCdName("CD");
		given(cDService.updateCd(cdModel)).willReturn(cdResponse);
		cDController.setcDService(cDService);
		CDResponse result = cDController.updateCd(cdModel);
		assertEquals("SUCCESS", result.getStatus());
	}
	
	@Test
	public void getAllCdTest(){
		CDResponse cdResponse = new CDResponse();
		cdResponse.setStatus("SUCCESS");
		CDController cDController = new CDController();
		given(cDService.getAllCd()).willReturn(cdResponse);
		cDController.setcDService(cDService);
		CDResponse result = cDController.getAllCd();
		assertEquals("SUCCESS", result.getStatus());
	}
	
	@Test
	public void getOneCdTest(){
		CDResponse cdResponse = new CDResponse();
		cdResponse.setStatus("SUCCESS");
		CDController cDController = new CDController();
		given(cDService.getOneCd("CD")).willReturn(cdResponse);
		cDController.setcDService(cDService);
		CDResponse result = cDController.getOneCd("CD");
		assertEquals("SUCCESS", result.getStatus());
	}
	
	@Test
	public void deleteCdTest(){
		CDResponse cdResponse = new CDResponse();
		cdResponse.setStatus("SUCCESS");
		CDController cDController = new CDController();
		given(cDService.deleteCd("CD")).willReturn(cdResponse);
		cDController.setcDService(cDService);
		CDResponse result = cDController.deleteCd("CD");
		assertEquals("SUCCESS", result.getStatus());
	}
	
	
}
