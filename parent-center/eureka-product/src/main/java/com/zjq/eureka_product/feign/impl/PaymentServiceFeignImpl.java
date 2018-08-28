package com.zjq.eureka_product.feign.impl;

import org.springframework.stereotype.Component;

import com.zjq.eureka_product.feign.PaymentServiceFeign;

@Component
public class PaymentServiceFeignImpl implements PaymentServiceFeign {

	@Override
	public String hello() {
		return "error";
	}

}
