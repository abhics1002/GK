package com.abhi.gk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class Splashstart extends Activity {
	Button start ;
	Button options;
	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	private static final int RESULT_SETTINGS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_splashstart);

		start = (Button) findViewById(R.id.startButton);
		start.setOnClickListener(startQuiz);
		
		options = (Button) findViewById(R.id.optionButton);
		options.setOnClickListener(settingPage);
	
		Log.v("PopulateDBTask", "PopulateDBTask------ starting");
		new PopulateDBTask().execute(null, null, null);
//		new SyncQuestionTask().execute(null,null,null);

	}

	OnClickListener startQuiz = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Splashstart.this, Test_questions.class);
			startActivity(i);
		}
    	
	};
	
	OnClickListener settingPage = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			Log.v("SETTING page", "setting page ---------START");		
			Intent i = new Intent(Splashstart.this, UserSettingActivity.class);
			startActivityForResult(i, RESULT_SETTINGS);
		}
    	
	};
	
	class PopulateDBTask extends AsyncTask<String,Void,Boolean>
	{
		protected void onPostExecute(Boolean result) 
		{
			//		databaseConnector.close();
			Log.v("PopulateDBTask", "-----------------onPostExecute-------END");
			new SyncQuestionTask().execute(null,null,null);

		}

		protected Boolean doInBackground(String... params) 
		{
			// TODO Auto-generated method stub
			Log.v("PopulateDBTask", "doInBackground ----------start");
			try{
				DatabaseConnector databaseConnector = new DatabaseConnector(Splashstart.this);
				databaseConnector.open();

				SharedPreferences prefs = getPreferences(MODE_PRIVATE);
				String savedString = prefs.getString("Is_database_present", "0");
				if(savedString.equals("0"))
				{
					prefs.edit().putString("Is_database_present", "1").commit();

					databaseConnector.populateDatabase();
				}

				Log.v("PopulateDBTask", "-----------doInBackground---------END");
				return true;
			}
			catch(Exception ex)
			{
				Log.v("PopulateDBTask", " EXCEPTION ---doInBackground");
				return false;
			}
		}

	};

    class SyncQuestionTask extends AsyncTask<Object,Object,Boolean>
    {
    	String urls = "https://dl.dropboxusercontent.com/u/105989177/test.txt";
		@Override
		protected Boolean doInBackground(Object... params) {
			// TODO Auto-generated method stub
			
			Log.v("SyncQuestionTask", "--------doInBackground-------START");
			DatabaseConnector databaseConnector = new DatabaseConnector(Splashstart.this);
			//databaseConnector.open();
			try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(urls);
			HttpResponse response = httpClient.execute(request);

			HttpEntity httpEntity = response.getEntity();
			InputStream is = httpEntity.getContent();           

			int max_db_ques_id = databaseConnector.max_question_id();
			Log.v("max_db_ques_id", Integer.toString(max_db_ques_id));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null && !isCancelled()) 
			{
				String output="";
				output += line;
				String[] ques = output.split("@@");
//				Log.v("string from server", ques[0] );
//				Log.v("string from server", ques[1] );
				
				int current_ques_id = Integer.parseInt(ques[0]);
				if (current_ques_id > max_db_ques_id)
				{
				
				// put condition for check if the max number of question in DB and update if current index is larger than the max_question_id on DB.
				databaseConnector.insertQuestion(Integer.parseInt(ques[0]), ques[1].trim(), ques[2].trim(), ques[3].trim(), ques[4].trim(), ques[5].trim(), ques[6].trim(), ques[7].trim());
				}
			}
			is.close();
			reader.close();
			databaseConnector.close();
			is = null;
			reader = null;
			httpEntity = null;
			response = null;
			request = null;
			httpClient = null;

			//return output;
			}
			catch(Exception ex)
			{					
				Log.v("EXCEPTION", ex.getMessage());
				return false;
			}
			return true;
		}
    	
		protected void onPostExecute(Boolean result) {

			Log.v("SyncQuestionTask", "-----------------onPostExecute-------END");
			Log.v("SyncQuestionTask", "-----------------calling UpdateDBQuestionTask ");
			new UpdateDBQuestionTask().execute(null,null,null);
				}
    }
    
    
    // update questions from server. 
    // this API will read questions from dropbox link and then update question in users phone database. 
    class UpdateDBQuestionTask extends AsyncTask<Object,Object,Object>
    {

		@Override
		protected Object doInBackground(Object... arg0) {
			
			Log.v("UpdateDBQuestionTask", "--------doInBackground-------START");
			String update_question_link = "https://dl.dropboxusercontent.com/u/105989177/GK_update_question.txt";
			DatabaseConnector databaseConnector = new DatabaseConnector(Splashstart.this);
			//databaseConnector.open();
			try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(update_question_link);
			HttpResponse response = httpClient.execute(request);

			HttpEntity httpEntity = response.getEntity();
			InputStream is = httpEntity.getContent();           

			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null && !isCancelled()) 
			{
				String output="";
				output += line;
				String[] ques = output.split("@@");
//				Log.v("string from server", ques[0] );
//				Log.v("string from server", ques[1] );
				
//				int current_ques_id = Integer.parseInt(ques[0]);
				Log.v("update question", ques[0]);
				Log.v("update question", ques[1]);
				Log.v("update question", ques[2]);
				Log.v("update question", ques[3]);
				Log.v("update question", ques[4]);
				Log.v("update question", ques[5]);
				Log.v("update question", ques[6]);
				Log.v("update question", ques[7]);
				int current_ques_id = Integer.parseInt(ques[0]);
				if (databaseConnector.getOneQuestion(current_ques_id))
				{
				// put condition for check if the max number of question in DB and update if current index is larger than the max_question_id on DB.
				databaseConnector.updateQuestion(current_ques_id, ques[1].trim(), ques[2].trim(), ques[3].trim(), ques[4].trim(), ques[5].trim(), ques[6].trim().toLowerCase(), ques[7].trim().toLowerCase());
				Log.v("update question", "quaestion updated");
				}
			}
			is.close();
			reader.close();
			databaseConnector.close();
			is = null;
			reader = null;
			httpEntity = null;
			response = null;
			request = null;
			httpClient = null;

			//return output;
			}
			catch(Exception ex)
			{					
				Log.v("EXCEPTION", ex.getMessage());
			}
			return null;
			
		}
    	
    }
    
    public Boolean get_preferences_sync_category()
    {
    	SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	return sharedPrefs.getBoolean("prefsyncquestions", false);
    }
}
