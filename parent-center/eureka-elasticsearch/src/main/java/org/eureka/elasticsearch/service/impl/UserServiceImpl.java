package org.eureka.elasticsearch.service.impl;

import java.util.List;

import org.eureka.elasticsearch.dao.UserDao;
import org.eureka.elasticsearch.domain.EsUser;
import org.eureka.elasticsearch.feign.UserServiceFeign;
import org.eureka.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjq.euraka_domain.common.ResultBody;
import com.zjq.euraka_domain.user.User;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserServiceFeign userServiceFeign;
	
	@Override
	public Long save(EsUser user) {
		if (user!=null&&user.getId()!=null) {
			return userDao.save(user).getId();
		}
		return 0l;
	}

	@Override
	public boolean delete(EsUser user) {
		if (user!=null&&user.getId()!=null) {
			userDao.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(EsUser user) {
		if (user!=null&&user.getId()!=null) {
			userDao.save(user);
			return true;
		}
		return false;
	}

	@Override
	public EsUser queryUserBYId(Long id) {
		return userDao.findById(id);
	}

	@Override
	public boolean sychUserEs() {
		ResultBody resultBody = userServiceFeign.listAll();
		List<Object> users = (List<Object>) resultBody.getData();
		if (users!=null) {
			for (Object obj : users) {
			    User user = JSON.parseObject(JSONObject.toJSONString(obj,true),User.class);
				EsUser esUser = EsUser.getEsUser(user);
				userDao.save(esUser);
			}
			
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAll() {
		userDao.deleteAll();
		return true;
	}

}
