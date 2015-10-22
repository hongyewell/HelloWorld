package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import com.example.util.WebUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ThirdActivity extends Activity {
	
	List<NewsItem> aList = new ArrayList<NewsItem>();
	String result = "hhh";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		initPeople();
		ListView mListView = (ListView) findViewById(R.id.peopleListView);
		NewsAdapter adapter = new NewsAdapter(ThirdActivity.this, aList);
		mListView.setAdapter(adapter);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				WebUtil webUtil = new WebUtil();
				result = webUtil.getNewsInfo();
				Log.i("info", result);
				
			}
		}).start();
		
	
	}
			
	private void initPeople() {
		
		for (int i = 0; i < 10; i++) {
			aList.add(new NewsItem("±êÌâÑ½"+i, "ÄÚÈÝÑ½"+i));
			
		}
	}

}
