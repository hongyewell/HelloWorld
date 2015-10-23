package com.example.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.pojo.NewsItem;;


public class WebUtil {
	
		 String myURL = "http://10.0.2.2:8080/helloworld/HelloData";
		
		public  List<NewsItem> getNewsInfo(){
			List<NewsItem> mList = new ArrayList<NewsItem>();
				
				try {
					//HttpClient是一个接口，无法创建它的实例，通常情况下都会创建一个DefaultHttpClient的实例
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet =  new HttpGet(myURL);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						Log.i("yeye", "get运行了");
						HttpEntity entity = httpResponse.getEntity();
						String result = EntityUtils.toString(entity,"utf-8");
						//JSON字符串解析
						try {
							JSONArray jsonArray = new JSONArray(result);
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject nObject = (JSONObject) jsonArray.get(i);
								NewsItem news = new NewsItem();
								news.setTitle(nObject.getString("myTitle"));
								news.setContent(nObject.getString("myContent"));
								mList.add(news);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			return mList;
			
		}
		
		public void postNewsInfo(String title,String content){
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/helloworld/PublishData");
			List<NameValuePair> mList = new ArrayList<NameValuePair>();
			mList.add(new BasicNameValuePair("title", title));
			mList.add(new BasicNameValuePair("content", content));
		
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(mList,"utf-8"));
					HttpResponse response = httpClient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						Log.i("yeye", "我开始接受了.."+title+content);
					}
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			
			
		}
		
}
