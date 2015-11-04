package com.example.servicesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Method to go to PDF Downloads
	public void goToPDF(View view){
		Intent intent = new Intent(this, PDFDownload.class);
		startActivity(intent);
	}
	//Method to go to Image Downloads
	public void goToImage(View view){
		Intent intent = new Intent(this, ImageDownload.class);
		startActivity(intent);
	}
	//Method to go to Text File Downloads
	public void goToText(View view){
		Intent intent = new Intent(this, TextFileDownload.class);
		startActivity(intent);
	}
	
	public void close(View view){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
