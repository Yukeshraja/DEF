package com.altimetrik.def.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.controller.ScmController;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.model.ScmResponse;
import com.altimetrik.def.service.ScmService;
import com.altimetrik.def.serviceImpl.GitValidatorImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ScmControllerTest {

	private static Logger logger = LoggerFactory.getLogger(ScmControllerTest.class);

	@Mock
	private ScmService scmService;
	@Mock
	private GitValidatorImpl gitValidatorImpl;

	@Test
	public void createScmTest() {
		ScmResponse chkResponse = new ScmResponse();
		ScmController scmController = new ScmController();
		SCM scm = new SCM();
		scm.setName("github");
		scm.setRepoBranch("*/master");
		Response response = new Response();
		chkResponse.setStatus("SUCCESS");
		response.setStatus("SUCCESS");
		//given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(response);
		given(scmService.createScm(scm)).willReturn(chkResponse);
		scmController.setScmService(scmService);
		//scmController.setGitValidatorImpl(gitValidatorImpl);
		ScmResponse result = scmController.createScm(scm);
		assertEquals("SUCCESS", result.getStatus());
	}

	@Test
	public void updateScmTest() {
		ScmResponse chkResponse = new ScmResponse();
		ScmController scmController = new ScmController();
		SCM scm = new SCM();
		scm.setName("github");
		scm.setRepoBranch("*/master");
		Response response = new Response();
		chkResponse.setStatus("SUCCESS");
		response.setStatus("SUCCESS");
		//given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(response);
		given(scmService.updateScm(scm)).willReturn(chkResponse);
		scmController.setScmService(scmService);
		//scmController.setGitValidatorImpl(gitValidatorImpl);
		ScmResponse result = scmController.updateScm(scm, scm.getName());
		assertEquals("SUCCESS", result.getStatus());
	}

	@Test
	public void getAllScmTest() {

		ScmResponse chkResponse = new ScmResponse();
		ScmController scmController = new ScmController();
		chkResponse.setStatus("SUCCESS");
		given(scmService.getAllScm()).willReturn(chkResponse);
		scmController.setScmService(scmService);
		ScmResponse result = scmController.getAllScm();
		assertEquals("SUCCESS", result.getStatus());
	}

	@Test
	public void getOneScmTest() {

		ScmResponse chkResponse = new ScmResponse();
		ScmController scmController = new ScmController();
		chkResponse.setStatus("SUCCESS");
		given(scmService.getOneScm("Test")).willReturn(chkResponse);
		scmController.setScmService(scmService);
		ScmResponse result = scmController.getOneScm("Test");
		assertEquals("SUCCESS", result.getStatus());
	}

	@Test
	public void deleteScmTest() {

		ScmResponse chkResponse = new ScmResponse();
		ScmController scmController = new ScmController();
		chkResponse.setStatus("SUCCESS");
		given(scmService.deleteScm("Test")).willReturn(chkResponse);
		scmController.setScmService(scmService);
		ScmResponse result = scmController.deleteScm("Test");
		assertEquals("SUCCESS", result.getStatus());
	}

	@Test
	public void validateScmTest() {

		Response chkResponse = new Response();
		ScmController scmController = new ScmController();
		SCM scm = new SCM();
		scm.setName("Test");
		scm.setRepoBranch("Test");
		scm.setAuthType("üp");
		scm.setCredentialID("Test");
		scm.setLink("Test");
		chkResponse.setStatus("SUCCESS");
		given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(chkResponse);
		scmController.setScmService(scmService);
		scmController.setGitValidatorImpl(gitValidatorImpl);
		Response result = scmController.validateScm(scm);
		assertEquals("SUCCESS", result.getStatus());
	}
}
