package com.example.servicesapp;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.servicesapp.CustomService.CustomBinder;

public class TextFileDownload extends Activity {

	boolean bound;
	CustomService cService;
	ArrayList<String> sURLs = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_file_download);
	}
	
	@Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_file_download, menu);
		return true;
	}
	
	//OnClick Method for Start PDF Download
			@SuppressLint("InlinedApi")
			public void startTextDownload(View view){
				Intent intent = new Intent(this, CustomService.class);
				
				//--Retrieve Values From Edit Text Widgets--
				EditText text1URL = (EditText) findViewById(R.id.text1);
				sURLs.add(text1URL.getText().toString());
				
				EditText text2URL = (EditText) findViewById(R.id.text2);
				sURLs.add(text2URL.getText().toString());
				
				EditText text3URL = (EditText) findViewById(R.id.text3);
				sURLs.add(text3URL.getText().toString());
			
				//Attach URLs to intent
		    	intent.putStringArrayListExtra("sURLs", sURLs);
		    	
		    	bindService(intent, connection, Context.BIND_AUTO_CREATE);
			}
			
			private ServiceConnection connection = new ServiceConnection() {

		        @Override
		        public void onServiceConnected(ComponentName className,
		                IBinder service) {
		            // Cast the IBinder and get CustomService instance
		            CustomBinder binder = (CustomBinder) service;
		            cService = binder.getService();
		            cService.peformService();
		            bound = true;
		        }
		        @Override
		        public void onServiceDisconnected(ComponentName arg0) {
		            bound = false;
		        }
		    };
}

