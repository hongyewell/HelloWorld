package com.example.helloworld;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SecondActivity extends Activity {
	
	private Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		List<People> peopleList = new ArrayList<People>();
		for (int i = 0; i <4; i++) {
			peopleList.add(new People(R.drawable.pig,"我是标题"+i,"我是内容"+i));
		}
	
		ListView listView = (ListView) findViewById(R.id.myListView);
		submit = (Button) findViewById(R.id.btn_submit);
		listView.setAdapter(new MyAdapter(this, peopleList));
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
				startActivity(intent);
			}
		});
	}

}
