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

import com.altimetrik.def.controller.CredentialMgmtController;
import com.altimetrik.def.model.Credential;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.serviceImpl.CredentialMgmtImpl;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CredentialMgmtTest {

	private static final Logger logger = LoggerFactory.getLogger(CredentialMgmtTest.class);
	
	@Mock
	private CredentialMgmtImpl credenTialService;
	
	
	@Test
	public void createCredentialsTest(){
	
		CredentialMgmtController credMgmt = new CredentialMgmtController();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		CredentialResponse credentialResponse = new CredentialResponse();
		credentialResponse.setStatus("SUCCESS");
		given(credenTialService.dataValidation(credential)).willReturn(credentialResponse);
		given(credenTialService.createCredentials(credential)).willReturn(credentialResponse);
		credMgmt.setCredentialService(credenTialService);
		CredentialResponse response = credMgmt.createCredentials(credential);
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void fetchAllCredentialsTest(){
		CredentialMgmtController credMgmt = new CredentialMgmtController();
		Credential credential = new Credential();
		CredentialResponse credentialResponse = new CredentialResponse();
		given(credenTialService.fetchAllCredentials()).willReturn(credentialResponse);
		credMgmt.setCredentialService(credenTialService);
		credentialResponse.setStatus("SUCCESS");
		CredentialResponse response = credMgmt.fetchAllCredentials();
		assertEquals("SUCCESS", response.getStatus());
	}
	
	@Test
	public void fetchOneCredentialsTest(){
		
		CredentialMgmtController credMgmt = new CredentialMgmtController();
		Credential credential = new Credential();
		CredentialResponse credentialResponse = new CredentialResponse();
		credentialResponse.setStatus("SUCCESS");
		given(credenTialService.fetchOneCredentials("Test")).willReturn(credentialResponse);
		credMgmt.setCredentialService(credenTialService);
		CredentialResponse response = credMgmt.fetchOneCredentials("Test");
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void updateJobTest(){
		CredentialMgmtController credMgmt = new CredentialMgmtController();
		Credential credential = new Credential();
		credential.setCredentialName("Testing");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		CredentialResponse credentialResponse = new CredentialResponse();
		credentialResponse.setStatus("SUCCESS");
		given(credenTialService.dataValidation(credential)).willReturn(credentialResponse);
		given(credenTialService.updateCredentials(credential)).willReturn(credentialResponse);
		credMgmt.setCredentialService(credenTialService);
		CredentialResponse response = credMgmt.updateCredential(credential, "Test");
		assertEquals("SUCCESS", response.getStatus());
	}
	
	@Test
	public void deleteCredentialTest(){
		
		CredentialMgmtController credMgmt = new CredentialMgmtController();
		Credential credential = new Credential();
		CredentialResponse credentialResponse = new CredentialResponse();
		credentialResponse.setStatus("SUCCESS");
		given(credenTialService.deleteCredentials("Test")).willReturn(credentialResponse);
		credMgmt.setCredentialService(credenTialService);
		CredentialResponse response = credMgmt.deleteCredential("Test");
		assertEquals("SUCCESS", response.getStatus());
	}
	
}
