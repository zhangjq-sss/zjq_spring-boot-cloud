package com.zjq.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zjq.product.feign.PaymentServiceFeign;

@RefreshScope
@RestController
public class HelloConsumer {
	
	@Value("${version}")
    String version;
	
	@Autowired
	private PaymentServiceFeign paymentService;
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public String index() {
		return paymentService.hello();
	}
	
	@RequestMapping(value="/version", method=RequestMethod.GET)
	public String test_version() {
		return version;
	}
	
}
