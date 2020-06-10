package com.zjq.user.service;

import java.util.List;

import com.zjq.domain.service.BaseService;
import com.zjq.domain.user.User;

public interface UserService extends BaseService{
	String hello();
	
	List<User> listAll();
}
