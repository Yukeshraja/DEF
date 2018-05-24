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

import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.model.ScmResponse;
import com.altimetrik.def.repository.ScmRepository;
import com.altimetrik.def.serviceImpl.GitValidatorImpl;
import com.altimetrik.def.serviceImpl.ScmServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ScmServiceImplTest {

	private static Logger logger = LoggerFactory.getLogger(ScmServiceImplTest.class);

	@Mock
	private ScmRepository scmRepository;
	@Mock
	private GitValidatorImpl gitValidatorImpl;

	@Test
	public void createScmTestSuccess() {

		ScmResponse response = new ScmResponse();
		Response chkResponse = new Response();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		chkResponse.setStatus("SUCCESS");
		given(scmRepository.save(scm)).willReturn(scm);
		given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(chkResponse);
		scmServiceImpl.setScmRepository(scmRepository);
		scmServiceImpl.setGitValidatorImpl(gitValidatorImpl);
		response = scmServiceImpl.createScm(scm);
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void createScmTestFailure() {

		ScmResponse response = new ScmResponse();
		Response chkResponse = new Response();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		chkResponse.setStatus("FAILURE");
		given(scmRepository.save(scm)).willThrow(NullPointerException.class);
		given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(chkResponse);
		scmServiceImpl.setScmRepository(scmRepository);
		scmServiceImpl.setGitValidatorImpl(gitValidatorImpl);
		response = scmServiceImpl.createScm(scm);
		assertEquals("FAILURE", response.getStatus());
	}

	@Test
	public void updateScmTestSuccess() {

		ScmResponse response = new ScmResponse();
		Response chkResponse = new Response();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		chkResponse.setStatus("SUCCESS");
		given(scmRepository.save(scm)).willReturn(scm);
		given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(chkResponse);
		scmServiceImpl.setScmRepository(scmRepository);
		scmServiceImpl.setGitValidatorImpl(gitValidatorImpl);
		response = scmServiceImpl.updateScm(scm);
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void updateScmTestFailure() {

		ScmResponse response = new ScmResponse();
		Response chkResponse = new Response();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		chkResponse.setStatus("FAILURE");
		given(scmRepository.save(scm)).willThrow(NullPointerException.class);
		given(gitValidatorImpl.chkDetailsValidation(scm)).willReturn(chkResponse);
		scmServiceImpl.setScmRepository(scmRepository);
		scmServiceImpl.setGitValidatorImpl(gitValidatorImpl);
		response = scmServiceImpl.updateScm(scm);
		assertEquals("FAILURE", response.getStatus());
	}

	@Test
	public void getAllScmSuccessTest() {

		ScmResponse response = new ScmResponse();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		List<SCM> scmList = new ArrayList<SCM>();
		given(scmRepository.findAll()).willReturn(scmList);
		scmServiceImpl.setScmRepository(scmRepository);
		response = scmServiceImpl.getAllScm();
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void getAllScmFailureTest() {

		ScmResponse response = new ScmResponse();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		List<SCM> scmList = new ArrayList<SCM>();
		given(scmRepository.findAll()).willThrow(NullPointerException.class);
		scmServiceImpl.setScmRepository(scmRepository);
		response = scmServiceImpl.getAllScm();
		assertEquals("FAILURE", response.getStatus());
	}

	@Test
	public void getOneScmSuccessTest() {

		ScmResponse response = new ScmResponse();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		given(scmRepository.findOne("Test")).willReturn(scm);
		scmServiceImpl.setScmRepository(scmRepository);
		response = scmServiceImpl.getOneScm("Test");
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void getOneScmFailureTest() {

		ScmResponse response = new ScmResponse();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		given(scmRepository.findOne("Test")).willThrow(NullPointerException.class);
		scmServiceImpl.setScmRepository(scmRepository);
		response = scmServiceImpl.getOneScm("Test");
		assertEquals("FAILURE", response.getStatus());
	}

	@Test
	public void deleteScmSuccessTest() {

		ScmResponse response = new ScmResponse();
		ScmServiceImpl scmServiceImpl = new ScmServiceImpl();
		SCM scm = new SCM();
		doNothing().when(scmRepository).delete("Test");
		;
		scmServiceImpl.setScmRepository(scmRepository);
		response = scmServiceImpl.deleteScm("Test");
		assertEquals("SUCCESS", response.getStatus());
	}
}
