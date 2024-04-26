package com.example.serviceregistrationanddiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistrationAndDiscoveryServerApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistrationAndDiscoveryServerApp.class, args);
	}
}
