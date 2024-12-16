package com.mublesstgo.ms_api_gtw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsApiGtwApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsApiGtwApplication.class, args);
	}

}
