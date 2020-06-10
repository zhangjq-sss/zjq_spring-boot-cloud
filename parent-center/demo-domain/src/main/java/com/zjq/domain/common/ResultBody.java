package com.zjq.domain.common;

import lombok.Data;

@Data
public class ResultBody {

	private String code;
	
	private Object data;
	
	private String message;
	
	public void success() {
		this.code = "00";
		this.message = "";
	}
}
