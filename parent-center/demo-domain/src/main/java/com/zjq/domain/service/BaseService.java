package com.zjq.domain.service;

public interface BaseService {

	Object getById(Integer id);
	
	int insert(Object object);
	
	int update(Object object);
	
	int deleteByKey(Integer id);
	
	boolean checkoutInsert(Object object);
	
	boolean checkoutUpdate(Object object);
	
	boolean checkoutDelete(Object object);
	
}
