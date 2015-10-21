package com.example.helloworld;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ThirdActivity extends Activity {
	
	List<PeopleItem> aList = new ArrayList<PeopleItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		initPeople();
		ListView mListView = (ListView) findViewById(R.id.peopleListView);
		PeopleAdapter adapter = new PeopleAdapter(ThirdActivity.this, aList);
		mListView.setAdapter(adapter);
	}

	private void initPeople() {
		
		for (int i = 0; i < 10; i++) {
			aList.add(new PeopleItem("geek_ymv"+i, "18"+i));
			
		}
	}

}
