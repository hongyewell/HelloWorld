package com.example.helloworld;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.util.WebUtil;

public class PostInfoActivity extends Activity{
	
	private Button postInfoButton;
	private EditText editTitle;
	private EditText editContent;
	private EditText editAuthor;
	private String inputTitle,inputContent,inputAuthor;
	private String  inputTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_info);
		
		postInfoButton = (Button) findViewById(R.id.btn_postInfo);
		editTitle = (EditText) findViewById(R.id.edit_title);
		editContent = (EditText) findViewById(R.id.edit_content);
		editAuthor = (EditText) findViewById(R.id.edit_author);
		
		postInfoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				/*Toast.makeText(PostInfoActivity.this, inputTitle, Toast.LENGTH_SHORT).show();*/		
				new AsyncTask<Void, Void, Void>(){

					@Override
					protected Void doInBackground(Void... arg0) {
						inputTitle = editTitle.getText().toString();
						inputContent = editContent.getText().toString();
						inputAuthor = editAuthor.getText().toString();
						Date date = new Date();
						SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						inputTime = simpleDate.format(date);
						WebUtil webUtil = new WebUtil();
						webUtil.postNewsInfo(inputTitle, inputContent,inputAuthor,inputTime);
						return null;
					}
					
				}.execute();
				//Ìø×ªÖÁÖ÷Ò³...
				Intent intent = new Intent(PostInfoActivity.this,ThirdActivity.class);
				startActivity(intent);
			}
		});
	}

}
