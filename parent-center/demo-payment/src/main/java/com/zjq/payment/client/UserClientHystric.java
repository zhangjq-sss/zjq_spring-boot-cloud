package com.zjq.payment.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UserClientHystric implements UserClient {

    @Override
    public int save() {
        log.info("进入断路器-save。。。");
        throw new RuntimeException("save 保存失败.");
    }
}
