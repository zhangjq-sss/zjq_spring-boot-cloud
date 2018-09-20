//package com.zjq.eureka_product.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class Receiver {
//	@RabbitListener(queues="hello")
//	@RabbitHandler
//    public void process(String hello) {
//        log.info("Receiver : " + hello);
//    }
//
//}
