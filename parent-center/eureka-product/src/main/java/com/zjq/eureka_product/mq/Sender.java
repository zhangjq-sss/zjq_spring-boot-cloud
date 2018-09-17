package com.zjq.eureka_product.mq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sender {
	@Autowired
    private AmqpTemplate amqpTemplate;
 
    public void send() {
        String context = "hello " + new Date();
        log.info("Sender : " + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }

}
