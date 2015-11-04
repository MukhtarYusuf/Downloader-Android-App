
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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.servicesapp.CustomService.CustomBinder;

public class PDFDownload extends Activity {
	
	CustomService cService;
	ArrayList<String> sURLs = new ArrayList<String>();
	String tag = "logging";
	
	/*public final static String PDF1_URL = "com.mukhtaryusuf.PDF1_URL";
	public final static String PDF2_URL = "com.mukhtaryusuf.PDF2_URL";
	public final static String PDF3_URL = "com.mukhtaryusuf.PDF3_URL";
	public final static String PDF4_URL = "com.mukhtaryusuf.PDF4_URL";
	public final static String PDF5_URL = "com.mukhtaryusuf.PDF5_URL";
	public final static String ACTIVITY_NAME = "com.mukhtaryusuf.ACTIVITY_NAME";*/
	
	boolean bound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdfdownload);
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
		getMenuInflater().inflate(R.menu.pdfdownload, menu);
		return true;
	}
	
	//OnClick Method for Start PDF Download
	@SuppressLint("InlinedApi")
	public void startPDFDownload(View view){
		Intent intent = new Intent(this, CustomService.class);
		
		//--Retrieve Values From Edit Text Widgets--
		EditText pdf1URL = (EditText) findViewById(R.id.pdf1);
		sURLs.add(pdf1URL.getText().toString());
	
		EditText pdf2URL = (EditText) findViewById(R.id.pdf2);
		sURLs.add(pdf2URL.getText().toString());
	
		EditText pdf3URL = (EditText) findViewById(R.id.pdf3);
		sURLs.add(pdf3URL.getText().toString());
	
		EditText pdf4URL = (EditText) findViewById(R.id.pdf4);
		sURLs.add(pdf4URL.getText().toString());
	
		EditText pdf5URL = (EditText) findViewById(R.id.pdf5);
		sURLs.add(pdf5URL.getText().toString());
		
		//Attach URLs to intent
    	intent.putStringArrayListExtra("sURLs", sURLs);
    	
    	bindService(intent, connection, Context.BIND_ADJUST_WITH_ACTIVITY);
    	Log.i(tag, "PDFDownload Activity with Context.BIND_ADJUST_WITH_ACTIVITY");
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