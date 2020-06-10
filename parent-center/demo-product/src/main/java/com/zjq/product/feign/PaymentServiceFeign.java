package com.zjq.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-payment")
public interface PaymentServiceFeign {

	@RequestMapping("/hello")
	String hello();
}
