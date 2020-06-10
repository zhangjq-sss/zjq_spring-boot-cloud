package org.elasticsearch.feign.impl;

import org.elasticsearch.feign.UserServiceFeign;
import org.springframework.stereotype.Component;

import com.zjq.domain.common.ResultBody;

@Component
public class UserServiceFeignImpl implements UserServiceFeign {

	@Override
	public ResultBody listAll() {
		ResultBody resultBody = new ResultBody();
		resultBody.setCode("500");
		resultBody.setMessage("调用user模块出错！");
		return resultBody;
	}


}
