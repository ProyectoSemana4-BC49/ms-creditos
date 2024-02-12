package com.nttdatabc.mscreditos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCreditosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCreditosApplication.class, args);
	}

}
