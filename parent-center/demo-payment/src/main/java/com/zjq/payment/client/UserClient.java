package com.zjq.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-user",fallback = UserClientHystric.class)
public interface UserClient {

    @RequestMapping(value = "/insert_lcn",method = RequestMethod.GET)
    int save();
}
