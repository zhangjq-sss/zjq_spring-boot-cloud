package com.zjq.eureka_payment;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PaymentApp.class)
public class PaymentTest extends TestCase {
	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	public void testEnvironmentProperties() {
		//jasypt方式
		System.out.println("----------------" + stringEncryptor.encrypt("123456"));
		System.out.println("----------------" + stringEncryptor.encrypt("root"));
		System.out.println("----------------" + stringEncryptor.encrypt("guest"));
		//对称加密方式
		//对称请求加密地址 http://localhost:2222/encrypt 解密地址 http://localhost:2222/decrypt 
	}
}
