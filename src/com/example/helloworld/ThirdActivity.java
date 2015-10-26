package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.NewsAdapter;
import com.example.pojo.NewsItem;
import com.example.util.WebUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ThirdActivity extends Activity {
	
	private List<NewsItem> aList = new ArrayList<NewsItem>();
	private TextView tvUserName;
	private PullToRefreshListView mListView;
	private NewsAdapter adapter;
	private Button btnSubmit;
	private String username;
	private int myId;
	private NewsItem newsItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		tvUserName = (TextView) findViewById(R.id.tv_username);
		mListView = (PullToRefreshListView) findViewById(R.id.peopleListView);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		
		Intent intent = getIntent();
		username = intent.getStringExtra("username");
		tvUserName.setText("hello~"+username+"~");
/*		Toast.makeText(ThirdActivity.this, username, Toast.LENGTH_SHORT).show();*/
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
						NewsItem newsItem = new NewsItem("我是新标题", "我是新内容","我是发布人","我是发布时间");
						aList.add(0,newsItem);
						adapter.notifyDataSetChanged();
						mListView.onRefreshComplete();
					};
				}.execute();
			}
		});
		
		//ListView点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				newsItem = aList.get(position-1);
				myId = newsItem.getId();
				Intent intent = new Intent(ThirdActivity.this,NewsDetailActivity.class);
				intent.putExtra("id",myId);
				startActivity(intent);
				Toast.makeText(ThirdActivity.this, "点击了.."+newsItem.getId(), Toast.LENGTH_SHORT).show();
			}
		});
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ThirdActivity.this,PostInfoActivity.class);
				intent.putExtra("username", username);
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
