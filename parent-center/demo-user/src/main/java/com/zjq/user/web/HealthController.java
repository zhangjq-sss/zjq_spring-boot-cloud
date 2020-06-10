package com.zjq.user.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
public class HealthController {
	
	@Autowired
	private DiscoveryClient client;
	
	@Value("${server.port}")
    String port;

	@Value("${spring.application.name}")
	String name;
	
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String index() {
		List<ServiceInstance> instance = client.getInstances("service-user");
		instance.forEach(in -> System.out.println(in.getHost()));
		instance.forEach(in -> System.out.println(in.getServiceId()));
		instance.forEach(in -> System.out.println(in.getMetadata().get("qg")));
		instance.forEach(in -> System.out.println(in.getMetadata().get("baz")));
		return "hello word, port: " + port;
	}
	
	@RequestMapping(value="test_post", method=RequestMethod.POST)
	public String testPost(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		if (null != request) {
			Set<String> paramsKey = request.getParameterMap().keySet();
			for (String key : paramsKey) {
				params.put(key, request.getParameter(key));
			}
		}
	    System.out.println(params);    
		return "success";
	}
	
	@RequestMapping(value="test_env")
	public String testEnv() {
		return name + "====" + port;
	}

	@RequestMapping(value="/health")
	public String health() {
		log.info("check health .........................");
		return "success";
	}
}
