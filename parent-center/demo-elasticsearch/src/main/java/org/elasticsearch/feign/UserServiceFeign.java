package org.elasticsearch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjq.domain.common.ResultBody;

@FeignClient("service-user")
public interface UserServiceFeign {

	@RequestMapping("/listAll")
	ResultBody listAll();
}
