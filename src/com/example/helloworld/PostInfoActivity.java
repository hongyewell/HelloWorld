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
import android.widget.Toast;

import com.example.util.WebUtil;

public class PostInfoActivity extends Activity{
	
	private Button postInfoButton;
	private EditText editTitle;
	private EditText editContent;
	private String inputTitle,inputContent,username;
	private String  inputTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_info);
		
		postInfoButton = (Button) findViewById(R.id.btn_postInfo);
		editTitle = (EditText) findViewById(R.id.edit_title);
		editContent = (EditText) findViewById(R.id.edit_content);
		Intent intent = getIntent();
	    username = intent.getStringExtra("username");
		
		postInfoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				inputTitle = editTitle.getText().toString();
				inputContent = editContent.getText().toString();
				
				if (inputTitle == null || inputTitle.length() <= 0 || inputContent == null || inputContent.length() <= 0) {
					Toast.makeText(PostInfoActivity.this, "您可能还没输入标题或内容o(>﹏<)o",Toast.LENGTH_SHORT ).show();
				}
				else 
				{
					new AsyncTask<Void, Void, Void>(){

						@Override
						protected Void doInBackground(Void... arg0) {
							
								Date date = new Date();
								SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								inputTime = simpleDate.format(date);
								WebUtil webUtil = new WebUtil();
								webUtil.postNewsInfo(inputTitle, inputContent,username,inputTime);
							return null;
						}
						
					}.execute();
					//跳转至主页...
					Intent intent = new Intent(PostInfoActivity.this,ThirdActivity.class);
					intent.putExtra("username", username);
					startActivity(intent);
				}
				
			}
		});
	}

}
