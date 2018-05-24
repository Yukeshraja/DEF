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
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.model.CDModel;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.model.CIModel;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.repository.CdRepository;
import com.altimetrik.def.serviceImpl.CDServiceImpl;
import com.altimetrik.def.serviceImpl.CiServiceImpl;
import com.altimetrik.def.serviceImpl.ValidatorCdImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CdServiceImplTest {

	private static Logger logger = LoggerFactory.getLogger(CdServiceImplTest.class);

	@Mock
	private CdRepository cdRepository;
	@Mock
	private ValidatorCdImpl validatorCdImpl;

	@Test
	public void createCdTestSuccess() {

		CDResponse cdResponse = new CDResponse();
		CDModel cdModel = new CDModel();
		cdResponse.setStatus("SUCCESS");
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		cdModel.setCdName("Name");
		given(validatorCdImpl.validateCD(cdModel)).willReturn(cdResponse);
		given(cdRepository.save(cdModel)).willReturn(cdModel);
		cdServiceImpl.setValidatorCdImpl(validatorCdImpl);
		cdServiceImpl.setCdRepository(cdRepository);
		CDResponse chkResponse = cdServiceImpl.createCd(cdModel);
		assertEquals("SUCCESS", chkResponse.getStatus());

	}

	@Test
	public void createCdTestFailure() {

		CDResponse cdResponse = new CDResponse();
		CDModel cdModel = new CDModel();
		cdResponse.setStatus("SUCCESS");
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		cdModel.setCdName("Name");
		given(validatorCdImpl.validateCD(cdModel)).willReturn(cdResponse);
		given(cdRepository.save(cdModel)).willThrow(NullPointerException.class);
		cdServiceImpl.setValidatorCdImpl(validatorCdImpl);
		cdServiceImpl.setCdRepository(cdRepository);
		CDResponse chkResponse = cdServiceImpl.createCd(cdModel);
		assertEquals("FAILURE", chkResponse.getStatus());

	}

	@Test
	public void createCdTestValidateJobFailure() {

		CDResponse cdResponse = new CDResponse();
		CDModel cdModel = new CDModel();
		cdResponse.setStatus("FAILURE");
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		cdModel.setCdName("Name");
		given(validatorCdImpl.validateCD(cdModel)).willReturn(cdResponse);
		given(cdRepository.save(cdModel)).willReturn(cdModel);
		cdServiceImpl.setValidatorCdImpl(validatorCdImpl);
		cdServiceImpl.setCdRepository(cdRepository);
		CDResponse chkResponse = cdServiceImpl.createCd(cdModel);
		assertEquals("FAILURE", chkResponse.getStatus());

	}

	@Test
	public void updateCdTestSuccess() {

		CDResponse cdResponse = new CDResponse();
		CDModel cdModel = new CDModel();
		cdResponse.setStatus("SUCCESS");
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		cdModel.setCdName("Name");
		given(validatorCdImpl.validateCD(cdModel)).willReturn(cdResponse);
		given(cdRepository.save(cdModel)).willReturn(cdModel);
		cdServiceImpl.setValidatorCdImpl(validatorCdImpl);
		cdServiceImpl.setCdRepository(cdRepository);
		CDResponse chkResponse = cdServiceImpl.updateCd(cdModel);
		assertEquals("SUCCESS", chkResponse.getStatus());

	}

	@Test
	public void updateCdTestFailure() {

		CDResponse cdResponse = new CDResponse();
		CDModel cdModel = new CDModel();
		cdResponse.setStatus("SUCCESS");
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		cdModel.setCdName("Name");
		given(validatorCdImpl.validateCD(cdModel)).willReturn(cdResponse);
		given(cdRepository.save(cdModel)).willThrow(NullPointerException.class);
		cdServiceImpl.setValidatorCdImpl(validatorCdImpl);
		cdServiceImpl.setCdRepository(cdRepository);
		CDResponse chkResponse = cdServiceImpl.updateCd(cdModel);
		assertEquals("FAILURE", chkResponse.getStatus());
	}

	@Test
	public void getAllCdSuccessTest() {
		CDResponse cdResponse = new CDResponse();
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		CDModel cdModel = new CDModel();
		List<CDModel> cdModels = new ArrayList<CDModel>();
		given(cdRepository.findAll()).willReturn(cdModels);
		cdServiceImpl.setCdRepository(cdRepository);
		cdResponse = cdServiceImpl.getAllCd();
		assertEquals("SUCCESS", cdResponse.getStatus());

	}

	@Test
	public void getAllCdFailureTest() {
		CDResponse cdResponse = new CDResponse();
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		CDModel cdModel = new CDModel();
		List<CDModel> cdModels = new ArrayList<CDModel>();
		given(cdRepository.findAll()).willThrow(NullPointerException.class);
		cdServiceImpl.setCdRepository(cdRepository);
		cdResponse = cdServiceImpl.getAllCd();
		assertEquals("FAILURE", cdResponse.getStatus());

	}

	@Test
	public void getOneCdSuccessTest() {
		CDResponse cdResponse = new CDResponse();
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		CDModel cdModel = new CDModel();
		given(cdRepository.findOne("Test")).willReturn(cdModel);
		cdServiceImpl.setCdRepository(cdRepository);
		cdResponse = cdServiceImpl.getOneCd("Test");
		assertEquals("SUCCESS", cdResponse.getStatus());

	}

	@Test
	public void getOneCdFailureTest() {
		CDResponse cdResponse = new CDResponse();
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		CDModel cdModel = new CDModel();
		given(cdRepository.findOne("Test")).willThrow(NullPointerException.class);
		cdServiceImpl.setCdRepository(cdRepository);
		cdResponse = cdServiceImpl.getOneCd("Test");
		assertEquals("FAILURE", cdResponse.getStatus());
	}

	@Test
	public void deleteCdSuccessTest() {
		CDResponse cdResponse = new CDResponse();
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		doNothing().when(cdRepository).delete("Test");
		cdServiceImpl.setCdRepository(cdRepository);
		cdResponse = cdServiceImpl.deleteCd("Test");
		assertEquals("SUCCESS", cdResponse.getStatus());
	}

	@Test
	public void deleteCdFailureTest() {
		CDResponse cdResponse = new CDResponse();
		CDServiceImpl cdServiceImpl = new CDServiceImpl();
		doNothing().when(cdRepository).delete("Test");
		cdServiceImpl.setCdRepository(cdRepository);
		cdResponse = cdServiceImpl.deleteCd("Test");
		assertEquals("SUCCESS", cdResponse.getStatus());
	}

}
