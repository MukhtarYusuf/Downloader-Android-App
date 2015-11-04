package com.example.servicesapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Environment;
import android.util.Log;

public class Downloader {
	//URL url;
    static URLConnection conn;
    static int fileSize, lastSlash;
    static String fileName;
    static BufferedInputStream inStream;
    static BufferedOutputStream outStream;
    static File outputFile;
    static FileOutputStream fileStream;
    static String tag = "logging";
 
	
	//Download Method
	public static void download(URL url){
		try
	    {
	        conn = url.openConnection();
	        conn.setUseCaches(false);
	        fileSize = conn.getContentLength();
	 
	        // get the filename
	        lastSlash = url.toString().lastIndexOf('/');
	        fileName = "TestFile.bin";
	        if(lastSlash >=0)
	        {
	            fileName = url.toString().substring(lastSlash + 1);
	            Log.i(tag, "Downloading file: " + fileName);
	        }
	        
	        // start download
	        inStream = new BufferedInputStream(conn.getInputStream());
	        outputFile = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
	        fileStream = new FileOutputStream(outputFile);
	        outStream = new BufferedOutputStream(fileStream);
	        byte[] data = new byte[1024];
	        int bytesRead = 0, totalRead = 0;
	        while((bytesRead = inStream.read(data, 0, data.length)) >= 0)
	        {
	            outStream.write(data, 0, bytesRead);
	 
	            //Update Progress
	            totalRead += bytesRead;
	        }
	 
	        outStream.close();
	        fileStream.close();
	        inStream.close();
	    }
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
