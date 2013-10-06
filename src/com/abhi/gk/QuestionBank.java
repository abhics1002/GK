package com.abhi.gk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import android.util.Log;

public class QuestionBank {

	
	
	public InputStream OpenHttpConnection(String urlString) 
		    throws IOException
		    {
		Log.v("OpenHttpConnection","---------------START");
		        InputStream in = null;
		        int response = -1;
		                
		        URL url = new URL(urlString); 
		        URLConnection conn = url.openConnection();
		                  
		        if (!(conn instanceof HttpURLConnection))                     
		            throw new IOException("Not an HTTP connection");
		         
		        try{
		            HttpsURLConnection httpConn = (HttpsURLConnection) conn;
		            httpConn.setAllowUserInteraction(false);
		            httpConn.setInstanceFollowRedirects(true);
		            httpConn.setRequestMethod("GET");
		            httpConn.connect(); 
		 
//		            response = httpConn.getResponseCode();                 
//		            if (response == HttpURLConnection.HTTP_OK) {
		                in = httpConn.getInputStream();                                 
//		            }                     
		        }
		        catch (Exception ex)
		        {
		            throw new IOException("Error connecting");            
		        }
		        Log.v("OpenHttpConnection","---------------END");
		        return in;     
		    }
	
	
	public String DownloadQuestionBank(String URL)
    {	Log.v("DownloadQuestionBank","---------------START");
		int BUFFER_SIZE = 2000; //??
		InputStream in = null;
		try 
		{
			in = OpenHttpConnection(URL);
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}
		
		InputStreamReader isr = new InputStreamReader(in);
		
		int charRead;
		String str = "";
		char[] inputBuffer = new char[BUFFER_SIZE];          
		try {
			while ((charRead = isr.read(inputBuffer))>0)
			{                    
				//---convert the chars to a String---
				String readString = String.copyValueOf(inputBuffer, 0, charRead);
				str += readString;
				inputBuffer = new char[BUFFER_SIZE];
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}    
		Log.v("DownloadQuestionBank","---------------END");
		return str;        
    }
	
	
	
	public void print_QuestionBank()
	{	
		Log.v("PRINT_QUESTIONBANK","---------------START");
		String URL = "https://www.dropbox.com/s/gmftmrr90cwu4pj/QuestionBank.txt";
		String questions_from_file = DownloadQuestionBank(URL);
		String[] splits = questions_from_file.split("\n");
		
		for(int i =0 ; i<splits.length ; i++)
			Log.v("PRINT_QUESTIONBANK",splits[i]);
		
	}
	
	public void populate_QUIZ_db()
	{
		
	}
	
	public void update_QUIZ_db()
	{
		
	}
}
