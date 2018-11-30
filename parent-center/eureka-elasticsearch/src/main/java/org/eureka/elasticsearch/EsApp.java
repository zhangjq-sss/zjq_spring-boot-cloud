package org.eureka.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableElasticsearchRepositories
public class EsApp {

    public static void main(String[] args) {
        SpringApplication.run(EsApp.class,args);
    }
}