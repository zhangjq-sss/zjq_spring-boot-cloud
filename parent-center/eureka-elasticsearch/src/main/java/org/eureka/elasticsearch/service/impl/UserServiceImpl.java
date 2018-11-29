package org.eureka.elasticsearch.service.impl;

import org.eureka.elasticsearch.dao.UserDao;
import org.eureka.elasticsearch.domain.User;
import org.eureka.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Long save(User user) {
		if (user!=null&&user.getId()!=null) {
			return userDao.save(user).getId();
		}
		return 0l;
	}

	@Override
	public boolean delete(User user) {
		if (user!=null&&user.getId()!=null) {
			userDao.delete(user.getId());
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		if (user!=null&&user.getId()!=null) {
			userDao.save(user);
			return true;
		}
		return false;
	}

	@Override
	public User queryUserBYId(Long id) {
		return userDao.findById(id);
	}

}
