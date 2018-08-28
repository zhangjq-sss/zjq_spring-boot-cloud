package com.zjq.euraka_core;

import java.util.Map;

public class AsyCallBackThreadTask implements Runnable{
	
	private String url;
	
	private Map<String, String> paramMap;
	
	@Override
	public void run() {
		try {
			AsyncClientHttp.asyHttpPost(paramMap, url, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

}
