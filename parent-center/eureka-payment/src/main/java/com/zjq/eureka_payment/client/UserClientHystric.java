package com.zjq.eureka_payment.client;

import org.springframework.stereotype.Component;

import com.zjq.eureka_payment.entity.Test;

import java.util.List;


@Component
public class UserClientHystric implements UserClient {

    @Override
    public int save() {
        System.out.println("进入断路器-save。。。");
        throw new RuntimeException("save 保存失败.");
    }
}
