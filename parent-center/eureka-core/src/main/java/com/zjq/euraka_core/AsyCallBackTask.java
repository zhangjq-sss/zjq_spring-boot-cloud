package com.zjq.euraka_core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyCallBackTask{
	
	private static ExecutorService executorService ;
	
	
	static {
		executorService = Executors.newFixedThreadPool(20);
	}
	
	public static void startCallBack(AsyCallBackThreadTask asyCallBackThreadTask) {
		executorService.execute(asyCallBackThreadTask);
	}

}
