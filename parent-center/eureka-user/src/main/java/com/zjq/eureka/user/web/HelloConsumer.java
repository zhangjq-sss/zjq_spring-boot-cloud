package com.zjq.eureka.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zjq.eureka.user.service.UserService;

@RestController
public class HelloConsumer {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String index() {
		return userService.hello();
	}
	
	@RequestMapping(value="/getUserById", method=RequestMethod.GET)
	public Object getUserById(Integer id) {
		return userService.getById(id);
	}
	
}
