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

import com.altimetrik.def.controller.test.CredentialMgmtTest;
import com.altimetrik.def.model.Credential;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.repository.DefCredentialRepository;
import com.altimetrik.def.serviceImpl.CredentialMgmtImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CredentialsMgmtImplTest {

	private static final Logger logger = LoggerFactory.getLogger(CredentialMgmtTest.class);

	@Mock
	private DefCredentialRepository credentialRepository;

	@Test
	public void createCredentialsTestSuccess() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		// CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		// credentialResponse.setStatus("SUCCESS");
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		given(credentialRepository.save(credential)).willReturn(credential);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.createCredentials(credential);
		assertEquals("SUCCESS", response.getStatus());
		// assertEquals("Test", response);
	}

	@Test
	public void createCredentialsTestFailure() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		// CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		// credentialResponse.setStatus("FAILURE");
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		given(credentialRepository.save(credential)).willThrow(NullPointerException.class);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.createCredentials(credential);
		assertEquals("FAILURE", response.getStatus());
		// assertEquals("Test", response);
	}

	@Test
	public void fetchAllCredentialsTestSuccess() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("TestPassword");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		List<Credential> credentialList = new ArrayList<Credential>();
		credentialList.add(credential);
		given(credentialRepository.findAll()).willReturn(credentialList);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.fetchAllCredentials();
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void fetchAllCredentialsTestFailure() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("TestPassword");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		List<Credential> credentialList = new ArrayList<Credential>();
		credentialList.add(credential);
		given(credentialRepository.findAll()).willThrow(NullPointerException.class);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.fetchAllCredentials();
		assertEquals("FAILURE", response.getStatus());
	}

	@Test

	public void fetchOneCredentialsTestSuccess() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("TestPassword");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		given(credentialRepository.findOne(credential.getCredentialName())).willReturn(credential);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.fetchOneCredentials(credential.getCredentialName());
		assertEquals("SUCCESS", response.getStatus());
	}

	@Test
	public void fetchOneCredentialsTestFailure() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("TestPassword");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		given(credentialRepository.findOne(credential.getCredentialName())).willThrow(NullPointerException.class);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.fetchOneCredentials(credential.getCredentialName());
		assertEquals("FAILURE", response.getStatus());
	}

	@Test
	public void updateCredentialsTestSuccess() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		// CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		// credentialResponse.setStatus("SUCCESS");
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		given(credentialRepository.save(credential)).willReturn(credential);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.updateCredentials(credential);
		assertEquals("SUCCESS", response.getStatus());
		// assertEquals("Test", response);
	}

	@Test
	public void updateCredentialsTestFailure() {

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		// CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		// credentialResponse.setStatus("FAILURE");
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		given(credentialRepository.save(credential)).willThrow(NullPointerException.class);
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse response = credentialsService.updateCredentials(credential);
		assertEquals("FAILURE", response.getStatus());
		// assertEquals("Test", response);
	}
	
	
	@Test
	public void deleteCredentialsSuccess(){

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		// CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		doNothing().when(credentialRepository).delete(credential.getCredentialName());
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse credentialResponse = credentialsService.deleteCredentials(credential.getCredentialName());
		assertEquals("SUCCESS", credentialResponse.getStatus());
		
	}
	
/*	@Test
	public void deleteCredentialsFailure(){

		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		// CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		//credential.setCredentialName("Test");
		doNothing().when(credentialRepository).delete(credential.getCredentialName());
		credentialsService.setCredentialRepository(credentialRepository);
		CredentialResponse credentialResponse = credentialsService.deleteCredentials(credential.getCredentialName());
		assertEquals("FAILURE", credentialResponse.getStatus());
		
	}*/
	
	@Test
	public void dataValidationTestCredentialNameSuccess(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("SUCCESS", response.getStatus());		
	}
	
	@Test
	public void dataValidationTestCredentialNameFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		//credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
	@Test
	public void dataValidationTestCredentialSelectTypeFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		//credential.setType("Test  Type");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
	@Test
	public void dataValidationTestCredentialSelectTypeUpUserNameFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("up");
		//credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
	@Test
	public void dataValidationTestCredentialSelectTypeUpPasswordFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		//credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("up");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
	/*@Test
	public void dataValidationTestCredentialSelectTypeUpsUserNameFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("ups");
		//credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
	@Test
	public void dataValidationTestCredentialSelectTypeUpsPassowrdFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		//credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("ups");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}*/
	
	@Test
	public void dataValidationTestCredentialSelectTypeUpsSshKeyFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		credential.setSecretKey("Test Sec Key");
		//credential.setSshKey("Test SSH Key");
		credential.setType("ups");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
	
	@Test
	public void dataValidationTestCredentialSelectTypeUpssSecretKeyFailure(){
		CredentialMgmtImpl credentialsService = new CredentialMgmtImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential();
		credential.setCredentialName("Test");
		credential.setPassword("Test Password");
		//credential.setSecretKey("Test Sec Key");
		credential.setSshKey("Test SSH Key");
		credential.setType("upss");
		credential.setUserName("Test User Name");
		credentialResponse.setStatus("SUCCESS");
		//given(credentialsService.dataValidation(credential)).willReturn(credentialResponse);
		CredentialResponse response = credentialsService.dataValidation(credential);
		assertEquals("FAILURE", response.getStatus());		
	}
	
}
