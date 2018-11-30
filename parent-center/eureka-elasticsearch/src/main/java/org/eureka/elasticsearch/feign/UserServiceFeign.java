package org.eureka.elasticsearch.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjq.euraka_domain.common.ResultBody;

@FeignClient("eureka-user")
public interface UserServiceFeign {

	@RequestMapping("/listAll")
	ResultBody listAll();
}
