package com.zjq.eureka_product.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-payment")
public interface PaymentServiceFeign {

	@RequestMapping("/hello")
	String hello();
}
