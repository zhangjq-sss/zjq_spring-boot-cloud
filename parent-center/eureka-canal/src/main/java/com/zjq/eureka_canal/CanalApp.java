package com.zjq.eureka_canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableElasticsearchRepositories(basePackages = "com.zjq.eureka_canal.elasticsearch.dao")
public class CanalApp {
	public static void main(String[] args) {
		SpringApplication.run(CanalApp.class, args);
		
	}
}
