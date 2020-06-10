package com.zjq.payment.service.impl;

import com.zjq.payment.client.UserClient;
import com.zjq.payment.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {


    @Autowired
    private UserClient userClient;


}
