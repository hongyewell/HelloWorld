package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.util.WebUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ThirdActivity extends Activity {
	
	private List<NewsItem> aList = new ArrayList<NewsItem>();
	private PullToRefreshListView mListView;
	private NewsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		mListView = (PullToRefreshListView) findViewById(R.id.peopleListView);
		//�첽��Ϣ�����װ�� AsyncTask
		new AsyncTask<Void, Void,List<NewsItem>>() {

			@Override
			protected List<NewsItem> doInBackground(Void... arg0) {
				WebUtil webUtil = new WebUtil();
				aList = webUtil.getNewsInfo();
				Log.i("ym", "׼��..");
			    webUtil.postNewsInfo();
				return aList;
			}
			protected void onPostExecute(java.util.List<NewsItem> result) {
			    
			    adapter = new NewsAdapter(ThirdActivity.this, aList);
			    mListView.setAdapter(adapter);
				
			};

		}.execute();
		
		//PullToRefreshListView����ˢ����Ӧ�¼�
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
						NewsItem newsItem = new NewsItem("�����±���", "����������");
						aList.add(0,newsItem);
						adapter.notifyDataSetChanged();
						mListView.onRefreshComplete();
					};
					
				}.execute();
			}
		});
		
		/*//���̷߳�������,������
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
