package org.eureka.elasticsearch.service;

import org.eureka.elasticsearch.domain.User;

public interface UserService {

	Long save(User user);
	
	boolean delete(User user);
	
	boolean update(User user);
	
	User queryUserBYId(Long id);
}
