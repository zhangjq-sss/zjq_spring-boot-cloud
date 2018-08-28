package com.zjq.eureka_product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zjq.eureka_product.feign.PaymentServiceFeign;

@RestController
public class HelloConsumer {
	
	@Autowired
	private PaymentServiceFeign paymentService;
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public String index() {
		return paymentService.hello();
	}
	
}
