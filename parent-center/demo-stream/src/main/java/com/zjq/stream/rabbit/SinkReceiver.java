package com.zjq.stream.rabbit;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(Sink.class)
public class SinkReceiver {
	@StreamListener(Sink.INPUT)
	public void receive(Object payload) {
		log.info("收到信息：" + payload.toString());
	}
}
