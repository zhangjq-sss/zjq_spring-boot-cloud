package com.zjq.eureka_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(PaymentApp.class, args);
    }
}
