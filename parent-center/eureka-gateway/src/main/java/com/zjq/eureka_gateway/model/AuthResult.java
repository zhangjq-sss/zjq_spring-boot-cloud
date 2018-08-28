package com.zjq.eureka_gateway.model;

import lombok.Data;

@Data
public class AuthResult {
	private String 			code; 		//错误编码
	private String 		message; //错误编码
	private Object 		data;  		//token
	

	public AuthResult() {
		super();
		code ="0000";
		message ="";
		data =null;
	}

	
	
	public boolean isSuccess() {
		return code.equals("0000");
	}

}
