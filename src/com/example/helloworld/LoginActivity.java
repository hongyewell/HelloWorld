package com.example.helloworld;

import com.example.util.WebUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private EditText editUserName;
	private EditText editPassword;
	private Button btnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		editUserName = (EditText) findViewById(R.id.edit_username);
		editPassword = (EditText) findViewById(R.id.edit_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				new AsyncTask<Void, Void, String>() {
					String username = editUserName.getText().toString();
					String password = editPassword.getText().toString();
					
					@Override
					protected String doInBackground(Void... arg0) {
						WebUtil webUtil = new WebUtil();
						String result = webUtil.userLogin(username, password);
						
						return result;
					}
					
					protected void onPostExecute(String result) {
						if (result.equals("ok")) {
							Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(LoginActivity.this,ThirdActivity.class);
							intent.putExtra("username", username);
							startActivity(intent);
						}
						else {
							Toast.makeText(LoginActivity.this, "亲，请检查用户名或密码是否正确~", Toast.LENGTH_SHORT).show();
						}
					};
				}.execute();
				
				
			}
		});
		
		
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				WebUtil webUtil = new WebUtil();
				webUtil.userLogin("ym", "19920316");
				
			}
		}).start();*/
	}

}
