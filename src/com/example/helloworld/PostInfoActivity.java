package com.example.helloworld;

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
	private String inputTitle,inputContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_info);
		
		postInfoButton = (Button) findViewById(R.id.btn_postInfo);
		editTitle = (EditText) findViewById(R.id.edit_title);
		editContent = (EditText) findViewById(R.id.edit_content);
		
		postInfoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				/*Toast.makeText(PostInfoActivity.this, inputTitle, Toast.LENGTH_SHORT).show();*/		
				new AsyncTask<Void, Void, Void>(){

					@Override
					protected Void doInBackground(Void... arg0) {
						inputTitle = editTitle.getText().toString();
						inputContent = editContent.getText().toString();
						WebUtil webUtil = new WebUtil();
						webUtil.postNewsInfo(inputTitle, inputContent);
						return null;
					}
					
				}.execute();
				//��ת����ҳ...
				Intent intent = new Intent(PostInfoActivity.this,ThirdActivity.class);
				startActivity(intent);
			}
		});
	}

}
