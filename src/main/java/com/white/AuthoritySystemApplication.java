package com.white;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ServletComponentScan
public class AuthoritySystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(AuthoritySystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AuthoritySystemApplication.class, args);
		logger.info("Spring Boot is Running!");
	}
}
