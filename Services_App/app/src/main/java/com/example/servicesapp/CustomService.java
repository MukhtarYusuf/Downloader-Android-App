package com.example.servicesapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class CustomService extends Service {
	Intent intent;
	ArrayList<String> sURLs = new ArrayList<String>();
	ArrayList<URL> URLs = new ArrayList<URL>();
	
	private final IBinder binder = new CustomBinder();
	
	public class CustomBinder extends Binder{
		//Method to Return Instance of CustomService
		CustomService getService(){
			return CustomService.this;
		}
	}
	
	//Override onBind() Callback Method
	@Override
	public IBinder onBind(Intent intent){
		this.intent = intent;
		return binder;
	}
	
	//--AsyncTask Class--
	private class DownloadTask extends AsyncTask<ArrayList<URL>, Integer, Long> {
	    
		@Override
		protected void onPreExecute(){
			Toast toast = Toast.makeText(getApplicationContext(), "Download Started...", Toast.LENGTH_LONG);
	    	toast.show();
		}
		
		@Override
		protected Long doInBackground(ArrayList<URL>... urls) {
	    	 ArrayList<URL> URLs = urls[0];
	         int count = URLs.size();
	         long totalSize = 0;
	         for (int i = 0; i < count; i++) {
	        	 if(URLs.get(i) != null){
	        		 Downloader.download(URLs.get(i));
	        		 totalSize += Downloader.fileSize;
	        	 }
	             publishProgress((int) ((i / (float) count) * 100));
	         }
	         return totalSize;
	     }
		@Override
	     protected void onProgressUpdate(Integer... progress){ 
			
			/*Toast toast = Toast.makeText(getApplicationContext(), progress[0], Toast.LENGTH_LONG);
	         toast.show();*/
	     }
		@Override
	     protected void onPostExecute(Long result) {
	         Toast toast = Toast.makeText(getApplicationContext(),"Downloaded " + result + " Bytes", Toast.LENGTH_LONG);
	         toast.show();
	     }
	}
	
	@SuppressLint("NewApi")
	public void peformService(){
		sURLs = intent.getStringArrayListExtra("sURLs");
		
		//Iterate to make ArrayList of URLS
		for(int i=0; i< sURLs.size(); i++){
			try {
				if(!sURLs.get(i).isEmpty()){
					URLs.add(new URL(sURLs.get(i)));
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new DownloadTask().execute(URLs);
	}
}
