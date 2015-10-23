package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.adapter.MainAdapter;
import com.example.pojo.MainItem;
import com.example.util.WebUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends Activity {
	
	private PullToRefreshListView lV;
	private List<MainItem> mListItems;
	private MainAdapter adapter;
	private Button btnMain;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mListItems = new ArrayList<MainItem>();
        mListItems.add(new MainItem(R.drawable.leaf, "hongyewell"));
        
        lV = (PullToRefreshListView) findViewById(R.id.mylv);
        btnMain = (Button) findViewById(R.id.btn_main);
        
        adapter = new MainAdapter(MainActivity.this, mListItems);
        lV.setAdapter(adapter);
        
        lV.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				
				new AsyncTask<Void, Void, Void>() {

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
						MainItem mainItem = new MainItem(R.drawable.pig, "geek_ymv");
						mListItems.add(0,mainItem);
						adapter.notifyDataSetChanged();
						lV.onRefreshComplete();
					};
					
				}.execute();

				
			}
		});
      
        btnMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(intent);
			}
		});
    }

}
