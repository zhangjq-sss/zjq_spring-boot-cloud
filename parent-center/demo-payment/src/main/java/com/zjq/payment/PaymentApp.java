package com.zjq.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class PaymentApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(PaymentApp.class, args);
    }
    
    @Autowired
	private Environment env;

}
