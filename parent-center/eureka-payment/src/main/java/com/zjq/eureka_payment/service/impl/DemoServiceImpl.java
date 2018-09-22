package com.zjq.eureka_payment.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.TxTransaction;
import com.zjq.eureka_payment.client.UserClient;
import com.zjq.eureka_payment.dao.TestMapper;
import com.zjq.eureka_payment.entity.Test;
import com.zjq.eureka_payment.service.DemoService;

/**
 * Created by lorne on 2017/6/26.
 */
@Service
public class DemoServiceImpl implements DemoService {


    @Autowired
    private UserClient userClient;


    @Autowired
    private TestMapper testMapper;


    @Override
    public List<Test> list() {
        return testMapper.findAll();
    }

    @Override
    @TxTransaction(isStart = true)
    @Transactional
    public int save() {

        int rs1 = testMapper.save("mybatis-hello-1");

        int rs2 = userClient.save();

//        int v = 100/0;

        return rs1+rs2;
    }
}
