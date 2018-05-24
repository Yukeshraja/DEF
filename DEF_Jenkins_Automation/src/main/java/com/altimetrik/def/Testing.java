/*package com.altimetrik.def;

import org.apache.commons.validator.UrlValidator;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class Testing {

	public static void main(String args[])
	{

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
			    .withDockerHost("tcp://192.168.91.181:5000")
			    .withApiVersion("1.23")
			    .withRegistryUrl("http://192.168.91.181:5000")
			    
			    .build();
			DockerClient docker = DockerClientBuilder.getInstance(config).build();
			System.out.println("Docker : "+docker.listImagesCmd().withShowAll(true));
		
		
		String[] schemes = {"http","https","ssh"}; // DEFAULT schemes = "http", "https", "ftp"
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid("http://192.168.91.61:8080")) {
		   System.out.println("URL is valid");
		} else {
		   System.out.println("URL is invalid");
		}
			
	}
}
*/