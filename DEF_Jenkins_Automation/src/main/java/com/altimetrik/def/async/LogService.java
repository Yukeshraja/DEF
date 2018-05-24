package com.altimetrik.def.async;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

/*    
 	private final RestTemplate restTemplate;

    public LogService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
*/

    @Async
    public CompletableFuture<String> getLogs(URI url, String username, String password) throws InterruptedException {
        logger.info("Looking up " + url);
        JenkinsHttpClient client = new JenkinsHttpClient(url, username, password);
        Build build = new Build(1, url.getRawPath());
        build.setClient(client);
        BuildWithDetails details = null;
        try {
        	details = build.details();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     
        String consoleOutputText = null;
		try {
			consoleOutputText = details.getConsoleOutputText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
/*        
  		ConsoleLog consoleOutputText = 
        assertThat(consoleOutputText.getConsoleLog()).isEqualTo(text);
        assertThat(consoleOutputText.getCurrentBufferSize()).isEqualTo(textLength);
        assertThat(consoleOutputText.getHasMoreData()).isFalse();
*/        
        logger.info("Looking up " + consoleOutputText);
        return CompletableFuture.completedFuture(consoleOutputText);

    }

}
