package org.elasticsearch.service;

import org.elasticsearch.domain.EsUser;

public interface UserService {

	Long save(EsUser user);
	
	boolean delete(EsUser user);
	
	boolean update(EsUser user);
	
	EsUser queryUserBYId(Long id);
	
	boolean sychUserEs();
	
	boolean deleteAll();
}
