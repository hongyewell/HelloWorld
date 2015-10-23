package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.adapter.NewsAdapter;
import com.example.pojo.NewsItem;
import com.example.util.WebUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ThirdActivity extends Activity {
	
	private List<NewsItem> aList = new ArrayList<NewsItem>();
	private PullToRefreshListView mListView;
	private NewsAdapter adapter;
	private Button btnSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		mListView = (PullToRefreshListView) findViewById(R.id.peopleListView);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		//异步消息处理封装类 AsyncTask
		new AsyncTask<Void, Void,List<NewsItem>>() {

			@Override
			protected List<NewsItem> doInBackground(Void... arg0) {
				WebUtil webUtil = new WebUtil();
				aList = webUtil.getNewsInfo();
				return aList;
			}
			protected void onPostExecute(java.util.List<NewsItem> result) {
			    
			    adapter = new NewsAdapter(ThirdActivity.this, aList);
			    mListView.setAdapter(adapter);
				
			};

		}.execute();
		
		//PullToRefreshListView下拉刷新响应事件
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				
				new AsyncTask<Void, Void, Void>(){

					@Override
					protected Void doInBackground(Void... arg0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return null;
					}
					protected void onPostExecute(Void result) {
						NewsItem newsItem = new NewsItem("我是新标题", "我是新内容");
						aList.add(0,newsItem);
						adapter.notifyDataSetChanged();
						mListView.onRefreshComplete();
					};
					
				}.execute();
			}
		});
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ThirdActivity.this,PostInfoActivity.class);
				startActivity(intent);
			}
		});
		/*//子线程访问网络,匿名类
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				WebUtil webUtil = new WebUtil();
				result = webUtil.getNewsInfo();
				Log.i("hongye", result.get(1).getContent());
			}
		}).start();*/
		
	
	}
}
