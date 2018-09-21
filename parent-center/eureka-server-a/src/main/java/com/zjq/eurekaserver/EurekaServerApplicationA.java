package com.zjq.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplicationA {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplicationA.class, args);
	}
}
