package com.zjq.eureka.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zjq.euraka_domain.common.RequestContent;
import com.zjq.euraka_domain.common.ResultBody;
import com.zjq.euraka_domain.user.User;
import com.zjq.eureka.user.aspect.SpringLog;
import com.zjq.eureka.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户-调用第三方和回调接API")
@RestController
@RefreshScope
public class HelloConsumer {
	
	@Autowired
	private UserService userService;
	
	@Value("${version}")
    String version;
	
	@ApiOperation(value = "测试支付第三方", notes = "测试-支付第三方调用",  response = String.class)
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String index() {
		return userService.hello();
	}
	
	@ApiOperation(value = "通过userId查询", notes = "通过userId查询",  response = Object.class)
	@RequestMapping(value="/getUserById", method=RequestMethod.GET)
	public ResultBody getUserById(Integer userId) {
		ResultBody resultBody = new ResultBody();
		resultBody.success();
		resultBody.setData(userService.getById(userId));
		return resultBody;
	}
	
	@ApiOperation(value = "用户信息插入", notes = "用户信息插入",  response = ResultBody.class)
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="body", name = "userBody", value = "用户基本信息", required = true,  dataType = "RequestContent")
	 })
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ResultBody insert(@RequestBody  RequestContent userBody) {
		User user = JSON.parseObject(JSON.toJSONString(userBody.getBody()), User.class);
		ResultBody resultBody = new ResultBody();
		resultBody.success();
		resultBody.setData(userService.insert(user));
		return resultBody;
	}
	
	@ApiOperation(value = "测试配置文件刷新", notes = "测试配置文件刷新",  response = String.class)
	@RequestMapping(value="/version", method=RequestMethod.GET)
	public String test_version() {
		return version;
	}
	
	@RequestMapping(value="/insert_lcn", method=RequestMethod.GET)
	public int insert() {
		User user = new User();
		user.setUserName("lcn");
		user.setPassword("111111");
		return userService.insert(user);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public int insertGet() {
		User user = new User();
		user.setUserName("lcn");
		user.setPassword("111111");
		return userService.insert(user);
	}
	
	@SpringLog(requestUrl="/testAspect",action="测试日志注解",title="注解")
	@RequestMapping(value="/testAspect", method=RequestMethod.GET)
	public String testAspect() {
		
		return "success";
	}
}
