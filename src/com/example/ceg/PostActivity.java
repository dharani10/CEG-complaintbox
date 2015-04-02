package com.example.ceg;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


	
	public class PostActivity extends Activity {
		
		 ImageView button3;
		 ImageView button4;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.home);
			
			button3=(ImageView)findViewById(R.id.button3);
	        button3.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View arg0){
	        		Intent nextScreen=new Intent(getApplicationContext(),MainActivity.class);
	        		startActivity(nextScreen);
	        		
	        	}
	        });
	        	button4=(ImageView)findViewById(R.id.button4);
		        button4.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View arg1){
	        		Intent nextScreen=new Intent(getApplicationContext(),MailActivity.class);
	        		startActivity(nextScreen);
	        		
	        	}
	        });
		}
	}
