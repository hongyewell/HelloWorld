package com.example.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.helloworld.NewsItem;


public class WebUtil {
	
		 String myURL = "http://120.25.125.185/helloworld/HelloData";
		
		public  List<NewsItem> getNewsInfo(){
			List<NewsItem> mList = new ArrayList<NewsItem>();
				
				try {
					//HttpClient��һ���ӿڣ��޷���������ʵ����ͨ������¶��ᴴ��һ��DefaultHttpClient��ʵ��
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet =  new HttpGet(myURL);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						HttpEntity entity = httpResponse.getEntity();
						String result = EntityUtils.toString(entity,"utf-8");
						//JSON�ַ�������
						try {
							JSONArray jsonArray = new JSONArray(result);
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject nObject = (JSONObject) jsonArray.get(i);
								NewsItem news = new NewsItem();
								news.setTitle(nObject.getString("myTitle"));
								news.setContent(nObject.getString("myContent"));
								Log.i("ym", nObject.getString("myTitle"));
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
		
}
