/*package com.altimetrik.def.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.altimetrik.def.controller.DefController;
import com.altimetrik.def.model.CDResponse;
import com.altimetrik.def.model.CiModelResponse;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.ScmResponse;
import com.altimetrik.def.service.CDService;
import com.altimetrik.def.service.CiService;
import com.altimetrik.def.service.DefLoader;
import com.altimetrik.def.service.DefService;
import com.altimetrik.def.service.ScmService;
import com.altimetrik.def.serviceImpl.DefServiceImpl;
import com.altimetrik.def.serviceImpl.DefValidatorImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DefControllerTest {

	private static Logger logger = LoggerFactory.getLogger(DefControllerTest.class);

	@Mock
	private DefService defservice;
	
	@Mock
	private DefLoader  defLoader;
	
	@Mock
	private ScmService scmService;
	
	@Mock
	private CiService ciService;
	
	@Mock
	private CDService cDService;
	
	@Mock
	DefValidatorImpl defValidatorImpl;

	@Test
	public void createJobTestSuccess()
			throws JsonParseException, JsonMappingException, IOException, ParseException, URISyntaxException {
		JobParams jobParams = new JobParams();
		DefController defController = new DefController();
		HttpServletRequest httpRequest = new MockHttpServletRequest();
		HttpServletResponse httpResponse = new MockHttpServletResponse();
		CiModelResponse ciResponse = new CiModelResponse();
		ScmResponse scmResponse = new ScmResponse();
		CDResponse cDResponse = new CDResponse();
		Response response = new Response();
		Def def = new Def();
		def.setName("Test Project");
		response.setStatus("SUCCESS");
		Date date = new Date();
		response.setTimestamp(date.getTime());
		jobParams.setReturnValue(response);
		given(defValidatorImpl.Validation(jobParams.getDefInputValue())).willReturn(response);
		given(scmService.getOneScm("Test")).willReturn(scmResponse);
		given(ciService.getOneCi("Test")).willReturn(ciResponse);
		given(cDService.getOneCd("Test")).willReturn(cDResponse);
		given(defLoader.createJob(jobParams)).willReturn(jobParams);
		defController.setDefValidatorImpl(defValidatorImpl);
		defController.setcDService(cDService);
		defController.setCiService(ciService);
		defController.setScmService(scmService);
		defController.setDefLoader(defLoader);
		defController.setDefService(defservice);
		Response resDetails = defController.createJob(httpRequest, httpResponse, def);
		assertEquals("SUCCESS", resDetails.getStatus());
	}

	@Test
	public void getAllJobTestSuccess() throws NotFoundException, URISyntaxException {
		DefController defController = new DefController();
		Date date = new Date();

		JobParams jobParams = new JobParams();
		Response response = new Response();

		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		jobParams.setReturnValue(response);
		given(defservice.getAllJOb()).willReturn(jobParams);
		defController.setDefService(defservice);
		Response resDefDetails = defController.getAllJob();
		assertEquals("SUCCESS", resDefDetails.getStatus());
	}
	
	@Test
	public void getAllJobTestFailure() throws NotFoundException, URISyntaxException {
		DefController defController = new DefController();
		Date date = new Date();

		JobParams jobParams = new JobParams();
		Response response = new Response();

		response.setStatus("FAILURE");
		response.setTimestamp(date.getTime());
		jobParams.setReturnValue(response);
		given(defservice.getAllJOb()).willReturn(jobParams);
		defController.setDefService(defservice);
		Response resDefDetails = defController.getAllJob();
		assertEquals("FAILURE", resDefDetails.getStatus());
	}
	
	@Test
	public void getOneJobDetailsTestsSuccess() throws NotFoundException, URISyntaxException {

		DefController defController = new DefController();
		Def def = new Def();
		Date date = new Date();
		JobParams jobParams = new JobParams();
		Response response = new Response();
		response.setStatus("SUCCESS");
		def.setName("Test Project");
		List<Def> defDetails = new ArrayList<Def>();
		//List<Def> resDefDetails = null;
		defDetails.add(def);
		jobParams.setReturnValue(response);
		given(defservice.getOneJobDetails("Test Project")).willReturn(jobParams);
		defController.setDefService(defservice);
		Response chkResponse = defController.getOneJobDetails("Test Project");
		assertEquals("SUCCESS", chkResponse.getStatus());
		//assertNotNull(resDefDetails);
		given(defserviceImpl.getOneJobDetails("Test")).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resResponse = defController.getOneJobDetails("Test");
		assertEquals("SUCCESS", resResponse.getStatus());
	}

	
	
	
	@Test
	public void getAllJobTestFailure() throws URISyntaxException {

		DefController defController = new DefController();
		Date date = new Date();

		Response response = new Response();

		response.setStatus("FAILURE");
		response.setTimestamp(date.getTime());
		given(defserviceImpl.getAllJOb()).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resDefDetails = defController.getAllJob();
		assertEquals("FAILURE", resDefDetails.getStatus());
	}

	@Test
	public void getOneJobDetailsTestsSuccess() throws NotFoundException, URISyntaxException {

		DefController defController = new DefController();
		Date date = new Date();
		Response response = new Response();
		response.setStatus("SUCCESS");
		List<Def> defDetails = new ArrayList<Def>();
		List<Def> resDefDetails = null;
		defDetails.add(def);
		given(defserviceImpl.getAllJOb()).willReturn(response);
		defController.setDefService(defserviceImpl);
		assertEquals("SUCCESS", actual);
		assertNotNull(resDefDetails);
		given(defserviceImpl.getOneJobDetails("Test")).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resResponse = defController.getOneJobDetails("Test");
		assertEquals("SUCCESS", resResponse.getStatus());
	}

	@Test
	public void getOneJobDetailsTestsFailure() throws NotFoundException, URISyntaxException {

		DefController defController = new DefController();
		Date date = new Date();
		Response response = new Response();
		response.setStatus("FAILURE");
		List<Def> defDetails = new ArrayList<Def>();
		List<Def> resDefDetails = null;
		defDetails.add(def);
		given(defserviceImpl.getAllJOb()).willReturn(response);
		defController.setDefService(defserviceImpl);
		assertEquals("SUCCESS", actual);
		assertNotNull(resDefDetails);
		given(defserviceImpl.getOneJobDetails("Test")).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resResponse = defController.getOneJobDetails("Test");
		assertEquals("FAILURE", resResponse.getStatus());
	}
	
	@Test
	public void createJobTestFailure()
			throws JsonParseException, JsonMappingException, IOException, ParseException, URISyntaxException {

		DefController defController = new DefController();
		HttpServletRequest httpRequest = new MockHttpServletRequest();
		HttpServletResponse httpResponse = new MockHttpServletResponse();
		Def def = new Def();
		Response response = new Response();
		def.setName("Test-Project");
		response.setStatus("FAILURE");
		given(defserviceImpl.nullChkJenkinsJob(def)).willReturn(response);
		given(defserviceImpl.createJob(def)).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resDefDetails = defController.createJob(httpRequest, httpResponse, def);
		assertEquals("FAILURE", resDefDetails.getStatus());

	}

	@Test

	public void updateJobTestSuccess()
			throws JsonParseException, JsonMappingException, IOException, URISyntaxException {

		DefController defController = new DefController();
		HttpServletRequest httpRequest = new MockHttpServletRequest();
		HttpServletResponse httpResponse = new MockHttpServletResponse();
		Def def = new Def();
		Response response = new Response();
		def.setName("Test-Project");
		response.setStatus("SUCCESS");
		given(defserviceImpl.nullChkJenkinsJob(def)).willReturn(response);
		given(defserviceImpl.updateJob(def, def.getName())).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resDefDetails = defController.updateJenkinsJob(httpRequest, httpResponse, def, def.getName());
		assertEquals("SUCCESS", resDefDetails.getStatus());

	}

	@Test

	public void updateJobTestFailure()
			throws JsonParseException, JsonMappingException, IOException, URISyntaxException {

		DefController defController = new DefController();
		HttpServletRequest httpRequest = new MockHttpServletRequest();
		HttpServletResponse httpResponse = new MockHttpServletResponse();
		Def def = new Def();
		Response response = new Response();
		def.setName("Test-Project");
		response.setStatus("FAILURE");
		given(defserviceImpl.nullChkJenkinsJob(def)).willReturn(response);
		given(defserviceImpl.updateJob(def, def.getName())).willReturn(response);
		defController.setDefService(defserviceImpl);
		Response resDefDetails = defController.updateJenkinsJob(httpRequest, httpResponse, def, def.getName());
		assertEquals("FAILURE", resDefDetails.getStatus());

	}

}*/