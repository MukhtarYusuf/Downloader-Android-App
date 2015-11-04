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

public class ImageDownload extends Activity {
	boolean bound;
	CustomService cService;
	ArrayList<String> sURLs = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_download);
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
		getMenuInflater().inflate(R.menu.image_download, menu);
		return true;
	}
	
	//OnClick Method for Start PDF Download
		@SuppressLint("InlinedApi")
		public void startImageDownload(View view){
			Intent intent = new Intent(this, CustomService.class);
			
			//--Retrieve Values From Edit Text Widgets--
			EditText image1URL = (EditText) findViewById(R.id.image1);
			sURLs.add(image1URL.getText().toString());
			
			EditText image2URL = (EditText) findViewById(R.id.image2);
			sURLs.add(image2URL.getText().toString());
			
			EditText image3URL = (EditText) findViewById(R.id.image3);
			sURLs.add(image3URL.getText().toString());
			
			EditText image4URL = (EditText) findViewById(R.id.image4);
			sURLs.add(image4URL.getText().toString());
			
			EditText image5URL = (EditText) findViewById(R.id.image5);
			sURLs.add(image5URL.getText().toString());
			
			EditText image6URL = (EditText) findViewById(R.id.image6);
			sURLs.add(image6URL.getText().toString());
			
			EditText image7URL = (EditText) findViewById(R.id.image7);
			sURLs.add(image7URL.getText().toString());
			
			EditText image8URL = (EditText) findViewById(R.id.image8);
			sURLs.add(image8URL.getText().toString());
			
			EditText image9URL = (EditText) findViewById(R.id.image9);
			sURLs.add(image9URL.getText().toString());
			
			EditText image10URL = (EditText) findViewById(R.id.image10);
			sURLs.add(image10URL.getText().toString());
		
			
			
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


