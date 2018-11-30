package com.zjq.eureka.user.service;

import java.util.List;

import com.zjq.euraka_domain.service.BaseService;
import com.zjq.euraka_domain.user.User;

public interface UserService extends BaseService{
	String hello();
	
	List<User> listAll();
}
