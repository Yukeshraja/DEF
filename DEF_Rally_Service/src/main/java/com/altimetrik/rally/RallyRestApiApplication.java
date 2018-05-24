package com.altimetrik.rally;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages={"com.altimetrik.rally.api","com.altimetrik.rally.bean"})
public class RallyRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RallyRestApiApplication.class, args);
	}
}
