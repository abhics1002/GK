package com.abhi.gk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

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
	
		
	/*	new Handler().postDelayed(new Runnable() {

			
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(Splashstart.this, Test_questions.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);*/
	}

	OnClickListener startQuiz = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			QuestionBank qb = new QuestionBank();
//			Log.v("SPLASH SCREEN", "STARTING print_QuestionBank");
//			qb.print_QuestionBank();
//			Log.v("SPLASH SCREEN", "RETURNED FROM print_QUESTIONBNAK");
			Intent i = new Intent(Splashstart.this, Test_questions.class);
			startActivity(i);
		}
    	
	};
	
	OnClickListener settingPage = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//QuestionBank qb = new QuestionBank();
			Log.v("SETTING page", "setting page ---------START");
//			Intent i = new Intent(Splashstart.this, QuizSettings.class);
//			startActivity(i);
			
			Intent i = new Intent(Splashstart.this, UserSettingActivity.class);
			startActivityForResult(i, RESULT_SETTINGS);
		}
    	
	};
}
