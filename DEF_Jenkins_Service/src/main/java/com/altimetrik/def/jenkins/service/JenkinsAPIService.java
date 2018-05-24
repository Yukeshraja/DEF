package com.altimetrik.def.jenkins.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import com.altimetrik.def.jenkins.model.JenkinsFileCreationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class JenkinsAPIService {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        BufferedWriter bufferedWriter = null;
        try {
        	JenkinsFileCreationConfig jenkinsconfig = mapper.readValue(new File(JenkinsAPIService.class.getResource("/input/jenkinsconfig.yaml").toURI()), JenkinsFileCreationConfig.class);
        	System.out.println("jenkinsconfig="+jenkinsconfig);
        	String newLine  = System.getProperty("line.separator");
        	File configFile = new File("src/main/resources/output/Jenkinsfile");
        	if (!configFile.exists()) {
        		configFile.createNewFile();
            }
        	
        	Writer writer = new FileWriter(configFile);
            bufferedWriter = new BufferedWriter(writer);
           
            if (jenkinsconfig.getLinux().equals("TRUE"))
            {
	        	if (jenkinsconfig.getCheckout().equals("TRUE"))  
	        		{
	        			System.out.println( "node { "+ newLine+ "\t"+  "checkout scm" + newLine + "\t"+ "env.PATH ="+'"'+"${tool 'Maven3'}/bin:${env.PATH}"+'"' + newLine +"\t"+  "stash excludes: 'target/', includes: '**', name: 'source'");
	        			bufferedWriter.write("node { "+ newLine+ "\t"+  "checkout scm" + newLine + "\t"+ "env.PATH ="+'"'+"${tool 'Maven3'}/bin:${env.PATH}"+'"' + newLine +"\t"+  "stash excludes: 'target/', includes: '**', name: 'source'");
	        		}	
	        	
	        	if (jenkinsconfig.getValidate().equals("TRUE"))  
	    		{
	    			System.out.println("\t"+"stage('validate') {" + newLine + "\t\t"+ "sh 'mvn validate'"+ newLine +"\t"+ "} " );
	    			bufferedWriter.append( newLine + "\t"+"stage('validate') {" + newLine + "\t\t"+ "sh 'mvn validate'"+ newLine +"\t"+ "} " );
	    		}	
	        	if (jenkinsconfig.getCompile().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('compile') {" + newLine + "\t\t"+ "sh 'mvn compile'"+ newLine +"\t"+ "} " );
	    			bufferedWriter.append( newLine +"\t"+"stage('compile') {" + newLine + "\t\t"+ "sh 'mvn compile'"+ newLine +"\t"+ "} " );
	    		}		        	
	        	if (jenkinsconfig.getPkg().equals("TRUE"))  
	    		{
	    			System.out.println("\t"+ "stage('package') {" + newLine +"\t\t"+  " sh 'mvn clean package -DskipTests'" + newLine +"\t" +"}");
	    			bufferedWriter.append( newLine +"\t"+ "stage('package') {" + newLine +"\t\t"+  " sh 'mvn clean package -DskipTests'" + newLine +"\t" +"}");
	    		}
	        	if (jenkinsconfig.getInstall().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('install') {" + newLine + "\t\t"+ "sh 'mvn clean install'"+ newLine +"\t"+ "} " );
	    			bufferedWriter.append( newLine +"\t"+"stage('install') {" + newLine + "\t\t"+ "sh 'mvn clean install'"+ newLine +"\t"+ "} " );
	    		}	
	        	if (jenkinsconfig.getTest().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('test') {" + newLine + "\t\t"+ "parallel 'integration': {"+ newLine +"\t\t\t"+"sh 'mvn clean verify'"+ newLine +"\t\t"+"}, 'quality': {" +newLine +"\t\t\t"+"//sh 'mvn sonar:sonar'"+ newLine +"\t\t\t" + "} "+newLine +"\t"+"} " );
	    			bufferedWriter.append(newLine + "\t"+"stage('test') {" + newLine + "\t\t"+ "parallel 'integration': {"+ newLine +"\t\t\t"+"sh 'mvn clean verify'"+ newLine +"\t\t"+"}, 'quality': {" +newLine +"\t\t\t"+"//sh 'mvn sonar:sonar'"+ newLine +"\t\t\t" + "} "+newLine +"\t"+"} " );
	    		}		
	        	if (jenkinsconfig.getApprove().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('approve') {" + newLine + "\t"+ "timeout(time: 7, unit: 'DAYS') {"+ newLine +"\t\t"+"input message: 'Do you want to deploy?', submitter: 'ops'"+ newLine +"\t"+"}");
	    			bufferedWriter.append( newLine +"\t"+"stage('approve') {" + newLine + "\t"+ "timeout(time: 7, unit: 'DAYS') {"+ newLine +"\t\t"+"input message: 'Do you want to deploy?', submitter: 'ops'"+ newLine +"\t"+"}");
	    		}		      
	        	if (jenkinsconfig.getDeploy().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('deploy') {" + newLine + "\t\t"+ "unstash 'source'"+ newLine +"\t\t"+"sh 'cp target/*.jar /opt/deploy'"+ newLine +"\t"+"}"+ newLine +"}");
	    			bufferedWriter.append( newLine + "\t"+"stage('deploy') {" + newLine + "\t\t"+ "unstash 'source'"+ newLine +"\t\t"+"sh 'cp target/*.jar /opt/deploy'"+ newLine +"\t"+"}"+ newLine +"}");
	    		}		        	
            }	
            if (jenkinsconfig.getWindows().equals("TRUE"))
            {
	        	if (jenkinsconfig.getCheckout().equals("TRUE"))  
	        		{
	        			System.out.println( "node { "+ newLine+ "\t"+  "checkout scm" + newLine + "\t"+ "env.PATH ="+'"'+"${tool 'Maven3'}/bin:${env.PATH}"+'"' + newLine +"\t"+  "stash excludes: 'target/', includes: '**', name: 'source'");
	        			bufferedWriter.write("node { "+ newLine+ "\t"+  "checkout scm" + newLine + "\t"+ "env.PATH ="+'"'+"${tool 'Maven3'}/bin:${env.PATH}"+'"' + newLine +"\t"+  "stash excludes: 'target/', includes: '**', name: 'source'");
	        		}	
	        	if (jenkinsconfig.getValidate().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('validate') {" + newLine + "\t\t"+ "bat 'mvn validate'"+ newLine +"\t"+ "} " );
	    			bufferedWriter.append( newLine +"\t"+"stage('validate') {" + newLine + "\t\t"+ "bat 'mvn validate'"+ newLine +"\t"+ "} " );
	    		}	
	        	if (jenkinsconfig.getCompile().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('compile') {" + newLine + "\t\t"+ "bat 'mvn compile'"+ newLine +"\t"+ "} " );
	    			bufferedWriter.append( newLine +"\t"+"stage('compile') {" + newLine + "\t\t"+ "bat 'mvn compile'"+ newLine +"\t"+ "} " );
	    		}		        	
	        	if (jenkinsconfig.getPkg().equals("TRUE"))  
	    		{
	    			System.out.println("\t"+ "stage('package') {" + newLine +"\t\t"+  " bat 'mvn clean package -DskipTests'" + newLine +"\t" +"}");
	    			bufferedWriter.append( newLine +"\t"+ "stage('package') {" + newLine +"\t\t"+  " bat 'mvn clean package -DskipTests'" + newLine +"\t" +"}");
	    		}
	        	if (jenkinsconfig.getInstall().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('install') {" + newLine + "\t\t"+ "bat 'mvn clean install'"+ newLine +"\t"+ "} " );
	    			bufferedWriter.append( newLine +"\t"+"stage('install') {" + newLine + "\t\t"+ "bat 'mvn clean install'"+ newLine +"\t"+ "} " );
	    		}	
	        	if (jenkinsconfig.getTest().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('test') {" + newLine + "\t\t"+ "parallel 'integration': {"+ newLine +"\t\t\t"+"bat 'mvn clean verify'"+ newLine +"\t\t"+"}, 'quality': {" +newLine +"\t\t\t"+"//bat 'mvn sonar:sonar'"+ newLine +"\t\t\t" + "} "+newLine +"\t"+"} " );
	    			bufferedWriter.append(newLine + "\t"+"stage('test') {" + newLine + "\t\t"+ "parallel 'integration': {"+ newLine +"\t\t\t"+"bat 'mvn clean verify'"+ newLine +"\t\t"+"}, 'quality': {" +newLine +"\t\t\t"+"//bat 'mvn sonar:sonar'"+ newLine +"\t\t\t" + "} "+newLine +"\t"+"} " );
	    		}		
	        	if (jenkinsconfig.getApprove().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('approve') {" + newLine + "\t"+ "timeout(time: 7, unit: 'DAYS') {"+ newLine +"\t\t"+"input message: 'Do you want to deploy?', submitter: 'ops'"+ newLine +"\t"+"}");
	    			bufferedWriter.append( newLine +"\t"+"stage('approve') {" + newLine + "\t"+ "timeout(time: 7, unit: 'DAYS') {"+ newLine +"\t\t"+"input message: 'Do you want to deploy?', submitter: 'ops'"+ newLine +"\t"+"}");
	    		}		      
	        	if (jenkinsconfig.getDeploy().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('deploy') {" + newLine + "\t\t"+ "unstash 'source'"+ newLine +"\t\t"+"bat 'copy .\\\\target\\\\*.jar d:\\\\deploy'"+ newLine +"\t"+"}" + newLine +"}");
	    			bufferedWriter.append( newLine +"\t"+"stage('deploy') {" + newLine + "\t\t"+ "unstash 'source'"+ newLine +"\t\t"+"bat 'copy .\\\\target\\\\*.jar d:\\\\deploy'"+ newLine +"\t"+"}"+ newLine +"}");
	    		}
	        	if (jenkinsconfig.getPrint().equals("TRUE"))  
	    		{
	    			System.out.println( "\t"+"stage('print') {" + newLine + "\t\t"+ "echo 'HELLO WORLD'"+ newLine +"}");
	    			bufferedWriter.append( "\t"+"stage('print') {" + newLine + "\t\t"+ "echo 'HELLO WORLD'"+ newLine +"}");
	    		}
            }	
            
          //  System.out.println(ReflectionToStringBuilder.toString(jenkinsconfig,ToStringStyle.MULTI_LINE_STYLE));
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            try{
                if(bufferedWriter != null) bufferedWriter.close();
            } catch(Exception ex){
                 
            }
        }

    }
}