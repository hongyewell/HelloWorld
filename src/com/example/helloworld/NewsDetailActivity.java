package com.example.helloworld;

import com.example.pojo.NewsItem;
import com.example.util.WebUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NewsDetailActivity extends Activity {
	
	private TextView tvTitle,tvContent,tvAuthor,tvTime;
	private NewsItem newsItem;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		
		
		
		tvTitle = (TextView) findViewById(R.id.tv_title_detail);
		tvContent = (TextView) findViewById(R.id.tv_content_detail);
		tvAuthor = (TextView) findViewById(R.id.tv_author_detail);
		tvTime = (TextView) findViewById(R.id.tv_time_detail);
		
		
		new AsyncTask<Void, Void, NewsItem>() {

			@Override
			protected NewsItem doInBackground(Void... arg0) {
				Intent intent = getIntent();
				id = intent.getExtras().getInt("id");
				newsItem = new NewsItem();
				WebUtil webUtil = new WebUtil();
				newsItem = webUtil.getNewsDeatil(id);
				return newsItem;
			}
			protected void onPostExecute(NewsItem result) {
				tvTitle.setText(result.getTitle());
				tvContent.setText(result.getContent());
				tvAuthor.setText(result.getAuthor());
				tvTime.setText(result.getTime());
			};
		}.execute();
		
	}

}
