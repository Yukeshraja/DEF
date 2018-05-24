/*package com.altimetrik.github.service;

import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.altimetrik.github.model.CommitterModel;
import com.altimetrik.github.model.GitHubCheckInModel;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//https://stackoverflow.com/questions/19238715/how-to-set-an-accept-header-on-spring-resttemplate-request
			//https://stackoverflow.com/questions/21723183/spring-resttemplate-to-post-request-with-custom-headers-and-a-request-object
			String tokenValue = "96426853a753efced376853157d6f3297b461497";
			String urlPost = "https://api.github.com/repos/shyamnarayan2001/HelloSpringWorld/contents/test6.txt";
			MultiValueMap<String, String> headers = new LinkedMultiValueMap()<String, String>();
			
			headers.add("Authorization", "token " + tokenValue);
			headers.add("Content-Type", "application/json");
			
			
			//RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			GitHubCheckInModel gitHubCheckInModel = new GitHubCheckInModel();
			//gitHubCheckInModel.setPath("test7.txt);
			//gitHubCheckInModel.setMessage
			
			
			CommitterModel committerModel = new CommitterModel();
			committerModel.setEmail("schacon@gmail.com");
			committerModel.setName("Scott Chacon");

			HttpEntity<CommitterModel> request = new HttpEntity<CommitterModel>(committerModel, headers);

			
			CommitterModel committer = restTemplate.postForObject(urlPost, request, CommitterModel.class);
			System.out.println("committer="+committer.toString());
			
			
			
			
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			
			
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}*/