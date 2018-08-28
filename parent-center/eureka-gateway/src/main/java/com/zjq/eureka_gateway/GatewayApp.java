package com.zjq.eureka_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.zjq.eureka_gateway.filter.AccessTokenFilter;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApp {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApp.class, args);
	}
	
	@Bean
	public AccessTokenFilter getAccessTokenFilter() {
		return new AccessTokenFilter();
	}
}
