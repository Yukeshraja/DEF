package com.altimetrik.def.serviceImpl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetrik.def.model.BuildFaces;
import com.altimetrik.def.model.Buildpacks;
import com.altimetrik.def.model.CD;
import com.altimetrik.def.model.Def;
import com.altimetrik.def.model.Jenkins;
import com.altimetrik.def.model.JobParams;
import com.altimetrik.def.model.Response;
import com.altimetrik.def.model.SCM;
import com.altimetrik.def.repository.JenkinsRepository;
import com.altimetrik.def.service.DefLoader;
import com.altimetrik.def.service.DefService;
import com.altimetrik.def.serviceImpl.DefServiceImpl;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.offbytwo.jenkins.JenkinsServer;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DefServiceImplTest {

	
   @Mock private DefLoader defLoader;
	 

	@Mock
	private JenkinsRepository defMongoRepository;

	@Mock
	private Handlebars handlebars;

	@Mock
	private Jenkins jenkins;

	@Mock
	private DefService defService;

	@Mock
	private Template template;

	@Mock
	private Context context;

	@Mock
	private JenkinsServer jenkinsServer;

	@Test
	public void getAllJObTestSuccess() {

		Def def = new Def();
		List<Def> defList = new ArrayList<>();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setName("Testing");
		defList.add(def);
		given(defMongoRepository.findAll()).willReturn(defList);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams allResponse = defServiceImpl.getAllJOb();
		assertEquals("SUCCESS", allResponse.getReturnValue().getStatus());
	}

	@Test
	public void getAllJObTestFailure() {

		Def def = new Def();
		List<Def> defList = new ArrayList<>();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setName("Testing");
		defList.add(def);
		given(defMongoRepository.findAll()).willThrow(NullPointerException.class);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams allResponse = defServiceImpl.getAllJOb();
		assertEquals("FAILURE", allResponse.getReturnValue().getStatus());

	}

	@Test
	public void getOneJobDetailsSuccess() {
		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setName("Testing");
		given(defMongoRepository.findOne(def.getName())).willReturn(def);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams singleResponse = defServiceImpl.getOneJobDetails(def.getName());
		assertEquals("SUCCESS", singleResponse.getReturnValue().getStatus());

	}

	@Test
	public void getOneJobDetailsFailure() {
		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setName("Testing");
		given(defMongoRepository.findOne(def.getName())).willThrow(NullPointerException.class);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams singleResponse = defServiceImpl.getOneJobDetails(def.getName());
		assertEquals("FAILURE", singleResponse.getReturnValue().getStatus());

	}

	@Test
	public void insertDetailsSuccessTest() {

		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		JobParams jobParams = new JobParams();
		Date date = new Date();
		def.setName("Test Project");
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		response.setErrorMessage("No Error");
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(defMongoRepository.save(def)).willReturn(def);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams resultResponse = defServiceImpl.insertDetails(jobParams);
		assertEquals("SUCCESS", resultResponse.getReturnValue().getStatus());
	}

	@Test
	public void insertDetailsFailureTest() {

		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		JobParams jobParams = new JobParams();
		Date date = new Date();
		def.setName("Test Project");
		Response response = new Response();
		response.setStatus("FAILURE");
		response.setTimestamp(date.getTime());
		response.setErrorMessage("Error");
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(defMongoRepository.save(def)).willThrow(NullPointerException.class);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams resultResponse = defServiceImpl.insertDetails(jobParams);
		assertEquals("FAILURE", resultResponse.getReturnValue().getStatus());
	}

	@Test
	public void insertDetailsFailureBadRequestTest() {

		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		JobParams jobParams = new JobParams();
		Date date = new Date();
		def.setName("Test Project");
		Response response = new Response();
		response.setStatus("FAILURE");
		response.setTimestamp(date.getTime());
		response.setErrorMessage("Bad Request");
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(defMongoRepository.save(def)).willReturn(def);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams resultResponse = defServiceImpl.insertDetails(jobParams);
		assertEquals("FAILURE", resultResponse.getReturnValue().getStatus());
	}

	@Test
	public void updateDetailsSuccessTest() {

		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		JobParams jobParams = new JobParams();
		Date date = new Date();
		def.setName("Test Project");
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setTimestamp(date.getTime());
		response.setErrorMessage("No Error");
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(defMongoRepository.save(def)).willReturn(def);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams resultResponse = defServiceImpl.updateDetails(jobParams);
		assertEquals("SUCCESS", resultResponse.getReturnValue().getStatus());
	}

	@Test
	public void updateDetailsFailureTest() {

		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		JobParams jobParams = new JobParams();
		Date date = new Date();
		def.setName("Test Project");
		Response response = new Response();
		response.setStatus("FAILURE");
		response.setTimestamp(date.getTime());
		response.setErrorMessage("Error");
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(defMongoRepository.save(def)).willThrow(NullPointerException.class);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams resultResponse = defServiceImpl.updateDetails(jobParams);
		assertEquals("FAILURE", resultResponse.getReturnValue().getStatus());
	}

	@Test
	public void updateDetailsFailureBadRequestTest() {

		Def def = new Def();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		JobParams jobParams = new JobParams();
		Date date = new Date();
		def.setName("Test Project");
		Response response = new Response();
		response.setStatus("FAILURE");
		response.setTimestamp(date.getTime());
		response.setErrorMessage("Bad Request");
		jobParams.setDefInputValue(def);
		jobParams.setReturnValue(response);
		given(defMongoRepository.save(def)).willReturn(def);
		defServiceImpl.setDefMongoRepository(defMongoRepository);
		JobParams resultResponse = defServiceImpl.updateDetails(jobParams);
		assertEquals("FAILURE", resultResponse.getReturnValue().getStatus());
	}

	@Test
	public void nullChkJenkinsJobNameFail() {

		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		CD cd = new CD();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		// def.setName("Test Project");
		def.setStatus("FAILURE");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setCd(cd);
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildPackNameFail() {

		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		// buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildPackBuildToolFail() {

		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		// buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildPackPomXmlFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		// buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobScmNameFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		// scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobScmLinkFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		// scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobScmRepoFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		// scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildFaceMavenLifeCycleFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(false);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(false);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(false);
		buildFaces.setDockerBuild(true);
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildFaceDockerAndDockerBuildBothTrueFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(true);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildFaceDockerTrueDockerImageNameFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		// buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobBuildFaceDockerTrueDockerFilePathFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		// buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobCdAppTargetPortNumberFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		// cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobCdCredentialFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		// cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobConnectToFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		//def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}
	
	@Test
	public void nullChkJenkinsJobDeploymentServerFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		// cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobDockerExposerPortFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		// cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobImgRegistryServerFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		// cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobImgRegistryPortFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		// cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobJenkinsURLFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		// jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsJobArtifactoryUrlFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		// jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsTargetReleaseSynataxFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		// jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsTargetSnapshotSyntaxFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		// jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}

	@Test
	public void nullChkJenkinsCredentialIDFail() {
		Def def = new Def();
		Response response = new Response();
		Buildpacks buildpacks = new Buildpacks();
		SCM scm = new SCM();
		CD cd = new CD();
		Jenkins jenkins = new Jenkins();
		BuildFaces buildFaces = new BuildFaces();
		DefServiceImpl defServiceImpl = new DefServiceImpl();
		def.setStatus("FAILURE");
		def.setName("Test Project");
		buildpacks.setName("Java");
		buildpacks.setBuildtool("Maven");
		buildpacks.setPomXml("pom.xml");
		scm.setName("Github");
		scm.setLink("scmLink");
		scm.setRepoBranch("master");
		scm.setAuthType("up");
		scm.setCredentialID("2");
		buildFaces.setValidate(true);
		buildFaces.setClean(false);
		buildFaces.setCompile(false);
		buildFaces.setBuild(false);
		buildFaces.setDeploy(false);
		buildFaces.set_package(true);
		buildFaces.setInstall(false);
		buildFaces.setTest(false);
		buildFaces.setDeploy(false);
		buildFaces.setDocker(true);
		buildFaces.setDockerBuild(false);
		buildFaces.setDockerImageName("dockerImage");
		buildFaces.setDockerFilePath("dockerFilePath");
		cd.setAppTargetPortNumber("1");
		cd.setAuthType("up");
		cd.setCredentialID("2");
		cd.setDeploymentServer("192.168.2.1");
		cd.setDeployTo("docker");
		cd.setDockerExposedPortNumber("8080");
		cd.setDockerImgRegistryPort("8081");
		cd.setDockerImgRegistryServer("192.168.2.2");
		def.setBuildpacks(buildpacks);
		def.setScm(scm);
		def.setBuildFaces(buildFaces);
		def.setConnectTo("jenkins");
		jenkins.setJenkinsURL("jenkinsUrl");
		jenkins.setJenkinsUsername("jenkinsUserName");
		jenkins.setJenkinsPassword("jenkinsPassword");
		jenkins.setArtifactoryUrl("artifactoryUrl");
		jenkins.setTargetReleaseSynatax("targetReleaseSyntax");
		jenkins.setTargetSnapshotSyntax("targetSnapshotSyntax");
		jenkins.setAuthType("up");
		// jenkins.setCredentialID("2");
		def.setCd(cd);
		def.setJenkins(jenkins);
		Response resResponse = defServiceImpl.nullChkJenkinsJob(def);
		assertEquals("FAILURE", resResponse.getStatus());
	}
}