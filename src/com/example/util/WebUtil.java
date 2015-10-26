package com.example.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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

import com.example.pojo.NewsItem;
import com.google.gson.Gson;


public class WebUtil {
	
	//String getURL = "http://120.25.125.185/helloworld/HelloData";
		 String getURL = "http://10.0.2.2:8080/helloworld/HelloData";
		 String postURL = "http://10.0.2.2:8080/helloworld/PublishData";
		 String loginURL = "http://10.0.2.2:8080/helloworld/LoginController";
		 String NewsDetailURL = "http://10.0.2.2:8080/helloworld/NewsDetailController";
		 
		public  List<NewsItem> getNewsInfo(){
			List<NewsItem> mList = new ArrayList<NewsItem>();
				
				try {
					//HttpClient��һ���ӿڣ��޷���������ʵ����ͨ������¶��ᴴ��һ��DefaultHttpClient��ʵ��
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet =  new HttpGet(getURL);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						/*Log.i("yeye", "get������");*/
						HttpEntity entity = httpResponse.getEntity();
						String result = EntityUtils.toString(entity,"utf-8");
						//JSON�ַ�������
						try {
							JSONArray jsonArray = new JSONArray(result);
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject nObject = (JSONObject) jsonArray.get(i);
								NewsItem news = new NewsItem();
								news.setId(nObject.getInt("myId"));
								news.setTitle(nObject.getString("myTitle"));
								news.setContent(nObject.getString("myContent"));
								news.setAuthor(nObject.getString("myAuthor"));
								news.setTime(nObject.getString("myTime"));
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
		/**
		 * ��ȡ��Ϣ����
		 * @param newsId
		 * @return newsItem
		 */
		public NewsItem getNewsDeatil(int newsId){
			NewsItem newsItem = new NewsItem();
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(NewsDetailURL);
			List<NameValuePair> mList = new ArrayList<NameValuePair>();
			mList.add(new BasicNameValuePair("newsId", String.valueOf(newsId)));
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(mList,"utf-8"));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					String result = EntityUtils.toString(entity,"utf-8");
					try {
						JSONObject nObject = new JSONObject(result);
						newsItem.setId(nObject.getInt("myId"));
						newsItem.setTitle(nObject.getString("myTitle"));
						newsItem.setContent(nObject.getString("myContent"));
						newsItem.setAuthor(nObject.getString("myAuthor"));
						newsItem.setTime(nObject.getString("myTime"));
						return newsItem;
					} catch (JSONException e) {
						e.printStackTrace();
					}
					/*Gson gson = new Gson();
					newsItem = gson.fromJson(result,NewsItem.class);*/
					Log.i("yeye", "���ͳɹ�...");
					
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return newsItem;
		}
		
		public void postNewsInfo(String title,String content,String author, String time){
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(postURL);
			List<NameValuePair> mList = new ArrayList<NameValuePair>();
			mList.add(new BasicNameValuePair("title", title));
			mList.add(new BasicNameValuePair("content", content));
			mList.add(new BasicNameValuePair("author", author));
			mList.add(new BasicNameValuePair("time", time));
		
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(mList,"utf-8"));
					HttpResponse response = httpClient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						/*Log.i("yeye", "�ҿ�ʼ������.."+title+content);*/
					}
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		public String userLogin(String username,String password){
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(loginURL);
			List<NameValuePair> user = new ArrayList<NameValuePair>();
			user.add(new BasicNameValuePair("username", username));
			user.add(new BasicNameValuePair("password", password));
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(user,"utf-8"));
				try {
					HttpResponse response = httpClient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						/*Log.i("yeye", "��¼�ɹ�");*/
						HttpEntity entity = response.getEntity();
						String result = EntityUtils.toString(entity,"utf-8");
						Gson gson = new Gson();
						String state = gson.fromJson(result, String.class);
						return state;
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
}
