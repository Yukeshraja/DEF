package com.altimetrik.def.jenkins.service;

import java.io.File;
import java.net.URI;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.altimetrik.def.jenkins.model.AllInputs;
import com.altimetrik.def.jenkins.model.CreateJob;
import com.altimetrik.def.jenkins.model.JenkinsFileInputs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import com.offbytwo.jenkins.JenkinsServer;

public class JenkinsMain {
	
	static String fileNameTemplateJenkinsconfig="src/main/resources/template/config_jenkins_template.xml";
	static String fileNameTemplateFullconfig="src/main/resources/template/full_config_template.xml";
	
	static String fileNameYAMLJenkinsconfig="src/main/resources/input/inputJenkinsData.yaml";
	static String fileNameYAMLFullconfig="src/main/resources/input/inputFullData.yaml";
	
	static String fileNameOutput="src/main/resources/output/config_out.xml";
	
	static String fileNameYAMLCreateJob="src/main/resources/input/createJobInput.yaml";
	//static String fileNameOutput="src/main/resources/output/config_out.xml";
	
	public static void main(String[] args) throws Exception {

		
		//--if(JenkinsFile  Mode) then below call
			updateXML(fileNameTemplateJenkinsconfig,true);
		//if(full Config files  Mode) then below two lines
			//updateXML(fileNameTemplateFullconfig,false);
			
			createjob(fileNameOutput);
			System.out.println("Job Created");
	}

	static void updateXML(String fileNameTemplateJenkinsconfig,boolean jenkinsFlag) throws Exception{
			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileNameTemplateJenkinsconfig);
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node node = null;
			
			if(!jenkinsFlag){	
				AllInputs helperObject=fillHelperYAMLFullConfig();
				
				node = (Node) xpath.evaluate("//scm/userRemoteConfigs/hudson.plugins.git.UserRemoteConfig/url", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getUrl());
				node = (Node) xpath.evaluate("//scm/branches/hudson.plugins.git.BranchSpec/name", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getName());			
				node = (Node) xpath.evaluate("//triggers/hudson.triggers.TimerTrigger/spec", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getTrigger());
				
				node = (Node) xpath.evaluate("//builders/hudson.tasks.Maven/targets", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getTargets());
				node = (Node) xpath.evaluate("//builders/hudson.tasks.Maven/mavenName", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getMavenName());
				node = (Node) xpath.evaluate("//builders/hudson.tasks.Maven/jvmOptions", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getJvmOptions());
				node = (Node) xpath.evaluate("//builders/hudson.tasks.Maven/pom", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getPom());
				node = (Node) xpath.evaluate("//builders/hudson.tasks.Maven/usePrivateRepository", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getUsePrivateRepository());
				
				node = (Node) xpath.evaluate("//builders/hudson.tasks.Maven/settings/path", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getPath());
				
				node = (Node) xpath.evaluate("//publishers/hudson.plugins.sonar.SonarPublisher/mavenOpts", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getSonarMavenOpts());
				node = (Node) xpath.evaluate("//publishers/hudson.plugins.sonar.SonarPublisher/rootPom", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getUsePrivateRepository());
				node = (Node) xpath.evaluate("//publishers/hudson.plugins.sonar.SonarPublisher/usePrivateRepository", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getUsePrivateRepository());
				node = (Node) xpath.evaluate("//publishers/hudson.plugins.emailext.ExtendedEmailPublisher/recipientList", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getUsePrivateRepository());
			}
			else{
				JenkinsFileInputs helperObject=fillHelperYAMLJenkins();
				
				node = (Node) xpath.evaluate("//flow-definition/definition/scm/userRemoteConfigs/hudson.plugins.git.UserRemoteConfig/url", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getUrl());
				
				node = (Node) xpath.evaluate("//flow-definition/definition/scm/branches/hudson.plugins.git.BranchSpec/name", doc,
					    XPathConstants.NODE);
				node.setTextContent(helperObject.getName());
				
				node = (Node) xpath.evaluate("//flow-definition/properties/org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty/triggers/hudson.triggers.TimerTrigger/spec", doc,
						XPathConstants.NODE);
				node.setTextContent(helperObject.getTrigger());
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File(fileNameOutput));
			transformer.transform(source, result);

			System.out.println("xml File Updated");

		}

	static JenkinsFileInputs fillHelperYAMLJenkins() throws Exception{
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        
        JenkinsFileInputs helper =mapper.readValue(new File(fileNameYAMLJenkinsconfig), JenkinsFileInputs.class);
        
		return helper;
	}

	static AllInputs fillHelperYAMLFullConfig() throws Exception{
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        
        AllInputs helper =mapper.readValue(new File(fileNameYAMLFullconfig), AllInputs.class);
        
		return helper;
	}
	
	static void createjob(String fileNameOutput) throws Exception{
		CreateJob helper = fillHelperYAMLCreateJob();
		JenkinsServer jenkins = new JenkinsServer(new URI(helper.getUrl()),helper.getUname(), helper.getPwd());
		
		XML xml = new XMLDocument(new File(fileNameOutput)); 
		String xmlString = xml.toString();

		jenkins.createJob(helper.getJobName(), xmlString,true);
		
	}

	static CreateJob fillHelperYAMLCreateJob() throws Exception{
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        
        CreateJob helper =mapper.readValue(new File(fileNameYAMLCreateJob), CreateJob.class);
        
		return helper;
	}
}
