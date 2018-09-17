package com.zjq.eureka_product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zjq.eureka_product.mq.Sender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ProductApp.class)
public class HelloApplicationTest {
	@Autowired
    private Sender sender;
 
    @Test
    public void hello() throws Exception {
        sender.send();
    }

}
