package com.zjq.eureka_payment.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lorne on 2017/6/27.
 */
@FeignClient(value = "eureka-user",fallback = UserClientHystric.class)
public interface UserClient {

    @RequestMapping(value = "/insert_lcn",method = RequestMethod.GET)
    int save();
}
