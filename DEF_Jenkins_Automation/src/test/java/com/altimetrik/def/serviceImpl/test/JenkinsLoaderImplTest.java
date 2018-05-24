package com.altimetrik.def.serviceImpl.test;
/*package com.jenkins.serviceImpl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.model.Credential;
import com.altimetrik.def.model.CredentialResponse;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.ServerObj;
import com.altimetrik.def.serviceImpl.CredentialMgmtImpl;
import com.altimetrik.def.serviceImpl.JenkinsLoaderImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.offbytwo.jenkins.JenkinsServer;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class JenkinsLoaderImplTest {

	@Mock
	private Template template;
	
	@Mock
	private Handlebars handlebars;

	@Mock
	private TemplateLoader templateLoader;
	
	@Mock
	private Context context;
	
	@Mock
	private CredentialMgmtImpl credentialMgmtImpl;
	
	@Mock
	private JenkinsServer jenkinsServer;
	
	@Mock
	private ServerObj serverObj;
	
	@Test
	public void createJobTestSuccess() {
		Date date = new Date();
		JobParams jobParams = new JobParams();
		Def def = new Def();
		def.setName("Test Project");
		Response response = new Response();
		JenkinsLoaderImpl jenkinsLoaderImpl = new JenkinsLoaderImpl();
		CredentialResponse credentialResponse = new CredentialResponse();
		Credential credential = new Credential(); 
		Jenkins jenkins = new Jenkins();
		credential.setCredentialName("credName");
		credential.setUserName("userName");
		credential.setPassword("password");
		
		credentialResponse.setStatus("SUCCESS");
		credentialResponse.setTimestamp(date.getTime());
		credentialResponse.setErrorMessage("No Error");
		response.setStatus("SUCCESS");
		response.setErrorMessage("No Error");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("credName");
		def.setJenkins(jenkins);
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		JSONObject jsonObject = new JSONObject(jobParams.getDefInputValue());
		String value = jsonObject.toString(4);
		JsonNode jsonNode = null;
		try {
			jsonNode = new ObjectMapper().readValue(value, JsonNode.class);
		} catch (JsonParseException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		} catch (JsonMappingException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		}
		String finalTemplate = null;
		try {
			finalTemplate = template.apply(context);
			
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			response.setStatus("FAILURE");
			response.setErrorMessage(exception.getMessage());
		}
		
		try{
			
			given(credentialMgmtImpl.fetchOneCredentials("credName")).willReturn(credentialResponse);
			//ServerObj jenkinsServerObj = null;
			//jenkinsServerObj = jenkinsLoaderImpl.jenkinsConnection(jobParams.getDefInputValue().getJenkins().getJenkinsURL(), credentialResponse.getResult().get(0).getUserName(), credentialResponse.getResult().get(0).getPassword());
			//jenkinsServer.createJob("jobName", "fileBody", false);
			//JenkinsServer jenkins1 = (JenkinsServer) jenkinsServer.getServer();
			given(serverObj.getServer()).willReturn(jenkinsServer);
			doNothing().when(jenkinsServer).createJob("jobName", "fileBody", false);
			jenkinsLoaderImpl.setCredentialMgmtImpl(credentialMgmtImpl);
		}catch(Exception exception){
			
		}
		JobParams responseParams = jenkinsLoaderImpl.createJob(jobParams);
		assertEquals("SUCCESS", responseParams.getReturnValue().getStatus());
	}
}
*/