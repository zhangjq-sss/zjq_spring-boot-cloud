package com.zjq.eureka.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zjq.euraka_domain.user.User;
import com.zjq.eureka.user.config.DbContextHolder.DbType;
import com.zjq.eureka.user.config.ReadOnlyConnection;
import com.zjq.eureka.user.dao.UserMapper;
import com.zjq.eureka.user.redis.RedisService;
import com.zjq.eureka.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private UserMapper userMapper;
	
	@Autowired
	private RedisService redisService;

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
	@ReadOnlyConnection(DbType.READ1)
	public Object getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(Object object) {
		int id = -1;
		User user = (User) object;
		if (checkoutInsert(user)) {
			user.setCreateTime(new Date());
			user.setDeleted(false);
			user.setVersion(0);
			userMapper.insert(user);
			id = user.getId();
			//存缓存
			redisService.set(id+"", JSON.toJSON(user).toString());
		}
		return id;
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
		return true;
	}

	@Override
	public boolean checkoutUpdate(Object object) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean checkoutDelete(Object object) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
