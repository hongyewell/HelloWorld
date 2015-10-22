package com.example.util;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class WebUtil {
	
		 String myURL = "http://120.25.125.185/helloworld/HelloData";
		
		public  String getNewsInfo(){
			String result = "";
				
				try {
					//HttpClient是一个接口，无法创建它的实例，通常情况下都会创建一个DefaultHttpClient的实例
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet =  new HttpGet(myURL);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						HttpEntity entity = httpResponse.getEntity();
						result = EntityUtils.toString(entity,"utf-8");
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			return result;
			
		}
		
}
