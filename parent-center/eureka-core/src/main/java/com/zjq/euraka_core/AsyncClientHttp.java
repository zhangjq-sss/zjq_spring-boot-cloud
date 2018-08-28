package com.zjq.euraka_core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;

public class AsyncClientHttp {
	public static void main(final String[] args) throws Exception {  
		Map<String, String> map = new HashMap<>();
		map.put("order_code", "20189283764633333");
		map.put("rsp_code", "00");
//        asyHttpPost(map, "http://localhost:2010/test_post", 0);  
        AsyCallBackThreadTask asyCallBackThreadTask = new AsyCallBackThreadTask();
        asyCallBackThreadTask.setParamMap(map);
        asyCallBackThreadTask.setUrl("http://localhost:2010/test_post");
        AsyCallBackTask.startCallBack(asyCallBackThreadTask);
        System.out.println("ok");
    }

	public static void asyHttpPost(Map<String, String> map, String url, int sleep) throws Exception{
		if (sleep>2) {
			System.out.println("发送失败");
			return;
		}
		if (sleep>0) {
			
			System.out.println("重新发送：" + sleep + "0秒后");
		}
		Thread.sleep(sleep*10000);
		sleep++;
		// 默认的配置  
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
//        BufferedReader in = null;  
        try {  
			httpclient.start();
			HttpPost request = new HttpPost(url);
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				// 给参数赋值
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
			request.setEntity(entity);
			Future<HttpResponse> future = httpclient.execute(request, null);
			// 获取结果
			HttpResponse response = future.get();
			System.out.println("Status:"+response.getStatusLine().getStatusCode());
//			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//			StringBuffer sb = new StringBuffer("");
//			String line = null;
//			String NL = System.getProperty("line.separator");
//			while ((line = in.readLine()) != null) {
//				sb.append(line + NL);
//			}
//			in.close();
//			System.out.println("网页内容："+EntityUtils.toString(response.getEntity(), "utf-8"));
//			System.out.println("Response content: " + sb.toString());
//			if (!"success".equals(sb.toString())) {
//				asyHttpPost(map, url, sleep);
//			}
			if (!(response.getStatusLine().getStatusCode()==200)) {
				asyHttpPost(map, url, sleep);
			}
        } catch (Exception e) {
        	asyHttpPost(map, url, sleep);
		}
        finally {  
             try {
				httpclient.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}  
        }
	}  
	
	
}
