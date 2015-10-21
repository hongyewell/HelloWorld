package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends Activity {
	
	private PullToRefreshListView lV;
	private List<MainItem> mListItems;
	private MainAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mListItems = new ArrayList<MainItem>();
        mListItems.add(new MainItem(R.drawable.ic_launcher, "hongyewell"));
        
        lV = (PullToRefreshListView) findViewById(R.id.mylv);
        
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
						MainItem mainItem = new MainItem(R.drawable.ic_launcher, "geek_ymv");
						mListItems.add(0,mainItem);
						adapter.notifyDataSetChanged();
						lV.onRefreshComplete();
					};
					
				}.execute();

				
			}
		});
      
    }

}
