package com.zjq.eureka.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zjq.euraka_domain.user.User;
import com.zjq.eureka.user.dao.UserMapper;
import com.zjq.eureka.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private UserMapper userMapper;

	@HystrixCommand(fallbackMethod = "hiError")
	@Override
	public String hello() {
		log.info("-------开始执行-------");
		String result = restTemplate.getForEntity("http://eureka-payment/hello", String.class).getBody();
		return result;
	}
	
	public String hiError() {
        return "hi,sorry,error!";
    }

	@Override
	public Object getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(Object object) {
		User user = (User) object;
		return userMapper.insert(user);
	}

	@Override
	public int update(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkoutInsert(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkoutUpdate(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkoutDelete(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
