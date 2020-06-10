package com.zjq.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="BusiBody", description="请求参数报业务封装类")
@Data
public class BusiBody {

	@ApiModelProperty(value="业务类型code", name="busiCode", example="1000")
	private String busiCode;
	
	@ApiModelProperty(value="客户编号id", name="custId", example="1000")
	private String custId;
}
