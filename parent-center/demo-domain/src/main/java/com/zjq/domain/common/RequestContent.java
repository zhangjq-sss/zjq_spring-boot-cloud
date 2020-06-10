package com.zjq.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="RequestContent", description="请求参数报文封装类")
@Data
public class RequestContent {
	
	@ApiModelProperty(value="请求内容", name="body", example="")
	private Object body;
	
	@ApiModelProperty(value="请求业务信息", name="busiBody", example="")
	private BusiBody busiBody;
}
